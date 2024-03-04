package ar.gob.chaco.subseambiente.noticias.controller.noticia;


import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import ar.gob.chaco.subseambiente.noticias.exceptions.NotFoundException;
import ar.gob.chaco.subseambiente.noticias.model.dto.noticia.NoticiaDTO;
import ar.gob.chaco.subseambiente.noticias.repository.noticia.IdNoticiaRepository;
import ar.gob.chaco.subseambiente.noticias.services.noticia.NoticiaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/noticia")
@RequiredArgsConstructor
@Slf4j
public class NoticiaController {

    private final NoticiaService noticiaService;
    private final IdNoticiaRepository idNoticiaRepository;

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

    @GetMapping("/{idNoticia}")
    public NoticiaDTO getNoticiaPorId(@PathVariable(name = "idNoticia")UUID idNoticia)
            throws NotFoundException {
        return noticiaService.getNoticiaPorId(idNoticia).orElseThrow(NotFoundException::new);
    }

    @GetMapping("/noticiaPorLong/{idLong}")
    public NoticiaDTO getNoticiaPorLong(@PathVariable("idLong") Long idLong)
            throws NotFoundException {
        return noticiaService.getNoticiaPorLong(idLong).orElseThrow(NotFoundException::new);
    }

    //UPDATE
    @PutMapping("/{idNoticia}")
    public ResponseEntity<Void> actualizarNoticia(@PathVariable(name = "idNoticia") UUID idNoticia,
                                                  @RequestBody NoticiaDTO noticiaActualizadaDTO) throws NotFoundException {
        Optional<NoticiaDTO> noticiaDTO = noticiaService.actualizarNoticia(idNoticia, noticiaActualizadaDTO);
        if (noticiaDTO.isEmpty()){
            log.info("Noticia no encontrada");
            throw new NotFoundException();
        } else {
            log.info("Noticia actualizada");
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //DELETE
    @DeleteMapping("/{idNoticia}")
    public ResponseEntity<Void> borrarNoticia(@PathVariable(name = "idNoticia") UUID idNoticia) throws NotFoundException {
        boolean isNoticiaBorrada = noticiaService.borrarNoticia(idNoticia);
        if (isNoticiaBorrada){
            log.info("Noticia eliminada");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Noticia no encontrada");
            throw new NotFoundException();
        }
    }


}
