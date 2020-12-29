package br.com.dld.checkpoint.form.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Duarte
 */
public class UpdateActivityForm {
    
    @NotNull
    private Long activityId;
    
    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime ended;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public LocalTime getEnded() {
        return ended;
    }

    public void setEnded(LocalTime ended) {
        this.ended = ended;
    }
}
