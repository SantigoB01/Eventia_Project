package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;
@Builder
@Getter@Setter

@Data
public class BookingResponseDTO {

    private Long idReserva;
    private Long idServicio;
    private Long idUsuarioCliente;

    private LocalDate fechaReserva;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    private String estado;
    private Double total;
    private LocalDateTime fechaCreacion;
}