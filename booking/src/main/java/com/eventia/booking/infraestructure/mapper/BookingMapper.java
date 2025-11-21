package com.eventia.booking.infraestructure.mapper;

import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.BookingData;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking toBooking(BookingData BookingData){
        return new Booking(
                BookingData.getId_Reserva(),
                BookingData.getId_Servicio(),
                BookingData.getId_Usuario_Cliente(),
                BookingData.getFecha_Reserva(),
                BookingData.
                BookingData.getEstado(),
                BookingData.getTotal()
        );
    }

    public BookingData toData(Booking booking){
        return new BookingData(
                booking.getId_Reserva(),
                booking.getId_Servicio(),
                booking.getId_Usuario_Cliente(),
                booking.getFecha_Reserva(),
                booking.getFechaInicio(),
                booking.getFechaFin(),
                booking.getEstado(),
                booking.getTotal()
        );
    }
}
