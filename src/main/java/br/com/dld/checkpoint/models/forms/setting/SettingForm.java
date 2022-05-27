package br.com.dld.checkpoint.models.forms.setting;

import br.com.dld.checkpoint.models.entities.Account;
import br.com.dld.checkpoint.models.entities.Setting;
import br.com.dld.checkpoint.models.enums.SettingKey;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**setting_save
 *
 * @author David Duarte
 */
@Data
public class SettingForm {

    @NotNull
    private SettingKey key;

    @NotNull
    @NotBlank
    private String value;

    public Setting convert(Account account) {
        Setting s = new Setting();
        s.setAccountId(account);
        s.setSettingKey(this.key);
        s.setCurrentValue(this.value);
        
        return s;
    }
}
