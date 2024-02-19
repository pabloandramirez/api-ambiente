package ar.gob.chaco.subseambiente.noticias.mapper.noticia.impl;

import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.mapper.noticia.NoticiaMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;

import java.time.LocalDate;
import java.util.UUID;

public class NoticiaMapperImpl implements NoticiaMapper {
    @Override
    public Noticia noticiaDTOtoNoticia(NoticiaDTO noticiaDTO) {
        return Noticia.builder()
                .uuid(UUID.randomUUID())
                .titulo(noticiaDTO.getTitulo())
                .subtitulo(noticiaDTO.getSubtitulo())
                .contenido(noticiaDTO.getContenido())
                .imagenesUrl(noticiaDTO.getImagenesUrl())
                .fechaPublicacion(LocalDate.now())
                .build();
    }

    @Override
    public NoticiaDTO noticiaToNoticiaDTO(Noticia noticia) {
        return NoticiaDTO.builder()
                .titulo(noticia.getTitulo())
                .subtitulo(noticia.getSubtitulo())
                .contenido(noticia.getContenido())
                .imagenesUrl(noticia.getImagenesUrl())
                .fechaPublicacion(getLocalDate(noticia.getFechaPublicacion()))
                .build();
    }

    private String getLocalDate(LocalDate localDate){
        return localDate.getYear() +
                "/" +
                localDate.getMonthValue() +
                "/" +
                localDate.getDayOfYear();
    }
}
