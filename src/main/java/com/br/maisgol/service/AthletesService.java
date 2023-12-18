package com.br.maisgol.service;

import com.br.maisgol.model.athletes.Athletes;
import com.br.maisgol.model.enums.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface AthletesService {
    
    Athletes findById(Long id);


    Page<Athletes> findAthletes(Pageable pageable);

    Athletes createAthlete(Athletes athletes);

    Athletes updateAthletes(Athletes athletes);


    void delete(Long id);


    Page<Athletes> findActiveAthletes(Pageable pageable);

    Page<Athletes> findInactiveAthletes(Pageable pageable);


    Page<Athletes> findAthletesByCriteria(String name, Status status,Pageable pageable);

    


}
