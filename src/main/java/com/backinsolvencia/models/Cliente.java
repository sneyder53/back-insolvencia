package com.backinsolvencia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "cliente")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String identificacion;

    @Column(nullable = false)
    private String ciudadExpedicion;

    @Column(nullable = false)
    private String departamentExpedicion;

    @Column(nullable = false)
    private String ciudadResidencia;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(unique = true, nullable = false)
    private String email;

    @Column( nullable = true)
    private String telefono;

    @Column( nullable = true)
    private String direccion;

    @Column( nullable = true)
    private String profesion;

    @Column(nullable = true)
    private String estadoCivil;

    @Column(nullable = true)
    private LocalDate fechaNacimiento;

    @Column(nullable = true)
    private Integer personasaCargo;

    @Column( nullable = false)
    private Boolean camaraComercio;

    @Column( nullable = false)
    private Double ingresos;

    @Column( nullable = false)
    private Double descuentos;

    @Column( nullable = false)
    private boolean orientacion;

    @OneToOne(mappedBy = "cliente")
    @JsonIgnore
    private Insolvencia insolvencia;


    @Transient
    public Integer getEdad() {

        if (fechaNacimiento == null) {
            return null;
        }

        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

}
