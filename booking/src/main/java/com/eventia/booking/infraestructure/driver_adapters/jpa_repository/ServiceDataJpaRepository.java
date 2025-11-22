package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;


import com.eventia.booking.domain.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceDataJpaRepository extends JpaRepository<ServiceData, Long> {
    List<Servicio> findByCiudad(String ciudad);

    List<Servicio> findByTipo(String tipo);
}