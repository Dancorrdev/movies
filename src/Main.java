import contenido.Pelicula;
import plataforma.Plataforma;
import plataforma.Usuario;
import util.ScannerUtils;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "🍿🍿🍿 Stream Dev 🎥🎥🎥";
    public static final String VERSION = "1.0.0";
    public static final int AGREGAR_CONTENIDO = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int ELIMINAR_CONTENIDO = 4;
    public static final int SALIR = 5;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);


        while (true){
            int selectedOption = ScannerUtils.catchNumber("""
                            Seleccione una opción:
                            1. Agregar contenido
                            2. Mostrar todo
                            3. Buscar por título
                            4. Eliminar contenido
                            5. Salir"""
            );
            switch (selectedOption) {
                case AGREGAR_CONTENIDO -> {
                    String title = ScannerUtils.catchText("Nombre del contenido");
                    String genre = ScannerUtils.catchText("Genero del contenido");
                    int length = ScannerUtils.catchNumber("Duracion del contenido");
                    double rating = ScannerUtils.catchDecimal("Calificacion del Contenido");

                    plataforma.add(new Pelicula(title, length, genre, rating));
                    System.out.println("Contenido agregado exitosamente.");
                }
                case MOSTRAR_TODO -> plataforma.showMovies();

                case BUSCAR_POR_TITULO -> {
                    //falta implementar
                }
                case ELIMINAR_CONTENIDO ->   plataforma.remove(null); //falta implementar

                case SALIR -> {
                    System.out.println("Saliendo de "+ NOMBRE_PLATAFORMA + ". ¡Hasta luego!");
                    System.exit(0);
                }
            }

        }

//        Pelicula pelicula = new Pelicula(title, length, genre, rating);
//        Pelicula pelicula1 = new Pelicula("The Matrix", 136, "Ciencia Ficción", 4.7);
//        Pelicula pelicula2 = new Pelicula("Inception", 148, "Acción", 4.8);
//
//        plataforma.add(pelicula);
//        plataforma.add(pelicula1);
//        plataforma.add(pelicula2);

//        System.out.println("Numero de elementos en la plataforma: " + plataforma.getContenido().size());
//
//
//
//        Usuario usuario = new Usuario("Jorge Gonzalez", "Jorge@devstream.com");
//
//
//        usuario.watch(pelicula);

    }
}