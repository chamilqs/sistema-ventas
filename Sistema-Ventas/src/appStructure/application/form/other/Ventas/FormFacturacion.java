package appStructure.application.form.other.Ventas;

import Database.Connect.ConexionSingleton;
import Database.DAO.ProductoDAO;
import Database.DAOImpl.ProductoDAOImpl;
import Database.DTO.Producto;
import Database.DTO.Usuario;
import Utils.Tools.UniqueIDGenerator;
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
import java.awt.Color;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samil
 */
public class FormFacturacion extends javax.swing.JPanel {

    private static FormFacturacion instance = null;
    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final String formattedFechaHora = LocalDateTime.now().format(formatter);
    LocalDateTime fechaHoraActua = LocalDateTime.now();    
    Usuario u = null;
    
    // HashMaps para almacenar los valores de los JLabels
    @SuppressWarnings("FieldMayBeFinal")
    private HashMap<String, String> labelValues = new HashMap<>();
    @SuppressWarnings("FieldMayBeFinal")
    private HashMap<String, String> tableValues = new HashMap<>();
    @SuppressWarnings("FieldMayBeFinal")
    private HashMap<Integer, Object[]> tableData = new HashMap<>();
        
    public FormFacturacion() {
        initComponents();
        inicializar();
        
        
        restaurarDatos();
        agregarListeners();        
        
    }
    
        public FormFacturacion(Usuario u) {
        initComponents();
        inicializar();
        
        
        this.u = u;
        restaurarDatos();
        agregarListeners();
        
    }
    
    // Instancia de la clase 
     public static FormFacturacion getInstance() {
        if (instance == null) {
            instance = new FormFacturacion();
        }
        return instance;
    }
    
    public void setIDVenta(String id) {
        LblIDVenta.setText(id);
    } 
    
    public void metodoB() {
        confirmarLimpieza(true);
    }
    private void inicializar() {
                            
        String idV = UniqueIDGenerator.generateUniqueID();
        LblIDVenta.setText(idV);        
        
        iconEliminar.setSvgImage("appStructure/icon/svg/delete.svg", 35, 35);
        iconPDF.setSvgImage("appStructure/icon/svg/pdf.svg", 25, 25);
        iconAgregar.setSvgImage("appStructure/icon/svg/add.svg", 35, 35);
        
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
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
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
                LblNombreProducto.setText("No encontrado");
                LblSizeProducto.setText("");
                LblPrecioProducto.setText("");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormFacturacion.class.getName()).log(Level.SEVERE, null, ex);
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

