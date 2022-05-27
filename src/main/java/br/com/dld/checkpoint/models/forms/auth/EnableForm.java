package br.com.dld.checkpoint.models.forms.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Duarte
 */
public class EnableForm {

    @NotNull
    @NotEmpty
    private String identification;

    @NotNull
    @NotEmpty
    private String activationCode;

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
