package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;

import com.eventia.booking.domain.model.enums.CiudadSumapaz;
import com.eventia.booking.domain.model.enums.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ServiceDataJpaRepository extends JpaRepository<ServiceData, Long> {
    List<ServiceData> findByCiudad(CiudadSumapaz ciudad);

    List<ServiceData> findByTipo(TipoServicio tipo);

    List<ServiceData> findByIdUsuarioOferente(Long idUsuarioOferente);
}