package com.eventia.booking.domain.model.UseCase;

import com.eventia.booking.domain.model.Agenda;
import com.eventia.booking.domain.model.gateway.AgendaGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AgendaUseCase {

    private final com.eventia.booking.domain.model.gateway.AgendaGateway gateway;

    public Agenda crear(Agenda agenda) {
        return gateway.save(agenda);
    }

    public Agenda obtener(Long idAgenda) {
        return gateway.findById(idAgenda);
    }

    public List<Agenda> obtenerPorServicio(Long idServicio) {
        return gateway.findByServicio(idServicio);
    }

    public void eliminar(Long idAgenda) {
        gateway.delete(idAgenda);
    }
}
