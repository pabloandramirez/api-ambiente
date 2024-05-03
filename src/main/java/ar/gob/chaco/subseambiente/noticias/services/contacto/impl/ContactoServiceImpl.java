package ar.gob.chaco.subseambiente.noticias.services.contacto.impl;


import ar.gob.chaco.subseambiente.noticias.bootstrap.enums.EstadoConsulta;
import ar.gob.chaco.subseambiente.noticias.domain.Contacto;
import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.mapper.contacto.ContactoMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.contacto.ContactoDTO;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import ar.gob.chaco.subseambiente.noticias.repository.contacto.ContactoRepository;
import ar.gob.chaco.subseambiente.noticias.services.contacto.ContactoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ContactoServiceImpl implements ContactoService {

    private final ContactoMapper contactoMapper;

    private final ContactoRepository contactoRepository;
    @Override
    public Contacto crearContacto(ContactoDTO contactoDTO) {
        Contacto contactoCreado = contactoMapper.contactoDTOtoContacto(contactoDTO);
        return contactoRepository.save(contactoCreado);
    }

    @Override
    public List<ContactoDTO> getContactos() {
        List<ContactoDTO> contactoDTOList = new ArrayList<>();
        for (Contacto contacto: contactoRepository.findAll()){
            contactoDTOList.add(contactoMapper.contactoToContactoDTO(contacto));
        }
        contactoDTOList.sort(Comparator.comparing(ContactoDTO::getFechaConsulta).reversed());
        return contactoDTOList;
    }

    @Override
    public List<ContactoDTO> getContactosPorNombreApellido(String nombreYApellido) {
        List<ContactoDTO> contactoDTOList = new ArrayList<>();
        for (Contacto contacto: contactoRepository.findAll()){
            if (contacto.getNombreYApellido().toLowerCase().contains(nombreYApellido.toLowerCase())){
                contactoDTOList.add(contactoMapper.contactoToContactoDTO(contacto));
            }
        }
        return contactoDTOList;
    }

    @Override
    public Optional<ContactoDTO> getContactoPorId(UUID idContacto) {
        Optional<Contacto> contactoOptional = contactoRepository.findById(idContacto);
        if (contactoOptional.isPresent()){
            return Optional.of(contactoMapper.contactoToContactoDTO(contactoOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ContactoDTO> actualizarContacto(UUID idContacto, ContactoDTO contactoActualizadoDTO) {
        Optional<Contacto> contactoOptional = contactoRepository.findById(idContacto);
        if (contactoOptional.isPresent()){
            actualizacionNoticia(contactoOptional.get(), contactoActualizadoDTO);
            contactoRepository.saveAndFlush(contactoOptional.get());
            return Optional.of(contactoMapper.contactoToContactoDTO(contactoOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean borrarContacto(UUID idContacto) {
        if (contactoRepository.existsById(idContacto)){
            contactoRepository.deleteById(idContacto);
            return true;
        }
        return false;
    }

    private void actualizacionNoticia(Contacto contacto, ContactoDTO contactoActualizadoDTO){
        if (contactoActualizadoDTO.getEstado() != null && !contactoActualizadoDTO.getEstado().isBlank()){
            contacto.setEstadoConsulta(getEstadoConsulta(contactoActualizadoDTO.getEstado()));
        }
    }

    private EstadoConsulta getEstadoConsulta(String estadoString){
        if(!estadoString.isBlank()){
            for (EstadoConsulta estado: EstadoConsulta.values()) {
                if (estado.getEstado().equalsIgnoreCase(estadoString)) {
                    return estado;
                }
            }
        }
        return null;
    }

}
