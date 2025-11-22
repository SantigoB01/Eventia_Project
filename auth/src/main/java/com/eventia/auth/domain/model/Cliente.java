package com.eventia.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Setter
@Getter
public class Cliente extends Usuario {

    private String telefono;
    private Boolean activo;
    private String resenasH;

    public Cliente() {
        super();
        setRol(Rol.CLIENTE);
    }
}

