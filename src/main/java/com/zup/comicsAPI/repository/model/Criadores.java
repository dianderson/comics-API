package com.zup.comicsAPI.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Criadores {
    @JsonProperty("items")
    private List<Autor> items;
}
