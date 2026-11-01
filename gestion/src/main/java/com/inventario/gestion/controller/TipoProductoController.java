package com.inventario.gestion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.gestion.dto.TipoProductoRequestDto;
import com.inventario.gestion.dto.TipoProductoResponseDto;
import com.inventario.gestion.service.TipoProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipo-producto")
public class TipoProductoController {

    private final TipoProductoService tipoProductoService;

    public TipoProductoController(TipoProductoService tipoProductoService){
        this.tipoProductoService = tipoProductoService;
    }

    @GetMapping("/obtener-todos")
    ResponseEntity<List<TipoProductoResponseDto>> obtener(){
        return ResponseEntity.ok().body(tipoProductoService.obtenerTodos());
    }

    @GetMapping("/obtener-{id}")
    ResponseEntity<TipoProductoResponseDto> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(tipoProductoService.obtenerPorId(id));
    }

    @PostMapping("/crear")
    ResponseEntity<TipoProductoResponseDto> crear(@Valid @RequestBody TipoProductoRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoProductoService.crear(dto));
    }

    @DeleteMapping("/borrar-{id}")
    ResponseEntity<Void> borrar(@PathVariable Long id){
        tipoProductoService.borrar(id);
        return ResponseEntity.noContent().build();
    }
}

