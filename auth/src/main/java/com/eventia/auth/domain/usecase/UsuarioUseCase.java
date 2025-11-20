package com.eventia.auth.domain.usecase;

import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.model.gateway.EncrypterGateway;
import com.eventia.auth.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final EncrypterGateway encrypterGateway;

    public Usuario guardarUsuario (Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getPassword() == null || usuario.getNombre() == null || usuario.getEdad() == null) {
            throw new NullPointerException("Ojo con eso, campos vacios");

        }
        if(usuario.getNombre() == null && usuario.getPassword() == null){
            throw new IllegalArgumentException("El nombre y la contraseña son obligatorios");
        }
        if (usuario.getEdad()<18) {
            System.out.println("Necesitas ser mayor de 18");
        }
        String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
        usuario.setPassword(passwordEncrypt);
        Usuario usuarioGuardado = usuarioGateway.guardarUsuario(usuario);


        return usuarioGuardado;
    }

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

    public String loginConEmail(String email, String password) {
        String PassBD = usuarioGateway.loginConEmail(email, password);
        Boolean validacion = encrypterGateway.checkPass(password, PassBD);
        if(validacion) {
            return "Ha iniciado sesión con exito";
        }else{
            return "Contraseña o Correo incorrecto";
        }

    }

    public void registrarRol (Usuario usuario) {
        try{

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

