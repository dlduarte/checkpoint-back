package br.com.dld.checkpoint.forms.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Duarte
 */
public class RecoverForm {

    @NotNull
    @NotEmpty
    private String code;

    @NotNull
    @NotEmpty
    private String password;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
