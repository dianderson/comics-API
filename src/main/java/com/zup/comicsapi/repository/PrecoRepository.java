package com.zup.comicsapi.repository;

import com.zup.comicsapi.repository.model.Precos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecoRepository extends CrudRepository<Precos, Long> {
}
