package com.eventia.review.domain.exceptions;

public class ReservaNoexisteException extends RuntimeException {
    public ReservaNoexisteException(String message) {
        super(message);
    }
}
