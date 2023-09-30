package appStructure.application.form.other.Producto;

import Database.Connect.ConexionSingleton;
import Database.DAO.ProductoDAO;
import Database.DAO.ProveedorDAO;
import Database.DAOImpl.ProductoDAOImpl;
import Database.DAOImpl.ProveedorDAOImpl;
import Database.DTO.Producto;
import Database.DTO.Proveedor;
import Utils.Tools.UniqueIDGenerator2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samil 
 */
public class FormRInventario extends javax.swing.JPanel {

    LocalDateTime fecha;
    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormRInventario() {
        
        initComponents();
        init();
    
    }
    
    private void init(){
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       fecha = LocalDateTime.now();
       
       generarIdCompra();
       
       lblFecha.setText("Fecha: " + formatter.format(fecha));
       iconInventario.setSvgImage("appStructure/icon/svg/inventory.svg", 220, 220);
    }
    
    private void actualizarTxt()
    {
            try{
            int cantidad = Integer.parseInt(txtCantidadEntrada.getText());
            float precioC = Float.parseFloat(txtPrecioCompra.getText());
            if(cantidad > 0 && precioC > 0)
            {
                
             String total = String.format("%.2f", (cantidad * precioC));
            txtTotal.setText(total.replace(",", "."));
            }else
            {
                txtTotal.setText("Monto invalido");
            }
            
        }catch(NumberFormatException e)
        {
            System.out.println(e);
        }
    }
    
    private void checkIdProducto(){
        String idProducto = txtIdProducto.getText();
        // String consulta = "SELECT descripcion, size, precioU FROM producto WHERE id = ?";
        ProductoDAO pdo = new ProductoDAOImpl();
        
        try {
            
            Producto p = pdo.get(idProducto);
            if(p != null)
            {
                txtDescripcion.setText(p.getNombre());
                txtSize.setText(p.getSize());
            }else
            {
            txtDescripcion.setText("Prod no encontrado");
            txtSize.setText("");
            }


        } catch (SQLException ex) {
            System.out.println(ex);
        }
     }
     
    private void checkIdProveedor(){
        String idProveedor = txtIdProveedor.getText();
          ProveedorDAO pdo = new ProveedorDAOImpl();
        
        try {
            
            Proveedor pr = pdo.get(idProveedor);
            if(pr != null)
            {
                txtProveedor.setText(pr.getNombre());
            }else
            {
                txtProveedor.setText("");
            }


        } catch (SQLException ex) {
           
            System.out.println(ex);
        }
     }
      
    private boolean agregar()
    {
        Boolean agregado = false;
        String idProd = txtIdProducto.getText();
       String descripcion = txtDescripcion.getText();
       String size = txtSize.getText();
       int cantidad = 0;
       float precioCompra = 0;
       String idProveedor = txtIdProveedor.getText();
       String proveedor = txtProveedor.getText();
        System.out.println(idExiste(idProd));
       if(idExiste(idProd) == false)
       {
                try{
                cantidad = Integer.parseInt(txtCantidadEntrada.getText());
                precioCompra = Float.parseFloat(txtPrecioCompra.getText());
                
                if(cantidad < 0 || precioCompra < 0)
                {
                    JOptionPane.showMessageDialog(null, "No puede añadir negativos en los campos de cantidad y precio");

                }else{
                    if(!idProd.isEmpty() && !idProveedor.isEmpty() && cantidad != 0 && precioCompra != 0)
                     {

                        DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();

                        Object[] row = {idProd,descripcion,size,cantidad,precioCompra,idProveedor,proveedor};

                        model.addRow(row);
                                 Float totalActual = Float.valueOf(lblTotal.getText());

                                String total = String.format("%.2f", (cantidad * precioCompra + totalActual));

                         lblTotal.setText(total.replace(",", "."));
                         
                         agregado = true;

                     } else{
                         JOptionPane.showMessageDialog(null, "Complete los campos");
                         agregado = false;

                    }
                }

           }catch(Exception e)
           {
               JOptionPane.showMessageDialog(null, "Ingrese correctamente los campos");
               System.out.println(e);
                 agregado = false;
           }
       }else{
             JOptionPane.showMessageDialog(null, "Ya tiene este producto en la lista");
              agregado = false;

       }
      
       return  agregado;
    }
      
