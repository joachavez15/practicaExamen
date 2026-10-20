package dominio;

import dominio.excepciones.*;

public class Socio {
    public static final int MAX_PRESTAMOS = 3;

    private final String id;
    private final String nombre;
    private int prestamosActivos = 0;

    public Socio(String id, String nombre) {
        if (id == null || id.isBlank()) {
            throw new IdInvalidoException("socio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new NombreInvalidoException();
        }
        this.id = id;
        this.nombre = nombre;
    }

    public boolean puedeTomarPrestamo() {
        return prestamosActivos < MAX_PRESTAMOS;
    }

    public void registrarPrestamo() {
        if (!puedeTomarPrestamo()) {
            throw new ReglaDeNegocioException(
                    "El socio " + id + " ya tiene el máximo de " + MAX_PRESTAMOS + " préstamos activos");
        }
        prestamosActivos++;
    }

    public void registrarDevolucion() {
        if (prestamosActivos == 0) {
            throw new ReglaDeNegocioException("El socio " + id + " no tiene préstamos activos");
        }
        prestamosActivos--;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPrestamosActivos() { return prestamosActivos; }
}