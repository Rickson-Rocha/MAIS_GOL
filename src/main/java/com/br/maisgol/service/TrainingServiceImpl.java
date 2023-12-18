package com.br.maisgol.service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.maisgol.model.coach.Coach;
import com.br.maisgol.model.field.Field;
import com.br.maisgol.model.group.Group;
import com.br.maisgol.model.schedule.Schedule;
import com.br.maisgol.model.training.Training;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class TrainingServiceImpl implements TrainingService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Training createTraining(Group group, Coach coach, Field field, List<Schedule> selectedSchedules) {
        for (Schedule newSchedule : selectedSchedules) {
            if (!isGroupAvailable(group, newSchedule.getDayOfWeek(), newSchedule.getStartTime(), newSchedule.getEndTime())) {
                throw new RuntimeException("A turma selecionada não está disponível para um novo treino.");
            }
        }
        if (!isFieldAvailable(field, selectedSchedules)) {
            throw new RuntimeException("O campo selecionado não está disponível para este horário e dias da semana.");
        }

        if (!isCoachAvailable(coach, selectedSchedules)) {
            throw new RuntimeException("O professor selecionado não está disponível para este horário e dias da semana.");
        }

        Training training = new Training(field, coach, group);

        // Define a lista de schedules para o treinamento
        training.setSchedules(selectedSchedules);

        // Persiste o treinamento e os schedules associados
        entityManager.persist(training);

        return training;
    }

    @Override
    public boolean isCoachAvailable(Coach coach, List<Schedule> selectedSchedules) {
        String query = "SELECT COUNT(*) FROM Training t " +
                      "WHERE t.coach.id = :coachId " +
                      "AND (";
        
        StringBuilder conditions = new StringBuilder();
        for (Schedule schedule : selectedSchedules) {
            conditions.append("OR (t.schedule.dayOfWeek = :dayOfWeek")
                      .append(" AND t.schedule.startTime BETWEEN :startTime AND :endTime ")
                      .append(" OR t.schedule.endTime BETWEEN :startTime AND :endTime) ");
        }
        query += conditions.substring(2) + ")";

        Query q = entityManager.createQuery(query);
        q.setParameter("coachId", coach.getId());
        for (Schedule schedule : selectedSchedules) {
            q.setParameter("dayOfWeek", schedule.getDayOfWeek());
            q.setParameter("startTime", schedule.getStartTime());
            q.setParameter("endTime", schedule.getEndTime());
        }

        Long count = (Long) q.getSingleResult();
        return count == 0;
    }

    @Override
    public boolean isFieldAvailable(Field field, List<Schedule> selectedSchedules) {
        for (Schedule schedule : selectedSchedules) {
            String query = "SELECT COUNT(*) FROM Training t " +
                          "WHERE t.field.id = :fieldId " +
                          "AND t.schedule.dayOfWeek = :dayOfWeek " +
                          "AND ((t.schedule.startTime BETWEEN :startTime AND :endTime) " +
                          "OR (t.schedule.endTime BETWEEN :startTime AND :endTime))";
    
            Query q = entityManager.createQuery(query);
            q.setParameter("fieldId", field.getId());
            q.setParameter("dayOfWeek", schedule.getDayOfWeek());
            q.setParameter("startTime", schedule.getStartTime());
            q.setParameter("endTime", schedule.getEndTime());
    
            Long count = (Long) q.getSingleResult();
            if (count > 0) {
                return false; // Field unavailable for at least one schedule
            }
        }
        return true; // Field available for all selected schedules
    }

    @Override
    public boolean isGroupAvailable(Group group, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
       String query = "SELECT COUNT(*) FROM Training t " +
                      "WHERE t.group.id = :groupId " +
                      "AND t.schedule.dayOfWeek = :dayOfWeek " +
                      "AND ((t.schedule.startTime BETWEEN :startTime AND :endTime) " +
                      "OR (t.schedule.endTime BETWEEN :startTime AND :endTime))";

        Query q = entityManager.createQuery(query);
        q.setParameter("groupId", group.getId());
        q.setParameter("dayOfWeek", dayOfWeek);
        q.setParameter("startTime", startTime);
        q.setParameter("endTime", endTime);

        Long count = (Long) q.getSingleResult();
        return count == 0;
    }
    
}
