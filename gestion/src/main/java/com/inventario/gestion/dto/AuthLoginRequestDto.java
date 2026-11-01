package com.inventario.gestion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder 
public record AuthLoginRequestDto(

    @NotBlank(message = "Se requiere el nombre de usuario.")
    String username,

    @NotBlank(message = "Se requiere la contraseña.")
    String password

) {}
