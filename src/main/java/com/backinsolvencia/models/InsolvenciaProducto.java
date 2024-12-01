package com.backinsolvencia.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "insolvencia_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsolvenciaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "insolvenciaId", referencedColumnName = "id")
    private Insolvencia insolvenciaId;

    @ManyToOne
    @JoinColumn(name = "productoId", referencedColumnName = "id")
    private Producto productoId;

    @Column(nullable = false)
    private Double valorDeuda;

    @Column(nullable = false)
    private int diasMora;
}
