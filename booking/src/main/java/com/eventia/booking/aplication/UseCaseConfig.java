package com.eventia.booking.aplication;

import com.eventia.booking.domain.model.UseCase.BookingUseCase;
import com.eventia.booking.domain.model.gateway.BookingGateway;
import com.eventia.booking.domain.model.gateway.ServicioGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UseCaseConfig {
    @Bean
    public BookingUseCase BookingUseCase(BookingGateway bookingGateway, ServicioGateway servicioGateway){
        return new BookingUseCase(bookingGateway,servicioGateway);
    }
}