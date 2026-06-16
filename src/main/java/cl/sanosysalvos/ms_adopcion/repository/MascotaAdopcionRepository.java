package cl.sanosysalvos.ms_adopcion.repository;

import cl.sanosysalvos.ms_adopcion.model.MascotaAdopcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaAdopcionRepository extends JpaRepository<MascotaAdopcion, Long> {

    List<MascotaAdopcion> findByEstadoIgnoreCase(String estado);

    List<MascotaAdopcion> findByEspecieIgnoreCaseAndEstadoIgnoreCase(String especie, String estado);

    List<MascotaAdopcion> findByUbicacionContainingIgnoreCaseAndEstadoIgnoreCase(String ubicacion, String estado);
    
    List<MascotaAdopcion> findByContactoIgnoreCaseAndEstadoIgnoreCase(String contacto, String estado);
}