package com.ecommerce.auth.infraestructure.driver_adapters.jpa_repository;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import com.ecommerce.auth.infraestructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioMapper  usuarioMapper;
    private final UsuarioDataJpaRepository repository;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        UsuarioData usuarioData = usuarioMapper.toData(usuario);
        return usuarioMapper.toUsuario(repository.save(usuarioData));
    }

    @Override
    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Usuario buscarPorId(Long id) {

        //return usuarioMapper.toUsuario(repository.findById(id).get());

        return repository.findById(id)
                .map(usuarioData -> usuarioMapper.toUsuario(usuarioData))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        UsuarioData usuarioDataActualizar = usuarioMapper.toData(usuario);

        if (!repository.existsById(usuarioDataActualizar.getId())){
            throw new RuntimeException("Usuario con id: " + usuarioDataActualizar.getId() + " no existe");
        }
        return usuarioMapper.toUsuario(repository.save(usuarioDataActualizar));

    }
    @Override
    public String loginConEmail(String email, String password) {
        Usuario usuario = usuarioMapper.toUsuario(repository.findByEmail(email).get());
        return usuario.getPassword();
    }
}
