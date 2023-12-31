package com.br.maisgol.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDTO {
    private String dayOfWeek;
    private String startTime;
    private String endTime;

    public ScheduleDTO(String dayOfWeek, String startTime, String endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}