package com.santinuin.musicstore.music_store_graphql.infrastructure.web.graphql.resolvers;

import com.santinuin.musicstore.music_store_graphql.application.dto.InstrumentoCreateDTO;
import com.santinuin.musicstore.music_store_graphql.application.dto.output.InstrumentoDTO;
import com.santinuin.musicstore.music_store_graphql.application.dto.input.InstrumentoInput;
import com.santinuin.musicstore.music_store_graphql.domain.service.InstrumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class InstrumentoResolver {

    @Autowired
    private InstrumentoService instrumentoService;

    @QueryMapping
    public List<InstrumentoDTO> instrumentos(
            @Argument String tipo,
            @Argument Long marcaId,
            @Argument Long categoriaId,
            @Argument BigDecimal precioMin,
            @Argument BigDecimal precioMax
    ) {
        if (tipo != null || marcaId != null || categoriaId != null || precioMin != null || precioMax != null) {
            return instrumentoService.findWithFilters(tipo, marcaId, categoriaId, precioMin, precioMax);
        } else {
            return instrumentoService.findAll();
        }
    }

    @QueryMapping
    public InstrumentoDTO instrumento(@Argument Long id) {
        return instrumentoService.findById(id).orElse(null);
    }

    @MutationMapping
    public InstrumentoDTO createInstrumento(@Argument InstrumentoInput input) {
        InstrumentoCreateDTO dto = mapToCreateDTO(input);
        return instrumentoService.create(dto);
    }

    @MutationMapping
    public InstrumentoDTO updateInstrumento(@Argument Long id, @Argument InstrumentoInput input) {
        InstrumentoCreateDTO dto = mapToCreateDTO(input);
        return instrumentoService.update(id, dto).orElse(null);
    }

    @MutationMapping
    public Boolean deleteInstrumento(@Argument Long id) {
        return instrumentoService.delete(id);
    }

    private InstrumentoCreateDTO mapToCreateDTO(InstrumentoInput input) {
        InstrumentoCreateDTO dto = new InstrumentoCreateDTO();
        dto.setNombre(input.getNombre());
        dto.setTipo(input.getTipo());
        dto.setPrecio(input.getPrecio());
        dto.setDescripcion(input.getDescripcion());
        dto.setStock(input.getStock());
        dto.setCategoriaId(input.getCategoriaId());
        dto.setMarcaId(input.getMarcaId());
        return dto;
    }
}
