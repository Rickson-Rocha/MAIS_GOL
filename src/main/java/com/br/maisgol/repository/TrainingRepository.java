package com.br.maisgol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.maisgol.model.training.Training;

public interface TrainingRepository extends JpaRepository<Training,Long> {
    
}
