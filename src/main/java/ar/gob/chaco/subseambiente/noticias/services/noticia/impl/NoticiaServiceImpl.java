package ar.gob.chaco.subseambiente.noticias.services.noticia.impl;

import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.mapper.noticia.NoticiaMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import ar.gob.chaco.subseambiente.noticias.repository.noticia.NoticiaRepository;
import ar.gob.chaco.subseambiente.noticias.services.noticia.NoticiaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoticiaServiceImpl implements NoticiaService {

    private final NoticiaRepository noticiaRepository;

    private final NoticiaMapper noticiaMapper;

    @Override
    public Noticia crearNoticia(NoticiaDTO noticiaDTO) {
        Noticia noticiaCreada = noticiaMapper.noticiaDTOtoNoticia(noticiaDTO);
        return noticiaRepository.save(noticiaCreada);
    }
}
