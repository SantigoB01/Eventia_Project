package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "idservicio", nullable = false)
    private Long idServicio;

    @Column(name = "idusuariocliente", nullable = false)
    private Long idUsuarioCliente;

    @Column(name = "fechAreserva", nullable = false)
    private LocalDate fechaReserva;

    @Column(name="fechainicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name="fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    @Column(nullable = false, length = 120)
    private String estado;

    @Column(nullable = false)
    private Double total;

}