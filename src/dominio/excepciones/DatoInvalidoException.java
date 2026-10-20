package dominio.excepciones;

public class DatoInvalidoException extends BibliotecaException {
    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}