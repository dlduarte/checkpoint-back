package br.com.dld.checkpoint.configs.security;

import br.com.dld.checkpoint.configs.security.auth.Credential;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @author David Duarte
 */
@Service
public class TokenService {

    @Value("${siemrest.jwt.expiration}")
    private String expiration;

    @Value("${siemrest.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Credential credential = (Credential) authentication.getPrincipal();

        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + Long.parseLong(this.expiration));

        return Jwts.builder()
                .setIssuer("Checkpoint API 1.0.0")
                .setSubject(String.valueOf(credential.getAccount().getId()))
                .setIssuedAt(agora)
                .setExpiration(expiracao)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
