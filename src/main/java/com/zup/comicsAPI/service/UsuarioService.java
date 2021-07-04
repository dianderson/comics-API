package com.zup.comicsAPI.service;

import com.zup.comicsAPI.repository.UsuarioRepository;
import com.zup.comicsAPI.repository.model.Livro;
import com.zup.comicsAPI.repository.model.Usuario;
import com.zup.comicsAPI.resource.dto.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final LivroService livroService;

    public UsuarioResponse save(Usuario usuario) {
        return UsuarioResponse.of(usuarioRepository.save(usuario));
    }

    public UsuarioResponse saveComics(Long usuarioId, List<Integer> livrosId) {
        Usuario usuarioToBeUpdated = Usuario.of(findById(usuarioId));
        usuarioToBeUpdated.setId(usuarioId);

        List<Livro> livros = new ArrayList<>();
        for (int id : livrosId) {
            livros.add(Livro.of(livroService.save(id)));
        }

        usuarioToBeUpdated.setLivros(livros);

        return UsuarioResponse.of(usuarioRepository.save(usuarioToBeUpdated));
    }

    public UsuarioResponse findById(Long id) {
        return usuarioRepository
                .findById(id)
                .map(UsuarioResponse::of)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario com id %s não encontrado", id)));
    }
}
