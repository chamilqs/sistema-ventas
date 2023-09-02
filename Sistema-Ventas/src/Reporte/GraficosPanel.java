package Reporte;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Productos {
    String nombre;
    int ventas;
    double porcentaje;

    public Productos(String nombre, int ventas, double porcentaje) {
        this.nombre = nombre;
        this.ventas = ventas;
        this.porcentaje = porcentaje;
    }
}

public class GraficosPanel extends JPanel {

    private List<Productos> productos;

    public GraficosPanel() {
        productos = obtenerProductosMasVendidos();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (productos != null && !productos.isEmpty()) {
            dibujarGrafico(g);
        } else {
            dibujarGraficoVacio(g);
        }
    }

    private void dibujarGrafico(Graphics g) {
        int numProductos = productos.size();
        int barWidth = 65;
        int spacing = 30;
        int startX = 30;
        int maxHeight = 500;

        int maxBarHeight = maxHeight - 50;

        g.setFont(new Font("Arial", Font.PLAIN, 10));

        int totalVentas = 0;
        for (Productos producto : productos) {
            totalVentas += producto.ventas;
        }

        for (int i = 0; i < numProductos; i++) {
            Productos producto = productos.get(i);
            int barHeight = (int) (producto.porcentaje * maxBarHeight / 100);

            Color barColor = getColorForIndex(i);
            g.setColor(barColor);
            g.fillRect(startX + i * (barWidth + spacing), maxHeight - barHeight, barWidth, barHeight);

            g.setColor(Color.BLACK);
            g.drawString(producto.nombre, startX + i * (barWidth + spacing), maxHeight + 15);

            int porcentaje = (int) producto.porcentaje;
            String label = producto.ventas + " (" + porcentaje + "%)";
            int labelX = startX + i * (barWidth + spacing) + barWidth / 2 - 15;
            int labelY = maxHeight - barHeight - 5;
            g.drawString(label, labelX, labelY);
        }
    }

    private void dibujarGraficoVacio(Graphics g) {
        int numBarras = 10;
        int barWidth = 65;
        int spacing = 30;
        int startX = 30;
        int maxHeight = 500;

        g.setFont(new Font("Arial", Font.PLAIN, 10));

        for (int i = 0; i < numBarras; i++) {
            int barHeight = maxHeight - 50;
            Color barColor = Color.LIGHT_GRAY;
            g.setColor(barColor);
            g.fillRect(startX + i * (barWidth + spacing), maxHeight - barHeight, barWidth, barHeight);

            g.setColor(Color.BLACK);
            g.drawString("No data", startX + i * (barWidth + spacing), maxHeight + 15);

            String label = "0 (0%)";
            int labelX = startX + i * (barWidth + spacing) + barWidth / 2 - 15;
            int labelY = maxHeight - barHeight - 5;
            g.drawString(label, labelX, labelY);
        }
    }

    private Color getColorForIndex(int index) {
        Color[] colors = {
            new Color(255, 165, 0),   // Naranja
            new Color(255, 255, 0),   // Amarillo
            new Color(0, 255, 0),     // Verde
            new Color(0, 0, 255),     // Azul
            new Color(75, 0, 130),    // Índigo
            new Color(128, 0, 128),   // Púrpura
            new Color(255, 0, 255),   // Magenta
            new Color(0, 128, 128),   // Teal
            new Color(46, 139, 87),   // SeaGreen
            new Color(218, 112, 214)  // Orchid
        };

        int numColors = colors.length;
        int normalizedIndex = index % numColors;

        return colors[numColors - normalizedIndex - 1];
    }

 private static List<Productos> obtenerProductosMasVendidos() {
        List<Productos> productos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/paradafria", "root", "")) {
            String query = "SELECT p.descripcion AS nombre, " +
                           "SUM(dv.cantidadP) AS ventas, " +
                           "(SUM(dv.cantidadP) / (SELECT SUM(dv2.cantidadP) FROM detalleVenta AS dv2)) * 100 AS porcentaje " +
                           "FROM detalleVenta AS dv " +
                           "INNER JOIN producto AS p ON p.id = dv.idProducto " +
                           "GROUP BY dv.idProducto " +
                           "ORDER BY ventas DESC " +
                           "LIMIT 10;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String nombre = resultSet.getString("nombre");
                        int ventas = resultSet.getInt("ventas");
                        double porcentaje = resultSet.getDouble("porcentaje");
                        productos.add(new Productos(nombre, ventas, porcentaje));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

 
    }
