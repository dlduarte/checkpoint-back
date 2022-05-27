package br.com.dld.checkpoint.models.dtos.gitlab.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TimeStats{

	@JsonProperty("time_estimate")
	private int timeEstimate;

	@JsonProperty("total_time_spent")
	private int totalTimeSpent;

	@JsonProperty("human_time_estimate")
	private Object humanTimeEstimate;

	@JsonProperty("human_total_time_spent")
	private String humanTotalTimeSpent;
}