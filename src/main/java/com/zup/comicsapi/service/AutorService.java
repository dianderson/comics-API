package com.zup.comicsapi.service;

import com.zup.comicsapi.repository.AutorRepository;
import com.zup.comicsapi.repository.model.Autor;
import com.zup.comicsapi.resource.dto.response.AutorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorResponse save(Autor autor) {
        return AutorResponse.of(autorRepository.save(autor));
    }
}
