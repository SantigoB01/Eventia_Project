package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;


import com.eventia.booking.domain.model.Servicio;
import com.eventia.booking.domain.model.UseCase.ServicioUseCase;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.ServiceData;
import com.eventia.booking.infraestructure.mapper.ServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceMapper mapper;
    private final ServicioUseCase servicioUseCase;

    @GetMapping
    public ResponseEntity<Servicio> listarServicios(@PathVariable ServiceData serviceData){


    }
}
