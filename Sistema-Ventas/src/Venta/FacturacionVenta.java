
package Venta;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
import DTO.Usuario;
import Utils.Utils.UniqueIDGenerator;
import Venta.DeudaClientes;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.Timer;

/**
 *
 * @author Angelo Fr.
 */
public class FacturacionVenta extends javax.swing.JPanel {

    /**
     * Creates new form FacturacionVenta
     */
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final String formattedFechaHora = LocalDateTime.now().format(formatter);
    LocalDateTime fechaHoraActua = LocalDateTime.now();    
    Usuario u = null;
    
      // HashMaps para almacenar los valores de los JLabels
    private HashMap<String, String> labelValues = new HashMap<>();
    private HashMap<String, String> tableValues = new HashMap<>();
    private HashMap<Integer, Object[]> tableData = new HashMap<>();
    

    private float total = 0;
    

    private static final String rutaBase = "src\\imgnew\\";
    public FacturacionVenta() {
        initComponents();
        inicializar();
         
    }

    public FacturacionVenta(Usuario u) {
        initComponents();
        this.u = u;
        inicializar();
        restaurarDatos();
        agregarListeners();
        

        // Setear un ID aleatorio en ID Venta
        String idV = UniqueIDGenerator.generateUniqueID();
        LblIDVenta.setText(idV);
         
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContentPanelVenta = new javax.swing.JPanel();
        ScrollPanelVenta = new javax.swing.JScrollPane();
        tablaProductosVentas = new javax.swing.JTable();
        PanelContenido = new javax.swing.JPanel();
        txtCantidadProducto = new javax.swing.JTextField();
        LblCantidad = new javax.swing.JLabel();
        LblPrecio = new javax.swing.JLabel();
        LblPrecioProducto = new javax.swing.JLabel();
        LblSizeProducto = new javax.swing.JLabel();
        LblSize = new javax.swing.JLabel();
        LblNombre = new javax.swing.JLabel();
        LblIDProducto = new javax.swing.JLabel();
        txtIDProducto = new javax.swing.JTextField();
        LblNombreProducto = new javax.swing.JLabel();
        LblDevuelta = new javax.swing.JLabel();
        LblDevueltaMonto = new javax.swing.JLabel();
        LblMontoPagado = new javax.swing.JLabel();
        LblTotalPagar = new javax.swing.JLabel();
        LblMontoPagar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LblDetalle = new javax.swing.JLabel();
        LblEliminar1 = new javax.swing.JLabel();
        LblAgregar1 = new javax.swing.JLabel();
        pdf = new javax.swing.JLabel();
        Eliminardelalista = new javax.swing.JLabel();
        btnFacturar = new rojerusan.RSMaterialButtonRectangle();
        IconPDF = new javax.swing.JLabel();
        btnCotizar = new rojerusan.RSMaterialButtonRectangle();
        montoIngresado = new javax.swing.JTextField();
        LblCantidad1 = new javax.swing.JLabel();
        LblCantidad2 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        Agregaralalista = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        LblAdvertencia = new javax.swing.JLabel();
        LblUtilidad4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        LblIDVenta = new javax.swing.JLabel();

        ContentPanelVenta.setBackground(new java.awt.Color(24, 39, 72));
        ContentPanelVenta.setPreferredSize(new java.awt.Dimension(1005, 660));
        ContentPanelVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProductosVentas.setBackground(new java.awt.Color(51, 51, 51));
        tablaProductosVentas.setForeground(new java.awt.Color(204, 204, 204));
        tablaProductosVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Tamaño", "Precio", "Cantidad", "Total"
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
        tablaProductosVentas.getTableHeader().setReorderingAllowed(false);
        ScrollPanelVenta.setViewportView(tablaProductosVentas);
        if (tablaProductosVentas.getColumnModel().getColumnCount() > 0) {
            tablaProductosVentas.getColumnModel().getColumn(0).setResizable(false);
            tablaProductosVentas.getColumnModel().getColumn(1).setResizable(false);
            tablaProductosVentas.getColumnModel().getColumn(2).setResizable(false);
            tablaProductosVentas.getColumnModel().getColumn(3).setResizable(false);
            tablaProductosVentas.getColumnModel().getColumn(4).setResizable(false);
            tablaProductosVentas.getColumnModel().getColumn(5).setResizable(false);
        }

        ContentPanelVenta.add(ScrollPanelVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 620, 470));

