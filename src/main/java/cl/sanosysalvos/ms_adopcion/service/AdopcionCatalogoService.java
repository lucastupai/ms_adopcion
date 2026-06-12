package cl.sanosysalvos.ms_adopcion.service;

import cl.sanosysalvos.ms_adopcion.client.MascotasClient;
import cl.sanosysalvos.ms_adopcion.dto.MascotaCatalogoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopcionCatalogoService {

    private final MascotasClient mascotasClient;

    public AdopcionCatalogoService(MascotasClient mascotasClient) {
        this.mascotasClient = mascotasClient;
    }

    public List<MascotaCatalogoDTO> obtenerCatalogoAdopcion() {
        return mascotasClient.obtenerMascotas()
                .stream()
                .filter(mascota -> mascota.getEstado() != null)
                .filter(mascota ->
                        mascota.getEstado().equalsIgnoreCase("EN_ADOPCION")
                                || mascota.getEstado().equalsIgnoreCase("DISPONIBLE_ADOPCION")
                                || mascota.getEstado().equalsIgnoreCase("DISPONIBLE")
                )
                .toList();
    }

    public List<MascotaCatalogoDTO> obtenerCatalogoPorUbicacion(String ubicacion) {
        return obtenerCatalogoAdopcion()
                .stream()
                .filter(mascota -> mascota.getUbicacion() != null)
                .filter(mascota -> mascota.getUbicacion().toLowerCase().contains(ubicacion.toLowerCase()))
                .toList();
    }

    public List<MascotaCatalogoDTO> obtenerCatalogoPorTipo(String tipo) {
        return obtenerCatalogoAdopcion()
                .stream()
                .filter(mascota -> mascota.getTipo() != null)
                .filter(mascota -> mascota.getTipo().equalsIgnoreCase(tipo))
                .toList();
    }
}