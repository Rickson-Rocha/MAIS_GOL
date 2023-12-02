package com.br.maisgol.model.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_address")
public class Address {
    @Size(max=8)
    @NotNull
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Invalid ZIP code format. Use the format: XXXXX-XXX")
    @Column(name = "cep_address")
    private String zipCode;

    @NotNull
    @Column(name = "street_address")
    private String street;

    @NotNull
    @Column(name = "number_address")
    private String number;

    @NotNull
    @Column(name = "city_address")
    private String city;

    @NotNull
    @Column(name = "state_address")
    private String state;

    @NotNull
    @Column(name = "neighborhood__address")
    private String neighborhood;

    @Column(name = "block_address")
    private String block;

    @Column(name = "floor_address")
    private String floor;
    
}
