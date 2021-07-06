package com.zup.comicsapi.repository;

import com.zup.comicsapi.repository.model.Comic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComicRepository extends CrudRepository<Comic, Integer> {
    Optional<Comic> findByExternalComicId(Long comicId);
}
