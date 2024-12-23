package com.backinsolvencia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gasto_manutencion")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GastosManutencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    private float energia;

    private float aguaalcantarilladoaseo;

    private float gas;

    private float telecomunicacion;

    private float television;

    private float arriendo;

    private float administracion;

    private float salud;

    private float seguro;

    private float aliminacion;

    private float educacion;

    private float transporte;

    private float otros;


}
