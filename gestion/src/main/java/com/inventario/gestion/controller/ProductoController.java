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

import com.inventario.gestion.dto.ProductoRequesDto;
import com.inventario.gestion.dto.ProductoResponseDto;
import com.inventario.gestion.service.ProductoService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping("/obtener-todos")
    ResponseEntity<List<ProductoResponseDto>> obtenerTodos(){
        return ResponseEntity.ok().body(productoService.obtenerTodos());
    }

    @GetMapping("/obtener-{id}")
    ResponseEntity<ProductoResponseDto> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(productoService.obtenerPorId(id));
    }

    @PostMapping("/crear")
    ResponseEntity<ProductoResponseDto> crear(@Valid @RequestBody ProductoRequesDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(dto));
    }

    @DeleteMapping("/borrar-{id}")
    ResponseEntity<Void> borrar(@PathVariable Long id){
        productoService.borrar(id);
        return ResponseEntity.noContent().build();
    }

}

