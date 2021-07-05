package com.zup.comicsapi.repository;

import com.zup.comicsapi.repository.model.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Integer> {
    List<Livro> findAll();
}
