package com.zup.comicsapi.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Dados {
    @JsonProperty("results")
    private List<Resultados> resultados;
}
