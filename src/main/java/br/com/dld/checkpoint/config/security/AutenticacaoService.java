package br.com.dld.checkpoint.config.security;

import br.com.dld.checkpoint.config.security.auth.Credential;
import br.com.dld.checkpoint.model.Account;
import br.com.dld.checkpoint.repository.AccountRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author David Duarte
 */
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String identification) throws UsernameNotFoundException {

        Optional<Account> optAccount;
        if (identification.contains("@")) {
            optAccount = accountRepository.findByEmail(identification);
        } else {
            optAccount = accountRepository.findByUsername(identification);
        }

        if (optAccount.isPresent()) {
            return new Credential(optAccount.get());
        }

        throw new UsernameNotFoundException("Dados Inválidos!");
    }
}
