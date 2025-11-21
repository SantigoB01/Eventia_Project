package com.eventia.auth.domain.model.gateway;

import com.eventia.auth.domain.model.Rol;
import com.eventia.auth.domain.model.Usuario;

import java.util.List;

public interface AdminGateway {
    Usuario crearAdmin(Usuario usuario);
    List<Usuario> verListadoUsuarios();
    String cambiarRol(Long idUsuario, Rol rol);
    String resetearContraseña(Long idUsuario);
    String crearCategoria(String nombreC);
    String eliminarCategoria(String nombreC);

}
