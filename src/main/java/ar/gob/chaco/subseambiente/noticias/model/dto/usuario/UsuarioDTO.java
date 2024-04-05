package ar.gob.chaco.subseambiente.noticias.model.dto.usuario;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private String usuario;
    private String password;
    private List<String> roles;
}
