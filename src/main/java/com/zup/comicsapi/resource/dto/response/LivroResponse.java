package com.zup.comicsapi.resource.dto.response;

import com.zup.comicsapi.repository.model.Autor;
import com.zup.comicsapi.repository.model.Livro;
import com.zup.comicsapi.repository.model.Precos;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;

@Data
public class LivroResponse {
    private int comicId;
    private String titulo;
    private List<Precos> precos;
    private List<Autor> autores;
    private String isbn;
    private String descricao;
    private DayOfWeek diaDaSemana;
    private boolean descontoAtivo;

    public static LivroResponse of(Livro livro) {
        var response = new LivroResponse();
        response.setComicId(livro.getComicId());
        response.setTitulo(livro.getTitulo());
        response.setPrecos(livro.getPrecos());
        response.setAutores(livro.getAutores());
        response.setIsbn(livro.getIsbn());
        response.setDescricao(livro.getDescricao());
        response.setDiaDaSemana(livro.getDiaDaSemana());
        response.setDescontoAtivo(livro.isDescontoAtivo());
        return response;
    }
}
