package com.inventario.gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.gestion.dto.RolNombreRequestDto;
import com.inventario.gestion.dto.RolResponseDto;
import com.inventario.gestion.exception.RolNoEncontradoException;
import com.inventario.gestion.mappers.RolMapper;
import com.inventario.gestion.model.Rol;
import com.inventario.gestion.repository.RolRepository;

@Service 
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository){
        this.rolRepository = rolRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RolResponseDto> obtenerTodos() {
        return rolRepository.findAll()
            .stream()
            .map(RolMapper::toDto)
            .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public RolResponseDto obtenerPorId(Long id) {
        return RolMapper.toDto(rolRepository.findById(id)
            .orElseThrow(() -> new RolNoEncontradoException("Rol no encontrado"))
        );
    }

    @Transactional
    @Override
    public RolResponseDto modificarNombre(RolNombreRequestDto dto) {
        Rol rol = rolRepository.findById(dto.idRol())
            .orElseThrow(() -> new RolNoEncontradoException("Rol no encontrado"));

        rol.setNombre(dto.nombre());

        return RolMapper.toDto(rol);
    }


    @Override
    public void eliminar(Long id) {
        if(rolRepository.deleteByIdRol(id) == 0){
            throw new RolNoEncontradoException("Rol no encontrado");
        }
    }
}


