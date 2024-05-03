package ar.gob.chaco.subseambiente.noticias.bootstrap.enums;

import lombok.Getter;

@Getter
public enum EstadoConsulta {
    PENDIENTE("PENDIENTE"),
    ATENDIDO("ATENDIDO");

    private final String estado;

    EstadoConsulta(String estado){ this.estado = estado;}
}
