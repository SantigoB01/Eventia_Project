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
        return oferenteMapper.toOferente(repository.save (oferenteData));
    }

    @Override
    public void eliminarOferente(Long id_Usuario) {

    }

    @Override
    public Oferente buscarPorId(Long id_Usuario) {
        return null;
    }

    @Override
    public Oferente actualizarOferente(Oferente oferente) {
        return null;
    }


    @Override
    public boolean cambioEstadoCuenta(Long id_Oferente) {
        return false;
    }
}
