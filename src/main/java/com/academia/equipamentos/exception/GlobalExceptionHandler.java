package com.academia.equipamentos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe global para tratamento de exceções no sistema.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manipula exceções do tipo ResourceNotFoundException.
     *
     * @param ex      A exceção lançada.
     * @param request Os detalhes da requisição onde ocorreu a exceção.
     * @return ResponseEntity com os detalhes do erro e o status HTTP 404.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Manipula exceções genéricas não tratadas explicitamente.
     *
     * @param ex      A exceção lançada.
     * @param request Os detalhes da requisição onde ocorreu a exceção.
     * @return ResponseEntity com os detalhes do erro e o status HTTP 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        body.put("message", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
        body.put("details", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

