package plataforma;

import contenido.Contenido;

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

    public void watch(Contenido contenido){
        System.out.println(name + " esta viendo ... ");
        contenido.play();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
