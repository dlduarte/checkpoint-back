package br.com.dld.checkpoint.models.dtos.gitlab;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Spend{

	@JsonProperty("time_estimate")
	private int timeEstimate;

	@JsonProperty("total_time_spent")
	private int totalTimeSpent;

	@JsonProperty("human_time_estimate")
	private String humanTimeEstimate;

	@JsonProperty("human_total_time_spent")
	private String humanTotalTimeSpent;
}