package br.com.dld.checkpoint.models.dtos.gitlab.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class References{

	@JsonProperty("short")
	private String jsonMemberShort;

	@JsonProperty("relative")
	private String relative;

	@JsonProperty("full")
	private String full;
}