package ar.gob.chaco.subseambiente.noticias.bootstrap.enums;

import lombok.Getter;

@Getter
public enum Role {
    USUARIO("USUARIO"),
    ADMIN("ADMIN");

    private final String role;

    Role(String role){ this.role = role;}
}
