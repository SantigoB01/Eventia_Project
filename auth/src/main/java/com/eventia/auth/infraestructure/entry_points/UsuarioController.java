package com.eventia.auth.infraestructure.entry_points;

import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.usecase.UsuarioUseCase;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.eventia.auth.infraestructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventia/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioUseCase usuarioUseCase;

    @PostMapping("/saveUsuario")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody UsuarioData usuarioData) {
        Usuario usuarioConvertido = usuarioMapper.toUsuario(usuarioData);
        var usuario = usuarioUseCase.guardarUsuario(usuarioConvertido);

        if (usuarioConvertido.getNombre() != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);
    }

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
            return new ResponseEntity<>("Usuario eliminado exictosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Hubo un error", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody UsuarioData usuarioData){
        try{
            Usuario usuario = usuarioMapper.toUsuario(usuarioData);
            Usuario usuarioValidadoActualizado = usuarioUseCase.actualizarUsuario(usuario);
            return new ResponseEntity<>(usuarioValidadoActualizado, HttpStatus.OK);
        } catch (Exception error){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login/{email}/{password}")
    public ResponseEntity<String> loginConEmail(@PathVariable String email, @PathVariable String password) {
        try{
            String validacion = usuarioUseCase.loginConEmail(email, password);
            return new ResponseEntity<>(validacion, HttpStatus.OK);
        }catch(Exception error) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>("Correo Incorrecto", HttpStatus.NOT_FOUND);
        }
    }
}
