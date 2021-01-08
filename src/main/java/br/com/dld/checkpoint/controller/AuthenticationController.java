package br.com.dld.checkpoint.controller;

import br.com.dld.checkpoint.dto.auth.RecoveryResponseDto;
import br.com.dld.checkpoint.dto.errors.ClientErrors;
import br.com.dld.checkpoint.dto.errors.ServerErrors;
import br.com.dld.checkpoint.form.auth.EnableForm;
import br.com.dld.checkpoint.form.auth.LoginForm;
import br.com.dld.checkpoint.form.auth.RecoverForm;
import br.com.dld.checkpoint.form.auth.RegisterForm;
import br.com.dld.checkpoint.service.AuthenticationService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author David Duarte
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody @Valid LoginForm form) {
        try {
            return ResponseEntity.ok(service.authenticate(form));
        } catch (Exception e) {

            if (e instanceof AuthenticationException) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ClientErrors(e));
            }

            return ServerErrors.build(e);
        }
    }

    @GetMapping("/recovery/{accountId}")
    public ResponseEntity recovery(@PathVariable Long accountId) {
        try {

            String email = service.requestRecovery(accountId);
            if (email != null && !email.isEmpty()) {
                return ResponseEntity.ok(new RecoveryResponseDto(email));
            }

            return ClientErrors.build("Conta não encontrada!");
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }

    @PostMapping("/recovery")
    public ResponseEntity recovery(@RequestBody @Valid RecoverForm form) {
        try {
            List<String> errors = service.recovery(form);

            if (errors.isEmpty()) {
                return ResponseEntity.ok().build();
            }

            return ClientErrors.build(errors);
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterForm form) {
        try {
            List<String> errors = service.register(form);

            if (errors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }

            return ClientErrors.build(errors);
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }

    @PostMapping("/activation")
    public ResponseEntity activation(@RequestBody @Valid EnableForm form) {
        try {
            if (service.activation(form)) {
                return ResponseEntity.ok().build();
            }

            return ClientErrors.build("Código de ativação inválido");
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }
}
