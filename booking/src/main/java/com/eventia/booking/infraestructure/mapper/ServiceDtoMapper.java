package com.eventia.booking.infraestructure.mapper;

import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request.ServiceRequestDTO;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response.ServiceResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ServiceDtoMapper {

    public Servicio toDomain(ServiceRequestDTO dto) {
        return new Servicio(
                null,                       // idServicio se genera en la BD
                dto.getIdUsuarioOferente(),
                dto.getDescripcion(),
                dto.getCosto(),
                dto.getTipoServicio(),
                dto.getCiudad()
        );
    }

    public ServiceResponseDTO toResponse(Servicio servicio) {
        ServiceResponseDTO response = new ServiceResponseDTO();
        response.setIdServicio(servicio.getIdServicio());
        response.setIdUsuarioOferente(servicio.getIdUsuarioOferente());
        response.setDescripcion(servicio.getDescripcion());
        response.setCosto(servicio.getCosto());
        response.setTipoServicio(servicio.getTipoServicio());
        response.setCiudad(servicio.getCiudad());
        return response;
    }
}
