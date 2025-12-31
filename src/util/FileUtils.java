package util;

import contenido.Genero;
import contenido.Contenido;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;

public class FileUtils {

    public static final String FILE_NAME = "movies.txt";
    public static final String SEPARATOR = "|";

    public static void writeFile(Contenido contenido) {
        String linea = String.join(SEPARATOR,
                contenido.getTitle(),
                String.valueOf(contenido.getLength()),
                contenido.getGenre().name(),
                String.valueOf(contenido.getRating()),
                contenido.getFirstRelease().toString()
        );

        try {
            Files.writeString(Paths.get(FILE_NAME),
                    linea + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println("Error al escribir en el archivo. " + e.getMessage());
        }

    }


    public static List<Contenido> readFile() {
        List<Contenido> contenidodesdeArchivo = new java.util.ArrayList<>();


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

                    Contenido movie = new Contenido(title, length, genre, rating);
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
