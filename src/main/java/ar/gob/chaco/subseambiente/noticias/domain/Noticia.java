package ar.gob.chaco.subseambiente.noticias.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Noticia {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID uuid;

    @Column(length = 50, columnDefinition = "varchar(50)", updatable = true, nullable = false)
    private String titulo;

    @Column(length = 150, columnDefinition = "varchar(150)", updatable = true, nullable = false)
    private String subtitulo;

    @Column(length = 500, columnDefinition = "varchar(500)", updatable = true, nullable = false)
    private String contenido;

    private List<String> imagenesUrl = new ArrayList<>();

    private LocalDate fechaPublicacion;

    @OneToOne
    @JoinColumn(name = "idNoticia_id")
    private IdNoticia idNoticia;

    @Override
    public String toString() {
        return "Noticia{" +
                "titulo='" + titulo + '\'' +
                ", subtitulo='" + subtitulo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", imagenesUrl=" + imagenesUrl +
                ", fechaPublicacion=" + fechaPublicacion.toString() +
                '}';
    }
}
