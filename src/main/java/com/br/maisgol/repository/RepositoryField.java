package com.br.maisgol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.maisgol.model.field.Field;

public interface RepositoryField extends JpaRepository<Field,Long> {
    
}
