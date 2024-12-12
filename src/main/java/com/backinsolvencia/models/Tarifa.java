package com.backinsolvencia.models;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "tarifa")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private Double rangoMenor;
    @NotNull
    @Column(nullable = false)
    private Double rangoMayor;
    @NotNull
    @Column(nullable = false)
    private Double honorarios;
    @NotNull
    @Column(nullable = false)
    private int numeroCuotas;
    @NotNull
    @Column(nullable = false)
    private Float porcentaje;

    @Override
    public String toString() {
        return "Tarifa{" +
                "id=" + id +
                ", rangoMenor=" + rangoMenor +
                ", rangoMayor=" + rangoMayor +
                ", honorarios=" + honorarios +
                ", numeroCuotas=" + numeroCuotas +
                ", porcentaje=" + porcentaje +
                '}';
    }
}
