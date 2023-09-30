/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appStructure.application.form.other.Historial;

import Database.Connect.ConexionSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samil
 */
public class FormHCompra extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormHCompra() {
        initComponents();
        init();
    }

    private void init(){
        DatosCompraHistorial();
    }
    private void actualizarTabla(JTable tabla, String query){
         DefaultTableModel model = new DefaultTableModel();
 
        try(Connection cn = c.getConexion()) {

            Statement stm = cn.createStatement();            
            ResultSet rs = stm.executeQuery(query);
            
            //RELLENADO DE LAS COLUMNAS
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            for (int column = 1; column <= columnCount; column++) {
                 model.addColumn(metaData.getColumnLabel(column));
             }

            //RELLENADO DE LAS FILAS
            while (rs.next()) {
                 Object[] rowData = new Object[columnCount];
                 for (int column = 1; column <= columnCount; column++) {
                     rowData[column - 1] = rs.getObject(column);
                 }
                 model.addRow(rowData);
             }
            
            tabla.setModel(model);
            c.desconectar();
        }  catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FormHCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DatosCompraHistorial() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        String query = "SELECT c.id AS idCompra, c.idProveedor, pr.nombre AS nombreProveedor, p.descripcion AS descripcionArticulo, c.total AS precioCompra, c.fecha "
             + "FROM compra AS c "
             + "INNER JOIN proveedor AS pr ON c.idProveedor = pr.id "
             + "INNER JOIN producto AS p ON p.id = c.idProveedor";

        try (Connection connection = c.getConexion()) 
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();


            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }


            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TablaCompraHistorial.setModel(modelo);

        // TablaCompraHistorial.getTableHeader().setResizingAllowed(false);
