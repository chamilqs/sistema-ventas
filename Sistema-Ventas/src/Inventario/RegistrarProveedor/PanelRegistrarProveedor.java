
package Inventario.RegistrarProveedor;
import Connect.Conexion;
import DAO.ProveedorDAO;
import DAOImpl.ProveedorDAOImpl;
import DTO.Proveedor;
import Utils.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 *
 * @author baez_
 */
public class PanelRegistrarProveedor extends javax.swing.JPanel {

    
    
    public PanelRegistrarProveedor() {
        initComponents();
        cargarProveedores();
        btnguardarCambio.setVisible(false);
        //inicializar();
        Utils.setScaledImage(lblLogo, "contacto.png", 100, 100);
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Content = new javax.swing.JPanel();
        scrProveedores = new javax.swing.JScrollPane();
        tableProveedores = new javax.swing.JTable();
        panelLateralDerecho = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        separatorNombre = new javax.swing.JSeparator();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        separatorId = new javax.swing.JSeparator();
        btnReload = new rojerusan.RSMaterialButtonRectangle();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        separatorCorreo = new javax.swing.JSeparator();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        separatorTelefono = new javax.swing.JSeparator();
        btnAdd = new rojerusan.RSMaterialButtonRectangle();
        lblTelefono1 = new javax.swing.JLabel();
        separatorTelefono1 = new javax.swing.JSeparator();
        txtTelefono2 = new javax.swing.JTextField();
        btnEliminar = new rojerusan.RSMaterialButtonRectangle();
        aterisco4 = new javax.swing.JLabel();
        aterisco1 = new javax.swing.JLabel();
        aterisco2 = new javax.swing.JLabel();
        aterisco3 = new javax.swing.JLabel();
        btnguardarCambio = new rojerusan.RSMaterialButtonRectangle();
        btnEditar = new rojerusan.RSMaterialButtonRectangle();
        lblLogo = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1005, 590));
        setMinimumSize(new java.awt.Dimension(1005, 590));
        setPreferredSize(new java.awt.Dimension(1005, 660));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setPreferredSize(new java.awt.Dimension(1005, 590));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Correo", "Telefono", "Telefono 2", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tableProveedores.getTableHeader().setReorderingAllowed(false);
        scrProveedores.setViewportView(tableProveedores);
        if (tableProveedores.getColumnModel().getColumnCount() > 0) {
            tableProveedores.getColumnModel().getColumn(0).setResizable(false);
            tableProveedores.getColumnModel().getColumn(1).setResizable(false);
            tableProveedores.getColumnModel().getColumn(2).setResizable(false);
            tableProveedores.getColumnModel().getColumn(3).setResizable(false);
            tableProveedores.getColumnModel().getColumn(4).setResizable(false);
            tableProveedores.getColumnModel().getColumn(5).setResizable(false);
        }

        Content.add(scrProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 620));

        panelLateralDerecho.setBackground(new java.awt.Color(255, 255, 255));
        panelLateralDerecho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblNombre.setText("Nombre:");
        panelLateralDerecho.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        txtNombre.setBorder(null);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 140, 20));

        separatorNombre.setBackground(new java.awt.Color(0, 0, 0));
        separatorNombre.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(separatorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 140, 10));

        lblId.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblId.setText("ID:");
        panelLateralDerecho.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, 20));

        txtId.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtId.setBorder(null);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 140, 20));

        separatorId.setBackground(new java.awt.Color(0, 0, 0));
        separatorId.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(separatorId, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 140, 10));

        btnReload.setBackground(new java.awt.Color(24, 39, 72));
        btnReload.setText("Reload");
        btnReload.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(btnReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 120, 40));

        lblCorreo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblCorreo.setText("Correo:");
        panelLateralDerecho.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 20));

        txtCorreo.setBorder(null);
        panelLateralDerecho.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 150, 20));

        separatorCorreo.setBackground(new java.awt.Color(0, 0, 0));
        separatorCorreo.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(separatorCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 150, 10));

        lblTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblTelefono.setText("Telefono 1:");
        panelLateralDerecho.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 20));

        txtTelefono.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtTelefono.setBorder(null);
        panelLateralDerecho.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 130, 20));

        separatorTelefono.setBackground(new java.awt.Color(0, 0, 0));
        separatorTelefono.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(separatorTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 130, -1));

        btnAdd.setBackground(new java.awt.Color(0, 204, 51));
        btnAdd.setText("Registrar");
        btnAdd.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 120, 40));

        lblTelefono1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblTelefono1.setText("Telefono 2:");
        panelLateralDerecho.add(lblTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 20));

        separatorTelefono1.setBackground(new java.awt.Color(0, 0, 0));
        separatorTelefono1.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(separatorTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 130, 10));

        txtTelefono2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtTelefono2.setBorder(null);
        panelLateralDerecho.add(txtTelefono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 130, 20));

        btnEliminar.setBackground(new java.awt.Color(24, 39, 72));
        btnEliminar.setText("Eliminar");
        btnEliminar.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 120, 40));

        aterisco4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelLateralDerecho.add(aterisco4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 20, 20));

        aterisco1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelLateralDerecho.add(aterisco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 20, 20));

        aterisco2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelLateralDerecho.add(aterisco2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 20, 20));

        aterisco3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelLateralDerecho.add(aterisco3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 20, 20));

        btnguardarCambio.setBackground(new java.awt.Color(24, 39, 72));
        btnguardarCambio.setText("Save");
        btnguardarCambio.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnguardarCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarCambioActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(btnguardarCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 120, 40));

        btnEditar.setBackground(new java.awt.Color(24, 39, 72));
        btnEditar.setText("Editar");
        btnEditar.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 120, 40));

        Content.add(panelLateralDerecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, 285, 390));

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Content.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 260, 210));

        add(Content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 660));
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
             
        
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
         cargarProveedores();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
       
         
    if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtTelefono.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Rellene los campos obligatorios antes de cargar los proveedores.", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
        aterisco1.setText("*");
        aterisco1.setForeground(Color.red);
        aterisco2.setText("*");
        aterisco2.setForeground(Color.red);
        aterisco3.setText("*");
        aterisco3.setForeground(Color.red);
        aterisco4.setText("*");
        aterisco4.setForeground(Color.red);
        return;
    }else  
        
        insertarProveedor();
        cargarProveedores();
        
        txtId.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtTelefono2.setText("");
        
        aterisco1.setText("");
        aterisco2.setText("");
        aterisco3.setText("");
        aterisco4.setText("");
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarProveedor();
        
        cargarProveedores();

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnguardarCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarCambioActionPerformed
      
        if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtTelefono.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Complete los campos obligatorios (*) antes de guardar los cambios.", "Campos Obligatorios", JOptionPane.ERROR_MESSAGE);
        aterisco1.setText("*");
        aterisco1.setForeground(Color.red);
        aterisco2.setText("*");
        aterisco2.setForeground(Color.red);
        aterisco3.setText("*");
        aterisco3.setForeground(Color.red);
        aterisco4.setText("*");
        aterisco4.setForeground(Color.red);
        
    }else{
            
            if(txtTelefono.getText().equals(txtTelefono2.getText()))
                {
                 JOptionPane.showMessageDialog(this, "Los telefonos no pueden ser iguales", "Error", JOptionPane.ERROR_MESSAGE);
                 return;
                }
        guardarCambiosSeleccionados();
        cargarProveedores();
        btnguardarCambio.setVisible(false);
        txtId.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtTelefono2.setText("");
        
        aterisco1.setText("");
        aterisco2.setText("");
        aterisco3.setText("");
        aterisco4.setText("");
        }
    }//GEN-LAST:event_btnguardarCambioActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       editarProveedor();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void cargarProveedores() {

    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        String query = " SELECT p.id AS ID, p.nombre AS Proveedor, p.correo AS Correo,\n" +
