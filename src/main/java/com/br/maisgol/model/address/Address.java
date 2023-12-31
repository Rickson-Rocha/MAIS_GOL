package com.br.maisgol.model.address;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
