package com.br.maisgol.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.br.maisgol.model.coach.Coach;
import com.br.maisgol.model.enums.Status;
import com.br.maisgol.repository.CoachRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public Coach findById(Long id) {
        Optional<Coach> coach = this.coachRepository.findById(id);
        return coach.orElseThrow(()-> new RuntimeException("Coach not found"));
    }

    @Override
    public Page<Coach> findAllCoaches(Pageable pageable) {
        return coachRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Coach createCoach(Coach coach) {
        if (coach.getCpf() != null) {
            coach.setCpf(coach.getCpf().replaceAll("[^0-9]", ""));
        }
       coach.setId(null);
       Coach newCoach = coachRepository.save(coach);
       return newCoach;

    }

    @Override
    @Transactional
    public Coach updateCoach(Coach coach) {
        if (coach.getCpf() != null) {
            coach.setCpf(coach.getCpf().replaceAll("[^0-9]", ""));
        }
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

    @Override
    public Page<Coach> findActiveCoaches(Pageable pageable) {
        return coachRepository.findByStatus(Status.ACTIVE,pageable);
    }

    @Override
    public Page<Coach> findInactiveCoaches(Pageable pageable) {
        return coachRepository.findByStatus(Status.INACTIVE,pageable);
    }

    @Override
    public Page<Coach> findVacationCoaches(Pageable pageable) {
        return coachRepository.findByStatus(Status.VACATION,  pageable);
    }

    @Override
    public Page<Coach> findCoachesByCriteria(String name, Status status,Pageable pageable) {
        return coachRepository.findByNameAndStatus(name, status,pageable);
    }

    @Override
    public void uploadCoachPhoto(Long coachId, MultipartFile photoFile) throws IOException {
        Coach coach = findById(coachId);
        byte[] photoBytes = photoFile.getBytes();
        coach.setPhoto(photoBytes);
        coachRepository.save(coach);
    }
    
}
