package ar.gob.chaco.subseambiente.noticias.mapper.contacto.impl;

import ar.gob.chaco.subseambiente.noticias.domain.Contacto;
import ar.gob.chaco.subseambiente.noticias.mapper.contacto.ContactoMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.contacto.ContactoDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class ContactoMapperImpl implements ContactoMapper {
    @Override
    public Contacto contactoDTOtoContacto(ContactoDTO contactoDTO) {
        return Contacto.builder()
                .uuid(UUID.randomUUID())
                .asunto(contactoDTO.getAsunto())
                .nombreYApellido(contactoDTO.getNombreYApellido())
                .email(contactoDTO.getEmail())
                .telefono(contactoDTO.getTelefono())
                .mensaje(contactoDTO.getMensaje())
                .fechaConsulta(LocalDateTime.now())
                .build();
    }

    @Override
    public ContactoDTO contactoToContactoDTO(Contacto contacto) {
        return ContactoDTO.builder()
                .asunto(contacto.getAsunto())
                .nombreYApellido(contacto.getNombreYApellido())
                .email(contacto.getEmail())
                .telefono(contacto.getTelefono())
                .mensaje(contacto.getMensaje())
                .fechaConsulta(getLocalDate(contacto.getFechaConsulta()))
                .build();
    }


    private String getLocalDate(LocalDateTime localDateTime){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

        return localDateTime.format(formato);
    }
}
