package com.inventario.gestion.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.inventario.gestion.enums.RolEnum;

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
            .authorizeHttpRequests(auth -> auth
                // AUTH
                .requestMatchers("/login")
                    .permitAll()

                // ROL
                .requestMatchers("/rol/todos")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())

                .requestMatchers("/rol/{id}")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())

                .requestMatchers("/rol/modificar-nombre")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())

                .requestMatchers("/rol/borrar/{id}")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())

                // USUARIO
                .requestMatchers("/usuario/crear")
                    .permitAll()
                
                .requestMatchers("/usuario/obtener")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())
                
                .requestMatchers("/usuario/obtener/{id}")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())

                .requestMatchers("/usuario/perfil")
                    .authenticated()
                
                .requestMatchers("/usuario/borrar-logico/{id}")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())
                
                .requestMatchers("/usuario/borrar/{id}")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())

                // USUARIO-ROL
                .requestMatchers("/usuario-rol/{usuarioId}/roles")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())

                .requestMatchers("/usuario-rol/roles/{rolId}/usuario/{usuarioId}")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())

                .requestMatchers("/usuario-rol/roles/{rolId}/usuarios")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())


                // TIPO PRODUCTO
                .requestMatchers("/tipo-producto/obtener-todos")
                    .hasAnyRole(
                        RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo(),
                        RolEnum.ROLE_GESTION_INVENTARIO.nombreSinPrefijo(),
                        RolEnum.ROLE_USUARIO.nombreSinPrefijo()        
                    )

                .requestMatchers("/tipo-producto/obtener-{id}")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())

                .requestMatchers("/tipo-producto/crear")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())

                .requestMatchers("/tipo-producto/borrar-{id}")
                    .hasRole(RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo())

                // PRODUCTO
                .requestMatchers("/producto/obtener-todos")
                    .hasAnyRole(
                        RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo(),
                        RolEnum.ROLE_GESTION_INVENTARIO.nombreSinPrefijo(),
                        RolEnum.ROLE_USUARIO.nombreSinPrefijo()        
                    )

                .requestMatchers("/producto/obtener-{id}")
                    .hasAnyRole(
                        RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo(),
                        RolEnum.ROLE_GESTION_INVENTARIO.nombreSinPrefijo()
                    )

                .requestMatchers("/producto/crear")
                    .hasAnyRole(
                        RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo(),
                        RolEnum.ROLE_GESTION_INVENTARIO.nombreSinPrefijo()
                    )

                .requestMatchers("/producto/borrar-{id}")
                    .hasAnyRole(
                        RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo(),
                        RolEnum.ROLE_GESTION_INVENTARIO.nombreSinPrefijo()
                    )

                .anyRequest().authenticated()
            );

        return http.build();
    }
}
