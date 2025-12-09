package plataforma;

import contenido.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private String name;
    private List<Pelicula> contenido;

    public Plataforma(String name){
        this.name = name;
        this.contenido = new ArrayList<>();
    }

    public void add(Pelicula pelicula){
        contenido.add(pelicula);
    }

    public void showMovies(){
        for (Pelicula pelicula : contenido) {
            System.out.println(pelicula.getTitle());
        }
    }

    public void remove(Pelicula pelicula){
        contenido.remove(pelicula);
    }

    public Pelicula findByTitle(String title){
        for (Pelicula pelicula : contenido) {
            if (pelicula.getTitle().equalsIgnoreCase(title)) {
                return pelicula;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
