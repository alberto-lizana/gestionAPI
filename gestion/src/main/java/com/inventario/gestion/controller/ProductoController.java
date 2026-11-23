package com.inventario.gestion.controller;


import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.gestion.dto.ProductoModificarDto;
import com.inventario.gestion.dto.ProductoRequesDto;
import com.inventario.gestion.dto.ProductoRequestNombreDto;
import com.inventario.gestion.dto.ProductoRequestPrecioDto;
import com.inventario.gestion.dto.ProductoRequestStockDto;
import com.inventario.gestion.dto.ProductoRequestTipoProductoDto;
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
    ResponseEntity<Page<ProductoResponseDto>> obtenerTodos(
        @PageableDefault(
            page = 0,
            size = 5,
            sort = "id",
            direction = Sort.Direction.ASC
        ) Pageable pageable) {
        return ResponseEntity.ok().body(productoService.obtenerTodos(pageable));
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


    @PutMapping("/modificar")
    ResponseEntity<ProductoResponseDto> modificar(@Valid @RequestBody ProductoModificarDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.modificar(dto));
    }

    @PatchMapping("/modificar-nombre")
    ResponseEntity<ProductoResponseDto> modificarNombre(@Valid @RequestBody ProductoRequestNombreDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.modificarNombre(dto));
    }

    @PatchMapping("/modificar-precio")
    ResponseEntity<ProductoResponseDto> modificarPrecio(@Valid @RequestBody ProductoRequestPrecioDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.modificarPrecio(dto));
    }

    @PatchMapping("/modificar-stock")
    ResponseEntity<ProductoResponseDto> modificarStock(@Valid @RequestBody ProductoRequestStockDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.modificarStock(dto));
    }

    @PatchMapping("/modificar-tipo_producto")
    ResponseEntity<ProductoResponseDto> modificarTipoProducto(@Valid @RequestBody ProductoRequestTipoProductoDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.modificarTipoProducto(dto));
    }


}

