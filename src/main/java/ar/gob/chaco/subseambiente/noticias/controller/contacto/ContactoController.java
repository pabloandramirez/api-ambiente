package ar.gob.chaco.subseambiente.noticias.controller.contacto;

import ar.gob.chaco.subseambiente.noticias.domain.Contacto;
import ar.gob.chaco.subseambiente.noticias.exceptions.NotFoundException;
import ar.gob.chaco.subseambiente.noticias.model.dto.contacto.ContactoDTO;
import ar.gob.chaco.subseambiente.noticias.services.contacto.ContactoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/contacto")
@RequiredArgsConstructor
@Slf4j
public class ContactoController {

    private final ContactoService contactoService;

    //POST
    @PostMapping
    public ResponseEntity<Void> crearContacto(@RequestBody ContactoDTO contactoDTO){
        log.info("Creando una nueva consulta");
        Contacto contactoCreado = contactoService.crearContacto(contactoDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //GET
    @GetMapping("/")
    public List<ContactoDTO> getContactos(@RequestParam(name="nombreYApellido", required = false) String nombreYApellido){
        log.info("Se busca todos las consultas o filtra por nombre y/o apellido");
        if (nombreYApellido == null || nombreYApellido.isBlank()){
            return contactoService.getContactos();
        } else {
            if (contactoService.getContactosPorNombreApellido(nombreYApellido).isEmpty()){
                log.info("No hay consultas con este nombre y/o apellido");
            }
        }
        return contactoService.getContactosPorNombreApellido(nombreYApellido);
    }

    @GetMapping("/{idContacto}")
    public ContactoDTO getContactoPorId(@PathVariable(value = "idContacto")UUID idContacto)
            throws NotFoundException {
        return contactoService.getContactoPorId(idContacto).orElseThrow(NotFoundException::new);
    }

    //DELETE
    @DeleteMapping("/idContacto")
    public ResponseEntity<Void> borrarConsulta(@PathVariable(name = "idContacto") UUID idContacto)
            throws NotFoundException {
        boolean isContactoBorrado = contactoService.borrarContacto(idContacto);
        if (isContactoBorrado){
            log.info("Se elimino la consulta");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Consulta no encontrada");
            throw new NotFoundException();
        }
    }
}
