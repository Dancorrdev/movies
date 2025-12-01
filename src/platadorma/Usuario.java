package platadorma;

import contenido.Pelicula;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario {
    public String name;
    public String email;
    public LocalDateTime registrationDate;

    public Usuario(String name, String email){
        this.name = name;
        this.email = email;
        this.registrationDate = LocalDateTime.now();
    }

    public void watch(Pelicula pelicula){
        System.out.println(name + " esta viendo ... ");
        pelicula.play();
    }
}
