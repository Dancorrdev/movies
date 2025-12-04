package contenido;

import java.time.LocalDate;

public class Pelicula {
    private String title;
    private String description;
    private int length;
    private String genre;
    private LocalDate firstRelease;
    private double rating;
    private boolean available;

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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getLength() {
        return length;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getFirstRelease() {
        return firstRelease;
    }

    public double getRating() {
        return rating;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFirstRelease(LocalDate firstRelease) {
        this.firstRelease = firstRelease;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
