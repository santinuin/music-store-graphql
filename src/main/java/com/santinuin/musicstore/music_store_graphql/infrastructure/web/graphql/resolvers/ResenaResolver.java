package com.santinuin.musicstore.music_store_graphql.infrastructure.web.graphql.resolvers;

import com.santinuin.musicstore.music_store_graphql.application.dto.output.ResenaDTO;
import com.santinuin.musicstore.music_store_graphql.application.dto.input.ResenaInput;
import com.santinuin.musicstore.music_store_graphql.domain.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ResenaResolver {

    @Autowired
    private ResenaService resenaService;

    @QueryMapping
    public List<ResenaDTO> resenas() {
        return resenaService.findAll();
    }

    @QueryMapping
    public ResenaDTO resena(@Argument Long id) {
        return resenaService.findById(id).orElse(null);
    }

    @QueryMapping
    public List<ResenaDTO> resenasPorInstrumento(@Argument Long instrumentoId) {
        return resenaService.findByInstrumento(instrumentoId);
    }

    @MutationMapping
    public ResenaDTO crearResena(@Argument ResenaInput input) {
        ResenaDTO dto = mapToDTO(input);
        return resenaService.create(dto);
    }

    @MutationMapping
    public ResenaDTO actualizarResena(@Argument Long id, @Argument ResenaInput input) {
        ResenaDTO dto = mapToDTO(input);
        return resenaService.update(id, dto).orElse(null);
    }

    @MutationMapping
    public Boolean eliminarResena(@Argument Long id) {
        return resenaService.delete(id);
    }

    private ResenaDTO mapToDTO(ResenaInput input) {
        ResenaDTO dto = new ResenaDTO();
        dto.setUsuario(input.getUsuario());
        dto.setCalificacion(input.getCalificacion());
        dto.setComentario(input.getComentario());
        dto.setInstrumentoId(input.getInstrumentoId());
        return dto;
    }
}

