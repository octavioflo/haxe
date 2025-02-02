package com.haxe.backend.exception;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handle(
            Exception e,
            HttpServletRequest request) {
        ApiError apiError = new ApiError(request.getRequestURI(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ZonedDateTime.now(), List.of());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handle(
            ResourceNotFoundException e,
            HttpServletRequest request) {
        ApiError apiError = new ApiError(request.getRequestURI(), e.getMessage(), HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(), List.of());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handle(DuplicateResourceException e, HttpServletRequest request) {
        ApiError apiError = new ApiError(request.getRequestURI(), e.getMessage(), HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(), List.of());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

}
