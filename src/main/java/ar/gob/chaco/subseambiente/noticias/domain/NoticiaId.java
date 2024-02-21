package ar.gob.chaco.subseambiente.noticias.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class NoticiaId implements Serializable {
    private UUID uuid;
    private Long id;

}