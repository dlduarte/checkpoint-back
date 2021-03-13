package br.com.dld.checkpoint.forms.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author David Duarte
 */
public class LoginForm {

    @NotNull
    @NotEmpty
    private String identification;

    @NotNull
    @NotEmpty
    private String password;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(this.identification, this.password);
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
