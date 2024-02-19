package ar.gob.chaco.subseambiente.noticias.services.noticia;

import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import org.springframework.web.bind.annotation.RequestBody;

public interface NoticiaService {

    //POST
    Noticia crearNoticia(@RequestBody Noticia noticia);



}
