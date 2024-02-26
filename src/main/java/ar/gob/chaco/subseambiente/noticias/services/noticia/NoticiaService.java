package ar.gob.chaco.subseambiente.noticias.services.noticia;

import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoticiaService {

    //POST
    Noticia crearNoticia(@RequestBody NoticiaDTO noticiaDTO);

    //GET
    List<NoticiaDTO> getNoticias();

    List<NoticiaDTO> getNoticiasPorTitulo(String titulo);

    Optional<NoticiaDTO> getNoticiaPorId(UUID idNoticia);

    Optional<NoticiaDTO> getNoticiaPorLong(Long idLong);

    //PUT
    Optional<NoticiaDTO> actualizarNoticia(UUID idNoticia, NoticiaDTO noticiaActualizadaDTO);

    //DELETE
    boolean borrarNoticia(UUID idNoticia);
}
