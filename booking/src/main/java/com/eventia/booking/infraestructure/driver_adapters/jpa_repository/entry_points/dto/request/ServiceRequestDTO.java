package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request;


import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class ServiceRequestDTO {
    private Long idUsuarioOferente;
    private String descripcion;
    private Double costo;
    private String tipo;
    private String ciudad;
}