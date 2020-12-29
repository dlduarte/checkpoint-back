package br.com.dld.checkpoint.dto.authentication;

import java.time.LocalDateTime;

/**
 *
 * @author David Duarte
 */
public class AuthResponseDto {
    
    private LocalDateTime requestedIn = LocalDateTime.now();
    private TokenDto token;

    public LocalDateTime getRequestedIn() {
        return requestedIn;
    }

    public void setRequestedIn(LocalDateTime requestedIn) {
        this.requestedIn = requestedIn;
    }

    public TokenDto getToken() {
        return token;
    }

    public void setToken(TokenDto token) {
        this.token = token;
    }
}
