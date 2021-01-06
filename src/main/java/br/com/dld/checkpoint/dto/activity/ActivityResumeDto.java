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
    private String command;
    private ActivityType type;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime total;

    public ActivityResumeDto(Object[] object) {
        name = String.valueOf(object[0]);
        type = ActivityType.valueOf(String.valueOf(object[1]));
        total = LocalTime.parse(String.valueOf(object[3]));
        mountCommand(String.valueOf(object[2]));
    }

    private void mountCommand(String reference) {
        if (ActivityType.PAID.equals(type)) {
            String[] split = total.toString().split(":");
            String hours = split[0];
            String minutes = split[1];

            StringBuilder cmd = new StringBuilder("/spend");

            if (Integer.parseInt(hours) > 0) {
                cmd.append(" ").append(hours).append("h");
            }

            if (Integer.parseInt(minutes) > 0) {
                cmd.append(" ").append(minutes).append("m");
            }

            command = cmd.append(" ").append(reference).toString();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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
