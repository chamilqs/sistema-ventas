
package Database.DTO;

import java.util.List;

public class Proveedor {
    private String id,nombre,correo;
    private List<String> telefonos;

    public Proveedor(String id, String nombre, String correo, List<String> telefonos) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefonos = telefonos;
    }
    
    public Proveedor(String id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefonos.add(telefono);
    }
    
    public Proveedor(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
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

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }
    
     public void setTelefonos(String telefono) {
        this.telefonos.add(telefono);
    }
    
    
    
}
