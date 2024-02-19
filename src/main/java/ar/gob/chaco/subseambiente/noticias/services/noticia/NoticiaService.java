package ar.gob.chaco.subseambiente.noticias.services.noticia;

import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface NoticiaService {

    //POST
    Noticia crearNoticia(@RequestBody NoticiaDTO noticiaDTO);



}
