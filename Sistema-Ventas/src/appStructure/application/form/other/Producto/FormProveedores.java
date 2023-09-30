package appStructure.application.form.other.Producto;

import Database.Connect.ConexionSingleton;
import Database.DAO.ProveedorDAO;
import Database.DAOImpl.ProveedorDAOImpl;
import Database.DTO.Proveedor;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samil
 */
public class FormProveedores extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormProveedores() {
        initComponents();
        init();
    }
    
    private void init(){
        iconProveedor.setSvgImage("appStructure/icon/svg/supplier.svg", 260, 260);
        actualizarTablaP();
    }
    public void actualizarTablaP(){
        DefaultTableModel model = new DefaultTableModel() {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false; 
             }
         };
        
        try(Connection cn = c.getConexion()) {
            
            Statement stm = cn.createStatement();
            String query = " SELECT p.id AS ID, p.nombre AS Proveedor, p.correo AS Correo," +
                        " MAX(CASE WHEN tp.telefono = (SELECT MIN(telefono) FROM telefonoproveedor WHERE idproveedor = p.id) THEN tp.telefono ELSE NULL END) AS 'Teléfono 1'," +
                        " MAX(CASE WHEN tp.telefono = (SELECT MAX(telefono) FROM telefonoproveedor WHERE idproveedor = p.id) THEN tp.telefono ELSE NULL END) AS 'Teléfono 2'" +
                        " FROM proveedor p" +
                        " LEFT JOIN telefonoproveedor tp ON p.id = tp.idproveedor" +
                        " GROUP BY p.id, p.nombre, p.correo;";
            
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
            
            TablaProveedores.setModel(model);
            
        } catch (SQLException ex) {
            Logger.getLogger(FormInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registrarProveedor() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String telefono1 = txtTelefono.getText();
        String telefono2 = txtTelefono2.getText();

        try
        {  
            ProveedorDAO prd = new ProveedorDAOImpl();
            List<String> telefonos = new ArrayList<>();

        if (!telefono1.isEmpty()) {
           telefonos.add(telefono1);
        }
        if (!telefono2.isEmpty()) {
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

        actualizarTablaP();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    private void eliminarProveedor() {
        int i = TablaProveedores.getSelectedRow();

        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este proveedor?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            
            String idProveedor = TablaProveedores.getValueAt(i, 0).toString(); 

            try {
                 ProveedorDAO prd = new ProveedorDAOImpl();

                 prd.delete(idProveedor);
                 JOptionPane.showMessageDialog(this, "Proveedor eliminado", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
                 actualizarTablaP();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FormProveedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void editarProveedor(){
    
        if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla o complete los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            aterisco1.setText("*");
            aterisco1.setForeground(Color.red);
            aterisco2.setText("*");
            aterisco2.setForeground(Color.red);
            aterisco3.setText("*");
            aterisco3.setForeground(Color.red);
            aterisco4.setText("*");
            aterisco4.setForeground(Color.red);

        }else{
            
            if(txtTelefono.getText().equals(txtTelefono2.getText())){
                 JOptionPane.showMessageDialog(this, "Los telefonos no pueden ser iguales", "Error", JOptionPane.ERROR_MESSAGE);
                 return;
            }
        
        try {
                
            String id = txtId.getText();
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String telefono1 = txtTelefono.getText();
            String telefono2 = txtTelefono2.getText();                

            ProveedorDAOImpl prd = new ProveedorDAOImpl();
            List<String> telefonos = new ArrayList<String>();
                
            if (!telefono1.isEmpty()) {
               telefonos.add(telefono1);
            }
            if (!telefono2.isEmpty()) {
                telefonos.add(telefono2);
            }

            Proveedor pr = new Proveedor(id, nombre, correo, telefonos);                
            
            prd.update(pr);
            prd.updateTelefonos(pr);
            
            JOptionPane.showMessageDialog(this, "Proveedor actualizado.", "Actualizado", JOptionPane.INFORMATION_MESSAGE);
            limpiar();
            init();
            
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FormProveedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
        
    private void buscador() {
    String textoBusqueda = txtBuscador.getText();  
    if (!textoBusqueda.isEmpty()) {
        
            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

            String seleccion = (String) cmbProveedor.getSelectedItem();

            String query = "";
            switch (seleccion) {
                case "ID" -> {
                    String id = txtBuscador.getText();
                    query = "SELECT p.id AS 'ID', p.nombre AS 'Proveedor', p.correo AS 'Correo', " +
                            "MAX(CASE WHEN tp.telefono = (SELECT MIN(telefono) FROM telefonoproveedor WHERE idproveedor = p.id) THEN tp.telefono ELSE NULL END) AS 'Teléfono 1', " +
                            "MAX(CASE WHEN tp.telefono = (SELECT MAX(telefono) FROM telefonoproveedor WHERE idproveedor = p.id) THEN tp.telefono ELSE NULL END) AS 'Teléfono 2' " +
                            "FROM proveedor p " +
                            "LEFT JOIN telefonoproveedor tp ON p.id = tp.idproveedor " +
                            "WHERE p.id = '" + id + "' " +
                            "GROUP BY p.id, p.nombre, p.correo";
                }
                case "Nombre" -> {
                    String nombre = txtBuscador.getText();
                    query = "SELECT p.id AS 'ID', p.nombre AS 'Proveedor', p.correo AS 'Correo', " +
                            "MAX(CASE WHEN tp.telefono = (SELECT MIN(telefono) FROM telefonoproveedor WHERE idproveedor = p.id) THEN tp.telefono ELSE NULL END) AS 'Teléfono 1', " +
                            "MAX(CASE WHEN tp.telefono = (SELECT MAX(telefono) FROM telefonoproveedor WHERE idproveedor = p.id) THEN tp.telefono ELSE NULL END) AS 'Teléfono 2' " +
                            "FROM proveedor p " +
                            "LEFT JOIN telefonoproveedor tp ON p.id = tp.idproveedor " +
                            "WHERE p.nombre = '" + nombre + "' " +
                            "GROUP BY p.id, p.nombre, p.correo";
                }
                case "Teléfono" -> {
                    String telefono = txtBuscador.getText();
                    query = "SELECT p.id AS 'ID', p.nombre AS 'Proveedor', p.correo AS 'Correo', " +
                            "MAX(CASE WHEN tp.telefono = '" + telefono + "' THEN tp.telefono ELSE NULL END) AS 'Teléfono 1', " +
                            "NULL AS 'Teléfono 2' " +
                            "FROM proveedor p " +
                            "LEFT JOIN telefonoproveedor tp ON p.id = tp.idproveedor " +
                            "WHERE tp.telefono = '" + telefono + "' " +
                            "GROUP BY p.id, p.nombre, p.correo";
                }
                default -> {
                    JOptionPane.showMessageDialog(this, "Seleccione una opción válida para relizar la búsqueda.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            try (Connection connection = c.getConexion()) {
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
            } catch (SQLException ex) {
                Logger.getLogger(FormProveedores.class.getName()).log(Level.SEVERE, null, ex);
            }

            TablaProveedores.setModel(modelo);
            c.desconectar();
        }  catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FormProveedores.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    } else {
        JOptionPane.showMessageDialog(this, "Introduzca un valor para relizar la búsqueda.", "Error", JOptionPane.ERROR_MESSAGE);
    }
        
}   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Content = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        TablaProveedores = new javax.swing.JTable();
        PanelDatos = new javax.swing.JPanel();
        sp1 = new javax.swing.JSeparator();
        sp2 = new javax.swing.JSeparator();
        sp3 = new javax.swing.JSeparator();
        sp4 = new javax.swing.JSeparator();
        sp5 = new javax.swing.JSeparator();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblTelefono1 = new javax.swing.JLabel();
        txtTelefono2 = new javax.swing.JTextField();
        btnEliminar = new Utils.ButtonRounded();
        btnEditar = new Utils.ButtonRounded();
        btnRegistrar = new Utils.ButtonRounded();
        PanelTitle = new javax.swing.JPanel();
        LblDetalle = new javax.swing.JLabel();
        aterisco4 = new javax.swing.JLabel();
        aterisco1 = new javax.swing.JLabel();
        aterisco2 = new javax.swing.JLabel();
        aterisco3 = new javax.swing.JLabel();
        iconProveedor = new Utils.SVGImage2();
        LblTitle = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        LblFormatoFecha = new javax.swing.JLabel();

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setPreferredSize(new java.awt.Dimension(1005, 590));
        Content.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ContentMousePressed(evt);
            }
        });
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Correo", "Teléfono", "Teléfono 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        TablaProveedores.getTableHeader().setReorderingAllowed(false);
        TablaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProveedoresMouseClicked(evt);
            }
        });
        scroll.setViewportView(TablaProveedores);
        if (TablaProveedores.getColumnModel().getColumnCount() > 0) {
            TablaProveedores.getColumnModel().getColumn(0).setResizable(false);
            TablaProveedores.getColumnModel().getColumn(0).setPreferredWidth(15);
            TablaProveedores.getColumnModel().getColumn(1).setResizable(false);
            TablaProveedores.getColumnModel().getColumn(2).setResizable(false);
            TablaProveedores.getColumnModel().getColumn(3).setResizable(false);
            TablaProveedores.getColumnModel().getColumn(4).setResizable(false);
        }

        Content.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 740, 600));

        PanelDatos.setBackground(new java.awt.Color(255, 255, 255));
        PanelDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelDatosMousePressed(evt);
            }
        });
        PanelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sp1.setBackground(new java.awt.Color(0, 0, 0));
        sp1.setForeground(new java.awt.Color(0, 0, 0));
        PanelDatos.add(sp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 140, 10));

        sp2.setBackground(new java.awt.Color(0, 0, 0));
        sp2.setForeground(new java.awt.Color(0, 0, 0));
        PanelDatos.add(sp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 140, 10));

        sp3.setBackground(new java.awt.Color(0, 0, 0));
        sp3.setForeground(new java.awt.Color(0, 0, 0));
        PanelDatos.add(sp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 140, 10));

        sp4.setBackground(new java.awt.Color(0, 0, 0));
        sp4.setForeground(new java.awt.Color(0, 0, 0));
        PanelDatos.add(sp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 140, 10));

        sp5.setBackground(new java.awt.Color(0, 0, 0));
        sp5.setForeground(new java.awt.Color(0, 0, 0));
        PanelDatos.add(sp5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 140, 10));

        lblId.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblId.setForeground(new java.awt.Color(0, 0, 0));
        lblId.setText("ID Proveedor:");
        PanelDatos.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, 20));

        txtId.setBackground(new java.awt.Color(255, 255, 255));
        txtId.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtId.setForeground(new java.awt.Color(0, 0, 0));
        txtId.setToolTipText("");
        txtId.setBorder(null);
        PanelDatos.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 140, 20));

        lblNombre.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre.setText("Nombre:");
        PanelDatos.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setToolTipText("");
        txtNombre.setBorder(null);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        PanelDatos.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 140, 20));

        lblCorreo.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(0, 0, 0));
        lblCorreo.setText("Correo:");
        PanelDatos.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, -1, 20));

        txtCorreo.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreo.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo.setToolTipText("");
        txtCorreo.setBorder(null);
        PanelDatos.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 140, 20));

        lblTelefono.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(0, 0, 0));
        lblTelefono.setText("Telefono 1:");
        PanelDatos.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, 20));

        txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(0, 0, 0));
        txtTelefono.setToolTipText("");
        txtTelefono.setBorder(null);
        PanelDatos.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 140, 20));

        lblTelefono1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblTelefono1.setForeground(new java.awt.Color(0, 0, 0));
        lblTelefono1.setText("Telefono 2:");
        PanelDatos.add(lblTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, -1, 20));

        txtTelefono2.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono2.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtTelefono2.setForeground(new java.awt.Color(0, 0, 0));
        txtTelefono2.setToolTipText("");
        txtTelefono2.setBorder(null);
        PanelDatos.add(txtTelefono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 140, 20));

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        PanelDatos.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 520, 100, -1));

        btnEditar.setBackground(new java.awt.Color(24, 39, 72));
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("EDITAR");
        btnEditar.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        PanelDatos.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 100, -1));

        btnRegistrar.setBackground(new java.awt.Color(51, 204, 0));
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        PanelDatos.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 100, -1));

        PanelTitle.setBackground(new java.awt.Color(0, 112, 192));
        PanelTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelTitleMousePressed(evt);
            }
        });
        PanelTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblDetalle.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        LblDetalle.setForeground(new java.awt.Color(255, 255, 255));
        LblDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDetalle.setText("Datos del Proveedor");
        LblDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LblDetalleMousePressed(evt);
            }
        });
        PanelTitle.add(LblDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 6, -1, -1));

        PanelDatos.add(PanelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 40));

        aterisco4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        aterisco4.setForeground(new java.awt.Color(255, 0, 51));
        PanelDatos.add(aterisco4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 20, 20));

        aterisco1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        aterisco1.setForeground(new java.awt.Color(255, 0, 51));
        PanelDatos.add(aterisco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 20, 20));

        aterisco2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        aterisco2.setForeground(new java.awt.Color(255, 0, 51));
        PanelDatos.add(aterisco2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 20, 20));

        aterisco3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        aterisco3.setForeground(new java.awt.Color(255, 0, 51));
        PanelDatos.add(aterisco3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 360, 20, 20));

        iconProveedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iconProveedorMousePressed(evt);
            }
        });
        PanelDatos.add(iconProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 250, 210));

        Content.add(PanelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, 330, 600));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Proveedores");
        LblTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LblTitleMousePressed(evt);
            }
        });
        Content.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        cmbProveedor.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 12)); // NOI18N
        cmbProveedor.setForeground(new java.awt.Color(255, 255, 255));
        cmbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Teléfono" }));
        Content.add(cmbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 100, 30));
        Content.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, 159, 30));

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        Content.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 100, 70, 30));

        LblFormatoFecha.setBackground(new java.awt.Color(255, 255, 255));
        LblFormatoFecha.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        LblFormatoFecha.setForeground(new java.awt.Color(255, 255, 255));
        LblFormatoFecha.setText("AAAA - MM - DD");
        LblFormatoFecha.setToolTipText("Formato de la fecha.");
        Content.add(LblFormatoFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 90, 80, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed

    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscador();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos obligatorios (*) antes de guardar los cambios.", "Error", JOptionPane.ERROR_MESSAGE);
            aterisco1.setText("*");
            aterisco1.setForeground(Color.red);
            aterisco2.setText("*");
            aterisco2.setForeground(Color.red);
            aterisco3.setText("*");
            aterisco3.setForeground(Color.red);
            aterisco4.setText("*");
            aterisco4.setForeground(Color.red);
        
        }else{
            
        if(txtTelefono.getText().equals(txtTelefono2.getText())){                
            JOptionPane.showMessageDialog(this, "Los telefonos no pueden ser iguales", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
            
        registrarProveedor();
        init();
        
        limpiar();
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void TablaProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProveedoresMouseClicked
    int i = TablaProveedores.getSelectedRow();

    if (i != -1) {
        
        DefaultTableModel model = (DefaultTableModel) TablaProveedores.getModel();
        String id = (String) model.getValueAt(i, 0);        txtId.setText(id);
        String nombre = (String) model.getValueAt(i, 1);
        String correo = (String) model.getValueAt(i, 2);
        String telefono1 = (String) model.getValueAt(i, 3);
        String telefono2 = (String) model.getValueAt(i, 4);
        
        txtId.setText(id);
        txtNombre.setText(nombre);
        txtCorreo.setText(correo);
        txtTelefono.setText(telefono1);
        if(!telefono2.isEmpty()){txtTelefono2.setText(telefono2);}

        btnRegistrar.setVisible(false);

    } 
        
    }//GEN-LAST:event_TablaProveedoresMouseClicked

    private void ContentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContentMousePressed
        deseleccion();
    }//GEN-LAST:event_ContentMousePressed

    private void LblTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblTitleMousePressed
        deseleccion();
    }//GEN-LAST:event_LblTitleMousePressed

    private void PanelDatosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelDatosMousePressed
        deseleccion();
    }//GEN-LAST:event_PanelDatosMousePressed

    private void iconProveedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconProveedorMousePressed
        deseleccion();
    }//GEN-LAST:event_iconProveedorMousePressed

    private void PanelTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelTitleMousePressed
        deseleccion();
    }//GEN-LAST:event_PanelTitleMousePressed

    private void LblDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblDetalleMousePressed
        deseleccion();
    }//GEN-LAST:event_LblDetalleMousePressed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarProveedor();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editarProveedor();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void limpiar(){
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
    private void deseleccion(){
           
    int i = TablaProveedores.getSelectedRow();

    if (i != -1) {
        TablaProveedores.clearSelection();
        btnRegistrar.setVisible(true);
        btnEditar.setVisible(true);
        limpiar();
    }
            
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblDetalle;
    private javax.swing.JLabel LblFormatoFecha;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JPanel PanelTitle;
    private javax.swing.JTable TablaProveedores;
    private javax.swing.JLabel aterisco1;
    private javax.swing.JLabel aterisco2;
    private javax.swing.JLabel aterisco3;
    private javax.swing.JLabel aterisco4;
    private javax.swing.JButton btnBuscar;
    private Utils.ButtonRounded btnEditar;
    private Utils.ButtonRounded btnEliminar;
    private Utils.ButtonRounded btnRegistrar;
    private javax.swing.JComboBox<String> cmbProveedor;
    private Utils.SVGImage2 iconProveedor;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JSeparator sp1;
    private javax.swing.JSeparator sp2;
    private javax.swing.JSeparator sp3;
    private javax.swing.JSeparator sp4;
    private javax.swing.JSeparator sp5;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono2;
    // End of variables declaration//GEN-END:variables
}
