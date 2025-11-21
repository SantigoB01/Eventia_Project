package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;

import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.domain.model.gateway.BookingGateway;
import com.eventia.booking.infraestructure.mapper.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Component
public class BookingGatewayImpl implements BookingGateway {

    private final BookingDataJpaRepository repository;
    private final BookingMapper mapper;

    @Override
    public Booking crearReserva(Booking reserva) {
        BookingData data = mapper.toData(reserva);
        BookingData saved = repository.save(data);
        return mapper.toBooking(saved);
    }

    @Override
    public Booking obtenerReservaPorId(Long Id_Reserva) {
        return repository.findById(Id_Reserva)
                .map(mapper::toBooking)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    @Override
    public List<Booking> listarReservasPorCliente(Long Id_Usuario_Cliente) {
        return repository.findByIdUsuarioCliente(Id_Usuario_Cliente)
                .stream()
                .map(mapper::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public Booking actualizarReserva(Booking reserva) {
        BookingData data = mapper.toData(reserva);
        BookingData saved = repository.save(data);
        return mapper.toBooking(saved);
    }

    @Override
    public void eliminarReserva(Long Id_Reserva){
        repository.deleteById(Id_Reserva);
    }
}