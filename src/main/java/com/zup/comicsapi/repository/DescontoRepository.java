package com.zup.comicsapi.repository;

import com.zup.comicsapi.repository.model.Desconto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescontoRepository extends CrudRepository<Desconto, Long> {
    Desconto findByfinalIsbn(Integer finalIsbn);
}
