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
import appStructure.application.form.other.Contabilidad.Registrar.RegistrarIngresos;

/**
 *
 * @author Samil 
 */
public class FormIngresos extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormIngresos() {
        initComponents();
        init();
    }

    private void init(){
        
        obtenerIngresos();
    
    }
    
    private void buscarIngresosPorFecha() {
    
        String textoBusqueda = txtBuscador.getText();
        if (!textoBusqueda.isEmpty()){
            try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

            String seleccion = (String) cmbIngresos.getSelectedItem();

            String query = "";

                switch (seleccion) {
                    case "Dia" -> {
                        String dia = txtBuscador.getText();
                        if (isValidDay(dia)) {
                            query = "SELECT ID, Monto, Concepto, Fecha FROM Ingresos WHERE DAY(fecha) = '" + dia + "'";

                        } else {
                            JOptionPane.showMessageDialog(this, "Día incorrecto. Ingrese un número entre 1 y 31.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;                    
                        }                        
                    }
                    case "Mes" -> {
                        String mes = txtBuscador.getText();
                        if (isValidMonth(mes)) {
                        query = "SELECT ID, Monto, Concepto, Fecha FROM Ingresos WHERE MONTH(fecha) = '" + mes + "'";
                        
                        } else {
                            JOptionPane.showMessageDialog(this, "Mes incorrecto. Ingrese un número entre 1 y 12.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }                        
                    }
                    case "Año" -> {
                        String ano = txtBuscador.getText();
                        if (isValidYear(ano)) {
                        query = "SELECT ID, Monto, Concepto, Fecha FROM Ingresos WHERE YEAR(fecha) = '" + ano + "'";
                        
                        } else {
                            JOptionPane.showMessageDialog(this, "Año incorrecto. Ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }                        
                    }
                    case "Fecha completa" -> {
                        String fechaCompleta = txtBuscador.getText();
                        if (isValidDateFormat(fechaCompleta, "yyyy-MM-dd")) {                        
                        query = "SELECT ID, Monto, Concepto, Fecha FROM Ingresos WHERE fecha = '" + fechaCompleta + "'";
                        
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
            }

            TablaIngresos.setModel(modelo);
            c.desconectar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormIngresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } else {
                        
            JOptionPane.showMessageDialog(this, "Introduzca un valor para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
              
        }
        
}

    private void obtenerIngresos() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        String query = "SELECT ID, Monto, Concepto,  Fecha FROM Ingresos";

        // Ejecutar la consulta y llenar el modelo
        try (Connection connection = c.getConexion()){
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            int columnCount = metaData.getColumnCount();

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

        TablaIngresos.setModel(modelo);
        c.desconectar();
    } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(FormIngresos.class.getName()).log(Level.SEVERE, null, ex);
    }
}   
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
        TablaIngresos = new javax.swing.JTable();
        LblTitle = new javax.swing.JLabel();
        cmbIngresos = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        LblFormatoFecha = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnRegistrarIngreso = new Utils.ButtonRounded();
        btnRecargarTabla = new Utils.ButtonRounded();

        setMinimumSize(new java.awt.Dimension(1115, 760));
        setPreferredSize(new java.awt.Dimension(1115, 760));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setMinimumSize(new java.awt.Dimension(1100, 740));
        Content.setPreferredSize(new java.awt.Dimension(1115, 760));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaIngresos.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        TablaIngresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Ingreso", "Monto", "Descripcion", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
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
        TablaIngresos.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(TablaIngresos);
        if (TablaIngresos.getColumnModel().getColumnCount() > 0) {
            TablaIngresos.getColumnModel().getColumn(0).setResizable(false);
            TablaIngresos.getColumnModel().getColumn(1).setResizable(false);
            TablaIngresos.getColumnModel().getColumn(2).setResizable(false);
            TablaIngresos.getColumnModel().getColumn(3).setResizable(false);
        }

        Content.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1080, 580));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Ingresos");
        Content.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        cmbIngresos.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 12)); // NOI18N
        cmbIngresos.setForeground(new java.awt.Color(255, 255, 255));
        cmbIngresos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Mes", "Año", "Fecha completa" }));
        Content.add(cmbIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 127, 100, 30));
        Content.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(892, 127, 150, 30));

        LblFormatoFecha.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        LblFormatoFecha.setForeground(new java.awt.Color(255, 255, 255));
        LblFormatoFecha.setText("AAAA - MM - DD");
        LblFormatoFecha.setToolTipText("Formato de la fecha.");
        Content.add(LblFormatoFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 115, 80, 10));

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        Content.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 127, 60, 30));

        btnRegistrarIngreso.setBackground(new java.awt.Color(51, 204, 0));
        btnRegistrarIngreso.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarIngreso.setText("REGISTRAR INGRESO");
        btnRegistrarIngreso.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRegistrarIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarIngresoActionPerformed(evt);
            }
        });
        Content.add(btnRegistrarIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 27, 150, -1));

        btnRecargarTabla.setBackground(new java.awt.Color(51, 204, 0));
        btnRecargarTabla.setForeground(new java.awt.Color(255, 255, 255));
        btnRecargarTabla.setText("RECARGAR TABLA");
        btnRecargarTabla.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRecargarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarTablaActionPerformed(evt);
            }
        });
        Content.add(btnRecargarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 60, 150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarTablaActionPerformed
        init();
        txtBuscador.setText("");        
    }//GEN-LAST:event_btnRecargarTablaActionPerformed

    private void btnRegistrarIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarIngresoActionPerformed
        RegistrarIngresos RI = new RegistrarIngresos();
        RI.setLocationRelativeTo(this);
        RI.setVisible(true);
        init();
    }//GEN-LAST:event_btnRegistrarIngresoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarIngresosPorFecha();
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblFormatoFecha;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JTable TablaIngresos;
    private javax.swing.JButton btnBuscar;
    private Utils.ButtonRounded btnRecargarTabla;
    private Utils.ButtonRounded btnRegistrarIngreso;
    private javax.swing.JComboBox<String> cmbIngresos;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
