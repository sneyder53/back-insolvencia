package com.backinsolvencia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "causa")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Causa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "causas")
    @JsonIgnore
    private List<Insolvencia> insolvencias;

}
