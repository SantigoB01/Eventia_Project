package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;

import com.eventia.booking.domain.exception.ServicioNotFoundException;
import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.UseCase.ServicioUseCase;
import com.eventia.booking.domain.model.enums.CiudadSumapaz;
import com.eventia.booking.domain.model.enums.TipoServicio;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.ServiceData;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request.ServiceRequestDTO;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response.BookingResponseDTO;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response.ServiceResponseDTO;
import com.eventia.booking.infraestructure.mapper.ServiceDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
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

        List<Servicio> servicios = servicioUseCase.listarServicios(serviceData);

        if (servicios.isEmpty()){
            throw new ServicioNotFoundException("Servicios inexisistentes");
        }

        List<ServiceResponseDTO> response = servicios.stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/verServicio/{idServicio}")
    public ResponseEntity<ServiceResponseDTO> obtenerPorId(@PathVariable Long idServicio) {
        Servicio servicio = servicioUseCase.obtenerServicioPorId(idServicio);

        if (servicio == null){
            throw new ServicioNotFoundException("El servicio con id '"+idServicio+"' no encontrado");
        }

        return ResponseEntity.ok(mapper.toResponse(servicio));

    }

    @DeleteMapping("/delete/{idServicio}")
    public ResponseEntity<ServiceResponseDTO> eliminar(@PathVariable Long idServicio) {
        servicioUseCase.eliminarServicio(idServicio);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<ServiceResponseDTO>> buscarPorCiudad(@PathVariable CiudadSumapaz ciudad) {
        List<ServiceResponseDTO> lista = servicioUseCase.buscarPorCiudad(ciudad)
                .stream().map(mapper::toResponse).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ServiceResponseDTO>> buscarPorTipo(@PathVariable TipoServicio tipo) {
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