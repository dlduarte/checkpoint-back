package br.com.dld.checkpoint.forms.activity;

import br.com.dld.checkpoint.entities.Account;
import br.com.dld.checkpoint.entities.Activity;
import br.com.dld.checkpoint.entities.enums.ActivityType;
import br.com.dld.checkpoint.serialization.enumerated.ValidEnumerated;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Duarte
 */
public class ActivityForm {

    @NotNull
    @NotEmpty
    private String name;

    @ValidEnumerated(enumClass = ActivityType.class)
    private ActivityType type;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reference;
    
    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime ended;

    public Activity convert(Account account) {
        Activity activity = new Activity();
        activity.setAccountId(account);
        activity.setCreation(LocalDateTime.now());
        activity.setName(name.trim().toUpperCase());
        activity.setType(type);
        activity.setReference(reference);
        activity.setEnded(ended);

        return activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public LocalDate getReference() {
        return reference;
    }

    public void setReference(LocalDate reference) {
        this.reference = reference;
    }

    public LocalTime getEnded() {
        return ended;
    }

    public void setEnded(LocalTime ended) {
        this.ended = ended;
    }
}
