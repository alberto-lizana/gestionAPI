package com.inventario.gestion.service;

public interface UsuarioRolService {

    void quitarTodosLosRoles(Long usuarioId);

    void quitarRol(Long rolId, Long usuarioId);

    void desasignarRolDeTodosLosUsuarios(Long rolId);

}
