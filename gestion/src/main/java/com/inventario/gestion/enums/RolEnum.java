package com.inventario.gestion.enums;

public enum RolEnum { 
    ROLE_USUARIO, 
    ROLE_ADMINISTRADOR,
    ROLE_GESTION_INVENTARIO;

    public String nombreSinPrefijo() {
        return name().substring(5);
    }

}
