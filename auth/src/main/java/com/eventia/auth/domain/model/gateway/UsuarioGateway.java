package com.eventia.auth.domain.model.gateway;

import com.eventia.auth.domain.model.Usuario;

public interface UsuarioGateway {
    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuario(Long id_Usuario);
    Usuario buscarPorId(Long id_Usuario);
    Usuario actualizarUsuario(Usuario usuario);
}
