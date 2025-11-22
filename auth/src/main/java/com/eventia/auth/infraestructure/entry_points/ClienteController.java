package com.eventia.auth.infraestructure.entry_points;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.domain.usecase.ClienteUseCase;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Cliente.ClienteData;
import com.eventia.auth.infraestructure.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/eventia/cliente")
    @RequiredArgsConstructor
    public class ClienteController {

        private final ClienteMapper clienteMapper;
        private final ClienteUseCase clienteUseCase;

        @PostMapping("/saveCliente")
        public ResponseEntity<Cliente> saveCliente(@RequestBody ClienteData clienteData) {
            Cliente clienteConvertido = clienteMapper.toCliente(clienteData);
            var cliente = clienteUseCase.guardarCliente(clienteConvertido);

            if (clienteConvertido.getNombre() != null) {
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
            return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Cliente> findByIdCliente(@PathVariable Long id) {

            Cliente cliente = clienteUseCase.buscarClientePorId(id);

            if (cliente.getId_Usuario() != null) {
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
            return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteClienteById(@PathVariable Long id) {
            try {
                clienteUseCase.eliminarClientePorId(id);
                return new ResponseEntity<>("Cliente eliminado exitosamente", HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>("Hubo un error", HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping("/update")
        public ResponseEntity<Cliente> actualizarCliente(@RequestBody ClienteData clienteData){
            try{
                Cliente cliente = clienteMapper.toCliente(clienteData);
                Cliente clienteActualizado = clienteUseCase.actualizarCliente(cliente);
                return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
            } catch (Exception error){
                return ResponseEntity.notFound().build();
            }
        }

    }

