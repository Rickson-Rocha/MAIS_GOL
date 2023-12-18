package com.br.maisgol.model.training;


import java.io.Serializable;
import java.util.List;

import com.br.maisgol.model.coach.Coach;
import com.br.maisgol.model.field.Field;
import com.br.maisgol.model.group.Group;
import com.br.maisgol.model.schedule.Schedule;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_training")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Training  implements Serializable{
    
    public Training(Field field2, Schedule schedule2, Coach coach2, Group group2) {
    }

    public Training(Field field2, Coach coach2, Group group2) {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_training")
    private Long id;

    @NotNull
    @ManyToOne
    
    private Field field;

    // @NotNull
    // @ManyToOne
    // private Schedule schedule;
    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;


    @NotNull
    @ManyToOne
    private Coach coach;

    @NotNull
    @ManyToOne
    private Group group;

}
