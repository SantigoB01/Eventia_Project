package com.eventia.auth.domain.model.gateway;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.domain.model.Oferente;

public interface OferenteGateway {
        public Oferente registroOferente(Oferente oferente);
    public Oferente verPerfil(Oferente oferente);
    public Oferente editarCliente(Oferente oferente);
    public boolean desactivarCuenta(Long id_Oferente);
}
