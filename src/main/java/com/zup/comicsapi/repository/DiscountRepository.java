package com.zup.comicsapi.repository;

import com.zup.comicsapi.repository.model.Discount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends CrudRepository<Discount, Long> {
    Discount findByFinalIsbn(Integer finalIsbn);
}
