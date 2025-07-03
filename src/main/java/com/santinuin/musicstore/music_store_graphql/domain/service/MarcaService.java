package com.santinuin.musicstore.music_store_graphql.domain.service;

import com.santinuin.musicstore.music_store_graphql.application.dto.output.MarcaDTO;

import java.util.List;
import java.util.Optional;

public interface MarcaService {
    List<MarcaDTO> findAll();
    Optional<MarcaDTO> findById(Long id);
    List<MarcaDTO> findByNombre(String nombre);
    List<MarcaDTO> findByPais(String pais);
    MarcaDTO create(MarcaDTO marcaDTO);
    Optional<MarcaDTO> update(Long id, MarcaDTO marcaDTO);
    boolean delete(Long id);
}

