package ar.gob.chaco.subseambiente.noticias.model.dto.usuario;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private String usuario;
    private String rol;
}
