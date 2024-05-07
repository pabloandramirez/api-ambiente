package ar.gob.chaco.subseambiente.noticias.services.usuario.impl;

import ar.gob.chaco.subseambiente.noticias.bootstrap.enums.Role;
import ar.gob.chaco.subseambiente.noticias.domain.Usuario;
import ar.gob.chaco.subseambiente.noticias.mapper.usuario.UsuarioMapper;
import ar.gob.chaco.subseambiente.noticias.model.dto.usuario.UsuarioDTO;
import ar.gob.chaco.subseambiente.noticias.repository.usuario.UsuarioRepository;
import ar.gob.chaco.subseambiente.noticias.services.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioMapper usuarioMapper;

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDTO> getUsuarios() {
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        for (Usuario usuario: usuarioRepository.findAll()){
            usuarioDTOList.add(usuarioMapper.usuarioAUsuarioDTO(usuario));
        }
        return usuarioDTOList;
    }

    @Override
    public List<UsuarioDTO> getUsuarioPorNombre(String nombre) {
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        for (Usuario usuario: usuarioRepository.findAll()){
            if (usuario.getUsuario().toLowerCase().contains(nombre.toLowerCase())){
                usuarioDTOList.add(usuarioMapper.usuarioAUsuarioDTO(usuario));
            }
        }
        return usuarioDTOList;
    }

    @Override
    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioCreado = usuarioMapper.usuarioDTOaUsuario(usuarioDTO);
        return usuarioRepository.save(usuarioCreado);
    }

    @Override
    public boolean borrarNoticia(UUID idUsuario) {
        if(usuarioRepository.existsById(idUsuario)){
            usuarioRepository.deleteById(idUsuario);
            return true;
        }
        return false;
    }

    @Override
    public Optional<UsuarioDTO> actualizarUsuario(UUID idUsuario, UsuarioDTO usuarioActualizadoDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isPresent()){
            actualizacionUsuario(usuarioOptional.get(), usuarioActualizadoDTO);
            usuarioRepository.saveAndFlush(usuarioOptional.get());
            return Optional.of(usuarioMapper.usuarioAUsuarioDTO(usuarioOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    private void actualizacionUsuario(Usuario usuario, UsuarioDTO usuarioActualizadoDTO){
        if (usuarioActualizadoDTO.getUsuario() != null && !usuarioActualizadoDTO.getUsuario().isBlank()){
            usuario.setUsuario(usuarioActualizadoDTO.getUsuario());
        }

        if (usuarioActualizadoDTO.getRoles() != null && !usuarioActualizadoDTO.getRoles().isEmpty()){
            usuario.setRoles(usuarioActualizadoDTO.getRoles().stream().map(Role::valueOf).collect(Collectors.toSet()));
        }
    }
}
