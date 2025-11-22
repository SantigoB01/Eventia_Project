package com.eventia.booking.domain.exception;

public class ServicioNoDisponibleException extends RuntimeException {
    public ServicioNoDisponibleException(Long idServicio) {
        super("El servicio con id " + idServicio + " no está disponible.");
    }
}