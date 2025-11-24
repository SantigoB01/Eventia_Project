package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;

import com.eventia.booking.domain.model.enums.CiudadSumapaz;
import com.eventia.booking.domain.model.enums.TipoServicio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


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

    @Column (nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 600)
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal tarifaPorHora;

    @Column(name = "tipo_servicio", nullable = false, length = 120)
    private TipoServicio tipo;

    @Column(nullable = false, length = 120)
    private CiudadSumapaz ciudad;
}
