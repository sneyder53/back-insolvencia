package com.backinsolvencia.repository;

import com.backinsolvencia.models.Acreedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcreedorRepository extends CrudRepository<Acreedor, Long> {
}
