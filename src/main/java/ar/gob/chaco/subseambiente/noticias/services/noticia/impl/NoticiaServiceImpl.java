package ar.gob.chaco.subseambiente.noticias.services.noticia.impl;

import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.mapper.noticia.NoticiaMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import ar.gob.chaco.subseambiente.noticias.repository.noticia.NoticiaRepository;
import ar.gob.chaco.subseambiente.noticias.services.noticia.NoticiaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<NoticiaDTO> getNoticias() {
        List<NoticiaDTO> noticiaDTOList = new ArrayList<>();
        for (Noticia noticia: noticiaRepository.findAll()){
            noticiaDTOList.add(noticiaMapper.noticiaToNoticiaDTO(noticia));
        }
        return noticiaDTOList;
    }

    @Override
    public List<NoticiaDTO> getNoticiasPorTitulo(String titulo) {
        List<NoticiaDTO> noticiaDTOList = new ArrayList<>();
        for (Noticia noticia: noticiaRepository.findAll()){
            if (noticia.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                noticiaDTOList.add(noticiaMapper.noticiaToNoticiaDTO(noticia));
            }
        }
        return noticiaDTOList;
    }
}
