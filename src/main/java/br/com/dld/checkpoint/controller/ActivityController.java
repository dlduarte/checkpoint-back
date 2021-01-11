package br.com.dld.checkpoint.controller;

import br.com.dld.checkpoint.dto.errors.ClientErrors;
import br.com.dld.checkpoint.dto.errors.ServerErrors;
import br.com.dld.checkpoint.form.activity.ActivityForm;
import br.com.dld.checkpoint.form.activity.UpdateActivityForm;
import br.com.dld.checkpoint.service.ActivityService;
import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author David Duarte
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService service;

    @GetMapping("/{date}")
    public ResponseEntity find(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try {
            return ResponseEntity.ok(service.find(date));
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }

    @GetMapping("/summary/{date}")
    public ResponseEntity summary(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try {
            return ResponseEntity.ok(service.summary(date));
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }

    @PostMapping()
    public ResponseEntity save(@RequestBody @Valid ActivityForm form) {
        try {
            return ResponseEntity.ok(service.save(form));
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody @Valid UpdateActivityForm form) {
        try {
            String error = service.update(form);

            if (error == null || error.isEmpty()) {
                return ResponseEntity.ok().build();
            }

            return ClientErrors.build(error);
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }
}
