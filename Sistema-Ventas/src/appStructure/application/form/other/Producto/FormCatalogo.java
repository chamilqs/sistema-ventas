package appStructure.application.form.other.Producto;

import Database.Connect.ConexionSingleton;
import Database.DAO.ProductoDAO;
import Database.DAOImpl.ProductoDAOImpl;
import Database.DTO.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samil 
 */
public class FormCatalogo extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormCatalogo() {
        initComponents();
        init();
    }

    private void init(){
       
        iconProducto.setSvgImage("appStructure/icon/svg/product.svg", 220, 220);
        actualizarTablaC();
        
    }

    private void delete(){   
        try {
            ProductoDAOImpl pd = new ProductoDAOImpl();
            
            int indexRow = tablaCatalogo.getSelectedRow();
            
            if(indexRow != -1)
            {
                
            DefaultTableModel model = (DefaultTableModel) tablaCatalogo.getModel();
            
            String id = (String) model.getValueAt(indexRow, 0);
            
            pd.delete(id);
            
            actualizarTablaC();
            }else
            {
             JOptionPane.showMessageDialog(Content, "Debe seleccionar una fila primero");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormCatalogo.class.getName()).log(Level.SEVERE, null, ex);
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

            actualizarTablaC();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void add(){
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
                    
                    actualizarTablaC();
                    limpiar();
                }catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(Content, "Ingrese un precio valido");
                }
                 catch(SQLException e)
                {
                    System.err.println(e);
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(FormCatalogo.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        
    }
    private void actualizarTabla(JTable tabla, String query){
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
 
        try(Connection cn = c.getConexion()) {
            
            Statement stm = cn.createStatement();            
            ResultSet rs = stm.executeQuery(query);
            
            //RELLENADO DE LAS COLUMNAS
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            for (int column = 1; column <= columnCount; column++) {
                 model.addColumn(metaData.getColumnLabel(column));
             }

            //RELLENADO DE LAS FILAS
            while (rs.next()) {
                 Object[] rowData = new Object[columnCount];
                 for (int column = 1; column <= columnCount; column++) {
                     rowData[column - 1] = rs.getObject(column);
                 }
                 model.addRow(rowData);
             }
            
            tabla.setModel(model);
            
        } catch (SQLException ex) {
            Logger.getLogger(FormCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void actualizarTablaC(){
           
        String query =  "SELECT p.id AS 'ID Producto', p.descripcion AS 'Nombre', p.size AS 'Tamaño', p.precioU AS Precio, p.prioridad AS 'Prioridad'"  +
                        "FROM Producto AS p;";
        
        actualizarTabla(tablaCatalogo, query);
    } 
    
    private void limpiar(){
        txtDescripcion.setText("");
        txtId.setText("");
        txtPrecio.setText("");
        txtSize.setText("");
        chkbxPrioridad.setSelected(false);
    }     
    private void deseleccion(){
           
        tablaCatalogo.clearSelection();
        btnAgregar.setVisible(true);
        btnEditar.setVisible(true);
        limpiar();
            
    }
    private void buscador() {
    String textoBusqueda = txtBuscador.getText();

    if (textoBusqueda.isEmpty()) {
        mostrarMensajeError("Introduzca un valor para buscar.");
        return;
    }

    String seleccion = (String) cmbCatalogo.getSelectedItem();
    String query = construirConsulta(seleccion, textoBusqueda);

    if (query.isEmpty()) {
        return; // Hubo un error al construir la consulta.
    }

    ejecutarConsulta(query);
}

    private String construirConsulta(String seleccion, String textoBusqueda) {
        switch (seleccion) {
            case "ID":
                return construirConsultaPorId(textoBusqueda);
            case "Nombre":
                return construirConsultaPorNombre(textoBusqueda);
            case "Precio":
                return construirConsultaPorPrecio(textoBusqueda);
            case "Tamaño":
                return construirConsultaPorTamaño(textoBusqueda);
            case "Prioridad":
                return construirConsultaPorPrioridad(textoBusqueda);
            default:
                mostrarMensajeError("Seleccione una opción válida para realizar la búsqueda.");
                return "";
        }
    }
    private String construirConsultaPorId(String id) {
        return "SELECT p.id AS 'ID Producto', p.descripcion AS 'Nombre', p.size AS 'Tamaño', p.precioU AS Precio, p.prioridad AS 'Prioridad'" 
                + " FROM Producto AS p"
                + " WHERE p.id = '" + id + "'";
    }
    private String construirConsultaPorNombre(String nombre) {
        return "SELECT p.id AS 'ID Producto', p.descripcion AS 'Nombre', p.size AS 'Tamaño', p.precioU AS Precio, p.prioridad AS 'Prioridad'" 
                + " FROM Producto AS p"
                + " WHERE p.descripcion = '" + nombre + "'";
    }
    private String construirConsultaPorPrecio(String precio) {
        if (!esNumero(precio)) {
            mostrarMensajeError("Precio debe ser un número válido.");
            return "";
        }

        return "SELECT p.id AS 'ID Producto', p.descripcion AS 'Nombre', p.size AS 'Tamaño', p.precioU AS Precio, p.prioridad AS 'Prioridad'" 
                + " FROM Producto AS p"
                + " WHERE p.precioU = " + precio;
    }
    private String construirConsultaPorTamaño(String tamaño) {
        return "SELECT p.id AS 'ID Producto', p.descripcion AS 'Nombre', p.size AS 'Tamaño', p.precioU AS Precio, p.prioridad AS 'Prioridad'" 
                + " FROM Producto AS p"
                + " WHERE p.size = '" + tamaño + "'";
    }
    private String construirConsultaPorPrioridad(String prioridad) {
        if (!esPrioridadValida(prioridad)) {
            mostrarMensajeError("La prioridad debe ser 'alta' o 'baja'.");
            return "";
        }

        return "SELECT p.id AS 'ID Producto', p.descripcion AS 'Nombre', p.size AS 'Tamaño', p.precioU AS Precio, p.prioridad AS 'Prioridad'" 
                + " FROM Producto AS p"
                + " WHERE p.prioridad = '" + prioridad + "'";
    }
    private void ejecutarConsulta(String query) {
        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            try (Connection connection = c.getConexion();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

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

            tablaCatalogo.setModel(modelo);
            c.desconectar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    private boolean esNumero(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean esPrioridadValida(String texto) {
        return texto.equalsIgnoreCase("alta") || texto.equalsIgnoreCase("baja");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Content = new javax.swing.JPanel();
        scrTablaCatalogo = new javax.swing.JScrollPane();
        tablaCatalogo = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        panelLateralDerecho = new javax.swing.JPanel();
        sp1 = new javax.swing.JSeparator();
        sp2 = new javax.swing.JSeparator();
        sp3 = new javax.swing.JSeparator();
        sp4 = new javax.swing.JSeparator();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblSize = new javax.swing.JLabel();
        txtSize = new javax.swing.JTextField();
        chkbxPrioridad = new javax.swing.JCheckBox();
        PanelTitle = new javax.swing.JPanel();
        LblDetalle = new javax.swing.JLabel();
        btnEditar = new Utils.ButtonRounded();
        btnEliminar = new Utils.ButtonRounded();
        btnAgregar = new Utils.ButtonRounded();
        iconProducto = new Utils.SVGImage2();
        cmbCatalogo = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        LblFormatoFecha = new javax.swing.JLabel();

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setPreferredSize(new java.awt.Dimension(1005, 620));
        Content.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ContentMousePressed(evt);
            }
        });
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaCatalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Producto", "Producto", "Tamaño", "Precio", "Prioridad"
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
            tablaCatalogo.getColumnModel().getColumn(0).setPreferredWidth(15);
            tablaCatalogo.getColumnModel().getColumn(1).setResizable(false);
            tablaCatalogo.getColumnModel().getColumn(2).setResizable(false);
            tablaCatalogo.getColumnModel().getColumn(3).setResizable(false);
            tablaCatalogo.getColumnModel().getColumn(4).setResizable(false);
        }

        Content.add(scrTablaCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 740, 600));

        lblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("Catálogo de productos");
        lblTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTitleMousePressed(evt);
            }
        });
        Content.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        panelLateralDerecho.setBackground(new java.awt.Color(255, 255, 255));
        panelLateralDerecho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelLateralDerechoMousePressed(evt);
            }
        });
        panelLateralDerecho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sp1.setBackground(new java.awt.Color(0, 0, 0));
        sp1.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 308, 140, 10));

        sp2.setBackground(new java.awt.Color(0, 0, 0));
        sp2.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 338, 140, 10));

        sp3.setBackground(new java.awt.Color(0, 0, 0));
        sp3.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 368, 140, 10));

        sp4.setBackground(new java.awt.Color(0, 0, 0));
        sp4.setForeground(new java.awt.Color(0, 0, 0));
        panelLateralDerecho.add(sp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 398, 140, 10));

        lblId.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblId.setForeground(new java.awt.Color(0, 0, 0));
        lblId.setText("ID Producto:");
        panelLateralDerecho.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 20));

        txtId.setBackground(new java.awt.Color(255, 255, 255));
        txtId.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtId.setForeground(new java.awt.Color(0, 0, 0));
        txtId.setBorder(null);
        panelLateralDerecho.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 140, 20));

        lblNombre.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre.setText("Nombre:");
        panelLateralDerecho.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        txtPrecio.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecio.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(0, 0, 0));
        txtPrecio.setBorder(null);
        panelLateralDerecho.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 140, 20));

        lblPrecio.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(0, 0, 0));
        lblPrecio.setText("Precio:");
        panelLateralDerecho.add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, -1, 20));

        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        txtDescripcion.setBorder(null);
        panelLateralDerecho.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 140, 20));

        lblSize.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lblSize.setForeground(new java.awt.Color(0, 0, 0));
        lblSize.setText("Tamaño:");
        panelLateralDerecho.add(lblSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, 20));

        txtSize.setBackground(new java.awt.Color(255, 255, 255));
        txtSize.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtSize.setForeground(new java.awt.Color(0, 0, 0));
        txtSize.setBorder(null);
        txtSize.setVerifyInputWhenFocusTarget(false);
        panelLateralDerecho.add(txtSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 140, 20));

        chkbxPrioridad.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        chkbxPrioridad.setForeground(new java.awt.Color(0, 0, 0));
        chkbxPrioridad.setText("Prioridad Alta");
        chkbxPrioridad.setToolTipText("Prioridad del producto. Dejela sin marcar si tiene prioridad baja.");
        panelLateralDerecho.add(chkbxPrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 140, -1));

        PanelTitle.setBackground(new java.awt.Color(0, 112, 192));
        PanelTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblDetalle.setFont(new java.awt.Font("Roboto Black", 1, 20)); // NOI18N
        LblDetalle.setForeground(new java.awt.Color(255, 255, 255));
        LblDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDetalle.setText("Producto");
        PanelTitle.add(LblDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 160, -1));

        panelLateralDerecho.add(PanelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 40));

        btnEditar.setBackground(new java.awt.Color(24, 39, 72));
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("EDITAR");
        btnEditar.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 100, -1));

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 100, -1));

        btnAgregar.setBackground(new java.awt.Color(51, 204, 0));
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("AGREGAR");
        btnAgregar.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        panelLateralDerecho.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 100, -1));

        iconProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iconProductoMousePressed(evt);
            }
        });
        panelLateralDerecho.add(iconProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 250, 210));

        Content.add(panelLateralDerecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, 330, 600));

        cmbCatalogo.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 12)); // NOI18N
        cmbCatalogo.setForeground(new java.awt.Color(255, 255, 255));
        cmbCatalogo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Precio", "Tamaño", "Prioridad" }));
        Content.add(cmbCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 100, 30));
        Content.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 100, 159, 30));

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

    private void tablaCatalogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCatalogoMouseClicked
        int i = tablaCatalogo.getSelectedRow();

    if (i != -1) {
        
        DefaultTableModel model = (DefaultTableModel) tablaCatalogo.getModel();
        String id = (String) model.getValueAt(i, 0);        txtId.setText(id);
        String nombre = (String) model.getValueAt(i, 1);
        String size = (String) model.getValueAt(i, 2);
        float precio = (float) model.getValueAt(i, 3);
        String prioridad = (String) model.getValueAt(i, 4);
        
        txtId.setText(id);
        txtDescripcion.setText(nombre);
        txtSize.setText(size);
        txtPrecio.setText("" + precio);

        if (prioridad.equals("alta")) {
            chkbxPrioridad.setSelected(true);
        } else {
            chkbxPrioridad.setSelected(false);
        }
        btnAgregar.setVisible(false);
        btnEditar.setVisible(true);

    } 
        
    }//GEN-LAST:event_tablaCatalogoMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        update();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        delete();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        add();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscador();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void iconProductoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconProductoMousePressed
        deseleccion();
    }//GEN-LAST:event_iconProductoMousePressed

    private void panelLateralDerechoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLateralDerechoMousePressed
        deseleccion();
    }//GEN-LAST:event_panelLateralDerechoMousePressed

    private void ContentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContentMousePressed
        deseleccion();
    }//GEN-LAST:event_ContentMousePressed

    private void lblTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTitleMousePressed
        deseleccion();
    }//GEN-LAST:event_lblTitleMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblDetalle;
    private javax.swing.JLabel LblFormatoFecha;
    private javax.swing.JPanel PanelTitle;
    private Utils.ButtonRounded btnAgregar;
    private javax.swing.JButton btnBuscar;
    private Utils.ButtonRounded btnEditar;
    private Utils.ButtonRounded btnEliminar;
    private javax.swing.JCheckBox chkbxPrioridad;
    private javax.swing.JComboBox<String> cmbCatalogo;
    private Utils.SVGImage2 iconProducto;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelLateralDerecho;
    private javax.swing.JScrollPane scrTablaCatalogo;
    private javax.swing.JSeparator sp1;
    private javax.swing.JSeparator sp2;
    private javax.swing.JSeparator sp3;
    private javax.swing.JSeparator sp4;
    private javax.swing.JTable tablaCatalogo;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSize;
    // End of variables declaration//GEN-END:variables
}
