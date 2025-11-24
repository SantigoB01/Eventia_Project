package com.eventia.auth.infraestructure.entry_points;

import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.usecase.UsuarioUseCase;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.eventia.auth.infraestructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/eventia/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioUseCase usuarioUseCase;

    // ===== ENDPOINTS PARA VERIFICACIÓN DE OTROS SERVICIOS =====

    @GetMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyCredentials(@AuthenticationPrincipal UserDetails userDetails) {
        Map<String, Object> response = new HashMap<>();

        if (userDetails != null) {
            response.put("authenticated", true);
            response.put("username", userDetails.getUsername());
            response.put("roles", userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));
        } else {
            response.put("authenticated", false);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/verificar-rol")
    public ResponseEntity<Map<String, Boolean>> verificarRol(
            @RequestParam String rol,
            @AuthenticationPrincipal UserDetails userDetails) {

        Map<String, Boolean> response = new HashMap<>();

        if (userDetails == null) {
            response.put("tieneRol", false);
            return ResponseEntity.ok(response);
        }

        boolean tieneRol = userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + rol));

        response.put("tieneRol", tieneRol);
        return ResponseEntity.ok(response);
    }

    // ===== ENDPOINTS EXISTENTES =====

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findByIdUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioUseCase.buscarUsuarioPorId(id);

        if (usuario.getId_Usuario() != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsuarioById(@PathVariable Long id) {
        try {
            usuarioUseCase.eliminarUsuarioPorId(id);
            return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Hubo un error", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody UsuarioData usuarioData) {
        try {
            Usuario usuario = usuarioMapper.toUsuario(usuarioData);
            Usuario usuarioValidadoActualizado = usuarioUseCase.actualizarUsuario(usuario);
            return new ResponseEntity<>(usuarioValidadoActualizado, HttpStatus.OK);
        } catch (Exception error) {
            return ResponseEntity.notFound().build();
        }
    }
}