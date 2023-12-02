package com.br.maisgol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.maisgol.model.coach.Coach;

@Repository
public interface CoachRepository extends JpaRepository<Coach,Long>{
    
}
