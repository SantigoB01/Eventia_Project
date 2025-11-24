package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgendaResponseDTO {
    private Long idAgenda;
    private Long idServicio;
    private String fechaReserva;
    private Boolean disponibilidad;
    private String horaInicio;
}
