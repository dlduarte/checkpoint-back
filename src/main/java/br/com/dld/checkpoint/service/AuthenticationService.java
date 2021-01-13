package br.com.dld.checkpoint.service;

import br.com.dld.checkpoint.config.security.TokenService;
import br.com.dld.checkpoint.dto.auth.AuthResponseDto;
import br.com.dld.checkpoint.form.auth.EnableForm;
import br.com.dld.checkpoint.form.auth.LoginForm;
import br.com.dld.checkpoint.form.auth.RecoverForm;
import br.com.dld.checkpoint.form.auth.RegisterForm;
import br.com.dld.checkpoint.model.Account;
import br.com.dld.checkpoint.model.RecoverPassword;
import br.com.dld.checkpoint.repository.AccountRepository;
import br.com.dld.checkpoint.repository.RecoverPasswordRepository;
import br.com.dld.checkpoint.service.email.EmailService;
import br.com.dld.checkpoint.service.email.dto.Email;
import br.com.dld.checkpoint.util.AuthenticationHandler;
import br.com.dld.checkpoint.util.RandomString;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David Duarte
 */
@Service
public class AuthenticationService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RecoverPasswordRepository recoverPasswordRepository;

    public AuthResponseDto authenticate(LoginForm form) throws Exception, RuntimeException {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        Authentication auth = authManager.authenticate(dadosLogin);
        String token = tokenService.gerarToken(auth);

        return new AuthResponseDto(token);
    }

    public String requestRecovery(long id) throws Exception, RuntimeException {
        Optional<Account> optional = accountRepository.findById(id);

        if (optional.isPresent()) {
            Account account = optional.get();

            Optional<RecoverPassword> optional2 = recoverPasswordRepository.findFirstByAccountId_Id(id);

            if (optional2.isPresent()) {
                recoverPasswordRepository.delete(optional2.get());
            }

            RecoverPassword recover = new RecoverPassword();
            recover.setCode(RandomString.createAlphanumeric(20));
            recover.setAccountId(account);
            recover.setExpiration(LocalDateTime.now().plus(30, ChronoUnit.MINUTES));

            recoverPasswordRepository.saveAndFlush(recover);

            Email email = new Email();
            email.setCredential(AuthenticationHandler.Email.credential());
            email.setTitle("Checkpoint | Confirmação de conta");
            email.setContent("teste");
            email.setHtml(false);

            HttpStatus status = EmailService.send(email);

            if (!status.equals(HttpStatus.ACCEPTED)) {
                throw new Exception("Falha no envio do email");
            }

            return AuthenticationHandler.Email.mask(account.getEmail());
        }

        return null;
    }

    public List<String> recovery(RecoverForm form) throws Exception, RuntimeException {
        Optional<RecoverPassword> optional = recoverPasswordRepository.findFirstByCode(form.getCode());

        List<String> errors = new ArrayList();
        if (optional.isPresent()) {
            RecoverPassword recover = optional.get();
            errors = AuthenticationHandler.Password.validate(form.getPassword());

            if (errors.isEmpty()) {
                if (recover.getExpiration().isBefore(LocalDateTime.now())) {
                    errors.add("Código expirado!");
                }

                if (errors.isEmpty()) {
                    Account account = recover.getAccountId();
                    account.setPassword(AuthenticationHandler.Password.encode(form.getPassword()));
                    account.setCredentialsExpired(false);
                    accountRepository.saveAndFlush(account);
                }

                recoverPasswordRepository.delete(recover);
            }
        } else {
            errors.add("Código não encontrado!");
        }

        return errors;
    }

    @Transactional
    public List<String> register(RegisterForm form) throws Exception, RuntimeException {
        List<String> errors = AuthenticationHandler.Register
                .validate(form, accountRepository);

        if (errors.isEmpty()) {
            Account account = form.convert();
            accountRepository.save(account);
        }

        return errors;
    }

    public boolean requestActivation() throws Exception, RuntimeException {
        /* >>> Fazer envio de email aqui <<< */
        return false;
    }

    public boolean activation(EnableForm form) throws Exception, RuntimeException {
        Optional<Account> optional;
        if (form.getIdentification().contains("@")) {
            optional = accountRepository.findByEmail(form.getIdentification());
        } else {
            optional = accountRepository.findByUsername(form.getIdentification());
        }

        if (optional.isPresent()) {
            Account account = optional.get();

            if (account.getActivationCode().equals(form.getActivationCode())) {
                account.setEnabled(true);
                accountRepository.saveAndFlush(account);
                return true;
            }
        }

        return false;
    }
}
