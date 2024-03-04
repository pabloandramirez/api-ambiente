package ar.gob.chaco.subseambiente.noticias.bootstrap.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER("usuario"),
    ADMIN("admin");

    private final String role;

    Role(String role){ this.role = role;}
}
