package cl.sanosysalvos.ms_adopcion.controller;

import cl.sanosysalvos.ms_adopcion.dto.MascotaAdopcionRequest;
import cl.sanosysalvos.ms_adopcion.model.EstadoMascotaAdopcion;
import cl.sanosysalvos.ms_adopcion.model.MascotaAdopcion;
import cl.sanosysalvos.ms_adopcion.service.MascotaAdopcionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo-adopcion")
@CrossOrigin(origins = "*")
public class MascotaAdopcionController {

    private final MascotaAdopcionService mascotaAdopcionService;

    public MascotaAdopcionController(MascotaAdopcionService mascotaAdopcionService) {
        this.mascotaAdopcionService = mascotaAdopcionService;
    }

    @PostMapping
    public MascotaAdopcion crearMascotaCatalogo(@Valid @RequestBody MascotaAdopcionRequest request) {
        return mascotaAdopcionService.crearMascotaCatalogo(request);
    }

    @GetMapping
    public List<MascotaAdopcion> listarTodas() {
        return mascotaAdopcionService.listarTodas();
    }

    @GetMapping("/disponibles")
    public List<MascotaAdopcion> listarDisponibles() {
        return mascotaAdopcionService.listarDisponibles();
    }

    @GetMapping("/{id}")
    public MascotaAdopcion buscarPorId(@PathVariable Long id) {
        return mascotaAdopcionService.buscarPorId(id);
    }

    @GetMapping("/tipo/{tipo}")
    public List<MascotaAdopcion> buscarPorTipo(@PathVariable String tipo) {
        return mascotaAdopcionService.buscarPorTipo(tipo);
    }

    @GetMapping("/ubicacion/{ubicacion}")
    public List<MascotaAdopcion> buscarPorUbicacion(@PathVariable String ubicacion) {
        return mascotaAdopcionService.buscarPorUbicacion(ubicacion);
    }

    @PutMapping("/{id}")
    public MascotaAdopcion actualizarMascota(
            @PathVariable Long id,
            @Valid @RequestBody MascotaAdopcionRequest request) {
        return mascotaAdopcionService.actualizarMascota(id, request);
    }

    @PutMapping("/{id}/estado/{estado}")
    public MascotaAdopcion cambiarEstado(
            @PathVariable Long id,
            @PathVariable EstadoMascotaAdopcion estado) {
        return mascotaAdopcionService.cambiarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    public void eliminarMascota(@PathVariable Long id) {
        mascotaAdopcionService.eliminarMascota(id);
    }
}