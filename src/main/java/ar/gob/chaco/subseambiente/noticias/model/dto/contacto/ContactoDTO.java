package ar.gob.chaco.subseambiente.noticias.model.dto.contacto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactoDTO {
    private String asunto;
    private String nombreYApellido;
    private String email;
    private String telefono;
    private String mensaje;
    private String fechaConsulta;
    private String estado;
}
