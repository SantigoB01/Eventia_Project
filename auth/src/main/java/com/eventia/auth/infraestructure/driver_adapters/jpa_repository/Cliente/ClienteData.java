package com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteData {
    private String telefono;
    private Boolean activo;
    private String reseñaH;
}
