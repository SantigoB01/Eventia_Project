package com.eventia.auth.domain.usecase;

import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.model.gateway.EncrypterGateway;
import com.eventia.auth.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final EncrypterGateway encrypterGateway;


    public void eliminarUsuarioPorId(Long id_Usuario) {
        try{
            usuarioGateway.eliminarUsuario(id_Usuario);
        } catch (Exception error){
            System.out.println(error.getMessage());
        }
    }

    public Usuario buscarUsuarioPorId (Long id_Usuario) {
        try{
            return usuarioGateway.buscarPorId(id_Usuario);
        }catch (Exception error){
            System.out.println(error.getMessage());
            return new Usuario();
        }
    }

    public Usuario actualizarUsuario (Usuario usuario){
        if (usuario.getId_Usuario() == null){
            throw new IllegalArgumentException("El ID es obligatorio");
        }
        //if (usu)
        String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
        usuario.setPassword(passwordEncrypt);

        return usuarioGateway.actualizarUsuario(usuario);
    }




}

