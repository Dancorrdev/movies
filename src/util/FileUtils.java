package util;

import contenido.Genero;
import contenido.Pelicula;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class FileUtils {

    public static final String FILE_NAME = "movies.txt";
    public static final String SEPARATOR = "|";


    public static List<Pelicula> readFile() {
        List<Pelicula> contenidodesdeArchivo = new java.util.ArrayList<>();


        try {
            List<String> lineas = Files.readAllLines(Paths.get(FILE_NAME));

            lineas.forEach(linea -> {
                String[] datos = linea.split("\\" + SEPARATOR);
                if (datos.length == 5) {
                    String title = datos[0];
                    int length = Integer.parseInt(datos[1]);
                    Genero genre = Genero.valueOf(datos[2].toUpperCase());
                    double rating = datos[3].isBlank() ? 0 : Double.parseDouble(datos[3]);
                    LocalDate releaseDate = LocalDate.parse(datos[4]);

                    Pelicula movie = new Pelicula(title, length, genre, rating);
                    movie.setFirstRelease(releaseDate);

                    contenidodesdeArchivo.add(movie);
                }
            });
        } catch (IOException e) {
            System.out.println("error al cargar el archivo. " + e.getMessage());
        }
        return contenidodesdeArchivo;
    }
}
