package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;


import com.eventia.booking.domain.model.Booking;
import com.eventia.booking.domain.model.gateway.BookingGateway;
import com.eventia.booking.infraestructure.mapper.BookingMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Transactional
public class BookingGatewayImpl implements BookingGateway {

    private final BookingDataJpaRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public Booking crearReserva(Booking booking) {
        BookingData entity = bookingMapper.toData(booking);
        BookingData saved = bookingRepository.save(entity);
        return bookingMapper.toBooking(saved);
    }

    @Override
    public Booking obtenerReservaPorId(Long id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toBooking)
                .orElse(null);
    }

    @Override
    public List<Booking> listarReservas() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toBooking)
                .collect(Collectors.toList());
    }


    @Override
    public List<Booking> listarReservasPorUsuario(Long idUsuario) {
        return bookingRepository.findByIdUsuarioCliente(idUsuario)
                .stream()
                .map(bookingMapper::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> listarReservasPorServicio(Long idServicio) {
        return bookingRepository.findByIdServicio(idServicio)
                .stream()
                .map(bookingMapper::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> listarReservasActivas() {
        return bookingRepository.findByEstado("ACTIVO")
                .stream()
                .map(bookingMapper::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public Booking actualizarReserva(Booking booking) {
        BookingData entity = bookingMapper.toData(booking);
        BookingData updated = bookingRepository.save(entity);
        return bookingMapper.toBooking(updated);
    }

    @Override
    public void eliminarReserva(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> listarReservasPorFecha(LocalDate fecha) {
        return bookingRepository.findByFechaReserva(fecha)
                .stream()
                .map(bookingMapper::toBooking)
                .collect(Collectors.toList());
    }
}