package br.com.dld.checkpoint.dto.setting;

import br.com.dld.checkpoint.entities.Setting;

/**
 *
 * @author David Duarte
 */
public class SettingDto {

    private String key;
    private String value;

    public SettingDto() {
    }

    public SettingDto(Setting setting) {
        this.key = setting.getSettingKey().name();
        this.value = setting.getCurrentValue();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
