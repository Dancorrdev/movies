import contenido.Pelicula;
import platadorma.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("🍿🍿🍿 Stream Dev 🎥🎥🎥");

        Pelicula pelicula = new Pelicula();

        pelicula.title = "Spider-man into the spider-verse";
        pelicula.firstRelease = LocalDate.of(2018, 7, 15);
        pelicula.genre = "Animada";
        pelicula.rate(4.8);

        Usuario usuario = new Usuario();
        usuario.name = "Robert ";
        usuario.registrationDate = LocalDateTime.of(2025,12,5,13,15,22 );

        System.out.println(pelicula.getFactSheet());


    }
}