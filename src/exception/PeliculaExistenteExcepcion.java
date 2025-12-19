package exception;

public class PeliculaExistenteExcepcion extends RuntimeException{
    public PeliculaExistenteExcepcion (String title){
        super("La pelicula " + title + " ya existe." );
    }
}
