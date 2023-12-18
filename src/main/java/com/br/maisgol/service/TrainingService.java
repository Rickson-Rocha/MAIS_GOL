package com.br.maisgol.service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import com.br.maisgol.model.coach.Coach;
import com.br.maisgol.model.field.Field;
import com.br.maisgol.model.group.Group;
import com.br.maisgol.model.schedule.Schedule;
import com.br.maisgol.model.training.Training;

public interface TrainingService {
    boolean isCoachAvailable(Coach coach, List<Schedule> selectedSchedules);

    boolean isFieldAvailable(Field field, List<Schedule> selectedSchedules);

    boolean isGroupAvailable(Group group, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime);

    Training createTraining(Group group, Coach coach, Field field, List<Schedule> selectedSchedules);
}
