package br.com.dld.checkpoint.models.dtos.gitlab.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Links{

	@JsonProperty("notes")
	private String notes;

	@JsonProperty("self")
	private String self;

	@JsonProperty("award_emoji")
	private String awardEmoji;

	@JsonProperty("project")
	private String project;
}