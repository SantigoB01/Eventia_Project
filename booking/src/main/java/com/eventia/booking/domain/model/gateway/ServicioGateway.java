package com.eventia.booking.domain.model.gateway;

import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.enums.CiudadSumapaz;
import com.eventia.booking.domain.model.enums.TipoServicio;

import java.util.List;

public interface ServicioGateway {
    Servicio crearServicio(Servicio servicio);
    Servicio actualizarServicio (Servicio servicio);
    void eliminarServicio (Long IdServicio);
    List<Servicio> listarServicios();
    Servicio obtenerServicioPorId(Long IdServicio);

    List<Servicio> buscarPorCiudad(CiudadSumapaz ciudad);

    List<Servicio> buscarPorTipoServicio(TipoServicio tipo);

    List<Servicio> buscarPorOferente(Long idUsuarioOferente);
}
