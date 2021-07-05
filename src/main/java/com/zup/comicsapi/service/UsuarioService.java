package com.zup.comicsapi.service;

import com.zup.comicsapi.repository.UsuarioRepository;
import com.zup.comicsapi.repository.model.Desconto;
import com.zup.comicsapi.repository.model.Livro;
import com.zup.comicsapi.repository.model.Precos;
import com.zup.comicsapi.repository.model.Usuario;
import com.zup.comicsapi.resource.dto.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final LivroService livroService;
    private final DescontoService descontoService;

    public UsuarioResponse save(Usuario usuario) {
        return UsuarioResponse.of(usuarioRepository.save(usuario));
    }

    public UsuarioResponse saveComics(Long usuarioId, List<Integer> livrosId) {
        Usuario usuarioToBeUpdated = Usuario.of(findById(usuarioId));
        usuarioToBeUpdated.setId(usuarioId);

        List<Livro> livros = usuarioToBeUpdated.getLivros();
        for (int id : livrosId) {
            livros.add(Livro.of(livroService.save(id)));
        }

        usuarioToBeUpdated.setLivros(livros);

        return UsuarioResponse.of(usuarioRepository.save(usuarioToBeUpdated));
    }

    public UsuarioResponse findById(Long id) {
        var usuario = usuarioRepository
                .findById(id)
                .map(UsuarioResponse::of)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario com id %s não encontrado", id)));

        for (Livro livro : usuario.getLivros()) {
            String isbn = livro.getIsbn();
            if (!isbn.isBlank()) {
                Integer lastIsbn = Character.getNumericValue(isbn.charAt(isbn.length() - 1));
                Desconto desconto = descontoService.findByFinalIsbn(lastIsbn);
                livro.setDiaDaSemana(desconto.getDiaDaSemana());
                livro.setDescontoAtivo(desconto.getDiaDaSemana() == LocalDate.now().getDayOfWeek());
                if (livro.isDescontoAtivo()) {
                    for (Precos preco : livro.getPrecos()) {
                        preco.setPrice(preco.getPrice() * (1F - desconto.getValorDesconto() / 100F));
                    }
                }
            }
        }
        return usuario;
    }
}
