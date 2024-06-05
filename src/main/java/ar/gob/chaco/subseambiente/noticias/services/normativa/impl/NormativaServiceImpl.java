package ar.gob.chaco.subseambiente.noticias.services.normativa.impl;

import ar.gob.chaco.subseambiente.noticias.bootstrap.enums.Direccion;
import ar.gob.chaco.subseambiente.noticias.domain.Normativa;
import ar.gob.chaco.subseambiente.noticias.mapper.normativa.NormativaMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.normativa.NormativaDTO;
import ar.gob.chaco.subseambiente.noticias.repository.normativa.NormativaRepository;
import ar.gob.chaco.subseambiente.noticias.services.normativa.NormativaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class NormativaServiceImpl implements NormativaService {

    private NormativaMapper normativaMapper;

    private NormativaRepository normativaRepository;
    @Override
    public Normativa crearNormativa(NormativaDTO normativaDTO) {
        Normativa normativaCreada = normativaMapper.normativaDTOtoNormativa(normativaDTO);
        return normativaRepository.save(normativaCreada);
    }

    @Override
    public List<NormativaDTO> getNormativas() {
        List<NormativaDTO> normativaDTOList = new ArrayList<>();
        for (Normativa normativa: normativaRepository.findAll()){
            normativaDTOList.add(normativaMapper.normativaToNormativaDTO(normativa));
        }
        return normativaDTOList;
    }

    @Override
    public List<NormativaDTO> getNormativasPorDIreccion(String direccion) {
        List<NormativaDTO> normativaDTOList = new ArrayList<>();
        for (Normativa normativa: normativaRepository.findAll()){
            if (normativa.getDireccion().getDireccion().equalsIgnoreCase(direccion.trim())){
                normativaDTOList.add(normativaMapper.normativaToNormativaDTO(normativa));
            }
        }
        return normativaDTOList;
    }

    @Override
    public Optional<NormativaDTO> getNormativaPorId(UUID idNormativa) {
        Optional<Normativa> normativaOptional = normativaRepository.findById(idNormativa);
        if (normativaOptional.isPresent()){
            return Optional.of(normativaMapper.normativaToNormativaDTO(normativaOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<NormativaDTO> getNormativasPaginadas(int indiceInicio, int normativasPorPagina) {

        // Obtener todas las normativas desde la base de datos
        List<NormativaDTO> todasLasNormativas = new ArrayList<>(); // Suponiendo que utiliza Spring Data JPA

        for (Normativa normativa: normativaRepository.findAll()) {
            todasLasNormativas.add(normativaMapper.normativaToNormativaDTO(normativa));
        }

        // Calcular el índice final de las normativas en función de la página y la cantidad de normativas por página
        int indiceFinal = Math.min(indiceInicio + normativasPorPagina, todasLasNormativas.size());

        // Devolver las normativas correspondientes a la página solicitada
        return todasLasNormativas.subList(indiceInicio, indiceFinal);
    }

    @Override
    public Optional<NormativaDTO> actualizarNormativa(UUID idNormativa, NormativaDTO normativaDTO) {
        Optional<Normativa> normativaOptional = normativaRepository.findById(idNormativa);
        if (normativaOptional.isPresent()){
            actualizarNormativa(normativaOptional.get(), normativaDTO);
            normativaRepository.saveAndFlush(normativaOptional.get());
            return Optional.of(normativaMapper.normativaToNormativaDTO(normativaOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean borrarNormativa(UUID idNormativa) {
        if (normativaRepository.existsById(idNormativa)){
            normativaRepository.deleteById(idNormativa);
            return true;
        }
        return false;
    }

    private void actualizarNormativa(Normativa normativa, NormativaDTO normativaDTO){

        if (normativaDTO.getTitulo()!=null && !normativaDTO.getTitulo().isBlank()){
            normativa.setTitulo(normativaDTO.getTitulo());
        }

        if (normativaDTO.getDireccion()!=null && !normativaDTO.getDireccion().isBlank()){
            normativa.setDireccion(getDireccion(normativaDTO.getDireccion()));
        }

        if (normativaDTO.getUrlDocumento()!=null && !normativaDTO.getUrlDocumento().isBlank()){
            normativa.setUrlDocumento(normativaDTO.getUrlDocumento());
        }
    }

    private Direccion getDireccion(String direccionString){
        if (!direccionString.isBlank()){
            for (Direccion direccion: Direccion.values()){
                if (direccion.getDireccion().equalsIgnoreCase(direccionString)){
                    return direccion;
                }
            }
        }
        return null;
    }
}
