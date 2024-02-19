package ar.gob.chaco.subseambiente.noticias.controller.noticia;


import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import ar.gob.chaco.subseambiente.noticias.services.noticia.NoticiaService;
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
@RequestMapping("/noticia")
@RequiredArgsConstructor
@Slf4j
public class NoticiaController {

    private final NoticiaService noticiaService;

    //POST
    @PostMapping
    public ResponseEntity<Void> crearNoticia(@RequestBody NoticiaDTO noticiaDTO){
        log.info("Creando una nueva noticia");
        Noticia noticiaCreada = noticiaService.crearNoticia(noticiaDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/" + noticiaCreada.getUuid());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
