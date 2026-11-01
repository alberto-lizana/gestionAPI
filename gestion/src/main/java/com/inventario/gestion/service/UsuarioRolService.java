package com.inventario.gestion.service;

public interface UsuarioRolService {

    void quitarTodosLosRoles(Long usuarioId);

    void quitarRol(Long usuarioId, Long rolId);

    void desasignarRolDeTodosLosUsuarios(Long rolId);

}
