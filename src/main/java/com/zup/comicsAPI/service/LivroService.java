package com.zup.comicsAPI.service;

import com.zup.comicsAPI.repository.LivroFeignClient;
import com.zup.comicsAPI.repository.LivroRepository;
import com.zup.comicsAPI.repository.model.Autor;
import com.zup.comicsAPI.repository.model.Livro;
import com.zup.comicsAPI.repository.model.PrecoRepository;
import com.zup.comicsAPI.repository.model.Precos;
import com.zup.comicsAPI.resource.dto.response.ImportLivroResponse;
import com.zup.comicsAPI.resource.dto.response.LivroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroFeignClient livroFeignClient;
    private final LivroRepository livroRepository;
    private final PrecoRepository precoRepository;
    private final AutorService autorService;

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

    public ImportLivroResponse importLivro(int comicId) {
        return livroFeignClient.importLivro(comicId, "1", "03f275351e75358c53fbb78909b90ffb", "81ca290515009266e9d114889465a9da");
    }
}
