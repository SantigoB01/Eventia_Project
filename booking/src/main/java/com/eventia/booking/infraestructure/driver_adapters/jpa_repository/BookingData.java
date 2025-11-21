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
    @Column(name = "id_reserva")
    private Long Id_Reserva;

    @Column(name = "id_servicio", nullable = false)
    private Long Id_Servicio;

    @Column(name = "id_usuario_cliente", nullable = false)
    private Long Id_Usuario_Cliente;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate Fecha_Reserva;

    @Column(name="fecha_inicio", nullable = false)
    private LocalDateTime FechaInicio;

    @Column(name="fecha_fin", nullable = false)
    private LocalDateTime FechaFin;

    @Column(nullable = false, length = 120)
    private String Estado;

    @Column(nullable = false)
    private Integer Total;

}