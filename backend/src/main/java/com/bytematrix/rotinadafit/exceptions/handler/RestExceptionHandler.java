package com.bytematrix.rotinadafit.exceptions.handler;

import com.bytematrix.rotinadafit.exceptions.JsonErrorStructure;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {
    private static final HttpStatus FORBIDDEN = HttpStatus.FORBIDDEN;
    private static final HttpStatus NOT_FOUND = HttpStatus.FORBIDDEN;
    private static final HttpStatus INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final HttpStatus CONFLICT = HttpStatus.CONFLICT;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonErrorStructure> genericExceptionHandle (Exception exception){
        var jsonErrorStructure = new JsonErrorStructure(LocalDateTime.now(), INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.name(), exception.getMessage());
        return new ResponseEntity<>(jsonErrorStructure, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({EntityNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<JsonErrorStructure> forbiddenExceptionHandler (Exception exception){
        var jsonErrorStructure = new JsonErrorStructure(LocalDateTime.now(), NOT_FOUND.value(), NOT_FOUND.name(), exception.getMessage());
        return new ResponseEntity<>(jsonErrorStructure, NOT_FOUND);
    }

    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<JsonErrorStructure> conflictExceptionHandler(EntityExistsException exception) {
        var jsonErrorStructure = new JsonErrorStructure(LocalDateTime.now(), CONFLICT.value(), CONFLICT.name(), exception.getMessage());
        return new ResponseEntity<>(jsonErrorStructure, CONFLICT);
    }
}
