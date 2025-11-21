package com.eventia.auth.infraestructure.entry_points;

import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.usecase.AdminUseCase;
import com.eventia.auth.domain.usecase.UsuarioUseCase;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.eventia.auth.infraestructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UsuarioUseCase usuarioUseCase;
    private final AdminUseCase adminUseCase;
    private final UsuarioMapper usuarioMapper;

    @PostMapping("/create")
    public ResponseEntity<Usuario> createUsuario(@RequestBody UsuarioData usuarioData) {
        Usuario usuario = usuarioMapper.toUsuario(usuarioData);
        Usuario guardado = adminUseCase.g;

        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioUseCase.buscarPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
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
            usuarioUseCase.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error al eliminar usuario", HttpStatus.NOT_FOUND);
        }
    }
    //    FUNCIONES SOLO DE ADMIN

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioUseCase.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/verPerfil/{id}")
    public ResponseEntity<Usuario> verPerfilDeUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioUseCase.buscarPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/cambiarEstado/{id}")
    public ResponseEntity<String> cambiarEstado(@PathVariable Long id) {
        boolean cambiado = usuarioUseCase.cambiarEstadoCuenta(id);

        if (cambiado) {
            return ResponseEntity.ok("Estado de cuenta actualizado.");
        }

        return new ResponseEntity<>("No se pudo cambiar el estado.", HttpStatus.BAD_REQUEST);
    }
}
