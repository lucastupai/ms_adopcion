package cl.sanosysalvos.ms_adopcion.service;

import cl.sanosysalvos.ms_adopcion.dto.MascotaAdopcionRequest;
import cl.sanosysalvos.ms_adopcion.model.EstadoMascotaAdopcion;
import cl.sanosysalvos.ms_adopcion.model.MascotaAdopcion;
import cl.sanosysalvos.ms_adopcion.repository.MascotaAdopcionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MascotaAdopcionServiceTest {

    @Mock
    private MascotaAdopcionRepository mascotaAdopcionRepository;

    @InjectMocks
    private MascotaAdopcionService mascotaAdopcionService;

    private MascotaAdopcionRequest request;
    private MascotaAdopcion mascota;

    @BeforeEach
    void setUp() {
        request = new MascotaAdopcionRequest();
        request.setNombre("Luna");
        request.setTipo("Perro");
        request.setEdad("2 años");
        request.setUbicacion("Maipú, Región Metropolitana");
        request.setFundacion("Fundación Patitas Seguras");
        request.setDescripcion("Perrita tranquila y cariñosa.");
        request.setAspectosPositivos("Es sociable y tranquila.");
        request.setAspectosConsiderar("Necesita espacio y paciencia.");
        request.setImagen("/img/perro-default.jpg");

        mascota = new MascotaAdopcion(
                request.getNombre(),
                request.getTipo(),
                request.getEdad(),
                request.getUbicacion(),
                request.getFundacion(),
                request.getDescripcion(),
                request.getAspectosPositivos(),
                request.getAspectosConsiderar(),
                request.getImagen()
        );
    }

    @Test
    @DisplayName("Debe crear una mascota en el catálogo con estado DISPONIBLE")
    void crearMascotaCatalogo_deberiaGuardarMascotaConEstadoDisponible() {
        when(mascotaAdopcionRepository.save(any(MascotaAdopcion.class))).thenReturn(mascota);

        MascotaAdopcion resultado = mascotaAdopcionService.crearMascotaCatalogo(request);

        assertNotNull(resultado);
        assertEquals("Luna", resultado.getNombre());
        assertEquals("Perro", resultado.getTipo());
        assertEquals(EstadoMascotaAdopcion.DISPONIBLE, resultado.getEstado());

        verify(mascotaAdopcionRepository, times(1)).save(any(MascotaAdopcion.class));
    }

    @Test
    @DisplayName("Debe listar todas las mascotas del catálogo")
    void listarTodas_deberiaRetornarListaDeMascotas() {
        when(mascotaAdopcionRepository.findAll()).thenReturn(List.of(mascota));

        List<MascotaAdopcion> resultado = mascotaAdopcionService.listarTodas();

        assertEquals(1, resultado.size());
        assertEquals("Luna", resultado.get(0).getNombre());

        verify(mascotaAdopcionRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe listar solo mascotas con estado DISPONIBLE")
    void listarDisponibles_deberiaRetornarMascotasDisponibles() {
        when(mascotaAdopcionRepository.findByEstado(EstadoMascotaAdopcion.DISPONIBLE))
                .thenReturn(List.of(mascota));

        List<MascotaAdopcion> resultado = mascotaAdopcionService.listarDisponibles();

        assertEquals(1, resultado.size());
        assertEquals(EstadoMascotaAdopcion.DISPONIBLE, resultado.get(0).getEstado());

        verify(mascotaAdopcionRepository, times(1))
                .findByEstado(EstadoMascotaAdopcion.DISPONIBLE);
    }

    @Test
    @DisplayName("Debe buscar una mascota por ID cuando existe")
    void buscarPorId_cuandoExiste_deberiaRetornarMascota() {
        when(mascotaAdopcionRepository.findById(1L)).thenReturn(Optional.of(mascota));

        MascotaAdopcion resultado = mascotaAdopcionService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Luna", resultado.getNombre());

        verify(mascotaAdopcionRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar error cuando se busca una mascota que no existe")
    void buscarPorId_cuandoNoExiste_deberiaLanzarExcepcion() {
        when(mascotaAdopcionRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mascotaAdopcionService.buscarPorId(99L);
        });

        assertEquals("Mascota del catálogo no encontrada", exception.getMessage());

        verify(mascotaAdopcionRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Debe filtrar mascotas por tipo")
    void buscarPorTipo_deberiaRetornarMascotasPorTipo() {
        when(mascotaAdopcionRepository.findByTipo("Perro")).thenReturn(List.of(mascota));

        List<MascotaAdopcion> resultado = mascotaAdopcionService.buscarPorTipo("Perro");

        assertEquals(1, resultado.size());
        assertEquals("Perro", resultado.get(0).getTipo());

        verify(mascotaAdopcionRepository, times(1)).findByTipo("Perro");
    }

    @Test
    @DisplayName("Debe filtrar mascotas por ubicación")
    void buscarPorUbicacion_deberiaRetornarMascotasPorUbicacion() {
        when(mascotaAdopcionRepository.findByUbicacionContainingIgnoreCase("Maipú"))
                .thenReturn(List.of(mascota));

        List<MascotaAdopcion> resultado = mascotaAdopcionService.buscarPorUbicacion("Maipú");

        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).getUbicacion().contains("Maipú"));

        verify(mascotaAdopcionRepository, times(1))
                .findByUbicacionContainingIgnoreCase("Maipú");
    }

    @Test
    @DisplayName("Debe actualizar los datos de una mascota del catálogo")
    void actualizarMascota_deberiaModificarLosDatosDeLaMascota() {
        MascotaAdopcionRequest requestActualizado = new MascotaAdopcionRequest();
        requestActualizado.setNombre("Max");
        requestActualizado.setTipo("Perro");
        requestActualizado.setEdad("3 años");
        requestActualizado.setUbicacion("Santiago Centro");
        requestActualizado.setFundacion("Rescate Animal Centro");
        requestActualizado.setDescripcion("Perrito activo y amistoso.");
        requestActualizado.setAspectosPositivos("Juguetón y sociable.");
        requestActualizado.setAspectosConsiderar("Necesita paseos diarios.");
        requestActualizado.setImagen("/img/max.jpg");

        when(mascotaAdopcionRepository.findById(1L)).thenReturn(Optional.of(mascota));
        when(mascotaAdopcionRepository.save(any(MascotaAdopcion.class))).thenReturn(mascota);

        MascotaAdopcion resultado = mascotaAdopcionService.actualizarMascota(1L, requestActualizado);

        assertEquals("Max", resultado.getNombre());
        assertEquals("Santiago Centro", resultado.getUbicacion());
        assertEquals("Rescate Animal Centro", resultado.getFundacion());

        verify(mascotaAdopcionRepository, times(1)).findById(1L);
        verify(mascotaAdopcionRepository, times(1)).save(mascota);
    }

    @Test
    @DisplayName("Debe cambiar el estado de una mascota a NO_DISPONIBLE")
    void cambiarEstado_deberiaActualizarEstadoDeLaMascota() {
        when(mascotaAdopcionRepository.findById(1L)).thenReturn(Optional.of(mascota));
        when(mascotaAdopcionRepository.save(any(MascotaAdopcion.class))).thenReturn(mascota);

        MascotaAdopcion resultado = mascotaAdopcionService.cambiarEstado(
                1L,
                EstadoMascotaAdopcion.NO_DISPONIBLE
        );

        assertEquals(EstadoMascotaAdopcion.NO_DISPONIBLE, resultado.getEstado());

        verify(mascotaAdopcionRepository, times(1)).findById(1L);
        verify(mascotaAdopcionRepository, times(1)).save(mascota);
    }

    @Test
    @DisplayName("Debe eliminar una mascota existente del catálogo")
    void eliminarMascota_deberiaEliminarMascotaExistente() {
        when(mascotaAdopcionRepository.findById(1L)).thenReturn(Optional.of(mascota));

        mascotaAdopcionService.eliminarMascota(1L);

        verify(mascotaAdopcionRepository, times(1)).findById(1L);
        verify(mascotaAdopcionRepository, times(1)).delete(mascota);
    }
}