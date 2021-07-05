package com.zup.comicsapi.resource.dto.response;

import com.zup.comicsapi.repository.model.Livro;
import com.zup.comicsapi.repository.model.Usuario;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UsuarioResponse {
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataDeNascimento;
    private List<Livro> livros;

    public static UsuarioResponse of(Usuario usuario) {
        var response = new UsuarioResponse();
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setCpf(usuario.getCpf());
        response.setDataDeNascimento(usuario.getDataDeNascimento());
        response.setLivros(usuario.getLivros());
        return response;
    }
}
