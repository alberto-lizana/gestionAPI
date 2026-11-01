package com.inventario.gestion.security;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.gestion.config.AdminProperties;
import com.inventario.gestion.enums.RolEnum;
import com.inventario.gestion.model.Rol;
import com.inventario.gestion.model.Usuario;
import com.inventario.gestion.model.UsuarioRol;
import com.inventario.gestion.repository.RolRepository;
import com.inventario.gestion.repository.UsuarioRepository;
import com.inventario.gestion.repository.UsuarioRolRepository;

@Component
public class AdminInitializer implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final AdminProperties adminProperties;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRolRepository usuarioRolRepository;


    public AdminInitializer(
        UsuarioRepository usuarioRepository,
        AdminProperties adminProperties,
        PasswordEncoder passwordEncoder,
        RolRepository rolRepository,
        UsuarioRolRepository usuarioRolRepository
    ){
        this.usuarioRepository = usuarioRepository;
        this.adminProperties = adminProperties;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
        this.usuarioRolRepository = usuarioRolRepository;
    }

    @Transactional 
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Map<String, Rol> rolesPorNombre = rolRepository.obtenerTodosLosRoles()
            .stream()
            .collect(Collectors.toMap(Rol::getNombre, Function.identity()));

        Rol rolAdministrador = rolesPorNombre.get(RolEnum.ROLE_ADMINISTRADOR.name());

        Rol rolModerador = rolesPorNombre.get(RolEnum.ROLE_GESTION_INVENTARIO.name());

        if (rolAdministrador == null || rolModerador == null) {
            throw new IllegalStateException("No se encontraron todos los roles necesarios");
        }

        if (usuarioRepository.existsByEmail(adminProperties.email())) {
            return;
        }

        Usuario admin = Usuario
            .builder()
                .username(adminProperties.username())
                .password(passwordEncoder.encode(adminProperties.password()))
                .nombre(adminProperties.nombre())
                .appat(adminProperties.appat())
                .email(adminProperties.email())
                .isActive(true)
            .build();

        usuarioRepository.save(admin);

        UsuarioRol asignacionAdmin = UsuarioRol
            .builder()
                .rol(rolAdministrador)
                .usuario(admin)
            .build();

        UsuarioRol asignacionGestionInventario = UsuarioRol
            .builder()
                .rol(rolModerador)
                .usuario(admin)
            .build();

        usuarioRolRepository.save(asignacionAdmin);
        usuarioRolRepository.save(asignacionGestionInventario);

        admin.setAsignacionesRoles(List.of(asignacionAdmin, asignacionGestionInventario));
        
    }

}
