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

import java.math.BigDecimal;
import java.security.Provider;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/servicios")
@RequiredArgsConstructor
public class ServiceController {

    private final ServicioUseCase servicioUseCase;
    private final ServiceDtoMapper mapper;


    // ---------------------------------------------------------
    // CREAR SERVICIO
    // ---------------------------------------------------------
    @PostMapping("/crearServicio")
    public ResponseEntity<ServiceRequestDTO> crearServicio(
            @RequestBody ServiceRequestDTO requestDTO) {

        if (requestDTO == null) {
            throw new IllegalArgumentException("El cuerpo de la solicitud no puede ser nulo.");
        }

        if (requestDTO.getNombre() == null || requestDTO.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del servicio es obligatorio.");
        }

        if (requestDTO.getDescripcion() == null || requestDTO.getDescripcion().isBlank()) {
            throw new IllegalArgumentException("La descripción del servicio es obligatoria.");
        }

        if ((requestDTO.getTarifaPorHora() == null) || requestDTO.getTarifaPorHora().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero.");
        }

        if (requestDTO.getCiudad() == null) {
            throw new IllegalArgumentException("La ciudad del servicio es obligatoria.");
        }

        if (requestDTO.getTipo() == null) {
            throw new IllegalArgumentException("El tipo de servicio es obligatorio.");
        }

        if (requestDTO.getIdUsuarioOferente() == null || requestDTO.getIdUsuarioOferente() <= 0) {
            throw new IllegalArgumentException("El ID del usuario oferente debe ser válido.");
        }

        Servicio servicio = mapper.toDomain(requestDTO);
        Servicio creado = servicioUseCase.crearServicio(servicio);

        if (creado == null) {
            throw new IllegalStateException("No se pudo crear el servicio.");
        }

        return ResponseEntity.ok(mapper.toResponse(creado));
    }

    @GetMapping("/verServicios")
    public ResponseEntity<List<ServiceResponseDTO>> obtenerTodos() {

        List<Servicio> servicios = servicioUseCase.listarServicios();

        if (servicios == null || servicios.isEmpty()) {
            throw new ServicioNotFoundException("No existen servicios disponibles.");
        }

        List<ServiceResponseDTO> response = servicios.stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/verServicio/{idServicio}")
    public ResponseEntity<ServiceResponseDTO> obtenerPorId(@PathVariable Long idServicio) {

        if (idServicio == null || idServicio <= 0) {
            throw new IllegalArgumentException("El ID del servicio debe ser válido.");
        }

        Servicio servicio = servicioUseCase.obtenerServicioPorId(idServicio);

        if (servicio == null) {
            throw new ServicioNotFoundException("El servicio con id '" + idServicio + "' no fue encontrado.");
        }

        return ResponseEntity.ok(mapper.toResponse(servicio));
    }

    @DeleteMapping("/delete/{idServicio}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idServicio) {

        if (idServicio == null || idServicio <= 0) {
            throw new IllegalArgumentException("El ID del servicio debe ser válido.");
        }

        Servicio servicio = servicioUseCase.obtenerServicioPorId(idServicio);

        if (servicio == null) {
            throw new ServicioNotFoundException("No se puede eliminar. El servicio no existe.");
        }

        servicioUseCase.eliminarServicio(idServicio);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<ServiceResponseDTO>> buscarPorCiudad(@PathVariable CiudadSumapaz ciudad) {

        if (ciudad == null) {
            throw new IllegalArgumentException("La ciudad es obligatoria.");
        }

        List<ServiceResponseDTO> lista = servicioUseCase.buscarPorCiudad(ciudad)
                .stream().map(mapper::toResponse).collect(Collectors.toList());

        if (lista.isEmpty()) {
            throw new ServicioNotFoundException("No existen servicios en la ciudad especificada.");
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ServiceResponseDTO>> buscarPorTipo(@PathVariable TipoServicio tipo) {

        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de servicio es obligatorio.");
        }

        List<ServiceResponseDTO> lista = servicioUseCase.buscarPorTipoServicio(tipo)
                .stream().map(mapper::toResponse).collect(Collectors.toList());

        if (lista.isEmpty()) {
            throw new ServicioNotFoundException("No existen servicios para el tipo especificado.");
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ServiceResponseDTO>> buscarPorOferente(@PathVariable Long idUsuario) {

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("El ID del usuario oferente debe ser válido.");
        }

        List<ServiceResponseDTO> lista = servicioUseCase.buscarPorUsuarioOferente(idUsuario)
                .stream().map(mapper::toResponse).collect(Collectors.toList());

        if (lista.isEmpty()) {
            throw new ServicioNotFoundException("El usuario no tiene servicios registrados.");
        }

        return ResponseEntity.ok(lista);
    }
}
