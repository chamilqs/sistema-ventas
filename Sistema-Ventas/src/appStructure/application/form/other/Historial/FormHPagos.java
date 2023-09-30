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
import javax.swing.table.DefaultTableModel;
import appStructure.application.Application;
import appStructure.application.form.other.Contabilidad.FormCxP;

/**
 *
 * @author Samil
 */
public class FormHPagos extends javax.swing.JPanel {

    // Este form registra los pagos realizados de cuentas por pagar que tiene de la empresa    
    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormHPagos() {
        initComponents();
        init();
    }

    private void init(){
    
        DatosPagadosHistorial();
    
    }
    public void DatosPagadosHistorial() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        String query = "SELECT p.idCompra, c.idProveedor, pr.nombre AS nombreProveedor, c.deuda, p.monto AS montoPagado, p.fecha AS fechaPago "
             + "FROM pagocp AS p "
             + "INNER JOIN cuentaporpagar AS c ON p.idCompra = c.idCompra "
             + "INNER JOIN proveedor AS pr ON c.idProveedor = pr.id";

        try (Connection connection = c.getConexion();
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

        TablaPagosHistorial.setModel(modelo);
        c.desconectar();
//        TablaPagosHistorial.getTableHeader().setResizingAllowed(false);
//        for (int i = 0; i < TablaPagosHistorial.getColumnCount(); i++) {
//            TablaPagosHistorial.getColumnModel().getColumn(i).setResizable(false);
//        }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(FormHPagos.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private void buscador() {
     String textoBusqueda = txtBuscador.getText();
        if (!textoBusqueda.isEmpty()){
        
            try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

            String seleccion = (String) cmbPagos.getSelectedItem(); // Obtener la opción seleccionada del combo box

            String query = ""; 
            switch (seleccion) {
                 case "ID" -> {

                    String id = txtBuscador.getText();
                        query = "SELECT p.idCompra, pr.nombre AS 'Nombre Proveedor', c.deuda, p.monto AS 'Monto Pagado', p.fecha AS 'Fecha Pago'"
                        + "FROM pagocp AS p "
                        + "INNER JOIN cuentaporpagar AS c ON p.idCompra = c.idCompra "
                        + "INNER JOIN proveedor AS pr ON c.idProveedor = pr.id"
                        + " WHERE p.idCompra = '" + id + "'";
                }
                  case "Proveedor" -> {                 
                    String nombreProveedor = txtBuscador.getText();
                        query = "SELECT p.idCompra, pr.nombre AS 'Nombre Proveedor', c.deuda, p.monto AS 'Monto Pagado', p.fecha AS 'Fecha Pago'"
                        + "FROM pagocp AS p "
                        + "INNER JOIN cuentaporpagar AS c ON p.idCompra = c.idCompra "
                        + "INNER JOIN proveedor AS pr ON c.idProveedor = pr.id"
                        + " WHERE pr.nombre = '" + nombreProveedor + "'";
                } 
                case "Dia" -> {
                    String dia = txtBuscador.getText();
                    if (isValidDay(dia)) {
                        query = "SELECT p.idCompra, pr.nombre AS 'Nombre Proveedor', c.deuda, p.monto AS 'Monto Pagado', p.fecha AS 'Fecha Pago'"
                        + "FROM pagocp AS p "
                        + "INNER JOIN cuentaporpagar AS c ON p.idCompra = c.idCompra "
                        + "INNER JOIN proveedor AS pr ON c.idProveedor = pr.id"
                        + " WHERE DAY(p.fecha) = '" + dia + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Día incorrecto. Ingrese un número entre 1 y 31.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Mes" -> {
                    String mes = txtBuscador.getText();
                    if (isValidMonth(mes)) {
                        query = "SELECT p.idCompra, pr.nombre AS 'Nombre Proveedor', c.deuda, p.monto AS 'Monto Pagado', p.fecha AS 'Fecha Pago'"
                       + "FROM pagocp AS p "
                       + "INNER JOIN cuentaporpagar AS c ON p.idCompra = c.idCompra "
                       + "INNER JOIN proveedor AS pr ON c.idProveedor = pr.id"
                        + " WHERE MONTH(p.fecha) = '" + mes + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Mes incorrecto. Ingrese un número entre 1 y 12.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Año" -> {
                    String ano = txtBuscador.getText();
                    if (isValidYear(ano)) {
                        query = "SELECT p.idCompra, pr.nombre AS 'Nombre Proveedor', c.deuda, p.monto AS 'Monto Pagado', p.fecha AS 'Fecha Pago'"
                        + "FROM pagocp AS p "
                        + "INNER JOIN cuentaporpagar AS c ON p.idCompra = c.idCompra "
                        + "INNER JOIN proveedor AS pr ON c.idProveedor = pr.id"
                        + " WHERE YEAR(p.fecha) = '" + ano + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Año incorrecto. Ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Fecha completa" -> {
                    String fechaCompleta = txtBuscador.getText();
                    if (isValidDateFormat(fechaCompleta, "yyyy-MM-dd")) {
                        query = "SELECT p.idCompra AS 'ID Compra', pr.nombre AS 'Nombre Proveedor', c.deuda AS 'Monto Adeudado', p.monto AS 'Monto Pagado', p.fecha AS 'Fecha Pago'"
                       + "FROM pagocp AS p "
                       + "INNER JOIN cuentaporpagar AS c ON p.idCompra = c.idCompra "
                       + "INNER JOIN proveedor AS pr ON c.idProveedor = pr.id"
                        + " WHERE p.fecha = '" + fechaCompleta + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Formato de fecha completo incorrecto. Utilice AAAA-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                default -> {
                     JOptionPane.showMessageDialog(this, "Seleccione una opción válida para realizar la búsqueda.", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }
            }

            try (Connection connection = c.getConexion()) {
                Statement statement = connection.createStatement();
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
            }   catch (SQLException ex) {
                    Logger.getLogger(FormHUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }

            TablaPagosHistorial.setModel(modelo);
            c.desconectar();
            }  catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FormHUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        } else{        
            JOptionPane.showMessageDialog(this, "Introduzca un valor para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
       
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
        TablaPagosHistorial = new javax.swing.JTable();
        LblTitle = new javax.swing.JLabel();
        cmbPagos = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnRecargarTabla = new Utils.ButtonRounded();
        LblFormatoFecha = new javax.swing.JLabel();
        btnCxP = new Utils.ButtonRounded();

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setMinimumSize(new java.awt.Dimension(1115, 760));
        Content.setPreferredSize(new java.awt.Dimension(1115, 760));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaPagosHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID de la Compra", "Proveedor", "Monto Adeudado", "Monto Pagado", "Fecha del Pago"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
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
        TablaPagosHistorial.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(TablaPagosHistorial);
        if (TablaPagosHistorial.getColumnModel().getColumnCount() > 0) {
            TablaPagosHistorial.getColumnModel().getColumn(0).setResizable(false);
            TablaPagosHistorial.getColumnModel().getColumn(0).setPreferredWidth(15);
            TablaPagosHistorial.getColumnModel().getColumn(1).setResizable(false);
            TablaPagosHistorial.getColumnModel().getColumn(2).setResizable(false);
            TablaPagosHistorial.getColumnModel().getColumn(3).setResizable(false);
            TablaPagosHistorial.getColumnModel().getColumn(4).setResizable(false);
        }

        Content.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1080, 580));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Historial de Pagos");
        Content.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        cmbPagos.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 12)); // NOI18N
        cmbPagos.setForeground(new java.awt.Color(255, 255, 255));
        cmbPagos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Proveedor", "Dia", "Mes", "Año", "Fecha completa" }));
        Content.add(cmbPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 127, 100, 30));
        Content.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(892, 127, 150, 30));

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        Content.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 127, 60, 30));

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

        LblFormatoFecha.setBackground(new java.awt.Color(255, 255, 255));
        LblFormatoFecha.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        LblFormatoFecha.setForeground(new java.awt.Color(255, 255, 255));
        LblFormatoFecha.setText("AAAA - MM - DD");
        LblFormatoFecha.setToolTipText("Formato de la fecha.");
        Content.add(LblFormatoFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 115, 80, 10));

        btnCxP.setBackground(new java.awt.Color(51, 204, 0));
        btnCxP.setForeground(new java.awt.Color(255, 255, 255));
        btnCxP.setText("IR A CxP");
        btnCxP.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnCxP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCxPActionPerformed(evt);
            }
        });
        Content.add(btnCxP, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 25, 130, -1));

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
        init();
    }//GEN-LAST:event_btnRecargarTablaActionPerformed

    private void btnCxPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCxPActionPerformed
        Application.showForm(new FormCxP());
    }//GEN-LAST:event_btnCxPActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscador();
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblFormatoFecha;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JTable TablaPagosHistorial;
    private javax.swing.JButton btnBuscar;
    private Utils.ButtonRounded btnCxP;
    private Utils.ButtonRounded btnRecargarTabla;
    private javax.swing.JComboBox<String> cmbPagos;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
