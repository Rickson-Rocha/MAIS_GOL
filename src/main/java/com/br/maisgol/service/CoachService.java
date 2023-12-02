package com.br.maisgol.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.maisgol.model.coach.Coach;

@Service
public interface CoachService {
    
    Coach findById(Long id);

    Page<Coach> findAllCoaches(Pageable pageable);

    Coach createCoach(Coach coach);

    Coach updateCoach(Coach coach);

    void delete(Long id);
}
