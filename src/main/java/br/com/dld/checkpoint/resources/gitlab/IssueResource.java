package br.com.dld.checkpoint.resources.gitlab;

import br.com.dld.checkpoint.models.dtos.errors.ClientErrors;
import br.com.dld.checkpoint.models.dtos.errors.ServerErrors;
import br.com.dld.checkpoint.models.dtos.gitlab.Spend;
import br.com.dld.checkpoint.models.forms.activity.ActivityForm;
import br.com.dld.checkpoint.models.forms.activity.UpdateActivityForm;
import br.com.dld.checkpoint.services.ActivityService;
import br.com.dld.checkpoint.services.gitlab.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDate;

/**
 @author David Duarte */
@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/issue")
public class IssueResource {

	@Autowired
	private IssueService service;

	@GetMapping
	public ResponseEntity<?> find() {
		try {
			return ResponseEntity.ok(service.find());
		} catch(Exception e) {
			return ServerErrors.build(e);
		}
	}

	@PostMapping("/spend")
	public ResponseEntity<?> applyDuration(@PathParam("projectId") String projectId, @PathParam("issueId") String issueId, @PathParam("duration") String duration) {
		try {
			return ResponseEntity.ok(service.applyDuration(projectId, issueId, duration));
		} catch(Exception e) {
			return ServerErrors.build(e);
		}
	}
}
