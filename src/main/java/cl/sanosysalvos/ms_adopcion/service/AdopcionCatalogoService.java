package cl.sanosysalvos.ms_adopcion.service;

import cl.sanosysalvos.ms_adopcion.client.MascotasClient;
import cl.sanosysalvos.ms_adopcion.dto.MascotaAdopcionInfoDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class AdopcionCatalogoService {

    private final MascotasClient mascotasClient;

    public AdopcionCatalogoService(MascotasClient mascotasClient) {
        this.mascotasClient = mascotasClient;
    }

    public List<MascotaAdopcionInfoDTO> obtenerCatalogoAdopcion() {
        return mascotasClient.obtenerMascotas()
                .stream()
                .filter(this::esMascotaEnAdopcion)
                .map(this::convertirAFichaInformativa)
                .toList();
    }

    public MascotaAdopcionInfoDTO obtenerMascotaPorId(Long id) {
        return obtenerCatalogoAdopcion()
                .stream()
                .filter(mascota -> mascota.getId() != null && mascota.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada en el catálogo de adopción"));
    }

    public List<MascotaAdopcionInfoDTO> obtenerCatalogoPorUbicacion(String ubicacion) {
        return obtenerCatalogoAdopcion()
                .stream()
                .filter(mascota -> mascota.getUbicacion() != null)
                .filter(mascota -> mascota.getUbicacion().toLowerCase().contains(ubicacion.toLowerCase()))
                .toList();
    }

    public List<MascotaAdopcionInfoDTO> obtenerCatalogoPorEspecie(String especie) {
        return obtenerCatalogoAdopcion()
                .stream()
                .filter(mascota -> mascota.getEspecie() != null)
                .filter(mascota -> mascota.getEspecie().equalsIgnoreCase(especie))
                .toList();
    }

    private boolean esMascotaEnAdopcion(Map datosMascota) {
        String estadoReporte = convertirString(datosMascota.get("estadoReporte"));

        if (estadoReporte == null) {
            return false;
        }

        return estadoReporte.equalsIgnoreCase("ADOPCION")
                || estadoReporte.equalsIgnoreCase("EN_ADOPCION")
                || estadoReporte.equalsIgnoreCase("EN ADOPCION");
    }

    private MascotaAdopcionInfoDTO convertirAFichaInformativa(Map datosMascota) {
        MascotaAdopcionInfoDTO dto = new MascotaAdopcionInfoDTO();

        dto.setId(convertirLong(datosMascota.get("id")));
        dto.setNombre(convertirString(datosMascota.get("nombre")));
        dto.setEspecie(convertirString(datosMascota.get("especie")));
        dto.setRaza(convertirString(datosMascota.get("raza")));
        dto.setEdad(convertirInteger(datosMascota.get("edad")));
        dto.setDescripcion(convertirString(datosMascota.get("descripcion")));
        dto.setUbicacion(convertirString(datosMascota.get("ubicacion")));
        dto.setEstadoReporte(convertirString(datosMascota.get("estadoReporte")));
        dto.setFotos(convertirFotos(convertirString(datosMascota.get("foto"))));

        return dto;
    }

    private List<String> convertirFotos(String foto) {
        if (foto == null || foto.isBlank()) {
            return List.of();
        }

        return Arrays.stream(foto.split(","))
                .map(String::trim)
                .filter(url -> !url.isBlank())
                .toList();
    }

    private String convertirString(Object valor) {
        if (valor == null) {
            return null;
        }

        return valor.toString();
    }

    private Long convertirLong(Object valor) {
        if (valor == null) {
            return null;
        }

        if (valor instanceof Number numero) {
            return numero.longValue();
        }

        return Long.valueOf(valor.toString());
    }

    private Integer convertirInteger(Object valor) {
        if (valor == null) {
            return null;
        }

        if (valor instanceof Number numero) {
            return numero.intValue();
        }

        return Integer.valueOf(valor.toString());
    }
}