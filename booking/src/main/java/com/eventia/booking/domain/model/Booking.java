package com.eventia.booking.domain.model;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

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