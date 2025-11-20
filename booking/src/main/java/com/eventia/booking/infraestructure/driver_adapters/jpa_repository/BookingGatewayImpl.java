package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;

import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.domain.model.gateway.BookingGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingGatewayImpl implements BookingGateway {

    private final BookingDataJpaRepository repository;

    public BookingGatewayImpl(BookingDataJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Booking crearReserva(Booking reserva) {
        BookingData data = toData(reserva);
        BookingData saved = repository.save(data);
        return toBooking(saved);
    }

    @Override
    public Booking obtenerReservaPorId(Integer Id_Reserva) {
        return repository.findById(Id_Reserva)
                .map(this::toBooking)
                .orElse(null);
    }

    @Override
    public List<Booking> listarReservasPorCliente(Integer Id_Usuario_Cliente) {
        return repository.findByIdUsuarioCliente(Id_Usuario_Cliente)
                .stream()
                .map(this::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public Booking actualizarReserva(Booking reserva) {
        BookingData data = toData(reserva);
        BookingData saved = repository.save(data);
        return toBooking(saved);
    }
