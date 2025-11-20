package com.eventia.booking.domain.model.UseCase;

import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.gateway.BookingGateway;
import com.eventia.booking.domain.model.gateway.ServicioGateway;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@RequiredArgsConstructor

public class BookingUseCase {
    private final BookingGateway BookingGateway;
    private final ServicioGateway servicioGateway;

/// Creacion de reserva///
    public CrearReservaUseCase(BookingGateway reservaGateway, ServicioGateway servicioGateway) {
        this.BookingGateway = reservaGateway;
        this.servicioGateway = servicioGateway;
    }

    public Booking ejecutar(Integer Id_Servicio, Integer Id_Cliente, LocalDate fecha, Integer horas) {
        Servicio servicio = servicioGateway.obtenerServicioPorId(Id_Servicio);
        if (servicio == null) throw new RuntimeException("Servicio no encontrado");
        if (fecha.isBefore(LocalDate.now())) throw new RuntimeException("Fecha no válida");
        Integer total = (int) Math.round(servicio.getCosto() * horas);
        Booking reserva = new Booking();
        return BookingGateway.crearReserva(reserva);
    }
}