//        for (int i = 0; i < TablaCompraHistorial.getColumnCount(); i++) {
//            TablaCompraHistorial.getColumnModel().getColumn(i).setResizable(false);
//        }
            c.desconectar();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(FormHCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private void buscadorCompra() {
        String textoBusqueda = txtBuscador.getText();
        if (!textoBusqueda.isEmpty()){
            try {
                DefaultTableModel modeloCompra = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; // Evitar que las celdas sean editables, lo hice por code no deja el properties
                    }
                };

                String seleccion = (String) cmbCompras.getSelectedItem(); // Obtener la opción seleccionada del combo box

                String query = ""; // Inicializar la consulta
                switch (seleccion) {
                     case "ID" -> {                   
                       String id = txtBuscador.getText();                                  
                           query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c "
                                   + "INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id "
                                   + "INNER JOIN producto AS a ON a.id = dc.idProducto WHERE c.idProveedor = '" + id +"'";                    
                    }
                      case "Nombre" -> {
                        String nombre = txtBuscador.getText();
                        query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c "
                                + "INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id "
                                + "INNER JOIN producto AS a ON a.id = dc.idProducto WHERE a.descripcion = '" + nombre +"'";                    
                    } 
                    case "Dia" -> {
                        String dia = txtBuscador.getText();
                        if (isValidDay(dia)) {
                           query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c "
                                   + "INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id "
                                   + "INNER JOIN producto AS a ON a.id = dc.idProducto WHERE DAY(c.fecha) = '" + dia +"'";                    
                   
                        } else {
                            JOptionPane.showMessageDialog(this, "Día incorrecto. Ingrese un número entre 1 y 31.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }                        
                    }
                    case "Mes" -> {
                        String mes = txtBuscador.getText();
                        if (isValidMonth(mes)) {
                        query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c "
                                + "INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id "
                                + "INNER JOIN producto AS a ON a.id = dc.idProducto WHERE MONTH(c.fecha) = '" + mes +"'";                    
                    
                        } else {
                            JOptionPane.showMessageDialog(this, "Mes incorrecto. Ingrese un número entre 1 y 12.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }                        
                    }
                    case "Año" -> {
                        String ano = txtBuscador.getText();
                        if (isValidYear(ano)) {
                        query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c "
                                + "INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id "
                                + "INNER JOIN producto AS a ON a.id = dc.idProducto WHERE YEAR(c.fecha) = '" + ano +"'";                    
                    
                        } else {
                            JOptionPane.showMessageDialog(this, "Año incorrecto. Ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    case "Fecha completa" -> {
                        String fechaCompleta = txtBuscador.getText();
                        if (isValidDateFormat(fechaCompleta, "yyyy-MM-dd")) {
                        query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c "
                                + "INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id "
                                + "INNER JOIN producto AS a ON a.id = dc.idProducto WHERE c.fecha = '" + fechaCompleta +"'";   
                            
                        } else {
                            JOptionPane.showMessageDialog(this, "Formato de fecha completo incorrecto. Utilice AAAA-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
}
                    default -> {
                    }
                }

                try (Connection connection = c.getConexion()) {

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    // Agregar nombres de columnas al modelo
                    for (int column = 1; column <= columnCount; column++) {
                        modeloCompra.addColumn(metaData.getColumnLabel(column));

                    }

                    // Agregar filas al modelo
                    while (resultSet.next()) {
                        Object[] rowData = new Object[columnCount];
                        for (int column = 1; column <= columnCount; column++) {
                            rowData[column - 1] = resultSet.getObject(column);
                        }
                        modeloCompra.addRow(rowData);
                    }
                }

                TablaCompraHistorial.setModel(modeloCompra);
                c.desconectar();
            }  catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FormHCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
        
            JOptionPane.showMessageDialog(this, "Introduzca un valor para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }  
         
    private void actualizarTablaC(){
       
           String query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id INNER JOIN producto AS a ON a.id = dc.idProducto";
           
           actualizarTabla(TablaCompraHistorial,query);
       }
     
    // Confirmadores de formato
    private boolean isValidYear(String year) {
        try {
            int yearValue = Integer.parseInt(year);
            return yearValue >= 2022 && yearValue <= 5000; 
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isValidMonth(String month) {
        try {
            int monthValue = Integer.parseInt(month);
            return monthValue >= 1 && monthValue <= 12;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isValidDay(String day) {
        try {
            int dayValue = Integer.parseInt(day);
            return dayValue >= 1 && dayValue <= 31;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isValidDateFormat(String date, String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Content = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        TablaCompraHistorial = new javax.swing.JTable();
        LblTitle = new javax.swing.JLabel();
        cmbCompras = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        LblFormatoFecha = new javax.swing.JLabel();
        btnRecargarTabla = new Utils.ButtonRounded();

        setMinimumSize(new java.awt.Dimension(1115, 760));

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setMinimumSize(new java.awt.Dimension(1115, 760));
        Content.setPreferredSize(new java.awt.Dimension(1115, 760));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaCompraHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Compra", "ID Proveedor", "Nombre del proveedor", "Descripcion del producto", "Precio de Compra", "Fecha de la compra"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaCompraHistorial.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(TablaCompraHistorial);
        if (TablaCompraHistorial.getColumnModel().getColumnCount() > 0) {
            TablaCompraHistorial.getColumnModel().getColumn(0).setResizable(false);
            TablaCompraHistorial.getColumnModel().getColumn(0).setPreferredWidth(10);
            TablaCompraHistorial.getColumnModel().getColumn(1).setResizable(false);
            TablaCompraHistorial.getColumnModel().getColumn(1).setPreferredWidth(10);
            TablaCompraHistorial.getColumnModel().getColumn(2).setResizable(false);
            TablaCompraHistorial.getColumnModel().getColumn(3).setResizable(false);
            TablaCompraHistorial.getColumnModel().getColumn(4).setResizable(false);
            TablaCompraHistorial.getColumnModel().getColumn(5).setResizable(false);
        }

        Content.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1080, 580));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Historial de Compras");
        Content.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        cmbCompras.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 12)); // NOI18N
        cmbCompras.setForeground(new java.awt.Color(255, 255, 255));
        cmbCompras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Dia", "Mes", "Año", "Fecha completa" }));
        Content.add(cmbCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 127, 100, 30));
        Content.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(892, 127, 150, 30));

        btnBuscar.setText("BUSCAR");
        Content.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 127, 60, 30));

        LblFormatoFecha.setBackground(new java.awt.Color(255, 255, 255));
        LblFormatoFecha.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        LblFormatoFecha.setForeground(new java.awt.Color(255, 255, 255));
        LblFormatoFecha.setText("AAAA - MM - DD");
        LblFormatoFecha.setToolTipText("Formato de la fecha.");
        Content.add(LblFormatoFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 115, 80, 10));

        btnRecargarTabla.setBackground(new java.awt.Color(51, 204, 0));
        btnRecargarTabla.setForeground(new java.awt.Color(255, 255, 255));
        btnRecargarTabla.setText("RECARGAR TABLA");
        btnRecargarTabla.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRecargarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarTablaActionPerformed(evt);
            }
        });
        Content.add(btnRecargarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 60, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1115, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarTablaActionPerformed
        actualizarTablaC();
    }//GEN-LAST:event_btnRecargarTablaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblFormatoFecha;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JTable TablaCompraHistorial;
    private javax.swing.JButton btnBuscar;
    private Utils.ButtonRounded btnRecargarTabla;
    private javax.swing.JComboBox<String> cmbCompras;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
