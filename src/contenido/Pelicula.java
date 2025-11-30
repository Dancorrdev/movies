package contenido;

public class Pelicula {
    public String title;
    public String description;
    public int length;
    public String genre;
    public int firstRelease;
    public double rating;
    public boolean available;

    public void play(){
        System.out.println("Playing " + title);
    }

    public String getFactSheet(){
        return title + " (" + firstRelease + ")\n" +
                "Genero: " + genre + "\n" +
                "Calificacion" ;
    }
}
