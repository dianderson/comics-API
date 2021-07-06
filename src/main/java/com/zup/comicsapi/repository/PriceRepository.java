package com.zup.comicsapi.repository;

import com.zup.comicsapi.repository.model.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {
}
