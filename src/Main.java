import contenido.Pelicula;
import plataforma.Plataforma;
import plataforma.Usuario;
import util.ScannerUtils;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "🍿🍿🍿 Stream Dev 🎥🎥🎥";
    public static final String VERSION = "1.0.0";

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        String title = ScannerUtils.catchText("Nombre del contenido");
        String genre = ScannerUtils.catchText("Genero del contenido");
        int length = ScannerUtils.catchNumber("Duracion del contenido");
        double rating = ScannerUtils.catchDecimal("Calificacion del Contenido");

        Pelicula pelicula = new Pelicula(title, length, genre, rating);
        Pelicula pelicula1 = new Pelicula("The Matrix", 136, "Ciencia Ficción", 4.7);
        Pelicula pelicula2 = new Pelicula("Inception", 148, "Acción", 4.8);

        plataforma.add(pelicula);
        plataforma.add(pelicula1);
        plataforma.add(pelicula2);

        System.out.println("Numero de elementos en la plataforma: " + plataforma.getContenido().size());
        plataforma.remove(pelicula1);

        plataforma.showMovies();


        Usuario usuario = new Usuario("Jorge Gonzalez", "Jorge@devstream.com");


        usuario.watch(pelicula);

    }
}