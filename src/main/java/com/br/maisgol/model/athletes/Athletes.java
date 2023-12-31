package com.br.maisgol.model.athletes;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.br.maisgol.model.enums.Status;
import com.br.maisgol.model.group.Group;
import com.br.maisgol.model.guardian.Guardian;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "tb_athlete")
@Entity(name = "tb_athlete")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"group"})
public class Athletes implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_athlete")
    private Long id;

    @NotNull
    @Column(name = "name_athlete")
    private String name;

    @Column(name ="cpf" , unique = true)
    private String cpf;

    @NotNull
    @Column(name = "height_athlete")
    private Double height;

    @NotNull
    @Column(name = "weight_athlete")
    private Double weight;


    @NotNull
    @Column(name = "status_athletes")
    @Enumerated(EnumType.STRING)
    private Status status;


    @NotNull
    @Column(name = "birth_date_athlete")
    private LocalDateTime birthDate; 

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpf_guardian", referencedColumnName = "cpf_guardian")
    private Guardian guardian;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id_group")
    private Group group;

}