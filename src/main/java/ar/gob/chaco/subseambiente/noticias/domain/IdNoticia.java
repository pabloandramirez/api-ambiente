package ar.gob.chaco.subseambiente.noticias.domain;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class IdNoticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador;

    @OneToOne(mappedBy = "idNoticia")
    private Noticia noticia;
}
