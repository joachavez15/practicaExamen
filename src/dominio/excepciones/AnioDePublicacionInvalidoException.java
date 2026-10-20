package dominio.excepciones;

public class AnioDePublicacionInvalidoException extends DatoInvalidoException {
    public AnioDePublicacionInvalidoException(int anio, int minimo, int maximo) {
        super("El año de publicación " + anio + " está fuera de rango (" + minimo + " a " + maximo + ")");
    }
}