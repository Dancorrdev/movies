import contenido.*;
import exception.PeliculaExistenteExcepcion;
import plataforma.Plataforma;
import util.FileUtils;
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
    public static final int REPRODUCIR_CONTENIDO = 7;
    public static final int BUSCAR_POR_TIPO = 8;
    public static final int ELIMINAR_CONTENIDO = 9;
    public static final int SALIR = 10;

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
                    8. Buscar por tipo de contenido
                    9. Eliminar contenido
                    10. Salir
                    """
            );
            switch (selectedOption) {
                case AGREGAR_CONTENIDO -> {
                    int tipoDeContenido = ScannerUtils.catchNumber("""
                            Seleccione el tipo de contenido a agregar:
                            1. Película
                            2. Documental
                            """);
                    String title = ScannerUtils.catchText("Nombre del contenido");
                    Genero genre = ScannerUtils.catchGenre("Género del contenido");
                    int length = ScannerUtils.catchNumber("Duracion del contenido");
                    double rating = ScannerUtils.catchDecimal("Calificacion del Contenido");

                    try {
                        if (tipoDeContenido == 1) {
                            plataforma.add(new Pelicula(title, length, genre, rating));
                        } else if (tipoDeContenido == 2) {
                            String narrator = ScannerUtils.catchText("Narrador del documental");
                            plataforma.add(new Documental(title, length, genre, rating, narrator));
                        } else {
                            System.out.println("Tipo de contenido no válido. Operación cancelada.");
                            break;
                        }
                    } catch (PeliculaExistenteExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Contenido agregado exitosamente.");
                }
                case MOSTRAR_TODO -> {
                    List<ResumenContenido> allContent = plataforma.getResumenes();
                    allContent.forEach(resumen -> System.out.println(resumen.toString()));
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreContenido = ScannerUtils.catchText("Ingrese el título del contenido a buscar");
                    Contenido contenido = plataforma.findByTitle(nombreContenido);
                    if (contenido != null) {
                        System.out.println(contenido.getFactSheet());
                    } else {
                        System.out.println("Contenido no encontrado dentro de" + NOMBRE_PLATAFORMA + ".");
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoDelContenido = ScannerUtils.catchGenre("Ingrese el género del contenido a buscar");
                    List<Contenido> contenidoPorGenero = plataforma.findByGenre(generoDelContenido);
                    System.out.println(contenidoPorGenero.size() + " Contenidos encontrados en el género " + generoDelContenido);
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.getFactSheet() + "\n"));
                }
                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.catchNumber("¿Cuántos contenidos populares desea ver?");

                    List<Contenido> contenidosPopulares = plataforma.getPopularContent(cantidad);
                    System.out.println("Top " + contenidosPopulares.size() + " contenidos populares:");
                    contenidosPopulares.forEach(contenido -> System.out.println(contenido.getFactSheet() + "\n"));
                }
                case VER_MAS_LARGO -> {
                    Contenido contenidoMasLarga = plataforma.getLongestMovie();
                    if (contenidoMasLarga != null) {
                        System.out.println("El contenido más largo es: +\n");
                        System.out.println(contenidoMasLarga.getFactSheet() + "\n");
                    } else {
                        System.out.println("No hay contenido disponible en la plataforma.");
                    }
                }
                case REPRODUCIR_CONTENIDO -> {
                    String contenidoAReproducir = ScannerUtils.catchText("Ingrese el título del contenido a reproducir");
                    Contenido contenido = plataforma.findByTitle(contenidoAReproducir);
                    if (contenido != null) {
                        plataforma.reproducir(contenido);
                    } else {
                        System.out.println("Contenido no encontrado dentro de " + NOMBRE_PLATAFORMA + ".");
                    }
                }

                case BUSCAR_POR_TIPO -> {
                    int tipoDeContenido = ScannerUtils.catchNumber("""
                            Seleccione el tipo de contenido a buscar:
                            1. Película
                            2. Documental
                            """);
                    if (tipoDeContenido == 1) {
                        List<Pelicula> peliculas = plataforma.getPeliculas();
                        System.out.println("Películas encontradas:");
                        peliculas.forEach(contenido -> System.out.println(contenido.getFactSheet() + "\n"));
                    } else if (tipoDeContenido == 2) {
                        List<Documental> documentales = plataforma.getDocumentales();
                        System.out.println("Documentales encontrados:");
                        documentales.forEach(contenido -> System.out.println(contenido.getFactSheet() + "\n"));
                    } else {
                        System.out.println("Tipo de contenido no válido.");
                    }
                }
                case ELIMINAR_CONTENIDO -> {
                    String contenidoABorrar = ScannerUtils.catchText("Ingrese el título del contenido a eliminar");
                    Contenido contenido = plataforma.findByTitle(contenidoABorrar);
                    if (contenido != null) {
                        plataforma.remove(contenido);
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