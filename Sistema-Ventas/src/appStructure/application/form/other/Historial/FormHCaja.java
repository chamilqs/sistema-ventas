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
public class FormHCaja extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormHCaja() {
        initComponents();
        init();
    }

    private void init(){
    
        actualizarTablaU();
    }
    private void actualizarTabla(JTable tabla, String query){
           
         DefaultTableModel model = new DefaultTableModel();
 
        try(Connection cn = c.getConexion();) {
            
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FormHUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void actualizarTablaU(){
           
        String query =  "SELECT u.Id AS Usuario, h.fecha_Apertura AS Apertura, h.monto_Apertura AS MontoApertura, h.fecha_Cierre AS Cierre, h.monto_Cierre AS MontoCierre" +
"                        FROM HistorialCaja AS h" +
"                        INNER JOIN usuario AS u ON u.id = h.usuarioId;";
        
        actualizarTabla(TablaCaja, query);
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

            String seleccion = (String) cmbCaja.getSelectedItem(); // Obtener la opción seleccionada del combo box

            String query = ""; 
            switch (seleccion) {
                 case "ID" -> {
                    String id = txtBuscador.getText();                    
                    query =  "SELECT u.Id AS 'ID Usuario', h.fecha_Apertura AS 'Fecha Apertura', h.monto_Apertura AS 'Monto Apertura', h.fecha_Cierre AS 'Fecha Cierre', h.monto_Cierre AS 'Monto Cierre'" 
                        + " FROM HistorialCaja AS h " 
                        + " INNER JOIN usuario AS u ON u.id = h.usuarioId "
                        + " WHERE h.usuarioId = '" + id + "'";
                }
                  case "Usuario" -> {                 
                    String nombre = txtBuscador.getText();
                    query =  "SELECT u.Id AS 'ID Usuario', h.fecha_Apertura AS 'Fecha Apertura', h.monto_Apertura AS 'Monto Apertura', h.fecha_Cierre AS 'Fecha Cierre', h.monto_Cierre AS 'Monto Cierre'" 
                        + " FROM HistorialCaja AS h " 
                        + " INNER JOIN usuario AS u ON u.id = h.usuarioId "
                        + " WHERE u.nombre = '" + nombre + "'";
                } 
                case "Dia" -> {
                    String dia = txtBuscador.getText();
                    if (isValidDay(dia)) {
                    query =  "SELECT u.Id AS 'ID Usuario', h.fecha_Apertura AS 'Fecha Apertura', h.monto_Apertura AS 'Monto Apertura', h.fecha_Cierre AS 'Fecha Cierre', h.monto_Cierre AS 'Monto Cierre'" 
                        + " FROM HistorialCaja AS h " 
                        + " INNER JOIN usuario AS u ON u.id = h.usuarioId "
                        + " WHERE DAY(h.fecha_Apertura) = '" + dia + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Día incorrecto. Ingrese un número entre 1 y 31.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Mes" -> {
                    String mes = txtBuscador.getText();
                    if (isValidMonth(mes)) {
                    query =  "SELECT u.Id AS 'ID Usuario', h.fecha_Apertura AS 'Fecha Apertura', h.monto_Apertura AS 'Monto Apertura', h.fecha_Cierre AS 'Fecha Cierre', h.monto_Cierre AS 'Monto Cierre'" 
                        + " FROM HistorialCaja AS h " 
                        + " INNER JOIN usuario AS u ON u.id = h.usuarioId "
                        + " WHERE MONTH(h.fecha_Apertura) = '" + mes + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Mes incorrecto. Ingrese un número entre 1 y 12.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Año" -> {
                    String ano = txtBuscador.getText();
                    if (isValidYear(ano)) {
                    query =  "SELECT u.Id AS 'ID Usuario', h.fecha_Apertura AS 'Fecha Apertura', h.monto_Apertura AS 'Monto Apertura', h.fecha_Cierre AS 'Fecha Cierre', h.monto_Cierre AS 'Monto Cierre'" 
                        + " FROM HistorialCaja AS h " 
                        + " INNER JOIN usuario AS u ON u.id = h.usuarioId "
                        + " WHERE YEAR(h.fecha_Apertura) = '" + ano + "'";
                    
                    } else {
                        JOptionPane.showMessageDialog(this, "Año incorrecto. Ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Fecha completa" -> {
                    String fechaCompleta = txtBuscador.getText();
                    if (isValidDateFormat(fechaCompleta, "yyyy-MM-dd")) {
                    query =  "SELECT u.Id AS 'ID Usuario', h.fecha_Apertura AS 'Fecha Apertura', h.monto_Apertura AS 'Monto Apertura', h.fecha_Cierre AS 'Fecha Cierre', h.monto_Cierre AS 'Monto Cierre'" 
                        + " FROM HistorialCaja AS h " 
                        + " INNER JOIN usuario AS u ON u.id = h.usuarioId "
                        + " WHERE h.fecha_Apertura = '" + fechaCompleta + "'";
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

            TablaCaja.setModel(modelo);
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
        TablaCaja = new javax.swing.JTable();
        cmbCaja = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        LblTitle = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnRecargarTabla = new Utils.ButtonRounded();
        LblFormatoFecha = new javax.swing.JLabel();

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setForeground(new java.awt.Color(255, 255, 255));
        Content.setMinimumSize(new java.awt.Dimension(1115, 760));
        Content.setPreferredSize(new java.awt.Dimension(1115, 760));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaCaja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Usuario", "Fecha de Apertura", "Monto de Apertura", "Fecha de Cierre", "Monto al Cierre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class
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
        TablaCaja.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(TablaCaja);
        if (TablaCaja.getColumnModel().getColumnCount() > 0) {
            TablaCaja.getColumnModel().getColumn(0).setResizable(false);
            TablaCaja.getColumnModel().getColumn(1).setResizable(false);
            TablaCaja.getColumnModel().getColumn(2).setResizable(false);
            TablaCaja.getColumnModel().getColumn(3).setResizable(false);
            TablaCaja.getColumnModel().getColumn(4).setResizable(false);
        }

        Content.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1080, 580));

        cmbCaja.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 12)); // NOI18N
        cmbCaja.setForeground(new java.awt.Color(255, 255, 255));
        cmbCaja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Usuario", "Dia", "Mes", "Año", "Fecha completa" }));
        Content.add(cmbCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 127, 100, 30));
        Content.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(892, 127, 150, 30));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Historial de Caja");
        Content.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscador();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRecargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarTablaActionPerformed
        init();
    }//GEN-LAST:event_btnRecargarTablaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblFormatoFecha;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JTable TablaCaja;
    private javax.swing.JButton btnBuscar;
    private Utils.ButtonRounded btnRecargarTabla;
    private javax.swing.JComboBox<String> cmbCaja;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
