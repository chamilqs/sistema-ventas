/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.DTO;

import java.util.List;

     

public class Producto {
     private String id;
     private String nombre;
     private float precio;
     private String size;
     private String prioridad;
     private List<String> idProveedores;
     
     

    public Producto(String id, String nombre, float precio, String size,String prioridad, List<String> idProveedores) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.size = size;
        this.prioridad = prioridad;
        this.idProveedores = idProveedores;
    }
    
    
    public Producto(String id, String nombre, float precio, String size,String prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.size = size; 
        this.prioridad = prioridad;
    }
    
    public Producto(String id, String nombre, float precio, String size,String prioridad, String idProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.size = size;
        this.prioridad = prioridad;
        this.idProveedores.add(idProveedor);
    }

    public List<String> getIdProveedores() {
        return idProveedores;
    }

    public void setIdProveedores(List<String> idProveedores) {
        this.idProveedores = idProveedores;
    }
    
     public void setIdProveedores(String idProveedore) {
        this.idProveedores.add(idProveedore);
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
     
     

}
