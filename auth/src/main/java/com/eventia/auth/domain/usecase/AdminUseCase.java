package com.eventia.auth.domain.usecase;

import com.eventia.auth.domain.model.Rol;
import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.model.gateway.AdminGateway;
import com.eventia.auth.domain.model.gateway.EncrypterGateway;
import com.eventia.auth.domain.model.gateway.ServicioGateway;
import com.eventia.auth.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class AdminUseCase {

        private final UsuarioGateway usuarioGateway;
        private final AdminGateway adminGateway;
        private final EncrypterGateway encrypterGateway;
        private final ServicioGateway servicioGateway;

        public Usuario crearAdmin(Usuario usuario) {
            if (usuario.getEmail() == null || usuario.getPassword() == null) {
                throw new IllegalArgumentException("Email y contraseña son obligatorios");
            }

            usuario.setRol(Rol.ADMIN);

            String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
            usuario.setPassword(passwordEncrypt);


            return adminGateway.crearAdmin(usuario);
        }

        public List<Usuario> verListadoUsuarios() {
            return adminGateway.verListadoUsuarios();
        }

        public String cambiarRol(Long idUsuario, Rol rol) {
            Usuario usuario = usuarioGateway.buscarPorId(idUsuario);

            if (usuario == null || usuario.getId_Usuario() == null) {
                throw new IllegalArgumentException("Usuario no encontrado");
            }

            if (rol == null) {
                throw new IllegalArgumentException("El rol no puede ser nulo");
            }

            usuario.setRol(rol);
            usuarioGateway.actualizarUsuario(usuario);

            return "Rol actualizado correctamente";
        }


        public String resetearContraseña(Long idUsuario) {
            Usuario usuario = usuarioGateway.buscarPorId(idUsuario);

            if (usuario == null || usuario.getId_Usuario() == null) {
                throw new IllegalArgumentException("Usuario no encontrado");
            }

            String newPassword = "123456";

            String encrypted = encrypterGateway.encrypt(newPassword);
            usuario.setPassword(encrypted);

            usuarioGateway.actualizarUsuario(usuario);

            return "Contraseña restablecida correctamente";
        }


        public String crearCategoria(String nombreC) {
            if (nombreC == null || nombreC.isBlank()) {
                throw new IllegalArgumentException("El nombre de categoría es obligatorio");
            }

            if (servicioGateway.obtenerCategoria(nombreC) == "Existe") {
                throw new IllegalArgumentException("La categoría ya existe");
            }

            servicioGateway.crearCategoria(nombreC);
            return "Categoría creada correctamente";
        }


        public String eliminarCategoria(String nombreC) {
            if (servicioGateway.obtenerCategoria(nombreC) == "No existe") {
                throw new IllegalArgumentException("La categoría no existe");
            }

            servicioGateway.eliminarCategoria(nombreC);
            return "Categoría eliminada correctamente";
        }

    }

