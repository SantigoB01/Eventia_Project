package com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Oferente;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "oferente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OferenteData {
    @Id
    private Long id_Usuario;
    private String nombre_Artistico;
    private String especialidad;
    private String telefono;
}
