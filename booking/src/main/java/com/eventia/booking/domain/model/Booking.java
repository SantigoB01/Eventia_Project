package com.eventia.booking.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    private Long idReserva;
    private Long idServicio;
    private Long idUsuarioCliente;
    private LocalDate fechaReserva;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado; // ACTIVO, CANCELADA, COMPLETADA
    private Double total;
    private LocalDateTime fechaCreacion;
}