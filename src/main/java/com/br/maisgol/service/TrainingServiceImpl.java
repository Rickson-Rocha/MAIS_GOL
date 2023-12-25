package com.br.maisgol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.br.maisgol.model.coach.Coach;
import com.br.maisgol.model.group.Group;
import com.br.maisgol.model.schedule.Schedule;
import com.br.maisgol.model.training.Training;
import com.br.maisgol.repository.CoachRepository;
import com.br.maisgol.repository.GroupRepository;
import com.br.maisgol.repository.TrainingRepository;
import com.br.maisgol.service.exceptions.ConflictException;
import com.br.maisgol.service.exceptions.ObjectNotFoundException;

@Service
public class TrainingServiceImpl implements TrainingService{
    @Autowired
    private TrainingRepository trRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public Training createTraining(Long groupId, Long coachId, List<Schedule> schedules)throws ConflictException {
        Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new ObjectNotFoundException("Group not found"));

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new ObjectNotFoundException("Coach not found"));

        if (hasCoachScheduleConflict(coach, schedules)) {
        throw new ConflictException("There is a schedule conflict for the coach");
        }

        Training training = new Training();
        training.setGroup(group);
        training.setCoach(coach);
        for (Schedule schedule : schedules) {
        schedule.setTraining(training);
        }
        training.setSchedules(schedules);

        return trRepository.save(training);
    }

    private boolean hasCoachScheduleConflict(Coach coach, List<Schedule> schedules) {
        List<Training> coachTrainings = trRepository.findByCoach(coach);

        for (Training coachTraining : coachTrainings) {
            for (Schedule existingSchedule : coachTraining.getSchedules()) {
                for (Schedule newSchedule : schedules) {
                    if (newSchedule.getDayOfWeek().equals(existingSchedule.getDayOfWeek())) {
                        if (isTimeConflict(newSchedule, existingSchedule)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isTimeConflict(Schedule newSchedule, Schedule existingSchedule) {
        return newSchedule.getStartTime().isBefore(existingSchedule.getEndTime()) &&
                newSchedule.getEndTime().isAfter(existingSchedule.getStartTime());
    }

    @Override
    public Training getTrainingById(Long trainingId) throws ObjectNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTrainingById'");
    }

    @Override
    public Training updateTraining(Long trainingId, Training updatedTraining)
            throws NotFoundException, ConflictException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTraining'");
    }

    @Override
    public void deleteTraining(Long trainingId) throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTraining'");
    }

    @Override
    public List<Training> getAllTrainings() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTrainings'");
    }
    
    
    }

