package com.backinsolvencia.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "causa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Causa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @OneToOne
    @JoinColumn(name="insolvencia_id")
    private Insolvencia insolvencia;
}
