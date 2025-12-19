package plataforma;

import contenido.Genero;
import contenido.Pelicula;
import contenido.ResumenContenido;
import exception.PeliculaExistenteExcepcion;

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

    public void add(Pelicula pelicula) {
        Pelicula contenido = this.findByTitle(pelicula.getTitle());
        if (contenido != null) {
            throw new PeliculaExistenteExcepcion(pelicula.getTitle());
        }
        this.contenido.add(pelicula);
    }

    public List<String> showMovies() {
        return contenido.stream()
                .map(Pelicula::getTitle)
                .toList();
    }

    public List<ResumenContenido> getResumenes(){
        return  contenido.stream().map(c-> new ResumenContenido(c.getTitle(), c.getLength(), c.getGenre()))
                .toList();
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

    public List<Pelicula> findByGenre(Genero genre){
        return contenido.stream()
                .filter(contenido-> contenido.getGenre().equals(genre))
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
