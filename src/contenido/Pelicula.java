package contenido;

public class Pelicula  extends Contenido{
    public Pelicula(String title, int length, Genero genre, double rating) {
        super(title, length, genre, rating);
    }

    @Override
    public void play() {
        System.out.println("Reproduciendo la película: " + getTitle());
    }


}
