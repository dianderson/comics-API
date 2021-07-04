package com.zup.comicsAPI.resource.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.comicsAPI.repository.model.Dados;
import lombok.Data;

@Data
public class ImportLivroResponse {
    @JsonProperty("code")
    private int code;
    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    Dados dados = new Dados();
}