        PanelContenido.setBackground(new java.awt.Color(255, 255, 255));
        PanelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCantidadProducto.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidadProducto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCantidadProducto.setForeground(new java.awt.Color(51, 51, 51));
        txtCantidadProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidadProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCantidadProducto.setOpaque(true);
        txtCantidadProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadProductoKeyTyped(evt);
            }
        });
        PanelContenido.add(txtCantidadProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 120, 20));

        LblCantidad.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblCantidad.setForeground(new java.awt.Color(0, 0, 0));
        LblCantidad.setText("Cliente:");
        PanelContenido.add(LblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 60, 20));

        LblPrecio.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblPrecio.setForeground(new java.awt.Color(0, 0, 0));
        LblPrecio.setText("Precio:");
        PanelContenido.add(LblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 20));

        LblPrecioProducto.setBackground(new java.awt.Color(255, 255, 255));
        LblPrecioProducto.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblPrecioProducto.setForeground(new java.awt.Color(51, 51, 51));
        LblPrecioProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblPrecioProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblPrecioProducto.setOpaque(true);
        PanelContenido.add(LblPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 120, 20));

        LblSizeProducto.setBackground(new java.awt.Color(255, 255, 255));
        LblSizeProducto.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblSizeProducto.setForeground(new java.awt.Color(51, 51, 51));
        LblSizeProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblSizeProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblSizeProducto.setOpaque(true);
        PanelContenido.add(LblSizeProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 120, 20));

        LblSize.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblSize.setForeground(new java.awt.Color(0, 0, 0));
        LblSize.setText("Tamaño:");
        PanelContenido.add(LblSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 20));

        LblNombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblNombre.setForeground(new java.awt.Color(0, 0, 0));
        LblNombre.setText("Producto:");
        PanelContenido.add(LblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        LblIDProducto.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblIDProducto.setForeground(new java.awt.Color(0, 0, 0));
        LblIDProducto.setText("ID Prod:");
        PanelContenido.add(LblIDProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 20));

        txtIDProducto.setBackground(new java.awt.Color(255, 255, 255));
        txtIDProducto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtIDProducto.setForeground(new java.awt.Color(51, 51, 51));
        txtIDProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtIDProducto.setOpaque(true);
        txtIDProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDProductoKeyTyped(evt);
            }
        });
        PanelContenido.add(txtIDProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 120, 20));

        LblNombreProducto.setBackground(new java.awt.Color(255, 255, 255));
        LblNombreProducto.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblNombreProducto.setForeground(new java.awt.Color(51, 51, 51));
        LblNombreProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblNombreProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblNombreProducto.setOpaque(true);
        PanelContenido.add(LblNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 120, 20));

        LblDevuelta.setBackground(new java.awt.Color(255, 255, 255));
        LblDevuelta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblDevuelta.setForeground(new java.awt.Color(0, 0, 0));
        LblDevuelta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDevuelta.setText("0");
        LblDevuelta.setOpaque(true);
        PanelContenido.add(LblDevuelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 90, 20));

        LblDevueltaMonto.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblDevueltaMonto.setForeground(new java.awt.Color(0, 0, 0));
        LblDevueltaMonto.setText("Devolver:");
        PanelContenido.add(LblDevueltaMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 60, 20));

        LblMontoPagado.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoPagado.setForeground(new java.awt.Color(0, 0, 0));
        LblMontoPagado.setText("Total Recibido:");
        PanelContenido.add(LblMontoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 100, 30));

        LblTotalPagar.setBackground(new java.awt.Color(0, 0, 0));
        LblTotalPagar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblTotalPagar.setForeground(new java.awt.Color(0, 0, 0));
        LblTotalPagar.setText("Costo total:");
        PanelContenido.add(LblTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 80, 30));

        LblMontoPagar.setBackground(new java.awt.Color(51, 255, 0));
        LblMontoPagar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoPagar.setForeground(new java.awt.Color(0, 204, 0));
        LblMontoPagar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblMontoPagar.setText("0");
        PanelContenido.add(LblMontoPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 90, 30));

        jPanel1.setBackground(new java.awt.Color(0, 112, 192));

        LblDetalle.setFont(new java.awt.Font("Roboto Black", 1, 20)); // NOI18N
        LblDetalle.setForeground(new java.awt.Color(255, 255, 255));
        LblDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDetalle.setText("Detalles");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addComponent(LblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(LblDetalle)
                .addContainerGap())
        );

        PanelContenido.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        LblEliminar1.setBackground(new java.awt.Color(255, 255, 255));
        LblEliminar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LblEliminar1.setOpaque(true);
        LblEliminar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblEliminar1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LblEliminar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LblEliminar1MouseExited(evt);
            }
        });
        PanelContenido.add(LblEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 30, 30));

        LblAgregar1.setBackground(new java.awt.Color(255, 255, 255));
        LblAgregar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LblAgregar1.setOpaque(true);
        LblAgregar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblAgregar1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LblAgregar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LblAgregar1MouseExited(evt);
            }
        });
        PanelContenido.add(LblAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 30, 30));

        pdf.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        pdf.setForeground(new java.awt.Color(102, 102, 102));
        pdf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pdf.setText("Pdf");
        PanelContenido.add(pdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 250, 30, -1));

        Eliminardelalista.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        Eliminardelalista.setForeground(new java.awt.Color(102, 102, 102));
        Eliminardelalista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Eliminardelalista.setText("Eliminar ");
        PanelContenido.add(Eliminardelalista, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 50, -1));

        btnFacturar.setBackground(new java.awt.Color(132, 178, 80));
        btnFacturar.setText("Facturar");
        btnFacturar.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });
        PanelContenido.add(btnFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 110, 40));

        IconPDF.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        IconPDF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IconPDFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IconPDFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IconPDFMouseExited(evt);
            }
        });
        PanelContenido.add(IconPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 30, 30));

        btnCotizar.setBackground(new java.awt.Color(132, 178, 80));
        btnCotizar.setText("Cotizar");
        btnCotizar.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btnCotizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCotizarActionPerformed(evt);
            }
        });
        PanelContenido.add(btnCotizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 110, 40));

        montoIngresado.setBackground(new java.awt.Color(255, 255, 255));
        montoIngresado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        montoIngresado.setForeground(new java.awt.Color(0, 0, 0));
        montoIngresado.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                montoIngresadoCaretUpdate(evt);
            }
        });
        montoIngresado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                montoIngresadoKeyTyped(evt);
            }
        });
        PanelContenido.add(montoIngresado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 90, 30));

        LblCantidad1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblCantidad1.setForeground(new java.awt.Color(0, 0, 0));
        LblCantidad1.setText("Correo:");
        PanelContenido.add(LblCantidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 50, 20));

        LblCantidad2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblCantidad2.setForeground(new java.awt.Color(0, 0, 0));
        LblCantidad2.setText("Cantidad:");
        PanelContenido.add(LblCantidad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, 20));

        txtNombreCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCliente.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNombreCliente.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });
        PanelContenido.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 120, 20));

        txtCorreo.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCorreo.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });
        PanelContenido.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 120, 20));

        Agregaralalista.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        Agregaralalista.setForeground(new java.awt.Color(102, 102, 102));
        Agregaralalista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Agregaralalista.setText("Agregar");
        PanelContenido.add(Agregaralalista, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 50, -1));
        PanelContenido.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 250, 10));

        LblAdvertencia.setForeground(new java.awt.Color(255, 51, 51));
        LblAdvertencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanelContenido.add(LblAdvertencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 20, 20));

        ContentPanelVenta.add(PanelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 290, 530));

        LblUtilidad4.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad4.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblUtilidad4.setForeground(new java.awt.Color(0, 112, 192));
        LblUtilidad4.setText("Facturacion");
        ContentPanelVenta.add(LblUtilidad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Idventa / No. Factura:");
        ContentPanelVenta.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, 30));

        LblIDVenta.setForeground(new java.awt.Color(51, 255, 51));
        LblIDVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ContentPanelVenta.add(LblIDVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContentPanelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContentPanelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void inicializar(){
        
        // Setear un ID aleatorio en ID Venta
        String idV = UniqueIDGenerator.generateUniqueID();
        LblIDVenta.setText(idV);
      
      setScaledImage(LblEliminar1, "eliminar.png", 25, 25);
      setScaledImage(LblAgregar1, "AgregarDoc.png", 25, 25);
      setScaledImage(IconPDF, "pdf.png", 25, 25);

        UIManager.put("ToolTip.background", Color.white);
        LblAgregar1.setToolTipText("Haga clic para agregar a la lista");
        UIManager.put("ToolTip.background", Color.white);
        IconPDF.setToolTipText("imprimir pdf"); 
        UIManager.put("ToolTip.background", Color.white);
        LblEliminar1.setToolTipText("Haga clic para eliminar de la lista"); 
        UIManager.put("ToolTip.background", Color.white);
        
         // Add a DocumentListener to txtIDProducto
    txtIDProducto.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            checkId();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkId();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // Not needed for plain text components
        }
    });
    }
  
    private void agregarListeners() {

        txtIDProducto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                guardarDatos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                guardarDatos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                guardarDatos();
            }
        });

    }

    public void guardarDatos() {
        guardarLabelValues();
        guardarTablaData();
        System.out.println("Datos guardados");
    }

    public void restaurarDatos() {
        restaurarLabelValues();
        restaurarTablaData();
        System.out.println("Datos restaurados");
    }

    private void guardarTablaData() {
        DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
        int rowCount = model.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            Object[] rowData = new Object[model.getColumnCount()];
            for (int j = 0; j < model.getColumnCount(); j++) {
                rowData[j] = model.getValueAt(i, j);
            }
            tableData.put(i, rowData);
        }
    }

    private void restaurarTablaData() {
        DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
        model.setRowCount(0);

        for (Object[] rowData : tableData.values()) {
            model.addRow(rowData);
        }
    }

    private void guardarLabelValues() {
        //labelValues.put("LblIDVenta", LblIDVenta.getText());
        labelValues.put("LblNombreProducto", LblNombreProducto.getText());
        labelValues.put("LblSizeProducto", LblSizeProducto.getText());
        labelValues.put("LblPrecioProducto", LblPrecioProducto.getText());

    }

    private void restaurarLabelValues() {

        //LblIDVenta.setText(labelValues.get("LblIDVenta"));
        LblNombreProducto.setText(labelValues.get("LblNombreProducto"));
        LblSizeProducto.setText(labelValues.get("LblSizeProducto"));
        LblPrecioProducto.setText(labelValues.get("LblPrecioProducto"));

    }

    public void borrarDatos() {
        borrarLabelValues();
        borrarTablaData();
        System.out.println("Datos borrados");
    }

    private void borrarTablaData() {
        tableData.clear();
        DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
        model.setRowCount(0);
    }

    private void borrarLabelValues() {
        labelValues.clear();
        LblNombreProducto.setText("");
        LblSizeProducto.setText("");
    }
    
    private void checkId() {
        String idProducto = txtIDProducto.getText();
        ProductoDAO pdo = new ProductoDAOImpl();

        try {
            Producto p = pdo.get(idProducto);

            if (p != null) {
                LblNombreProducto.setText(p.getNombre());
                LblSizeProducto.setText(p.getSize());
                LblPrecioProducto.setText("" + p.getPrecio());
            } else {
                LblNombreProducto.setText("Producto no encontrado");
                LblSizeProducto.setText("");
                LblPrecioProducto.setText("");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FacturacionVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarATabla() {
     String idProducto = txtIDProducto.getText();
     String size = LblSizeProducto.getText();
     String nombre = LblNombreProducto.getText();
     String cantidad = txtCantidadProducto.getText();

     if (!idProducto.isEmpty() && !cantidad.isEmpty() && !nombre.isEmpty()) {
         try {
             int cant = Integer.parseInt(cantidad); // Parse cantidad as an integer

             if (cant >= 0) { // Check for non-negative quantity
                 float precio = Float.parseFloat(LblPrecioProducto.getText());
                 float total = precio * cant;
                 DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();

                 Object[] fila = { idProducto, nombre, size, precio, cantidad, total };
                 model.addRow(fila);
                 calcularTotal(); // Make sure you have a method to update the total

                 txtIDProducto.setText("");
                 LblNombreProducto.setText("");
                 LblSizeProducto.setText("");
                 LblPrecioProducto.setText("");
                 txtCantidadProducto.setText("");
             } else {
                 JOptionPane.showMessageDialog(this, "No se permiten números negativos en la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
             }
         } catch (NumberFormatException ex) {
             JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
         }
        } else {
            JOptionPane.showMessageDialog(this, "Rellene todos los campos.");
        }
    }
    
    public void calcularTotal() {
    DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
    double total = 0.0;

    for (int i = 0; i < model.getRowCount(); i++) {
        float totalFloat = (float) model.getValueAt(i, 5);
        total += totalFloat;
    }

    LblMontoPagar.setText(String.format("%.2f", total).replace(",", "."));
}
    
    private void EliminarFila() {
    int selectedRow = tablaProductosVentas.getSelectedRow();

    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar este producto?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
        model.removeRow(selectedRow);
        calcularTotal();
    }
}

    public static void CleanAll(){
        
        DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel(); 
        txtIDProducto.setText("");
        LblNombreProducto.setText("");
        LblSizeProducto.setText("");
        LblPrecioProducto.setText("");
        txtCantidadProducto.setText("");
        LblMontoPagar.setText("0");
        montoIngresado.setText("");
        LblDevuelta.setText("0"); 
        LblAdvertencia.setText("");
        model.setRowCount(0);  
    
    }

    private boolean sesionCajaAbierta() {
        String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre is NULL";

        try (Connection conectar = Conexion.conectar();
             PreparedStatement pst = conectar.prepareStatement(checkNullDate);
             ResultSet rs = pst.executeQuery()) {

            return rs.next();
        } catch (SQLException ex) {
            mostrarError("Error en la base de datos: " + ex.getMessage());
            Logger.getLogger(FacturacionVenta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    private void mostrarDeuda(float devuelta, int MontoR, String nombreCliente, String idVenta) {
        DeudaClientes dc = new DeudaClientes();
        dc.inicializarDeuda(devuelta, MontoR, nombreCliente, idVenta); // Inicializar los valores de deuda, nombre e ID de venta
        dc.setLocationRelativeTo(this);
        dc.setSize(459, 456);
        dc.setVisible(true);
    }
    
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void VerificacionStock() {
    String idProductoText = txtIDProducto.getText();
    String cantidadIngresadaText = txtCantidadProducto.getText();

    if (idProductoText.isEmpty() || cantidadIngresadaText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese valores en los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        int cantidadIngresada = Integer.parseInt(cantidadIngresadaText);

        if (cantidadIngresada <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection cn = Conexion.conectar();
        PreparedStatement ps = cn.prepareStatement(
            "SELECT p.descripcion, p.size, i.stock " +
            "FROM Inventario i " +
            "INNER JOIN Producto p ON p.id = i.idProducto " +
            "WHERE i.idProducto = ?"
        );
        ps.setString(1, idProductoText);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String descripcionProducto = rs.getString("descripcion");
            int stockActual = rs.getInt("stock");

            if (stockActual <= 0) {
                JOptionPane.showMessageDialog(this, "El producto " + descripcionProducto + " está agotado", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (stockActual < cantidadIngresada) {
                JOptionPane.showMessageDialog(this, "No hay suficiente stock para vender " + cantidadIngresada + " unidades del producto " + descripcionProducto + " (Stock disponible: " + stockActual + " unidades)", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (stockActual < 10) {
                int opcion = JOptionPane.showConfirmDialog(this, "El producto " + descripcionProducto + " está bajo en stock (" + stockActual + " unidades).\n¿Desea continuar con la venta?", "Advertencia", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    agregarATabla();
                }
            } else {
                agregarATabla();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado en el inventario", "Error", JOptionPane.ERROR_MESSAGE);
        }

        rs.close();
        ps.close();
        cn.close();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "La cantidad debe ser un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    private void validarFacturacion() {
    try {
        Connection conectar = Conexion.conectar();

        DefaultTableModel modelo = (DefaultTableModel) tablaProductosVentas.getModel();
        int numRows = modelo.getRowCount();

        boolean productosSuperanStock = false;
        List<String> productosConStockExcedido = new ArrayList<>();
        Map<String, Integer> accumulatedQuantities = new HashMap<>();

        for (int i = 0; i < numRows; i++) {
            String idProducto = (String) modelo.getValueAt(i, 0);
            String cantidadIngresadaText = modelo.getValueAt(i, 4).toString();

            if (idProducto.isEmpty() || cantidadIngresadaText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese valores en los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int cantidadIngresada = Integer.parseInt(cantidadIngresadaText);

                // Accumulate quantities for each product ID
                if (accumulatedQuantities.containsKey(idProducto)) {
                    int currentQuantity = accumulatedQuantities.get(idProducto);
                    accumulatedQuantities.put(idProducto, currentQuantity + cantidadIngresada);
                } else {
                    accumulatedQuantities.put(idProducto, cantidadIngresada);
                }

                Connection cn = Conexion.conectar();
                PreparedStatement ps = cn.prepareStatement(
                    "SELECT p.descripcion, i.stock " +
                    "FROM Inventario i " +
                    "INNER JOIN Producto p ON p.id = i.idProducto " +
                    "WHERE i.idProducto = ?"
                );
                ps.setString(1, idProducto);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String descripcionProducto = rs.getString("descripcion");
                    int stockActual = rs.getInt("stock");

                    if (stockActual <= 0) {
                        productosSuperanStock = true;
                        productosConStockExcedido.add(descripcionProducto + " (Agotado)");
                    }
                } else {
                    productosSuperanStock = true;
                    productosConStockExcedido.add("Producto no encontrado");
                }

                rs.close();
                ps.close();
                cn.close();
            } catch (NumberFormatException ex) {
                productosSuperanStock = true;
                productosConStockExcedido.add("Cantidad inválida");
            }
        }

        Conexion.desconectar(conectar);

        // Compare accumulated quantities with available stock
        for (String idProducto : accumulatedQuantities.keySet()) {
            Connection cn = Conexion.conectar();
            PreparedStatement ps = cn.prepareStatement(
                "SELECT i.stock " +
                "FROM Inventario i " +
                "WHERE i.idProducto = ?"
            );
            ps.setString(1, idProducto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int stockActual = rs.getInt("stock");
                int accumulatedQuantity = accumulatedQuantities.get(idProducto);

                if (stockActual < accumulatedQuantity) {
                    productosSuperanStock = true;
                    productosConStockExcedido.add("ID: " + idProducto + " (Stock insuficiente, disponible: " + stockActual + ")");
                }
            }

            rs.close();
            ps.close();
            cn.close();
        }

       
if (productosSuperanStock) {
    StringBuilder mensajeAlerta = new StringBuilder("Los siguientes productos tienen problemas de stock:\n");

    for (String productoExcedido : productosConStockExcedido) {
        if (productoExcedido.contains("Stock insuficiente")) {
            String[] parts = productoExcedido.split("\\(");  
            if (parts.length >= 2) {
                String nombreProducto = parts[0].trim();
                String disponibilidad = parts[1].replace(")", "").trim();
                mensajeAlerta.append(nombreProducto).append(" ya está sobrepasado. Quedan ").append(disponibilidad).append(". Debe eliminar ").append(disponibilidad).append(".\n");
            } else {
                mensajeAlerta.append(productoExcedido).append("\n");
            }
        } else {
            mensajeAlerta.append(productoExcedido).append("\n");
        }
    }

    JOptionPane.showMessageDialog(this,
        mensajeAlerta.toString() + "\nAjusta las cantidades antes de facturar.", "Problemas de Stock", JOptionPane.WARNING_MESSAGE);
} else {
    // Se cumplen las condiciones, procesar la factura
    processInvoice();
}

    } catch (SQLException ex) {
        System.out.print(ex);
        Logger.getLogger(FacturacionVenta.class.getName()).log(Level.SEVERE, null, ex);
    }
}
   
    public static void setScaledImage(JLabel lblImage, String imageName, int width, int height) {
         
        String imagePath = rutaBase + imageName;
        ImageIcon img = new ImageIcon(imagePath);
        Image scaledImage = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(scaledImage);
        lblImage.setIcon(icon);
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
   }
    
    private void txtIDProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDProductoKeyTyped

    }//GEN-LAST:event_txtIDProductoKeyTyped

    private void LblEliminar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblEliminar1MouseEntered
        LblEliminar1.setBackground(new Color(255,91,94));
    }//GEN-LAST:event_LblEliminar1MouseEntered

    private void LblEliminar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblEliminar1MouseExited
        LblEliminar1.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_LblEliminar1MouseExited

    private void LblAgregar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblAgregar1MouseEntered
        LblAgregar1.setBackground(new Color(144,255,193));
    }//GEN-LAST:event_LblAgregar1MouseEntered

    private void LblAgregar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblAgregar1MouseExited
        LblAgregar1.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_LblAgregar1MouseExited

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed

    String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre IS NULL";
    boolean condicionesCumplidas = false;

    try {
        Connection conectar = Conexion.conectar();
        PreparedStatement pst = conectar.prepareStatement(checkNullDate);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            if (tablaProductosVentas.getRowCount() > 0) {
             /*  if (txtNombreCliente.getText().isEmpty()) {
                    LblAdvertencia.setText("*");
                    LblAdvertencia.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(this, "Debes ingresar el nombre del cliente antes de facturar.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {*/
                    guardarDatos();
                    condicionesCumplidas = true;
               // }
            } else {
                JOptionPane.showMessageDialog(this, "Debes ingresar al menos un producto en la tabla antes de facturar.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            } 
        } else {
            JOptionPane.showMessageDialog(this, "No existe ninguna sesión de caja abierta.\n                  Dirijase a Caja.","Error.",JOptionPane.ERROR_MESSAGE);            
            return;
        }
        Conexion.desconectar(conectar);
    } catch (SQLException ex) {
        System.out.print(ex);
        Logger.getLogger(FacturacionVenta.class.getName()).log(Level.SEVERE, null, ex);
    }  

    if (condicionesCumplidas) { 
        // Se cumplen las condiciones, ahora procesamos la factura
        // VerificacionStock();
        validarFacturacion();
    }

    }//GEN-LAST:event_btnFacturarActionPerformed

    private void IconPDFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IconPDFMouseEntered
        IconPDF.setBackground(new Color(255,91,94));
    }//GEN-LAST:event_IconPDFMouseEntered

    private void IconPDFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IconPDFMouseExited
        IconPDF.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_IconPDFMouseExited

    private void btnCotizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCotizarActionPerformed
              
       if (txtNombreCliente.getText().isEmpty()) {
        LblAdvertencia.setText("*");
        LblAdvertencia.setForeground(Color.RED);
        JOptionPane.showMessageDialog(this, "Debes ingresar el nombre del cliente antes de Cotizar.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
    } else if (tablaProductosVentas.getRowCount() > 0) {
         Cotizar();
    } else {
        LblAdvertencia.setText("");
        JOptionPane.showMessageDialog(this, "Debes ingresar al menos un producto en la tabla antes de Cotizar.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
        
    }//GEN-LAST:event_btnCotizarActionPerformed

    private void LblAgregar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblAgregar1MouseClicked
       //agregarATabla();
       VerificacionStock();
       guardarDatos();
      // validarFacturacion();
    }//GEN-LAST:event_LblAgregar1MouseClicked

    private void LblEliminar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblEliminar1MouseClicked
        EliminarFila();
    }//GEN-LAST:event_LblEliminar1MouseClicked

    private void txtCantidadProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyTyped
       char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
            //agregarATabla();
            VerificacionStock();
            guardarDatos();
            
        }
    }//GEN-LAST:event_txtCantidadProductoKeyTyped

    private void montoIngresadoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_montoIngresadoCaretUpdate
       String montoIngresadoTxt = montoIngresado.getText(); // Suponiendo que campoTextoMonto es el JTextField
    if (!montoIngresadoTxt.isEmpty()) {
        double montoIngresado = Double.parseDouble(montoIngresadoTxt);
        DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
        calcularYActualizarDevuelta(model, montoIngresado, LblDevuelta);
    }
    }//GEN-LAST:event_montoIngresadoCaretUpdate

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped

    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
   
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void IconPDFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IconPDFMouseClicked
         
        if (txtNombreCliente.getText().isEmpty()) {
        LblAdvertencia.setText("*");
        LblAdvertencia.setForeground(Color.RED);
        JOptionPane.showMessageDialog(this, "Debes ingresar el nombre del cliente antes de Imprimir un PDF.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
    } else if (tablaProductosVentas.getRowCount() > 0) {
         PDF();
    } else {
        LblAdvertencia.setText("");
        JOptionPane.showMessageDialog(this, "Debes ingresar al menos un producto en la tabla antes de  Imprimir un PDF.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_IconPDFMouseClicked

    
    //Calcular
    
    private void montoIngresadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoIngresadoKeyTyped
        char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
            guardarDatos();
             btnFacturar.doClick();
             
            
        }
    }//GEN-LAST:event_montoIngresadoKeyTyped
    
    public void calcularYActualizarDevuelta(DefaultTableModel model, double montoIngresado, JLabel LblDevuelta) {
    double total = 0.0;

    for (int i = 0; i < model.getRowCount(); i++) {
        float totalFloat = (float) model.getValueAt(i, 5);
        total += totalFloat;
    }

    double devuelta = montoIngresado - total;
    LblDevuelta.setText(String.format("%.2f", devuelta).replace(",", "."));
    }
    
    public void processInvoice() {

        if (!sesionCajaAbierta()) {
            System.out.println("Debe abrir la caja primero antes de hacer una venta.");
            return;
        }

        float totalProductos = 0;  // Inicializar el total de productos
            DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
            for (int row = 0; row < model.getRowCount(); row++) {
                totalProductos += Float.parseFloat(model.getValueAt(row, 5).toString()); // Sumar el costo total de cada producto
            }

            // Calcular el total recibido y el total devuelto      
            String montoIngresadoTx = montoIngresado.getText();
            if (montoIngresadoTx.matches("\\d+")) {
                int montoIngresadoInt = Integer.parseInt(montoIngresadoTx);

                float Devuelto = montoIngresadoInt - totalProductos;
                
                if (Devuelto >= 0){
                     int confirmation = JOptionPane.showConfirmDialog(null, "¿Está seguro de facturar?", "Confirmación de Facturación", JOptionPane.YES_NO_OPTION);
                        if (confirmation == JOptionPane.YES_OPTION) {
                            realizarVenta();
                            generateAndSendInvoicePDF();
                                                       
                            //CleanAll();
                        }

                } else if (Devuelto < 0) {
                        System.out.println("La suma es incompleta, esto podría generar una deuda.");
                        int deudaConfirmation = JOptionPane.showConfirmDialog(null, "¿Desea abrir la instancia de deuda?", "Deuda", JOptionPane.YES_NO_OPTION);

                        if (deudaConfirmation == JOptionPane.YES_OPTION) {
//                            DeudaClientes DC = new DeudaClientes();
//                            DC.setVisible(true);
                              mostrarDeuda(Devuelto, montoIngresadoInt, txtNombreCliente.getText(), LblIDVenta.getText());

                           // mostrarDeuda(totalProductos, montoIngresadoInt);
                        }
                        }
            } else {
                     JOptionPane.showMessageDialog(null, "Ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                   }
    }

    
    //idGene
    
    public void setIDVenta(String id) {
        LblIDVenta.setText(id);
    } 
    
    
    //Facturacion en PDF
    
    public static void generateAndSendInvoicePDF() {
        System.out.print("LblIDVenta");
    String idVenta = LblIDVenta.getText();
    String nombrCliente = txtNombreCliente.getText();
    String nombreArchivo = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "Factura_" + nombrCliente + ".pdf";
     // Verificar si hay una factura abierta y cerrarla o cambiar el nombre
        cerrarFacturaAbierta(nombreArchivo);

   float totalProductos = 0;  // Inicializar el total de productos
    DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
    for (int row = 0; row < model.getRowCount(); row++) {
        totalProductos += Float.parseFloat(model.getValueAt(row, 5).toString()); // Sumar el costo total de cada producto
    }

    try {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
        document.open();

        // Add your code for generating the PDF content here
        
        // Add the logo in the top-left corner
        com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("src\\imgnew\\ParadaLogo.png");
        logo.scaleToFit(50, 50);
        logo.setAlignment(Element.ALIGN_LEFT);
        document.add(logo);

        // Create title
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.UNDERLINE);
        Paragraph titulo = new Paragraph("Factura", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        // Invoice information
        document.add(new Paragraph("Número de factura:" + idVenta));
        document.add(new Paragraph("Fecha: " + formattedFechaHora));
        document.add(new Paragraph("Cliente:" + nombrCliente));

        // Create table for product details
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("ID Producto"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombre Producto"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Tamaño"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Precio"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Cantidad"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Costo Total"));
        table.addCell(cell);

        // Add products to the table
        for (int row = 0; row < model.getRowCount(); row++) {
            table.addCell((String) model.getValueAt(row, 0)); // ID Producto
            table.addCell((String) model.getValueAt(row, 1)); // Nombre Producto
            table.addCell((String) model.getValueAt(row, 2)); // Tamaño
            table.addCell("$" + model.getValueAt(row, 3));   // Precio
            table.addCell(model.getValueAt(row, 4).toString()); // Cantidad
            table.addCell("$" + model.getValueAt(row, 5)); // Costo Total
        }

        document.add(table);

        // Agregar el total de productos al PDF
        Paragraph totalProductosParagraph = new Paragraph("Total de productos: $" + totalProductos);
        totalProductosParagraph.setAlignment(Element.ALIGN_RIGHT);
        totalProductosParagraph.setSpacingBefore(20f);
        document.add(totalProductosParagraph);

              
        // Calcular el total recibido y el total devuelto      
        String montoIngresadoTx = montoIngresado.getText();
        int montoIngresadoInt = Integer.parseInt(montoIngresadoTx);
        System.out.print("nada o " + montoIngresadoInt);

        float Devuelto = montoIngresadoInt - totalProductos;

        // Agregar el total recibido y el total devuelto al PDF
        String montoIngresadoTxt = montoIngresado.getText();
        Paragraph totalRecibidoParagraph = new Paragraph("Total Recibido: $" + montoIngresadoTxt);
        totalRecibidoParagraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(totalRecibidoParagraph);

        Paragraph totalDevueltoParagraph = new Paragraph("Total Devuelto: $" + Devuelto);
        totalDevueltoParagraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(totalDevueltoParagraph);


        document.close();
        writer.close();

        System.out.println("Factura generada exitosamente en " + nombreArchivo);

        // Open the invoice automatically
        abrirFactura(nombreArchivo);
        
        // Send the invoice by email
        String remitente = "angelo.ferreras23@gmail.com";
        String contraseña = "ixptbjicfedqabod";
        String destinatario = txtCorreo.getText();
        String asunto = "Factura Adjunta";
        String mensaje = "Estimado cliente, adjuntamos la factura en PDF de su compra en parada fria.";

        enviarFacturaPorCorreo(remitente, contraseña, destinatario, asunto, mensaje, nombreArchivo);
    } catch (DocumentException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    private static void cerrarFacturaAbierta(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists() && archivo.canWrite()) {
                archivo.renameTo(new File(nombreArchivo + "_cerrada.pdf")); // Cambiar el nombre del archivo
                System.out.println("Factura cerrada o archivo renombrado: " + nombreArchivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void abrirFactura(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                if (!archivo.canWrite()) {
                    System.out.println("El archivo está siendo utilizado por otro proceso. Cerrando y abriendo nuevamente.");
                    Desktop.getDesktop().open(archivo);
                } else {
                    Desktop.getDesktop().open(archivo);
                }
            } else {
                System.out.println("El archivo no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CleanCorreos(){
       
        txtNombreCliente.setText("");
        txtCorreo.setText("");

    }
    
    public static void enviarFacturaPorCorreo(String remitente, String contraseña, String destinatario,
                                              String asunto, String mensaje, String archivoAdjunto) {
        final String host = "smtp.gmail.com";
        final int port = 587;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Iniciar sesión de correo
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, contraseña);
            }
        });

        try {
            // Validar la dirección de correo electrónico del destinatario
            if (!isValidEmailAddress(destinatario)) {
                String errorMessage = "La dirección de correo electrónico del destinatario no es válida.";
                System.err.println(errorMessage);
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear mensaje de correo
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));

            // Validar y configurar la dirección de correo del destinatario
            InternetAddress recipientAddress;
            try {
                recipientAddress = new InternetAddress(destinatario);
                message.setRecipient(Message.RecipientType.TO, recipientAddress);
            } catch (AddressException e) {
                String errorMessage = "La dirección de correo electrónico del destinatario no es válida.";
                System.err.println(errorMessage);
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return;
            }

            message.setSubject(asunto);

            // Crear parte de texto del correo
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(mensaje);

            // Crear parte del archivo adjunto
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(archivoAdjunto);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(new File(archivoAdjunto).getName());

            // Combinar partes en un multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            // Configurar contenido del mensaje
            message.setContent(multipart);

            // Enviar el mensaje
            System.out.println("Enviando correo a: " + destinatario);
            Transport.send(message);
            System.out.println("Correo enviado exitosamente con el archivo adjunto.");
           // JOptionPane.showMessageDialog(null, "Correo enviado exitosamente con el archivo adjunto.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            CleanCorreos();
        } catch (MessagingException e) {
            String errorMessage = "Error al enviar el correo:\n" + e.getMessage();
            System.err.println(errorMessage);

            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static boolean isValidEmailAddress(String email) {
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    public static void PDF() {
    String idVenta = LblIDVenta.getText();
    String nombrCliente = txtNombreCliente.getText();
    String nombreArchivo = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "Factura_" + nombrCliente + ".pdf";

    float totalProductos = 0;  // Inicializar el total de productos
    DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
    for (int row = 0; row < model.getRowCount(); row++) {
        totalProductos += Float.parseFloat(model.getValueAt(row, 5).toString()); // Sumar el costo total de cada producto
    }

    try {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
        document.open();

        // Add your code for generating the PDF content here
        
        // Add the logo in the top-left corner
        com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("src\\imgnew\\ParadaLogo.png");
        logo.scaleToFit(50, 50);
        logo.setAlignment(Element.ALIGN_LEFT);
        document.add(logo);

        // Create title
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.UNDERLINE);
        Paragraph titulo = new Paragraph("Factura", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        // Invoice information
        document.add(new Paragraph("Número de factura:" + idVenta));
        document.add(new Paragraph("Fecha: " + formattedFechaHora));
        document.add(new Paragraph("Cliente:" + nombrCliente));

        // Create table for product details
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("ID Producto"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombre Producto"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Tamaño"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Precio"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Cantidad"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Costo Total"));
        table.addCell(cell);

        // Add products to the table
        for (int row = 0; row < model.getRowCount(); row++) {
            table.addCell((String) model.getValueAt(row, 0)); // ID Producto
            table.addCell((String) model.getValueAt(row, 1)); // Nombre Producto
            table.addCell((String) model.getValueAt(row, 2)); // Tamaño
            table.addCell("$" + model.getValueAt(row, 3));   // Precio
            table.addCell(model.getValueAt(row, 4).toString()); // Cantidad
            table.addCell("$" + model.getValueAt(row, 5)); // Costo Total
        }

        document.add(table);

        // Agregar el total de productos al PDF
        Paragraph totalProductosParagraph = new Paragraph("Total de productos: $" + totalProductos);
        totalProductosParagraph.setAlignment(Element.ALIGN_RIGHT);
        totalProductosParagraph.setSpacingBefore(20f);
        document.add(totalProductosParagraph);

        // Calcular el total recibido y el total devuelto      
//        String montoIngresadoTx = montoIngresado.getText();
//        int montoIngresadoInt = Integer.parseInt(montoIngresadoTx);
//
//        float Devuelto = montoIngresadoInt - totalProductos;

        // Agregar el total recibido y el total devuelto al PDF
//        String montoIngresadoTxt = montoIngresado.getText();
//        Paragraph totalRecibidoParagraph = new Paragraph("Total Recibido: $" + montoIngresadoTxt);
//        totalRecibidoParagraph.setAlignment(Element.ALIGN_RIGHT);
//        document.add(totalRecibidoParagraph);

        //Paragraph totalDevueltoParagraph = new Paragraph("Total Devuelto: $" + Devuelto);
//        totalDevueltoParagraph.setAlignment(Element.ALIGN_RIGHT);
//        document.add(totalDevueltoParagraph);


        document.close();
        writer.close();

        System.out.println("Factura generada exitosamente en " + nombreArchivo);

        // Open the invoice automatically
        abrirFactura(nombreArchivo);
       // CleanAll();

        // Print the PDF
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        if (defaultPrintService != null) {
            FileInputStream inputStream = new FileInputStream(nombreArchivo);
            Doc pdfDoc = new SimpleDoc(inputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            printJob.print(pdfDoc, null);
            inputStream.close();
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró una impresora disponible.", "Error de Impresión", JOptionPane.ERROR_MESSAGE);
        }

    } catch (DocumentException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public static void Cotizar() {
    String idVenta = LblIDVenta.getText();
    String nombrCliente = txtNombreCliente.getText();
    String nombreArchivo = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "Factura_" + nombrCliente + ".pdf";

    float totalProductos = 0;  // Inicializar el total de productos
    DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
    for (int row = 0; row < model.getRowCount(); row++) {
        totalProductos += Float.parseFloat(model.getValueAt(row, 5).toString()); // Sumar el costo total de cada producto
    }

    try {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
        document.open();

        // Add your code for generating the PDF content here
        
        // Add the logo in the top-left corner
        com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("src\\imgnew\\ParadaLogo.png");
        logo.scaleToFit(50, 50);
        logo.setAlignment(Element.ALIGN_LEFT);
        document.add(logo);

        // Create title
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.UNDERLINE);
        Paragraph titulo = new Paragraph("Factura", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        // Invoice information
        document.add(new Paragraph("Número de factura:" + idVenta));
        document.add(new Paragraph("Fecha: " + formattedFechaHora));
        document.add(new Paragraph("Cliente:" + nombrCliente));

        // Create table for product details
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("ID Producto"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombre Producto"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Tamaño"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Precio"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Cantidad"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Costo Total"));
        table.addCell(cell);

        // Add products to the table
        for (int row = 0; row < model.getRowCount(); row++) {
            table.addCell((String) model.getValueAt(row, 0)); // ID Producto
            table.addCell((String) model.getValueAt(row, 1)); // Nombre Producto
            table.addCell((String) model.getValueAt(row, 2)); // Tamaño
            table.addCell("$" + model.getValueAt(row, 3));   // Precio
            table.addCell(model.getValueAt(row, 4).toString()); // Cantidad
            table.addCell("$" + model.getValueAt(row, 5)); // Costo Total
        }

        document.add(table);

        // Agregar el total de productos al PDF
        Paragraph totalProductosParagraph = new Paragraph("Total de productos: $" + totalProductos);
        totalProductosParagraph.setAlignment(Element.ALIGN_RIGHT);
        totalProductosParagraph.setSpacingBefore(20f);
        document.add(totalProductosParagraph);

        // Calcular el total recibido y el total devuelto      
//        String montoIngresadoTx = montoIngresado.getText();
//        int montoIngresadoInt = Integer.parseInt(montoIngresadoTx);
//
//        float Devuelto = montoIngresadoInt - totalProductos;

        // Agregar el total recibido y el total devuelto al PDF
//        String montoIngresadoTxt = montoIngresado.getText();
//        Paragraph totalRecibidoParagraph = new Paragraph("Total Recibido: $" + montoIngresadoTxt);
//        totalRecibidoParagraph.setAlignment(Element.ALIGN_RIGHT);
//        document.add(totalRecibidoParagraph);

        //Paragraph totalDevueltoParagraph = new Paragraph("Total Devuelto: $" + Devuelto);
//        totalDevueltoParagraph.setAlignment(Element.ALIGN_RIGHT);
//        document.add(totalDevueltoParagraph);


        document.close();
        writer.close();

        System.out.println("Factura generada exitosamente en " + nombreArchivo);

        // Open the invoice automatically
        abrirFactura(nombreArchivo);
        //CleanAll();
        
        // Send the invoice by email
        String remitente = "angelo.ferreras23@gmail.com";
        String contraseña = "ixptbjicfedqabod";
        String destinatario = txtCorreo.getText();
        String asunto = "Factura Adjunta";
        String mensaje = "Estimado cliente, adjuntamos la factura en PDF de su compra en parada fria.";

        enviarFacturaPorCorreo(remitente, contraseña, destinatario, asunto, mensaje, nombreArchivo);
 
        // Print the PDF
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        if (defaultPrintService != null) {
            FileInputStream inputStream = new FileInputStream(nombreArchivo);
            Doc pdfDoc = new SimpleDoc(inputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            printJob.print(pdfDoc, null);
            inputStream.close();
        } else {
            System.out.println("No se encontró una impresora disponible.");
        }
    } catch (DocumentException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    
    
    //base data
    
    private void realizarVenta() {
    String idVenta = LblIDVenta.getText();
    String nombreCliente = txtNombreCliente.getText();
    calcularTotal();
   // float totalV = total;
   
    //System.out.print(totalV);
    float totalV = Float.parseFloat(LblMontoPagar.getText());
    String conceptoIngreso = "Venta de Productos: , " + idVenta;
    System.out.print(totalV);

    String consulta = "SELECT id FROM venta WHERE id = ?";
    String ventaQuery = "INSERT INTO venta (id, nombreCliente, total, fecha) VALUES (?,?,?,?)";
    String detalleVentaQuery = "INSERT INTO detalleventa (idVenta, idProducto, cantidadP) VALUES (?,?,?)";
    String ingresoQuery = "INSERT INTO ingresos (concepto, monto, fecha) VALUES (?,?,?)";
   // String updateProductoQuery = "UPDATE producto SET cantidad = cantidad - ? WHERE id = ? AND cantidad >= ?";

    // Agregar enviar datos a la tabla ingresos en la base de datos

    try {
        Connection conectar = Conexion.conectar();
        PreparedStatement pst = conectar.prepareStatement(consulta);
        pst.setString(1, idVenta);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "Este ID ya existe, por favor intentelo denuevo.");
            return;
        } else {
            // Begin a transaction
            conectar.setAutoCommit(false);

            try {
                // Insert into venta table
                PreparedStatement pst2 = conectar.prepareStatement(ventaQuery);
                pst2.setString(1, idVenta);
                pst2.setString(2, nombreCliente);
                pst2.setFloat(3, totalV);
                pst2.setTimestamp(4, Timestamp.valueOf(fechaHoraActua));
                pst2.executeUpdate();

                // Insert into ingresos table
                PreparedStatement pst4 = conectar.prepareStatement(ingresoQuery);
                pst4.setString(1, conceptoIngreso);
                pst4.setFloat(2, totalV);
                pst4.setTimestamp(3, Timestamp.valueOf(fechaHoraActua));
                pst4.executeUpdate();

                // Insert into detalleventa table and update inventory
                DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
                for (int row = 0; row < model.getRowCount(); row++) {
                    String productoID = (String) model.getValueAt(row, 0);
                    Object cantidadObject = model.getValueAt(row, 4);  // Obtener el valor de la columna 4

                    // Verificar si el valor es una cadena válida y numérica
                    int cantidad = 0;  // Valor predeterminado si no se puede convertir
                    if (cantidadObject != null && cantidadObject instanceof String) {
                        String cantidadStr = (String) cantidadObject;
                        if (!cantidadStr.isEmpty()) {
                            try {
                                cantidad = Integer.parseInt(cantidadStr);
                            } catch (NumberFormatException e) {
                                // Manejar la excepción si la conversión falla
                                e.printStackTrace();
                                // También podrías mostrar un mensaje de error al usuario aquí
                            }
                        }
                    }
                    PreparedStatement pst3 = conectar.prepareStatement(detalleVentaQuery);
                    pst3.setString(1, idVenta);
                    pst3.setString(2, productoID);
                    pst3.setInt(3, cantidad);
                    pst3.executeUpdate();

                    // Update inventory
//                    PreparedStatement pstUpdate = conectar.prepareStatement(updateProductoQuery);
//                    pstUpdate.setInt(1, cantidad);
//                    pstUpdate.setString(2, productoID);
//                    pstUpdate.setInt(3, cantidad);
//                    pstUpdate.executeUpdate();  // Esta línea ya no debería causar un error
                }

                // Commit the transaction
                conectar.commit();
                   confirmarLimpieza(true);
 

            } catch (SQLException ex) {
                // Rollback the transaction on error
                conectar.rollback();
                throw ex;
            } finally {
                // Always set auto-commit back to true
                conectar.setAutoCommit(true);
            }
        }
        
       
        Conexion.desconectar(conectar);
    } catch (SQLException ex) {
        Logger.getLogger(FacturacionVenta.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
    
    public void confirmarLimpieza(boolean shouldClean) {
    if (shouldClean) {
        // Crear un temporizador que ejecute el código después de 3 segundos (3000 ms)
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                CleanAll();
                CleanCorreos();
                borrarDatos();
                String idV = UniqueIDGenerator.generateUniqueID();
                LblIDVenta.setText(idV);
  
            }
        });

        // Iniciar el temporizador
        timer.setRepeats(false); // Para que solo se ejecute una vez
        timer.start();
        }
    }
   

    public void metodoB() {
        System.out.println("Método Clean activado.");
        confirmarLimpieza(true);
    }





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Agregaralalista;
    private javax.swing.JPanel ContentPanelVenta;
    private javax.swing.JLabel Eliminardelalista;
    private javax.swing.JLabel IconPDF;
    public static javax.swing.JLabel LblAdvertencia;
    private javax.swing.JLabel LblAgregar1;
    private javax.swing.JLabel LblCantidad;
    private javax.swing.JLabel LblCantidad1;
    private javax.swing.JLabel LblCantidad2;
    private javax.swing.JLabel LblDetalle;
    private static javax.swing.JLabel LblDevuelta;
    private javax.swing.JLabel LblDevueltaMonto;
    private javax.swing.JLabel LblEliminar1;
    private javax.swing.JLabel LblIDProducto;
    public static javax.swing.JLabel LblIDVenta;
    private javax.swing.JLabel LblMontoPagado;
    public static javax.swing.JLabel LblMontoPagar;
    private javax.swing.JLabel LblNombre;
    private static javax.swing.JLabel LblNombreProducto;
    private javax.swing.JLabel LblPrecio;
    private static javax.swing.JLabel LblPrecioProducto;
    private javax.swing.JLabel LblSize;
    private static javax.swing.JLabel LblSizeProducto;
    private javax.swing.JLabel LblTotalPagar;
    private javax.swing.JLabel LblUtilidad4;
    private javax.swing.JPanel PanelContenido;
    private javax.swing.JScrollPane ScrollPanelVenta;
    private rojerusan.RSMaterialButtonRectangle btnCotizar;
    private rojerusan.RSMaterialButtonRectangle btnFacturar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTextField montoIngresado;
    private javax.swing.JLabel pdf;
    public static javax.swing.JTable tablaProductosVentas;
    private static javax.swing.JTextField txtCantidadProducto;
    public static javax.swing.JTextField txtCorreo;
    private static javax.swing.JTextField txtIDProducto;
    public static javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
