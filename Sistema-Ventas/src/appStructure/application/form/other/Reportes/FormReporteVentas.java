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
public class FormReporteVentas extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormReporteVentas() {
        initComponents();
        init();
    }
    
    private void init() {
        
        CargarTablaVentas();

    }
        
    public void CargarTablaVentas() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        String query =  "SELECT v.id AS IDVenta, v.nombreCliente AS NombreCliente, GROUP_CONCAT(p.descripcion ORDER BY p.descripcion ASC SEPARATOR ', ') AS Productos," +
                        " v.total AS TotalVenta," +
                        " v.fecha AS FechaVenta" +
                        " FROM venta v" +
                        " JOIN detalleventa dv ON v.id = dv.idVenta" +
                        " JOIN producto p ON dv.idProducto = p.id" +
                        " GROUP BY v.id, v.nombreCliente, v.total, v.fecha" +
                        " ORDER BY v.id;";

        try (Connection conectar = c.getConexion()) 
        {
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
            
            c.desconectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormReporteVentas.class.getName()).log(Level.SEVERE, null, ex);
        }

        TablaReporteVenta.setModel(modelo);

        TablaReporteVenta.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < TablaReporteVenta.getColumnCount(); i++) {
            TablaReporteVenta.getColumnModel().getColumn(i).setResizable(false);
        }
    } catch (SQLException e){
        System.out.println(e);
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Content = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        TablaReporteVenta = new javax.swing.JTable();
        LblTitle = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1115, 760));

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaReporteVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "ID Venta", "Nombre del cliente", "Total de la venta", "Fecha", "Productos Vendidos"
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
        scroll.setViewportView(TablaReporteVenta);
        if (TablaReporteVenta.getColumnModel().getColumnCount() > 0) {
            TablaReporteVenta.getColumnModel().getColumn(0).setResizable(false);
            TablaReporteVenta.getColumnModel().getColumn(0).setPreferredWidth(15);
            TablaReporteVenta.getColumnModel().getColumn(1).setResizable(false);
            TablaReporteVenta.getColumnModel().getColumn(1).setPreferredWidth(15);
            TablaReporteVenta.getColumnModel().getColumn(2).setResizable(false);
            TablaReporteVenta.getColumnModel().getColumn(3).setResizable(false);
            TablaReporteVenta.getColumnModel().getColumn(4).setResizable(false);
        }

        Content.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1080, 580));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Reporte de Ventas");
        Content.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JTable TablaReporteVenta;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
