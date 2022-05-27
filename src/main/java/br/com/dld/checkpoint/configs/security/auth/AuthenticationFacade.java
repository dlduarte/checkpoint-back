package br.com.dld.checkpoint.configs.security.auth;

import br.com.dld.checkpoint.models.entities.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author David Duarte
 */
@Component
public class AuthenticationFacade {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Account getAccount() {
        return (Account) getAuthentication().getPrincipal();
    }
}
