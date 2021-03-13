package br.com.dld.checkpoint.forms.setting;

import br.com.dld.checkpoint.entities.Account;
import br.com.dld.checkpoint.entities.Setting;
import br.com.dld.checkpoint.entities.enums.SettingKey;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Duarte
 */
public class SettingForm {

    @NotNull
    private SettingKey key;

    @NotNull
    @NotBlank
    private String value;

    public SettingKey getKey() {
        return key;
    }

    public void setKey(SettingKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Setting convert(Account account) {
        Setting s = new Setting();
        s.setAccountId(account);
        s.setSettingKey(this.key);
        s.setCurrentValue(this.value);
        
        return s;
    }
}
