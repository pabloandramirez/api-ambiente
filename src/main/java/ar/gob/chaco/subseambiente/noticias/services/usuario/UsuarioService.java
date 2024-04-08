package ar.gob.chaco.subseambiente.noticias.services.usuario;

import ar.gob.chaco.subseambiente.noticias.domain.Usuario;
import ar.gob.chaco.subseambiente.noticias.model.dto.usuario.UsuarioDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    List<UsuarioDTO> getUsuarios();

    List<UsuarioDTO> getUsuarioPorNombre(String nombre);

    Usuario crearUsuario(@RequestBody UsuarioDTO usuarioDTO);

    boolean borrarNoticia(UUID idUsuario);

    Optional<UsuarioDTO> actualizarUsuario(UUID idUsuario, UsuarioDTO usuarioActualizadoDTO);
}