        try (Connection conectar = c.getConexion();)
              {
            PreparedStatement pst = conectar.prepareStatement(checkNullDate);
            ResultSet rs = pst.executeQuery();
            return rs.next();
            
        } catch (SQLException ex) {
            showMessage("Error en la base de datos: " + ex.getMessage());
            Logger.getLogger(FormFacturacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    private void mostrarDeuda(float devuelta, int MontoR, String nombreCliente, String idVenta) {
        DeudaClientes dc = new DeudaClientes();
        dc.inicializarDeuda(devuelta, MontoR, nombreCliente, idVenta);
        dc.setLocationRelativeTo(this);
        dc.setSize(459, 456);
        dc.setVisible(true);
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

        Connection cn = c.getConexion();
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

        c.closeRS(rs);
        c.closePST(ps);
        c.desconectar();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "La cantidad debe ser un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
    }   catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FormFacturacion.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    private void validarFacturacion() {
    try(Connection conectar = c.getConexion()) {
        
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

                if (accumulatedQuantities.containsKey(idProducto)) {
                    int currentQuantity = accumulatedQuantities.get(idProducto);
                    accumulatedQuantities.put(idProducto, currentQuantity + cantidadIngresada);
                } else {
                    accumulatedQuantities.put(idProducto, cantidadIngresada);
                }

                Connection cn = c.getConexion();
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

                c.closeRS(rs);
                c.closePST(ps);
                c.desconectar();
            } catch (NumberFormatException ex) {
                productosSuperanStock = true;
                productosConStockExcedido.add("Cantidad inválida");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FormFacturacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        c.desconectar();

        // Compare accumulated quantities with available stock
        for (String idProducto : accumulatedQuantities.keySet()) {
            Connection cn = c.getConexion();
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

                c.closeRS(rs);
                c.closePST(ps);
                c.desconectar();
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

    }    catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(FormFacturacion.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    // Hashmaps
    
    private void restaurarDatos() {
    restaurarLabelValues();
    restaurarTablaData();
}
    private void guardarDatos() {
        guardarLabelValues();
        guardarTablaData();
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
    
    private void guardarLabelValues() {
        //labelValues.put("LblIDVenta", LblIDVenta.getText());
        labelValues.put("LblNombreProducto", LblNombreProducto.getText());
        labelValues.put("LblSizeProducto", LblSizeProducto.getText());
        labelValues.put("LblPrecioProducto", LblPrecioProducto.getText());

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
    private void restaurarLabelValues() {

        LblNombreProducto.setText(labelValues.get("LblNombreProducto"));
        LblSizeProducto.setText(labelValues.get("LblSizeProducto"));
        LblPrecioProducto.setText(labelValues.get("LblPrecioProducto"));

    }
    private void restaurarTablaData() {
        DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
        model.setRowCount(0);

        for (Object[] rowData : tableData.values()) {
            model.addRow(rowData);
        }
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

        if (Devuelto >= 0) {
            int confirmation = JOptionPane.showConfirmDialog(null, "¿Está seguro de facturar?", "Confirmación de Facturación", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                realizarVenta();
                generateAndSendInvoicePDF();
                // CleanAll();
            }
        } else if (Devuelto < 0) {
            System.out.println("La suma es incompleta, esto podría generar una deuda.");
            int deudaConfirmation = JOptionPane.showConfirmDialog(null, "Se generara una deuda, ¿desea continuar?", "Deuda", JOptionPane.YES_NO_OPTION);

            if (deudaConfirmation == JOptionPane.YES_OPTION) {
                String descripcion = JOptionPane.showInputDialog(null, "Ingrese una descripción para la deuda:", "Descripción de Deuda", JOptionPane.QUESTION_MESSAGE);

                if (descripcion != null && !descripcion.isEmpty()) {
                   // mostrarDeuda(Devuelto, montoIngresadoInt, txtNombreCliente.getText(), LblIDVenta.getText());
                    guardar(descripcion); // Llama al método guardar con la descripción
                    generateAndSendInvoicePDF();
                } else {
                    JOptionPane.showMessageDialog(null, "La descripción es requerida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void guardar(String descripcion) {
            
    String idVenta = LblIDVenta.getText();
    String nombreCliente = txtNombreCliente.getText();
    calcularTotal();
    float totalI = Float.parseFloat(montoIngresado.getText());
    
    float totald = Float.parseFloat(LblDevuelta.getText());
    float deudapositivo = Math.abs(totald);

    
    String conceptoIngreso = "Venta de Productos: , " + idVenta;

    String consulta = "SELECT id FROM venta WHERE id = ?";
    String ventaQuery = "INSERT INTO venta (id, nombreCliente, total, fecha) VALUES (?,?,?,?)";
    String detalleVentaQuery = "INSERT INTO detalleventa (idVenta, idProducto, cantidadP) VALUES (?,?,?)";
    String ingresoQuery = "INSERT INTO ingresos (concepto, monto, fecha) VALUES (?,?,?)";
    String cuentaporcobrarQuery = "INSERT INTO cuentaporcobrar (idVenta, deuda, totalFaltante, fecha, descripcion) VALUES (?,?,?,?,?)";

    try {
        Connection conectar = c.getConexion();
        PreparedStatement pst = conectar.prepareStatement(consulta);
        pst.setString(1, idVenta);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "Este ID ya existe, por favor inténtelo de nuevo.");
            return;
        } else {
            c.desactivarAutoCommit();

            try {
                PreparedStatement pst2 = conectar.prepareStatement(ventaQuery);
                pst2.setString(1, idVenta);
                pst2.setString(2, nombreCliente);
                pst2.setFloat(3, totalI);
                pst2.setTimestamp(4, Timestamp.valueOf(fechaHoraActua));
                pst2.executeUpdate();

                PreparedStatement pst4 = conectar.prepareStatement(ingresoQuery);
                pst4.setString(1, conceptoIngreso);
                pst4.setFloat(2, totalI);
                pst4.setTimestamp(3, Timestamp.valueOf(fechaHoraActua));
                pst4.executeUpdate();

                DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
                for (int row = 0; row < model.getRowCount(); row++) {
                    String productoID = (String) model.getValueAt(row, 0);
                    Object cantidadObject = model.getValueAt(row, 4);
                    int cantidad = 0;  // Valor predeterminado si no se puede convertir
                    if (cantidadObject != null && cantidadObject instanceof String) {
                        String cantidadStr = (String) cantidadObject;
                        if (!cantidadStr.isEmpty()) {
                            try {
                                cantidad = Integer.parseInt(cantidadStr);
                            } catch (NumberFormatException e) {
                                System.out.println(e);
                            }
                        }
                    }
                    PreparedStatement pst3 = conectar.prepareStatement(detalleVentaQuery);
                    pst3.setString(1, idVenta);
                    pst3.setString(2, productoID);
                    pst3.setInt(3, cantidad);
                    pst3.executeUpdate();
                }

                PreparedStatement pst5 = conectar.prepareStatement(cuentaporcobrarQuery);
                pst5.setString(1, idVenta);
                pst5.setFloat(2, deudapositivo);
                pst5.setFloat(3, deudapositivo);
                pst5.setTimestamp(4, Timestamp.valueOf(fechaHoraActua));
                pst5.setString(5, descripcion);
                pst5.executeUpdate();

                c.commit();
                confirmarLimpieza(true);

            } catch (SQLException ex) {
                conectar.rollback();
                throw ex;
            } finally {
                conectar.setAutoCommit(true);
            }
        }

        c.desconectar();
    } catch (SQLException | ClassNotFoundException ex) {
        System.out.println(ex);
        Logger.getLogger(FormFacturacion.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    private void realizarVenta() {
    String idVenta = LblIDVenta.getText();
    String nombreCliente = txtNombreCliente.getText();
    calcularTotal();
   
    float totalV = Float.parseFloat(LblMontoPagar.getText());
    String conceptoIngreso = "Venta de Productos: , " + idVenta;
    System.out.print(totalV);

    String consulta = "SELECT id FROM venta WHERE id = ?";
    String ventaQuery = "INSERT INTO venta (id, nombreCliente, total, fecha) VALUES (?,?,?,?)";
    String detalleVentaQuery = "INSERT INTO detalleventa (idVenta, idProducto, cantidadP) VALUES (?,?,?)";
    String ingresoQuery = "INSERT INTO ingresos (concepto, monto, fecha) VALUES (?,?,?)";

    try {
        Connection conectar = c.getConexion();
        PreparedStatement pst = conectar.prepareStatement(consulta);
        pst.setString(1, idVenta);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "Este ID ya existe, por favor intentelo denuevo.");
            return;
        } else {
            c.desactivarAutoCommit();

            try {

                PreparedStatement pst2 = conectar.prepareStatement(ventaQuery);
                pst2.setString(1, idVenta);
                pst2.setString(2, nombreCliente);
                pst2.setFloat(3, totalV);
                pst2.setTimestamp(4, Timestamp.valueOf(fechaHoraActua));
                pst2.executeUpdate();

                PreparedStatement pst4 = conectar.prepareStatement(ingresoQuery);
                pst4.setString(1, conceptoIngreso);
                pst4.setFloat(2, totalV);
                pst4.setTimestamp(3, Timestamp.valueOf(fechaHoraActua));
                pst4.executeUpdate();

                DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
                for (int row = 0; row < model.getRowCount(); row++) {
                    String productoID = (String) model.getValueAt(row, 0);
                    Object cantidadObject = model.getValueAt(row, 4);  

                    int cantidad = 0;  
                    if (cantidadObject != null && cantidadObject instanceof String) {
                        String cantidadStr = (String) cantidadObject;
                        if (!cantidadStr.isEmpty()) {
                            try {
                                cantidad = Integer.parseInt(cantidadStr);
                            } catch (NumberFormatException e) {
                                System.out.println(e);
                            }
                        }
                    }
                    PreparedStatement pst3 = conectar.prepareStatement(detalleVentaQuery);
                    pst3.setString(1, idVenta);
                    pst3.setString(2, productoID);
                    pst3.setInt(3, cantidad);
                    pst3.executeUpdate();

                }

                c.commit();
                confirmarLimpieza(true); 

            } catch (SQLException ex) {
                c.rollback();
                throw ex;
            } finally {
                // Always set auto-commit back to true
                conectar.setAutoCommit(true);
            }
        }
        
        c.desconectar();
    } catch (SQLException | ClassNotFoundException ex) {
        System.out.println(ex);
        Logger.getLogger(FormFacturacion.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
    public void confirmarLimpieza(boolean shouldClean) {
    if (shouldClean) {
        // Crear un temporizador que ejecute el código después de 3 segundos (3000 ms)
        Timer timer = new Timer(3000, (ActionEvent e) -> {
            CleanAll();
            CleanCorreos();
            borrarDatos();
            String idV = UniqueIDGenerator.generateUniqueID();
            LblIDVenta.setText(idV);
        });

        // Iniciar el temporizador
        timer.setRepeats(false); // Para que solo se ejecute una vez
        timer.start();
        }
    }
    public void borrarDatos() {
        borrarLabelValues();
        borrarTablaData();
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

        com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("src\\img\\iconSell.png");
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
            table.addCell((String) model.getValueAt(row, 0)); 
            table.addCell((String) model.getValueAt(row, 1)); 
            table.addCell((String) model.getValueAt(row, 2)); 
            table.addCell("$" + model.getValueAt(row, 3));   
            table.addCell(model.getValueAt(row, 4).toString()); 
            table.addCell("$" + model.getValueAt(row, 5)); 
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

        // Open the invoice automatically
        abrirFactura(nombreArchivo);
        
        // Send the invoice by email
        String remitente = "devsamil26@gmail.com";
        String contraseña = "agotjuuiihruwbod";
        String destinatario = txtCorreo.getText();
        String asunto = "Factura Adjunta";
        String mensaje = "Estimado cliente, adjuntamos la factura en PDF de su compra. ¡Gracias por preferirnos!";

        enviarFacturaPorCorreo(remitente, contraseña, destinatario, asunto, mensaje, nombreArchivo);
    } catch (DocumentException | IOException | NumberFormatException e) {
        System.out.println(e);
    }
}
    private static void cerrarFacturaAbierta(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists() && archivo.canWrite()) {
                archivo.renameTo(new File(nombreArchivo + "_cerrada.pdf")); // Cambiar el nombre del archivo
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void abrirFactura(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                if (!archivo.canWrite()) {
                    Desktop.getDesktop().open(archivo);
                } else {
                    Desktop.getDesktop().open(archivo);
                }
            } else {
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void CleanCorreos(){
       
        txtNombreCliente.setText("");
        txtCorreo.setText("");

    }
    public static void enviarFacturaPorCorreo(String remitente, String contraseña, String destinatario, String asunto, String mensaje, String archivoAdjunto) {
        final String host = "smtp.gmail.com";
        final int port = 587;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Iniciar sesión de correo
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
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
                System.out.println(e);
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
            Transport.send(message);
           
            CleanCorreos();
        } catch (MessagingException e) {
            String errorMessage = "Error al enviar el correo:\n" + e.getMessage();

            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
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

        com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("src\\img\\iconSell.png");
        logo.scaleToFit(50, 50);
        logo.setAlignment(Element.ALIGN_LEFT);
        document.add(logo);

        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.UNDERLINE);
        Paragraph titulo = new Paragraph("Factura", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        document.add(new Paragraph("Número de factura:" + idVenta));
        document.add(new Paragraph("Fecha: " + formattedFechaHora));
        document.add(new Paragraph("Cliente:" + nombrCliente));

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

        for (int row = 0; row < model.getRowCount(); row++) {
            table.addCell((String) model.getValueAt(row, 0)); // ID Producto
            table.addCell((String) model.getValueAt(row, 1)); // Nombre Producto
            table.addCell((String) model.getValueAt(row, 2)); // Tamaño
            table.addCell("$" + model.getValueAt(row, 3));   // Precio
            table.addCell(model.getValueAt(row, 4).toString()); // Cantidad
            table.addCell("$" + model.getValueAt(row, 5)); // Costo Total
        }

        document.add(table);

        Paragraph totalProductosParagraph = new Paragraph("Total de productos: $" + totalProductos);
        totalProductosParagraph.setAlignment(Element.ALIGN_RIGHT);
        totalProductosParagraph.setSpacingBefore(20f);
        document.add(totalProductosParagraph);

        document.close();
        writer.close();

        abrirFactura(nombreArchivo);

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

    } catch (DocumentException | HeadlessException | IOException | PrintException e) {
        System.out.println(e);
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

        com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("src\\img\\iconSell.png");
        logo.scaleToFit(70, 70);
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

        Paragraph totalProductosParagraph = new Paragraph("Total de productos: $" + totalProductos);
        totalProductosParagraph.setAlignment(Element.ALIGN_RIGHT);
        totalProductosParagraph.setSpacingBefore(20f);
        document.add(totalProductosParagraph);

        document.close();
        writer.close();

        abrirFactura(nombreArchivo);
        
        String remitente = "devsamil26@gmail.com";
        String contraseña = "devsamil2005";
        String destinatario = txtCorreo.getText();
        String asunto = "Factura Adjunta";
        String mensaje = "Estimado cliente, adjuntamos la factura en PDF de su compra. ¡Gracias por preferirnos!";

        enviarFacturaPorCorreo(remitente, contraseña, destinatario, asunto, mensaje, nombreArchivo);
 
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        if (defaultPrintService != null) {
            FileInputStream inputStream = new FileInputStream(nombreArchivo);
            Doc pdfDoc = new SimpleDoc(inputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            printJob.print(pdfDoc, null);
            inputStream.close();
        } else {
        }
    } catch (DocumentException | IOException | PrintException e) {
        System.out.println(e);
    }
}
    
    public void calcularYActualizarDevuelta(DefaultTableModel model, double montoIngresado, JLabel LblDevuelta) {
    double total = 0.0;

    for (int i = 0; i < model.getRowCount(); i++) {
        float totalFloat = (float) model.getValueAt(i, 5);
        total += totalFloat;
    }

    double devuelta = montoIngresado - total;
    LblDevuelta.setText(String.format("%.2f", devuelta).replace(",", "."));
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContentAll = new javax.swing.JPanel();
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
        PanelTitle = new javax.swing.JPanel();
        LblDetalle = new javax.swing.JLabel();
        pdf = new javax.swing.JLabel();
        Eliminardelalista = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        LblCorreo = new javax.swing.JLabel();
        LblNCliente = new javax.swing.JLabel();
        Agregaralalista = new javax.swing.JLabel();
        btnCotizar = new Utils.ButtonRounded();
        btnFacturar = new Utils.ButtonRounded();
        PanelTitleC = new javax.swing.JPanel();
        LblCliente = new javax.swing.JLabel();
        montoIngresado = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        LblAdvertencia = new javax.swing.JLabel();
        iconPDF = new Utils.SVGImage2();
        iconAgregar = new Utils.SVGImage2();
        iconEliminar = new Utils.SVGImage2();
        LblIDVenta = new javax.swing.JLabel();
        TituloIDVenta = new javax.swing.JLabel();
        LblTitle = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        tablaProductosVentas = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1115, 760));

        ContentAll.setBackground(new java.awt.Color(24, 39, 72));
        ContentAll.setPreferredSize(new java.awt.Dimension(806, 460));
        ContentAll.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        PanelContenido.add(txtCantidadProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 120, 20));

        LblCantidad.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblCantidad.setForeground(new java.awt.Color(0, 0, 0));
        LblCantidad.setText("Nombre:");
        PanelContenido.add(LblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 80, 20));

        LblPrecio.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblPrecio.setForeground(new java.awt.Color(0, 0, 0));
        LblPrecio.setText("Precio:");
        PanelContenido.add(LblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 160, -1, 20));

        LblPrecioProducto.setBackground(new java.awt.Color(255, 255, 255));
        LblPrecioProducto.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblPrecioProducto.setForeground(new java.awt.Color(51, 51, 51));
        LblPrecioProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblPrecioProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblPrecioProducto.setOpaque(true);
        PanelContenido.add(LblPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 120, 20));

        LblSizeProducto.setBackground(new java.awt.Color(255, 255, 255));
        LblSizeProducto.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblSizeProducto.setForeground(new java.awt.Color(51, 51, 51));
        LblSizeProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblSizeProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblSizeProducto.setOpaque(true);
        PanelContenido.add(LblSizeProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 120, 20));

        LblSize.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblSize.setForeground(new java.awt.Color(0, 0, 0));
        LblSize.setText("Tamaño:");
        PanelContenido.add(LblSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 130, -1, 20));

        LblNombre.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblNombre.setForeground(new java.awt.Color(0, 0, 0));
        LblNombre.setText("Producto:");
        PanelContenido.add(LblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        LblIDProducto.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblIDProducto.setForeground(new java.awt.Color(0, 0, 0));
        LblIDProducto.setText("ID Prod:");
        PanelContenido.add(LblIDProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 70, -1, 20));

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
        PanelContenido.add(txtIDProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 120, 20));

        LblNombreProducto.setBackground(new java.awt.Color(255, 255, 255));
        LblNombreProducto.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblNombreProducto.setForeground(new java.awt.Color(51, 51, 51));
        LblNombreProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblNombreProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblNombreProducto.setOpaque(true);
        PanelContenido.add(LblNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 120, 20));

        LblDevuelta.setBackground(new java.awt.Color(255, 255, 255));
        LblDevuelta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblDevuelta.setForeground(new java.awt.Color(0, 0, 0));
        LblDevuelta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDevuelta.setText("0");
        LblDevuelta.setOpaque(true);
        PanelContenido.add(LblDevuelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 120, 20));

        LblDevueltaMonto.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblDevueltaMonto.setForeground(new java.awt.Color(0, 0, 0));
        LblDevueltaMonto.setText("Devolver:");
        PanelContenido.add(LblDevueltaMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 470, 90, 20));

        LblMontoPagado.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblMontoPagado.setForeground(new java.awt.Color(0, 0, 0));
        LblMontoPagado.setText("Recibido:");
        PanelContenido.add(LblMontoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 520, 80, 20));

        LblTotalPagar.setBackground(new java.awt.Color(0, 0, 0));
        LblTotalPagar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblTotalPagar.setForeground(new java.awt.Color(0, 0, 0));
        LblTotalPagar.setText("Costo total:");
        PanelContenido.add(LblTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 100, 20));

        LblMontoPagar.setBackground(new java.awt.Color(51, 255, 0));
        LblMontoPagar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoPagar.setForeground(new java.awt.Color(0, 204, 0));
        LblMontoPagar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblMontoPagar.setText("0");
        PanelContenido.add(LblMontoPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, 120, 20));

