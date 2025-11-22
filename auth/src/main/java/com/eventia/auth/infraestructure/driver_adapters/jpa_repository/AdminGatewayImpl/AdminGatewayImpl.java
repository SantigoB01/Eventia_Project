package com.eventia.auth.infraestructure.driver_adapters.jpa_repository.AdminGatewayImpl;

import com.eventia.auth.domain.model.Rol;
import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.model.gateway.AdminGateway;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.UsuarioDataJpaRepository;
import com.eventia.auth.infraestructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AdminGatewayImpl implements AdminGateway {

    private final UsuarioDataJpaRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario crearAdmin(Usuario usuario) {
        try{
        UsuarioData usuarioData = usuarioRepository.save(usuarioMapper.toData(usuario));

        return usuarioMapper.toUsuario(usuarioData);}
        catch(Exception e){
            return null;
        }
    }

    @Override
    public List<Usuario> verListadoUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toUsuario)
                .collect(Collectors.toList());
    }

    @Override
    public String cambiarRol(Long idUsuario, Rol rol) {

        UsuarioData data = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        data.setRol(rol);
        usuarioRepository.save(data);

        return "Rol actualizado correctamente a: " + rol.name();
    }

    @Override
    public String resetearContraseña(Long idUsuario) {

        UsuarioData data = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String defaultPassword = "123456";
        data.setPassword(defaultPassword);

        usuarioRepository.save(data);

        return "Contraseña reseteada exitosamente";
    }

    @Override
    public String crearCategoria(String nombreC) {
        // TODO: mover a microservicio Categoría más adelante
        return "Operación delegada al microservicio de Categorías";
    }

    @Override
    public String eliminarCategoria(String nombreC) {
        // TODO: mover a microservicio Categoría más adelante
        return "Operación delegada al microservicio de Categorías";
    }
}
