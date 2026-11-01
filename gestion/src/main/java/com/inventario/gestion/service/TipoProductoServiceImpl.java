package com.inventario.gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.gestion.dto.TipoProductoRequestDto;
import com.inventario.gestion.dto.TipoProductoResponseDto;
import com.inventario.gestion.exception.NoSeEncontroElTipoProductoException;
import com.inventario.gestion.exception.TipoProductoYaExisteException;
import com.inventario.gestion.mappers.TipoProductoMapper;
import com.inventario.gestion.model.TipoProducto;
import com.inventario.gestion.repository.TipoProductoRepository;

import static com.inventario.gestion.config.ConstantesErrorResponse.TIPO_PRODUCTO_NO_ENCONTRADO;
import static com.inventario.gestion.config.ConstantesErrorResponse.TIPO_PRODUCTO_EXISTENTE;

@Service 
public class TipoProductoServiceImpl implements TipoProductoService {

    private final TipoProductoRepository tipoProductoRepository;

    public TipoProductoServiceImpl(TipoProductoRepository tipoProductoRepository){
        this.tipoProductoRepository = tipoProductoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TipoProductoResponseDto> obtenerTodos() {
        return tipoProductoRepository.findAll()
            .stream()
            .map(TipoProductoMapper::toDto)
            .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public TipoProductoResponseDto obtenerPorId(Long id) {
        return TipoProductoMapper.toDto(tipoProductoRepository.findById(id).orElseThrow(() -> 
                new NoSeEncontroElTipoProductoException(TIPO_PRODUCTO_NO_ENCONTRADO)));
    }

    @Transactional
    @Override
    public TipoProductoResponseDto crear(TipoProductoRequestDto dto) {

        if (tipoProductoRepository.existsByNombre(dto.nombre())) {
            throw new TipoProductoYaExisteException(TIPO_PRODUCTO_EXISTENTE);
        }

        return TipoProductoMapper.toDto(tipoProductoRepository.save(TipoProducto
            .builder()
                .nombre(dto.nombre())
                .descripcion(dto.descripcion())
            .build()));
    }

    @Transactional 
    @Override
    public void borrar(Long id) {
        if(tipoProductoRepository.deleteByIdTipoProducto(id) == 0){
            throw new NoSeEncontroElTipoProductoException(TIPO_PRODUCTO_NO_ENCONTRADO);
        }
    }
}
