package br.com.dld.checkpoint.config.security;

import br.com.dld.checkpoint.model.Account;
import br.com.dld.checkpoint.repository.AccountRepository;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author David Duarte
 */
public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AccountRepository accountRepository;

    public AutenticacaoTokenFilter(TokenService tokenService, AccountRepository accountRepository) {
        this.tokenService = tokenService;
        this.accountRepository = accountRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest hsr, HttpServletResponse hsr1, FilterChain fc) throws ServletException, IOException {
        String token = retrieveToken(hsr);

        if (tokenService.isTokenValido(token)) {
            autenticar(token);
        }

        fc.doFilter(hsr, hsr1);
    }

    private String retrieveToken(HttpServletRequest hsr) {
        String token = hsr.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

    private void autenticar(String token) {
        Long id = tokenService.getId(token);
        Optional<Account> optional = accountRepository.findById(id);

        if (optional.isPresent()) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    optional.get(),
                    null,
                    AuthorityUtils.createAuthorityList("Default")
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
