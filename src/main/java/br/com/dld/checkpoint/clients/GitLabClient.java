package br.com.dld.checkpoint.clients;

import br.com.dld.checkpoint.models.dtos.gitlab.Spend;
import br.com.dld.checkpoint.models.dtos.gitlab.issue.Issue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 @author David Duarte
 @created 20/05/2022 */
@FeignClient(name="GitLabApi", url="https://gitlab.com/api/v4")
public interface GitLabClient {

	@GetMapping("/issues?state=opened")
	List<Issue> getIssues(@RequestHeader("PRIVATE-TOKEN") String privateToken);

	@PostMapping(value="/projects/{projectId}/issues/{issueId}/add_spent_time?duration={duration}", consumes={
			"application/json" })
	Spend addSpend(@RequestHeader("PRIVATE-TOKEN") String privateToken, @PathVariable String projectId,
	               @PathVariable String issueId, @PathVariable String duration);
}
