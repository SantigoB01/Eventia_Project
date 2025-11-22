package com.eventia.booking.domain.model.UseCase;


import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.domain.model.gateway.BookingGateway;
import com.eventia.booking.domain.model.gateway.ServicioGateway;
import com.eventia.booking.domain.exception.FechaNoDisponibleException;
import com.eventia.booking.domain.exception.ReservaNoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingUseCase {

    private final BookingGateway bookingGateway;
    private final ServicioGateway servicioGateway;

    public Booking crearReserva(Booking booking) {

        if (booking.getFechaReserva().isBefore(LocalDate.now())) {
            throw new FechaNoDisponibleException("La fecha de reserva no puede ser anterior a hoy.");
        }

        var servicio = servicioGateway.obtenerServicioPorId(booking.getIdServicio());
        if (servicio == null) {
            throw new RuntimeException("El servicio asociado no existe.");
        }

        // Validar solapamiento de fechas (conflicto de horario)
        boolean conflicto = bookingGateway.listarReservasPorServicio(booking.getIdServicio())
                .stream()
                .anyMatch(b ->
                        !(booking.getFechaFin().isBefore(b.getFechaInicio())
                                || booking.getFechaInicio().isAfter(b.getFechaFin()))
                );

        if (conflicto) {
            throw new FechaNoDisponibleException("El servicio ya está reservado en ese horario.");
        }

        booking.setEstado("ACTIVO");
        booking.setFechaCreacion(java.time.LocalDateTime.now());

        if (booking.getTotal() == null) {
            booking.setTotal(servicio.getCosto());
        }

        return bookingGateway.crearReserva(booking);
    }

    public Booking obtenerReservaPorId(Long id) {
        var reserva = bookingGateway.obtenerReservaPorId(id);
        if (reserva == null) throw new ReservaNoEncontradaException(id);
        return reserva;
    }

    public List<Booking> listarReservas(Long idUsuarioCliente) {
        return bookingGateway.listarReservas();
    }

    public List<Booking> listarReservasPorUsuario(Long idUsuario) {
        return bookingGateway.listarReservasPorUsuario(idUsuario);
    }

    public List<Booking> listarReservasActivas() {
        return bookingGateway.listarReservasActivas();
    }

    public List<Booking> listarReservasPorServicio(Long idServicio) {
        return bookingGateway.listarReservasPorServicio(idServicio);
    }

    public List<Booking> listarReservasPorFecha(LocalDate fecha) {
        return bookingGateway.listarReservasPorFecha(fecha);
    }

    public Booking cancelarReserva(Long idReserva) {
        var reserva = obtenerReservaPorId(idReserva);
        reserva.setEstado("CANCELADA");
        return bookingGateway.actualizarReserva(reserva);
    }

    public void eliminarReserva(Long id) {
        obtenerReservaPorId(id);
        bookingGateway.eliminarReserva(id);
    }
}