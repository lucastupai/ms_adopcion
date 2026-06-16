package cl.sanosysalvos.ms_adopcion.service;

import cl.sanosysalvos.ms_adopcion.model.MascotaAdopcion;
import cl.sanosysalvos.ms_adopcion.repository.MascotaAdopcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopcionCatalogoService {

    private final MascotaAdopcionRepository mascotaAdopcionRepository;

    public AdopcionCatalogoService(MascotaAdopcionRepository mascotaAdopcionRepository) {
        this.mascotaAdopcionRepository = mascotaAdopcionRepository;
    }

    public MascotaAdopcion registrarMascotaAdopcion(MascotaAdopcion mascotaAdopcion) {
        mascotaAdopcion.setId(null);

        if (mascotaAdopcion.getEstado() == null || mascotaAdopcion.getEstado().isBlank()) {
            mascotaAdopcion.setEstado("DISPONIBLE");
        }

        return mascotaAdopcionRepository.save(mascotaAdopcion);
    }

    public List<MascotaAdopcion> obtenerCatalogoAdopcion() {
        return mascotaAdopcionRepository.findByEstadoIgnoreCase("DISPONIBLE");
    }

    public MascotaAdopcion obtenerMascotaPorId(Long id) {
        return mascotaAdopcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota en adopción no encontrada"));
    }

    public List<MascotaAdopcion> obtenerCatalogoPorUbicacion(String ubicacion) {
        return mascotaAdopcionRepository
                .findByUbicacionContainingIgnoreCaseAndEstadoIgnoreCase(ubicacion, "DISPONIBLE");
    }

    public List<MascotaAdopcion> obtenerCatalogoPorEspecie(String especie) {
        return mascotaAdopcionRepository
                .findByEspecieIgnoreCaseAndEstadoIgnoreCase(especie, "DISPONIBLE");
    }

    public List<MascotaAdopcion> obtenerMascotasPorContactoVeterinaria(String contacto) {
        return mascotaAdopcionRepository
                .findByContactoIgnoreCaseAndEstadoIgnoreCase(contacto, "DISPONIBLE");
    }

    public MascotaAdopcion marcarComoAdoptada(Long id) {
        MascotaAdopcion mascota = mascotaAdopcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota en adopción no encontrada"));

        mascota.setEstado("ADOPTADA");

        return mascotaAdopcionRepository.save(mascota);
    }
}