package com.br.maisgol.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.maisgol.model.field.Field;
import com.br.maisgol.repository.FieldRepository;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;


    @Override
    public Field findByid(Long id) {
         Optional<Field> field = this.fieldRepository.findById(id);
        return field.orElseThrow(()-> new RuntimeException("Field not found"));
    }
    
}
