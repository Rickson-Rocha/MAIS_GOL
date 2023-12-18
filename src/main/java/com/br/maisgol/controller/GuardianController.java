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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.br.maisgol.model.guardian.Guardian;
import com.br.maisgol.service.GuardianService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("guardian")
@Validated
public class GuardianController {

    @Autowired
    private GuardianService guardianService;


   @GetMapping("/{id}")
   public ResponseEntity<Guardian> findById(@PathVariable Long id){
    Guardian guardian = this.guardianService.findById(id);
    return ResponseEntity.ok(guardian);
   }

   @GetMapping
    public ResponseEntity<Page<Guardian>> findAllCoaches(
            @PageableDefault(size = 10) Pageable pageable 
    ) {
        Page<Guardian> guardianPage = guardianService.findAllGuardian(pageable);
        return ResponseEntity.ok(guardianPage);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody Guardian guardian, UriComponentsBuilder uriBuilder){
        Guardian createdGuardian = guardianService.createGuardian(guardian);
        var uri = uriBuilder.path("/guardians/{id}").buildAndExpand(createdGuardian.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Guardian> update(@PathVariable Long id,@RequestBody @Valid Guardian guardian) {
        guardian.setId(id);
        Guardian localGuardian = guardianService.createGuardian(guardian);

        return ResponseEntity.ok(localGuardian);
    }
    

    
}
