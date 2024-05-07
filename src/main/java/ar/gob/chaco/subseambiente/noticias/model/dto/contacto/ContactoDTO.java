package ar.gob.chaco.subseambiente.noticias.model.dto.contacto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactoDTO {
    private String id;
    private String asunto;
    private String nombreYApellido;
    private String email;
    private String telefono;
    private String mensaje;
    private String fechaConsulta;
    private LocalDate fechaConsultaDate;
    private String estado;
    private String observaciones;
}
