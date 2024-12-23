package com.backinsolvencia.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bienes_muebles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BienesMueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "insolvenciaId", referencedColumnName = "id")
    @JsonBackReference
    private Insolvencia insolvenciaId;

    private String tipo;

    private String numero;

    private float valor;

    private String afectacion;


}
