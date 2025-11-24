package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;

import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request.BookingRequestDTO;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response.BookingResponseDTO;
import com.eventia.booking.infraestructure.mapper.BookingDtoMapper;
import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.domain.model.UseCase.BookingUseCase;
import com.eventia.booking.domain.exception.ReservaNoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingUseCase bookingUseCase;
    private final BookingDtoMapper bookingMapper;
    private final BookingDtoMapper bookingDtoMapper;

    @PostMapping("/crearReserva")
    public ResponseEntity<BookingResponseDTO> crearReserva(@RequestBody BookingRequestDTO request) {

        if (request == null) {
            throw new IllegalArgumentException("El cuerpo de la solicitud no puede ser nulo.");
        }

        if (request.getIdUsuarioCliente() == null || request.getIdUsuarioCliente() <= 0) {
            throw new IllegalArgumentException("El id del usuario cliente es obligatorio y debe ser válido.");
        }
        if (request.getIdServicio() == null || request.getIdServicio() <= 0) {
            throw new IllegalArgumentException("El id del servicio es obligatorio y debe ser válido.");
        }

        Booking booking = bookingMapper.toDomain(request);
        Booking creada = bookingUseCase.crearReserva(booking);

        if (creada == null) {
            throw new IllegalStateException("La reserva no pudo ser creada.");
        }

        return ResponseEntity.ok(bookingMapper.toResponse(creada));
    }

    @GetMapping("/reserva/{id}")
    public ResponseEntity<BookingResponseDTO> obtenerPorId(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de la reserva debe ser válido.");
        }

        Booking booking = bookingUseCase.obtenerReservaPorId(id);

        if (booking == null) {
            throw new ReservaNoEncontradaException("Reserva inexistente.");
        }

        return ResponseEntity.ok(bookingMapper.toResponse(booking));
    }


    @GetMapping("/verTodasReservas/{idUsuarioCliente}")
    public ResponseEntity<List<BookingResponseDTO>> listarReservasPorUsuario(@PathVariable Long idUsuarioCliente) {

        if (idUsuarioCliente == null || idUsuarioCliente <= 0) {
            throw new IllegalArgumentException("El id del usuario cliente debe ser válido.");
        }

        List<Booking> bookings = bookingUseCase.listarReservasPorUsuario(idUsuarioCliente);

        if (bookings == null || bookings.isEmpty()) {
            throw new ReservaNoEncontradaException("No existen reservas para este usuario.");
        }

        List<BookingResponseDTO> response = bookings.stream()
                .map(bookingDtoMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/verTodasReservas/{fecha}")
    public ResponseEntity<List<BookingResponseDTO>> listarReservasPorFecha(@PathVariable LocalDate fecha) {

        List<Booking> bookings = bookingUseCase.listarReservasPorFecha(fecha);

        if (fecha == null) {
            throw new IllegalArgumentException("Fecha invalida.");
        }


        if (bookings == null || bookings.isEmpty()) {
            throw new ReservaNoEncontradaException("No existen reservas por esta fecha.");
        }

        List<BookingResponseDTO> response = bookings.stream()
                .map(bookingDtoMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/verTodasReservas")
    public ResponseEntity<List<BookingResponseDTO>> listarReservas() {

        List<Booking> bookings = bookingUseCase.listarReservas();

        if (bookings == null || bookings.isEmpty()) {
            throw new ReservaNoEncontradaException("No existen reservas ");
        }

        List<BookingResponseDTO> response = bookings.stream()
                .map(bookingDtoMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de la reserva debe ser válido.");
        }

        Booking booking = bookingUseCase.obtenerReservaPorId(id);
        if (booking == null) {
            throw new ReservaNoEncontradaException("No se puede eliminar. La reserva no existe.");
        }

        bookingUseCase.eliminarReserva(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<BookingResponseDTO> activar(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de la reserva debe ser válido.");
        }

        Booking booking = bookingUseCase.activarReserva(id);

        if (booking == null) {
            throw new ReservaNoEncontradaException("No se puede cancelar. La reserva no existe.");
        }

        return ResponseEntity.ok(bookingMapper.toResponse(booking));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<BookingResponseDTO> cancelar(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de la reserva debe ser válido.");
        }

        Booking booking = bookingUseCase.cancelarReserva(id);

        if (booking == null) {
            throw new ReservaNoEncontradaException("No se puede cancelar. La reserva no existe.");
        }

        return ResponseEntity.ok(bookingMapper.toResponse(booking));
    }

    @GetMapping("/usuario/{idUsuarioCliente}")
    public ResponseEntity<List<BookingResponseDTO>> obtenerPorUsuario(@PathVariable Long idUsuarioCliente) {

        if (idUsuarioCliente == null || idUsuarioCliente <= 0) {
            throw new IllegalArgumentException("El id del usuario cliente debe ser válido.");
        }

        List<Booking> bookings = bookingUseCase.listarReservasPorUsuario(idUsuarioCliente);

        if (bookings == null || bookings.isEmpty()) {
            throw new ReservaNoEncontradaException("No existen reservas para este usuario.");
        }

        List<BookingResponseDTO> response = bookings.stream()
                .map(bookingMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/servicio/{idServicio}")
    public ResponseEntity<List<BookingResponseDTO>> obtenerPorServicio(@PathVariable Long idServicio) {

        if (idServicio == null || idServicio <= 0) {
            throw new IllegalArgumentException("El id del servicio debe ser válido.");
        }

        List<Booking> bookings = bookingUseCase.listarReservasPorServicio(idServicio);

        if (bookings == null || bookings.isEmpty()) {
            throw new ReservaNoEncontradaException("No existen reservas para este servicio.");
        }

        List<BookingResponseDTO> response = bookings.stream()
                .map(bookingMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/activas")
    public ResponseEntity<List<BookingResponseDTO>> activas() {

        List<Booking> bookings = bookingUseCase.listarReservasActivas();

        if (bookings == null || bookings.isEmpty()) {
            throw new ReservaNoEncontradaException("No hay reservas activas.");
        }

        List<BookingResponseDTO> response = bookings.stream()
                .map(bookingMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }
}
