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
import appStructure.application.form.other.Contabilidad.Registrar.RegistrarCobroCxC;
import java.awt.HeadlessException;

/**
 *
 * @author Samil 
 */
public class FormCxC extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    private JFrame fr;    
    
    public FormCxC() {
        initComponents();
        init();        
    }
    
    public FormCxC(JFrame fr) {
        initComponents();
        init();
        this.fr = fr;
    }

    private void init(){
    
        DatosCuentaCobrar();
    
    }
    
    //Cuentas por Cobrar
    
    public void DatosCuentaCobrar() {
         try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

            String query = "SELECT c.idVenta AS IdVenta, v.nombreCliente AS ClienteDeudor, c.fecha as Fecha, c.deuda as Deuda, c.totalFaltante as 'Total Faltante', c.descripcion as Descripcion "
                 + "FROM cuentaporcobrar AS c "
                 + "INNER JOIN venta AS v ON v.id = c.idVenta";

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

            TableCobrar.setModel(modelo);

            TableCobrar.getTableHeader().setResizingAllowed(false);
            for (int i = 0; i < TableCobrar.getColumnCount(); i++) {
                TableCobrar.getColumnModel().getColumn(i).setResizable(false);
            }
            c.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(FormCxC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
        Logger.getLogger(FormCxC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void buscarPorFechaCuentaCobrar() {
        String textoBusqueda = txtBuscador.getText();
        if (!textoBusqueda.isEmpty()){
        
        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Evitar que las celdas sean editables
                }
            };

            String seleccion = (String) cmbCxC.getSelectedItem();             
            String query = "";
            
            switch (seleccion) {
                case "Cliente" -> {
                    String cliente = txtBuscador.getText();
                    query = "SELECT v1.id AS ventaId, v.nombreCliente AS Cliente, c.fecha, c.deuda, c.totalFaltante, c.descripcion" +
                            " FROM cuentaporcobrar AS c" +
                            " INNER JOIN Venta AS v ON v.id" +
                            " INNER JOIN Venta AS v1 ON v1.id = c.idVenta" +
                            " WHERE v.nombreCliente = '" + cliente + "'";
                }
                case "Dia" -> {
                    String dia = txtBuscador.getText();
                    if (isValidDay(dia)) {
                        query = "SELECT v1.id AS ventaId, v.nombreCliente AS Cliente, c.fecha, c.deuda, c.totalFaltante, c.descripcion" +
                                " FROM cuentaporcobrar AS c" +
                                " INNER JOIN Venta AS v ON v.id" +
                                " INNER JOIN Venta AS v1 ON v1.id = c.idVenta" +
                                " WHERE DAY(c.fecha) = '" + dia + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Día incorrecto. Ingrese un número entre 1 y 31.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Mes" -> {
                    String Mes = txtBuscador.getText();
                    if (isValidMonth(Mes)) {
                        query = "SELECT v1.id AS ventaId, v.nombreCliente AS Cliente, c.fecha, c.deuda, c.totalFaltante, c.descripcion"
                                + " FROM cuentaporcobrar AS c"
                                + " INNER JOIN Venta AS v ON v.id"
                                + " INNER JOIN Venta AS v1 ON v1.id = c.idVenta" 
                                + " WHERE MONTH(c.fecha) = '" + Mes + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Mes incorrecto. Ingrese un número entre 1 y 12.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Año" -> {
                    String ano = txtBuscador.getText();
                    if (isValidYear(ano)) {
                        query = "SELECT v1.id AS ventaId, v.nombreCliente AS Cliente, c.fecha, c.deuda, c.totalFaltante, c.descripcion"
                                + " FROM cuentaporcobrar AS c"
                                + " INNER JOIN Venta AS v ON v.id"
                                + " INNER JOIN Venta AS v1 ON v1.id = c.idVenta" 
                                + " WHERE YEAR(c.fecha) = '" + ano + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Año incorrecto. Ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Fecha completa" -> {
                    String fechaCompleta = txtBuscador.getText();
                    if (isValidDateFormat(fechaCompleta, "yyyy-MM-dd")) {
                        query = "SELECT v1.id AS ventaId, v.nombreCliente AS Cliente, c.fecha, c.deuda, c.totalFaltante, c.descripcion"
                                + "FROM cuentaporcobrar AS c "
                                + " INNER JOIN Venta AS v ON v.id"
                                + " INNER JOIN Venta AS v1 ON v1.id = c.idVenta" 
                                + "WHERE c.fecha = '" + fechaCompleta + "'";
                    } else {
                        JOptionPane.showMessageDialog(this, "Formato de fecha completo incorrecto. Utilice AAAA-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                default -> {
                    JOptionPane.showMessageDialog(this, "Seleccione una opción válida para realizar la búsqueda.", "Error", JOptionPane.ERROR_MESSAGE);
           
                }
            }

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

            TableCobrar.setModel(modelo);
            c.desconectar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormCxC.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        
            JOptionPane.showMessageDialog(this, "Introduzca un valor para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
                   
        }
        
        
        
    }
    private void registrarPagoCuentaCobrar() {
        int selectedRow = TableCobrar.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para registrar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String IdVenta = TableCobrar.getValueAt(selectedRow, 0).toString();
        String clienteDeudor = TableCobrar.getValueAt(selectedRow, 1).toString();
        String deuda = TableCobrar.getValueAt(selectedRow, 3).toString();
        String totalFaltante = TableCobrar.getValueAt(selectedRow, 4).toString();

        RegistrarCobroCxC dialog = new RegistrarCobroCxC(fr, true);

        dialog.setDatosRegistro(IdVenta, clienteDeudor, deuda, totalFaltante);

        dialog.setVisible(true);
    }
    private void eliminarCuentaPorCobrar(String idVenta) {
        try(Connection connection = c.getConexion()) {
            String query = "DELETE FROM cuentaporcobrar WHERE idVenta = ?";
      
            PreparedStatement statement = connection.prepareStatement(query); 
            statement.setString(1, idVenta);
            statement.executeUpdate();
            

        } catch (SQLException ex) {
            Logger.getLogger(FormCxC.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    public void revisarCuentasSaldadas() {
        
       try {
           DefaultTableModel modelo = (DefaultTableModel) TableCobrar.getModel();

           for (int row = modelo.getRowCount() - 1; row >= 0; row--) {
               double totalFaltante = Double.parseDouble(modelo.getValueAt(row, 4).toString());
               if (totalFaltante == 0.00) {
                   String idVenta = String.valueOf(modelo.getValueAt(row, 0));
                   String nombreCliente = modelo.getValueAt(row, 1).toString();

                    eliminarCuentaPorCobrar(idVenta);                 
                   JOptionPane.showMessageDialog(this, "Cuenta del cliente " + nombreCliente + " fue saldada.", "Cuenta Saldada", JOptionPane.INFORMATION_MESSAGE);

               }
           }
       } catch (HeadlessException | NumberFormatException ex) {
           Logger.getLogger(FormCxC.class.getName()).log(Level.SEVERE, null, ex);
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

        PanelCobrar = new javax.swing.JPanel();
        ScrollCobrar = new javax.swing.JScrollPane();
        TableCobrar = new javax.swing.JTable();
        LblTitle = new javax.swing.JLabel();
        cmbCxC = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        LblFormatoFecha = new javax.swing.JLabel();
        btnRegistrarCobro = new Utils.ButtonRounded();
        btnRecargarTabla = new Utils.ButtonRounded();

        PanelCobrar.setBackground(new java.awt.Color(24, 39, 72));
        PanelCobrar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        PanelCobrar.setMinimumSize(new java.awt.Dimension(1115, 760));
        PanelCobrar.setPreferredSize(new java.awt.Dimension(1115, 760));
        PanelCobrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableCobrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Venta", "Cliente", "Fecha de venta", "Monto adeudado", "Total Faltante", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
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
        TableCobrar.getTableHeader().setReorderingAllowed(false);
        ScrollCobrar.setViewportView(TableCobrar);
        if (TableCobrar.getColumnModel().getColumnCount() > 0) {
            TableCobrar.getColumnModel().getColumn(0).setResizable(false);
            TableCobrar.getColumnModel().getColumn(0).setPreferredWidth(10);
            TableCobrar.getColumnModel().getColumn(1).setResizable(false);
            TableCobrar.getColumnModel().getColumn(2).setResizable(false);
            TableCobrar.getColumnModel().getColumn(3).setResizable(false);
            TableCobrar.getColumnModel().getColumn(4).setResizable(false);
            TableCobrar.getColumnModel().getColumn(5).setResizable(false);
        }

        PanelCobrar.add(ScrollCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1080, 580));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Cuentas Por Cobrar");
        PanelCobrar.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        cmbCxC.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 12)); // NOI18N
        cmbCxC.setForeground(new java.awt.Color(255, 255, 255));
        cmbCxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Dia", "Mes", "Año", "Fecha completa" }));
        PanelCobrar.add(cmbCxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 127, 100, 30));
        PanelCobrar.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(892, 127, 150, 30));

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        PanelCobrar.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 127, 60, 30));

        LblFormatoFecha.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        LblFormatoFecha.setForeground(new java.awt.Color(255, 255, 255));
        LblFormatoFecha.setText("AAAA - MM - DD");
        LblFormatoFecha.setToolTipText("Formato de la fecha.");
        PanelCobrar.add(LblFormatoFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 115, 80, 10));

        btnRegistrarCobro.setBackground(new java.awt.Color(51, 204, 0));
        btnRegistrarCobro.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarCobro.setText("REGISTRAR COBRO");
        btnRegistrarCobro.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRegistrarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarCobroActionPerformed(evt);
            }
        });
        PanelCobrar.add(btnRegistrarCobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 27, 132, -1));

        btnRecargarTabla.setBackground(new java.awt.Color(51, 204, 0));
        btnRecargarTabla.setForeground(new java.awt.Color(255, 255, 255));
        btnRecargarTabla.setText("RECARGAR TABLA");
        btnRecargarTabla.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRecargarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarTablaActionPerformed(evt);
            }
        });
        PanelCobrar.add(btnRecargarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 60, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1115, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarCobroActionPerformed
        registrarPagoCuentaCobrar();
        init();
        revisarCuentasSaldadas();
    }//GEN-LAST:event_btnRegistrarCobroActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarPorFechaCuentaCobrar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRecargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarTablaActionPerformed
        init();
        txtBuscador.setText("");
    }//GEN-LAST:event_btnRecargarTablaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblFormatoFecha;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JPanel PanelCobrar;
    private javax.swing.JScrollPane ScrollCobrar;
    private javax.swing.JTable TableCobrar;
    private javax.swing.JButton btnBuscar;
    private Utils.ButtonRounded btnRecargarTabla;
    private Utils.ButtonRounded btnRegistrarCobro;
    private javax.swing.JComboBox<String> cmbCxC;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
