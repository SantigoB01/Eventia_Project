package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.handler;

import com.eventia.booking.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> buildBody(String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return body;
    }

    @ExceptionHandler(ReservaNoEncontradaException.class)
    public ResponseEntity<?> handleReservaNoEncontrada(ReservaNoEncontradaException ex) {
        return new ResponseEntity<>(buildBody(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServicioNoDisponibleException.class)
    public ResponseEntity<?> handleServicioNoDisponible(ServicioNoDisponibleException ex) {
        return new ResponseEntity<>(buildBody(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FechaNoDisponibleException.class)
    public ResponseEntity<?> handleFechaNoDisponible(FechaNoDisponibleException ex) {
        return new ResponseEntity<>(buildBody(ex.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleCamposObligatorios(CamposObligatoriosException ex) {
        return new ResponseEntity<>(buildBody(ex.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        return new ResponseEntity<>(buildBody(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}