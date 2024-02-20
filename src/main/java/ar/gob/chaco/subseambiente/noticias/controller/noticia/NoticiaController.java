package ar.gob.chaco.subseambiente.noticias.controller.noticia;


import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import ar.gob.chaco.subseambiente.noticias.services.noticia.NoticiaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //GET
    @GetMapping("/")
    public List<NoticiaDTO> getNoticias(@RequestParam(name = "titulo", required = false) String titulo){
        log.info("Busca por titulo, si no encuentra muestra todos");
        if (titulo == null || titulo.isBlank()){
            return noticiaService.getNoticias();
        } else {
            if (noticiaService.getNoticiasPorTitulo(titulo).isEmpty()){
                log.info("No hay noticiasa con este titulo");
            }
        }
        return noticiaService.getNoticiasPorTitulo(titulo);
    }
}
