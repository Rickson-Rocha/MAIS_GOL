package com.br.maisgol.model.group;

import java.util.List;
import com.br.maisgol.model.athletes.Athletes;
import com.br.maisgol.model.enums.Status;
import com.br.maisgol.model.training.Training;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_group")
@Table(name="tb_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @NotNull
    @Column(name="name_group",unique = true)
    private String name;

    @Column(name="description_group")
    private String description;

    @Column(name="status_group")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status  status;

    @OneToMany(mappedBy = "group")
    private List<Athletes> athletes;

    @OneToMany(mappedBy = "group")
    private List<Training> training;
}
