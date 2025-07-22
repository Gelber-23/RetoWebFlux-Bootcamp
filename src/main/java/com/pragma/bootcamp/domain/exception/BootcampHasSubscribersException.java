package com.pragma.bootcamp.domain.exception;

public class BootcampHasSubscribersException extends RuntimeException {

    public BootcampHasSubscribersException(String message) {
        super(message);
    }

}