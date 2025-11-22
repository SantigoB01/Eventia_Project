package com.eventia.booking.domain.model.UseCase;

import com.eventia.booking.domain.exception.FechaNoDisponibleException;
import com.eventia.booking.domain.exception.ServicioNoDisponibleException;
import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.gateway.BookingGateway;
import com.eventia.booking.domain.model.gateway.ServicioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class BookingUseCase {
    private final BookingGateway BookingGateway;
    private final ServicioGateway servicioGateway;

        public Booking crearReserva(Booking reserva) {

            validarFechass(reserva);
            validarExistenciaServicio(reserva.getIdServicio());

            if (!estaDisponible(reserva.getIdServicio(), reserva.getFechaInicio(), reserva.getFechaFin())) {
                throw new ServicioNoDisponibleException(reserva.getIdServicio());
            }

            return BookingGateway.crearReserva(reserva);
        }

        public Booking actualizarReserva(Booking reserva) {

            validarFechas(reserva);
            validarExistenciaServicio(reserva.getIdServicio());

            if (!estaDisponibleParaActualizacion(reserva)) {
                throw new FechaNoDisponibleException();
            }

            return BookingGateway.actualizarReserva(reserva);
        }

        public void eliminarReserva(Long IdReserva) {
            BookingGateway.eliminarReserva(IdReserva);
        }

        public Booking obtenerReservaPorId(Long IdReserva) {
            return BookingGateway.obtenerReservaPorId(IdReserva);
        }

        public List<Booking> listarReservas(Long IdUsuarioCliente) {
            return BookingGateway.listarReservasPorCliente(IdUsuarioCliente);
        }

        private void validarFechass(Booking booking) {

            if (booking.getFechaInicio() == null || booking.getFechaFin() == null) {
                throw new IllegalArgumentException("Las fechas inicio y fin son obligatorias");
            }

            if (booking.getFechaFin().isBefore(booking.getFechaInicio())) {
                throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
            }
        }

        private void validarFechas(Booking reserva) {
            if (reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
                throw new IllegalArgumentException("La fecha de inicio no puede ser mayor a la fecha fin.");
            }

            if (reserva.getFechaInicio().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("No se pueden crear reservas en el pasado.");
            }
        }

        private void validarExistenciaServicio(Long IdServicio) {
            Servicio servicio = servicioGateway.obtenerServicioPorId(IdServicio);
            if (servicio==null) {
                throw new IllegalArgumentException("El servicio solicitado no existe.");
            }
        }


        public boolean estaDisponible(Long IdServicio, LocalDateTime inicio, LocalDateTime fin) {
            return BookingGateway.obtenerReservasPorServicio(IdServicio).stream()
                    .noneMatch(r ->
                            inicio.isBefore(r.getFechaFin()) &&
                                    fin.isAfter(r.getFechaInicio())
                    );
        }

        private boolean estaDisponibleParaActualizacion(Booking reserva) {
            return BookingGateway.obtenerReservasPorServicio(reserva.getIdServicio()).stream()
                    .filter(e -> !e.getIdReserva().equals(reserva.getIdReserva())).isParallel();
        }
    }
