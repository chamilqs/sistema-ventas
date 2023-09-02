package Reporte;

import Connect.Conexion;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angelo
 */
//class Producto {

public class ReportesGeneral extends javax.swing.JPanel {
    
  //  public List<Producto> productos;
    
    public ReportesGeneral() {
    initComponents();
    incializar();
    

   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materialTabbed1 = new Utils.MaterialTabbed();
        ReporteVenta = new javax.swing.JPanel();
        scrollTablaReporteVenta = new javax.swing.JScrollPane();
        TablaReporteVenta = new javax.swing.JTable();
        LblUtilidad4 = new javax.swing.JLabel();
        ReporteVenta1 = new javax.swing.JPanel();
        scrollTablaReporteVenta1 = new javax.swing.JScrollPane();
        TablaReporteProducto = new javax.swing.JTable();
        LblUtilidad5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(24, 39, 72));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        materialTabbed1.setBackground(new java.awt.Color(24, 39, 72));
        materialTabbed1.setForeground(new java.awt.Color(255, 255, 255));
        materialTabbed1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N

        ReporteVenta.setBackground(new java.awt.Color(24, 39, 72));
        ReporteVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaReporteVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Idventa", "IdProducto", "Nombre Producto", "Cantidad", "Porcentaje"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaReporteVenta.getTableHeader().setReorderingAllowed(false);
        scrollTablaReporteVenta.setViewportView(TablaReporteVenta);

        ReporteVenta.add(scrollTablaReporteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 980, 500));

        LblUtilidad4.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad4.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblUtilidad4.setForeground(new java.awt.Color(255, 255, 255));
        LblUtilidad4.setText("Reporte de Venta");
        ReporteVenta.add(LblUtilidad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, -1));

        materialTabbed1.addTab("Reporte de Venta ", ReporteVenta);

        ReporteVenta1.setBackground(new java.awt.Color(24, 39, 72));
        ReporteVenta1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaReporteProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "IdProducto", "Nombre Producto", "Cantidad Vendidas", "Total en Ventas", "Porcentaje"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaReporteProducto.getTableHeader().setReorderingAllowed(false);
        scrollTablaReporteVenta1.setViewportView(TablaReporteProducto);
        if (TablaReporteProducto.getColumnModel().getColumnCount() > 0) {
            TablaReporteProducto.getColumnModel().getColumn(0).setResizable(false);
            TablaReporteProducto.getColumnModel().getColumn(1).setResizable(false);
            TablaReporteProducto.getColumnModel().getColumn(2).setResizable(false);
            TablaReporteProducto.getColumnModel().getColumn(3).setResizable(false);
            TablaReporteProducto.getColumnModel().getColumn(4).setResizable(false);
        }

        ReporteVenta1.add(scrollTablaReporteVenta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 980, 500));

        LblUtilidad5.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad5.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblUtilidad5.setForeground(new java.awt.Color(255, 255, 255));
        LblUtilidad5.setText("Reporte de Producto");
        ReporteVenta1.add(LblUtilidad5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, -1));

        materialTabbed1.addTab("Reporte de Producto", ReporteVenta1);

        add(materialTabbed1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 660));
    }// </editor-fold>//GEN-END:initComponents

    
    public void incializar(){
        
        GraficosPanel V = new GraficosPanel();
V.setBackground(new Color(255, 255, 255));
materialTabbed1.addTab("Grafica ventas", V);

// Supongo que estos métodos obtienen los datos y realizan operaciones
DatosReporteVentaConPorcentaje();
DatosReporteProductoConPorcentaje();

// Luego, después de realizar las operaciones en los datos del reporte, actualiza la vista
V.repaint(); // Redibuja el panel con los nuevos datos


        
        
    }
    
    //Reporte Venta
    
    public void DatosReporteVentaConPorcentaje() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        String query = "SELECT dv.idVenta, dv.idProducto, p.descripcion AS nombreProducto, dv.cantidadP AS cantidadVendida, " +
                       "(p.precioU * dv.cantidadP) AS precioTotal, " +
                       "((p.precioU * dv.cantidadP) / SUM(p.precioU * dv.cantidadP) OVER ()) * 100 AS porcentaje " +
                       "FROM detalleventa AS dv " +
                       "JOIN producto AS p ON dv.idProducto = p.id";

        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TablaReporteVenta.setModel(modelo);

        TablaReporteVenta.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < TablaReporteVenta.getColumnCount(); i++) {
            TablaReporteVenta.getColumnModel().getColumn(i).setResizable(false);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReportesGeneral.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public void DatosReporteProductoConPorcentaje() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String query = "SELECT dv.idProducto as IdProducto, p.descripcion AS 'Nombre Producto', " +
                       "SUM(dv.cantidadP) AS 'Cantidad Vendida', " +
                       "SUM(p.precioU * dv.cantidadP) AS 'Total en Ventas', " +
                       "CONCAT(ROUND((SUM(dv.cantidadP) / (SUM(dv.cantidadP) + i.stock)) * 100, 2), '%') AS Porcentaje " +
                       "FROM detalleventa AS dv " +
                       "JOIN producto AS p ON dv.idProducto = p.id " +
                       "JOIN inventario AS i ON p.id = i.idProducto " +
                       "GROUP BY dv.idProducto, p.descripcion";

        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TablaReporteProducto.setModel(modelo);

        TablaReporteProducto.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < TablaReporteProducto.getColumnCount(); i++) {
            TablaReporteProducto.getColumnModel().getColumn(i).setResizable(false);
        }

        
    } catch (SQLException ex) {
        Logger.getLogger(ReportesGeneral.class.getName()).log(Level.SEVERE, null, ex);
    }
}



    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblUtilidad4;
    private javax.swing.JLabel LblUtilidad5;
    private javax.swing.JPanel ReporteVenta;
    private javax.swing.JPanel ReporteVenta1;
    private javax.swing.JTable TablaReporteProducto;
    private javax.swing.JTable TablaReporteVenta;
    private Utils.MaterialTabbed materialTabbed1;
    private javax.swing.JScrollPane scrollTablaReporteVenta;
    private javax.swing.JScrollPane scrollTablaReporteVenta1;
    // End of variables declaration//GEN-END:variables
}