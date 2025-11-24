package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.LocalDate;

@Getter@Setter
@Data
public class BookingRequestDTO {

        private Long idServicio;
        private Long idUsuarioCliente;
        private LocalDate fechaReserva;
        private LocalTime fechaInicio;
        private LocalTime fechaFin;
        private BigDecimal costoCalculado;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        private OffsetDateTime fechaCreacion;
}