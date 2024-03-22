package ar.gob.chaco.subseambiente.noticias.repository.usuario;

import ar.gob.chaco.subseambiente.noticias.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
