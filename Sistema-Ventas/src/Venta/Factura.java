
package Venta;

import Connect.Conexion;
import static Venta.Facturacion.LblIDVenta;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Angelo
 */
public class Factura extends javax.swing.JDialog {

    private static int montoRecibido; // Campo estático para el monto recibido

    private float total = 0;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final String formattedFechaHora = LocalDateTime.now().format(formatter);

    
    
    public Factura(java.awt.Frame parent, boolean modal, int montoRecibido) {
        super(parent, modal);
        initComponents();
        this.montoRecibido = montoRecibido;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContentDialog = new javax.swing.JPanel();
        ScrollFactura = new javax.swing.JScrollPane();
        TablaFacturacion = new javax.swing.JTable();
        BtnPrint = new javax.swing.JButton();
        LblTtituloFactura = new javax.swing.JLabel();
        LblFecha = new javax.swing.JLabel();
        IconLogo = new javax.swing.JLabel();
        LblTotal = new javax.swing.JLabel();
        LblTotalFactura = new javax.swing.JLabel();
        LblNombre = new javax.swing.JLabel();
        LblFechaFactura = new javax.swing.JLabel();
        LblHoraFactura = new javax.swing.JLabel();
        lblVenta = new javax.swing.JLabel();
        LblIDVenta = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        LblHora1 = new javax.swing.JLabel();
        LblNombre1 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ContentDialog.setBackground(new java.awt.Color(24, 39, 72));
        ContentDialog.setMaximumSize(new java.awt.Dimension(415, 600));
        ContentDialog.setMinimumSize(new java.awt.Dimension(415, 600));
        ContentDialog.setPreferredSize(new java.awt.Dimension(415, 600));
        ContentDialog.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaFacturacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Tamaño", "Cantidad", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
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
        TablaFacturacion.getTableHeader().setReorderingAllowed(false);
        ScrollFactura.setViewportView(TablaFacturacion);
        if (TablaFacturacion.getColumnModel().getColumnCount() > 0) {
            TablaFacturacion.getColumnModel().getColumn(0).setResizable(false);
            TablaFacturacion.getColumnModel().getColumn(0).setPreferredWidth(6);
            TablaFacturacion.getColumnModel().getColumn(1).setResizable(false);
            TablaFacturacion.getColumnModel().getColumn(1).setPreferredWidth(70);
            TablaFacturacion.getColumnModel().getColumn(2).setResizable(false);
            TablaFacturacion.getColumnModel().getColumn(2).setPreferredWidth(50);
            TablaFacturacion.getColumnModel().getColumn(3).setResizable(false);
            TablaFacturacion.getColumnModel().getColumn(3).setPreferredWidth(4);
            TablaFacturacion.getColumnModel().getColumn(4).setResizable(false);
            TablaFacturacion.getColumnModel().getColumn(4).setPreferredWidth(8);
        }

        ContentDialog.add(ScrollFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 370, 334));

