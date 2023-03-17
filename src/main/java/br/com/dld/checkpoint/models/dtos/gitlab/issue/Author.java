package br.com.dld.checkpoint.models.dtos.gitlab.issue;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author{

	@JsonProperty("avatar_url")
	private String avatarUrl;

	@JsonProperty("web_url")
	private String webUrl;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	@JsonProperty("state")
	private String state;

	@JsonProperty("username")
	private String username;
}