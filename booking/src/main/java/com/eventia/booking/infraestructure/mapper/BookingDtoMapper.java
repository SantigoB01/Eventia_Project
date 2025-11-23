package com.eventia.booking.infraestructure.mapper;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request.BookingRequestDTO;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response.BookingResponseDTO;
import com.eventia.booking.domain.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingDtoMapper {

    public Booking toDomain(BookingRequestDTO dto) {
        return Booking.builder()
                .idReserva(null)  // se genera en BD
                .idServicio(dto.getIdServicio())
                .idUsuarioCliente(dto.getIdUsuarioCliente())
                .fechaReserva(dto.getFechaReserva()) // LocalDate
                .fechaInicio(dto.getFechaInicio().toLocalDateTime())   // LocalDateTime
                .fechaFin(dto.getFechaFin().toLocalDateTime())         // LocalDateTime
                .estado("ACTIVO")
                .total(dto.getTotal())
                .fechaCreacion(dto.getFechaCreacion().toLocalDateTime())
                .build();
    }

    public BookingResponseDTO toResponse(Booking booking) {
        return BookingResponseDTO.builder()
                .idReserva(booking.getIdReserva())
                .idServicio(booking.getIdServicio())
                .idUsuarioCliente(booking.getIdUsuarioCliente())
                .fechaReserva(booking.getFechaReserva())
                .fechaInicio(booking.getFechaInicio())
                .fechaFin(booking.getFechaFin())
                .estado(booking.getEstado())
                .total(booking.getTotal())
                .fechaCreacion(booking.getFechaCreacion())
                .build();
    }
}