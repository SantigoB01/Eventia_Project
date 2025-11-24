package com.eventia.auth.aplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos para verificación de otros servicios
                        .requestMatchers("/api/auth/verify").permitAll()
                        .requestMatchers("/api/auth/verificar-rol").permitAll()

                        // Endpoints de administración
                        .requestMatchers("/api/eventia/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/eventia/cliente/**").hasAnyRole("ADMIN", "CLIENTE")
                        .requestMatchers("/api/eventia/oferente/**").hasAnyRole("ADMIN", "OFERENTE")

                        // Cualquier otro endpoint requiere autenticación
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}