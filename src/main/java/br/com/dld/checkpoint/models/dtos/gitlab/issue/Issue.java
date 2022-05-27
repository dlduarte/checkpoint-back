package br.com.dld.checkpoint.models.dtos.gitlab.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Issue{

	@JsonProperty("discussion_locked")
	private Object discussionLocked;

	@JsonProperty("upvotes")
	private int upvotes;

	@JsonProperty("references")
	private References references;

	@JsonProperty("iid")
	private int iid;

	@JsonProperty("merge_requests_count")
	private int mergeRequestsCount;

	@JsonProperty("_links")
	private Links links;

	@JsonProperty("description")
	private String description;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("assignees")
	private List<Object> assignees;

	@JsonProperty("title")
	private String title;

	@JsonProperty("type")
	private String type;

	@JsonProperty("closed_by")
	private Object closedBy;

	@JsonProperty("has_tasks")
	private boolean hasTasks;

	@JsonProperty("service_desk_reply_to")
	private Object serviceDeskReplyTo;

	@JsonProperty("updated_at")
	private String updatedAt;

	@JsonProperty("project_id")
	private int projectId;

	@JsonProperty("time_stats")
	private TimeStats timeStats;

	@JsonProperty("id")
	private int id;

	@JsonProperty("state")
	private String state;

	@JsonProperty("confidential")
	private boolean confidential;

	@JsonProperty("severity")
	private String severity;

	@JsonProperty("closed_at")
	private Object closedAt;

	@JsonProperty("author")
	private Author author;

	@JsonProperty("due_date")
	private Object dueDate;

	@JsonProperty("issue_type")
	private String issueType;

	@JsonProperty("downvotes")
	private int downvotes;

	@JsonProperty("blocking_issues_count")
	private int blockingIssuesCount;

	@JsonProperty("labels")
	private List<Object> labels;

	@JsonProperty("moved_to_id")
	private Object movedToId;

	@JsonProperty("milestone")
	private Object milestone;

	@JsonProperty("web_url")
	private String webUrl;

	@JsonProperty("user_notes_count")
	private int userNotesCount;

	@JsonProperty("assignee")
	private Object assignee;

	@JsonProperty("task_completion_status")
	private TaskCompletionStatus taskCompletionStatus;
}