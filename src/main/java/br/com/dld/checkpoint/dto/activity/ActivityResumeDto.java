package br.com.dld.checkpoint.dto.activity;

import br.com.dld.checkpoint.model.enums.ActivityType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;

/**
 *
 * @author David Duarte
 */
public class ActivityResumeDto {
    
    private String name;
    private ActivityType type;
    
    @JsonFormat(pattern = "HH:mm")
    private LocalTime total;

    public ActivityResumeDto(Object[] object) {
        name = String.valueOf(object[0]);
        type = ActivityType.valueOf(String.valueOf(object[1]));
        total = LocalTime.parse(String.valueOf(object[2]));
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

    public LocalTime getTotal() {
        return total;
    }

    public void setTotal(LocalTime total) {
        this.total = total;
    }
}
