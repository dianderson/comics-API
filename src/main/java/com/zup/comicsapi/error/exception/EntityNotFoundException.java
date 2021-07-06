package com.zup.comicsapi.error.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class EntityNotFoundException extends RuntimeException {
    private String message;

    public EntityNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
