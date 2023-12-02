package com.br.maisgol.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.br.maisgol.model.coach.Coach;
import com.br.maisgol.model.enums.Status;


public interface CoachService {
    
    Coach findById(Long id);

    Page<Coach> findAllCoaches(Pageable pageable);

    Coach createCoach(Coach coach);

    void uploadCoachPhoto(Long coachId, MultipartFile photoFile) throws IOException;

    Coach updateCoach(Coach coach);

    void delete(Long id);

    Page<Coach> findActiveCoaches(Pageable pageable);

    Page<Coach> findInactiveCoaches(Pageable pageable);

    Page<Coach> findVacationCoaches(Pageable pageable);

    Page<Coach> findCoachesByCriteria(String name, Status status,Pageable pageable);
}
