package dominio.excepciones;

public class TituloInvalidoException extends DatoInvalidoException {
    public TituloInvalidoException() {
        super("El título del libro no puede ser nulo ni vacío");
    }
}