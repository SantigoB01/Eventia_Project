package com.eventia.booking.domain.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

public class Booking {

    private Long idReserva;
    private long idServicio;
    private Long idUsuarioCliente;
    private LocalDate fechaReserva;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;
    private BigDecimal CostoCalculado;
    private Instant fechaCreacion;
}