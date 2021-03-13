package br.com.dld.checkpoint.services;

import br.com.dld.checkpoint.config.security.auth.AuthenticationFacade;
import br.com.dld.checkpoint.dto.activity.ActivityDto;
import br.com.dld.checkpoint.dto.activity.ActivityResumeDto;
import br.com.dld.checkpoint.dto.activity.ActivitySummaryDto;
import br.com.dld.checkpoint.entities.Activity;
import br.com.dld.checkpoint.entities.Setting;
import br.com.dld.checkpoint.entities.enums.ActivityType;
import br.com.dld.checkpoint.entities.enums.SettingKey;
import br.com.dld.checkpoint.forms.activity.ActivityForm;
import br.com.dld.checkpoint.forms.activity.UpdateActivityForm;
import br.com.dld.checkpoint.repositories.ActivityRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David Duarte
 */
@Service
public class ActivityService {

    @Autowired
    private AuthenticationFacade facade;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SettingService settingService;

    public List<ActivityDto> find(LocalDate date) throws Exception, RuntimeException {
        return activityRepository
                .findAllByAccountId_IdAndReferenceOrderByCreation(facade.getAccount().getId(), date)
                .stream()
                .map(ActivityDto::new)
                .collect(Collectors.toList());
    }

    public ActivitySummaryDto summary(LocalDate date) throws Exception, RuntimeException {
        List<Object[]> list = activityRepository.getSummary(facade.getAccount().getId(), date);

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

        return summary;
    }

    @Transactional
    public Activity save(ActivityForm form) throws Exception, RuntimeException {
        Activity activity = form.convert(facade.getAccount());

        Optional<Activity> optActivity = activityRepository
                .findFirstByAccountId_IdAndReferenceOrderByCreationDesc(facade.getAccount().getId(), form.getReference());

        if (optActivity.isPresent()) {
            activity.setBeginning(optActivity
                    .get()
                    .getEnded()
            );
        } else {
            Optional<Setting> optSetting = settingService.find(SettingKey.START_OF_OFFICE_HOUR);

            if (optSetting.isEmpty()) {
                throw new Exception("Configure o horário de início do expediente");
            }

            activity.setBeginning(LocalTime.parse(optSetting
                    .get()
                    .getCurrentValue()
            ));
        }

        if (timeDiff(activity.getEnded(), activity.getBeginning()) <= 0) {
            throw new Exception("O horário de finalização deve ser maior que o horário de início");
        }

        activityRepository.save(activity);
        return activity;
    }

    @Transactional
    public String update(UpdateActivityForm form) throws Exception, RuntimeException {
        Optional<Activity> optActivity = activityRepository.findById(form.getId());

        if (!optActivity.isPresent()) {
            return "Atividade não encontrada";
        }

        Activity activity = optActivity.get();

        if (timeDiff(form.getEnded(), activity.getBeginning()) <= 0) {
            return "O horário de finalização deve ser maior que o horário de início";
        }

        optActivity = activityRepository.findFirstByAccountId_IdAndReferenceAndBeginning(
                facade.getAccount().getId(), activity.getReference(), activity.getEnded());

        if (optActivity.isPresent()) {
            if (timeDiff(optActivity.get().getEnded(), form.getEnded()) <= 0) {
                return "O horário de finalização desta atividade deve ser menor que o da próxima";
            }

            optActivity.get().setBeginning(form.getEnded());
            activityRepository.save(optActivity.get());
        }

        activity.setEnded(form.getEnded());
        activity.setName(form.getName().toUpperCase().trim());
        activity.setType(form.getType());
        activityRepository.save(activity);
        return "";
    }

    private int timeDiff(LocalTime time1, LocalTime time2) {
        return LocalTime.of(
                time1.get(ChronoField.HOUR_OF_DAY),
                time1.get(ChronoField.MINUTE_OF_HOUR))
                .compareTo(
                        LocalTime.of(
                                time2.get(ChronoField.HOUR_OF_DAY),
                                time2.get(ChronoField.MINUTE_OF_HOUR)
                        )
                );
    }
}
