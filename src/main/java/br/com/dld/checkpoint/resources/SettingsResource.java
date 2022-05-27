package br.com.dld.checkpoint.resources;

import br.com.dld.checkpoint.models.dtos.errors.ServerErrors;
import br.com.dld.checkpoint.models.enums.SettingKey;
import br.com.dld.checkpoint.models.forms.setting.SettingForm;
import br.com.dld.checkpoint.services.SettingService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author David Duarte
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/settings")
public class SettingsResource {

    @Autowired
    private SettingService service;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }

    @GetMapping("/{setting}")
    public ResponseEntity<?> find(@PathVariable SettingKey setting) {
        try {
            return ResponseEntity.ok(service.find(setting));
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody @Valid SettingForm form) {
        try {
            return ResponseEntity.ok(service.save(form));
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }

    @PostMapping("/all")
    public ResponseEntity<?> save(@RequestBody @Valid List<SettingForm> forms) {
        try {
            forms.forEach(service::save);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ServerErrors.build(e);
        }
    }
}
