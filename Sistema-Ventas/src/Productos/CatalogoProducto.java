/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Productos;

import Connect.Conexion;
import DAO.ProductoDAO;
import DAOImpl.ProductoDAOImpl;
import DTO.Producto;
import DTO.Usuario;
import Utils.Utils;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angelo
 */
public class CatalogoProducto extends javax.swing.JPanel {
 
    /**
     * Creates new form CatalogoProducto
     */
    Usuario u = null;  
    public CatalogoProducto() {
        initComponents();
         Utils.setScaledImage(IconAddProduct, "agregar-producto.png", 150, 150);
         actualizarTabla();
         inicializar();
         
           


    }
    
    public CatalogoProducto(Usuario u) {
       initComponents();
         Utils.setScaledImage(IconAddProduct, "agregar-producto.png", 150, 150);
         actualizarTabla();
         this.u = u;
         inicializar();
         
    }
    
    private void inicializar(){
        System.out.println("Nivel de acceso: " + u.getNivelAcceso());

         
        if ("user".equals(u.getNivelAcceso())) {
            panelLateralDerecho.setVisible(false);
            IconAddProduct.setVisible(false);
            txtId.setVisible(false);
            txtDescripcion.setVisible(false);
            txtSize.setVisible(false);
            txtPrecio.setVisible(false);
            chkbxPrioridad.setVisible(false);
            btnEditar.setVisible(false);
            btnEliminar.setVisible(false);
            btnAgregar.setVisible(false);
            lblPrecio.setVisible(false);
            lblSize.setVisible(false);
            lblNombre.setVisible(false);
            lblId.setVisible(false);

            LblProductoSelect.setVisible(true);
            ProductoSelectPanel.setVisible(true);

            // Agrega un listener a la tablaCatalogo para detectar selecciones
           tablaCatalogo.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println("Selection Event Triggered");

                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = tablaCatalogo.getSelectedRow();
                        if (selectedRow != -1) {
                            Object selectedProductName = tablaCatalogo.getValueAt(selectedRow, 1);
                            if (selectedProductName != null) {
                                String productName = selectedProductName.toString();
                                System.out.println("Selected Product Name: " + productName);
                                LblProductoSelect.setText(productName);
                            }
                        }
                    }
                }
            });

        }
            
        else if("admin".equals(u.getNivelAcceso())) {
           
        panelLateralDerecho.setVisible(true);
        IconAddProduct.setVisible(true);
        txtId.setVisible(true);
        txtDescripcion.setVisible(true);
        txtSize.setVisible(true);
        txtPrecio.setVisible(true);
        chkbxPrioridad.setVisible(true);
        btnEditar.setVisible(true);
        btnEliminar.setVisible(true);
        btnAgregar.setVisible(true);
        lblPrecio.setVisible(true);
        lblSize.setVisible(true);
        lblNombre.setVisible(true);
        lblId.setVisible(true);
        
        LblProductoSelect.setVisible(false);
        ProductoSelectPanel.setVisible(false);

    }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelBarBlue = new javax.swing.JPanel();
        Content = new javax.swing.JPanel();
        scrTablaCatalogo = new javax.swing.JScrollPane();
        tablaCatalogo = new javax.swing.JTable();
        btnBuscar = new rojerusan.RSMaterialButtonRectangle();
        cmbBusqueda = new javax.swing.JComboBox<>();
        txtBusqueda = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        panelLateralDerecho = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblSize = new javax.swing.JLabel();
        txtSize = new javax.swing.JTextField();
        btnAgregar = new rojerusan.RSMaterialButtonRectangle();
        btnEditar = new rojerusan.RSMaterialButtonRectangle();
        btnEliminar = new rojerusan.RSMaterialButtonRectangle();
        IconAddProduct = new javax.swing.JLabel();
        chkbxPrioridad = new javax.swing.JCheckBox();
        ProductoSelectPanel = new javax.swing.JPanel();
        LblProductoSelect = new javax.swing.JLabel();
        BtnReload = new rojerusan.RSMaterialButtonRectangle();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelBarBlue.setBackground(new java.awt.Color(24, 39, 72));
        PanelBarBlue.setPreferredSize(new java.awt.Dimension(1005, 40));

        javax.swing.GroupLayout PanelBarBlueLayout = new javax.swing.GroupLayout(PanelBarBlue);
        PanelBarBlue.setLayout(PanelBarBlueLayout);
        PanelBarBlueLayout.setHorizontalGroup(
            PanelBarBlueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1005, Short.MAX_VALUE)
        );
        PanelBarBlueLayout.setVerticalGroup(
            PanelBarBlueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(PanelBarBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Content.setBackground(new java.awt.Color(255, 255, 255));
        Content.setPreferredSize(new java.awt.Dimension(1005, 620));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaCatalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "IDProducto", "Producto", "Tamaño", "Precio", "Prioridad"
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
        tablaCatalogo.setGridColor(new java.awt.Color(255, 255, 255));
        tablaCatalogo.getTableHeader().setReorderingAllowed(false);
        tablaCatalogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCatalogoMouseClicked(evt);
            }
        });
        scrTablaCatalogo.setViewportView(tablaCatalogo);
        if (tablaCatalogo.getColumnModel().getColumnCount() > 0) {
            tablaCatalogo.getColumnModel().getColumn(0).setResizable(false);
            tablaCatalogo.getColumnModel().getColumn(0).setPreferredWidth(9);
            tablaCatalogo.getColumnModel().getColumn(1).setResizable(false);
            tablaCatalogo.getColumnModel().getColumn(1).setPreferredWidth(200);
            tablaCatalogo.getColumnModel().getColumn(2).setResizable(false);
            tablaCatalogo.getColumnModel().getColumn(2).setPreferredWidth(100);
            tablaCatalogo.getColumnModel().getColumn(3).setResizable(false);
            tablaCatalogo.getColumnModel().getColumn(4).setResizable(false);
        }

        Content.add(scrTablaCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 104, 610, 490));

        btnBuscar.setText("Buscar");
        btnBuscar.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        Content.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 80, 40));

        cmbBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Descripcion", "Precio", "Tamaño", "Prioridad" }));
        Content.add(cmbBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });
        Content.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 130, -1));

        lblTitle.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 112, 192));
        lblTitle.setText("Catalogo");
        Content.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        panelLateralDerecho.setBackground(new java.awt.Color(24, 39, 72));
        panelLateralDerecho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblId.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblId.setForeground(new java.awt.Color(255, 255, 255));
        lblId.setText("Id Producto:");
        panelLateralDerecho.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 20));

        txtId.setBackground(new java.awt.Color(255, 255, 255));
        txtId.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtId.setForeground(new java.awt.Color(0, 0, 0));
        txtId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelLateralDerecho.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 120, 20));

        lblNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("producto");
        panelLateralDerecho.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        txtPrecio.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecio.setForeground(new java.awt.Color(0, 0, 0));
        txtPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelLateralDerecho.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 140, 20));

        lblPrecio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecio.setText("Precio:");
        panelLateralDerecho.add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 20));

        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelLateralDerecho.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 140, 20));

        lblSize.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblSize.setForeground(new java.awt.Color(255, 255, 255));
        lblSize.setText("Tamaño:");
        panelLateralDerecho.add(lblSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, 20));

        txtSize.setBackground(new java.awt.Color(255, 255, 255));
        txtSize.setForeground(new java.awt.Color(0, 0, 0));
        txtSize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSize.setVerifyInputWhenFocusTarget(false);
        panelLateralDerecho.add(txtSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 140, 20));

        btnAgregar.setText("Agregar");
        btnAgregar.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });
        panelLateralDerecho.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 100, 40));

        btnEditar.setBackground(new java.awt.Color(51, 204, 0));
        btnEditar.setText("Editar");
        btnEditar.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarMouseClicked(evt);
            }
        });
        panelLateralDerecho.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 100, 40));

        btnEliminar.setText("Eliminar");
        btnEliminar.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        panelLateralDerecho.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 100, 40));

        IconAddProduct.setForeground(new java.awt.Color(255, 255, 255));
        IconAddProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelLateralDerecho.add(IconAddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 190, 150));

        chkbxPrioridad.setBackground(new java.awt.Color(24, 39, 72));
        chkbxPrioridad.setForeground(new java.awt.Color(255, 255, 255));
        chkbxPrioridad.setText("Prioridad Alta");
        panelLateralDerecho.add(chkbxPrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 110, -1));

        Content.add(panelLateralDerecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 260, 500));

        ProductoSelectPanel.setBackground(new java.awt.Color(24, 39, 72));
        ProductoSelectPanel.setForeground(new java.awt.Color(255, 255, 255));

        LblProductoSelect.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        LblProductoSelect.setForeground(new java.awt.Color(255, 255, 255));
        LblProductoSelect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblProductoSelect.setText("Producto");

        javax.swing.GroupLayout ProductoSelectPanelLayout = new javax.swing.GroupLayout(ProductoSelectPanel);
        ProductoSelectPanel.setLayout(ProductoSelectPanelLayout);
        ProductoSelectPanelLayout.setHorizontalGroup(
            ProductoSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblProductoSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        ProductoSelectPanelLayout.setVerticalGroup(
            ProductoSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductoSelectPanelLayout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(LblProductoSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        Content.add(ProductoSelectPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 300, 490));

        BtnReload.setBackground(new java.awt.Color(51, 204, 0));
        BtnReload.setText("Reload");
        BtnReload.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReloadActionPerformed(evt);
            }
        });
        Content.add(BtnReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 100, 40));

        jPanel1.add(Content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1005, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1005, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReloadActionPerformed
        actualizarTabla();
        txtBusqueda.setText("");
        LblProductoSelect.setText("Producto IMG");
    }//GEN-LAST:event_BtnReloadActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        busqueda();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        char teclapress =evt.getKeyChar();

        if(teclapress == KeyEvent.VK_ENTER){
          busqueda();

        }
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        add();
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseClicked
        update();
        limpiar();
    }//GEN-LAST:event_btnEditarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        // TODO add your handling code here:
       delete();
       limpiar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void tablaCatalogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCatalogoMouseClicked
        // TODO add your handling code here:
        int i = tablaCatalogo.getSelectedRow();
        
        if(i != -1)
        {
            try {
                DefaultTableModel model = (DefaultTableModel) tablaCatalogo.getModel();
                String id = (String) model.getValueAt(i, 0);
                
                ProductoDAO pd = new ProductoDAOImpl();
                
                Producto p = pd.get(id);
                
                txtId.setText(id);
                txtDescripcion.setText(p.getNombre());
                txtSize.setText(p.getSize());
                txtPrecio.setText("" + p.getPrecio());
                
                if(p.getPrioridad().equals("alta"))
                {
                    chkbxPrioridad.setSelected(true);
                }else{
                    chkbxPrioridad.setSelected(false);

                }
              
                
            } catch (SQLException ex) {
                Logger.getLogger(CatalogoProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }//GEN-LAST:event_tablaCatalogoMouseClicked

    private void delete()
    {   
        try {
            ProductoDAOImpl pd = new ProductoDAOImpl();
            
            int indexRow = tablaCatalogo.getSelectedRow();
            
            if(indexRow != -1)
            {
                
            DefaultTableModel model = (DefaultTableModel) tablaCatalogo.getModel();
            
            String id = (String) model.getValueAt(indexRow, 0);
            
            pd.delete(id);
            
            actualizarTabla();
            }else
            {
             JOptionPane.showMessageDialog(Content, "Debe seleccionar una fila primero");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CatalogoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
private void update() {
    String id = txtId.getText();

    if (id == null || id.trim().isEmpty()) {
        JOptionPane.showMessageDialog(Content, "Ingrese un ID válido");
        return;
    }

    ProductoDAO pd = new ProductoDAOImpl();

    try {
        Producto p = pd.get(id);
        if (p == null) {
            JOptionPane.showMessageDialog(Content, "El ID no existe");
            return;
        }

        String descripcion = txtDescripcion.getText();
        String size = txtSize.getText();
        String precioText = txtPrecio.getText();

        if (descripcion.isEmpty() || size.isEmpty() || precioText.isEmpty()) {
            JOptionPane.showMessageDialog(Content, "Complete todos los campos");
            return;
        }

        float precio;
        try {
            precio = Float.parseFloat(precioText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un precio válido");
            System.out.println(e);
            return;
        }
        
        if(precio < 0)
             {
                JOptionPane.showMessageDialog(Content, "No puede ingresar un precio negativo");
                 return;
            }

        String prioridad = chkbxPrioridad.isSelected() ? "alta" : "baja";

        p.setNombre(descripcion);
        p.setPrecio(precio);
        p.setSize(size);
        p.setPrioridad(prioridad);

        pd.update(p);

        actualizarTabla();
        
    } catch (SQLException ex) {
        Logger.getLogger(CatalogoProducto.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    private void add()
    {
        ProductoDAO pd = new ProductoDAOImpl();
        
        Producto p;
        
        if(txtId.getText() == null || txtDescripcion.getText() == null || txtPrecio.getText() == null || txtSize.getText() == null)
        {
            JOptionPane.showMessageDialog(Content, "Complete los campos");
        } else if(txtId.getText().equals("") || txtDescripcion.getText().equals("") || txtPrecio.getText().equals("") || txtSize.getText().equals("")){ 
            JOptionPane.showMessageDialog(Content, "Complete los campos");
        } else{
            try{
                    String id = txtId.getText();
                    String descripcion = txtDescripcion.getText();
                    String size = txtSize.getText();
                    Float precio = Float.valueOf(txtPrecio.getText());
                    
                    if(precio < 0)
                    {
                       JOptionPane.showMessageDialog(Content, "No puede ingresar un precio negativo");
                       return;
                    }
                    String prioridad = "";

                    if(chkbxPrioridad.isSelected())
                    {
                        prioridad = "alta";
                    }else
                    {
                        prioridad = "baja";
                    }

                    p = new Producto(id, descripcion, precio, size, prioridad);
                    
                    pd.insert(p);
                    
                    actualizarTabla();
                    limpiar();
                }catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(Content, "Ingrese un precio valido");
                }
                 catch(SQLException e)
                {
                    System.err.println(e);
                }
            }
        
        
    }
    
    private void actualizarTabla()
    {
        try {
            
         DefaultTableModel model = (DefaultTableModel) tablaCatalogo.getModel();
         Updater.Updater.actualizarTabla("producto", model);   
         
        } catch (SQLException ex) {
            Logger.getLogger(CatalogoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
      private void limpiar()
    {
        txtDescripcion.setText("");
        txtId.setText("");
        txtPrecio.setText("");
        txtSize.setText("");
        chkbxPrioridad.setSelected(false);
    }
      
       private void busqueda() {
     try {
         DefaultTableModel modelo = new DefaultTableModel() {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false; // Evitar que las celdas sean editables, lo hice por code no deja el properies
             }
         };

         String seleccion = (String) cmbBusqueda.getSelectedItem(); // Obtener la opción seleccionada del combo box

         String query = ""; // Inicializar la consulta
         switch (seleccion) {
             case "ID" -> {
                 String id = txtBusqueda.getText();
                 query = "select * from Producto"
                         + " WHERE id = '" + id + "'";
             }
             case "Descripcion" -> {
                 String descripcion = txtBusqueda.getText();
                 query = "select * from Producto"
                         + " where descripcion = '" + descripcion + "'";
             }
             case "Precio" -> {
                 String precioU = txtBusqueda.getText();
                 query = "select * from Producto"
                         + " where precioU = '" + precioU + "'";
             }
             case "Tamaño" -> {
                 String size = txtBusqueda.getText();
                 query = "select * from Producto"
                         + " where size = '" + size + "'";
             } case "Prioridad" -> {
                 String prioridad = txtBusqueda.getText();
                 query = "select * from Producto"
                         + " where prioridad = '" + prioridad + "'";
             }
             default -> {
             }
         }

         try (Connection connection = Conexion.conectar();
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

         tablaCatalogo.setModel(modelo);
     } catch (SQLException ex) {
         Logger.getLogger(CatalogoProducto.class.getName()).log(Level.SEVERE, null, ex);
     }
 }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle BtnReload;
    private javax.swing.JPanel Content;
    private javax.swing.JLabel IconAddProduct;
    private javax.swing.JLabel LblProductoSelect;
    private javax.swing.JPanel PanelBarBlue;
    private javax.swing.JPanel ProductoSelectPanel;
    private rojerusan.RSMaterialButtonRectangle btnAgregar;
    private rojerusan.RSMaterialButtonRectangle btnBuscar;
    private rojerusan.RSMaterialButtonRectangle btnEditar;
    private rojerusan.RSMaterialButtonRectangle btnEliminar;
    private javax.swing.JCheckBox chkbxPrioridad;
    private javax.swing.JComboBox<String> cmbBusqueda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelLateralDerecho;
    private javax.swing.JScrollPane scrTablaCatalogo;
    private javax.swing.JTable tablaCatalogo;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSize;
    // End of variables declaration//GEN-END:variables
}
