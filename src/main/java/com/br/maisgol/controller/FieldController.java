package com.br.maisgol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.maisgol.model.field.Field;
import com.br.maisgol.repository.RepositoryField;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("field")
public class FieldController {
    @Autowired
    private RepositoryField repositoryField;
    @PostMapping
    @Transactional
    public ResponseEntity<Object> create(@RequestBody @Valid Field field,
            UriComponentsBuilder uriBuilder) {
        Field localField = repositoryField.save(field);
        var uri = uriBuilder.path("/field/{id}").buildAndExpand(localField.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
