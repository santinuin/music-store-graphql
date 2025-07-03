package com.santinuin.musicstore.music_store_graphql.infrastructure.web.graphql.resolvers;

import com.santinuin.musicstore.music_store_graphql.application.dto.output.MarcaDTO;
import com.santinuin.musicstore.music_store_graphql.application.dto.input.MarcaInput;
import com.santinuin.musicstore.music_store_graphql.application.usecases.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MarcaResolver {

    @Autowired
    private MarcaService marcaService;

    @QueryMapping
    public List<MarcaDTO> marcas() {
        return marcaService.findAll();
    }

    @QueryMapping
    public MarcaDTO marca(@Argument Long id) {
        return marcaService.findById(id).orElse(null);
    }

    @QueryMapping
    public List<MarcaDTO> marcasPorPais(@Argument String pais) {
        return marcaService.findByPais(pais);
    }

    @MutationMapping
    public MarcaDTO crearMarca(@Argument MarcaInput input) {
        MarcaDTO dto = mapToDTO(input);
        return marcaService.create(dto);
    }

    @MutationMapping
    public MarcaDTO actualizarMarca(@Argument Long id, @Argument MarcaInput input) {
        MarcaDTO dto = mapToDTO(input);
        return marcaService.update(id, dto).orElse(null);
    }

    private MarcaDTO mapToDTO(MarcaInput input) {
        MarcaDTO dto = new MarcaDTO();
        dto.setNombre(input.getNombre());
        dto.setPaisOrigen(input.getPaisOrigen());
        return dto;
    }
}
