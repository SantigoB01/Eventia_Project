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
    public Oferente registroOferente(Oferente oferente) {
        OferenteData oferenteData = oferenteMapper.toData(oferente);
        return oferenteMapper.toOferente(repository.save (oferenteData));
    }

    @Override
    public Oferente verPerfil(Oferente oferente) {
        return repository.findById(oferente.getId_Usuario())
                .map(oferenteData -> oferenteMapper.toOferente(oferenteData))
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

    }

    @Override
    public Oferente editarCliente(Oferente oferente) {
        return null;
    }

    @Override
    public boolean desactivarCuenta(Long id_Oferente) {
        return false;
    }
}
