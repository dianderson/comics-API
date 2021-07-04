package com.zup.comicsAPI.repository.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecoRepository extends CrudRepository<Precos, Long> {
}
