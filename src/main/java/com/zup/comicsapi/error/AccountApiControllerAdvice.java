package com.zup.comicsapi.error;

import com.zup.comicsapi.error.model.ErrorHandlerModel;
import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class AccountApiControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorHandlerModel> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        var error = new ErrorHandlerModel();
        error.setTimestamp(Instant.now());
        error.setHttpStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        var error = new ErrorHandlerModel();
        error.setTimestamp(Instant.now());
        error.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorHandlerModel> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        var error = new ErrorHandlerModel();
        error.setTimestamp(Instant.now());
        error.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = FeignException.FeignClientException.NotFound.class)
    public ResponseEntity<ErrorHandlerModel> handleFeignExceptionNotFound(FeignException ex, HttpServletRequest request) {
        var error = new ErrorHandlerModel();
        error.setTimestamp(Instant.now());
        error.setHttpStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(value = FeignException.FeignClientException.Conflict.class)
    public ResponseEntity<ErrorHandlerModel> handleFeignExceptionConflict(FeignException ex, HttpServletRequest request) {
        var error = new ErrorHandlerModel();
        error.setTimestamp(Instant.now());
        error.setHttpStatus(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
