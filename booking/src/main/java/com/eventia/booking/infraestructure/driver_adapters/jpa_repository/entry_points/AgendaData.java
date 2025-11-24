package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agenda")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agenda")
    private Long idAgenda;

    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "fecha_reserva")
    private String fechaReserva;

    @Column(name = "disponibilidad")
    private Boolean disponibilidad;

    @Column(name = "hora_inicio")
    private String horaInicio;
}
