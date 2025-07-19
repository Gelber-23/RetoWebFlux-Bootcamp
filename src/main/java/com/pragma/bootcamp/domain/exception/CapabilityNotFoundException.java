package com.pragma.bootcamp.domain.exception;

public class CapabilityNotFoundException extends RuntimeException {

    public CapabilityNotFoundException(String message) {
        super(message);
    }

}