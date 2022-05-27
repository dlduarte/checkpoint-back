package br.com.dld.checkpoint.models.dtos.activity;

import br.com.dld.checkpoint.models.entities.Activity;
import br.com.dld.checkpoint.models.enums.ActivityType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author David Duarte
 */
public class ActivityDto {

    private Long id;
    private String name;
    private ActivityType type;
    private LocalDate reference;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime beginning;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime ended;

    public ActivityDto(Activity activity) {
        this.id = activity.getId();
        this.name = activity.getName();
        this.type = activity.getType();
        this.reference = activity.getReference();
        this.beginning = activity.getBeginning();
        this.ended = activity.getEnded();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalTime getBeginning() {
        return beginning;
    }

    public void setBeginning(LocalTime beginning) {
        this.beginning = beginning;
    }

    public LocalTime getEnded() {
        return ended;
    }

    public void setEnded(LocalTime ended) {
        this.ended = ended;
    }
}
