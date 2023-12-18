package com.br.maisgol.model.guardian;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_guardian")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_guardian")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @NotNull
    @Column(name = "name_guardian")
    private String name;

    @NotNull
    @Column(name = "cpf_guardian")
    @Digits(integer = 11, fraction = 0, message = "O CPF deve conter apenas dígitos")
    private String cpf;

    @NotNull
    @Column(name = "guardian_birth_date")
    private LocalDate birthDay;
    
    @NotNull
    @Digits(integer = 12, fraction = 0, message = "O Número de telefone deve conter apenas dígitos")
    @Column(name = "phone_guardian")
    private  String phone;
}
