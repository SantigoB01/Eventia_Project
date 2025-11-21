package com.eventia.booking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

public class Booking {
    private Long id_Reserva;
    private Long Id_Servicio;
    private Long Id_Usuario_Cliente;
    private LocalDate Fecha_Reserva;
    private LocalDateTime FechaInicio;
    private LocalDateTime FechaFin;
    private String Estado;
    private Double Total;
}
