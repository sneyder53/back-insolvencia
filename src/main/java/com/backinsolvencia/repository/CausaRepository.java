package com.backinsolvencia.repository;

import com.backinsolvencia.models.Causa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CausaRepository extends CrudRepository<Causa, Long> {
}
