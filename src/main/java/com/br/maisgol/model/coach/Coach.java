package com.br.maisgol.model.coach;

import java.time.LocalDate;

import com.br.maisgol.model.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_coach")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name_coach")
    private String name;

    @NotNull
    @Column(name = "cpf_coach")
    private String cpf;

    @Column(name = "phone_coach")
    @NotNull
    private String phone;

    @Column(name="status_coach")
    @NotNull
    private Status status;

    @Column(name = "birth_date_coach")
    @NotNull
    private LocalDate birthDay;
}
