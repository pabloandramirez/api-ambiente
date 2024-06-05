package ar.gob.chaco.subseambiente.noticias.domain;

import ar.gob.chaco.subseambiente.noticias.bootstrap.enums.Direccion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Normativa {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "direccion", nullable = true, length = 100)
    private Direccion direccion;

    @Column(length = 500, columnDefinition = "varchar(500)", updatable = true, nullable = false)
    private String urlDocumento;
}
