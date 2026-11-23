package com.inventario.gestion.config;

import com.inventario.gestion.enums.RolEnum;

public class ConstantesErrorResponse {

    // PRODUCTO
    public static final String PRODCUTO_NO_ENCONTRADO = "No se ha encontrado el producto.";
    public static final String PRODUCTO_EXISTENTE = "Ya existe un producto con ese nombre.";

    // ROL
    public static final String ROL_NO_ENCONTRADO = "No se ha encontrado el rol.";

    // TIPO PRODUCTO
    public static final String TIPO_PRODUCTO_NO_ENCONTRADO = "No se ha encontrado el tipo de producto.";
    public static final String TIPO_PRODUCTO_EXISTENTE = "Ya existe un tipo de producto con ese nombre.";

    // USUARIO ROL
    public static final String ASIGNACIONES_NO_ENCONTRADAS = "No se han encontrado asignaciones.";

    // USUARIO
    public static final String CREDENCIALES_EXISTENTES = "El nombre de usuario o email ya existen.";
    public static final String USUARIO_NO_ENCONTRADO = "El nombre de usuario o email ya existen.";

    // JWT 
    public static final String TOKEN_INVALIDO = "El token JWT no es válido o ha expirado.";
    public static final String TOKEN_SIN_AUTHORITIES = "El token no contiene authorities.";
    public static final String UTF = "UTF-8";


    // ROLES
    public static final String ADMIN = RolEnum.ROLE_ADMINISTRADOR.nombreSinPrefijo();
    public static final String GESTION_INVENTARIO = RolEnum.ROLE_GESTION_INVENTARIO.nombreSinPrefijo();
    public static final String USUARIO = RolEnum.ROLE_USUARIO.nombreSinPrefijo();
}
