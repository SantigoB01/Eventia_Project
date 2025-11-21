package com.eventia.booking.domain.model.UseCase;

import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.gateway.BookingGateway;
import com.eventia.booking.domain.model.gateway.ServicioGateway;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor

public class BookingUseCase {
    private final BookingGateway BookingGateway;
    private final ServicioGateway servicioGateway;

        public Booking crearReserva(Booking reserva) {

            validarFechas(reserva);
            validarExistenciaServicio(reserva.getId_Servicio());

            if (!estaDisponible(reserva.getId_Servicio(), reserva.getFechaInicio(), reserva.getFechaFin())) {
                throw new IllegalArgumentException("El servicio NO está disponible en este horario.");
            }

            return BookingGateway.crearReserva(reserva);
        }

        public Booking actualizarReserva(Booking reserva) {

            validarFechas(reserva);
            validarExistenciaServicio(reserva.getId_Servicio());

            if (!estaDisponibleParaActualizacion(reserva)) {
                throw new IllegalArgumentException("La reserva se cruza con otra existente.");
            }

            return BookingGateway.actualizarReserva(reserva);
        }

        public void eliminarReserva(Long id_reserva) {
            BookingGateway.eliminarReserva(id_reserva);
        }

        public Booking obtenerReservaPorId(Long Id_Reserva) {
            return BookingGateway.obtenerReservaPorId(Id_Reserva);
        }

        public List<Booking> listarReservas(Long Id_Usuario_Cliente) {
            return BookingGateway.listarReservasPorCliente(Id_Usuario_Cliente);
        }

        // ============================================================
        // VALIDACIONES
        // ============================================================

        private void validarFechas(Booking reserva) {
            if (reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
                throw new IllegalArgumentException("La fecha de inicio no puede ser mayor a la fecha fin.");
            }

            if (reserva.getFechaInicio().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("No se pueden crear reservas en el pasado.");
            }
        }

        private void validarExistenciaServicio(Long Id_Servicio) {
            Servicio servicio = servicioGateway.obtenerServicioPorId(Id_Servicio);
            if (servicio==null) {
                throw new IllegalArgumentException("El servicio solicitado no existe.");
            }
        }

        // ============================================================
        // DISPONIBILIDAD
        // ============================================================

        public boolean estaDisponible(Long Id_Servicio, LocalDateTime inicio, LocalDateTime fin) {
            return BookingGateway.obtenerReservasPorServicio(Id_Servicio).stream()
                    .noneMatch(r ->
                            inicio.isBefore(r.getFechaFin()) &&
                                    fin.isAfter(r.getFechaInicio())
                    );
        }

        private boolean estaDisponibleParaActualizacion(Booking reserva) {
            return BookingGateway.obtenerReservasPorServicio(reserva.getId_Servicio()).stream()
                    .filter(e -> !e.getId_Reserva().equals(reserva.getId_Reserva())).isParallel();
        }
    }
