package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;

import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.UseCase.ServicioUseCase;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.ServiceData;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request.ServiceRequestDTO;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response.ServiceResponseDTO;
import com.eventia.booking.infraestructure.mapper.ServiceDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/servicios")
@RequiredArgsConstructor
public class ServiceController {

    private final ServicioUseCase servicioUseCase;
    private final ServiceDtoMapper mapper;

    @PostMapping("/crearServicio")
    public ResponseEntity<ServiceResponseDTO> crearServicio(
            @RequestBody ServiceRequestDTO requestDTO) {

        Servicio servicio = mapper.toDomain(requestDTO);
        Servicio creado = servicioUseCase.crearServicio(servicio);

        return ResponseEntity.ok(mapper.toResponse(creado));
    }

    @GetMapping("/verServicios")
    public ResponseEntity<List<ServiceResponseDTO>> obtenerTodos(ServiceData serviceData) {
        List<ServiceResponseDTO> lista = servicioUseCase.listarServicios(serviceData)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{idServicio}")
    public ResponseEntity<ServiceResponseDTO> obtenerPorId(@PathVariable Long idServicio) {
        Servicio servicio = servicioUseCase.obtenerServicioPorId(idServicio);
        return ResponseEntity.ok(mapper.toResponse(servicio));
    }

    @DeleteMapping("/{idServicio}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idServicio) {
        servicioUseCase.eliminarServicio(idServicio);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<ServiceResponseDTO>> buscarPorCiudad(@PathVariable String ciudad) {
        List<ServiceResponseDTO> lista = servicioUseCase.buscarPorCiudad(ciudad)
                .stream().map(mapper::toResponse).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ServiceResponseDTO>> buscarPorTipo(@PathVariable String tipo) {
        List<ServiceResponseDTO> lista = servicioUseCase.buscarPorTipoServicio(tipo)
                .stream().map(mapper::toResponse).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ServiceResponseDTO>> buscarPorOferente(@PathVariable Long idUsuario) {
        List<ServiceResponseDTO> lista = servicioUseCase.buscarPorUsuarioOferente(idUsuario)
                .stream().map(mapper::toResponse).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}