    private boolean idExiste(String id)
      {
          boolean existe = false;
          for(int i = 0; i < tableProductos.getModel().getRowCount(); i++)
          {
              String idTabla = (String) tableProductos.getModel().getValueAt(i, 0);
              if(id.equals(idTabla))
              {
                  existe = true;
                  break;
                  
              }
              else{
                  existe = false;
              }
          }
          return existe;
      }
      
    private void limpiar()
      {
          txtCantidadEntrada.setText("");
          txtPrecioCompra.setText("");
          txtDescripcion.setText("");
          txtIdProducto.setText("");
          txtSize.setText("");
          txtTotal.setText("");
                                        
      }
      
    private void limpiarPestaña()
      {
          lblTotal.setText("0.00");
          txtCantidadEntrada.setText("");
          txtPrecioCompra.setText("");
          txtDescripcion.setText("");
          txtIdProducto.setText("");
          txtSize.setText("");
          txtTotal.setText("");
          txtIdProveedor.setText("");
          txtProveedor.setText("");
          txtIdProveedor.setEnabled(true);
          
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime fecha = LocalDateTime.now();
       
            lblFecha.setText("Fecha: " + formatter.format(fecha));
          
          DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();
          
            int rowCount = model.getRowCount();
           for (int row = rowCount - 1; row >= 0; row--) {
               model.removeRow(row);
           }

      }
      
    private boolean comprobarProductoProveedor(){
        boolean existe = false;

        String idProd = txtIdProducto.getText();
          
        if(!idProd.isEmpty())
          {
                try {
                    String query = "select idProducto, idProveedor from detalleProducto where idProducto = ? and idProveedor = ?";
                    Connection cn = c.getConexion();
                    PreparedStatement stm = cn.prepareStatement(query);
                    stm.setString(1, txtIdProducto.getText());
                    stm.setString(2, txtIdProveedor.getText());

                   ResultSet rs = stm.executeQuery();

                   if(!rs.next())
                   {
                       int option = JOptionPane.showConfirmDialog(null, "Quiere agregar este producto con este proveedor?");

                       if(option == JOptionPane.YES_OPTION)
                       {
                            String query2 = "insert into detalleProducto(idProducto,idProveedor) values (?,?)";
                            PreparedStatement stm2 = cn.prepareStatement(query2);
                            stm2.setString(1, txtIdProducto.getText());
                            stm2.setString(2, txtIdProveedor.getText());

                            stm2.execute();

                            existe = true;
                       }else{
                           JOptionPane.showMessageDialog(null, "Se cancelo el registro");
                           existe = false;
                       }
                   }else
                   {
                       existe = true;

                   }

                } catch (SQLException ex) {
                    Logger.getLogger(FormRInventario.class.getName()).log(Level.SEVERE, null, ex);
                }

          }
           return existe;

      }
      