        BtnPrint.setText("PRINT");
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        ContentDialog.add(BtnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, -1, -1));

        LblTtituloFactura.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        LblTtituloFactura.setForeground(new java.awt.Color(255, 255, 255));
        LblTtituloFactura.setText("Factura");
        ContentDialog.add(LblTtituloFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 120, -1));

        LblFecha.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblFecha.setForeground(new java.awt.Color(255, 255, 255));
        LblFecha.setText("Fecha: ");
        ContentDialog.add(LblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 20));

        IconLogo.setForeground(new java.awt.Color(255, 255, 255));
        ContentDialog.add(IconLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 100));

        LblTotal.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblTotal.setForeground(new java.awt.Color(255, 255, 255));
        LblTotal.setText("TOTAL:");
        ContentDialog.add(LblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, -1, 20));

        LblTotalFactura.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblTotalFactura.setForeground(new java.awt.Color(255, 255, 255));
        LblTotalFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblTotalFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ContentDialog.add(LblTotalFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 590, 120, 20));

        LblNombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblNombre.setForeground(new java.awt.Color(255, 255, 255));
        LblNombre.setText("Cliente:");
        ContentDialog.add(LblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, 20));

        LblFechaFactura.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblFechaFactura.setForeground(new java.awt.Color(255, 255, 255));
        LblFechaFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ContentDialog.add(LblFechaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 110, 20));

        LblHoraFactura.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblHoraFactura.setForeground(new java.awt.Color(255, 255, 255));
        LblHoraFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ContentDialog.add(LblHoraFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 110, 20));

        lblVenta.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lblVenta.setForeground(new java.awt.Color(255, 255, 255));
        lblVenta.setText("No. Venta:");
        ContentDialog.add(lblVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 20));

        LblIDVenta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LblIDVenta.setForeground(new java.awt.Color(51, 255, 51));
        LblIDVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ContentDialog.add(LblIDVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 90, 20));

        txtNombreCliente.setBackground(new java.awt.Color(24, 39, 72));
        txtNombreCliente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtNombreCliente.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });
        ContentDialog.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 120, 20));

        LblHora1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblHora1.setForeground(new java.awt.Color(255, 255, 255));
        LblHora1.setText("Hora:");
        ContentDialog.add(LblHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 20));

        LblNombre1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblNombre1.setForeground(new java.awt.Color(255, 255, 255));
        LblNombre1.setText("Correo:");
        ContentDialog.add(LblNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, -1, 20));

        txtCorreo.setBackground(new java.awt.Color(24, 39, 72));
        txtCorreo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(255, 255, 255));
        txtCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtCorreo.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });
        ContentDialog.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 120, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(ContentDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ContentDialog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void database() {
     String idVenta = LblIDVenta.getText();
     float totalV = Float.parseFloat(LblTotalFactura.getText());
     String conceptoIngreso = "Venta de producto";

     String ventaQuery = "INSERT INTO venta (id, total, fecha) VALUES (?,?,?)";
     String detalleVentaQuery = "INSERT INTO detalleventa (idVenta, idProducto, cantidadP) VALUES (?,?,?)";
     String ingresoQuery = "INSERT INTO ingresos (concepto, monto, fecha) VALUES (?,?,?)";

     try (Connection conectar = Conexion.conectar()) {
         // Begin a transaction
         conectar.setAutoCommit(false);

         try {
             // Insert into venta table
             PreparedStatement pst2 = conectar.prepareStatement(ventaQuery);
             pst2.setString(1, idVenta);
             pst2.setFloat(2, totalV);
             pst2.setTimestamp(3, Timestamp.valueOf(formattedFechaHora));
             pst2.executeUpdate();

             // Insert into ingresos table
             PreparedStatement pst4 = conectar.prepareStatement(ingresoQuery);
             pst4.setString(1, conceptoIngreso);
             pst4.setFloat(2, totalV);
             pst4.setTimestamp(3, Timestamp.valueOf(formattedFechaHora));
             pst4.executeUpdate();

             // Insert details into detalleventa table
             DefaultTableModel model = (DefaultTableModel) TablaFacturacion.getModel();
             for (int row = 0; row < model.getRowCount(); row++) {
                 String productoID = (String) model.getValueAt(row, 0);
                 int cantidad = (int) model.getValueAt(row, 2);

                 PreparedStatement pst3 = conectar.prepareStatement(detalleVentaQuery);
                 pst3.setString(1, idVenta);
                 pst3.setString(2, productoID);
                 pst3.setInt(3, cantidad);
                 pst3.executeUpdate();
             }

             // Commit the transaction
             conectar.commit();

             // Clear the table and other fields
             model.setRowCount(0);
             LblTotalFactura.setText("0.00");
             //limpiador();
         } catch (SQLException ex) {
             // Rollback the transaction on error
             conectar.rollback();
             throw ex;
         } finally {
             // Always set auto-commit back to true
             conectar.setAutoCommit(true);
         }
     } catch (SQLException ex) {
         Logger.getLogger(PanelVenta.class.getName()).log(Level.SEVERE, null, ex);
     }
 }

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
            
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
//        char t = evt.getKeyChar();
//        if(t == KeyEvent.VK_ENTER){
//            checkCliente();
//        }
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoKeyTyped

    public void setIDVenta(String id) {
        LblIDVenta.setText(id);
    } 
    
    public void addProducto(String ID, String nombre, String Tamaño, int cantidad, float precio, float totalProducto) {
    DefaultTableModel model = (DefaultTableModel) TablaFacturacion.getModel();
    model.addRow(new Object[]{ID, nombre, Tamaño, cantidad, precio, totalProducto});
    
    total += totalProducto; 
   
    updateTotalLabel();
}


    private void updateTotalLabel() {
        LblTotalFactura.setText(String.format("%.2f", total).replace(",", "."));
    }
    
    

   
    //Facturacion en PDF
    
    public static void generateAndSendInvoicePDF() {
    String idVenta = LblIDVenta.getText();
    String nombrCliente = txtNombreCliente.getText();
    String nombreArchivo = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "Factura_" + nombrCliente + ".pdf";

   float totalProductos = 0;  // Inicializar el total de productos
    DefaultTableModel model = (DefaultTableModel) TablaFacturacion.getModel();
    for (int row = 0; row < model.getRowCount(); row++) {
        totalProductos += Float.parseFloat(model.getValueAt(row, 5).toString()); // Sumar el costo total de cada producto
    }

    try {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
        document.open();

        // Add your code for generating the PDF content here
        
        // Add the logo in the top-left corner
        Image logo = Image.getInstance("src\\imgnew\\ParadaLogo.png");
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
            table.addCell("$" + model.getValueAt(row, 4));   // Precio
            table.addCell(model.getValueAt(row, 3).toString()); // Cantidad
            table.addCell("$" + model.getValueAt(row, 5)); // Costo Total
        }

        document.add(table);

        // Agregar el total de productos al PDF
        Paragraph totalProductosParagraph = new Paragraph("Total de productos: $" + totalProductos);
        totalProductosParagraph.setAlignment(Element.ALIGN_RIGHT);
        totalProductosParagraph.setSpacingBefore(20f);
        document.add(totalProductosParagraph);

        // Calcular el total recibido y el total devuelto
        int totalRecibido = montoRecibido;
        float totalDevuelto = totalRecibido - totalProductos;

        // Agregar el total recibido y el total devuelto al PDF
        Paragraph totalRecibidoParagraph = new Paragraph("Total Recibido: $" + totalRecibido);
        totalRecibidoParagraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(totalRecibidoParagraph);

        Paragraph totalDevueltoParagraph = new Paragraph("Total Devuelto: $" + totalDevuelto);
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
            // Crear mensaje de correo
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
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
            Transport.send(message);
            System.out.println("Correo enviado exitosamente con el archivo adjunto.");
        } catch (MessagingException e) {
            String errorMessage = "Error al enviar el correo:\n" + e.getMessage();
            System.err.println(errorMessage);

            JOptionPane.showMessageDialog(null, errorMessage, "Error al enviar correo", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        generateAndSendInvoicePDF();
    }
  
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnPrint;
    private javax.swing.JPanel ContentDialog;
    private javax.swing.JLabel IconLogo;
    private javax.swing.JLabel LblFecha;
    private javax.swing.JLabel LblFechaFactura;
    private javax.swing.JLabel LblHora1;
    private javax.swing.JLabel LblHoraFactura;
    public static javax.swing.JLabel LblIDVenta;
    private javax.swing.JLabel LblNombre;
    private javax.swing.JLabel LblNombre1;
    private javax.swing.JLabel LblTotal;
    private javax.swing.JLabel LblTotalFactura;
    private javax.swing.JLabel LblTtituloFactura;
    private javax.swing.JScrollPane ScrollFactura;
    public static javax.swing.JTable TablaFacturacion;
    private javax.swing.JLabel lblVenta;
    public static javax.swing.JTextField txtCorreo;
    public static javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
