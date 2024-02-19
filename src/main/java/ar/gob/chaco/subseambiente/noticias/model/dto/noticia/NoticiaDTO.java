package ar.gob.chaco.subseambiente.noticias.model.dto.noticia;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticiaDTO {
    private String titulo;
    private String subtitulo;
    private String contenido;
    private List<String> imagenes;
    private String fechaPublicacion;
}
