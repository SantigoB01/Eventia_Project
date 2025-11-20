package com.eventia.booking.domain.model.gateway;

import com.eventia.booking.domain.model.Servicio;

import java.util.List;

public interface ServicioGateway {
    List<Servicio> listarServicios();
    Servicio obtenerServicioPorId(Integer Id_Servicio);
}
