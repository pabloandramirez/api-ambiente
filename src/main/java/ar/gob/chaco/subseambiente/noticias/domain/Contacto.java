package ar.gob.chaco.subseambiente.noticias.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Contacto {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID uuid;

    @Column(length = 50, columnDefinition = "varchar(50)", updatable = true, nullable = false)
    private String asunto;

    @Column(length = 50, columnDefinition = "varchar(50)", updatable = true, nullable = false)
    private String nombreYApellido;

    @Column(length = 50, columnDefinition = "varchar(50)", updatable = true, nullable = false)
    private String email;

    @Column(length = 50, columnDefinition = "varchar(50)", updatable = true, nullable = false)
    private String telefono;

    @Column(length = 500, columnDefinition = "varchar(500)", updatable = true, nullable = false)
    private String mensaje;

    private LocalDate fechaConsulta;

    @Override
    public String toString() {
        return "Contacto{" +
                "asunto='" + asunto + '\'' +
                ", nombreYApellido='" + nombreYApellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fechaConsulta=" + fechaConsulta.toString() +
                '}';
    }
}
