package com.eventia.booking.domain.model.UseCase;
import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.gateway.ServicioGateway;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.ServiceData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioUseCase {

    private final ServicioGateway servicioGateway;

    public Servicio crearServicio(Servicio servicio) {
        return servicioGateway.crearServicio(servicio);
    }

    public Servicio actualizarServicio(Servicio servicio) {
        return servicioGateway.actualizarServicio(servicio);
    }

    public void eliminarServicio(Long idServicio) {
        servicioGateway.eliminarServicio(idServicio);
    }

    public Servicio obtenerServicioPorId(Long idServicio) {
        return servicioGateway.obtenerServicioPorId(idServicio);
    }

    public List<Servicio> listarServicios(ServiceData serviceData) {
        return servicioGateway.listarServicios();
    }
}
