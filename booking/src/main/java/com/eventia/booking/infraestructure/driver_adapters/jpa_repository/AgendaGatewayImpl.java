package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;

import com.eventia.booking.domain.model.gateway.AgendaGateway;
import com.eventia.booking.domain.model.Agenda;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.AgendaData;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.AgendaDataJpaRepository;
import com.eventia.booking.infraestructure.mapper.AgendaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AgendaGatewayImpl implements AgendaGateway {

    private final AgendaDataJpaRepository repository;

    @Override
    public Agenda save(Agenda agenda) {
        AgendaData saved = repository.save(AgendaMapper.toData(agenda));
        return AgendaMapper.toDomain(saved);
    }

    @Override
    public Agenda findById(Long idAgenda) {
        return repository.findById(idAgenda)
                .map(AgendaMapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<Agenda> findByServicio(Long idServicio) {
        return repository.findByIdServicio(idServicio)
                .stream()
                .map(AgendaMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Long idAgenda) {
        repository.deleteById(idAgenda);
    }
}
