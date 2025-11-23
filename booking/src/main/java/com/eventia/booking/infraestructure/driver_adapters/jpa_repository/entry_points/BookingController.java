package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;


import com.eventia.booking.domain.exception.ReservaNoEncontradaException;
import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.domain.model.UseCase.BookingUseCase;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.BookingData;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request.BookingRequestDTO;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response.BookingResponseDTO;
import com.eventia.booking.infraestructure.mapper.BookingDtoMapper;
import com.eventia.booking.infraestructure.mapper.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventia/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingMapper mapper;
    private final BookingUseCase bookingUseCase;
    private final BookingDtoMapper bookingDtoMapper;

    @PostMapping("/crearReserva")
    public BookingResponseDTO crearReserva(@RequestBody BookingRequestDTO request) {
        var booking = bookingDtoMapper.toDomain(request);
        var created = bookingUseCase.crearReserva(booking);
        return bookingDtoMapper.toResponse(created);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> obtenerReservaPorId(@PathVariable("id") Long id) {

        Booking booking = bookingUseCase.obtenerReservaPorId(id);

        if (booking == null) {
            throw new ReservaNoEncontradaException(id);
        }

        BookingResponseDTO response = bookingDtoMapper.toResponse(booking);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{Id_Servicio}")
    public ResponseEntity<List<Booking>> listarReservasPorServicio(@PathVariable Long IdServicio) {
        return ResponseEntity.ok(bookingUseCase.listarReservas(IdServicio));
    }

    @DeleteMapping("/eliminar/{Id_Reserva}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Long IdReserva)
    {
        try{
            bookingUseCase.eliminarReserva(IdReserva);
            return new ResponseEntity<>("Reserva eliminada", HttpStatus.OK);

        } catch (RuntimeException e){
            return new ResponseEntity<>("Error al eliminar", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Booking> actualizarReserva(@RequestBody BookingData reservaData){
        try{
            Booking reserva = mapper.toBooking(reservaData);
            Booking reservaValidadaAct = bookingUseCase.actualizarReserva(reserva);
            return new ResponseEntity<>(reservaValidadaAct, HttpStatus.OK);
        } catch (Exception error){
            return ResponseEntity.notFound().build();
        }
    }

}





