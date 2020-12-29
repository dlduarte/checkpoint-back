package br.com.dld.checkpoint.config.security.auth;

import br.com.dld.checkpoint.model.Account;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author David Duarte
 */
public class Credential implements UserDetails {

    private final Account account;

    public Credential(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Default");
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !account.isAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !account.isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !account.isCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return account.isEnabled();
    }

    public Account getAccount() {
        return account;
    }
}
