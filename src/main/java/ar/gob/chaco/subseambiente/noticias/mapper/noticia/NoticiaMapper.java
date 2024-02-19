package ar.gob.chaco.subseambiente.noticias.mapper.noticia;

import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;

public interface NoticiaMapper {

    Noticia noticiaDTOtoNoticia(NoticiaDTO noticiaDTO);

    NoticiaDTO noticiaToNoticiaDTO(Noticia noticia);
}
