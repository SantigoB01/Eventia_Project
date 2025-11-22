package com.eventia.booking.domain.exception;

public class FechaNoDisponibleException extends RuntimeException {
    public FechaNoDisponibleException() {
        super("La fecha seleccionada no está disponible para reserva.");
    }
}