package com.eventia.booking.infraestructure.mapper;

import com.eventia.booking.domain.model.Agenda;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.AgendaData;

public class AgendaMapper {

    public static AgendaData toData(Agenda agenda) {
        return AgendaData.builder()
                .idAgenda(agenda.getIdAgenda())
                .idServicio(agenda.getIdServicio())
                .fechaReserva(agenda.getFechaReserva())
                .disponibilidad(agenda.getDisponibilidad())
                .horaInicio(agenda.getHoraInicio())
                .build();
    }

    public static Agenda toDomain(AgendaData data) {
        return Agenda.builder()
                .idAgenda(data.getIdAgenda())
                .idServicio(data.getIdServicio())
                .fechaReserva(data.getFechaReserva())
                .disponibilidad(data.getDisponibilidad())
                .horaInicio(data.getHoraInicio())
                .build();
    }
}
