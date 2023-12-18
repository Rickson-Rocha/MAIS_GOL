// package com.br.maisgol.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.util.UriComponentsBuilder;

// import com.br.maisgol.model.coach.Coach;
// import com.br.maisgol.model.field.Field;
// import com.br.maisgol.model.group.Group;
// import com.br.maisgol.model.schedule.Schedule;
// import com.br.maisgol.model.training.Training;
// import com.br.maisgol.service.TrainingService;
// import com.br.maisgol.service.TrainingServiceImpl;

// import jakarta.transaction.Transactional;
// import jakarta.validation.Valid;

// import java.time.DayOfWeek;
// import java.time.LocalTime;
// import java.util.ArrayList;
// import java.util.List;

// @RestController
// @RequestMapping("/trainings")
// public class TrainingController {
   
//     @Autowired
//     private TrainingService trainingService;

// @PostMapping("/create")
// public ResponseEntity<?> createTraining(@RequestBody List<Training> trainingList) {
//      try {
//         Training training;
//         List<Schedule> selectedSchedules = training.getSchedules();
//         Group group = training.getGroup();
//         Coach coach = training.getCoach();
//         Field field = training.getField();

//         // Seu código para validar a disponibilidade e criar o treinamento...

//         Training createdTraining = trainingService.createTraining(group, coach, field, selectedSchedules);
//         return new ResponseEntity<>(createdTraining, HttpStatus.CREATED);
//     } catch (RuntimeException e) {
//         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//     }
// }

   
// }
