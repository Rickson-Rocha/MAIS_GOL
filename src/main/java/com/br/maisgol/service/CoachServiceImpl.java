package com.br.maisgol.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.br.maisgol.model.coach.Coach;
import com.br.maisgol.model.enums.Status;
import com.br.maisgol.repository.CoachRepository;

import jakarta.transaction.Transactional;

public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public Coach findById(Long id) {
        Optional<Coach> coach = this.coachRepository.findById(id);
        return coach.orElseThrow(()-> new RuntimeException("Task not found"));
    }

    @Override
    public Page<Coach> findAllCoaches(Pageable pageable) {
        return coachRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Coach createCoach(Coach coach) {
       coach.setId(null);
       Coach newCoach = coachRepository.save(coach);
       return newCoach;

    }

    @Override
    @Transactional
    public Coach updateCoach(Coach coach) {
        Coach newUser = findById(coach.getId());
        newUser.setName(coach.getName());
        newUser.setCpf(coach.getCpf());
        newUser.setPhone(coach.getPhone());
        newUser.setStatus(coach.getStatus());
        return newUser;
    }

    @Override
    public void delete(Long id) {
     Coach coach = findById(id);
    
        if (coach.getStatus() == Status.INACTIVE) {
            throw new RuntimeException("Coach is already inactive.");
        }
        
        coach.setStatus(Status.INACTIVE);
        updateCoach(coach);
    }
    
}
