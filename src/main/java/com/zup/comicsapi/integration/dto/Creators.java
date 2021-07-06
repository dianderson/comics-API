package com.zup.comicsapi.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Creators {
    @JsonProperty("items")
    private List<Creators> items;
}
