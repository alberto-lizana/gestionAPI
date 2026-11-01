package com.inventario.gestion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TipoProductoRequestDto(

    @NotBlank(message = "El nombre del tipo producto es obligatorio")
    @Size(min=3, max= 125,  message = "el nombre del tipo producto debe tener entre 3 a 125 caracteres")
    String nombre,

    @Size(max= 255,  message = "La descripcion no puede contener más de 255 caracteres")
    String descripcion

) {}
