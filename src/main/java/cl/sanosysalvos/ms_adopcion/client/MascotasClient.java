package cl.sanosysalvos.ms_adopcion.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class MascotasClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${mascotas.service.url}")
    private String mascotasServiceUrl;

    public List<Map<String, Object>> obtenerMascotas() {
        Map[] mascotas = restTemplate.getForObject(
                mascotasServiceUrl + "/api/mascotas/listar",
                Map[].class
        );

        if (mascotas == null) {
            return List.of();
        }

        return Arrays.asList(mascotas);
    }
}