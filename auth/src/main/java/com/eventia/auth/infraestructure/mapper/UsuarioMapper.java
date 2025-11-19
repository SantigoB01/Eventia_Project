package com.eventia.auth.infraestructure.mapper;

import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toUsuario(UsuarioData usuarioData){
        return new Usuario(
                usuarioData.getId_Usuario(),
                usuarioData.getNombre(),
                usuarioData.getEmail(),
                usuarioData.getPassword(),
                usuarioData.getRol(),
                usuarioData.getEdad(),
                usuarioData.getNumeroTelefono(),
                usuarioData.getCiudad()
        );
    }

    public UsuarioData toData(Usuario usuario){
        return new UsuarioData(
                usuario.getId_Usuario(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getRol(),
                usuario.getEdad(),
                usuario.getNumeroTelefono(),
                usuario.getCiudad()
        );
    }
}
