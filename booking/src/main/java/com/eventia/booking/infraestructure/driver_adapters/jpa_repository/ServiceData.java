package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;

import jakarta.persistence.*;

@Entity
@Table(name = "servicio")
public class ServiceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer idServicio;

    @Column(name = "id_usuario_of", nullable = false)
    private Integer idUsuarioOferente;

    @Column(nullable = false, length = 600)
    private String descripcion;

    @Column(nullable = false)
    private Double costo;

    @Column(name = "tipo_servicio", nullable = false, length = 120)
    private String tipoServicio;

    @Column(nullable = false, length = 120)
    private String ciudad;
}
