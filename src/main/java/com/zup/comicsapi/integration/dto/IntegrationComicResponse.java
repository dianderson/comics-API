package com.zup.comicsapi.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class IntegrationComicResponse {
    @JsonProperty("code")
    private Long code;
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private Data data;
}
