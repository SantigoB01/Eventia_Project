package com.eventia.auth.domain.model;

public class Oferente extends Usuario {

    private String nombre_Artistico;
    private String especialidad;
    private String telefono;

    public Oferente() {
        super();
        setRol(Rol.OFERENTE);
    }
}
