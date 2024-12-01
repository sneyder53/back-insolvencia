package com.backinsolvencia.repository;

import com.backinsolvencia.models.Insolvencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsolvenciaRepository extends CrudRepository<Insolvencia, Long> {
}
