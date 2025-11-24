package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;

import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.AgendaData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface AgendaDataJpaRepository extends JpaRepository<AgendaData, Long> {
    List<AgendaData> findByIdServicio(Long idServicio);
}
