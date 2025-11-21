package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;



import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.gateway.ServicioGateway;
import com.eventia.booking.infraestructure.mapper.ServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Component
public class ServiceGatewayImpl implements ServicioGateway {

    private final ServiceDataJpaRepository repository;
    private final ServiceMapper mapper;

    @Override
    public List<Servicio> listarServicios() {
        return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override public Servicio obtenerServicioPorId(Long Id_Servicio){
        return repository.findById(id).map(this::toDomain).orElse(null);
    }

    private Servicio toDomain(ServiceData d) {
        return new Servicio();
    }
}