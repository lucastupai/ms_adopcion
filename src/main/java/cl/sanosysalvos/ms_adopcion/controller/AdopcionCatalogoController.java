package cl.sanosysalvos.ms_adopcion.controller;

import cl.sanosysalvos.ms_adopcion.dto.MascotaAdopcionInfoDTO;
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
    public List<MascotaAdopcionInfoDTO> obtenerCatalogoAdopcion() {
        return adopcionCatalogoService.obtenerCatalogoAdopcion();
    }

    @GetMapping("/{id}")
    public MascotaAdopcionInfoDTO obtenerMascotaPorId(@PathVariable Long id) {
        return adopcionCatalogoService.obtenerMascotaPorId(id);
    }

    @GetMapping("/ubicacion/{ubicacion}")
    public List<MascotaAdopcionInfoDTO> obtenerCatalogoPorUbicacion(@PathVariable String ubicacion) {
        return adopcionCatalogoService.obtenerCatalogoPorUbicacion(ubicacion);
    }

    @GetMapping("/especie/{especie}")
    public List<MascotaAdopcionInfoDTO> obtenerCatalogoPorEspecie(@PathVariable String especie) {
        return adopcionCatalogoService.obtenerCatalogoPorEspecie(especie);
    }
}