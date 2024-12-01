package com.backinsolvencia.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tarifa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Double rangoMenor;
    @Column(nullable = false)
    private Double rangoMayor;
    @Column(nullable = false)
    private Double honorarios;
    @Column(nullable = false)
    private int numeroCuotas;
    @Column(nullable = false)
    private Float porcentaje;
}
