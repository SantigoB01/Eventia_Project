package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;

import com.eventia.booking.domain.model.enums.CiudadSumapaz;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ciudad")
public class CiudadSumapazController {
    
    @GetMapping
    public CiudadSumapaz[] getCiudadSumapaz(){
        return CiudadSumapaz.values();
    }
}
