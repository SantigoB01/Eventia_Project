package com.eventia.booking.infraestructure.mapper;

import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request.BookingRequestDTO;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response.BookingResponseDTO;
import com.eventia.booking.domain.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingDtoMapper {

    public Booking toDomain(BookingRequestDTO dto) {
        return new Booking(
                null,
                dto.getIdServicio(),
                dto.getIdUsuarioCliente(),
                dto.getFechaReserva(),
                dto.getFechaInicio().toLocalDateTime(),
                dto.getFechaFin().toLocalDateTime(),
                "ACTIVO",
                dto.getTotal()
        );
    }

    public BookingResponseDTO toResponse(Booking booking) {
        BookingResponseDTO response = new BookingResponseDTO();
        response.setIdReserva(booking.getIdReserva());
        response.setIdServicio(booking.getIdServicio());
        response.setIdUsuarioCliente(booking.getIdUsuarioCliente());
        response.setFechaReserva(booking.getFechaReserva());
        response.setFechaInicio(booking.getFechaInicio());
        response.setFechaFin(booking.getFechaFin());
        response.setEstado(booking.getEstado());
        response.setTotal(booking.getTotal());
        return response;
    }
}