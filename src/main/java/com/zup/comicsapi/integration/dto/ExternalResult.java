package com.zup.comicsapi.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.comicsapi.repository.model.Price;
import lombok.Data;

import java.util.List;

@Data
public class ExternalResult {
    private Long id;
    private String title;
    private String isbn;
    private String description;
    @JsonProperty("prices")
    private List<Price> prices;
    @JsonProperty("creators")
    private ExternalCreators externalCreators;
}
