package com.zup.comicsapi.error;

import com.zup.comicsapi.error.exception.EntityNotFoundException;
import com.zup.comicsapi.error.model.ErrorHandlerModel;
import com.zup.comicsapi.error.model.Message;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class AccountApiControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        final HttpStatus statusResponse = HttpStatus.BAD_REQUEST;
        return super.handleExceptionInternal(
                ex,
                ErrorHandlerModel
                        .builder()
                        .errors(List.of(Message.builder().value(ex.getMessage()).build()))
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .timestamp(System.currentTimeMillis())
                        .build(),
                new HttpHeaders(),
                statusResponse,
                request);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        final HttpStatus statusResponse = HttpStatus.NOT_FOUND;
        return super.handleExceptionInternal(
                ex,
                ErrorHandlerModel
                        .builder()
                        .errors(List.of(Message.builder().value(ex.getMessage()).build()))
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .timestamp(System.currentTimeMillis())
                        .build(),
                new HttpHeaders(),
                statusResponse,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {
        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::message)
                .collect(Collectors.toList());
        return super.handleExceptionInternal(
                ex,
                of(errors),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    public ErrorHandlerModel of(final List<Message> messages) {
        return builder(messages).errors(messages).build();
    }

    private ErrorHandlerModel.ErrorHandlerModelBuilder builder(List<Message> errors) {
        return ErrorHandlerModel.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .timestamp(System.currentTimeMillis());
    }

    public Message message(final FieldError fieldError) {
        Map<String, String> variables = extractVariables(fieldError);
        return Message.builder().value(fieldError.getDefaultMessage()).variables(variables).build();
    }

    public Map<String, String> extractVariables(FieldError fieldError) {
        Map<String, String> fv =
                Map.of(
                        "field",
                        fieldError.getField(),
                        "value",
                        String.valueOf(fieldError.getRejectedValue()));
        Map<String, String> args = new HashMap<>();
        if (fieldError.getArguments() != null
                && Arrays.stream(fieldError.getArguments()).count() != 0) {
            Object value =
                    Arrays.stream(fieldError.getArguments())
                            .filter(o -> !(o instanceof DefaultMessageSourceResolvable))
                            .findFirst()
                            .orElse(null);
            args.putAll(
                    Map.of(
                            Objects.requireNonNull(
                                    Objects.requireNonNull(fieldError.getCode()).toLowerCase()),
                            String.valueOf(value)));
        }
        Stream<Map.Entry<String, String>> combined =
                Stream.concat(fv.entrySet().stream(), args.entrySet().stream());
        return combined.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
