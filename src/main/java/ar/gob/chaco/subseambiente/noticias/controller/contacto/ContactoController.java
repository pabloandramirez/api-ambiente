package ar.gob.chaco.subseambiente.noticias.controller.contacto;

import ar.gob.chaco.subseambiente.noticias.domain.Contacto;
import ar.gob.chaco.subseambiente.noticias.model.dto.contacto.ContactoDTO;
import ar.gob.chaco.subseambiente.noticias.services.contacto.ContactoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
