package br.com.dld.checkpoint.models.dtos.gitlab.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskCompletionStatus{

	@JsonProperty("count")
	private int count;

	@JsonProperty("completed_count")
	private int completedCount;
}