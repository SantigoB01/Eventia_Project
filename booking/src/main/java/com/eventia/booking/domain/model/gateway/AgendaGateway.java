package com.eventia.booking.domain.model.gateway;

import com.eventia.booking.domain.model.Agenda;
import java.util.List;

public interface AgendaGateway {
    Agenda save(Agenda agenda);
    Agenda findById(Long idAgenda);
    List<Agenda> findByServicio(Long idServicio);
    void delete(Long idAgenda);
}
