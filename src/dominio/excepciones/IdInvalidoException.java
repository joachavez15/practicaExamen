package dominio.excepciones;

public class IdInvalidoException extends DatoInvalidoException {
    public IdInvalidoException(String entidad) {
        super("El id de " + entidad + " no puede ser nulo ni vacío");
    }
}