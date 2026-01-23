package com.rafael.itmanager.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroResponse> handleResponseStatusException(ResponseStatusException e) {
        ErroResponse erro = new ErroResponse(
                LocalDateTime.now(),
                e.getStatusCode().value(),
                "Erro na requisição",
                e.getReason()
        );

        return ResponseEntity.status(e.getStatusCode()).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroResponse> handleDadosDuplicados(DataIntegrityViolationException e) {
        ErroResponse erro = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflito de dados",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    public ResponseEntity<ErroResponse> handleGenericException(Exception e) {
        ErroResponse erro = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

}
