package util;

import contenido.Documental;
import contenido.Genero;
import contenido.Contenido;
import contenido.Pelicula;

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

        String lineaFinal;
        if (contenido instanceof Documental documental) {
            lineaFinal = "DOCUMENTAL" + SEPARATOR + linea + SEPARATOR + documental.getNarrator();
        } else {
            lineaFinal = "PELICULA" + SEPARATOR + linea;
        }

        try {
            Files.writeString(Paths.get(FILE_NAME),
                    linea + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo. " + e.getMessage());
        }

    }


    public static List<Contenido> readFile() {
        List<Contenido> contenidodesdeArchivo = new java.util.ArrayList<>();


        try {
            List<String> lineas = Files.readAllLines(Paths.get(FILE_NAME));

            lineas.forEach(linea -> {
                String[] datos = linea.split("\\" + SEPARATOR);

                String tipoContenido = datos[0];


                if (("PELICULA".equals(tipoContenido) && datos.length == 6) || ("DOCUMENTAL".equals(tipoContenido) && datos.length == 7)) {

                    String title = datos[1];
                    int length = Integer.parseInt(datos[2]);
                    Genero genre = Genero.valueOf(datos[3].toUpperCase());
                    double rating = datos[4].isBlank() ? 0 : Double.parseDouble(datos[4]);
                    LocalDate releaseDate = LocalDate.parse(datos[5]);

                    Contenido contenido;
                    if ("PELICULA".equals(tipoContenido)) {
                        contenido = new Pelicula(title, length, genre, rating);

                    } else {
                        String narrator = datos[6];
                        contenido = new Documental(title, length, genre, rating, narrator);
                    }
                    contenido.setFirstRelease(releaseDate);

                    contenidodesdeArchivo.add(contenido);
                }
            });
        } catch (IOException e) {
            System.out.println("error al cargar el archivo. " + e.getMessage());
        }
        return contenidodesdeArchivo;
    }
}
