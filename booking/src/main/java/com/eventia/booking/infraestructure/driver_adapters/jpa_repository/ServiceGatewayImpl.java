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
    public Servicio crearServicio(Servicio servicio) {
        return mapper.toServicio(repository.save(mapper.toData(servicio)));
    }

    @Override
    public Servicio actualizarServicio(Servicio servicio) {
        return mapper.toServicio(repository.save(mapper.toData(servicio)));
    }

    @Override
    public void eliminarServicio(Long idServicio) {
        repository.deleteById(idServicio);
    }

    @Override
    public List<Servicio> listarServicios() {
        return repository.findAll()
                .stream()
                .map(mapper::toServicio)
                .toList();
    }

    @Override
    public Servicio obtenerServicioPorId(Long idServicio) {
        return repository.findById(idServicio)
                .map(mapper::toServicio)
                .orElse(null);
    }

    @Override
    public List<Servicio> buscarPorCiudad(CiudadSumapaz ciudad) {
        return repository.findByCiudad(ciudad)
                .stream()
                .map(mapper::toServicio)
                .toList();
    }

    @Override
    public List<Servicio> buscarPorTipoServicio(TipoServicio tipo) {
        return repository.findByTipo(tipo)
                .stream()
                .map(mapper::toServicio)
                .toList();
    }

    @Override
    public List<Servicio> buscarPorOferente(Long idUsuarioOferente) {
        return repository.findByIdUsuarioOferente(idUsuarioOferente)
                .stream()
                .map(mapper::toServicio)
                .toList();
    }
}
