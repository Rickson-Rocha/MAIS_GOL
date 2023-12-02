package com.br.maisgol.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import com.br.maisgol.model.coach.Coach;
import com.br.maisgol.service.CoachService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("coach")
@Validated
public class CoachController {
    
    @Autowired
    private CoachService coachService;

   @GetMapping("/{id}")
   public ResponseEntity<Coach> findById(@PathVariable Long id){
    Coach coach = this.coachService.findById(id);
    return ResponseEntity.ok(coach);
   }

   @GetMapping
    public ResponseEntity<Page<Coach>> findAllCoaches(
            @PageableDefault(size = 10) Pageable pageable // Define o tamanho da página como 10 por padrão
    ) {
        Page<Coach> coachesPage = coachService.findAllCoaches(pageable);
        return ResponseEntity.ok(coachesPage);
    }

   @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody Coach coach, UriComponentsBuilder uriBuilder){
        Coach createdCoach = coachService.createCoach(coach);
        var uri = uriBuilder.path("/coaches/{id}").buildAndExpand(createdCoach.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PostMapping("/{id}/photo")
    public ResponseEntity<Void> uploadCoachPhoto(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        try {
            coachService.uploadCoachPhoto(id, file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Coach> update(@PathVariable Long id,@RequestBody @Valid Coach coach) {
        coach.setId(id);
        Coach localCoach = coachService.updateCoach(coach);

        return ResponseEntity.ok(localCoach);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coachService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public ResponseEntity<Page<Coach>> getActiveCoaches(
            @PageableDefault(size = 10) Pageable pageable // Define o tamanho da página como 10 por padrão
    ) {
        Page<Coach> activeCoachesPage = coachService.findActiveCoaches(pageable);
        return ResponseEntity.ok(activeCoachesPage);
    }

    @GetMapping("/inactive")
    public ResponseEntity<Page<Coach>> getInactiveCoaches(
            @PageableDefault(size = 10) Pageable pageable // Define o tamanho da página como 10 por padrão
    ) {
        Page<Coach> inactiveCoachesPage = coachService.findInactiveCoaches(pageable);
        return ResponseEntity.ok(inactiveCoachesPage);
    }

    @GetMapping("/vacation")
    public ResponseEntity<Page<Coach>> getVactionCoaches(
            @PageableDefault(size = 10) Pageable pageable // Define o tamanho da página como 10 por padrão
) {
        Page<Coach> vacationCoachesPage = coachService.findVacationCoaches(pageable);
        return ResponseEntity.ok(vacationCoachesPage);
    }


}
