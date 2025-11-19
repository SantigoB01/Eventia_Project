package com.eventia.auth.domain.model.gateway;

import com.eventia.auth.domain.model.Usuario;

public interface UsuarioGateway {
    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuario(Long id);
    Usuario buscarPorId(Long id);
    Usuario actualizarUsuario(Usuario usuario);
}
