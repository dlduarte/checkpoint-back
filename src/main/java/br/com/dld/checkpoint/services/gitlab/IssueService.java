package br.com.dld.checkpoint.services.gitlab;

import br.com.dld.checkpoint.clients.GitLabClient;
import br.com.dld.checkpoint.models.dtos.gitlab.Spend;
import br.com.dld.checkpoint.models.dtos.gitlab.issue.Issue;
import br.com.dld.checkpoint.models.dtos.gitlab.issue.IssueDto;
import br.com.dld.checkpoint.models.entities.Setting;
import br.com.dld.checkpoint.models.enums.SettingKey;
import br.com.dld.checkpoint.services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 @author David Duarte
 @created 13/05/2022
 */
@Service
public class IssueService {

	@Autowired
	private SettingService settingService;

	@Autowired
	private GitLabClient client;

	public List<IssueDto> find() throws Exception {

		Optional<Setting> settingOptional = settingService.find(SettingKey.GIT_LAB_TOKEN);

		if(settingOptional.isEmpty() || !settingOptional.get().isValid()) {
			throw new Exception("GitLab access token not configured");
		}

		return client
				.getIssues(settingOptional.get().getCurrentValue())
				.stream()
				.sorted(Comparator.comparing(Issue::getTitle))
				.map(i -> {
					IssueDto dto = new IssueDto();
					dto.setIssueId(i.getIid());
					dto.setProjectId(i.getProjectId());
					dto.setTitle(i.getTitle());

					return dto;
				})
				.collect(Collectors.toList());
	}

	public Spend applyDuration(String projectId, String issueId, String duration) throws Exception {
		Optional<Setting> settingOptional = settingService.find(SettingKey.GIT_LAB_TOKEN);

		if(settingOptional.isEmpty() || !settingOptional.get().isValid()) {
			throw new Exception("GitLab access token not configured");
		}

        return client.addSpend(settingOptional.get().getCurrentValue(), projectId, issueId, duration);
	}
}
