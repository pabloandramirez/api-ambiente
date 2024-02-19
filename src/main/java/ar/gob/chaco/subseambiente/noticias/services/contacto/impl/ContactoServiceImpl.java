package ar.gob.chaco.subseambiente.noticias.services.contacto.impl;


import ar.gob.chaco.subseambiente.noticias.domain.Contacto;
import ar.gob.chaco.subseambiente.noticias.mapper.contacto.ContactoMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.contacto.ContactoDTO;
import ar.gob.chaco.subseambiente.noticias.repository.contacto.ContactoRepository;
import ar.gob.chaco.subseambiente.noticias.services.contacto.ContactoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
