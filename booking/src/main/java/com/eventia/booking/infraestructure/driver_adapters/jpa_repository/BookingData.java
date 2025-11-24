package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reserva")
public class BookingData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreserva")
    private Long idReserva;

    @Column(name = "idservicio")
    private Long idServicio;

    @Column(name = "idusuariocliente")
    private Long idUsuarioCliente;

    @Column(name = "fechareserva")
    private LocalDate fechaReserva;

    @Column(name="fechainicio")
    private LocalTime fechaInicio;

    @Column(name="fechafin")
    private LocalTime fechaFin;

    @Column( length = 120)
    private String estado;

    @Column
    private BigDecimal costoCalculado;

    @Column
    private LocalDateTime fechaCreacion;
}