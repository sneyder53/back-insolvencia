package com.backinsolvencia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "insolvencia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Insolvencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String estado;

    @OneToMany(mappedBy = "insolvenciaId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InsolvenciaProducto> insolvenciaProductos;

    @OneToOne(mappedBy = "insolvencia", cascade = CascadeType.ALL)
    private Cliente cliente;

    @OneToOne(mappedBy = "insolvencia", cascade = CascadeType.ALL)
    private Causa causa;
}
