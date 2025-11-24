package com.eventia.auth.aplication.config;

import com.eventia.auth.domain.model.gateway.*;
import com.eventia.auth.domain.usecase.AdminUseCase;
import com.eventia.auth.domain.usecase.ClienteUseCase;
import com.eventia.auth.domain.usecase.OferenteUseCase;
import com.eventia.auth.domain.usecase.UsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
        @Bean
        public UsuarioUseCase usuarioUseCase(UsuarioGateway usuarioGateway, EncrypterGateway encrypterGatway){
            return new UsuarioUseCase(usuarioGateway, encrypterGatway);
        }
        @Bean
        public ClienteUseCase clienteUseCase(ClienteGateway clienteGateway,EncrypterGateway encrypterGateway, NotificationGateway notificationGateway) {
            return new ClienteUseCase(clienteGateway, encrypterGateway, notificationGateway);
        }

        @Bean
        public OferenteUseCase oferenteUseCase(OferenteGateway oferenteGateway,EncrypterGateway encrypterGateway, NotificationGateway notificationGateway) {
            return new OferenteUseCase(oferenteGateway, encrypterGateway, notificationGateway);
        }

        @Bean
        public AdminUseCase adminUseCase(UsuarioGateway usuarioGateway,ServicioGateway servicioGateway,EncrypterGateway encrypterGateway, AdminGateway adminGateway) {
            return new AdminUseCase(usuarioGateway, adminGateway , encrypterGateway, servicioGateway);
        }

}

