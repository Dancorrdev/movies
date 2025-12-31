package contenido;

public class Documental  extends Contenido{

    private String narrator;

    public Documental(String title, int length, Genero genre) {
        super(title, length, genre);
    }

    public Documental(String title, int length, Genero genre, double rating, String narrator) {
        super(title, length, genre, rating);
        this.narrator = narrator;
    }

    public String getNarrator() {
        return narrator;
    }
}
