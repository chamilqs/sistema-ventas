/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appStructure.application.form.other.Contabilidad;

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

/**
 *
 * @author Samil 
 */
public class FormCobrosClientes extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    // Este form muestra los datos de clientes que han pagado deudas
    // Solo los datos de todos los cobros
    
    public FormCobrosClientes() {
        initComponents();
        init();
    }
    
    private void init(){
    
        DatosPagosCxC();
        
    }
    
    private void DatosPagosCxC() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

         String query = "SELECT pc.idVenta as IDVenta, v.nombreCliente AS Cliente, pc.monto AS 'Monto Pago', pc.fecha AS 'Fecha Pago' "
                     + "FROM pagocc AS pc "
                     + "INNER JOIN cuentaporcobrar AS c ON pc.idVenta = c.idVenta "
                     + "INNER JOIN venta AS v ON c.idVenta = v.id";


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

        TablaPago.setModel(modelo);

        // Hacer que la tabla no sea redimensionable
        TablaPago.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < TablaPago.getColumnCount(); i++) {
            TablaPago.getColumnModel().getColumn(i).setResizable(false);
        }
        c.desconectar();
    } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(FormCobrosClientes.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    private void buscarPorFechaPago() {
        String textoBusqueda = txtBuscador.getText();
        if (!textoBusqueda.isEmpty()){
        
        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Evitar que las celdas sean editables
                }
            };

            String seleccion = (String) cmbPagos.getSelectedItem();
            String query = "";

            switch (seleccion) {
                case "Dia" -> {
                    String dia = txtBuscador.getText();
                    if (isValidDay(dia)) {
                        query = "SELECT pc.idVenta, v.nombreCliente AS Cliente, pc.monto AS MontoPago, pc.fecha AS FechaPago"
                                + " FROM pagocc AS pc"
                                + " INNER JOIN cuentaporcobrar AS c ON pc.idVenta = c.idVenta"
                                + " INNER JOIN Venta AS v ON c.idVenta = v.id"
                                + " WHERE DAY(pc.fecha) = '" + dia + "'";
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Día incorrecto. Ingrese un número entre 1 y 31.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Mes" -> {
                    String Mes = txtBuscador.getText();
                    if (isValidMonth(Mes)) {                                            
                        query = "SELECT pc.idVenta, v.nombreCliente AS Cliente, pc.monto AS MontoPago, pc.fecha AS FechaPago"
                                + " FROM pagocc AS pc"
                                + " INNER JOIN cuentaporcobrar AS c ON pc.idVenta = c.idVenta"
                                + " INNER JOIN Venta AS v ON c.idVenta = v.id"
                                + " WHERE MONTH(pc.fecha) = '" + Mes + "'";
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Mes incorrecto. Ingrese un número entre 1 y 12.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Año" -> {
                    String ano = txtBuscador.getText();
                    if (isValidYear(ano)) {
                        query = "SELECT pc.idVenta, v.nombreCliente AS Cliente, pc.monto AS MontoPago, pc.fecha AS FechaPago"
                                + " FROM pagocc AS pc"
                                + " INNER JOIN cuentaporcobrar AS c ON pc.idVenta = c.idVenta"
                                + " INNER JOIN Venta AS v ON c.idVenta = v.id"
                                + " WHERE YEAR(pc.fecha) = '" + ano + "'";
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Año incorrecto. Ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }                    
                }
                case "Fecha completa" -> {
                    String fechaCompleta = txtBuscador.getText();
                    if (isValidDateFormat(fechaCompleta, "yyyy-MM-dd")) {
                        query = "SELECT pc.idVenta, v.nombreCliente AS Cliente, pc.monto AS MontoPago, pc.fecha AS FechaPago"
                                + " FROM pagocc AS pc"
                                + " INNER JOIN cuentaporcobrar AS c ON pc.idVenta = c.idVenta"
                                + " INNER JOIN Venta AS v ON c.idVenta = v.id"
                                + " WHERE pc.fecha = '" + fechaCompleta + "'";
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Formato de fecha completo incorrecto. Utilice AAAA-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } 
                }
                case "Cliente" -> {
                    String Cliente = txtBuscador.getText();
                    query = "SELECT pc.idVenta, v.nombreCliente AS Cliente, pc.monto AS MontoPago, pc.fecha AS FechaPago"
                            + " FROM pagocc AS pc"
                            + " INNER JOIN cuentaporcobrar AS c ON pc.idVenta = c.idVenta"
                            + " INNER JOIN Venta AS v ON c.idVenta = v.id"
                            + " WHERE v.nombreCliente = '" + Cliente + "'";
                }
                default -> {
                    JOptionPane.showMessageDialog(this, "Seleccione una opción válida para realizar la búsqueda.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;    
                }
            }

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

            TablaPago.setModel(modelo);
            c.desconectar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormCobrosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } else {
                        
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

        PanelPagos = new javax.swing.JPanel();
        LblTitle = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        TablaPago = new javax.swing.JTable();
        cmbPagos = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        LblFormatoFecha = new javax.swing.JLabel();
        btnRecargarTabla = new Utils.ButtonRounded();
        btnBuscar = new javax.swing.JButton();
        btnCxC = new Utils.ButtonRounded();

        PanelPagos.setBackground(new java.awt.Color(24, 39, 72));
        PanelPagos.setMinimumSize(new java.awt.Dimension(1115, 760));
        PanelPagos.setPreferredSize(new java.awt.Dimension(1115, 760));
        PanelPagos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Cobros a clientes");
        PanelPagos.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        TablaPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "IdVenta", "Cliente", "Monto Pago", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaPago.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(TablaPago);

        PanelPagos.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1080, 580));

        cmbPagos.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 12)); // NOI18N
        cmbPagos.setForeground(new java.awt.Color(255, 255, 255));
        cmbPagos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Dia", "Mes", "Año", "Fecha completa" }));
        PanelPagos.add(cmbPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 127, 100, 30));
        PanelPagos.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(892, 127, 150, 30));

        LblFormatoFecha.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        LblFormatoFecha.setForeground(new java.awt.Color(255, 255, 255));
        LblFormatoFecha.setText("AAAA - MM - DD");
        LblFormatoFecha.setToolTipText("Formato de la fecha.");
        PanelPagos.add(LblFormatoFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 115, 80, 10));

        btnRecargarTabla.setBackground(new java.awt.Color(51, 204, 0));
        btnRecargarTabla.setForeground(new java.awt.Color(255, 255, 255));
        btnRecargarTabla.setText("RECARGAR TABLA");
        btnRecargarTabla.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRecargarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarTablaActionPerformed(evt);
            }
        });
        PanelPagos.add(btnRecargarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 60, 130, -1));

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        PanelPagos.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 127, 60, 30));

        btnCxC.setBackground(new java.awt.Color(51, 204, 0));
        btnCxC.setForeground(new java.awt.Color(255, 255, 255));
        btnCxC.setText("IR A CxC");
        btnCxC.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnCxC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCxCActionPerformed(evt);
            }
        });
        PanelPagos.add(btnCxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 25, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1115, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelPagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelPagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarTablaActionPerformed
        init();
        txtBuscador.setText("");
    }//GEN-LAST:event_btnRecargarTablaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarPorFechaPago();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCxCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCxCActionPerformed
        Application.showForm(new FormCxC());
    }//GEN-LAST:event_btnCxCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblFormatoFecha;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JPanel PanelPagos;
    private javax.swing.JTable TablaPago;
    private javax.swing.JButton btnBuscar;
    private Utils.ButtonRounded btnCxC;
    private Utils.ButtonRounded btnRecargarTabla;
    private javax.swing.JComboBox<String> cmbPagos;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
