import contenido.Genero;
import contenido.Pelicula;
import exception.PeliculaExistenteExcepcion;
import plataforma.Plataforma;
import util.ScannerUtils;

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
                    List<String> allContent = plataforma.showMovies();
                    System.out.println("Contenidos disponibles en " + NOMBRE_PLATAFORMA + ":");
                    allContent.forEach(System.out::println);
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
        plataforma.add(new Pelicula("The Shawshank Redemption", 142, Genero.DRAMA, 4.9));
        plataforma.add(new Pelicula("The Godfather", 175, Genero.CRIMEN, 4.8));
        plataforma.add(new Pelicula("The Dark Knight", 152, Genero.ACCION, 4.7));
        plataforma.add(new Pelicula("Pulp Fiction", 154, Genero.CRIMEN, 4.6));
        plataforma.add(new Pelicula("Sherk", 158, Genero.ANIMADA, 4.3));
        plataforma.add(new Pelicula("Toy Story", 81, Genero.ANIMADA, 4.5));
        plataforma.add(new Pelicula("Finding Nemo", 100, Genero.ANIMADA, 4.4));
        plataforma.add(new Pelicula("Inception", 148, Genero.CIENCIA_FICCION, 4.6));
        plataforma.add(new Pelicula("The Matrix", 136, Genero.CIENCIA_FICCION, 4.7));
        plataforma.add(new Pelicula("Interstellar", 169, Genero.CIENCIA_FICCION, 4.5));
        plataforma.add(new Pelicula("Gladiator", 155, Genero.ACCION, 4.4));
        plataforma.add(new Pelicula("Star Wars: Episode IV - A New Hope", 121, Genero.CIENCIA_FICCION, 4.6));
        plataforma.add(new Pelicula("Star Wars: Episode III - Revenge of the Sith", 140, Genero.CIENCIA_FICCION, 4.2));
        plataforma.add(new Pelicula("Avengers: Endgame", 181, Genero.ACCION, 4.7));
        plataforma.add(new Pelicula("Harry Potter and the Secret of the Chamber", 161, Genero.FANTASIA, 4.5));
        plataforma.add(new Pelicula("Justice League Snyder Cut", 242, Genero.ACCION, 4.1));
        plataforma.add(new Pelicula("Spiderman : Into the Spider-Verse", 117, Genero.ANIMADA, 4.8));
        plataforma.add(new Pelicula("Dragon Ball Evolution", 85, Genero.ACCION, 2.3));
        plataforma.add(new Pelicula("Catwoman", 104, Genero.ACCION, 1.9));
        plataforma.add(new Pelicula("John Carter", 132, Genero.CIENCIA_FICCION, 3.1));


    }
}