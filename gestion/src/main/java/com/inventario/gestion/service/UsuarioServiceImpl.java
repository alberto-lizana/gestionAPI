package com.inventario.gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.gestion.dto.UsuarioRequestDto;
import com.inventario.gestion.dto.UsuarioResponseDto;
import com.inventario.gestion.enums.RolEnum;
import com.inventario.gestion.exception.RolNoEncontradoException;
import com.inventario.gestion.exception.UsuarioNoEncontradoException;
import com.inventario.gestion.mappers.UsuarioMapper;
import com.inventario.gestion.model.Rol;
import com.inventario.gestion.model.Usuario;
import com.inventario.gestion.model.UsuarioRol;
import com.inventario.gestion.repository.RolRepository;
import com.inventario.gestion.repository.UsuarioRepository;
import com.inventario.gestion.repository.UsuarioRolRepository;
import com.inventario.gestion.security.PasswordConfig;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final PasswordConfig passwordConfig;

    public UsuarioServiceImpl(
        UsuarioRepository usuarioRepository,
        RolRepository rolRepository,
        UsuarioRolRepository usuarioRolRepository,
        PasswordConfig passwordConfig
    ){
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.rolRepository = rolRepository;
        this.passwordConfig = passwordConfig;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsuarioResponseDto> obtenerTodos() {
        return usuarioRepository.findAllConRoles()
            .stream()
            .map(UsuarioMapper::toDto)
            .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioResponseDto obtenerPorId(Long id) {
        return UsuarioMapper.toDto(usuarioRepository.findByIdUsuario(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("No se ha encontrado al usuario")));
    }

    @Transactional
    @Override
    public UsuarioResponseDto crear(UsuarioRequestDto dto) {

        Rol rolDefecto = rolRepository.findRolByNombre(RolEnum.ROLE_USUARIO.name())
            .orElseThrow(() -> new RolNoEncontradoException("No se encontró el rol"));

        Usuario nuevoUsuario = Usuario
            .builder()
                .username(dto.username())
                .password(passwordConfig.passwordEncoder().encode(dto.password()))
                .nombre(dto.nombre())
                .appat(dto.appat())
                .email(dto.email())
            .build();

        nuevoUsuario = usuarioRepository.save(nuevoUsuario);

        UsuarioRol asignacion = UsuarioRol
            .builder()
                .usuario(nuevoUsuario)
                .rol(rolDefecto)
            .build();
        
        usuarioRolRepository.save(asignacion);

        return UsuarioMapper.toDto(nuevoUsuario);
    }

    @Transactional 
    @Override
    public UsuarioResponseDto borrarLogico(Long id) {
        Usuario response = usuarioRepository.findByIdUsuario(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("No se ha encontrado al usuario"));
        
        response.setIsActive(false);
        
        return UsuarioMapper.toDto(response);
    }

    @Transactional
    @Override
    public void borrarPorId(Long id) {
        if (usuarioRepository.deleteByIdUsuario(id) == 0) {
            throw new UsuarioNoEncontradoException("No se ha encontrado al usuario con el id %s".formatted(id));
        }
    }
}
