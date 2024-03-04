package ar.gob.chaco.subseambiente.noticias.services.noticia.impl;

import ar.gob.chaco.subseambiente.noticias.domain.IdNoticia;
import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.mapper.noticia.NoticiaMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import ar.gob.chaco.subseambiente.noticias.repository.noticia.IdNoticiaRepository;
import ar.gob.chaco.subseambiente.noticias.repository.noticia.NoticiaRepository;
import ar.gob.chaco.subseambiente.noticias.services.noticia.NoticiaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@AllArgsConstructor
public class NoticiaServiceImpl implements NoticiaService {

    private final NoticiaRepository noticiaRepository;

    private final NoticiaMapper noticiaMapper;

    private final IdNoticiaRepository idNoticiaRepository;

    @Override
    public Noticia crearNoticia(NoticiaDTO noticiaDTO) {
        Noticia noticiaCreada = noticiaMapper.noticiaDTOtoNoticia(noticiaDTO);
        IdNoticia idNoticia = idNoticiaRepository.save(IdNoticia.builder().build());
        noticiaCreada.setIdNoticia(idNoticia);
        return noticiaRepository.save(noticiaCreada);
    }

    @Override
    public List<NoticiaDTO> getNoticias() {
        List<NoticiaDTO> noticiaDTOList = new ArrayList<>();
        for (Noticia noticia: noticiaRepository.findAll()){
            noticiaDTOList.add(noticiaMapper.noticiaToNoticiaDTO(noticia));
        }
        noticiaDTOList.sort(Comparator.comparing(NoticiaDTO::getFechaPublicacion).reversed());
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

    @Override
    public Optional<NoticiaDTO> getNoticiaPorId(UUID idNoticia) {
        Optional<Noticia> noticiaOptional = noticiaRepository.findById(idNoticia);
        if (noticiaOptional.isPresent()){
            return Optional.of(noticiaMapper.noticiaToNoticiaDTO(noticiaOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<NoticiaDTO> getNoticiaPorLong(Long idLong) {
        Optional<IdNoticia> idNoticiaOptional = idNoticiaRepository.findByIdentificador(idLong);
        if (idNoticiaOptional.isPresent()){
            return Optional.of(noticiaMapper.noticiaToNoticiaDTO(idNoticiaOptional.get().getNoticia()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<NoticiaDTO> actualizarNoticia(UUID idNoticia, NoticiaDTO noticiaActualizadaDTO) {
        Optional<Noticia> noticiaOptional = noticiaRepository.findById(idNoticia);
        if (noticiaOptional.isPresent()){
            actualizacionNoticia(noticiaOptional.get(), noticiaActualizadaDTO);
            noticiaRepository.saveAndFlush(noticiaOptional.get());
            return Optional.of(noticiaMapper.noticiaToNoticiaDTO(noticiaOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean borrarNoticia(UUID idNoticia) {
        if (noticiaRepository.existsById(idNoticia)){
            noticiaRepository.deleteById(idNoticia);
            Optional<Noticia> noticiaOptional = noticiaRepository.findById(idNoticia);
            if (noticiaOptional.isPresent()){
                idNoticiaRepository.deleteById(noticiaOptional.get().getIdNoticia().getIdentificador());
            }
            return true;
        }
        return false;
    }

    @Override
    public List<NoticiaDTO> getNoticiasPaginadas(int indiceInicio, int noticiasPorPagina) {
        // Obtener todas las noticias desde la base de datos
        List<NoticiaDTO> todasLasNoticias = new ArrayList<>(); // Suponiendo que utilizas Spring Data JPA

        for (Noticia noticia: noticiaRepository.findAll()) {
            todasLasNoticias.add(noticiaMapper.noticiaToNoticiaDTO(noticia));
        }

        todasLasNoticias.sort(Comparator.comparing(NoticiaDTO::getFechaPublicacion).reversed());

        // Calcular el índice final de las noticias en función de la página y la cantidad de noticias por página
        int indiceFinal = Math.min(indiceInicio + noticiasPorPagina, todasLasNoticias.size());

        // Devolver las noticias correspondientes a la página solicitada
        return todasLasNoticias.subList(indiceInicio, indiceFinal);
    }

    private void actualizacionNoticia(Noticia noticia, NoticiaDTO noticiaActualizadaDTO){
        if (noticiaActualizadaDTO.getTitulo() != null && !noticiaActualizadaDTO.getTitulo().isBlank()){
            noticia.setTitulo(noticiaActualizadaDTO.getTitulo());
        }

        if (noticiaActualizadaDTO.getSubtitulo() != null && !noticiaActualizadaDTO.getSubtitulo().isBlank()){
            noticia.setSubtitulo(noticiaActualizadaDTO.getSubtitulo());
        }

        if (noticiaActualizadaDTO.getContenido() != null && !noticiaActualizadaDTO.getContenido().isBlank()){
            noticia.setContenido(noticiaActualizadaDTO.getContenido());
        }

        if (noticiaActualizadaDTO.getImagenesUrl() != null && !noticiaActualizadaDTO.getImagenesUrl().isEmpty()){
            noticia.setImagenesUrl(noticiaActualizadaDTO.getImagenesUrl());
        }

        if (noticiaActualizadaDTO.getFechaPublicacion() != null && !noticiaActualizadaDTO.getFechaPublicacion().isBlank()){
            noticia.setFechaPublicacion(getLocalDate(noticiaActualizadaDTO.getFechaPublicacion()));
        }
    }

    private LocalDateTime getLocalDate(String localDate){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(localDate, formato);
    }
}
