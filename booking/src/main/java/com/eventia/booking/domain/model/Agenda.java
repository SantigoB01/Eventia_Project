package com.eventia.booking.domain.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {
    private Long idAgenda;
    private Long idServicio;
    private String fechaReserva;
    private Boolean disponibilidad;
    private String horaInicio;
}
