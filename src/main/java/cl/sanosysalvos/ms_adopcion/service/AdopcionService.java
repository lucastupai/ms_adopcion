package cl.sanosysalvos.ms_adopcion.service;

import cl.sanosysalvos.ms_adopcion.dto.AdopcionRequest;
import cl.sanosysalvos.ms_adopcion.model.Adopcion;
import cl.sanosysalvos.ms_adopcion.model.EstadoAdopcion;
import cl.sanosysalvos.ms_adopcion.repository.AdopcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopcionService {

    private final AdopcionRepository adopcionRepository;

    public AdopcionService(AdopcionRepository adopcionRepository) {
        this.adopcionRepository = adopcionRepository;
    }

    public Adopcion crearSolicitud(AdopcionRequest request) {
        Adopcion adopcion = new Adopcion(
                request.getMascotaId(),
                request.getUsuarioInteresadoId(),
                request.getUsuarioPublicadorId(),
                request.getMensaje()
        );

        return adopcionRepository.save(adopcion);
    }

    public List<Adopcion> listarTodas() {
        return adopcionRepository.findAll();
    }

    public List<Adopcion> listarPorUsuarioInteresado(Long usuarioId) {
        return adopcionRepository.findByUsuarioInteresadoId(usuarioId);
    }

    public List<Adopcion> listarPorUsuarioPublicador(Long usuarioId) {
        return adopcionRepository.findByUsuarioPublicadorId(usuarioId);
    }

    public List<Adopcion> listarPorMascota(Long mascotaId) {
        return adopcionRepository.findByMascotaId(mascotaId);
    }

    public Adopcion marcarComoContactado(Long id) {
        Adopcion adopcion = adopcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud de interés no encontrada"));

        adopcion.setEstado(EstadoAdopcion.CONTACTADO);
        return adopcionRepository.save(adopcion);
    }

    public Adopcion descartarSolicitud(Long id) {
        Adopcion adopcion = adopcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud de interés no encontrada"));

        adopcion.setEstado(EstadoAdopcion.DESCARTADO);
        return adopcionRepository.save(adopcion);
    }

    public Adopcion cancelarSolicitud(Long id) {
        Adopcion adopcion = adopcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud de interés no encontrada"));

        adopcion.setEstado(EstadoAdopcion.CANCELADO);
        return adopcionRepository.save(adopcion);
    }
}