package dominio.excepciones;

public class FechaInvalidaException extends DatoInvalidoException {
    public FechaInvalidaException(String mensaje) {
        super(mensaje);
    }
}