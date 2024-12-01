package com.backinsolvencia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "acreedor", referencedColumnName = "id")
    private Acreedor acreedor;

    @OneToMany(mappedBy = "productoId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InsolvenciaProducto> insolvenciaProductos;
}
