package com.eventia.booking.infraestructure.mapper;

import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.ServiceData;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

        public Servicio toServicio(ServiceData ServicioData){
            return new Servicio(
                    ServicioData.getId_Servicio(),
                    ServicioData.getId_Usuario_Oferente(),
                    ServicioData.getDescripcion(),
                    ServicioData.getCosto(),
                    ServicioData.getTipo_Servicio(),
                    ServicioData.getCiudad()
            );
        }

        public ServiceData toData(Servicio servicio){
            return new ServiceData(
                    servicio.getId_Servicio(),
                    servicio.getId_Usuario_Oferente(),
                    servicio.getDescripcion(),
                    servicio.getCosto(),
                    servicio.getTipo_Servicio(),
                    servicio.getCiudad());
        }
}


