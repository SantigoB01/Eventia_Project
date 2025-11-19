package com.eventia.auth.domain.usecase;

import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.model.gateway.EncrypterGateway;
import com.eventia.auth.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final EncrypterGateway encrypterGateway;

    public Usuario guardarUsuario(Usuario usuario){
        if(usuario.getEmail() == null && usuario.getPassword() == null){
            throw new NullPointerException("Ojo con eso manito!! - guardarUsuario");
            String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
            usuario.setPassword(passwordEncrypt);

            Usuario usuarioGuardado = usuarioGateway.guardarUsuario(usuario);
        }
    }
}
