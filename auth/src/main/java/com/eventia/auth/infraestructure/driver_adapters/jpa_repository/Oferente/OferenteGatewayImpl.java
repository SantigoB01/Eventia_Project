package com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Oferente;

import com.eventia.auth.domain.model.Oferente;
import com.eventia.auth.domain.model.gateway.OferenteGateway;
import com.eventia.auth.infraestructure.mapper.OferenteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OferenteGatewayImpl implements OferenteGateway {

    private final OferenteMapper oferenteMapper;
    private final OferenteDataJpaRepository repository;

    @Override
    public Oferente guardarOferente(Oferente oferente) {
        OferenteData oferenteData = oferenteMapper.toData(oferente);
        return oferenteMapper.toOferente(repository.save(oferenteData));
    }

    @Override
    public void eliminarOferente(Long idUsuario) {
        repository.deleteById(idUsuario);
    }

    @Override
    public Oferente buscarPorId(Long idUsuario) {
        return repository.findById(idUsuario)
                .map(oferenteMapper::toOferente)
                .orElse(null);
    }

    @Override
    public Oferente actualizarOferente(Oferente oferente) {
        if (!repository.existsById(oferente.getId_Usuario())) {
            return null;
        }
        OferenteData data = oferenteMapper.toData(oferente);
        return oferenteMapper.toOferente(repository.save(data));
    }

    @Override
    public boolean cambioEstadoCuenta(Long idOferente) {
        OferenteData oferenteData = repository.findById(idOferente)
                .orElseThrow(() -> new RuntimeException("El oferente no existe"));

        boolean nuevoEstado = !oferenteData.getActivo();
        oferenteData.setActivo(nuevoEstado);
        repository.save(oferenteData);

        return nuevoEstado;
    }

}
