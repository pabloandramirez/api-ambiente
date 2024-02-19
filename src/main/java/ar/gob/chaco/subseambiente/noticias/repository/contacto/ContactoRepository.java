package ar.gob.chaco.subseambiente.noticias.repository.contacto;

import ar.gob.chaco.subseambiente.noticias.domain.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, UUID> {
}
