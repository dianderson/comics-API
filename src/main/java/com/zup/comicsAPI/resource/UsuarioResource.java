package com.zup.comicsAPI.resource;

import com.zup.comicsAPI.repository.model.Livro;
import com.zup.comicsAPI.repository.model.Usuario;
import com.zup.comicsAPI.resource.dto.request.CreateUsuarioRequest;
import com.zup.comicsAPI.resource.dto.response.UsuarioResponse;
import com.zup.comicsAPI.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/usuario")
public class UsuarioResource {
    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse save(@RequestBody @Valid CreateUsuarioRequest request) {
        return usuarioService.save(Usuario.of(request));
    }

    @PutMapping("/{id}")
    public UsuarioResponse saveComics(@PathVariable Long id, @RequestBody List<Integer> comicsIds) {
        return usuarioService.saveComics(id, comicsIds);
    }

    @GetMapping("/{id}")
    public UsuarioResponse findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }
}
