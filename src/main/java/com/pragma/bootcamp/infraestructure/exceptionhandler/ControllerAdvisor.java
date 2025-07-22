package com.pragma.bootcamp.infraestructure.exceptionhandler;


import com.pragma.bootcamp.domain.exception.BootcampHasSubscribersException;
import com.pragma.bootcamp.domain.exception.CapabilityNotFoundException;
import com.pragma.bootcamp.domain.exception.InvalidBootcampException;
import com.pragma.bootcamp.domain.util.ExceptionConstans;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = ExceptionConstans.FIRST_PART_MESSAGE_EXCEPTION;
    private static final String STATUS = ExceptionConstans.STATUS_MESSAGE_EXCEPTION;

    @ExceptionHandler(CapabilityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCapabilityNotFound(CapabilityNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status)
                .body(Map.of(
                        STATUS, status.value(),
                        MESSAGE, ex.getMessage()
                ));
    }

    @ExceptionHandler(InvalidBootcampException.class)
    public ResponseEntity<Map<String, Object>> handleTechnologyValidation(InvalidBootcampException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(Map.of(
                        STATUS, status.value(),
                        MESSAGE, ex.getMessage()
                ));
    }
    @ExceptionHandler(BootcampHasSubscribersException.class)
    public ResponseEntity<Map<String, Object>> handleBootcampHasSubscriber(BootcampHasSubscribersException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(Map.of(
                        STATUS, status.value(),
                        MESSAGE, ex.getMessage()
                ));
    }
}