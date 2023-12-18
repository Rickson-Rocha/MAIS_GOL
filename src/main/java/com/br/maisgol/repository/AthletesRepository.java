package com.br.maisgol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.maisgol.model.athletes.Athletes;
import com.br.maisgol.model.enums.Status;

public interface AthletesRepository extends JpaRepository<Athletes,Long> {

    Page<Athletes> findByStatus(Status active, Pageable pageable);

    Page<Athletes> findByNameAndStatus(String name, Status status, Pageable pageable);
    
}
