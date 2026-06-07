package cl.sanosysalvos.ms_adopcion.controller;

import cl.sanosysalvos.ms_adopcion.dto.AdopcionRequest;
import cl.sanosysalvos.ms_adopcion.model.Adopcion;
import cl.sanosysalvos.ms_adopcion.service.AdopcionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adopciones")
@CrossOrigin(origins = "*")
public class AdopcionController {

    private final AdopcionService adopcionService;

    public AdopcionController(AdopcionService adopcionService) {
        this.adopcionService = adopcionService;
    }

    @PostMapping
    public Adopcion crearSolicitud(@Valid @RequestBody AdopcionRequest request) {
        return adopcionService.crearSolicitud(request);
    }

    @GetMapping
    public List<Adopcion> listarTodas() {
        return adopcionService.listarTodas();
    }

    @GetMapping("/interesado/{usuarioId}")
    public List<Adopcion> listarPorUsuarioInteresado(@PathVariable Long usuarioId) {
        return adopcionService.listarPorUsuarioInteresado(usuarioId);
    }

    @GetMapping("/publicador/{usuarioId}")
    public List<Adopcion> listarPorUsuarioPublicador(@PathVariable Long usuarioId) {
        return adopcionService.listarPorUsuarioPublicador(usuarioId);
    }

    @GetMapping("/mascota/{mascotaId}")
    public List<Adopcion> listarPorMascota(@PathVariable Long mascotaId) {
        return adopcionService.listarPorMascota(mascotaId);
    }

    @PutMapping("/{id}/contactado")
    public Adopcion marcarComoContactado(@PathVariable Long id) {
        return adopcionService.marcarComoContactado(id);
    }

    @PutMapping("/{id}/descartar")
    public Adopcion descartarSolicitud(@PathVariable Long id) {
        return adopcionService.descartarSolicitud(id);
    }

    @PutMapping("/{id}/cancelar")
    public Adopcion cancelarSolicitud(@PathVariable Long id) {
        return adopcionService.cancelarSolicitud(id);
    }
}