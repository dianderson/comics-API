package com.zup.comicsapi.resource.dto.request;

import com.zup.comicsapi.repository.model.Autor;
import com.zup.comicsapi.repository.model.Precos;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateLivroRequest {
    @Id
    @NotNull
    private int comicId;
    @NotBlank
    private String titulo;
    @NotNull
    private List<Precos> precos;
    @NotNull
    @ManyToMany
    private List<Autor> autores;
    @NotBlank
    private String isbn;
    @NotBlank
    private String descricao;
}
