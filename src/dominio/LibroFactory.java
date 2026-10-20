package dominio;

public class LibroFactory {

    // Método de fábrica estático para crear un libro estándar
    public static Libro crearLibro(String id, String titulo, String autor, int anioPublicacion) {
        // Acá podrías agregar lógica adicional de validación o inicialización si fuera necesario
        return new Libro(id, titulo, autor, anioPublicacion);
    }
}
