package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request;

import lombok.Data;

@Data
public class AgendaRequestDTO {
    private Long idServicio;
    private String fechaReserva;
    private Boolean disponibilidad;
    private String horaInicio;
}
