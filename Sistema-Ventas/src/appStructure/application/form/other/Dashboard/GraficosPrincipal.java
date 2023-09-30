package appStructure.application.form.other.Dashboard;

import Database.Connect.ConexionSingleton;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Productos {
    String nombre;
    int ventas;
    double porcentaje;  
    Color color;
    

    public Productos(String nombre, int ventas, double porcentaje, Color color) {
        this.nombre = nombre;
        this.ventas = ventas;
        this.porcentaje = porcentaje;  
        this.color = color;
    }
}

public class GraficosPrincipal extends JPanel {

    private List<Productos> productos;
    static ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    public GraficosPrincipal() {
        productos = obtenerProductosDesdeBaseDeDatos();
        calcularPorcentajeVentas();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (productos != null && !productos.isEmpty()) {
            dibujarGraficoPastel(g);
            dibujarCuadroNombres(g);
        } else {
            dibujarGraficoVacio(g);
        }
    }

    private void calcularPorcentajeVentas() {
        int totalVentas = productos.stream().mapToInt(p -> p.ventas).sum();

        for (Productos producto : productos) {
            double porcentajeVentas = (double) producto.ventas / totalVentas * 100;
            producto.porcentaje = porcentajeVentas;
        }
    }

    private void dibujarGraficoPastel(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 3;
        int radius = 150;
        int totalVentas = productos.stream().mapToInt(p -> p.ventas).sum();
        int startAngle = 0;

        for (Productos producto : productos) {
            int arcAngle = (int) (360.0 * producto.porcentaje / 100);
            g.setColor(producto.color);
            g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, arcAngle);
            startAngle += arcAngle;
        }
    }

   private void dibujarCuadroNombres(Graphics g) {
    int startX = getWidth() - 600;
    int startY = 300;
    int lineHeight = 20;
    int totalVentas = productos.stream().mapToInt(p -> p.ventas).sum();

    for (int i = productos.size() - 1; i >= 0; i--) {
        Productos producto = productos.get(i);
        Color color = producto.color;

        g.setColor(color);
        g.fillRect(startX, startY + (productos.size() - 1 - i) * lineHeight, 15, 15);

        g.setColor(Color.WHITE);

        // Formatear el porcentaje con un solo dígito decimal
        String porcentajeFormateado = String.format("%.1f%%", producto.porcentaje);

        String nombreYPorcentaje = producto.nombre + " (" + porcentajeFormateado + ")";
        g.drawString(nombreYPorcentaje, startX + 20, startY + (productos.size() - 1 - i) * lineHeight + 12);
    }
}
    private void dibujarGraficoVacio(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 3;
        int radius = 150;

        g.setColor(Color.LIGHT_GRAY);
        g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, 0, 360);

        g.setColor(Color.WHITE);
        g.drawString("No hay datos disponibles", centerX - 80, centerY + 5);
    }

    private static List<Productos> obtenerProductosDesdeBaseDeDatos() {
        List<Productos> productos = new ArrayList<>();
        Color[] colors = {
            new Color(255, 165, 0),  
            new Color(255, 255, 0),  
            new Color(0, 255, 0),     
            new Color(0, 0, 255),     
            new Color(75, 0, 130),    
            new Color(128, 0, 128),   
            new Color(255, 0, 255),   
            new Color(0, 128, 128),   
            new Color(46, 139, 87),   
            new Color(218, 112, 214)  
        };

        try (Connection conectar = c.getConexion()) {
            String query = "SELECT p.descripcion AS 'nombre', "
                 + "SUM(dv.cantidadP) AS 'ventas', "
                 + "ROUND((SUM(dv.cantidadP) / (SUM(dv.cantidadP) + i.stock)) * 100, 2) AS Porcentaje "
                 + "FROM detalleVenta AS dv "
                 + "JOIN producto AS p ON p.id = dv.idProducto "
                 + "JOIN inventario AS i ON p.id = i.idProducto "
                 + "GROUP BY dv.idProducto, p.descripcion "
                 + "ORDER BY 'ventas' DESC "
                 + "LIMIT 5";

            try (PreparedStatement statement = conectar.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    int colorIndex = 0;
                    while (resultSet.next() && colorIndex < colors.length) {
                        String nombre = resultSet.getString("nombre");
                        int ventas = resultSet.getInt("ventas");
                        double porcentaje = resultSet.getDouble("Porcentaje");  

                        Color color = colors[colorIndex];
                        productos.add(new Productos(nombre, ventas, porcentaje, color));
                        colorIndex++;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return productos;
    }

}