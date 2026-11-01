package com.inventario.gestion.exception;

public class TipoProductoYaExisteException extends RuntimeException {

    public TipoProductoYaExisteException(String message){
        super(message);
    }
    
}
