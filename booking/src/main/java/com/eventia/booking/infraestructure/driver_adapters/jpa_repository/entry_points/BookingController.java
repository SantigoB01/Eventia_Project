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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingUseCase bookingUseCase;
    private final BookingDtoMapper bookingMapper;
    private final BookingDtoMapper bookingDtoMapper;

    // -----------------------------------------------------------

    @PostMapping("/crearReserva")
    public ResponseEntity<BookingResponseDTO> crearReserva(
            @RequestBody BookingRequestDTO request) {

        Booking booking = bookingMapper.toDomain(request);
        Booking creada = bookingUseCase.crearReserva(booking);

        return ResponseEntity.ok(bookingMapper.toResponse(creada));
    }

    // -----------------------------------------------------------

    @GetMapping("/reserva/{id}")
    public ResponseEntity<BookingResponseDTO> obtenerPorId(@PathVariable Long id) {

        Booking booking = bookingUseCase.obtenerReservaPorId(id);

        if (booking == null) {
            throw new ReservaNoEncontradaException("Reserva Inexistente");
        }

        return ResponseEntity.ok(bookingMapper.toResponse(booking));
    }



    @GetMapping("/verTodasReservas/{idUsuarioCliente}")
    public ResponseEntity<List<BookingResponseDTO>> listarReservas(@PathVariable Long idUsuarioCliente) {

        List<Booking> bookings = bookingUseCase.listarReservas(idUsuarioCliente);

        if (bookings == null || bookings.isEmpty()) {
            throw new ReservaNoEncontradaException("Reservas inexistentes");
        }

        List<BookingResponseDTO> response = bookings.stream()
                .map(bookingDtoMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {

        bookingUseCase.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }

    // -----------------------------------------------------------

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<BookingResponseDTO> cancelar(@PathVariable Long id) {

        Booking booking = bookingUseCase.cancelarReserva(id);

        return ResponseEntity.ok(bookingMapper.toResponse(booking));
    }

    // -----------------------------------------------------------

    @GetMapping("/usuario/{idUsuarioCliente}")
    public ResponseEntity<List<BookingResponseDTO>> obtenerPorUsuario(@PathVariable Long idUsuarioCliente) {

        List<Booking> bookings = bookingUseCase.listarReservasPorUsuario(idUsuarioCliente);

        List<BookingResponseDTO> response = bookings.stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // -----------------------------------------------------------

    @GetMapping("/servicio/{idServicio}")
    public ResponseEntity<List<BookingResponseDTO>> obtenerPorServicio(@PathVariable Long idServicio) {

        List<Booking> bookings = bookingUseCase.listarReservasPorServicio(idServicio);

        List<BookingResponseDTO> response = bookings.stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // -----------------------------------------------------------

    @GetMapping("/activas")
    public ResponseEntity<List<BookingResponseDTO>> activas() {

        List<Booking> bookings = bookingUseCase.listarReservasActivas();

        List<BookingResponseDTO> response = bookings.stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}





