package ar.gob.chaco.subseambiente.noticias.mapper.contacto;

import ar.gob.chaco.subseambiente.noticias.domain.Contacto;
import ar.gob.chaco.subseambiente.noticias.model.dto.contacto.ContactoDTO;

public interface ContactoMapper {

    Contacto contactoDTOtoContacto (ContactoDTO contactoDTO);

    ContactoDTO contactoToContactoDTO(Contacto contacto);
}
