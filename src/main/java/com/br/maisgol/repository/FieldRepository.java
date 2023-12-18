package com.br.maisgol.repository;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.maisgol.model.field.Field;

public interface FieldRepository extends JpaRepository<Field,Long>{

    Field findByNumber(Integer fieldNumber);

    
}
