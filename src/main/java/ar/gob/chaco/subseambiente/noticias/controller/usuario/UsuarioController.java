package ar.gob.chaco.subseambiente.noticias.controller.usuario;

import ar.gob.chaco.subseambiente.noticias.domain.Usuario;
import ar.gob.chaco.subseambiente.noticias.exceptions.NotFoundException;
import ar.gob.chaco.subseambiente.noticias.model.dto.usuario.UsuarioDTO;
import ar.gob.chaco.subseambiente.noticias.services.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    //POST
    @PostMapping("/crearUsuario")
    public ResponseEntity<Void> crearUsuario(@RequestBody UsuarioDTO usuarioDTO){
        log.info("Creando un nuevo usuario");
        Usuario usuarioCreado = usuarioService.crearUsuario(usuarioDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/" + usuarioCreado.getUuid());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/eliminarUsuario/{idUsuario}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable(name = "idUsuario")UUID idUsuario)
            throws NotFoundException {
        boolean isUsuarioBorrado = usuarioService.borrarNoticia(idUsuario);

        if (isUsuarioBorrado){
            log.info("Usuario eliminado");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Usuario encontrado");
            throw new NotFoundException();
        }
    }

    //PUT
    @PutMapping("/actualizarUsuario/{idUsuario}")
    public ResponseEntity<Void> actualizarUsuario(@PathVariable(name = "idUsuario") UUID idUsuario,
                                                  @RequestBody UsuarioDTO usuarioActualizadoDTO)
            throws NotFoundException {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.actualizarUsuario(idUsuario, usuarioActualizadoDTO);
        if (usuarioDTO.isEmpty()){
            log.info("Usuario no encontrado");
            throw new NotFoundException();
        } else {
            log.info("Usuario actualizado");
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
