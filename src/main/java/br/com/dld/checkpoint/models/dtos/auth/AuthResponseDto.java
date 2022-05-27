package br.com.dld.checkpoint.models.dtos.auth;

import java.time.LocalDateTime;

/**
 *
 * @author David Duarte
 */
public class AuthResponseDto {
    
    private LocalDateTime requestedIn = LocalDateTime.now();
    private String token;

    public AuthResponseDto(String token) {
        this.token = "Bearer " + token;
    }

    public LocalDateTime getRequestedIn() {
        return requestedIn;
    }

    public void setRequestedIn(LocalDateTime requestedIn) {
        this.requestedIn = requestedIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
