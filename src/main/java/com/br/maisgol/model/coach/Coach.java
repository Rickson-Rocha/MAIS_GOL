package com.br.maisgol.model.coach;

import java.io.Serializable;
import java.time.LocalDate;

import com.br.maisgol.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
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
public class Coach implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coach")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @NotNull
    @Column(name = "name_coach")
    private String name;

    @NotNull
    @Column(name = "cpf_coach",unique = true)
    @Digits(integer = 11, fraction = 0, message = "O CPF deve conter apenas dígitos")
    private String cpf;

    @Column(name = "phone_coach")
    @NotNull
    @Digits(integer = 12, fraction = 0, message = "O Número de telefone deve conter apenas dígitos")
    private String phone;

    @Column(name="status_coach")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "birth_date_coach")
    @NotNull
    private LocalDate birthDay;

    @Lob
    @Column(name = "photo_coach")
    private byte[] photo;
}
