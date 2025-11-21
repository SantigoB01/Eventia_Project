package com.eventia.booking.domain.model.gateway;

import com.eventia.booking.domain.model.Servicio;

import java.util.List;

public interface ServicioGateway {
    Servicio crearServicio(Servicio servicio);
    Servicio actualizarServicio (Servicio servicio);
    void eliminarServicio (Long Id_Servicio);
    List<Servicio> listarServicios();
    Servicio obtenerServicioPorId(Long Id_Servicio);
}
