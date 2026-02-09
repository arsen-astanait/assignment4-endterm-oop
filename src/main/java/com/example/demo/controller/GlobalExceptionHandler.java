package com.example.demo.controller;

import com.example.demo.exception.InvalidInventoryException;
import com.example.demo.exception.InventoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InventoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound(InventoryNotFoundException ex) {
        return Map.of("error", "NOT_FOUND", "message", ex.getMessage());
    }

    @ExceptionHandler(InvalidInventoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBadRequest(InvalidInventoryException ex) {
        return Map.of("error", "BAD_REQUEST", "message", ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleServer(RuntimeException ex) {
        return Map.of("error", "SERVER_ERROR", "message", ex.getMessage());
    }
}
