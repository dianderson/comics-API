package com.zup.comicsapi.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.comicsapi.repository.model.Autor;
import lombok.Data;

import java.util.List;

@Data
public class Criadores {
    @JsonProperty("items")
    private List<Autor> items;
}
