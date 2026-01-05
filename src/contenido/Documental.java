package contenido;

public class Documental  extends Contenido implements Promotionable{

    private String narrator;

    public Documental(String title, int length, Genero genre) {
        super(title, length, genre);
    }



    public Documental(String title, int length, Genero genre, double rating, String narrator) {
        super(title, length, genre, rating);
        this.narrator = narrator;
    }

    @Override
    public void play() {
        System.out.println("Reproduciendo el documental: " + getTitle() + ", narrado por " + narrator);
    }

    @Override
    public String promote() {
        return "No te pierdas el documental: " + getTitle() + ", narrado por " + narrator + "!";
    }

    public String getNarrator() {
        return narrator;
    }
}
