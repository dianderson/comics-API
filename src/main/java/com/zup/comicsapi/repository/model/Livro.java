package com.zup.comicsapi.repository.model;

import com.zup.comicsapi.integration.dto.IntegrationComicResponse;
import com.zup.comicsapi.integration.dto.Resultados;
import com.zup.comicsapi.resource.dto.response.LivroResponse;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.util.List;

@Data
@Entity
public class Livro {
    @Id
    @NotNull
    private Integer comicId;
    @NotBlank
    private String titulo;
    @OneToMany
    private List<Precos> precos;
    @NotNull
    @ManyToMany
    private List<Autor> autores;
    private String isbn;
    @NotBlank
    @Column(length = 1500)
    private String descricao;
    private DayOfWeek diaDaSemana;
    private boolean descontoAtivo;

    public static Livro of(IntegrationComicResponse response) {
        var livro = new Livro();
        for (Resultados res : response.getDados().getResultados()) {
            livro.setComicId(res.getId());
            livro.setTitulo(res.getTitle());
            livro.setIsbn(res.getIsbn());
            livro.setDescricao(res.getDescription());
            livro.setPrecos(res.getPrices());
            livro.setAutores(res.getCreators().getItems());
        }
        return livro;
    }

    public static Livro of(LivroResponse response) {
        var livro = new Livro();
        livro.setComicId(response.getComicId());
        livro.setTitulo(response.getTitulo());
        livro.setIsbn(response.getIsbn());
        livro.setDescricao(response.getDescricao());
        livro.setPrecos(response.getPrecos());
        livro.setAutores(response.getAutores());
        return livro;
    }
}
