package com.br.maisgol.model.training;


import java.io.Serializable;
import java.util.List;

import com.br.maisgol.model.coach.Coach;
import com.br.maisgol.model.group.Group;
import com.br.maisgol.model.schedule.Schedule;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_training")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_group")
    private Group group;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_coach")
    private Coach coach;

    @NotNull
    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

}
