package com.inventario.gestion.security;

import static com.inventario.gestion.config.ConstantesErrorResponse.ADMIN;
import static com.inventario.gestion.config.ConstantesErrorResponse.GESTION_INVENTARIO;
import static com.inventario.gestion.config.ConstantesErrorResponse.USUARIO;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.validation.Validator;
import tools.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration){
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean 
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(
        HttpSecurity http,
        AuthenticationManager authenticationManager,
        ObjectMapper objectMapper,
        Validator validator
    ) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilter(new JwtAuthenticationFilter(
                authenticationManager,
                objectMapper,
                validator
            ))
            .addFilterBefore(
                new JwtValidationFilter(),
                UsernamePasswordAuthenticationFilter.class
            )
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                // AUTH
                .requestMatchers("/login").permitAll()

                // ROL
                .requestMatchers("/rol/todos").hasRole(ADMIN)
                .requestMatchers("/rol/{id}").hasRole(ADMIN)
                .requestMatchers("/rol/modificar-nombre").hasRole(ADMIN)
                .requestMatchers("/rol/borrar/{id}").hasRole(ADMIN)
                
                // USUARIO
                .requestMatchers("/usuario/crear").permitAll()
                .requestMatchers("/usuario/obtener").hasRole(ADMIN)
                .requestMatchers("/usuario/obtener/{id}").hasRole(ADMIN)
                .requestMatchers("/usuario/perfil").authenticated()
                .requestMatchers("/usuario/borrar-logico/{id}").hasRole(ADMIN)
                .requestMatchers("/usuario/borrar/{id}").hasRole(ADMIN)

                // USUARIO-ROL
                .requestMatchers("/usuario-rol/{usuarioId}/roles").hasRole(ADMIN)
                .requestMatchers("/usuario-rol/roles/{rolId}/usuario/{usuarioId}").hasRole(ADMIN)
                .requestMatchers("/usuario-rol/roles/{rolId}/usuarios").hasRole(ADMIN)

                // TIPO PRODUCTO
                .requestMatchers("/tipo-producto/obtener-todos").hasAnyRole(ADMIN, GESTION_INVENTARIO, USUARIO)
                .requestMatchers("/tipo-producto/obtener-{id}").hasRole(ADMIN)
                .requestMatchers("/tipo-producto/crear").hasRole(ADMIN)
                .requestMatchers("/tipo-producto/borrar-{id}").hasRole(ADMIN)

                // PRODUCTO
                .requestMatchers("/producto/obtener-todos").hasAnyRole(ADMIN, GESTION_INVENTARIO, USUARIO)
                .requestMatchers("/producto/obtener-{id}").hasAnyRole(ADMIN, GESTION_INVENTARIO)
                .requestMatchers("/producto/crear").hasAnyRole(ADMIN, GESTION_INVENTARIO)
                .requestMatchers("/producto/borrar-{id}").hasAnyRole(ADMIN, GESTION_INVENTARIO)

                .requestMatchers("/producto/modificar-nombre").hasAnyRole(ADMIN, GESTION_INVENTARIO)
                .requestMatchers("/producto/modificar-precio").hasAnyRole(ADMIN, GESTION_INVENTARIO)
                .requestMatchers("/producto/modificar-stock").hasAnyRole(ADMIN, GESTION_INVENTARIO)
                .requestMatchers("/producto/modificar-tipo_producto").hasAnyRole(ADMIN, GESTION_INVENTARIO)
            

                .anyRequest().authenticated()
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // 1. Orígenes autorizados
        configuration.setAllowedOrigins(
            List.of("http://localhost:4200")
        );

        // 2. Métodos HTTP permitidos
        configuration.setAllowedMethods(
            List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        );

        // 3. Encabezados HTTP permitidos
        configuration.setAllowedHeaders(
            List.of("Content-Type", "Authorization")
        );

        // 4. No utilizamos autenticación mediante cookies
        configuration.setAllowCredentials(false);

        // 5. Duración de la autorización preflight
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
