package com.zup.comicsapi.error.model;

import lombok.Data;

import java.time.Instant;

@Data
public class ErrorHandlerModel {
    private Instant timestamp;
    private int httpStatus;
    private String message;
    private String path;
}
