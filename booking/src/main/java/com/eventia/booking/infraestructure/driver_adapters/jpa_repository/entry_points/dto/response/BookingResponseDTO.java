package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter@Setter

@Data
public class BookingResponseDTO {

    private Long idReserva;
    private Long idServicio;
    private Long idUsuarioCliente;
    private LocalDate fechaReserva;
    private LocalTime fechaInicio;
    private LocalTime fechaFin;
    private String estado;
    private BigDecimal costoCalculado;
    private Instant fechaCreacion;
}