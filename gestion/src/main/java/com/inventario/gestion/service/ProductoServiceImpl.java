package com.inventario.gestion.service;

import static com.inventario.gestion.config.ConstantesErrorResponse.PRODCUTO_NO_ENCONTRADO;
import static com.inventario.gestion.config.ConstantesErrorResponse.PRODUCTO_EXISTENTE;
import static com.inventario.gestion.config.ConstantesErrorResponse.TIPO_PRODUCTO_NO_ENCONTRADO;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.gestion.dto.ProductoRequesDto;
import com.inventario.gestion.dto.ProductoResponseDto;
import com.inventario.gestion.exception.NoSeEncontroElTipoProductoException;
import com.inventario.gestion.exception.ProductoExistenteException;
import com.inventario.gestion.exception.ProductoNoEncontradoException;
import com.inventario.gestion.mappers.ProductoMapper;
import com.inventario.gestion.model.Producto;
import com.inventario.gestion.model.TipoProducto;
import com.inventario.gestion.repository.ProductoRepository;
import com.inventario.gestion.repository.TipoProductoRepository;

@Service 
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final TipoProductoRepository tipoProductoRepository;

    public ProductoServiceImpl(
        ProductoRepository productoRepository, 
        TipoProductoRepository tipoProductoRepository
    ){
        this.productoRepository = productoRepository;
        this.tipoProductoRepository = tipoProductoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductoResponseDto> obtenerTodos() {
        return productoRepository.obtenerTodos()
            .stream()
            .map(ProductoMapper::toDto)
            .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ProductoResponseDto obtenerPorId(Long id) {
        return ProductoMapper.toDto(productoRepository.obtenerPorId(id)
            .orElseThrow(() -> new ProductoNoEncontradoException(PRODCUTO_NO_ENCONTRADO)));
    }

    @Transactional
    @Override
    public ProductoResponseDto crear(ProductoRequesDto dto) {
        
        if(productoRepository.existsByNombre(dto.nombre())){
            throw new ProductoExistenteException(PRODUCTO_EXISTENTE);
        }

        TipoProducto tp = tipoProductoRepository
            .obtenerPorNombre(dto.tipoProducto().getNombre().toLowerCase())
                .orElseThrow(() -> new NoSeEncontroElTipoProductoException(TIPO_PRODUCTO_NO_ENCONTRADO));

        return ProductoMapper.toDto(productoRepository.save(Producto
            .builder()
                .nombre(dto.nombre())
                .precio(dto.precio())
                .stock(dto.stock())
                .tipoProducto(tp)
            .build())
            );
    }

    @Transactional
    @Override
    public void borrar(Long id) {
        if(productoRepository.borrarPorId(id) == 0){
            throw new ProductoNoEncontradoException(PRODCUTO_NO_ENCONTRADO);
        }
    }

}
