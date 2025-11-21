package com.eventia.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Oferente extends Usuario {
    private String nombre_Artistico;
    private String especialidad;
    private String telefono;
    private Boolean activo;


    public Oferente() {
        super();
        setRol(Rol.OFERENTE);
    }
}
