package com.eventia.booking.infraestructure.mapper;

import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.BookingData;
import org.springframework.stereotype.Component;


@Component
public class BookingMapper {

    public Booking toBooking(BookingData BookingData){
        return new Booking(
                BookingData.getIdReserva(),
                BookingData.getIdServicio(),
                BookingData.getIdUsuarioCliente(),
                BookingData.getFechaReserva(),
                BookingData.getFechaInicio(),
                BookingData.getFechaFin(),
                BookingData.getEstado(),
                BookingData.getTotal(),
                BookingData.getFechaCreacion()
        );
    }

    public BookingData toData(Booking booking){
        return new BookingData(
                booking.getIdReserva(),
                booking.getIdServicio(),
                booking.getIdUsuarioCliente(),
                booking.getFechaReserva(),
                booking.getFechaInicio(),
                booking.getFechaFin(),
                booking.getEstado(),
                booking.getTotal(),
                booking.getFechaCreacion());
    }
}
