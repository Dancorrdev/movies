package contenido;

import java.time.LocalDate;

public class Pelicula {
    public String title;
    public String description;
    public int length;
    public String genre;
    public LocalDate firstRelease;
    public double rating;
    public boolean available;

    public Pelicula(String title, int length, String genre){
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.firstRelease = LocalDate.now();
        this.available = true;
    }

    public Pelicula(String title, int length, String genre, double rating) {
        this(title, length, genre);
        this.rate(rating);
    }

    public void play(){
        System.out.println("Playing " + title);
    }

    public String getFactSheet(){
        return title + " (" + firstRelease + ")\n" +
                "Genero: " + genre + "\n" +
                "Calificacion: " + rating;
    }

    public void rate(double rating){
        if (rating >= 0 && rating <= 5 ) {
            this.rating = rating;
        }
    }

    public boolean isPopular() {
        return rating >= 4;
    }
}
