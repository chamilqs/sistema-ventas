package appStructure.application.form.other.Reportes;

import Database.Connect.ConexionSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samil
 */
public class FormReporteProductos extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormReporteProductos() {
        initComponents();
        init();
        
    }
    
    private void init() {
        
        CargarTablaProductos();

    }
    
    public void CargarTablaProductos() {
    
        try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String query = "SELECT dv.idProducto as 'ID Producto', p.descripcion AS 'Nombre Producto', " +
                       "SUM(dv.cantidadP) AS 'Cantidad Vendida', " +
                       "SUM(p.precioU * dv.cantidadP) AS 'Total en Ventas', " +
                       "CONCAT(ROUND((SUM(dv.cantidadP) / (SUM(dv.cantidadP) + i.stock)) * 100, 2), '%') AS Porcentaje " +
                       "FROM detalleventa AS dv " +
                       "JOIN producto AS p ON dv.idProducto = p.id " +
                       "JOIN inventario AS i ON p.id = i.idProducto " +
                       "GROUP BY dv.idProducto, p.descripcion";

        try (Connection conectar = c.getConexion()) {
            
            Statement statement = conectar.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
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

        c.desconectar();
    } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(FormReporteProductos.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ReporteVenta1 = new javax.swing.JPanel();
        scrollTablaReporteProductos = new javax.swing.JScrollPane();
        TablaReporteProducto = new javax.swing.JTable();
        LblTitle = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1115, 760));

        ReporteVenta1.setBackground(new java.awt.Color(24, 39, 72));
        ReporteVenta1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaReporteProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "ID del Producto", "Nombre del Producto", "Cantidad Vendidas", "Total en Ventas", "Porcentaje"
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
        scrollTablaReporteProductos.setViewportView(TablaReporteProducto);
        if (TablaReporteProducto.getColumnModel().getColumnCount() > 0) {
            TablaReporteProducto.getColumnModel().getColumn(0).setResizable(false);
            TablaReporteProducto.getColumnModel().getColumn(0).setPreferredWidth(15);
            TablaReporteProducto.getColumnModel().getColumn(1).setResizable(false);
            TablaReporteProducto.getColumnModel().getColumn(2).setResizable(false);
            TablaReporteProducto.getColumnModel().getColumn(3).setResizable(false);
            TablaReporteProducto.getColumnModel().getColumn(4).setResizable(false);
        }

        ReporteVenta1.add(scrollTablaReporteProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1080, 580));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Reporte de Productos");
        ReporteVenta1.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReporteVenta1, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReporteVenta1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblTitle;
    private javax.swing.JPanel ReporteVenta1;
    private javax.swing.JTable TablaReporteProducto;
    private javax.swing.JScrollPane scrollTablaReporteProductos;
    // End of variables declaration//GEN-END:variables
}
