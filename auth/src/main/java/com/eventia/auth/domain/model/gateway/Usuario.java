package com.eventia.auth.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

public class Usuario {
    private Long id_Usuario;
    private String nombre;
    private String email;
    private String password;
    private String rol;
    private Integer edad;
    private String numeroTelefono;
    private String ciudad;

}
