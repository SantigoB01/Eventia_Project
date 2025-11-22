package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface BookingDataJpaRepository extends JpaRepository<BookingData, Long> {
    Optional<BookingData> findByIdUsuarioCliente(long IdUsuarioCliente);
    List<BookingData> findAllByIdServicio(long IdServicio);
}