    private void generarCompra()
    {
      
        String compraQuery = "INSERT INTO compra (id, idProveedor, total, fecha) VALUES (?,?,?,?)";

        String detalleCompraQuery = "INSERT INTO detallecompra (idCompra, idProducto, cantidadP,precioCompra) VALUES (?,?,?,?)";
        try {  
            Connection cn = c.getConexion();
            
            
            try {
                c.desactivarAutoCommit();
                
                PreparedStatement pst = cn.prepareStatement(compraQuery);
                
                pst.setString(1, lblIdCompraG.getText());
                pst.setString(2, txtIdProveedor.getText());
                pst.setFloat(3, Float.parseFloat(lblTotal.getText()));
                pst.setTimestamp(4, Timestamp.valueOf(fecha));
                pst.executeUpdate();
                
                DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();
                for (int row = 0; row < model.getRowCount(); row++) {
                    String productoID = (String) model.getValueAt(row, 0);
                    int cantidad = (int) model.getValueAt(row, 3);
                    float precioCompra = (float) model.getValueAt(row, 4);
                    
                    PreparedStatement pst3 = cn.prepareStatement(detalleCompraQuery);
                    pst3.setString(1, lblIdCompraG.getText());
                    pst3.setString(2, productoID);
                    pst3.setInt(3, cantidad);
                    pst3.setFloat(4, precioCompra);
                    pst3.executeUpdate();
                }
 
            
                c.commit();
            } catch (SQLException ex) {
                Logger.getLogger(FormRInventario.class.getName()).log(Level.SEVERE, null, ex);
                
                c.rollback();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormRInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private void generarDeuda() {
    String deudaQuery = "INSERT INTO cuentaporpagar (idCompra,idProveedor, deuda,totalFaltante, fecha) VALUES (?,?,?,?,?)";

    try (Connection cn = c.getConexion();
        PreparedStatement pst = cn.prepareStatement(deudaQuery)) {
        
        cn.setAutoCommit(false);

        pst.setString(1, lblIdCompraG.getText());
        pst.setString(2, txtIdProveedor.getText());
        pst.setFloat(3, Float.parseFloat(lblTotal.getText()));
        pst.setFloat(4, Float.parseFloat(lblTotal.getText()));
        pst.setTimestamp(5, Timestamp.valueOf(fecha));
        pst.executeUpdate();

        
        cn.commit();
    } catch (SQLException ex) {
        Logger.getLogger(FormRInventario.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex);
    }
    }

    private void generarGasto() {
        String deudaQuery = "INSERT INTO gastos (concepto, monto, fecha) VALUES (?,?,?)";
        String concepto = "Compra a " + txtProveedor.getText();

        try (Connection cn = c.getConexion();
             PreparedStatement pst = cn.prepareStatement(deudaQuery)) {
            // Desactivar el autocommit
            cn.setAutoCommit(false);

            pst.setString(1, concepto);
            pst.setFloat(2, Float.parseFloat(lblTotal.getText()));
            pst.setTimestamp(3, Timestamp.valueOf(fecha));

            pst.executeUpdate();

            // Realizar el commit explícito
            cn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(FormRInventario.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    
    private void generarIdCompra()
    {
       String idCompra = UniqueIDGenerator2.generateUniqueID();
        lblIdCompraG.setText(idCompra);
   }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Content = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        panelLateralDerecho = new javax.swing.JPanel();
        sp1 = new javax.swing.JSeparator();
        sp2 = new javax.swing.JSeparator();
        sp3 = new javax.swing.JSeparator();
        sp4 = new javax.swing.JSeparator();
        sp5 = new javax.swing.JSeparator();
        sp6 = new javax.swing.JSeparator();
        sp7 = new javax.swing.JSeparator();
        sp8 = new javax.swing.JSeparator();
        lblId1 = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        lblNombre1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblPrecio1 = new javax.swing.JLabel();
        lblSize1 = new javax.swing.JLabel();
        lblPrecio2 = new javax.swing.JLabel();
        lblSize2 = new javax.swing.JLabel();
        lblSize3 = new javax.swing.JLabel();
        lblSize4 = new javax.swing.JLabel();
        btnRellenar = new Utils.ButtonRounded();
        btnGuardar = new Utils.ButtonRounded();
        btnEliminar = new Utils.ButtonRounded();
        txtSize = new javax.swing.JTextField();
        PanelTitle2 = new javax.swing.JPanel();
        LblDetalle1 = new javax.swing.JLabel();
        iconInventario = new Utils.SVGImage2();
        txtCantidadEntrada = new javax.swing.JTextField();
        txtPrecioCompra = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        txtIdProveedor = new javax.swing.JTextField();
        txtProveedor = new javax.swing.JTextField();
        LblTitle = new javax.swing.JLabel();
        PanelTitle = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        lblIdCompra = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblPrecioTotalCompra = new javax.swing.JLabel();
        lblIdCompraG = new javax.swing.JLabel();

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "Descripción", "Tamaño", "Cantidad", "Precio de compra", "ID del Proveedor", "Proveedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProductos.setToolTipText("");
        tableProductos.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(tableProductos);
        if (tableProductos.getColumnModel().getColumnCount() > 0) {
            tableProductos.getColumnModel().getColumn(0).setResizable(false);
            tableProductos.getColumnModel().getColumn(0).setPreferredWidth(15);
            tableProductos.getColumnModel().getColumn(1).setResizable(false);
            tableProductos.getColumnModel().getColumn(2).setResizable(false);
            tableProductos.getColumnModel().getColumn(3).setResizable(false);
            tableProductos.getColumnModel().getColumn(4).setResizable(false);
            tableProductos.getColumnModel().getColumn(5).setResizable(false);
            tableProductos.getColumnModel().getColumn(6).setResizable(false);
        }

        Content.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 770, 580));

        panelLateralDerecho.setBackground(new java.awt.Color(255, 255, 255));
        panelLateralDerecho.setForeground(new java.awt.Color(51, 51, 51));
        panelLateralDerecho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sp1.setBackground(new java.awt.Color(0, 0, 0));
        sp1.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 120, 10));

        sp2.setBackground(new java.awt.Color(0, 0, 0));
        sp2.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 120, 10));

