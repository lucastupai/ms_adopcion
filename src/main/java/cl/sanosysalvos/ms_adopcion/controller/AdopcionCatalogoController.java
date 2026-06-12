package cl.sanosysalvos.ms_adopcion.controller;

import cl.sanosysalvos.ms_adopcion.dto.MascotaCatalogoDTO;
import cl.sanosysalvos.ms_adopcion.service.AdopcionCatalogoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adopcion/catalogo")
@CrossOrigin(origins = "*")
public class AdopcionCatalogoController {

    private final AdopcionCatalogoService adopcionCatalogoService;

    public AdopcionCatalogoController(AdopcionCatalogoService adopcionCatalogoService) {
        this.adopcionCatalogoService = adopcionCatalogoService;
    }

    @GetMapping
    public List<MascotaCatalogoDTO> obtenerCatalogoAdopcion() {
        return adopcionCatalogoService.obtenerCatalogoAdopcion();
    }

    @GetMapping("/ubicacion/{ubicacion}")
    public List<MascotaCatalogoDTO> obtenerCatalogoPorUbicacion(@PathVariable String ubicacion) {
        return adopcionCatalogoService.obtenerCatalogoPorUbicacion(ubicacion);
    }

    @GetMapping("/especie/{especie}")
    public List<MascotaCatalogoDTO> obtenerCatalogoPorEspecie(@PathVariable String especie) {
        return adopcionCatalogoService.obtenerCatalogoPorEspecie(especie);
    }
}