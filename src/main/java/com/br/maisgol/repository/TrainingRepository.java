package com.br.maisgol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.maisgol.model.coach.Coach;
import com.br.maisgol.model.training.Training;

public interface TrainingRepository extends JpaRepository<Training,Long> {

    List<Training> findByCoach(Coach coach);
    List<Training> findByCoachId(Long coachId);
}
