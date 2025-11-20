package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceRepository repository;

    @GetMapping
    public List<ServiceEntity> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ServiceEntity getOne(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
    }
}
