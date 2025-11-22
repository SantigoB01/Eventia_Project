package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

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

    @Column(name = "fechAreserva")
    private LocalDate fechaReserva;

    @Column(name="fechainicio")
    private OffsetDateTime fechaInicio;

    @Column(name="fecha_fin")
    private OffsetDateTime fechaFin;

    @Column( length = 120)
    private String estado;

    @Column
    private Double total;

}