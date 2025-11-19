package com.eventia.auth.aplication;

import com.eventia.auth.domain.model.gateway.EncrypterGateway;
import com.eventia.auth.domain.model.gateway.UsuarioGateway;
import com.eventia.auth.domain.usecase.UsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public UsuarioUseCase usuarioUseCase(UsuarioGateway usuarioGateway, EncrypterGateway encrypterGatway){
        return new UsuarioUseCase(usuarioGateway, encrypterGatway);
    }
}
