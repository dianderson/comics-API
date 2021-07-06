package com.zup.comicsapi.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.comicsapi.repository.model.Creator;
import lombok.Data;

import java.util.List;

@Data
public class ExternalCreators {
    @JsonProperty("items")
    private List<Creator> creators;
}
