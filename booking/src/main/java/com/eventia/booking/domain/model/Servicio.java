package com.eventia.booking.domain.model;

import com.eventia.booking.domain.model.enums.CiudadSumapaz;
import com.eventia.booking.domain.model.enums.TipoServicio;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

public class Servicio {
    private Long idServicio;
    private Long idUsuarioOferente;
    private String nombre;
    private String descripcion;
    private BigDecimal tarifaPorHora;
    private TipoServicio tipo;
    private CiudadSumapaz ciudad;
}
