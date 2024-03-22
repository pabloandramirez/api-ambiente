package ar.gob.chaco.subseambiente.noticias.mapper.usuario;

import ar.gob.chaco.subseambiente.noticias.domain.Usuario;
import ar.gob.chaco.subseambiente.noticias.model.dto.usuario.UsuarioDTO;

public interface UsuarioMapper {

    Usuario usuarioDTOaUsuario(UsuarioDTO usuarioDTO);

    UsuarioDTO usuarioAUsuarioDTO(Usuario usuario);
}
