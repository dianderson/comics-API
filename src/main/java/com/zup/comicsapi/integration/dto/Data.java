package com.zup.comicsapi.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@lombok.Data
public class Data {
    @JsonProperty("results")
    private List<Result> results;
}