        sp3.setBackground(new java.awt.Color(0, 0, 0));
        sp3.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 120, 10));

        sp4.setBackground(new java.awt.Color(0, 0, 0));
        sp4.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 120, 10));

        sp5.setBackground(new java.awt.Color(0, 0, 0));
        sp5.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 120, 10));

        sp6.setBackground(new java.awt.Color(0, 0, 0));
        sp6.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 120, 10));

        sp7.setBackground(new java.awt.Color(0, 0, 0));
        sp7.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 120, 10));

        sp8.setBackground(new java.awt.Color(0, 0, 0));
        sp8.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 120, 10));

        lblId1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblId1.setForeground(new java.awt.Color(51, 51, 51));
        lblId1.setText("ID Producto:");
        panelLateralDerecho.add(lblId1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, 20));

        txtIdProducto.setBackground(new java.awt.Color(255, 255, 255));
        txtIdProducto.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtIdProducto.setForeground(new java.awt.Color(51, 51, 51));
        txtIdProducto.setBorder(null);
        txtIdProducto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIdProductoCaretUpdate(evt);
            }
        });
        panelLateralDerecho.add(txtIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 120, 20));

        lblNombre1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblNombre1.setForeground(new java.awt.Color(51, 51, 51));
        lblNombre1.setText("Nombre:");
        panelLateralDerecho.add(lblNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        txtDescripcion.setBorder(null);
        txtDescripcion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDescripcion.setEnabled(false);
        panelLateralDerecho.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 120, 20));

        lblPrecio1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblPrecio1.setForeground(new java.awt.Color(51, 51, 51));
        lblPrecio1.setText("Tamaño:");
        panelLateralDerecho.add(lblPrecio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, 20));

        lblSize1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblSize1.setForeground(new java.awt.Color(51, 51, 51));
        lblSize1.setText("Cant. Entrada:");
        panelLateralDerecho.add(lblSize1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, 20));

        lblPrecio2.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblPrecio2.setForeground(new java.awt.Color(51, 51, 51));
        lblPrecio2.setText("Precio Compra:");
        panelLateralDerecho.add(lblPrecio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, 20));

        lblSize2.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblSize2.setForeground(new java.awt.Color(51, 51, 51));
        lblSize2.setText("Monto TOTAL:");
        panelLateralDerecho.add(lblSize2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, 20));

        lblSize3.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblSize3.setForeground(new java.awt.Color(51, 51, 51));
        lblSize3.setText("ID Proveedor:");
        panelLateralDerecho.add(lblSize3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, 20));

        lblSize4.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblSize4.setForeground(new java.awt.Color(51, 51, 51));
        lblSize4.setText("Proveedor:");
        panelLateralDerecho.add(lblSize4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, 20));

        btnRellenar.setBackground(new java.awt.Color(24, 39, 72));
        btnRellenar.setForeground(new java.awt.Color(255, 255, 255));
        btnRellenar.setText("RELLENAR");
        btnRellenar.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRellenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRellenarActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(btnRellenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 570, 140, -1));

        btnGuardar.setBackground(new java.awt.Color(51, 204, 0));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("AGREGAR");
        btnGuardar.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 100, -1));

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, 100, -1));

        txtSize.setBackground(new java.awt.Color(255, 255, 255));
        txtSize.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtSize.setForeground(new java.awt.Color(51, 51, 51));
        txtSize.setBorder(null);
        txtSize.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSize.setEnabled(false);
        txtSize.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSizeCaretUpdate(evt);
            }
        });
        panelLateralDerecho.add(txtSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 120, 20));

        PanelTitle2.setBackground(new java.awt.Color(0, 112, 192));
        PanelTitle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelTitle2MousePressed(evt);
            }
        });
        PanelTitle2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblDetalle1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        LblDetalle1.setForeground(new java.awt.Color(255, 255, 255));
        LblDetalle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDetalle1.setText("Datos del producto");
        LblDetalle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LblDetalle1MousePressed(evt);
            }
        });
        PanelTitle2.add(LblDetalle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 6, -1, -1));

        panelLateralDerecho.add(PanelTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));
        panelLateralDerecho.add(iconInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 230, 180));

        txtCantidadEntrada.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidadEntrada.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtCantidadEntrada.setForeground(new java.awt.Color(51, 51, 51));
        txtCantidadEntrada.setBorder(null);
        txtCantidadEntrada.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCantidadEntradaCaretUpdate(evt);
            }
        });
        panelLateralDerecho.add(txtCantidadEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 120, 20));

        txtPrecioCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioCompra.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtPrecioCompra.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecioCompra.setBorder(null);
        txtPrecioCompra.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPrecioCompraCaretUpdate(evt);
            }
        });
        panelLateralDerecho.add(txtPrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 120, 20));

        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(51, 51, 51));
        txtTotal.setBorder(null);
        txtTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotal.setEnabled(false);
        txtTotal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTotalCaretUpdate(evt);
            }
        });
        panelLateralDerecho.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 120, 20));

        txtIdProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtIdProveedor.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtIdProveedor.setForeground(new java.awt.Color(51, 51, 51));
        txtIdProveedor.setBorder(null);
        txtIdProveedor.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIdProveedorCaretUpdate(evt);
            }
        });
        panelLateralDerecho.add(txtIdProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 120, 20));

        txtProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtProveedor.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtProveedor.setForeground(new java.awt.Color(51, 51, 51));
        txtProveedor.setBorder(null);
        txtProveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtProveedor.setEnabled(false);
        txtProveedor.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtProveedorCaretUpdate(evt);
            }
        });
        panelLateralDerecho.add(txtProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 120, 20));

        Content.add(panelLateralDerecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, 300, 620));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Rellenar Inventario");
        Content.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        PanelTitle.setBackground(new java.awt.Color(0, 112, 192));
        PanelTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFecha.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("Fecha:");
        PanelTitle.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        lblIdCompra.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        lblIdCompra.setForeground(java.awt.Color.white);
        lblIdCompra.setText("ID:");
        PanelTitle.add(lblIdCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, -1, -1));

        lblTotal.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(51, 255, 51));
        lblTotal.setText("0.00");
        PanelTitle.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 11, 160, -1));

        lblPrecioTotalCompra.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        lblPrecioTotalCompra.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecioTotalCompra.setText("Precio Total Compra: ");
        PanelTitle.add(lblPrecioTotalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 10, -1, -1));

        lblIdCompraG.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        lblIdCompraG.setForeground(new java.awt.Color(51, 255, 51));
        PanelTitle.add(lblIdCompraG, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 10, 160, 20));

        Content.add(PanelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 700, 770, 40));

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

    private void txtIdProductoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdProductoCaretUpdate
        checkIdProducto();
    }//GEN-LAST:event_txtIdProductoCaretUpdate
                               
    private void txtSizeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSizeCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSizeCaretUpdate

    private void btnRellenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRellenarActionPerformed
    int option2 = JOptionPane.showConfirmDialog(this, "¿Desea generar una cuenta por pagar?\n Si saldó la cuenta presione NO.", "Cuenta a Pagar.", JOptionPane.YES_NO_OPTION);
            
        if(option2 == JOptionPane.YES_OPTION)
        {
            generarCompra();
            generarDeuda();
        }else
        {
           generarCompra();
           generarGasto();

        }  
            
        limpiarPestaña();
        generarIdCompra();
    }//GEN-LAST:event_btnRellenarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int index = tableProductos.getSelectedRow();
        
        if(index != -1)
        {
            DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();
            Float total = Float.valueOf(lblTotal.getText());
            Float precioCompra =  (Float) model.getValueAt(index, 4);

            int cantidad =  (int) model.getValueAt(index, 3);


            Float monto = precioCompra * cantidad;
            String totalC = String.format("%.2f", (total - monto));

            lblTotal.setText(totalC.replace(",", "."));
            model.removeRow(index);
            
            tableProductos.setModel(model);
        }else{
            JOptionPane.showMessageDialog(this, "Elija una fila para eliminarla.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        boolean existe = comprobarProductoProveedor();
        
        if(existe)
        {
            Boolean agregado = agregar();
            
            if(agregado)
            {
                limpiar();
                txtIdProveedor.setEnabled(false);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void LblDetalle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblDetalle1MousePressed
       // deseleccion();
    }//GEN-LAST:event_LblDetalle1MousePressed

    private void PanelTitle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelTitle2MousePressed
        // deseleccion();
    }//GEN-LAST:event_PanelTitle2MousePressed

    private void txtCantidadEntradaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCantidadEntradaCaretUpdate
        actualizarTxt();
    }//GEN-LAST:event_txtCantidadEntradaCaretUpdate

    private void txtPrecioCompraCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPrecioCompraCaretUpdate
        actualizarTxt();
    }//GEN-LAST:event_txtPrecioCompraCaretUpdate

    private void txtTotalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTotalCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalCaretUpdate

    private void txtIdProveedorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdProveedorCaretUpdate
        checkIdProveedor();
    }//GEN-LAST:event_txtIdProveedorCaretUpdate

    private void txtProveedorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtProveedorCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedorCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblDetalle1;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JPanel PanelTitle;
    private javax.swing.JPanel PanelTitle2;
    private Utils.ButtonRounded btnEliminar;
    private Utils.ButtonRounded btnGuardar;
    private Utils.ButtonRounded btnRellenar;
    private Utils.SVGImage2 iconInventario;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblId1;
    private javax.swing.JLabel lblIdCompra;
    private javax.swing.JLabel lblIdCompraG;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblPrecio1;
    private javax.swing.JLabel lblPrecio2;
    private javax.swing.JLabel lblPrecioTotalCompra;
    private javax.swing.JLabel lblSize1;
    private javax.swing.JLabel lblSize2;
    private javax.swing.JLabel lblSize3;
    private javax.swing.JLabel lblSize4;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel panelLateralDerecho;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JSeparator sp1;
    private javax.swing.JSeparator sp2;
    private javax.swing.JSeparator sp3;
    private javax.swing.JSeparator sp4;
    private javax.swing.JSeparator sp5;
    private javax.swing.JSeparator sp6;
    private javax.swing.JSeparator sp7;
    private javax.swing.JSeparator sp8;
    private javax.swing.JTable tableProductos;
    private javax.swing.JTextField txtCantidadEntrada;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
