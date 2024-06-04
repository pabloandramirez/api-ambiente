package ar.gob.chaco.subseambiente.noticias.controller.normativa;

import ar.gob.chaco.subseambiente.noticias.domain.Normativa;
import ar.gob.chaco.subseambiente.noticias.exceptions.NotFoundException;
import ar.gob.chaco.subseambiente.noticias.model.dto.normativa.NormativaDTO;
import ar.gob.chaco.subseambiente.noticias.services.normativa.NormativaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/normativa")
@RequiredArgsConstructor
@Slf4j
public class NormativaController {

    private final NormativaService normativaService;

    //POST
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public ResponseEntity<Void> crearNormativa(@RequestBody NormativaDTO normativaDTO){
        log.info("Creando una nueva normativa");
        Normativa normativaCreada = normativaService.crearNormativa(normativaDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/"+normativaCreada.getUuid());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //GET
    @GetMapping("/")
    public List<NormativaDTO> getNormativas(@RequestParam(name="direccion", required = false) String direccion){
        log.info("Se busca todos las normativas o filtra por direccion");
        if (direccion == null || direccion.isBlank()){
            return normativaService.getNormativas();
        } else {
            if (normativaService.getNormativasPorDIreccion(direccion).isEmpty()){
                log.info("No hay normativas con esta direccion");
            }
        }
        return normativaService.getNormativasPorDIreccion(direccion);
    }

    @GetMapping("/{idNormativa}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public NormativaDTO getNormativaPorId(@PathVariable(value = "idNormativa") UUID idNormativa)
            throws NotFoundException {
        return normativaService.getNormativaPorId(idNormativa).orElseThrow(NotFoundException::new);
    }

    @GetMapping("/paginado")
    public List<NormativaDTO> getNormativasPaginado(@RequestParam(name = "pagina", required = true) int pagina,
                                                  @RequestParam(name = "normativasPorPagina", required = true) int normativasPorPagina){
        log.info("Se muestra las normativas en formato de paginado");
        // Calcular el índice de inicio de las normativas en función de la página y la cantidad de normativas por página
        int indiceInicio = (pagina - 1) * normativasPorPagina;

        // Obtener las normativas para la página actual

        return normativaService.getNormativasPaginadas(indiceInicio, normativasPorPagina);

    }

    //UPDATE
    @PutMapping("/{idNormativa}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public ResponseEntity<Void> actualizarNormativa(@PathVariable(name = "idNormativa") UUID idNormativa,
                                                   @RequestBody NormativaDTO normativaDTO)
            throws NotFoundException {
        Optional<NormativaDTO> normativaDTOOptional = normativaService.actualizarNormativa(idNormativa, normativaDTO);
        if (normativaDTOOptional.isEmpty()){
            log.info("Normativa no encontrada");
            throw new NotFoundException();
        } else {
            log.info("Normativa actualizada");
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //DELETE
    @DeleteMapping("/{idNormativa}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public ResponseEntity<Void> borrarNormativa(@PathVariable(name = "idNormativa") UUID idNormativa)
            throws NotFoundException {
        boolean isNormativaBorrada = normativaService.borrarNormativa(idNormativa);
        if (isNormativaBorrada){
            log.info("Se elimino la normativa");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Normativa no encontrada");
            throw new NotFoundException();
        }
    }
}
