package br.com.dld.checkpoint.controller;

import br.com.dld.checkpoint.dto.activity.ActivityDto;
import br.com.dld.checkpoint.dto.activity.ActivityResumeDto;
import br.com.dld.checkpoint.dto.activity.ActivitySummaryDto;
import br.com.dld.checkpoint.form.activity.ActivityForm;
import br.com.dld.checkpoint.form.activity.UpdateActivityForm;
import br.com.dld.checkpoint.model.Activity;
import br.com.dld.checkpoint.model.enums.ActivityType;
import br.com.dld.checkpoint.repository.ActivityRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping()
    public ResponseEntity findAll() {
        return ResponseEntity.ok(activityRepository
                .findAll()
                .stream()
                .map(ActivityDto::new)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{date}")
    public ResponseEntity findByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(activityRepository
                .findAllByReferenceOrderByCreation(date)
                .stream()
                .map(ActivityDto::new)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/summary/{date}")
    public ResponseEntity summary(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try {

            List<Object[]> list = activityRepository.getSummary(date);

            List<ActivityResumeDto> resume = list
                    .stream()
                    .map(ActivityResumeDto::new)
                    .collect(Collectors.toList());

            ActivitySummaryDto summary = new ActivitySummaryDto();
            summary.setResume(resume);
            summary.setWorked(LocalTime.MIN
                    .plusNanos(resume
                            .stream()
                            .filter(activity -> ActivityType.PAID.equals(activity.getType()))
                            .map(ActivityResumeDto::getTotal)
                            .map(LocalTime::toNanoOfDay)
                            .reduce(0L, Long::sum))
            );
            summary.setStrayed(LocalTime.MIN
                    .plusNanos(resume
                            .stream()
                            .filter(activity -> !ActivityType.PAID.equals(activity.getType()))
                            .map(ActivityResumeDto::getTotal)
                            .map(LocalTime::toNanoOfDay)
                            .reduce(0L, Long::sum))
            );

            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping()
    @Transactional
    public ResponseEntity save(@RequestBody @Valid ActivityForm form) {
        try {
            Activity activity = form.convert();

            Optional<Activity> optActivity = activityRepository
                    .findFirstByReferenceOrderByCreationDesc(LocalDate.now());

            if (optActivity.isPresent()) {
                activity.setBeginning(optActivity
                        .get()
                        .getEnded()
                );
            } else {
                activity.setBeginning(LocalTime.of(8, 0));
            }

            return ResponseEntity.ok(activityRepository.save(activity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping()
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateActivityForm form) {
        try {
            Optional<Activity> optActivity = activityRepository.findById(form.getActivityId());

            if (!optActivity.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Activity activity = optActivity.get();

            optActivity = activityRepository.findFirstByReferenceAndBeginning(
                    activity.getReference(), activity.getEnded());

            if (optActivity.isPresent()) {
                if (optActivity.get().getEnded().compareTo(form.getEnded()) <= 0) {
                    return ResponseEntity.badRequest().body("The amount sent for change exceeds the final moment of the next activity");
                }

                optActivity.get().setBeginning(form.getEnded());
                activityRepository.save(optActivity.get());
            }

            activity.setEnded(form.getEnded());
            activityRepository.save(activity);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
