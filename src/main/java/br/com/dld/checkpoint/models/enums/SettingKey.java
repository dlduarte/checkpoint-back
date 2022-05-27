package br.com.dld.checkpoint.models.enums;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author David Duarte
 */
@Getter
public enum SettingKey {
    START_OF_OFFICE_HOUR("Início do expediente", "time"),
    GIT_LAB_TOKEN("Token de acesso do GitLab", "text");

    private final String description;
    private final String type;

    SettingKey(String description, String type) {
        this.description = description;
        this.type = type;
    }
}
