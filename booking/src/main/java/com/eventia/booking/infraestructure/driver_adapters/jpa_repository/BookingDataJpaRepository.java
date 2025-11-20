package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface BookingDataJpaRepository extends JpaRepository<BookingData, Long> {
    Optional<BookingData> findByIdUsuarioCliente(Integer Id_Usuario_Cliente);
}
