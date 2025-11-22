package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.OffsetDateTime;
import java.time.LocalDate;

@Data
public class BookingRequestDTO {

    private Long idServicio;
    private Long idUsuarioCliente;
    private LocalDate fechaReserva;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime fechaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime fechaFin;

    private Double total;
}