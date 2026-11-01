package com.inventario.gestion.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.gestion.exception.AsignacionesNoEncontradasException;
import com.inventario.gestion.repository.UsuarioRolRepository;

import static com.inventario.gestion.config.ConstantesErrorResponse.ASIGNACIONES_NO_ENCONTRADAS;

@Service 
public class UsuarioRolServiceImpl implements UsuarioRolService {

    private final UsuarioRolRepository usuarioRolRepository;

    public UsuarioRolServiceImpl(UsuarioRolRepository usuarioRolRepository){
        this.usuarioRolRepository = usuarioRolRepository;
    }

    // Quita todos los roles de un usuario (sin borrar al usuario)
    @Transactional 
    @Override
    public void quitarTodosLosRoles(Long usuarioId) {
        if(usuarioRolRepository.borrarAsignacionesPorUsuarioId(usuarioId) == 0){
            throw new AsignacionesNoEncontradasException(ASIGNACIONES_NO_ENCONTRADAS);
        }
    }

    // Quita un rol específico a un usuario específico
    @Transactional 
    @Override
    public void quitarRol(Long rolId, Long usuarioId) {
        if(usuarioRolRepository.borrarAsignacion(usuarioId, rolId) == 0){
            throw new AsignacionesNoEncontradasException(ASIGNACIONES_NO_ENCONTRADAS);
        }
    }

    // Desasigna un rol de todos los usuarios (sin borrar el rol)
    @Transactional 
    @Override
    public void desasignarRolDeTodosLosUsuarios(Long rolId) {
        if(usuarioRolRepository.borrarAsignacionesPorRolId(rolId) == 0){
            throw new AsignacionesNoEncontradasException(ASIGNACIONES_NO_ENCONTRADAS);
        }
    }

}
