package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response;


import com.eventia.booking.domain.model.enums.CiudadSumapaz;
import com.eventia.booking.domain.model.enums.TipoServicio;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data

@Getter @Setter
public class ServiceResponseDTO {
    private Long idServicio;
    private Long idUsuarioOferente;
    private String nombre;
    private String descripcion;
    private BigDecimal tarifaPorHora;
    private TipoServicio tipo;
    private CiudadSumapaz ciudad;
}