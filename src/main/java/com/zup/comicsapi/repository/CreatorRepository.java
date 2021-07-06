package com.zup.comicsapi.repository;

import com.zup.comicsapi.repository.model.Creator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorRepository extends CrudRepository<Creator, String> {
}
