package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;


import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.domain.model.UseCase.BookingUseCase;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.BookingData;
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

    @PostMapping("/crearReserva")
    public ResponseEntity<Booking> crearReserva(@RequestBody BookingData reservadata) {
        Booking reservaConvertida = mapper.toBooking(reservadata);
        var reserva = bookingUseCase.crearReserva(reservaConvertida);

        if (reservaConvertida.getEstado() != null) {
            return new ResponseEntity<>(reserva, HttpStatus.OK);
        }
        return new ResponseEntity<>(reserva, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> ObtenerReservaPorId(@PathVariable Long Id_Reserva) {

            Booking reserva = bookingUseCase.obtenerReservaPorId(Id_Reserva);

            if (reserva.getId_Reserva() != null) {
                return new ResponseEntity<>(reserva, HttpStatus.OK);
            }
            return new ResponseEntity<>(reserva, HttpStatus.NOT_FOUND);
        }

    @GetMapping("/{Id_Servicio}")
    public ResponseEntity<List<Booking>> listarReservasPorServicio(@PathVariable Long Id_Usuario_Cliente) {
        return ResponseEntity.ok(bookingUseCase.listarReservas(Id_Usuario_Cliente));
    }

    @DeleteMapping("/eliminar/{Id_Reserva}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Long Id_Reserva)
    {
        try{
            bookingUseCase.eliminarReserva(Id_Reserva);
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





