package plataforma;

import contenido.*;
import exception.PeliculaExistenteExcepcion;
import util.FileUtils;

import java.util.*;

public class Plataforma {
    private String name;
    private List<Contenido> contenido;
    private Map<Contenido, Integer> visualizaciones;

    public Plataforma(String name) {
        this.name = name;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }

    public void add(Contenido elemento) {
        Contenido contenido = this.findByTitle(elemento.getTitle());
        System.out.println(contenido);
        if (contenido != null) {
            throw new PeliculaExistenteExcepcion(elemento.getTitle());
        }

        FileUtils.writeFile(elemento);
        this.contenido.add(elemento);
    }

    public void reproducir(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitle() + " se ha reproducido " + (conteoActual + 1) + " veces.");
        this.contarVisializaciones(contenido);
        contenido.play();
    }

    private void contarVisializaciones(Contenido contenido) {
        visualizaciones.put(contenido, visualizaciones.getOrDefault(contenido, 0) + 1);
    }

    public List<String> showMovies() {
        return contenido.stream()
                .map(Contenido::getTitle)
                .toList();
    }

    public List<ResumenContenido> getResumenes() {
        return contenido.stream().map(c -> new ResumenContenido(c.getTitle(), c.getLength(), c.getGenre()))
                .toList();
    }

    public void remove(Contenido contenido) {
        this.contenido.remove(contenido);
    }

    public Contenido findByTitle(String title) {
        return contenido.stream()
                .filter(contenido -> contenido.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public List<Contenido> findByGenre(Genero genre) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenre().equals(genre))
                .toList();
    }

    public int getDuracionTotal() {
        return contenido.stream()
                .mapToInt(Contenido::getLength)
                .sum();
    }

    public List<Contenido> getPopularContent(int cantidad) {
        return contenido.stream().sorted(Comparator.comparingDouble(Contenido::getRating).reversed())
                .limit(cantidad)
                .toList();
    }

    public Contenido getLongestMovie() {
        return contenido.stream()
                .max(Comparator.comparingInt(Contenido::getLength))
                .orElse(null);
    }

    public List<Pelicula> getPeliculas() {
        return  contenido.stream()
                .filter(contenido -> contenido instanceof Pelicula )
                .map(contenidoFiltrado -> (Pelicula) contenidoFiltrado)
                .toList();
    }


    public List<Documental> getDocumentales() {
        return  contenido.stream()
                .filter(contenido -> contenido instanceof Documental )
                .map(contenidoFiltrado -> (Documental) contenidoFiltrado)
                .toList();
    }

    public List<Promotionable> getPromotionables() {
        return  contenido.stream()
                .filter(contenido -> contenido instanceof Promotionable )
                .map(contenidoProm -> (Promotionable) contenidoProm)
                .toList();
    }

    public String getName() {
        return name;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }
}
