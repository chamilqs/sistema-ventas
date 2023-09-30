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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import appStructure.application.form.other.Contabilidad.Registrar.RegistrarGasto;

/**
 *
 * @author Samil 
 */
public class FormGastos extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    private JFrame fr;    
    
    public FormGastos() {
        initComponents();
        init();
    }

    public FormGastos(JFrame fr) {
        initComponents();
        init();
        this.fr = fr;
    }
    
    private void init(){

        obtenerGastos();   
    
    }
    
    private void buscarGastosPorFecha() {
        
        String textoBusqueda = txtBuscador.getText();
        if (!textoBusqueda.isEmpty()){
        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Evitar que las celdas sean editables
                }
            };

            String seleccion = (String) cmbGastos.getSelectedItem();
            String query = "";

            switch (seleccion) {
                case "Dia" -> {
                    String dia = txtBuscador.getText();                   
                    if (isValidDay(dia)) {
                    query = "SELECT ID, Concepto, Monto, Fecha FROM gastos WHERE DAY(fecha) = '" + dia + "'";
                    
                    } else {
                        JOptionPane.showMessageDialog(this, "Día incorrecto. Ingrese un número entre 1 y 31.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Mes" -> {
                    String mes = txtBuscador.getText();
                    if (isValidMonth(mes)) {
                        
                        query = "SELECT ID, Concepto, Monto, Fecha FROM gastos WHERE MONTH(fecha) = '" + mes + "'";
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Mes incorrecto. Ingrese un número entre 1 y 12.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }                   
                }
                case "Año" -> {
                    String ano = txtBuscador.getText();
                    if (isValidYear(ano)) {
                        query = "SELECT ID, Concepto, Monto, Fecha FROM gastos WHERE YEAR(fecha) = '" + ano + "'";
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Año incorrecto. Ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }                   
                }
                case "Fecha completa" -> {
                    String fechaCompleta = txtBuscador.getText();
                    if (isValidDateFormat(fechaCompleta, "yyyy-MM-dd")) {                        
                        query = "SELECT ID, Concepto, Monto, Fecha FROM gastos WHERE fecha = '" + fechaCompleta + "'";
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Formato de fecha completo incorrecto. Utilice AAAA-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }                    
                }
                case "ID" -> {
                    String id = txtBuscador.getText();
                    query = "SELECT ID, Concepto, Monto, Fecha FROM gastos WHERE id = '" + id + "'";
                }

                default -> {
                   JOptionPane.showMessageDialog(this, "Seleccione un criterio de búsqueda válido.", "Error", JOptionPane.ERROR_MESSAGE);
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

            TablaGastos.setModel(modelo);
            c.desconectar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormGastos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } else {
        
            JOptionPane.showMessageDialog(this, "Introduzca un valor para buscar.", "Error", JOptionPane.ERROR_MESSAGE);

        }
        
    }
    public void obtenerGastos() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        // La consulta SQL para obtener los gastos
        String query = "SELECT ID, Concepto, Monto, Fecha FROM gastos";

        // Ejecutar la consulta y llenar el modelo
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

        TablaGastos.setModel(modelo);
        c.desconectar();
    } catch (SQLException ex) {
        Logger.getLogger(FormGastos.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(FormGastos.class.getName()).log(Level.SEVERE, null, ex);
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
        TablaGastos = new javax.swing.JTable();
        LblTitle = new javax.swing.JLabel();
        btnRegistrarGasto = new Utils.ButtonRounded();
        cmbGastos = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        LblFormatoFecha = new javax.swing.JLabel();
        btnRecargarTabla = new Utils.ButtonRounded();
        btnBuscar = new javax.swing.JButton();

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setMinimumSize(new java.awt.Dimension(1115, 760));
        Content.setPreferredSize(new java.awt.Dimension(1115, 760));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaGastos.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        TablaGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Gasto", "Concepto", "Monto del gasto", "Fecha del gasto"
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
        TablaGastos.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(TablaGastos);
        if (TablaGastos.getColumnModel().getColumnCount() > 0) {
            TablaGastos.getColumnModel().getColumn(0).setResizable(false);
            TablaGastos.getColumnModel().getColumn(0).setPreferredWidth(15);
            TablaGastos.getColumnModel().getColumn(1).setResizable(false);
            TablaGastos.getColumnModel().getColumn(2).setResizable(false);
            TablaGastos.getColumnModel().getColumn(3).setResizable(false);
        }

        Content.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1080, 580));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Gastos");
        Content.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        btnRegistrarGasto.setBackground(new java.awt.Color(51, 204, 0));
        btnRegistrarGasto.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarGasto.setText("REGISTRAR GASTO");
        btnRegistrarGasto.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRegistrarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarGastoActionPerformed(evt);
            }
        });
        Content.add(btnRegistrarGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 27, 130, -1));

        cmbGastos.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 12)); // NOI18N
        cmbGastos.setForeground(new java.awt.Color(255, 255, 255));
        cmbGastos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Dia", "Mes", "Año", "Fecha completa" }));
        Content.add(cmbGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 127, 100, 30));
        Content.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(892, 127, 150, 30));

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

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        Content.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 127, 60, 30));

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

    private void btnRegistrarGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGastoActionPerformed
        RegistrarGasto Rg = new RegistrarGasto(fr, true);
        Rg.setLocationRelativeTo(this);
        Rg.setVisible(true);
        init();
    }//GEN-LAST:event_btnRegistrarGastoActionPerformed

    private void btnRecargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarTablaActionPerformed
        init();
        //obtenerGastos();
        txtBuscador.setText("");
    }//GEN-LAST:event_btnRecargarTablaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarGastosPorFecha();
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblFormatoFecha;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JTable TablaGastos;
    private javax.swing.JButton btnBuscar;
    private Utils.ButtonRounded btnRecargarTabla;
    private Utils.ButtonRounded btnRegistrarGasto;
    private javax.swing.JComboBox<String> cmbGastos;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
