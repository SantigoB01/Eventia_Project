package com.eventia.booking.domain.model.UseCase;
import com.eventia.booking.domain.exception.ServicioNotFoundException;
import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.enums.CiudadSumapaz;
import com.eventia.booking.domain.model.enums.TipoServicio;
import com.eventia.booking.domain.model.gateway.ServicioGateway;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import java.util.List;


@RequiredArgsConstructor
public class ServicioUseCase {

    private final ServicioGateway servicioGateway;

    public Servicio crearServicio(Servicio servicio) {

        if (servicio.getTarifaPorHora().compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        return servicioGateway.crearServicio(servicio);
    }

    public Servicio actualizarServicio(Servicio servicio) {

        Servicio actual = servicioGateway.obtenerServicioPorId(servicio.getIdServicio());
        if (actual == null) {
            throw new ServicioNotFoundException("No existe el servicio con id " + servicio.getIdServicio());
        }

        return servicioGateway.actualizarServicio(servicio);
    }

    public void eliminarServicio(Long idServicio) {
        Servicio existente = servicioGateway.obtenerServicioPorId(idServicio);

        if (existente == null) {
            throw new ServicioNotFoundException("No existe el servicio con id " + idServicio);
        }

        servicioGateway.eliminarServicio(idServicio);
    }

    public Servicio obtenerServicioPorId(Long idServicio) {
        Servicio servicio = servicioGateway.obtenerServicioPorId(idServicio);

        if (servicio == null) {
            throw new ServicioNotFoundException("Servicio no encontrado");
        }

        return servicio;
    }

    public List<Servicio> listarServicios() {
        return servicioGateway.listarServicios();
    }

    public List<Servicio> buscarPorCiudad(CiudadSumapaz ciudad) {
        List<Servicio> servicios = servicioGateway.buscarPorCiudad(ciudad);

        if (servicios.isEmpty()) {
            throw new ServicioNotFoundException("No hay servicios registrados en " + ciudad);
        }

        return servicios;
    }

    public List<Servicio> buscarPorTipoServicio(TipoServicio tipo) {
        List<Servicio> servicios = servicioGateway.buscarPorTipoServicio(tipo);

        if (servicios.isEmpty()) {
            throw new ServicioNotFoundException("No hay servicios del tipo " + tipo);
        }

        return servicios;
    }

    public List<Servicio> buscarPorUsuarioOferente(Long idUsuarioOferente) {
        List<Servicio> servicios = servicioGateway.buscarPorOferente(idUsuarioOferente);

        if (servicios.isEmpty()) {
            throw new ServicioNotFoundException("El usuario " + idUsuarioOferente + " no tiene servicios registrados");
        }

        return servicios;
    }
}
