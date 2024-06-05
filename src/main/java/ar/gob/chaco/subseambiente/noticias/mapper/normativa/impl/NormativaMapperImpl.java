package ar.gob.chaco.subseambiente.noticias.mapper.normativa.impl;

import ar.gob.chaco.subseambiente.noticias.bootstrap.enums.Direccion;
import ar.gob.chaco.subseambiente.noticias.domain.Normativa;
import ar.gob.chaco.subseambiente.noticias.mapper.normativa.NormativaMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.normativa.NormativaDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NormativaMapperImpl implements NormativaMapper {
    @Override
    public Normativa normativaDTOtoNormativa(NormativaDTO normativaDTO) {
        return Normativa.builder()
                .uuid(UUID.randomUUID())
                .titulo(normativaDTO.getTitulo())
                .direccion(getDireccion(normativaDTO.getDireccion()))
                .urlDocumento(normativaDTO.getUrlDocumento())
                .build();
    }

    @Override
    public NormativaDTO normativaToNormativaDTO(Normativa normativa) {
        return NormativaDTO.builder()
                .id(normativa.getUuid().toString())
                .titulo(normativa.getTitulo())
                .direccion(getDireccion(normativa.getDireccion()))
                .urlDocumento(normativa.getUrlDocumento())
                .build();
    }

    private Direccion getDireccion(String direccionString){
        if (!direccionString.isBlank()){
            for (Direccion direccion: Direccion.values()){
                if (direccion.getDireccion().equalsIgnoreCase(direccionString)){
                    return direccion;
                }
            }
        }
        return null;
    }

    private String getDireccion(Direccion direccion){return direccion.getDireccion();}
}
