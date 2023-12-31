package com.br.maisgol.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingInfoDTO {
    private String groupName;
    private List<ScheduleDTO> schedules;

    public TrainingInfoDTO(String groupName, List<ScheduleDTO> schedules) {
        this.groupName = groupName;
        this.schedules = schedules;
    }
}
