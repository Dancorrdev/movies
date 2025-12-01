import contenido.Pelicula;
import platadorma.Usuario;
import util.ScannerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("🍿🍿🍿 Stream Dev 🎥🎥🎥");

        String title = ScannerUtils.catchText("nombre del contenido");
        String genre = ScannerUtils.catchText("Genero del contenido");
        int length = ScannerUtils.catchNumber("Duracion del contenido");
        double rating = ScannerUtils.catchDecimal("Calificacion del Contenido");


        Pelicula pelicula = new Pelicula();

        pelicula.title = title;
        pelicula.firstRelease = LocalDate.of(2018, 7, 15);
        pelicula.genre = genre;
        pelicula.rate(rating);

        System.out.println(pelicula.getFactSheet());


        Usuario usuario = new Usuario();
        usuario.name = "Robert ";
        usuario.registrationDate = LocalDateTime.of(2025,12,5,13,15,22 );

        usuario.watch(pelicula);

    }
}