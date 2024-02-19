package ar.gob.chaco.subseambiente.noticias.repository.contacto;

import ar.gob.chaco.subseambiente.noticias.domain.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactoRepository extends JpaRepository<Contacto, UUID> {
}
