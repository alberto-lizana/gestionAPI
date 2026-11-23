package com.inventario.gestion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductoRequestNombreDto(

    @NotNull(message = "El id del producto es obligatorio")
    @Min(1)
    Long idProducto,

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(min = 3, max = 125, message = "El nombre debe tener entre 3 y 125 caracteres")
    String nombre

    
) {}
