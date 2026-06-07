package cl.sanosysalvos.ms_adopcion.service;

import cl.sanosysalvos.ms_adopcion.dto.MascotaAdopcionRequest;
import cl.sanosysalvos.ms_adopcion.model.EstadoMascotaAdopcion;
import cl.sanosysalvos.ms_adopcion.model.MascotaAdopcion;
import cl.sanosysalvos.ms_adopcion.repository.MascotaAdopcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaAdopcionService {

    private final MascotaAdopcionRepository mascotaAdopcionRepository;

    public MascotaAdopcionService(MascotaAdopcionRepository mascotaAdopcionRepository) {
        this.mascotaAdopcionRepository = mascotaAdopcionRepository;
    }

    public MascotaAdopcion crearMascotaCatalogo(MascotaAdopcionRequest request) {
        MascotaAdopcion mascota = new MascotaAdopcion(
                request.getNombre(),
                request.getTipo(),
                request.getEdad(),
                request.getUbicacion(),
                request.getFundacion(),
                request.getDescripcion(),
                request.getAspectosPositivos(),
                request.getAspectosConsiderar(),
                request.getImagen()
        );

        return mascotaAdopcionRepository.save(mascota);
    }

    public List<MascotaAdopcion> listarTodas() {
        return mascotaAdopcionRepository.findAll();
    }

    public List<MascotaAdopcion> listarDisponibles() {
        return mascotaAdopcionRepository.findByEstado(EstadoMascotaAdopcion.DISPONIBLE);
    }

    public MascotaAdopcion buscarPorId(Long id) {
        return mascotaAdopcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota del catálogo no encontrada"));
    }

    public List<MascotaAdopcion> buscarPorTipo(String tipo) {
        return mascotaAdopcionRepository.findByTipo(tipo);
    }

    public List<MascotaAdopcion> buscarPorUbicacion(String ubicacion) {
        return mascotaAdopcionRepository.findByUbicacionContainingIgnoreCase(ubicacion);
    }

    public MascotaAdopcion actualizarMascota(Long id, MascotaAdopcionRequest request) {
        MascotaAdopcion mascota = buscarPorId(id);

        mascota.setNombre(request.getNombre());
        mascota.setTipo(request.getTipo());
        mascota.setEdad(request.getEdad());
        mascota.setUbicacion(request.getUbicacion());
        mascota.setFundacion(request.getFundacion());
        mascota.setDescripcion(request.getDescripcion());
        mascota.setAspectosPositivos(request.getAspectosPositivos());
        mascota.setAspectosConsiderar(request.getAspectosConsiderar());
        mascota.setImagen(request.getImagen());

        return mascotaAdopcionRepository.save(mascota);
    }

    public MascotaAdopcion cambiarEstado(Long id, EstadoMascotaAdopcion estado) {
        MascotaAdopcion mascota = buscarPorId(id);
        mascota.setEstado(estado);
        return mascotaAdopcionRepository.save(mascota);
    }

    public void eliminarMascota(Long id) {
        MascotaAdopcion mascota = buscarPorId(id);
        mascotaAdopcionRepository.delete(mascota);
    }
}