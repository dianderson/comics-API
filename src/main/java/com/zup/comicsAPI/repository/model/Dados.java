package com.zup.comicsAPI.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Dados {
    @JsonProperty("results")
    List<Resultados> resultados = new ArrayList<>();
}
