package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;



import com.eventia.booking.domain.exception.ServicioNoDisponibleException;
import com.eventia.booking.domain.exception.ServicioNotFoundException;
import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.gateway.ServicioGateway;
import com.eventia.booking.infraestructure.mapper.ServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Component
public class ServiceGatewayImpl implements ServicioGateway {

    private final ServiceDataJpaRepository repository;
    private final ServiceMapper mapper;

    @Override
    public Servicio crearServicio(Servicio servicio){
       ServiceData serviceData = mapper.toData(servicio);
        return mapper.toServicio(repository.save(serviceData));
    }

    @Override
    public Servicio actualizarServicio(Servicio servicio){
        ServiceData serviceDataAct = mapper.toData(servicio);

        if (!repository.existsById(serviceDataAct.getIdServicio())){
            throw new RuntimeException("Servicio con id: " + serviceDataAct.getIdServicio() + " no existe");
        }
        return mapper.toServicio(repository.save(serviceDataAct));
    }

    @Override
    public void eliminarServicio(Long IdServicio){
        if (repository.existsById(IdServicio)){
            repository.deleteById(IdServicio);
        } else {
            System.out.println("Servicio no existente. ID SERVICE NOT FOUND");
        }
    }

    @Override
    public List<Servicio> listarServicios() {
        return repository.findAll().stream().map(mapper::toServicio).collect(Collectors.toList());
    }

    @Override public Servicio obtenerServicioPorId(Long IdServicio){
        return repository.findById(IdServicio).map(mapper::toServicio).orElse(null);
    }

    @Override
    public List<Servicio> buscarPorCiudad(String ciudad){
        try {
            return repository.findByCiudad(ciudad).stream().toList();
        } catch (Exception e) {
            throw new ServicioNotFoundException(ciudad);
        }
    }

    @Override
    public List<Servicio> buscarPorTipoServicio(String tipo){
        try {
            return repository.findByTipo(tipo).stream().toList();
        } catch (Exception e) {
            throw new ServicioNotFoundException(tipo);
        }
    }
 }