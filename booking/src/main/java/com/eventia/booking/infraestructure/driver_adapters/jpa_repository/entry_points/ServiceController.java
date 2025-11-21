package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;


import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.UseCase.ServicioUseCase;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.ServiceData;
import com.eventia.booking.infraestructure.mapper.ServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceMapper mapper;
    private final ServicioUseCase servicioUseCase;

    @PostMapping("/newService")
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio) {
        return ResponseEntity.ok(servicioUseCase.crearServicio(servicio));
    }

    @GetMapping("/view")
    public ResponseEntity<List<Servicio>> listarServicios(@PathVariable ServiceData serviceData){
        return ResponseEntity.ok(servicioUseCase.listarServicios());
    }
/// *
    @GetMapping("/{Id_Servicio}")
    public ResponseEntity<Servicio> obtenerServicio(@PathVariable Long Id_Servicio) {
        return servicioUseCase.obtenerServicioPorId(Id_Servicio)
                .map(mapper::)
                .orElse(ResponseEntity.notFound().build());
    }
    *///

    @DeleteMapping("/delete/{Id_Servicio}")
    public ResponseEntity<String> eliminarServicio(@PathVariable Long Id_Servicio){
        try {
            servicioUseCase.eliminarServicio(Id_Servicio);
            return new ResponseEntity<>("Reserva eliminada correcto",HttpStatus.OK)
        } catch (RuntimeException e) {
            return new ResponseEntity<>("ERROR.", HttpStatus.NOT_FOUND);
        }

    }



    }