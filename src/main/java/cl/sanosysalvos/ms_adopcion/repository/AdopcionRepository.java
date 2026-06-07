package cl.sanosysalvos.ms_adopcion.repository;

import cl.sanosysalvos.ms_adopcion.model.Adopcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdopcionRepository extends JpaRepository<Adopcion, Long> {

    List<Adopcion> findByUsuarioInteresadoId(Long usuarioInteresadoId);

    List<Adopcion> findByUsuarioPublicadorId(Long usuarioPublicadorId);

    List<Adopcion> findByMascotaId(Long mascotaId);
}