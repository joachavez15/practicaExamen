package dominio.excepciones;

public class AutorInvalidoException extends DatoInvalidoException {
    public AutorInvalidoException() {
        super("El autor del libro no puede ser nulo ni vacío");
    }
}