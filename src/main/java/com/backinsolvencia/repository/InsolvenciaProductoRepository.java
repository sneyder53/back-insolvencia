package com.backinsolvencia.repository;

import com.backinsolvencia.models.InsolvenciaProducto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsolvenciaProductoRepository extends CrudRepository<InsolvenciaProducto, Long> {
}
