package com.backinsolvencia.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "insolvencia")
@Data
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
    @JsonManagedReference
    private List<InsolvenciaProducto> insolvenciaProductos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( name = "insolvencia_causas" ,
            joinColumns = @JoinColumn(name = "insolvencia_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "causa_id", referencedColumnName = "id"))
    private List<Causa> causas;
}
