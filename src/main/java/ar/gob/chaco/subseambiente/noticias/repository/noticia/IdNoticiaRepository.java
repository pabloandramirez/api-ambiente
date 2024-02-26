package ar.gob.chaco.subseambiente.noticias.repository.noticia;

import ar.gob.chaco.subseambiente.noticias.domain.IdNoticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdNoticiaRepository extends JpaRepository<IdNoticia, Long> {

    Optional<IdNoticia> findByIdentificador(Long identificador);
}
