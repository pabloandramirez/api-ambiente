package ar.gob.chaco.subseambiente.noticias.mapper.normativa;

import ar.gob.chaco.subseambiente.noticias.domain.Normativa;
import ar.gob.chaco.subseambiente.noticias.model.dto.normativa.NormativaDTO;

public interface NormativaMapper {

    Normativa normativaDTOtoNormativa(NormativaDTO normativaDTO);

    NormativaDTO normativaToNormativaDTO(Normativa normativa);
}
