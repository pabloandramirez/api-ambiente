package ar.gob.chaco.subseambiente.noticias.mapper.noticia.impl;

import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.mapper.noticia.NoticiaMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                .fechaPublicacion(getLocalDate(noticiaDTO.getFechaPublicacionString()))
                .build();
    }

    @Override
    public NoticiaDTO noticiaToNoticiaDTO(Noticia noticia) {
        return NoticiaDTO.builder()
                .id(noticia.getUuid().toString())
                .identificador(noticia.getIdNoticia().getIdentificador())
                .titulo(noticia.getTitulo())
                .subtitulo(noticia.getSubtitulo())
                .contenido(noticia.getContenido())
                .imagenesUrl(noticia.getImagenesUrl())
                .fechaPublicacionString(getLocalDate(noticia.getFechaPublicacion()))
                .fechaPublicacionDate(noticia.getFechaPublicacion())
                .build();
    }

    private String getLocalDate(LocalDate localDate){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return localDate.format(formato);
    }

    private LocalDate getLocalDate(String localDate){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(localDate, formato);
    }
}
