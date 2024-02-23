package ar.gob.chaco.subseambiente.noticias.model.dto.noticia;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticiaDTO {
    private Long identificador;
    private String titulo;
    private String subtitulo;
    private String contenido;
    private List<String> imagenesUrl;
    private String fechaPublicacion;
}
