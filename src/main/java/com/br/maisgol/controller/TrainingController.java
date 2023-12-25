package com.br.maisgol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.maisgol.DTO.TrainingRequestDTO;
import com.br.maisgol.service.TrainingService;

@RestController
@RequestMapping("/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;
    @PostMapping("/create")
    public ResponseEntity<String> createTraining(@RequestBody TrainingRequestDTO requestDTO) {
        try {
            trainingService.createTraining(requestDTO.getGroupId(), requestDTO.getCoachId(), requestDTO.getSchedules());
            return ResponseEntity.ok("Training created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create training: " + e.getMessage());
        }
    }
}
