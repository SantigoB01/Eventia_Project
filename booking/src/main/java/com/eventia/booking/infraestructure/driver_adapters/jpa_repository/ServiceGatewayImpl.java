package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;



import com.eventia.booking.domain.exception.ServicioNotFoundException;
import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.enums.CiudadSumapaz;
import com.eventia.booking.domain.model.enums.TipoServicio;
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
    public Servicio crearServicio(Servicio servicio){
       ServiceData serviceData = mapper.toData(servicio);
        return mapper.toServicio(repository.save(serviceData));
    }

    @Override
    public Servicio actualizarServicio(Servicio servicio){
        ServiceData serviceDataAct = mapper.toData(servicio);

        if (!repository.existsById(serviceDataAct.getIdServicio())){
            throw new ServicioNotFoundException("Servicio con id: " + serviceDataAct.getIdServicio() + " no existe");
        }
        return mapper.toServicio(repository.save(serviceDataAct));
    }

    @Override
    public void eliminarServicio(Long IdServicio){
        if (repository.existsById(IdServicio)){
            repository.deleteById(IdServicio);
        } else {
            throw new ServicioNotFoundException("SERVICE NOT FOUND. Servicio no encontrado");
        }
    }

    @Override
    public List<Servicio> listarServicios() {
        try{
            return repository.findAll().stream().map(mapper::toServicio).collect(Collectors.toList());
        } catch (Exception e){
           throw new ServicioNotFoundException("No hay ningún servicio registrado pa.");
        }
    }

    @Override public Servicio obtenerServicioPorId(Long IdServicio) {
        try {
            return repository.findById(IdServicio).map(mapper::toServicio).orElse(null);
        } catch (Exception e) {
            throw new ServicioNotFoundException("Servicio no encontrado con id: "+IdServicio);
        }
    }

    @Override
    public List<Servicio> buscarPorCiudad(CiudadSumapaz ciudad){
        try {
            return repository.findByCiudad(ciudad).stream().toList();
        } catch (Exception e) {
            throw new ServicioNotFoundException("En "+ciudad+" no se han registrado servicios");
        }
    }


    @Override
    public List<Servicio> buscarPorTipoServicio(TipoServicio tipo){
        try {
            return repository.findByTipo(tipo).stream().toList();
        } catch (Exception e) {
            throw new ServicioNotFoundException("Ningún servicio por tipo '"+tipo+"' encontrado");
        }
    }

    @Override
    public List<Servicio> buscarPorOferente(Long idUsuarioOferente){
        try {
            return repository.findByIdUsuarioOferente(idUsuarioOferente).stream().toList();
        } catch (Exception e) {
            throw new ServicioNotFoundException("Ningún servicio se registra con el usuario: "+idUsuarioOferente.toString());
        }
    }

 }