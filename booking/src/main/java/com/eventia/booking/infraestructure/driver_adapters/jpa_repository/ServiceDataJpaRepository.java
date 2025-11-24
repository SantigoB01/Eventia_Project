package com.eventia.booking.infraestructure.driver_adapters.jpa_repository;


import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.enums.CiudadSumapaz;
import com.eventia.booking.domain.model.enums.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ServiceDataJpaRepository extends JpaRepository<ServiceData, Long> {
    List<Servicio> findByCiudad(CiudadSumapaz ciudad);

    List<Servicio> findByTipo(TipoServicio tipo);

    List<Servicio> findByIdUsuarioOferente(Long idUsuarioOferente);
}