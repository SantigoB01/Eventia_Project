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
        }
        if(usuario.getRol() == null){
            throw new NullPointerException("Debes ingresar un rol valido");
        }
        String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
        usuario.setPassword(passwordEncrypt);

        Usuario usuarioGuardado = usuarioGateway.guardarUsuario(usuario);
        return usuarioGuardado;
    }

    public void eliminarUsuarioPorId(Long id_Usuario){
        try{
            usuarioGateway.eliminarUsuario(id_Usuario);
        }catch(Exception error){
            System.out.println(error.getMessage());
        }
    }
}
