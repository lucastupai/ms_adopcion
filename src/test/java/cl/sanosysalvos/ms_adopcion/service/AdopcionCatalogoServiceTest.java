package cl.sanosysalvos.ms_adopcion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import cl.sanosysalvos.ms_adopcion.model.MascotaAdopcion;
import cl.sanosysalvos.ms_adopcion.repository.MascotaAdopcionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AdopcionCatalogoServiceTest {

    @Mock
    private MascotaAdopcionRepository repository;

    @InjectMocks
    private AdopcionCatalogoService service;

    @Test
    @DisplayName("Al registrar a Simón, si no tiene estado, el sistema le asigna DISPONIBLE")
    void testRegistrarMascota_AsignaDisponible() {
        // Arrange
        MascotaAdopcion simonEntrada = new MascotaAdopcion();
        simonEntrada.setNombre("Simón");
        simonEntrada.setEspecie("Gato");
        // Nota: A propósito no le ponemos estado para que actúe tu IF del service

        MascotaAdopcion simonGuardado = new MascotaAdopcion();
        simonGuardado.setId(1L);
        simonGuardado.setNombre("Simón");
        simonGuardado.setEstado("DISPONIBLE"); // Como debería salir de la BD

        when(repository.save(any(MascotaAdopcion.class))).thenReturn(simonGuardado);

        // Act
        MascotaAdopcion resultado = service.registrarMascotaAdopcion(simonEntrada);

        // Assert
        assertNotNull(resultado);
        assertEquals("DISPONIBLE", resultado.getEstado());
        assertEquals(1L, resultado.getId());
        verify(repository, times(1)).save(any(MascotaAdopcion.class));
    }

    @Test
    @DisplayName("Debería encontrar a Simón por su ID")
    void testObtenerMascotaPorId_Exitoso() {
        MascotaAdopcion simon = new MascotaAdopcion();
        simon.setId(1L);
        simon.setNombre("Simón");

        // Simulamos que la BD sí encuentra el registro
        when(repository.findById(1L)).thenReturn(Optional.of(simon));

        MascotaAdopcion resultado = service.obtenerMascotaPorId(1L);

        assertNotNull(resultado);
        assertEquals("Simón", resultado.getNombre());
    }

    @Test
    @DisplayName("Debería lanzar RuntimeException si se busca un ID que no existe")
    void testObtenerMascotaPorId_Falla() {
        // Simulamos que la BD devuelve vacío
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Verificamos que tu código lance exactamente la excepción que programaste
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.obtenerMascotaPorId(99L);
        });

        assertEquals("Mascota en adopción no encontrada", exception.getMessage());
    }

    @Test
    @DisplayName("Debería buscar perros o gatos en la sede Antonio Varas 666")
    void testObtenerCatalogoPorUbicacion() {
        MascotaAdopcion simon = new MascotaAdopcion();
        simon.setNombre("Simón");
        simon.setUbicacion("Antonio Varas 666");

        when(repository.findByUbicacionContainingIgnoreCaseAndEstadoIgnoreCase("Antonio Varas", "DISPONIBLE"))
                .thenReturn(Arrays.asList(simon));

        List<MascotaAdopcion> resultados = service.obtenerCatalogoPorUbicacion("Antonio Varas");

        assertFalse(resultados.isEmpty());
        assertEquals("Simón", resultados.get(0).getNombre());
        verify(repository).findByUbicacionContainingIgnoreCaseAndEstadoIgnoreCase("Antonio Varas", "DISPONIBLE");
    }
}