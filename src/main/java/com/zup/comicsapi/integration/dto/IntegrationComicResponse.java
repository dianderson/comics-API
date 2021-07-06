package com.zup.comicsapi.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IntegrationComicResponse {
    @JsonProperty("code")
    private Long code;
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private ExternalData externalData;
}
