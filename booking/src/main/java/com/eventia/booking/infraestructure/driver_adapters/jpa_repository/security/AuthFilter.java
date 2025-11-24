package com.eventia.booking.infraestructure.driver_adapters.jpa_repository.security;

import com.eventia.booking.aplication.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

public class AuthFilter extends OncePerRequestFilter {

    private final AuthService authServiceClient;

    public AuthFilter(AuthService authServiceClient) {
        this.authServiceClient = authServiceClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            try {
                String base64Credentials = authHeader.substring(6);
                String credentials = new String(Base64.getDecoder().decode(base64Credentials));
                String[] values = credentials.split(":", 2);
                String username = values[0];
                String password = values[1];

                // Verificar contra Auth Service
                boolean isAuthenticated = authServiceClient.verificarAutenticacion(username, password);

                if (isAuthenticated) {
                    // Crear autenticación básica para Spring Security
                    var authentication = org.springframework.security.authentication.UsernamePasswordAuthenticationToken.authenticated(
                            username, password, java.util.Collections.emptyList()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}