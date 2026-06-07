package cl.sanosysalvos.ms_adopcion.repository;

import cl.sanosysalvos.ms_adopcion.model.EstadoMascotaAdopcion;
import cl.sanosysalvos.ms_adopcion.model.MascotaAdopcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaAdopcionRepository extends JpaRepository<MascotaAdopcion, Long> {

    List<MascotaAdopcion> findByEstado(EstadoMascotaAdopcion estado);

    List<MascotaAdopcion> findByTipo(String tipo);

    List<MascotaAdopcion> findByUbicacionContainingIgnoreCase(String ubicacion);
}