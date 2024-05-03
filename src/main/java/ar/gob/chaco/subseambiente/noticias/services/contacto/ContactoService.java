package ar.gob.chaco.subseambiente.noticias.services.contacto;

import ar.gob.chaco.subseambiente.noticias.domain.Contacto;
import ar.gob.chaco.subseambiente.noticias.model.dto.contacto.ContactoDTO;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactoService {

    //POST
    Contacto crearContacto(@RequestBody ContactoDTO contactoDTO);

    //GET
    List<ContactoDTO> getContactos();

    List<ContactoDTO> getContactosPorNombreApellido(String nombreYApellido);

    Optional<ContactoDTO> getContactoPorId(UUID idContacto);

    //PUT
    Optional<ContactoDTO> actualizarContacto(UUID idContacto, ContactoDTO contactoActualizadoDTO);

    //DELETE
    boolean borrarContacto(UUID idContacto);
}
