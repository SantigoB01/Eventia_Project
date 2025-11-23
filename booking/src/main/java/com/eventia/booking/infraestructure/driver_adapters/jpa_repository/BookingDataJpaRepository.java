package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;

import com.eventia.booking.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookingDataJpaRepository extends JpaRepository<BookingData, Long> {
    Optional<BookingData> findByIdUsuarioCliente(long IdUsuarioCliente);

    List<BookingData> findByIdServicio(Long idServicio);

    List<BookingData> findByEstado(String activo);

    List<BookingData> findByFechaReserva(LocalDate fecha);
}
