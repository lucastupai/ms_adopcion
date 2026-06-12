package cl.sanosysalvos.ms_adopcion.client;

import cl.sanosysalvos.ms_adopcion.dto.MascotaCatalogoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class MascotasClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${mascotas.service.url}")
    private String mascotasServiceUrl;

    public List<MascotaCatalogoDTO> obtenerMascotas() {
        MascotaCatalogoDTO[] mascotas = restTemplate.getForObject(
                mascotasServiceUrl + "/api/mascotas",
                MascotaCatalogoDTO[].class
        );

        if (mascotas == null) {
            return List.of();
        }

        return Arrays.asList(mascotas);
    }
}