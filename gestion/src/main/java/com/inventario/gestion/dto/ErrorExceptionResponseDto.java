package com.inventario.gestion.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ErrorExceptionResponseDto (

    String mensaje,
    List<String> errores,
    Integer status,
    LocalDateTime timestamp,
    String path

) {}
