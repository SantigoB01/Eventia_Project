package com.eventia.booking.infraestructure.mapper;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request.BookingRequestDTO;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response.BookingResponseDTO;
import com.eventia.booking.domain.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingDtoMapper {

    public Booking toDomain(BookingRequestDTO dto) {
        return Booking.builder()
                .idReserva(null)
                .idServicio(dto.getIdServicio())
                .idUsuarioCliente(dto.getIdUsuarioCliente())
                .fechaReserva(dto.getFechaReserva())
                .horaInicio(dto.getFechaInicio())
                .horaFin(dto.getFechaFin())
                .estado("ACTIVO")
                .CostoCalculado(dto.getCostoCalculado())
                .fechaCreacion(dto.getFechaCreacion())
                .build();
    }

    public BookingResponseDTO toResponse(Booking booking) {
        return BookingResponseDTO.builder()
                .idReserva(booking.getIdReserva())
                .idServicio(booking.getIdServicio())
                .idUsuarioCliente(booking.getIdUsuarioCliente())
                .fechaReserva(booking.getFechaReserva())
                .fechaInicio(booking.getHoraInicio())
                .fechaFin(booking.getHoraFin())
                .estado(booking.getEstado())
                .costoCalculado(booking.getCostoCalculado())
                .fechaCreacion(booking.getFechaCreacion())
                .build();
    }
}