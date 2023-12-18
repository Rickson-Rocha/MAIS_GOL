package com.br.maisgol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.maisgol.model.schedule.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    
}
