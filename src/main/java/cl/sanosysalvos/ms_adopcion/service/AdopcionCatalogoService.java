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
        return mascotasClient.obtenerMascotas();
    }

    public List<MascotaCatalogoDTO> obtenerCatalogoPorUbicacion(String ubicacion) {
        return obtenerCatalogoAdopcion()
                .stream()
                .filter(mascota -> mascota.getUbicacion() != null)
                .filter(mascota -> mascota.getUbicacion().toLowerCase().contains(ubicacion.toLowerCase()))
                .toList();
    }

    public List<MascotaCatalogoDTO> obtenerCatalogoPorEspecie(String especie) {
        return obtenerCatalogoAdopcion()
                .stream()
                .filter(mascota -> mascota.getEspecie() != null)
                .filter(mascota -> mascota.getEspecie().equalsIgnoreCase(especie))
                .toList();
    }
}