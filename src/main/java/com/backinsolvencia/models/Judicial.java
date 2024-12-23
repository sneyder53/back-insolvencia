package com.backinsolvencia.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "judicial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Judicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "insolvenciaId", referencedColumnName = "id")
    @JsonBackReference
    private Insolvencia insolvenciaId;

    private String juzgado;

    private String radicado;

    private String demandante;

    private String tipoProceso;

}
