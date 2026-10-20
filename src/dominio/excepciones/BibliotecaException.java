package dominio.excepciones;

public class BibliotecaException extends RuntimeException {
    public BibliotecaException(String mensaje) {
        super(mensaje);
    }
}
