package ar.gob.chaco.subseambiente.noticias.repository.normativa;

import ar.gob.chaco.subseambiente.noticias.domain.Normativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NormativaRepository extends JpaRepository<Normativa, UUID> {

}
