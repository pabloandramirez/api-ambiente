package ar.gob.chaco.subseambiente.noticias.repository.noticia;

import ar.gob.chaco.subseambiente.noticias.domain.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, UUID> {
}
