package com.zup.comicsapi.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ExternalData {
    @JsonProperty("results")
    private List<ExternalResult> externalResults;
}
