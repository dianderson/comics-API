package com.zup.comicsAPI.resource.dto.response;

import com.zup.comicsAPI.repository.model.Autor;
import com.zup.comicsAPI.repository.model.DiasDaSemana;
import com.zup.comicsAPI.repository.model.Livro;
import com.zup.comicsAPI.repository.model.Precos;
import lombok.Data;

import java.util.List;

@Data
public class LivroResponse {
    private int comicId;
    private String titulo;
    private List<Precos> precos;
    private List<Autor> autores;
    private String isbn;
    private String descricao;
    private DiasDaSemana diasDaSemana;
    private boolean descontoAtivo;

    public static LivroResponse of(Livro livro) {
        var response = new LivroResponse();
        response.setComicId(livro.getComicId());
        response.setTitulo(livro.getTitulo());
        response.setPrecos(livro.getPrecos());
        response.setAutores(livro.getAutores());
        response.setIsbn(livro.getIsbn());
        response.setDescricao(livro.getDescricao());
        response.setDiasDaSemana(livro.getDiasDaSemana());
        response.setDescontoAtivo(livro.isDescontoAtivo());
        return response;
    }
}
