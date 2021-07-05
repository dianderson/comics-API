package com.zup.comicsapi.repository;

import com.zup.comicsapi.repository.model.Autor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends CrudRepository<Autor, String> {
}
