package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;

import com.eventia.booking.domain.model.enums.TipoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipos_servicios")
@RequiredArgsConstructor
public class TipoServicioController {

    @GetMapping
    public TipoServicio[] getTipoServicio() {
        return TipoServicio.values();
    }
}