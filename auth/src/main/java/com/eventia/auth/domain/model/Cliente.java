package com.eventia.auth.domain.model;

public class Cliente extends Usuario {

    public Cliente() {
        super();
        setRol(Rol.CLIENTE);
    }
}

