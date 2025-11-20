package com.eventia.auth.infraestructure.driver_adapters.jpa_repository.AdminGatewayImpl;

import com.eventia.auth.domain.model.Rol;
import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.model.gateway.AdminGateway;

import java.util.List;

public class AdminGatewayImpl implements AdminGateway {

    @Override
    public List<Usuario> verListadoUsuarios() {
        return List.of();
    }

    @Override
    public String cambiarRol(Long idUsuario, Rol rol) {
        return "";
    }

    @Override
    public String resetearContraseña(Long idUsuario) {
        return "";
    }

    @Override
    public String crearCategoria(String nombreC) {
        return "";
    }

    @Override
    public String eliminarCategoria(String nombreC) {
        return "";
    }
}
