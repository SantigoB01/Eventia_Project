package com.eventia.booking.domain.model.gateway;

import com.eventia.booking.domain.model.Booking;

import java.util.List;


public interface BookingGateway {
        Booking crearReserva(Booking reserva);
        Booking obtenerReservaPorId(Long Id_Reserva);
        List<Booking> listarReservasPorCliente(Long Id_Cliente);
        Booking actualizarReserva(Booking reserva);

}
