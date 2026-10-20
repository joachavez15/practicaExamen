package dominio;

import dominio.excepciones.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SocioTest {

    private Socio socio;

    @BeforeEach
    void preparar() {
        socio = new Socio("s1", "Ana");
    }

    // ---------- Creación ----------

    @Test
    void socioNuevoNoTienePrestamos() {
        assertEquals(0, socio.getPrestamosActivos());
    }

    @Test
    void socioNuevoPuedeTomarPrestamo() {
        assertTrue(socio.puedeTomarPrestamo());
    }

    @Test
    void idNuloLanzaExcepcion() {
        assertThrows(IdInvalidoException.class, () -> new Socio(null, "Ana"));
    }

    @Test
    void idVacioLanzaExcepcion() {
        assertThrows(IdInvalidoException.class, () -> new Socio("", "Ana"));
    }

    @Test
    void idConSoloEspaciosLanzaExcepcion() {
        assertThrows(IdInvalidoException.class, () -> new Socio("   ", "Ana"));
    }

    @Test
    void nombreNuloLanzaExcepcion() {
        assertThrows(NombreInvalidoException.class, () -> new Socio("s1", null));
    }

    @Test
    void nombreVacioLanzaExcepcion() {
        assertThrows(NombreInvalidoException.class, () -> new Socio("s1", ""));
    }

    @Test
    void nombreConSoloEspaciosLanzaExcepcion() {
        assertThrows(NombreInvalidoException.class, () -> new Socio("s1", "   "));
    }

    @Test
    void erroresDeDatosHeredanDeDatoInvalidoException() {
        assertThrows(DatoInvalidoException.class, () -> new Socio("", "Ana"));
    }

    @Test
    void todosLosErroresHeredanDeBibliotecaException() {
        assertThrows(BibliotecaException.class, () -> new Socio("s1", null));
    }

    // ---------- Préstamos ----------

    @Test
    void registrarPrestamoSumaUno() {
        socio.registrarPrestamo();
        assertEquals(1, socio.getPrestamosActivos());
    }

    @Test
    void permiteHastaTresPrestamos() {
        socio.registrarPrestamo();
        socio.registrarPrestamo();
        socio.registrarPrestamo();

        assertEquals(3, socio.getPrestamosActivos());
        assertFalse(socio.puedeTomarPrestamo());
    }

    @Test
    void cuartoPrestamoLanzaExcepcion() {
        socio.registrarPrestamo();
        socio.registrarPrestamo();
        socio.registrarPrestamo();

        assertThrows(ReglaDeNegocioException.class, () -> socio.registrarPrestamo());
    }

    @Test
    void cuartoPrestamoFallidoNoCambiaElContador() {
        socio.registrarPrestamo();
        socio.registrarPrestamo();
        socio.registrarPrestamo();

        assertThrows(ReglaDeNegocioException.class, () -> socio.registrarPrestamo());
        assertEquals(3, socio.getPrestamosActivos());
    }

    // ---------- Devoluciones ----------

    @Test
    void registrarDevolucionRestaUno() {
        socio.registrarPrestamo();
        socio.registrarDevolucion();
        assertEquals(0, socio.getPrestamosActivos());
    }

    @Test
    void devolverSinPrestamosLanzaExcepcion() {
        assertThrows(ReglaDeNegocioException.class, () -> socio.registrarDevolucion());
    }

    @Test
    void devolverSinPrestamosNoDejaElContadorEnNegativo() {
        assertThrows(ReglaDeNegocioException.class, () -> socio.registrarDevolucion());
        assertEquals(0, socio.getPrestamosActivos());
    }

    @Test
    void despuesDeDevolverPuedeTomarOtroPrestamo() {
        socio.registrarPrestamo();
        socio.registrarPrestamo();
        socio.registrarPrestamo();
        socio.registrarDevolucion();

        assertTrue(socio.puedeTomarPrestamo());
    }
}