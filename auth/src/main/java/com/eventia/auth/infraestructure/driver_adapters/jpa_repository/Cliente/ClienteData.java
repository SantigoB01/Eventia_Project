package com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Cliente;

import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteData extends UsuarioData {

    @JsonProperty("telefono")
    @Column(unique = true)
    private String telefono;

    @JsonProperty("activo")
    private Boolean activo;

    @JsonProperty("reseñaH")
    private String resenaH;
}
