package com.zup.comicsapi.resource.dto.response;

import com.zup.comicsapi.repository.model.Creator;
import lombok.Data;

@Data
public class CreatorResponse {
    private String resourceURI;
    private String name;
    private String role;

    public static CreatorResponse of(Creator creator) {
        var response = new CreatorResponse();
        response.setResourceURI(creator.getResourceURI());
        response.setName(creator.getName());
        response.setRole(creator.getRole());
        return response;
    }
}
