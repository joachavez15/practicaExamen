package dominio;

import dominio.excepciones.*;
import java.time.LocalDate;

public class Libro {
    public static final int ANIO_MINIMO = 1450;

    private final String id;
    private final String titulo;
    private final String autor;
    private final int anioPublicacion;
    private Socio prestadoA;

    Libro(String id, String titulo, String autor, int anioPublicacion) {
        if (id == null || id.isBlank()) {
            throw new IdInvalidoException("libro");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new TituloInvalidoException();
        }
        if (autor == null || autor.isBlank()) {
            throw new AutorInvalidoException();
        }
        int anioActual = LocalDate.now().getYear();
        if (anioPublicacion < ANIO_MINIMO || anioPublicacion > anioActual) {
            throw new AnioDePublicacionInvalidoException(anioPublicacion, ANIO_MINIMO, anioActual);
        }
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    public void prestarA(Socio socio) {
        if (socio == null) {
            throw new ParametroNuloException("socio");
        }
        if (!estaDisponible()) {
            throw new ReglaDeNegocioException("El libro " + id + " no está disponible");
        }
        if (!socio.puedeTomarPrestamo()) {
            throw new ReglaDeNegocioException(
                    "El socio " + socio.getId() + " ya tiene el máximo de " + Socio.MAX_PRESTAMOS + " préstamos activos");
        }

        socio.registrarPrestamo();
        this.prestadoA = socio;
    }

    public void devolverDe(Socio socio) {
        if (socio == null) {
            throw new ParametroNuloException("socio");
        }
        if (estaDisponible()) {
            throw new ReglaDeNegocioException("El libro " + id + " no está prestado, no se puede devolver");
        }
        if (prestadoA != socio) {
            throw new ReglaDeNegocioException(
                    "El socio " + socio.getId() + " no es quien tiene prestado el libro " + id);
        }

        socio.registrarDevolucion();
        this.prestadoA = null;
    }

    public boolean estaDisponible() { return prestadoA == null; }
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnioPublicacion() { return anioPublicacion; }
}