package com.br.maisgol.DTO;

import java.util.List;

import com.br.maisgol.model.schedule.Schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequestDTO{
    private Long groupId;
    private Long coachId;
    private List<Schedule> schedules;
}