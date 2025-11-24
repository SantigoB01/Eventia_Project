package com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Oferente;


import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "oferente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OferenteData extends UsuarioData {

        @JsonProperty("nombre_Artistico")
        @Column(unique = true)
        private String nombreArtistico;

        @JsonProperty("especialidad")
        private String especialidad;

        @JsonProperty("telefono")
        @Column(unique = true)
        private String telefono;

        @JsonProperty("activo")
        private Boolean activo;
}
