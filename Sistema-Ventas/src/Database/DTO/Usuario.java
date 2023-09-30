package Database.DTO;

import java.time.LocalDateTime;


public class Usuario {
     private String id;
     private String nombre;
     private String correo;
     private String password;
     private LocalDateTime login;
     private LocalDateTime logout;

    public Usuario(String id, String nombre, String correo, String password, LocalDateTime login, LocalDateTime logout, String nivelAcceso) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.login = login;
        this.logout = logout;
        this.nivelAcceso = nivelAcceso;
    }
     private String nivelAcceso;

    public Usuario(String id, String nombre, String correo, String password, String nivelAcceso) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.login = null;
        this.logout = null;
        this.nivelAcceso = nivelAcceso;
    }     

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLogin() {
        return login;
    }

    public void setLogin(LocalDateTime login) {
        this.login = login;
    }

    public LocalDateTime getLogout() {
        return logout;
    }

    public void setLogout(LocalDateTime logout) {
        this.logout = logout;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }   
}
