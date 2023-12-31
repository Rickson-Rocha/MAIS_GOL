package com.br.maisgol.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.br.maisgol.DTO.TrainingInfoDTO;
import com.br.maisgol.model.schedule.Schedule;
import com.br.maisgol.model.training.Training;
import com.br.maisgol.service.exceptions.ConflictException;
import com.br.maisgol.service.exceptions.ObjectNotFoundException;

public interface TrainingService {
   Training createTraining(Long groupId, Long coachId, List<Schedule> schedules)throws ConflictException;
   Training getTrainingById(Long trainingId) throws ObjectNotFoundException;
   Training updateTraining(Long trainingId, Training updatedTraining) throws ObjectNotFoundException, ConflictException;

    // Método para deletar um treinamento pelo ID
    void deleteTraining(Long trainingId) throws NotFoundException;
    
    // Método para obter todos os treinamentos
    List<Training> getAllTrainings();

    //List<TrainingInfoDTO> getTrainingsByCoachCPF(String coachCPF) throws ObjectNotFoundException;
    List<Training> getTrainingsByCoachId(Long coachId);

    List<TrainingInfoDTO> getTrainingsByCoachCPF(String coachCPF) throws ObjectNotFoundException;
}
