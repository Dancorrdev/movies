import contenido.Genero;
import contenido.Pelicula;
import contenido.ResumenContenido;
import exception.PeliculaExistenteExcepcion;
import plataforma.Plataforma;
import util.FileUtils;
import util.ScannerUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "🍿🍿🍿 Stream Dev 🎥🎥🎥";
    public static final String VERSION = "1.0.0";
    public static final int AGREGAR_CONTENIDO = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int VER_MAS_LARGO = 6;
    public static final int REPRODUCIR_CONTENIDO = 7;
    public static final int ELIMINAR_CONTENIDO = 8;
    public static final int SALIR = 9;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        loadMovies(plataforma);

        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de contenido disponible.");

        while (true) {
            int selectedOption = ScannerUtils.catchNumber("""
                    Seleccione una opción:
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por título
                    4. Buscar por género
                    5. Ver populares
                    6. Ver contenido más largo
                    7. Reproducir contenido
                    8. Eliminar contenido
                    9. Salir
                    """
            );
            switch (selectedOption) {
                case AGREGAR_CONTENIDO -> {
                    String title = ScannerUtils.catchText("Nombre del contenido");
                    Genero genre = ScannerUtils.catchGenre("Género del contenido");
                    int length = ScannerUtils.catchNumber("Duracion del contenido");
                    double rating = ScannerUtils.catchDecimal("Calificacion del Contenido");

                    try {
                        plataforma.add(new Pelicula(title, length, genre, rating));
                    } catch (PeliculaExistenteExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                    plataforma.add(new Pelicula(title, length, genre, rating));
                    System.out.println("Contenido agregado exitosamente.");
                }
                case MOSTRAR_TODO -> {
                    List<ResumenContenido> allContent = plataforma.getResumenes();
                    allContent.forEach(resumen -> System.out.println(resumen.toString()));
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreContenido = ScannerUtils.catchText("Ingrese el título del contenido a buscar");
                    Pelicula pelicula = plataforma.findByTitle(nombreContenido);
                    if (pelicula != null) {
                        System.out.println(pelicula.getFactSheet());
                    } else {
                        System.out.println("Contenido no encontrado dentro de" + NOMBRE_PLATAFORMA + ".");
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoDelContenido = ScannerUtils.catchGenre("Ingrese el género del contenido a buscar");
                    List<Pelicula> contenidoPorGenero = plataforma.findByGenre(generoDelContenido);
                    System.out.println(contenidoPorGenero.size() + " Contenidos encontrados en el género " + generoDelContenido);
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.getFactSheet() + "\n"));
                }
                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.catchNumber("¿Cuántos contenidos populares desea ver?");

                    List<Pelicula> contenidosPopulares = plataforma.getPopularContent(cantidad);
                    System.out.println("Top " + contenidosPopulares.size() + " contenidos populares:");
                    contenidosPopulares.forEach(contenido -> System.out.println(contenido.getFactSheet() + "\n"));
                }
                case VER_MAS_LARGO -> {
                    Pelicula peliculaMasLarga = plataforma.getLongestMovie();
                    if (peliculaMasLarga != null) {
                        System.out.println("El contenido más largo es: +\n");
                        System.out.println(peliculaMasLarga.getFactSheet() + "\n");
                    } else {
                        System.out.println("No hay contenido disponible en la plataforma.");
                    }
                }
                case REPRODUCIR_CONTENIDO -> {
                    String contenidoAReproducir = ScannerUtils.catchText("Ingrese el título del contenido a reproducir");
                    Pelicula pelicula = plataforma.findByTitle(contenidoAReproducir);
                    if (pelicula != null) {
                        plataforma.reproducir(pelicula);
                    } else {
                        System.out.println("Contenido no encontrado dentro de " + NOMBRE_PLATAFORMA + ".");
                    }
                }
                case ELIMINAR_CONTENIDO -> {
                    String contenidoABorrar = ScannerUtils.catchText("Ingrese el título del contenido a eliminar");
                    Pelicula pelicula = plataforma.findByTitle(contenidoABorrar);
                    if (pelicula != null) {
                        plataforma.remove(pelicula);
                        System.out.println("Contenido eliminado exitosamente.");
                    } else {
                        System.out.println("Contenido no encontrado dentro de " + NOMBRE_PLATAFORMA + ".");
                    }
                }

                case SALIR -> {
                    System.out.println("Saliendo de " + NOMBRE_PLATAFORMA + ". ¡Hasta luego!");
                    System.exit(0);
                }
            }
        }
    }

    private static void loadMovies(Plataforma plataforma) {
        plataforma.getContenido().addAll(FileUtils.readFile());
    }
}