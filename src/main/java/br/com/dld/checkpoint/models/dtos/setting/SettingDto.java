package br.com.dld.checkpoint.models.dtos.setting;

import br.com.dld.checkpoint.models.entities.Setting;
import lombok.Data;

/**
 @author David Duarte */
@Data
public class SettingDto {

	private String description;

	private String type;
	private String key;
	private String value;

	public SettingDto() {
	}

	public SettingDto(Setting setting) {
		this.key = setting.getSettingKey().name();
		this.value = setting.getCurrentValue();
		this.description = setting.getSettingKey().getDescription();
		this.type = setting.getSettingKey().getType();
	}
}
