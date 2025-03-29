package com.example.test1221.api.exception;

import com.example.test1221.api.dto.ExceptionResponse;
import com.example.test1221.core.exception.EntityNotFoundException;
import com.example.test1221.core.exception.ExistenceException;
import com.example.test1221.core.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = ApiExceptionHandling.class)
public class ApiControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(ExistenceException.class)
    public ResponseEntity<ExceptionResponse> handleExistenceException(ExistenceException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(e.getMessage()));
    }
}
