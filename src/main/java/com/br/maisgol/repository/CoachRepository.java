package com.br.maisgol.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.maisgol.model.coach.Coach;
import com.br.maisgol.model.enums.Status;

@Repository
public interface CoachRepository extends JpaRepository<Coach,Long>{

    Page<Coach> findByStatus(Status active, Pageable pageable);

    Page<Coach> findByNameAndStatus(String name, Status status,Pageable pageable);
    
}
