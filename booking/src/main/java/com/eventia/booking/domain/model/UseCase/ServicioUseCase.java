package com.eventia.booking.domain.model.UseCase;
import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.gateway.ServicioGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ServicioUseCase {

    private final ServicioGateway servicioGateway;

    public Servicio crearServicio(Servicio servicio) {
        return servicioGateway.crearServicio(servicio);
    }

    public Servicio actualizarServicio(Servicio servicio) {
        return servicioGateway.actualizarServicio(servicio);
    }

    public void eliminarServicio(Long Id_Servicio) {
        servicioGateway.eliminarServicio(Id_Servicio);
    }

    public Servicio obtenerServicioPorId(Long Id_Servicio) {
        return servicioGateway.obtenerServicioPorId(Id_Servicio);
    }

    public List<Servicio> listarServicios() {
        return servicioGateway.listarServicios();
    }
}
