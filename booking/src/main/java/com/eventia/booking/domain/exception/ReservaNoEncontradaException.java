package com.eventia.booking.domain.exception;

public class ReservaNoEncontradaException extends RuntimeException {
    public ReservaNoEncontradaException(Long id) {
        super("La reserva con id " + id + " no fue encontrada.");
    }
}