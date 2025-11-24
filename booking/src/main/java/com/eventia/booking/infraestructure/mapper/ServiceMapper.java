package com.eventia.booking.infraestructure.mapper;

import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.ServiceData;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

        public Servicio toServicio(ServiceData ServicioData){
            return new Servicio(
                    ServicioData.getIdServicio(),
                    ServicioData.getIdUsuarioOferente(),
                    ServicioData.getNombre(),
                    ServicioData.getDescripcion(),
                    ServicioData.getTarifaPorHora(),
                    ServicioData.getTipo(),
                    ServicioData.getCiudad()
            );
        }

        public ServiceData toData(Servicio servicio){
            return new ServiceData(
                    servicio.getIdServicio(),
                    servicio.getIdUsuarioOferente(),
                    servicio.getNombre(),
                    servicio.getDescripcion(),
                    servicio.getTarifaPorHora(),
                    servicio.getTipo(),
                    servicio.getCiudad());
        }
}


