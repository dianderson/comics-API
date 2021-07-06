package com.zup.comicsapi.error.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorHandlerModel {
    private long timestamp;
    private int httpStatus;
    private List<Message> errors;
}
