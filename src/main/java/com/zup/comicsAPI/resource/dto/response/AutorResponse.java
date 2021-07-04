package com.zup.comicsAPI.resource.dto.response;

import com.zup.comicsAPI.repository.model.Autor;
import lombok.Data;

@Data
public class AutorResponse {
    private String resourceURI;
    private String name;
    private String role;

    public static AutorResponse of(Autor autor) {
        var autorResponse = new AutorResponse();
        autorResponse.setResourceURI(autor.getResourceURI());
        autorResponse.setName(autor.getName());
        autorResponse.setRole(autor.getRole());
        return autorResponse;
    }
}
