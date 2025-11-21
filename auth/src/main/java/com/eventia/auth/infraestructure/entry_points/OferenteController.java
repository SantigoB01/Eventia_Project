package com.eventia.auth.infraestructure.entry_points;

import com.eventia.auth.domain.model.Oferente;
import com.eventia.auth.domain.model.Usuario;
import com.eventia.auth.domain.usecase.OferenteUseCase;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Oferente.OferenteData;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.eventia.auth.infraestructure.mapper.OferenteMapper;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oferente")
@RequiredArgsConstructor
public class OferenteController {

    private final OferenteMapper oferenteMapper;
    private final OferenteUseCase oferenteUseCase;

    @PostMapping("/saveOferente")
    public ResponseEntity<Oferente> saveOferente(@RequestBody OferenteData oferenteData) {
        Oferente oferenteC = oferenteMapper.toOferente(oferenteData);
        var Oferente = oferenteUseCase.guardarOferente(oferenteC);

        if (oferenteC.getNombre() != null) {
            return new ResponseEntity<>(Oferente, HttpStatus.OK);
        }
        return new ResponseEntity<>(Oferente, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findByIdUsuario(@PathVariable Long id) {

        Oferente oferente = oferenteUseCase.buscarOferentePorId(id);

        if (oferente.getId_Usuario() != null) {
            return new ResponseEntity<>(oferente, HttpStatus.OK);
        }
        return new ResponseEntity<>(oferente, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsuarioById(@PathVariable Long id) {
        try {
            oferenteUseCase.eliminarOferentePorId(id);
            return new ResponseEntity<>("Usuario eliminado exictosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Hubo un error", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody OferenteData oferenteData){
        try{
            Oferente oferente = oferenteMapper.toOferente(oferenteData);
            Oferente usuarioValidadoActualizado = oferenteUseCase.actualizarOferente(oferente);
            return new ResponseEntity<>(usuarioValidadoActualizado, HttpStatus.OK);
        } catch (Exception error){
            return ResponseEntity.notFound().build();
        }
    }

}
