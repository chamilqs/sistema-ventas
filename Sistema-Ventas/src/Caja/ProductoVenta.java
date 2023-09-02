package Caja;

public class ProductoVenta {
    private String idProducto;
    private String nombre;
    private String size;
    private float precio;
    private int cantidad;
    private float total;

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public ProductoVenta(String idProducto, String nombre, String size, float precio, int cantidad, float total) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.size = size;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    // Getter methods for the fields
}
