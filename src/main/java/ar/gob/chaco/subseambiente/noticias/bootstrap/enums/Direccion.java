package ar.gob.chaco.subseambiente.noticias.bootstrap.enums;

import lombok.Getter;

@Getter
public enum Direccion {
    POLITICA_INCENTIVOS_AMBIENTALES("Politica e incentivos ambientales"),
    FISCALIZACION_CONTROL_AMBIENTAL("Fiscalizacion y Control Ambiental"),
    AREAS_PROTEGIDAS_BIODIVERSIDAD("Areas Protegidas y Biodiversidad"),
    CAMBIO_CLIMATICO_RIESGO_AMBIENTAL("Cambio Climatico y Riesgo Ambiental");

    private final String direccion;

    Direccion(String direccion){ this.direccion = direccion;}
}
