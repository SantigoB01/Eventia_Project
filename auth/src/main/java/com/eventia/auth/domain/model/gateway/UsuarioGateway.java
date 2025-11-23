package com.eventia.auth.domain.model.gateway;

import com.eventia.auth.domain.model.Usuario;

public interface UsuarioGateway {
    void eliminarUsuario(Long id_Usuario);
    Usuario buscarPorId(Long id_Usuario);
    Usuario actualizarUsuario(Usuario usuario);
}
