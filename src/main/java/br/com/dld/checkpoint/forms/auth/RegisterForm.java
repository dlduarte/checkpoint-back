package br.com.dld.checkpoint.forms.auth;

import br.com.dld.checkpoint.entities.Account;
import br.com.dld.checkpoint.util.AuthenticationHandler;
import br.com.dld.checkpoint.util.RandomString;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Duarte
 */
public class RegisterForm {
    
    @NotNull @NotEmpty
    private String name;

    @NotNull @NotEmpty @Email
    private String email;

    @NotNull @NotEmpty
    private String username;

    @NotNull @NotEmpty
    private String password;
    
    public Account convert() throws Exception {
        Account account = new Account();
        account.setName(name);
        account.setEmail(email);
        account.setUsername(username);
        account.setPassword(AuthenticationHandler.Password.encode(password));
        account.setCreationDate(LocalDate.now());
        account.setActivationCode(RandomString.createAlphanumeric(20));
        
        return account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
