package com.eventia.review.application;

import com.eventia.review.domain.model.gateway.ClienteGateway;
import com.eventia.review.domain.model.gateway.ReservaGateway;
import com.eventia.review.domain.model.gateway.ReviewGateway;
import com.eventia.review.domain.model.gateway.ServicioGateway;
import com.eventia.review.domain.usecase.ReviewUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ReviewUseCase reviewUseCase (ReviewGateway reviewGateway, ClienteGateway clienteGateway, ReservaGateway reservaGateway, ServicioGateway servicioGateway){
        return new ReviewUseCase(reviewGateway, clienteGateway, reservaGateway, servicioGateway);
    }
}
