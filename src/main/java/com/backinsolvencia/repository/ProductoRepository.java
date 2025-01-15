package com.backinsolvencia.repository;


import com.backinsolvencia.models.Acreedor;
import com.backinsolvencia.models.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
    List<Producto> findByAcreedor(Acreedor acreedor);
}