        PanelTitle.setBackground(new java.awt.Color(0, 112, 192));
        PanelTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblDetalle.setFont(new java.awt.Font("Roboto Black", 1, 20)); // NOI18N
        LblDetalle.setForeground(new java.awt.Color(255, 255, 255));
        LblDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDetalle.setText("Producto");
        PanelTitle.add(LblDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 8, 100, -1));

        PanelContenido.add(PanelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 40));

        pdf.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        pdf.setForeground(new java.awt.Color(102, 102, 102));
        pdf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pdf.setText("PDF");
        PanelContenido.add(pdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 30, -1));

        Eliminardelalista.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        Eliminardelalista.setForeground(new java.awt.Color(102, 102, 102));
        Eliminardelalista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Eliminardelalista.setText("Eliminar ");
        PanelContenido.add(Eliminardelalista, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 50, -1));

        txtCorreo.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorreo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCorreoCaretUpdate(evt);
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });
        PanelContenido.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 120, 23));

        LblCorreo.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblCorreo.setForeground(new java.awt.Color(0, 0, 0));
        LblCorreo.setText("Correo:");
        PanelContenido.add(LblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 70, 20));

        LblNCliente.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblNCliente.setForeground(new java.awt.Color(0, 0, 0));
        LblNCliente.setText("Cantidad:");
        PanelContenido.add(LblNCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 190, -1, 20));

        Agregaralalista.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        Agregaralalista.setForeground(new java.awt.Color(102, 102, 102));
        Agregaralalista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Agregaralalista.setText("Agregar");
        PanelContenido.add(Agregaralalista, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 50, -1));

        btnCotizar.setBackground(new java.awt.Color(132, 178, 80));
        btnCotizar.setForeground(new java.awt.Color(255, 255, 255));
        btnCotizar.setText("COTIZAR");
        btnCotizar.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnCotizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCotizarActionPerformed(evt);
            }
        });
        PanelContenido.add(btnCotizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 100, 30));

        btnFacturar.setBackground(new java.awt.Color(132, 178, 80));
        btnFacturar.setForeground(new java.awt.Color(255, 255, 255));
        btnFacturar.setText("FACTURAR");
        btnFacturar.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });
        PanelContenido.add(btnFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 570, 100, 30));

        PanelTitleC.setBackground(new java.awt.Color(0, 112, 192));
        PanelTitleC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblCliente.setFont(new java.awt.Font("Roboto Black", 1, 20)); // NOI18N
        LblCliente.setForeground(new java.awt.Color(255, 255, 255));
        LblCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblCliente.setText("Cliente");
        PanelTitleC.add(LblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 8, 100, -1));

        PanelContenido.add(PanelTitleC, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 290, 40));

        montoIngresado.setBackground(new java.awt.Color(255, 255, 255));
        montoIngresado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        montoIngresado.setForeground(new java.awt.Color(0, 0, 0));
        montoIngresado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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
        PanelContenido.add(montoIngresado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 516, 120, 30));

        txtNombreCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCliente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreCliente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNombreClienteCaretUpdate(evt);
            }
        });
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });
        PanelContenido.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 120, 23));

        LblAdvertencia.setForeground(new java.awt.Color(255, 51, 51));
        LblAdvertencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanelContenido.add(LblAdvertencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 20, 20));

        iconPDF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconPDF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconPDFMouseClicked(evt);
            }
        });
        PanelContenido.add(iconPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 240, 30, 30));

        iconAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconAgregarMouseClicked(evt);
            }
        });
        PanelContenido.add(iconAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 30, 30));

        iconEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconEliminarMouseClicked(evt);
            }
        });
        PanelContenido.add(iconEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 240, 30, 30));

        ContentAll.add(PanelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 290, 620));

        LblIDVenta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LblIDVenta.setForeground(new java.awt.Color(51, 255, 51));
        ContentAll.add(LblIDVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(922, 80, 160, 30));

        TituloIDVenta.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        TituloIDVenta.setForeground(new java.awt.Color(255, 255, 255));
        TituloIDVenta.setText("ID Venta / No. Factura:");
        ContentAll.add(TituloIDVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, -1, 30));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Facturación");
        ContentAll.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

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
        scroll.setViewportView(tablaProductosVentas);

        ContentAll.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 750, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContentAll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContentAll, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantidadProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyTyped
    char teclaPress = evt.getKeyChar();
    
    if (Character.isDigit(teclaPress) || teclaPress == KeyEvent.VK_BACK_SPACE) {
        // Permitir dígitos y la tecla de retroceso (backspace)
    } else if (teclaPress == KeyEvent.VK_ENTER) {
        // Realizar las acciones cuando se presiona Enter
        VerificacionStock();
        guardarDatos();
    } else {

        evt.consume();
        JOptionPane.showMessageDialog(this, "Carácter incorrecto. Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_txtCantidadProductoKeyTyped

    private void txtIDProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDProductoKeyTyped

    }//GEN-LAST:event_txtIDProductoKeyTyped

    private void txtCorreoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCorreoCaretUpdate

    }//GEN-LAST:event_txtCorreoCaretUpdate

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped

    }//GEN-LAST:event_txtCorreoKeyTyped

    private void montoIngresadoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_montoIngresadoCaretUpdate
    String montoIngresadoTxt = montoIngresado.getText(); // Suponiendo que campoTextoMonto es el JTextField
        if (!montoIngresadoTxt.isEmpty()) {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            double montoIngresado = Double.parseDouble(montoIngresadoTxt);
            DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
            calcularYActualizarDevuelta(model, montoIngresado, LblDevuelta);
        }
    }//GEN-LAST:event_montoIngresadoCaretUpdate

    private void montoIngresadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoIngresadoKeyTyped
        char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
            guardarDatos();
             btnFacturar.doClick();           
            
        }
    }//GEN-LAST:event_montoIngresadoKeyTyped

    private void txtNombreClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNombreClienteCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteCaretUpdate

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteKeyTyped

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

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
            String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre IS NULL";
    boolean condicionesCumplidas = false;

    try(Connection conectar = c.getConexion();) {
        
        PreparedStatement pst = conectar.prepareStatement(checkNullDate);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            if (tablaProductosVentas.getRowCount() > 0) {
                    guardarDatos();
                    condicionesCumplidas = true;
            } else {
                JOptionPane.showMessageDialog(this, "Debes ingresar al menos un producto en la tabla antes de facturar.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            } 
        } else {
            JOptionPane.showMessageDialog(this, "No existe ninguna sesión de caja abierta.\n              Dirijase a Caja.","Error.",JOptionPane.ERROR_MESSAGE);            
            return;
        }
        c.closePST(pst);
        c.desconectar();
    }    catch (ClassNotFoundException | SQLException ex) {
        System.out.println(ex);
            Logger.getLogger(FormFacturacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    if (condicionesCumplidas) { 
        validarFacturacion();
    }
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void iconEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconEliminarMouseClicked
        EliminarFila();
    }//GEN-LAST:event_iconEliminarMouseClicked

    private void iconPDFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconPDFMouseClicked
        if (txtNombreCliente.getText().isEmpty()) {
            LblAdvertencia.setText("*");
            LblAdvertencia.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del cliente antes de imprimir.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else if (tablaProductosVentas.getRowCount() > 0) {
            PDF();
        } else {
            LblAdvertencia.setText("");
            JOptionPane.showMessageDialog(this, "Ingrese al menos un producto en la tabla antes de imprimir.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_iconPDFMouseClicked

    private void iconAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconAgregarMouseClicked
        VerificacionStock();
        guardarDatos();
    }//GEN-LAST:event_iconAgregarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Agregaralalista;
    private javax.swing.JPanel ContentAll;
    private javax.swing.JLabel Eliminardelalista;
    public static javax.swing.JLabel LblAdvertencia;
    private javax.swing.JLabel LblCantidad;
    private javax.swing.JLabel LblCliente;
    private javax.swing.JLabel LblCorreo;
    private javax.swing.JLabel LblDetalle;
    private static javax.swing.JLabel LblDevuelta;
    private javax.swing.JLabel LblDevueltaMonto;
    private javax.swing.JLabel LblIDProducto;
    private static javax.swing.JLabel LblIDVenta;
    private javax.swing.JLabel LblMontoPagado;
    public static javax.swing.JLabel LblMontoPagar;
    private javax.swing.JLabel LblNCliente;
    private javax.swing.JLabel LblNombre;
    private static javax.swing.JLabel LblNombreProducto;
    private javax.swing.JLabel LblPrecio;
    private static javax.swing.JLabel LblPrecioProducto;
    private javax.swing.JLabel LblSize;
    private static javax.swing.JLabel LblSizeProducto;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JLabel LblTotalPagar;
    private javax.swing.JPanel PanelContenido;
    private javax.swing.JPanel PanelTitle;
    private javax.swing.JPanel PanelTitleC;
    private javax.swing.JLabel TituloIDVenta;
    private Utils.ButtonRounded btnCotizar;
    private Utils.ButtonRounded btnFacturar;
    private Utils.SVGImage2 iconAgregar;
    private Utils.SVGImage2 iconEliminar;
    private Utils.SVGImage2 iconPDF;
    public static javax.swing.JTextField montoIngresado;
    private javax.swing.JLabel pdf;
    private javax.swing.JScrollPane scroll;
    public static javax.swing.JTable tablaProductosVentas;
    private static javax.swing.JTextField txtCantidadProducto;
    public static javax.swing.JTextField txtCorreo;
    public static javax.swing.JTextField txtIDProducto;
    public static javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
