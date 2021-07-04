package com.zup.comicsAPI.resource.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.comicsAPI.repository.model.Livro;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreateUsuarioRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String cpf;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    private List<Livro> livros;
}
