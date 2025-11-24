package com.eventia.auth.infraestructure.driver_adapters.jpa_repository;

import com.eventia.auth.domain.model.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UsuarioData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_Usuario;
    private String nombre;
    @Column(length = 30, nullable = false, unique = true)
    private String email;
    private String password;
    private Rol rol;
    private Integer edad;
    @OneToOne(cascade = CascadeType.ALL)
    private String ciudad;
}
