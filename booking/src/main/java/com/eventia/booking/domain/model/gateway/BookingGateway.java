package com.eventia.booking.domain.model.gateway;

import com.eventia.booking.domain.model.Booking;

import java.util.List;


public interface BookingGateway {
        Booking crearReserva(Booking reserva);
        Booking obtenerReservaPorId(Long IdReserva);
        List<Booking> listarReservasPorCliente(Long IdCliente);
        Booking actualizarReserva(Booking reserva);
        void eliminarReserva(Long IdReserva);
        List<Booking> obtenerReservasPorServicio(Long IdServicio);
}
