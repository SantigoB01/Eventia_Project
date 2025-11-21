package com.eventia.auth.domain.model.gateway;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.domain.model.Oferente;
import com.eventia.auth.domain.model.Usuario;

public interface OferenteGateway {
    Oferente guardarOferente(Oferente oferente);
    void eliminarOferente(Long id_Usuario);
    Oferente buscarPorId(Long id_Usuario);
    Oferente actualizarOferente(Oferente oferente);
    boolean cambioEstadoCuenta(Long id_Oferente);
}
