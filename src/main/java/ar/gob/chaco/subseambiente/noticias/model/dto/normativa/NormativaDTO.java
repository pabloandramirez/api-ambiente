package ar.gob.chaco.subseambiente.noticias.model.dto.normativa;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NormativaDTO {
    String id;
    String titulo;
    String direccion;
    String urlDocumento;
}
