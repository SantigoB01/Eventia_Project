package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "reserva")
public class BookingData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva") private Integer Id_Reserva;
    @Column(name = "id_servicio", nullable = false) private Integer Id_Servicio;
    @Column(name = "id_usuario_cliente", nullable = false) private Integer Id_Usuario_Cliente;
    @Column(name = "fecha_reserva", nullable = false) private LocalDate Fecha_Reserva;
    @Column(nullable = false, length = 120) private String Estado;
    @Column(nullable = false) private Integer Total;
// getters/setters
}