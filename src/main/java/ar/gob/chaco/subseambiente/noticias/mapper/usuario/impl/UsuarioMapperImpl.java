package ar.gob.chaco.subseambiente.noticias.mapper.usuario.impl;

import ar.gob.chaco.subseambiente.noticias.bootstrap.enums.Role;
import ar.gob.chaco.subseambiente.noticias.domain.Usuario;
import ar.gob.chaco.subseambiente.noticias.mapper.usuario.UsuarioMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.usuario.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario usuarioDTOaUsuario(UsuarioDTO usuarioDTO) {
        return Usuario
                .builder()
                .uuid(UUID.randomUUID())
                .usuario(usuarioDTO.getUsuario())
                .password(passwordEncoder.encode(usuarioDTO.getPassword()))
                .roles(usuarioDTO.getRoles().stream().map(Role::valueOf).collect(Collectors.toList()))
                .build();
    }

    @Override
    public UsuarioDTO usuarioAUsuarioDTO(Usuario usuario) {
        return UsuarioDTO
                .builder()
                .usuario(usuario.getUsuario())
                .roles(usuario.getRoles().stream().map(Role::getRole).collect(Collectors.toList()))
                .build();
    }
}
