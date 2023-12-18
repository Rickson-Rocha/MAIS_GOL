package com.br.maisgol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.maisgol.model.athletes.Athletes;
import com.br.maisgol.service.AthletesService;

@RestController
@RequestMapping("athlete")
@Validated
public class AthleteController {

    @Autowired
    private AthletesService athletesService;

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody Athletes athletes, UriComponentsBuilder uriBuilder){
        Athletes createdAthletes = athletesService.createAthlete(athletes);
        var uri = uriBuilder.path("/coaches/{id}").buildAndExpand(createdAthletes.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
}
