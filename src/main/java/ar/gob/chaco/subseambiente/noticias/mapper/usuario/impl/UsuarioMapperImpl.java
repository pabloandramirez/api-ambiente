package ar.gob.chaco.subseambiente.noticias.mapper.usuario.impl;

import ar.gob.chaco.subseambiente.noticias.bootstrap.enums.Role;
import ar.gob.chaco.subseambiente.noticias.domain.Usuario;
import ar.gob.chaco.subseambiente.noticias.mapper.usuario.UsuarioMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.usuario.UsuarioDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsuarioMapperImpl implements UsuarioMapper {

    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario usuarioDTOaUsuario(UsuarioDTO usuarioDTO) {
        return Usuario
                .builder()
                .uuid(UUID.randomUUID())
                .usuario(usuarioDTO.getUsuario())
                .password(passwordEncoder.encode(usuarioDTO.getPassword()))
                .roles(usuarioDTO.getRoles().stream().map(this::getRole).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public UsuarioDTO usuarioAUsuarioDTO(Usuario usuario) {
        return UsuarioDTO
                .builder()
                .usuario(usuario.getUsuario())
                .roles(usuario.getRoles().stream().map(this::getRole).collect(Collectors.toSet()))
                .build();
    }

    private Role getRole(String roleString){
        if(!roleString.isBlank()){
            for (Role role: Role.values()) {
                if (role.getRole().equalsIgnoreCase(roleString)) {
                    return role;
                }
            }
        }
        return null;
    }

    private String getRole(Role role){
        return role.getRole();
    }
}
