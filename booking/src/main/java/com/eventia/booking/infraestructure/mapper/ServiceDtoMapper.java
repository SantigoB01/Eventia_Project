package com.eventia.booking.infraestructure.mapper;

import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request.ServiceRequestDTO;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response.ServiceResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ServiceDtoMapper {

    public Servicio toDomain(ServiceRequestDTO dto) {
        return new Servicio(
                null,
                dto.getIdUsuarioOferente(),
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getTarifaPorHora(),
                dto.getTipo(),
                dto.getCiudad()
        );
    }

    public ServiceResponseDTO toResponse(Servicio servicio) {
        ServiceResponseDTO response = new ServiceResponseDTO();
        response.setIdServicio(servicio.getIdServicio());
        response.setIdUsuarioOferente(servicio.getIdUsuarioOferente());
        response.setDescripcion(servicio.getDescripcion());
        response.setTarifaPorHora(servicio.getTarifaPorHora());
        response.setTipo(servicio.getTipo());
        response.setCiudad(servicio.getCiudad());
        response.setNombre(servicio.getNombre());
        return response;
    }
}
