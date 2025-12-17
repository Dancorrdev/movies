package plataforma;

import contenido.Pelicula;

import java.util.ArrayList;
import java.util.Comparator;
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
        contenido.forEach(pelicula -> System.out.println(pelicula.getTitle()));
    }

    public void remove(Pelicula pelicula){
        contenido.remove(pelicula);
    }

    public Pelicula findByTitle(String title) {
       return contenido.stream()
                .filter(contenido -> contenido.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public List<Pelicula> findByGenre(String genre){
        return contenido.stream()
                .filter(contenido-> contenido.getGenre().equalsIgnoreCase(genre))
                .toList();
    }

    public int getDuracionTotal(){
        return contenido.stream()
                .mapToInt(Pelicula::getLength)
                .sum();
    }

    public List<Pelicula> getPopularContent(int cantidad){
        return contenido.stream().sorted(Comparator.comparingDouble(Pelicula::getRating).reversed())
                .limit(cantidad)
                .toList();
    }

    public Pelicula getLongestMovie(){
        return contenido.stream()
                .max(Comparator.comparingInt(Pelicula::getLength))
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
