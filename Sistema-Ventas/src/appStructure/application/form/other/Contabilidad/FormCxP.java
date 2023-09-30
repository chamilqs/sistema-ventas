/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appStructure.application.form.other.Contabilidad;

import Database.Connect.ConexionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import appStructure.application.form.other.Contabilidad.Registrar.RegistrarPagoCxP;

/**
 *
 * @author Samil Quezada Suriel
 */
public class FormCxP extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    private JFrame fr;  
    
    public FormCxP() {
        initComponents();
        init();

    }

    public FormCxP(JFrame fr) {
        initComponents();
        init();
        this.fr = fr; 
    }
    
    private void init(){
    
        DatosCuentaPagar();
        revisarCuentasSaldadasCuentaPagar();

    }
    
    public void DatosCuentaPagar() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        String query = "SELECT cp.idCompra AS IdCompra, cp.idProveedor AS IdAcreedor, pr.nombre AS DescripcionAcreedor," +
                        " cp.deuda AS Deuda, cp.totalFaltante AS TotalFaltante, cp.fecha AS Fecha" +
                        " FROM cuentaporpagar cp" +
                        " JOIN proveedor pr ON cp.idProveedor = pr.id;";

        
        // Ejecutar la consulta y llenar el modelo
        try (Connection connection = c.getConexion()) {
            
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

        TablePagar.setModel(modelo);
        c.desconectar();
    } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(FormCxP.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    private void buscarPorFechaCuentaPagar() {
        String textoBusqueda = txtBuscador.getText();
        if (!textoBusqueda.isEmpty()){
        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Evitar que las celdas sean editables
                }
            };

            String seleccion = (String) cmbCxP.getSelectedItem(); 

            String query = ""; 

            switch (seleccion) {
                case "Dia" -> {
                    String dia = txtBuscador.getText();
                    if (isValidDay(dia)) {
                    query = "SELECT cp.id, cp.idProveedor AS idAcreedor, pr.nombre AS DescripcionAcreedor,"
                            + " cp.fecha, cp.deuda AS 'Deuda', pc.monto AS Pago,"
                            + " pc.fecha AS 'Fecha Pago', cp.totalFaltante"
                            + " FROM cuentaporpagar cp"
                            + " JOIN proveedor pr ON cp.idProveedor = pr.id"
                            + " LEFT JOIN pagocp pc ON cp.id = pc.idCompra AND pc.monto BETWEEN cp.deuda AND cp.totalFaltante"
                            + " WHERE DAY(cp.fecha) = '" + dia + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Día incorrecto. Ingrese un número entre 1 y 31.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Mes" -> {
                    String mes = txtBuscador.getText();
                    if (isValidMonth(mes)) {
                    query = "SELECT cp.id, cp.idProveedor AS idAcreedor, pr.nombre AS DescripcionAcreedor,"
                            + " cp.fecha, cp.deuda AS 'Deuda', pc.monto AS Pago,"
                            + " pc.fecha AS 'Fecha Pago', cp.totalFaltante"
                            + " FROM cuentaporpagar cp"
                            + " JOIN proveedor pr ON cp.idProveedor = pr.id"
                            + " LEFT JOIN pagocp pc ON cp.id = pc.idCompra AND pc.monto BETWEEN cp.deuda AND cp.totalFaltante"
                            + " WHERE MONTH(cp.fecha) = '" + mes + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Mes incorrecto. Ingrese un número entre 1 y 12.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;                    }

                }
                case "Año" -> {
                    String ano = txtBuscador.getText();
                    if (isValidYear(ano)) {
                    query = "SELECT cp.id, cp.idProveedor AS idAcreedor, pr.nombre AS DescripcionAcreedor,"
                            + " cp.fecha, cp.deuda AS 'Deuda', pc.monto AS Pago,"
                            + " pc.fecha AS 'Fecha Pago', cp.totalFaltante"
                            + " FROM cuentaporpagar cp"
                            + " JOIN proveedor pr ON cp.idProveedor = pr.id"
                            + " LEFT JOIN pagocp pc ON cp.id = pc.idCompra AND pc.monto BETWEEN cp.deuda AND cp.totalFaltante"
                            + " WHERE YEAR(cp.fecha) = '" + ano + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Año incorrecto. Ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Fecha completa" -> {
                    String fechaCompleta = txtBuscador.getText();
                    if (isValidDateFormat(fechaCompleta, "yyyy-MM-dd")) {
                    query = "SELECT cp.id, cp.idProveedor AS idAcreedor, pr.nombre AS DescripcionAcreedor,"
                            + " cp.fecha, cp.deuda AS 'Deuda', pc.monto AS Pago,"
                            + " pc.fecha AS 'Fecha Pago', cp.totalFaltante"
                            + " FROM cuentaporpagar cp"
                            + " JOIN proveedor pr ON cp.idProveedor = pr.id"
                            + " LEFT JOIN pagocp pc ON cp.id = pc.idCompra AND pc.monto BETWEEN cp.deuda AND cp.totalFaltante"
                            + " WHERE cp.fecha = '" + fechaCompleta + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Formato de fecha completo incorrecto. Utilice AAAA-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                default -> JOptionPane.showMessageDialog(this, "Seleccione una opción válida para realizar la búsqueda.", "Error", JOptionPane.ERROR_MESSAGE);
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

            TablePagar.setModel(modelo);
            c.desconectar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormCxP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } else {
                        
            JOptionPane.showMessageDialog(this, "Ocurrió un error, compruebe que todo esté bien.", "Error", JOptionPane.ERROR_MESSAGE);
              
        }
        
        
    }


    public void revisarCuentasSaldadasCuentaPagar() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) TablePagar.getModel();

            for (int row = modelo.getRowCount() - 1; row >= 0; row--) {
                double totalFaltante = Double.parseDouble(modelo.getValueAt(row, 4).toString());
                if (totalFaltante == 0.00) {
                   // int idCompra = Integer.parseInt(modelo.getValueAt(row, 0).toString());
                    String idCompra = (modelo.getValueAt(row, 0).toString());
                    String nombreProveedor = modelo.getValueAt(row, 2).toString();

                    // Mostrar mensaje de cuenta saldada
                    JOptionPane.showMessageDialog(this, "Cuenta del proveedor " + nombreProveedor + " fue saldada.", "Cuenta Saldada", JOptionPane.INFORMATION_MESSAGE);

                    // Eliminar la fila de la tabla
                    modelo.removeRow(row);

                    // Eliminar la cuenta de la base de datos
                    eliminarCuentaPorPagar(idCompra);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FormCxP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void eliminarCuentaPorPagar(String idCompra) {
        try {
            String query = "DELETE FROM cuentaporpagar WHERE idCompra = ?";
            try (Connection connection = c.getConexion()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, idCompra);
                statement.executeUpdate();
            }
            c.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(FormCxP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormCxP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void registrarPagoCuentaPagar() {
        int selectedRow = TablePagar.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para registrar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idCompra = TablePagar.getValueAt(selectedRow, 0).toString();
        String idAcreedor = TablePagar.getValueAt(selectedRow, 1).toString();
        String descripcionAcreedor = TablePagar.getValueAt(selectedRow, 2).toString();
        String deuda = TablePagar.getValueAt(selectedRow, 3).toString();
        String totalFaltante = TablePagar.getValueAt(selectedRow, 4).toString();

        // Crear un nuevo diálogo de RegistroPagoCuentaPagar
        RegistrarPagoCxP dialog = new RegistrarPagoCxP(fr, true);

        // Establecer los campos en el diálogo con los datos recopilados
        dialog.setDatosRegistroPago(idCompra, idAcreedor, descripcionAcreedor, deuda, totalFaltante);

        dialog.setVisible(true);
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
        ScrollPagar = new javax.swing.JScrollPane();
        TablePagar = new javax.swing.JTable();
        LblTitle = new javax.swing.JLabel();
        cmbCxP = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        LblNotFecha = new javax.swing.JLabel();
        btnRecargarTabla = new Utils.ButtonRounded();
        btnRegistrarPago = new Utils.ButtonRounded();

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        Content.setMinimumSize(new java.awt.Dimension(1115, 760));
        Content.setPreferredSize(new java.awt.Dimension(1115, 760));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablePagar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID Acreedor", "Descripción", "Fecha", "Monto", "Total Faltante"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
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
        TablePagar.getTableHeader().setReorderingAllowed(false);
        ScrollPagar.setViewportView(TablePagar);
        if (TablePagar.getColumnModel().getColumnCount() > 0) {
            TablePagar.getColumnModel().getColumn(0).setPreferredWidth(15);
            TablePagar.getColumnModel().getColumn(1).setResizable(false);
            TablePagar.getColumnModel().getColumn(1).setPreferredWidth(15);
            TablePagar.getColumnModel().getColumn(2).setResizable(false);
            TablePagar.getColumnModel().getColumn(3).setResizable(false);
            TablePagar.getColumnModel().getColumn(4).setResizable(false);
            TablePagar.getColumnModel().getColumn(5).setResizable(false);
        }

        Content.add(ScrollPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1080, 580));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Cuentas Por Pagar");
        Content.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        cmbCxP.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 12)); // NOI18N
        cmbCxP.setForeground(new java.awt.Color(255, 255, 255));
        cmbCxP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Mes", "Año", "Fecha completa" }));
        Content.add(cmbCxP, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 127, 100, 30));
        Content.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(892, 127, 150, 30));

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        Content.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 127, 60, 30));

        LblNotFecha.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        LblNotFecha.setForeground(new java.awt.Color(255, 255, 255));
        LblNotFecha.setText("AAAA - MM - DD");
        LblNotFecha.setToolTipText("Formato de la fecha.");
        Content.add(LblNotFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 115, 80, 10));

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

        btnRegistrarPago.setBackground(new java.awt.Color(51, 204, 0));
        btnRegistrarPago.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarPago.setText("REGISTRAR PAGO");
        btnRegistrarPago.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRegistrarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPagoActionPerformed(evt);
            }
        });
        Content.add(btnRegistrarPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 27, 130, -1));

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

    private void btnRegistrarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPagoActionPerformed
        registrarPagoCuentaPagar();
        init();
        revisarCuentasSaldadasCuentaPagar();
    }//GEN-LAST:event_btnRegistrarPagoActionPerformed

    private void btnRecargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarTablaActionPerformed
        DatosCuentaPagar();
        revisarCuentasSaldadasCuentaPagar();
        txtBuscador.setText("");
    }//GEN-LAST:event_btnRecargarTablaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarPorFechaCuentaPagar();
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblNotFecha;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JScrollPane ScrollPagar;
    private javax.swing.JTable TablePagar;
    private javax.swing.JButton btnBuscar;
    private Utils.ButtonRounded btnRecargarTabla;
    private Utils.ButtonRounded btnRegistrarPago;
    private javax.swing.JComboBox<String> cmbCxP;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
