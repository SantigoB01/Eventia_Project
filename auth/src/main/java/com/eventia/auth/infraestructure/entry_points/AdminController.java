package com.eventia.auth.infraestructure.entry_points;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.domain.model.Rol;
import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.usecase.AdminUseCase;
import com.eventia.auth.domain.usecase.ClienteUseCase;
import com.eventia.auth.domain.usecase.OferenteUseCase;
import com.eventia.auth.domain.usecase.UsuarioUseCase;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.eventia.auth.infraestructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UsuarioUseCase usuarioUseCase;
    private final AdminUseCase adminUseCase;
    private final UsuarioMapper usuarioMapper;
    private final ClienteUseCase clienteUseCase;
    private final OferenteUseCase oferenteUseCase;

    @PostMapping("/create")
    public ResponseEntity<Usuario> createUsuario(@RequestBody UsuarioData usuarioData) {
        Usuario usuario = usuarioMapper.toUsuario(usuarioData);
        Usuario guardado = adminUseCase.crearAdmin(usuario);

        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody UsuarioData usuarioData) {
        try {
            Usuario usuario = usuarioMapper.toUsuario(usuarioData);
            Usuario actualizado = usuarioUseCase.actualizarUsuario(usuario);
            return ResponseEntity.ok(actualizado);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        try {
            usuarioUseCase.eliminarUsuarioPorId(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error al eliminar usuario", HttpStatus.NOT_FOUND);
        }
    }
    //    FUNCIONES SOLO DE ADMIN

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = adminUseCase.verListadoUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/verPerfil/{id}")
    public ResponseEntity<Usuario> verPerfilDeUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioUseCase.buscarUsuarioPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/cambiarEstado/{id}")
    public ResponseEntity<String> cambiarEstado(@PathVariable Long id) {
        Usuario usuario = usuarioUseCase.buscarUsuarioPorId(id);
        boolean cambiado;
        if (usuario.getRol() == Rol.CLIENTE) {
            cambiado = oferenteUseCase.cambioEstadoCuenta(id);

        }else{
            cambiado = clienteUseCase.cambiarEstadoCuenta(id);

        }

        if (cambiado) {
            return ResponseEntity.ok("Estado de cuenta actualizado.");
        }

        return new ResponseEntity<>("No se pudo cambiar el estado.", HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/cambiarRol/{id}")
    public ResponseEntity<String> cambiarRol(@PathVariable Long id,@RequestParam Rol rol) {
        try {
            String mensaje = adminUseCase.cambiarRol(id, rol);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/resetPassword/{id}")
    public ResponseEntity<String> resetearContraseña(@PathVariable Long id) {
        try {
            String mensaje = adminUseCase.resetearContraseña(id);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crearCategoria")
    public ResponseEntity<String> crearCategoria(@RequestParam String nombre) {
        try {
            String mensaje = adminUseCase.crearCategoria(nombre);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminarCategoria")
    public ResponseEntity<String> eliminarCategoria(@RequestParam String nombre) {
        try {
            String mensaje = adminUseCase.eliminarCategoria(nombre);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
