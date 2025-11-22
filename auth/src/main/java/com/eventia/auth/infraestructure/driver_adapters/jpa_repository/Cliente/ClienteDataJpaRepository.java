package com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDataJpaRepository extends JpaRepository<ClienteData, Long> {
    boolean existsByEmail(String email);
    boolean existsByTelefono(String telefono);
}
