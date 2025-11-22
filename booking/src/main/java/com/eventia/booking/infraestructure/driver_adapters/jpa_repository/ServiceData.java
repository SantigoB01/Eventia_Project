package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "servicio")
public class ServiceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "id_usuario_of", nullable = false)
    private Long idUsuarioOferente;

    @Column(nullable = false, length = 600)
    private String descripcion;

    @Column(nullable = false)
    private Double costo;

    @Column(name = "tipo_servicio", nullable = false, length = 120)
    private String tipo;

    @Column(nullable = false, length = 120)
    private String ciudad;
}
