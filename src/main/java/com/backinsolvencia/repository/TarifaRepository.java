package com.backinsolvencia.repository;

import com.backinsolvencia.models.Tarifa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends CrudRepository<Tarifa, Long> {
}
