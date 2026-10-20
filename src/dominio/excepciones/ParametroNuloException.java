package dominio.excepciones;

public class ParametroNuloException extends BibliotecaException {
    public ParametroNuloException(String parametro) {
        super("El parámetro '" + parametro + "' no puede ser nulo");
    }
}