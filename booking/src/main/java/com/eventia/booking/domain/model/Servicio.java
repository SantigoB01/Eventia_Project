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
    private Long Id_Servicio;
    private Long Id_Usuario_Oferente;
    private String Descripcion;
    private Double Costo;
    private String Tipo_Servicio;
    private String Ciudad;
}