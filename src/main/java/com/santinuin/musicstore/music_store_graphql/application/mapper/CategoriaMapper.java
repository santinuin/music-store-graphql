package com.santinuin.musicstore.music_store_graphql.application.mapper;

import com.santinuin.musicstore.music_store_graphql.application.dto.output.CategoriaDTO;
import com.santinuin.musicstore.music_store_graphql.domain.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }

    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) {
            return null;
        }

        Categoria categoria = new Categoria(dto.getNombre(), dto.getDescripcion());
        categoria.setId(dto.getId());
        return categoria;
    }
}
