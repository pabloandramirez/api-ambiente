package ar.gob.chaco.subseambiente.noticias.mapper.noticia.impl;

import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.mapper.noticia.NoticiaMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.OptionalLong;
import java.util.UUID;


@Component
public class NoticiaMapperImpl implements NoticiaMapper {
    @Override
    public Noticia noticiaDTOtoNoticia(NoticiaDTO noticiaDTO) {
        return Noticia.builder()
                .uuid(UUID.randomUUID())
                .titulo(noticiaDTO.getTitulo())
                .subtitulo(noticiaDTO.getSubtitulo())
                .contenido(noticiaDTO.getContenido())
                .imagenesUrl(noticiaDTO.getImagenesUrl())
                .fechaPublicacion(LocalDateTime.now())
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
                .identificador(noticia.getId())
                .build();
    }

    private String getLocalDate(LocalDateTime localDateTime){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return localDateTime.format(formato);
    }
}
