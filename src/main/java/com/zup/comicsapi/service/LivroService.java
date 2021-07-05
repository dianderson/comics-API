package com.zup.comicsapi.service;

import com.zup.comicsapi.integration.IntegrationFeignClient;
import com.zup.comicsapi.repository.LivroRepository;
import com.zup.comicsapi.repository.model.Autor;
import com.zup.comicsapi.repository.model.Livro;
import com.zup.comicsapi.repository.PrecoRepository;
import com.zup.comicsapi.repository.model.Precos;
import com.zup.comicsapi.integration.dto.IntegrationComicResponse;
import com.zup.comicsapi.resource.dto.response.LivroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final IntegrationFeignClient integrationFeignClient;
    private final LivroRepository livroRepository;
    private final PrecoRepository precoRepository;
    private final AutorService autorService;

    @Value("${marvel.key}")
    private String apikey;
    @Value("${marvel.hash}")
    private String hash;

    public LivroResponse save(int comicId) {
        Livro livro = Livro.of(importLivro(comicId));
        for (Precos preco : livro.getPrecos()) {
            precoRepository.save(preco);
        }
        for (Autor autor : livro.getAutores()) {
            autorService.save(autor);
        }
        return LivroResponse.of(livroRepository.save(livro));
    }

    public IntegrationComicResponse importLivro(int comicId) {
        return integrationFeignClient.importLivro(comicId, "1", apikey, hash);
    }
}
