package com.br.maisgol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.maisgol.model.athletes.Athletes;
import com.br.maisgol.service.AthletesService;
import com.br.maisgol.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("athlete")
@Validated
public class AthleteController {

    @Autowired
    private AthletesService athletesService;

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody Athletes athletes, UriComponentsBuilder uriBuilder){
        Athletes createdAthletes = athletesService.createAthlete(athletes);
        var uri = uriBuilder.path("/athletes/{id}").buildAndExpand(createdAthletes.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<Athletes>> findAllAthletes(
            @PageableDefault(size = 10) Pageable pageable // Define o tamanho da página como 10 por padrão
    ) {
        Page<Athletes> atheltesPage = athletesService.findAthletes(pageable);
        return ResponseEntity.ok(atheltesPage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Athletes> findById(@PathVariable Long id){
        try {
            Athletes athletes = this.athletesService.findById(id);
            return ResponseEntity.ok(athletes);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
   }
    
    }
}
