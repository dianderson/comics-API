package com.zup.comicsapi.repository.model;

import com.zup.comicsapi.resource.dto.request.CreateUsuarioRequest;
import com.zup.comicsapi.resource.dto.response.UsuarioResponse;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Column(unique = true)
    private String cpf;
    @NotNull
    private LocalDate dataDeNascimento;
    @ManyToMany
    private List<Livro> livros;

    public static Usuario of(CreateUsuarioRequest request) {
        var usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setCpf(request.getCpf());
        usuario.setDataDeNascimento(request.getDataDeNascimento());
        usuario.setLivros(request.getLivros());
        return usuario;
    }

    public static Usuario of(UsuarioResponse request) {
        var usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setCpf(request.getCpf());
        usuario.setDataDeNascimento(request.getDataDeNascimento());
        usuario.setLivros(request.getLivros());
        return usuario;
    }
}
