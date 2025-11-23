package com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Oferente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OferenteDataJpaRepository extends JpaRepository<OferenteData, Long> {
    boolean existsByEmail(String email);
    boolean existsByNombreArtistico(String nombreArtistico);
    boolean existsByTelefono(String telefono);
}
