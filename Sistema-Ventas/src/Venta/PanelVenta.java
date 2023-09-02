
package Venta;

import Connect.Conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import DAO.ProductoDAO;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import DAOImpl.ProductoDAOImpl;
import DTO.Producto;
import Utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

    public class PanelVenta extends javax.swing.JPanel {
        Facturacion f = new Facturacion();

    public PanelVenta() {
        initComponents();
        Utils.setScaledImage(IconLimpiarAll, "escoba.png", 25, 25);
 
    }
    
    private void checkId(){
        String idProducto = txtIDProducto.getText();
        // String consulta = "SELECT descripcion, size, precioU FROM producto WHERE id = ?";
        ProductoDAO pdo = new ProductoDAOImpl();
        
        try {
            
            Producto p = pdo.get(idProducto);
            
            if(p != null){
            LblNombreProducto.setText(p.getNombre());
            LblSizeProducto.setText(p.getSize());
            LblPrecioProducto.setText("" + p.getPrecio());
            } else{
                JOptionPane.showMessageDialog(this, "Producto no encontrado");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PanelVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void addToTable() {
        String idProducto = txtIDProducto.getText();
        String size = LblSizeProducto.getText();
        String nombre = LblNombreProducto.getText();
        String cantidad = txtCantidadProducto.getText();

        if (!idProducto.isEmpty() && !cantidad.isEmpty() && !nombre.isEmpty()) {
            try {
                int cant = Integer.parseInt(cantidad); // Parse cantidad as an integer

                float precio = Float.parseFloat(LblPrecioProducto.getText());
                float total = precio * cant;
                DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();

                Object[] fila = { idProducto, nombre, size, precio, cantidad, total };
                model.addRow(fila);
                calcularTotal();

                txtIDProducto.setText("");
                LblNombreProducto.setText("");
                LblSizeProducto.setText("");
                LblPrecioProducto.setText("");
                txtCantidadProducto.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Rellene todos los campos manito.");
        }
    }

    private void deleteFila(){

    int selectedRow = tablaProductos.getSelectedRow();

    if (selectedRow >= 0) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar este producto?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
            model.removeRow(selectedRow);
            calcularTotal();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar.");
    }    
}
    private void calcularTotal() {
        
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        double total = 0.0;

        for (int i = 0; i < model.getRowCount(); i++) {
            float totalFloat = (float) model.getValueAt(i, 5);
            total += totalFloat;
        }

        LblMontoPagar.setText(String.format("%.2f", total).replace(",", "."));

}
    private void confirmarFacturacion() {
        
        Facturacion f = new Facturacion();
        DeudaClientes dc = new DeudaClientes();

        String devueltaString = LblDevuelta.getText();
        float montoRecibido = Float.parseFloat(txtMontoRecibido.getText());
                
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre is NULL";

        try {
            Connection conectar = Conexion.conectar();
            PreparedStatement pst = conectar.prepareStatement(checkNullDate);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {              
                         
            if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay nada para facturar.");            
            
            } else if(devueltaString.isEmpty()){            
            JOptionPane.showMessageDialog(this, "Introduzca un monto recibido. Si ya lo hizo, presione ENTER desde la casilla MONTO RECIBIDO");            
            
            } else {
            
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de iniciar un proceso de facturación?", "Confirmación de facturación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                float devuelta = Float.parseFloat(LblDevuelta.getText());
                float totalV = Float.parseFloat(LblMontoPagar.getText());

                if (devuelta <= -0.01) {
                    
                    for (int i = 0; i < model.getRowCount(); i++) {
                        String ID = (String) model.getValueAt(i, 0);
                        String nombre = (String) model.getValueAt(i, 1);
                        String size = (String) model.getValueAt(i, 2);
                        float precio = (float) model.getValueAt(i, 3);
                        
                        String cant = (String) model.getValueAt(i, 4);
                        int cantidad = Integer.parseInt(cant);             
                        
                        float total = (float) model.getValueAt(i, 5);

                      //  dc.addDeuda(ID, size, nombre, cantidad, precio, total, devuelta, totalV, montoRecibido);
                    }
                        dc.setLocationRelativeTo(null);
                        dc.setSize(600, 520);
                        dc.setVisible(true);
                    
                } else if (devuelta >= 0) {
                    
                    for (int i = 0; i < model.getRowCount(); i++) {
                        String ID = (String) model.getValueAt(i, 0);
                        String nombre = (String) model.getValueAt(i, 1);
                        float precio = (float) model.getValueAt(i, 3);
                        
                        String cant = (String) model.getValueAt(i, 4);
                        int cantidad = Integer.parseInt(cant);
                        
                        float total = (float) model.getValueAt(i, 5);

                        f.addProducto(ID, nombre, cantidad, precio, total);
                    }
                        f.setLocationRelativeTo(null);
                        f.setSize(416, 600);
                        f.setVisible(true);
                        
                        if(f.isFacturado()){
                                                         
                            txtIDProducto.setText("");
                            LblNombreProducto.setText("");
                            LblSizeProducto.setText("");
                            LblPrecioProducto.setText("");
                            txtCantidadProducto.setText("");
                            LblMontoPagar.setText("0");
                            txtMontoRecibido.setText("");
                            LblDevuelta.setText("0");   
                            model.setRowCount(0);                                
                        
                        }
                        
                } else {
                    
                    JOptionPane.showMessageDialog(this, "Facturación cancelada.");
                    
                }
            }
        }       
                
            } else if(!rs.next()){
            
                JOptionPane.showMessageDialog(this, "No existe ninguna sesión de caja abierta.","Error.",JOptionPane.ERROR_MESSAGE);
            
            }
            
            Conexion.desconectar(conectar);
        } catch (SQLException ex) {
            System.out.print(ex);
             Logger.getLogger(PanelVenta.class.getName()).log(Level.SEVERE, null, ex);
        }  
                
    }
    private void limpiador(){
        
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel(); 
        txtIDProducto.setText("");
        LblNombreProducto.setText("");
        LblSizeProducto.setText("");
        LblPrecioProducto.setText("");
        txtCantidadProducto.setText("");
        LblMontoPagar.setText("0");
        txtMontoRecibido.setText("0");
        LblDevuelta.setText("0");   
        model.setRowCount(0);  
    
    }
    private void cotizarVenta(){
        
        Cotizacion c = new Cotizacion();
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        String devuelta = LblDevuelta.getText();
        
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de iniciar un proceso de cotización?", "Confirmación de cotización", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            
            if(model.getRowCount() == 0){
                    JOptionPane.showMessageDialog(this, "No hay nada para cotizar.");     
            }else if (!devuelta.isEmpty()) {            
                    for (int i = 0; i < model.getRowCount(); i++) {
                        String ID = (String) model.getValueAt(i, 0);
                        String nombre = (String) model.getValueAt(i, 1);
                        float precio = (float) model.getValueAt(i, 3);
                        
                        String cant = (String) model.getValueAt(i, 4);
                        int cantidad = Integer.parseInt(cant);
                        
                        float total = (float) model.getValueAt(i, 5);

                        c.addProducto(ID, nombre, cantidad, precio, total);
                    }
                    c.setLocationRelativeTo(null);
                    c.setSize(416, 600);
                    c.setVisible(true);
                } 
            }   else {
                    JOptionPane.showMessageDialog(this, "Cotización cancelada.");
                }
    
    }
    private void actualizarTxt(){
    float received = Float.parseFloat(txtMontoRecibido.getText());
    try {
        if (received < 0) {
            // Mostrar un mensaje de error o realizar alguna acción apropiada
            System.out.println("El monto recibido no puede ser negativo");
        } else {
            try {
                float total = Float.parseFloat(LblMontoPagar.getText());
                float devuelta = received - total;
                LblDevuelta.setText(String.format("%.2f", devuelta).replace(",", "."));

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    } catch (NumberFormatException ex) {
        throw ex; // Rethrow the exception to be caught in txtMontoRecibidoCaretUpdate
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContentPanelVenta = new javax.swing.JPanel();
        ScrollPanelVenta = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        PanelContenido = new javax.swing.JPanel();
        PanelDatos = new javax.swing.JPanel();
        LblIDProducto = new javax.swing.JLabel();
        LblNombre = new javax.swing.JLabel();
        LblSize = new javax.swing.JLabel();
        LblPrecio = new javax.swing.JLabel();
        LblCantidad = new javax.swing.JLabel();
        btnAgregarPanelVenta = new rojerusan.RSMaterialButtonRectangle();
        btnEliminarPanelVenta = new rojerusan.RSMaterialButtonRectangle();
        SeparadorProducto = new javax.swing.JSeparator();
        SeparadorNombre = new javax.swing.JSeparator();
        SeparadorSize = new javax.swing.JSeparator();
        SeparadorPrecio = new javax.swing.JSeparator();
        SeparadorCantidad = new javax.swing.JSeparator();
        txtIDProducto = new javax.swing.JTextField();
        txtCantidadProducto = new javax.swing.JTextField();
        PanelTotalPagar = new javax.swing.JPanel();
        LblTotalPagar = new javax.swing.JLabel();
        LblMontoPagar = new javax.swing.JLabel();
        LblDetalle = new javax.swing.JLabel();
        SeparadorDetalle = new javax.swing.JSeparator();
        LblSizeProducto = new javax.swing.JLabel();
        LblNombreProducto = new javax.swing.JLabel();
        LblPrecioProducto = new javax.swing.JLabel();
        IconLimpiarAll = new javax.swing.JLabel();
        BtnLimpiarAll = new rojerusan.RSMaterialButtonRectangle();
        PanelMontoPayBack = new javax.swing.JPanel();
        LblDevueltaMonto = new javax.swing.JLabel();
        LblMontoPagado = new javax.swing.JLabel();
        txtMontoRecibido = new javax.swing.JTextField();
        LblDevuelta = new javax.swing.JLabel();
        BtnFacturar = new org.edisoncor.gui.button.ButtonNice();
        BtnCotizar = new org.edisoncor.gui.button.ButtonNice();

        ContentPanelVenta.setPreferredSize(new java.awt.Dimension(1005, 660));
        ContentPanelVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Tamaño", "Precio", "Cantidad", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class
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
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        ScrollPanelVenta.setViewportView(tablaProductos);
        if (tablaProductos.getColumnModel().getColumnCount() > 0) {
            tablaProductos.getColumnModel().getColumn(0).setResizable(false);
            tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(15);
            tablaProductos.getColumnModel().getColumn(1).setResizable(false);
            tablaProductos.getColumnModel().getColumn(2).setResizable(false);
            tablaProductos.getColumnModel().getColumn(3).setResizable(false);
            tablaProductos.getColumnModel().getColumn(4).setResizable(false);
            tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(20);
            tablaProductos.getColumnModel().getColumn(5).setResizable(false);
        }

        ContentPanelVenta.add(ScrollPanelVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 660));

        PanelContenido.setBackground(new java.awt.Color(255, 255, 255));
        PanelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelDatos.setBackground(new java.awt.Color(24, 39, 72));
        PanelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblIDProducto.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblIDProducto.setForeground(new java.awt.Color(255, 255, 255));
        LblIDProducto.setText("ID Prod:");
        PanelDatos.add(LblIDProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, 20));

        LblNombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblNombre.setForeground(new java.awt.Color(255, 255, 255));
        LblNombre.setText("Nombre:");
        PanelDatos.add(LblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 82, -1, -1));

        LblSize.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblSize.setForeground(new java.awt.Color(255, 255, 255));
        LblSize.setText("Tamaño:");
        PanelDatos.add(LblSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 112, -1, 20));

        LblPrecio.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblPrecio.setForeground(new java.awt.Color(255, 255, 255));
        LblPrecio.setText("Precio:");
        PanelDatos.add(LblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 142, -1, 20));

        LblCantidad.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblCantidad.setForeground(new java.awt.Color(255, 255, 255));
        LblCantidad.setText("Cantidad:");
        PanelDatos.add(LblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 170, -1, 20));

        btnAgregarPanelVenta.setBackground(new java.awt.Color(132, 178, 80));
        btnAgregarPanelVenta.setText("AGREGAR");
        btnAgregarPanelVenta.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btnAgregarPanelVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPanelVentaActionPerformed(evt);
            }
        });
        PanelDatos.add(btnAgregarPanelVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 110, 40));

        btnEliminarPanelVenta.setBackground(new java.awt.Color(204, 0, 0));
        btnEliminarPanelVenta.setText("Eliminar");
        btnEliminarPanelVenta.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btnEliminarPanelVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarPanelVentaMouseClicked(evt);
            }
        });
        PanelDatos.add(btnEliminarPanelVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 110, 40));

        SeparadorProducto.setBackground(new java.awt.Color(255, 255, 255));
        SeparadorProducto.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorProducto.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        SeparadorProducto.setMinimumSize(new java.awt.Dimension(0, 2));
        SeparadorProducto.setOpaque(true);
        SeparadorProducto.setPreferredSize(new java.awt.Dimension(0, 2));
        PanelDatos.add(SeparadorProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 68, 120, -1));

        SeparadorNombre.setBackground(new java.awt.Color(255, 255, 255));
        SeparadorNombre.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorNombre.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        SeparadorNombre.setMinimumSize(new java.awt.Dimension(0, 2));
        SeparadorNombre.setOpaque(true);
        SeparadorNombre.setPreferredSize(new java.awt.Dimension(0, 2));
        PanelDatos.add(SeparadorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 98, 120, -1));

        SeparadorSize.setBackground(new java.awt.Color(255, 255, 255));
        SeparadorSize.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorSize.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        SeparadorSize.setMinimumSize(new java.awt.Dimension(0, 2));
        SeparadorSize.setOpaque(true);
        SeparadorSize.setPreferredSize(new java.awt.Dimension(0, 2));
        PanelDatos.add(SeparadorSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 130, 120, -1));

        SeparadorPrecio.setBackground(new java.awt.Color(255, 255, 255));
        SeparadorPrecio.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorPrecio.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        SeparadorPrecio.setMinimumSize(new java.awt.Dimension(0, 2));
        SeparadorPrecio.setOpaque(true);
        SeparadorPrecio.setPreferredSize(new java.awt.Dimension(0, 2));
        PanelDatos.add(SeparadorPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 160, 120, 2));

        SeparadorCantidad.setBackground(new java.awt.Color(255, 255, 255));
        SeparadorCantidad.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorCantidad.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        SeparadorCantidad.setOpaque(true);
        SeparadorCantidad.setPreferredSize(new java.awt.Dimension(0, 2));
        PanelDatos.add(SeparadorCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 190, 120, -1));

        txtIDProducto.setBackground(new java.awt.Color(24, 39, 72));
        txtIDProducto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtIDProducto.setForeground(new java.awt.Color(255, 255, 255));
        txtIDProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDProducto.setBorder(null);
        txtIDProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDProductoKeyTyped(evt);
            }
        });
        PanelDatos.add(txtIDProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 50, 120, 20));

        txtCantidadProducto.setBackground(new java.awt.Color(24, 39, 72));
        txtCantidadProducto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCantidadProducto.setForeground(new java.awt.Color(255, 255, 255));
        txtCantidadProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidadProducto.setBorder(null);
        PanelDatos.add(txtCantidadProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 172, 120, 20));

        PanelTotalPagar.setBackground(new java.awt.Color(255, 255, 255));
        PanelTotalPagar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblTotalPagar.setFont(new java.awt.Font("Roboto Black", 1, 16)); // NOI18N
        LblTotalPagar.setForeground(new java.awt.Color(0, 0, 0));
        LblTotalPagar.setText("TOTAL A PAGAR:");
        PanelTotalPagar.add(LblTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 138, -1));

        LblMontoPagar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblMontoPagar.setForeground(new java.awt.Color(0, 204, 51));
        LblMontoPagar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblMontoPagar.setText("0");
        PanelTotalPagar.add(LblMontoPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 30, 90, 20));

        PanelDatos.add(PanelTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 300, 160, 70));

        LblDetalle.setFont(new java.awt.Font("Roboto Black", 1, 20)); // NOI18N
        LblDetalle.setForeground(new java.awt.Color(255, 255, 255));
        LblDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDetalle.setText("Detalles");
        PanelDatos.add(LblDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 10, 100, -1));

        SeparadorDetalle.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorDetalle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SeparadorDetalle.setMinimumSize(new java.awt.Dimension(0, 3));
        SeparadorDetalle.setOpaque(true);
        PanelDatos.add(SeparadorDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 30, 100, -1));

        LblSizeProducto.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblSizeProducto.setForeground(new java.awt.Color(255, 255, 255));
        LblSizeProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanelDatos.add(LblSizeProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 112, 120, 20));
        LblSizeProducto.getAccessibleContext().setAccessibleName("LblNombre");

        LblNombreProducto.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblNombreProducto.setForeground(new java.awt.Color(255, 255, 255));
        LblNombreProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanelDatos.add(LblNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 81, 120, 20));

        LblPrecioProducto.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblPrecioProducto.setForeground(new java.awt.Color(255, 255, 255));
        LblPrecioProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanelDatos.add(LblPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 142, 120, 20));

        IconLimpiarAll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        IconLimpiarAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IconLimpiarAllMouseClicked(evt);
            }
        });
        PanelDatos.add(IconLimpiarAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 3, 30, 30));

        BtnLimpiarAll.setBackground(new java.awt.Color(242, 239, 239));
        BtnLimpiarAll.setFont(new java.awt.Font("Roboto Light", 1, 10)); // NOI18N
        BtnLimpiarAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarAllActionPerformed(evt);
            }
        });
        PanelDatos.add(BtnLimpiarAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        PanelContenido.add(PanelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 0, 290, 400));

        PanelMontoPayBack.setBackground(new java.awt.Color(24, 39, 72));
        PanelMontoPayBack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblDevueltaMonto.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        LblDevueltaMonto.setForeground(new java.awt.Color(255, 255, 255));
        LblDevueltaMonto.setText("DEVUELTA");
        PanelMontoPayBack.add(LblDevueltaMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 70, -1, 20));

        LblMontoPagado.setFont(new java.awt.Font("Roboto", 1, 19)); // NOI18N
        LblMontoPagado.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoPagado.setText("MONTO RECIBIDO:");
        PanelMontoPayBack.add(LblMontoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, -1, 20));

        txtMontoRecibido.setBackground(new java.awt.Color(255, 255, 255));
        txtMontoRecibido.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtMontoRecibido.setForeground(new java.awt.Color(0, 0, 0));
        txtMontoRecibido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMontoRecibido.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMontoRecibidoCaretUpdate(evt);
            }
        });
        PanelMontoPayBack.add(txtMontoRecibido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 90, -1));

        LblDevuelta.setBackground(new java.awt.Color(255, 255, 255));
        LblDevuelta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblDevuelta.setForeground(new java.awt.Color(0, 0, 0));
        LblDevuelta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDevuelta.setText("0");
        LblDevuelta.setOpaque(true);
        PanelMontoPayBack.add(LblDevuelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 90, 23));

        PanelContenido.add(PanelMontoPayBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 420, 190, 130));

        BtnFacturar.setBackground(new java.awt.Color(24, 39, 72));
        BtnFacturar.setBorder(null);
        BtnFacturar.setForeground(new java.awt.Color(255, 255, 255));
        BtnFacturar.setText("FACTURAR");
        BtnFacturar.setBorderPainted(false);
        BtnFacturar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        BtnFacturar.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.RECT);
        BtnFacturar.setSegundoColor(new java.awt.Color(69, 98, 126));
        BtnFacturar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnFacturarMouseClicked(evt);
            }
        });
        BtnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFacturarActionPerformed(evt);
            }
        });
        PanelContenido.add(BtnFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 570, 110, 40));

        BtnCotizar.setBackground(new java.awt.Color(24, 39, 72));
        BtnCotizar.setBorder(null);
        BtnCotizar.setForeground(new java.awt.Color(255, 255, 255));
        BtnCotizar.setText("COTIZAR");
        BtnCotizar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        BtnCotizar.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.RECT);
        BtnCotizar.setSegundoColor(new java.awt.Color(69, 98, 126));
        BtnCotizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCotizarActionPerformed(evt);
            }
        });
        PanelContenido.add(BtnCotizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 110, 40));

        ContentPanelVenta.add(PanelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 290, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ContentPanelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ContentPanelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarPanelVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarPanelVentaMouseClicked
        // TODO add your handling code here:
        deleteFila();
    }//GEN-LAST:event_btnEliminarPanelVentaMouseClicked
    private void BtnFacturarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnFacturarMouseClicked
        confirmarFacturacion();
    }//GEN-LAST:event_BtnFacturarMouseClicked
     
    private void BtnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFacturarActionPerformed
        confirmarFacturacion();        
    }//GEN-LAST:event_BtnFacturarActionPerformed
    private void txtIDProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDProductoKeyTyped
        // TODO add your handling code here:
        char t = evt.getKeyChar();
        if(t == KeyEvent.VK_ENTER){ 
        checkId();
        }       

    }//GEN-LAST:event_txtIDProductoKeyTyped
    private void btnAgregarPanelVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPanelVentaActionPerformed
        addToTable();
    }//GEN-LAST:event_btnAgregarPanelVentaActionPerformed
    private void BtnCotizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCotizarActionPerformed
        cotizarVenta();
    }//GEN-LAST:event_BtnCotizarActionPerformed
    private void BtnLimpiarAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarAllActionPerformed
        limpiador();
    }//GEN-LAST:event_BtnLimpiarAllActionPerformed
    private void IconLimpiarAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IconLimpiarAllMouseClicked
        limpiador(); 
    }//GEN-LAST:event_IconLimpiarAllMouseClicked
    private void txtMontoRecibidoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMontoRecibidoCaretUpdate

        actualizarTxt();

    }//GEN-LAST:event_txtMontoRecibidoCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonNice BtnCotizar;
    private org.edisoncor.gui.button.ButtonNice BtnFacturar;
    private rojerusan.RSMaterialButtonRectangle BtnLimpiarAll;
    private javax.swing.JPanel ContentPanelVenta;
    private javax.swing.JLabel IconLimpiarAll;
    private javax.swing.JLabel LblCantidad;
    private javax.swing.JLabel LblDetalle;
    private static javax.swing.JLabel LblDevuelta;
    private javax.swing.JLabel LblDevueltaMonto;
    private javax.swing.JLabel LblIDProducto;
    private javax.swing.JLabel LblMontoPagado;
    private static javax.swing.JLabel LblMontoPagar;
    private javax.swing.JLabel LblNombre;
    private static javax.swing.JLabel LblNombreProducto;
    private javax.swing.JLabel LblPrecio;
    private static javax.swing.JLabel LblPrecioProducto;
    private javax.swing.JLabel LblSize;
    private static javax.swing.JLabel LblSizeProducto;
    private javax.swing.JLabel LblTotalPagar;
    private javax.swing.JPanel PanelContenido;
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JPanel PanelMontoPayBack;
    private javax.swing.JPanel PanelTotalPagar;
    private javax.swing.JScrollPane ScrollPanelVenta;
    private javax.swing.JSeparator SeparadorCantidad;
    private javax.swing.JSeparator SeparadorDetalle;
    private javax.swing.JSeparator SeparadorNombre;
    private javax.swing.JSeparator SeparadorPrecio;
    private javax.swing.JSeparator SeparadorProducto;
    private javax.swing.JSeparator SeparadorSize;
    private rojerusan.RSMaterialButtonRectangle btnAgregarPanelVenta;
    private rojerusan.RSMaterialButtonRectangle btnEliminarPanelVenta;
    private javax.swing.JTable tablaProductos;
    private static javax.swing.JTextField txtCantidadProducto;
    private static javax.swing.JTextField txtIDProducto;
    private static javax.swing.JTextField txtMontoRecibido;
    // End of variables declaration//GEN-END:variables
}
