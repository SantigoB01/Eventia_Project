package com.eventia.auth.infraestructure.driver_adapters.jpa_repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UsuarioData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_Usuario;
    private String nombre;
    @Column(length = 30, nullable = false)
    private String email;
    private String password;
    private String rol;
    private Integer edad;
    private String numeroTelefono;
    private String ciudad;
}
