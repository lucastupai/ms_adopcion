package cl.sanosysalvos.ms_adopcion.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import cl.sanosysalvos.ms_adopcion.model.MascotaAdopcion;
import cl.sanosysalvos.ms_adopcion.service.AdopcionCatalogoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(AdopcionCatalogoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AdopcionCatalogoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdopcionCatalogoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/adopcion/registrar - Registrar a Simón, el gato romano")
    void testRegistrarMascota() throws Exception {
        MascotaAdopcion simon = new MascotaAdopcion();
        simon.setNombre("Simón");
        simon.setEspecie("Gato");
        simon.setRaza("Romano");
        simon.setUbicacion("Antonio Varas 666");
        simon.setEstado("DISPONIBLE");

        when(service.registrarMascotaAdopcion(any(MascotaAdopcion.class))).thenReturn(simon);

        mockMvc.perform(post("/api/adopcion/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(simon)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Simón"))
                .andExpect(jsonPath("$.especie").value("Gato"))
                .andExpect(jsonPath("$.raza").value("Romano"))
                .andExpect(jsonPath("$.ubicacion").value("Antonio Varas 666"));
    }

    @Test
    @DisplayName("GET /api/adopcion/catalogo - Obtener lista completa incluyendo a Simón")
    void testObtenerCatalogo() throws Exception {
        MascotaAdopcion simon = new MascotaAdopcion();
        simon.setId(1L);
        simon.setNombre("Simón");
        simon.setEspecie("Gato");
        simon.setEstado("DISPONIBLE");
        
        List<MascotaAdopcion> catalogoReal = Arrays.asList(simon);
        when(service.obtenerCatalogoAdopcion()).thenReturn(catalogoReal);

        mockMvc.perform(get("/api/adopcion/catalogo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Simón"))
                .andExpect(jsonPath("$[0].especie").value("Gato"));
    }

    @Test
    @DisplayName("GET /api/adopcion/catalogo/ubicacion/{ubicacion} - Buscar en sede Antonio Varas")
    void testObtenerCatalogoPorUbicacion() throws Exception {
        MascotaAdopcion simon = new MascotaAdopcion();
        simon.setId(1L);
        simon.setNombre("Simón");
        simon.setEspecie("Gato");
        simon.setUbicacion("Antonio Varas 666");
        
        List<MascotaAdopcion> catalogoFiltrado = Arrays.asList(simon);
        
        // El test simula que buscamos en esa ubicación exacta
        when(service.obtenerCatalogoPorUbicacion("Antonio Varas 666")).thenReturn(catalogoFiltrado);

        mockMvc.perform(get("/api/adopcion/catalogo/ubicacion/Antonio Varas 666"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Simón"))
                .andExpect(jsonPath("$[0].ubicacion").value("Antonio Varas 666"));
    }
}