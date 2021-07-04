package com.zup.comicsAPI.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
