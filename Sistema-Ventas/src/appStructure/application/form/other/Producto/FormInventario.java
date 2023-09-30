package appStructure.application.form.other.Producto;

import Database.Connect.ConexionSingleton;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samil
 */
public class FormInventario extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormInventario() {
        initComponents();
        init();
    }

    private void init(){    
        actualizarTablaI();
    }
    public void actualizarTablaI(){
        DefaultTableModel model = new DefaultTableModel() {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false; 
             }
         };
        
        try(Connection cn = c.getConexion()) {
            
            Statement stm = cn.createStatement();
            String query = "SELECT i.idProducto as 'ID Producto', p.descripcion as Descripción, p.size as Tamaño, i.entrada AS Entradas, i.salida AS Salidas, i.stock AS 'Stock actual' from Inventario as i "
                    + "INNER join Producto as p on p.id = i.idProducto";
            
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            for (int column = 1; column <= columnCount; column++) {
                 model.addColumn(metaData.getColumnLabel(column));
             }

            while (rs.next()) {
                 Object[] rowData = new Object[columnCount];
                 for (int column = 1; column <= columnCount; column++) {
                     rowData[column - 1] = rs.getObject(column);
                 }
                 model.addRow(rowData);
             }
            
            tableProductos.setModel(model);
            
        } catch (SQLException ex) {
            Logger.getLogger(FormInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void busqueda() {
        
    String textoBusqueda = txtBuscador.getText();
    if (!textoBusqueda.isEmpty()) {        
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
                 case "ID" -> {
                     String id = txtBuscador.getText();
                     query = "SELECT i.idProducto AS 'ID Producto', p.descripcion AS 'Nombre del producto', p.size as Tamaño, i.entrada AS Entradas, i.salida AS Salidas, i.stock as 'Stock actual' from Inventario as i "
                             + "inner join Producto as p on p.id = i.idProducto"
                             + " WHERE i.idProducto = '" + id + "'";
                 }
                 case "Nombre" -> {
                     String descripcion = txtBuscador.getText();
                     query = "SELECT i.idProducto AS 'ID Producto', p.descripcion AS 'Nombre del producto', p.size as Tamaño, i.entrada AS Entradas, i.salida AS Salidas, i.stock as 'Stock actual' from Inventario as i "
                             + "inner join Producto as p on p.id = i.idProducto"
                             + " where p.descripcion = '" + descripcion + "'";
                 }
                 case "Tamaño" -> {
                     String size = txtBuscador.getText();
                     query = "SELECT i.idProducto AS 'ID Producto', p.descripcion AS 'Nombre del producto', p.size as Tamaño, i.entrada AS Entradas, i.salida AS Salidas, i.stock as 'Stock actual' from Inventario as i "
                             + "INNER JOIN Producto as p on p.id = i.idProducto"
                             + " WHERE p.size = '" + size + "'";
                 }
                 default -> {
                    JOptionPane.showMessageDialog(this, "Seleccione una opción válida para relizar la búsqueda.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                 }
             }

             try (Connection connection = c.getConexion();
                 ) {
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

             tableProductos.setModel(modelo);
         } catch (SQLException ex) {
             Logger.getLogger(FormInventario.class.getName()).log(Level.SEVERE, null, ex);
         }
    } else {
    
                JOptionPane.showMessageDialog(this, "Introduzca un valor para relizar la búsqueda.", "Error", JOptionPane.ERROR_MESSAGE);
    }
 }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        LblUtilidad4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Content = new javax.swing.JPanel();
        cmbIngresos = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        LblFormatoFecha = new javax.swing.JLabel();
        btnRecargarTabla = new Utils.ButtonRounded();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();

        content.setBackground(new java.awt.Color(24, 39, 72));
        content.setPreferredSize(new java.awt.Dimension(1005, 618));
        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblUtilidad4.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad4.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblUtilidad4.setForeground(new java.awt.Color(255, 255, 255));
        LblUtilidad4.setText("Detalle de Inventario");
        content.add(LblUtilidad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        jPanel1.setMinimumSize(new java.awt.Dimension(1115, 760));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setVerifyInputWhenFocusTarget(false);

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setPreferredSize(new java.awt.Dimension(1115, 760));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbIngresos.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 12)); // NOI18N
        cmbIngresos.setForeground(new java.awt.Color(255, 255, 255));
        cmbIngresos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Tamaño" }));
        Content.add(cmbIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 127, 100, 30));

        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyTyped(evt);
            }
        });
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
        Content.add(btnRecargarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 60, 140, -1));

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        Content.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 127, 60, 30));

        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Producto", "Nombre", "Tamaño del producto", "Entradas", "Salidas", "Stock Actual"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        tableProductos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableProductos);
        if (tableProductos.getColumnModel().getColumnCount() > 0) {
            tableProductos.getColumnModel().getColumn(0).setResizable(false);
            tableProductos.getColumnModel().getColumn(0).setPreferredWidth(15);
            tableProductos.getColumnModel().getColumn(1).setResizable(false);
            tableProductos.getColumnModel().getColumn(2).setResizable(false);
            tableProductos.getColumnModel().getColumn(3).setResizable(false);
            tableProductos.getColumnModel().getColumn(4).setResizable(false);
            tableProductos.getColumnModel().getColumn(5).setResizable(false);
        }

        Content.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1080, 580));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        content.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        busqueda();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRecargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarTablaActionPerformed
        init();
        txtBuscador.setText("");
    }//GEN-LAST:event_btnRecargarTablaActionPerformed

    private void txtBuscadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyTyped
        char teclapress =evt.getKeyChar();

        if(teclapress == KeyEvent.VK_ENTER){
            busqueda();
        }
    }//GEN-LAST:event_txtBuscadorKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblFormatoFecha;
    private javax.swing.JLabel LblUtilidad4;
    private javax.swing.JButton btnBuscar;
    private Utils.ButtonRounded btnRecargarTabla;
    private javax.swing.JComboBox<String> cmbIngresos;
    private javax.swing.JPanel content;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProductos;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
