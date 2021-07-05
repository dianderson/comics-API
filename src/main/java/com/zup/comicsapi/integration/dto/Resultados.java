package com.zup.comicsapi.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.comicsapi.integration.dto.Criadores;
import com.zup.comicsapi.repository.model.Precos;
import lombok.Data;

import java.util.List;

@Data
public class Resultados {
    private int id;
    private String title;
    private String isbn;
    private String description;
    @JsonProperty("prices")
    private List<Precos> prices;
    @JsonProperty("creators")
    private Criadores creators;
}
