package com.eventia.booking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

public class Servicio {
    private Long idServicio;
    private Long idUsuarioOferente;
    private String descripcion;
    private Double costo;
    private String tipoServicio;
    private String ciudad;
}