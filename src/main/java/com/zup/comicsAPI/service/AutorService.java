package com.zup.comicsAPI.service;

import com.zup.comicsAPI.repository.AutorRepository;
import com.zup.comicsAPI.repository.model.Autor;
import com.zup.comicsAPI.resource.dto.response.AutorResponse;
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
