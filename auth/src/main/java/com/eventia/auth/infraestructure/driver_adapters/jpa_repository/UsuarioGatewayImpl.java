package com.eventia.auth.infraestructure.driver_adapters.jpa_repository;

import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.model.gateway.UsuarioGateway;
import com.eventia.auth.infraestructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioDataJpaRepository repository;


    @Override
    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Usuario buscarPorId(Long id_Usuario) {

        return repository.findById(id_Usuario)
                .map(usuarioData -> usuarioMapper.toUsuario(usuarioData))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        UsuarioData usuarioDataActualizar = usuarioMapper.toData(usuario);

        if (!repository.existsById(usuarioDataActualizar.getId_Usuario())){
            throw new RuntimeException("Usuario con id: " + usuarioDataActualizar.getId_Usuario() + " no existe");
        }
        return usuarioMapper.toUsuario(repository.save(usuarioDataActualizar));

    }
}
