package com.zup.comicsAPI.repository.model;

import com.zup.comicsAPI.resource.dto.response.ImportLivroResponse;
import com.zup.comicsAPI.resource.dto.response.LivroResponse;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private DiasDaSemana diasDaSemana;
    private boolean descontoAtivo;

    public static Livro of(ImportLivroResponse response) {
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
