package com.eventia.booking.domain.model.gateway;

import com.eventia.booking.domain.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingGateway {

    Booking crearReserva(Booking booking);

    Booking obtenerReservaPorId(Long id);

    List<Booking> listarReservas();

    List<Booking> listarReservasPorUsuario(Long idUsuarioCliente);

    List<Booking> listarReservasPorServicio(Long idServicio);

    List<Booking> listarReservasActivas();

    Booking actualizarReserva(Booking booking);

    void eliminarReserva(Long id);

    List<Booking> listarReservasPorFecha(LocalDate fecha);
}