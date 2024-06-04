package ar.gob.chaco.subseambiente.noticias.services.normativa;

import ar.gob.chaco.subseambiente.noticias.domain.Contacto;
import ar.gob.chaco.subseambiente.noticias.domain.Normativa;
import ar.gob.chaco.subseambiente.noticias.model.dto.contacto.ContactoDTO;
import ar.gob.chaco.subseambiente.noticias.model.dto.normativa.NormativaDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NormativaService {
    //POST
    Normativa crearNormativa(@RequestBody NormativaDTO normativaDTO);

    //GET
    List<NormativaDTO> getNormativas();

    List<NormativaDTO> getNormativasPorDIreccion(String direccion);

    Optional<NormativaDTO> getNormativaPorId(UUID idNormativa);

    List<NormativaDTO> getNormativasPaginadas(int indiceInicio, int normativasPorPagina);

    //PUT
    Optional<NormativaDTO> actualizarNormativa(UUID idNormativa, NormativaDTO normativaDTO);

    //DELETE
    boolean borrarNormativa(UUID idNormativa);
}
