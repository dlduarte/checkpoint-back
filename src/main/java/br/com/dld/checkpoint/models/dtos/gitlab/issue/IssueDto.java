package br.com.dld.checkpoint.models.dtos.gitlab.issue;

import lombok.Data;

/**
 @author David Duarte
 @created 20/05/2022
 @project checkpoint-back */
@Data
public class IssueDto {

	private int issueId;
	private int projectId;
	private String title;
}
