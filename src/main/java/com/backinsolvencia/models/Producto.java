package com.backinsolvencia.models;

import com.backinsolvencia.enums.CategoriaProducto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "producto")
@Data
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaProducto categoria;

    @ManyToOne
    @JoinColumn(name = "acreedor", referencedColumnName = "id")
    private Acreedor acreedor;

    @OneToMany(mappedBy = "productoId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InsolvenciaProducto> insolvenciaProductos;
}
