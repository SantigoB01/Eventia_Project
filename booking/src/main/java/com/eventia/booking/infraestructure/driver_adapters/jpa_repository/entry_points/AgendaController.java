package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points;

import com.eventia.booking.domain.model.Agenda;
import com.eventia.booking.domain.model.UseCase.AgendaUseCase;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.*;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.request.AgendaRequestDTO;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.entry_points.dto.response.AgendaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaUseCase useCase;

    @PostMapping
    public AgendaResponseDTO crear(@RequestBody AgendaRequestDTO request) {
        Agenda agenda = useCase.crear(
                Agenda.builder()
                        .idServicio(request.getIdServicio())
                        .fechaReserva(request.getFechaReserva())
                        .disponibilidad(request.getDisponibilidad())
                        .horaInicio(request.getHoraInicio())
                        .build()
        );
        return AgendaResponseDTO.builder()
                .idAgenda(agenda.getIdAgenda())
                .idServicio(agenda.getIdServicio())
                .fechaReserva(agenda.getFechaReserva())
                .disponibilidad(agenda.getDisponibilidad())
                .horaInicio(agenda.getHoraInicio())
                .build();
    }

    @GetMapping("/servicio/{idServicio}")
    public List<Agenda> listarPorServicio(@PathVariable Long idServicio) {
        return useCase.obtenerPorServicio(idServicio);
    }

    @DeleteMapping("/{idAgenda}")
    public void eliminar(@PathVariable Long idAgenda) {
        useCase.eliminar(idAgenda);
    }
}
