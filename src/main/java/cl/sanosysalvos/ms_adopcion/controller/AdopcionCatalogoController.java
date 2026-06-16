package cl.sanosysalvos.ms_adopcion.controller;

import cl.sanosysalvos.ms_adopcion.model.MascotaAdopcion;
import cl.sanosysalvos.ms_adopcion.service.AdopcionCatalogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adopcion")
@CrossOrigin(origins = "*")
public class AdopcionCatalogoController {

    private final AdopcionCatalogoService adopcionCatalogoService;

    public AdopcionCatalogoController(AdopcionCatalogoService adopcionCatalogoService) {
        this.adopcionCatalogoService = adopcionCatalogoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<MascotaAdopcion> registrarMascotaAdopcion(
            @RequestBody MascotaAdopcion mascotaAdopcion
    ) {
        return ResponseEntity.ok(
                adopcionCatalogoService.registrarMascotaAdopcion(mascotaAdopcion)
        );
    }

    @GetMapping("/catalogo")
    public ResponseEntity<List<MascotaAdopcion>> obtenerCatalogoAdopcion() {
        return ResponseEntity.ok(adopcionCatalogoService.obtenerCatalogoAdopcion());
    }

    @GetMapping("/catalogo/{id}")
    public ResponseEntity<MascotaAdopcion> obtenerMascotaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(adopcionCatalogoService.obtenerMascotaPorId(id));
    }

    @GetMapping("/catalogo/ubicacion/{ubicacion}")
    public ResponseEntity<List<MascotaAdopcion>> obtenerCatalogoPorUbicacion(
            @PathVariable String ubicacion
    ) {
        return ResponseEntity.ok(
                adopcionCatalogoService.obtenerCatalogoPorUbicacion(ubicacion)
        );
    }

    @GetMapping("/catalogo/especie/{especie}")
    public ResponseEntity<List<MascotaAdopcion>> obtenerCatalogoPorEspecie(
            @PathVariable String especie
    ) {
        return ResponseEntity.ok(
                adopcionCatalogoService.obtenerCatalogoPorEspecie(especie)
        );
    }

        @GetMapping("/veterinaria/{contacto}")
    public ResponseEntity<List<MascotaAdopcion>> obtenerMascotasPorVeterinaria(
            @PathVariable String contacto
    ) {
        return ResponseEntity.ok(
                adopcionCatalogoService.obtenerMascotasPorContactoVeterinaria(contacto)
        );
    }

    @PutMapping("/{id}/adoptada")
    public ResponseEntity<MascotaAdopcion> marcarMascotaComoAdoptada(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                adopcionCatalogoService.marcarComoAdoptada(id)
        );
    }
}