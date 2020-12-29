package br.com.dld.checkpoint.dto.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author David Duarte
 */
public class ActivitySummaryDto {
    
    private List<ActivityResumeDto> resume;
    
    @JsonFormat(pattern = "HH:mm")
    private LocalTime worked;
    
    @JsonFormat(pattern = "HH:mm")
    private LocalTime strayed;
    
    public List<ActivityResumeDto> getResume() {
        return resume;
    }

    public void setResume(List<ActivityResumeDto> resume) {
        this.resume = resume;
    }

    public LocalTime getWorked() {
        return worked;
    }

    public void setWorked(LocalTime worked) {
        this.worked = worked;
    }

    public LocalTime getStrayed() {
        return strayed;
    }

    public void setStrayed(LocalTime strayed) {
        this.strayed = strayed;
    }
}
