package dominio.excepciones;

public class NombreInvalidoException extends DatoInvalidoException {
    public NombreInvalidoException() {
        super("El nombre del socio no puede ser nulo ni vacío");
    }
}