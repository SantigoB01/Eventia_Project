package com.eventia.booking.domain.exception;

import com.eventia.booking.domain.model.Servicio;


public class ServicioNotFoundException extends RuntimeException  {
    public ServicioNotFoundException(String message) {
        super(message);
    }
}
