package dominio.excepciones;

public class ReglaDeNegocioException extends BibliotecaException {
    public ReglaDeNegocioException(String mensaje) {
        super(mensaje);
    }
}