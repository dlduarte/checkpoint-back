package br.com.dld.checkpoint.forms.activity;

import br.com.dld.checkpoint.entities.enums.ActivityType;
import br.com.dld.checkpoint.serialization.enumerated.ValidEnumerated;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Duarte
 */
public class UpdateActivityForm {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @ValidEnumerated(enumClass = ActivityType.class)
    private ActivityType type;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime ended;

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

    public LocalTime getEnded() {
        return ended;
    }

    public void setEnded(LocalTime ended) {
        this.ended = ended;
    }
}
