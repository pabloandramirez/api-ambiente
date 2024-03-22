package ar.gob.chaco.subseambiente.noticias.mapper.usuario.impl;

import ar.gob.chaco.subseambiente.noticias.bootstrap.enums.Role;
import ar.gob.chaco.subseambiente.noticias.domain.Usuario;
import ar.gob.chaco.subseambiente.noticias.mapper.usuario.UsuarioMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.usuario.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {
    @Override
    public Usuario usuarioDTOaUsuario(UsuarioDTO usuarioDTO) {
        return Usuario
                .builder()
                .uuid(UUID.randomUUID())
                .usuario(usuarioDTO.getUsuario())
                .rol(Role.valueOf(usuarioDTO.getRol()))
                .build();
    }

    @Override
    public UsuarioDTO usuarioAUsuarioDTO(Usuario usuario) {
        return UsuarioDTO
                .builder()
                .usuario(usuario.getUsuario())
                .rol(usuario.getRol().getRole())
                .build();
    }
}
