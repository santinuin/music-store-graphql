package com.santinuin.musicstore.music_store_graphql.infrastructure.web.graphql.resolvers;

import com.santinuin.musicstore.music_store_graphql.application.dto.output.CategoriaDTO;
import com.santinuin.musicstore.music_store_graphql.application.dto.input.CategoriaInput;
import com.santinuin.musicstore.music_store_graphql.domain.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoriaResolver {

    @Autowired
    private CategoriaService categoriaService;

    @QueryMapping
    public List<CategoriaDTO> categorias() {
        return categoriaService.findAll();
    }

    @QueryMapping
    public CategoriaDTO categoria(@Argument Long id) {
        return categoriaService.findById(id).orElse(null);
    }

    @MutationMapping
    public CategoriaDTO crearCategoria(@Argument CategoriaInput input) {
        CategoriaDTO dto = mapToDTO(input);
        return categoriaService.create(dto);
    }

    @MutationMapping
    public CategoriaDTO actualizarCategoria(@Argument Long id, @Argument CategoriaInput input) {
        CategoriaDTO dto = mapToDTO(input);
        return categoriaService.update(id, dto).orElse(null);
    }

    private CategoriaDTO mapToDTO(CategoriaInput input) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setNombre(input.getNombre());
        dto.setDescripcion(input.getDescripcion());
        return dto;
    }
}