"        MAX(CASE WHEN tp.telefono = (SELECT MIN(telefono) FROM telefonoproveedor WHERE idproveedor = p.id) THEN tp.telefono ELSE NULL END) AS 'Teléfono 1',\n" +
"        MAX(CASE WHEN tp.telefono = (SELECT MAX(telefono) FROM telefonoproveedor WHERE idproveedor = p.id) THEN tp.telefono ELSE NULL END) AS 'Teléfono 2'\n" +
"        FROM proveedor p\n" +
"        LEFT JOIN telefonoproveedor tp ON p.id = tp.idproveedor\n" +
"        GROUP BY p.id, p.nombre, p.correo;";

        
      /*  SELECT p.id AS ID, p.nombre AS Proveedor, p.correo AS Correo,
        MAX(CASE WHEN tp.telefono = (SELECT MIN(telefono) FROM telefonoproveedor WHERE idproveedor = p.id) THEN tp.telefono ELSE NULL END) AS 'Teléfono 1',
        MAX(CASE WHEN tp.telefono = (SELECT MAX(telefono) FROM telefonoproveedor WHERE idproveedor = p.id) THEN tp.telefono ELSE NULL END) AS 'Teléfono 2',
        p.descripcion AS Descripcion
        FROM proveedor p
        LEFT JOIN telefonoproveedor tp ON p.id = tp.idproveedor
        GROUP BY p.id, p.nombre, p.correo, p.descripcion;*/


        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            modelo.addColumn("ID");
            modelo.addColumn("Proveedor");
            modelo.addColumn("Correo");
            modelo.addColumn("Teléfono 1");
            modelo.addColumn("Teléfono 2");

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        tableProveedores.setModel(modelo);

        // Hacer que la tabla no sea redimensionable
        tableProveedores.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < tableProveedores.getColumnCount(); i++) {
            tableProveedores.getColumnModel().getColumn(i).setResizable(false);
        }
    } catch (SQLException ex) {
        Logger.getLogger(PanelRegistrarProveedor.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void insertarProveedor() {
    String id = txtId.getText();
    String nombre = txtNombre.getText();
    String correo = txtCorreo.getText();
    String telefono1 = txtTelefono.getText();
    String telefono2 = txtTelefono2.getText();

    // Insertar proveedor en la tabla 'proveedor'

    try
    {  
        ProveedorDAO prd = new ProveedorDAOImpl();
             
        List<String> telefonos = new ArrayList<String>();
       
   
        // Insertar teléfonos en la tabla 'telefonoproveedor'
    if (!telefono1.isEmpty()) {
       // insertTelefonoProveedor(id, telefono1);
       telefonos.add(telefono1);
    }
    if (!telefono2.isEmpty()) {
        //insertTelefonoProveedor(id, telefono2);
        telefonos.add(telefono2);
    }
    
    if(telefonos.size() > 1)
    {
        if(telefonos.get(0).equals(telefonos.get(1)))
        {
            JOptionPane.showMessageDialog(this, "Los telefonos no pueden ser iguales", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    
    if(!telefonos.isEmpty())
    {
        Proveedor pr = new Proveedor(id, nombre, correo, telefonos);
     
        prd.insert(pr);
    }else
    {
        Proveedor pr = new Proveedor(id, nombre, correo);
     
        prd.insert(pr);
    }
    
            
        
    } catch (SQLException ex) {
        Logger.getLogger(PanelRegistrarProveedor.class.getName()).log(Level.SEVERE, null, ex);
    }

    
}

//    private void insertTelefonoProveedor(String idProveedor, String telefono) {
//    String insertTelefono = "INSERT INTO telefonoproveedor (idproveedor, telefono) VALUES (?, ?)";
//    try (Connection connection = Conexion.conectar();
//         PreparedStatement preparedStatement = connection.prepareStatement(insertTelefono)) {
//        
//        preparedStatement.setString(1, idProveedor);
//        preparedStatement.setString(2, telefono);
//        
//        preparedStatement.executeUpdate();
//    } catch (SQLException ex) {
//        Logger.getLogger(PanelRegistrarProveedor.class.getName()).log(Level.SEVERE, null, ex);
//    }
//}

    private void eliminarProveedor() {
    int selectedRow = tableProveedores.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este proveedor?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        String idProveedor = tableProveedores.getValueAt(selectedRow, 0).toString(); 


        try {
             ProveedorDAO prd = new ProveedorDAOImpl();
             
             prd.delete(idProveedor);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al acceder a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    private void VerDescripcion() {
    int selectedRow = tableProveedores.getSelectedRow();

    String descripcion = tableProveedores.getValueAt(selectedRow, 5).toString(); // Index 5 is the column for "Descripción"

//    if (descripcion.isEmpty()) {
//        JOptionPane.showMessageDialog(this, "Este proveedor no tiene una descripción disponible.", "Descripción del Proveedor", JOptionPane.INFORMATION_MESSAGE);
//        return;
//    }

    JTextArea textArea = new JTextArea(descripcion);
    textArea.setWrapStyleWord(true);
    textArea.setLineWrap(true);
    textArea.setCaretPosition(0);
    textArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(400, 200));

    JOptionPane.showMessageDialog(this, scrollPane, "Descripción del Proveedor", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarProveedor() {
        int selectedRow = tableProveedores.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idProveedor = tableProveedores.getValueAt(selectedRow, 0).toString(); 

        String nombreProveedor = tableProveedores.getValueAt(selectedRow, 1) != null ? tableProveedores.getValueAt(selectedRow, 1).toString() : "";
        String correoProveedor = tableProveedores.getValueAt(selectedRow, 2) != null ? tableProveedores.getValueAt(selectedRow, 2).toString() : "";
        String Telefono1Proveedor = tableProveedores.getValueAt(selectedRow, 3) != null ? tableProveedores.getValueAt(selectedRow, 3).toString() : "";
        String Telefono2Proveedor = tableProveedores.getValueAt(selectedRow, 4) != null ? tableProveedores.getValueAt(selectedRow, 4).toString() : "";

        txtId.setText(idProveedor);
        txtNombre.setText(nombreProveedor);
        txtCorreo.setText(correoProveedor);
        txtTelefono.setText(Telefono1Proveedor);
        txtTelefono2.setText(Telefono2Proveedor);
        
        btnguardarCambio.setVisible(true);
    }
    
    private void guardarCambiosSeleccionados() {
    int selectedRow = tableProveedores.getSelectedRow();

//    if (selectedRow == -1) {
//        JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para guardar los cambios.", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }

    String idProveedor = tableProveedores.getValueAt(selectedRow, 0).toString(); 
    String Telefono1 = tableProveedores.getValueAt(selectedRow, 3).toString();
    String Telefono2 = tableProveedores.getValueAt(selectedRow, 4).toString();
    

        confirmarCambios(idProveedor, Telefono1, Telefono2);
    
    
    }

    private void confirmarCambios(String idProveedor, String oldTelefono1, String oldTelefono2) {
        
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de guardar los cambios?", "Confirmación de edición", JOptionPane.YES_NO_OPTION); 
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                
                 String id = txtId.getText();
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();
                String telefono1 = txtTelefono.getText();
                String telefono2 = txtTelefono2.getText();
                
                
                if(telefono1.equals(telefono2))
                {
                 JOptionPane.showMessageDialog(this, "Los telefonos no pueden ser iguales", "Error", JOptionPane.ERROR_MESSAGE);
                 return;
                }
                ProveedorDAOImpl prd = new ProveedorDAOImpl();
             
                List<String> telefonos = new ArrayList<String>();
                
                


                // Insertar teléfonos en la tabla 'telefonoproveedor'
            if (!telefono1.isEmpty()) {
               // insertTelefonoProveedor(id, telefono1);
               telefonos.add(telefono1);
            }
            if (!telefono2.isEmpty()) {
                //insertTelefonoProveedor(id, telefono2);
                telefonos.add(telefono2);
            }

            Proveedor pr = new Proveedor(id, nombre, correo, telefonos);
                
            
            prd.update(pr);
            prd.updateTelefonos(pr);
            
                JOptionPane.showMessageDialog(this, "Registro exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    txtId.setText("");
                    txtNombre.setText("");
                    txtCorreo.setText("");
                    txtTelefono.setText("");
                    txtTelefono2.setText("");
                cargarProveedores(); 
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "No se pudo editar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    private void inicializar() {
        // Otros códigos de inicialización...

        txtId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char teclaPress = evt.getKeyChar();
                if (teclaPress == KeyEvent.VK_ENTER) {
                    btnAdd.doClick();
                }
            }
        });

        txtId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char teclaPress = evt.getKeyChar();
                if (teclaPress == KeyEvent.VK_ENTER) {
                    //btnVerDescripcion.doClick();
                }
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel aterisco1;
    private javax.swing.JLabel aterisco2;
    private javax.swing.JLabel aterisco3;
    private javax.swing.JLabel aterisco4;
    private rojerusan.RSMaterialButtonRectangle btnAdd;
    private rojerusan.RSMaterialButtonRectangle btnEditar;
    private rojerusan.RSMaterialButtonRectangle btnEliminar;
    private rojerusan.RSMaterialButtonRectangle btnReload;
    private rojerusan.RSMaterialButtonRectangle btnguardarCambio;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JPanel panelLateralDerecho;
    private javax.swing.JScrollPane scrProveedores;
    private javax.swing.JSeparator separatorCorreo;
    private javax.swing.JSeparator separatorId;
    private javax.swing.JSeparator separatorNombre;
    private javax.swing.JSeparator separatorTelefono;
    private javax.swing.JSeparator separatorTelefono1;
    private javax.swing.JTable tableProveedores;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono2;
    // End of variables declaration//GEN-END:variables
}
