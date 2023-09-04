package Venta;

import Connect.Conexion;
import static Venta.FacturacionVenta.LblMontoPagar;
import static Venta.FacturacionVenta.tablaProductosVentas;
import java.awt.event.KeyEvent;
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

public class DeudaClientes extends javax.swing.JDialog {

    private float totalAdeudado;
    private String idVenta;
   // private float totalVdb;
    private float montoR;
    
    private String nC;     // Declarar nC en el ámbito de la clase
    private FacturacionVenta fv; // Instancia de FacturacionVenta
    
    

    public DeudaClientes() {
        initComponents();
        setModal(true);
        fv = new FacturacionVenta(); // Inicializar la instancia de FacturacionVenta
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fecha = formato.format(fechaHoraActual);
        LblFecha.setText(fecha);
        //idVenta = UniqueIDGenerator.generateUniqueID();
        LblIDVenta.setText(idVenta);
    }
    


    public void inicializarDeuda(float devuelta, int MontoR, String nombreCliente, String idVenta) {
        float dev = devuelta * -1;
        LblMontoAdeudado.setText(String.format("%.2f", dev));
        System.out.print("total adeudado: " + totalAdeudado );//+ " total: " + total);
       // totalVdb = Float.parseFloat(LblMontoPagar.getText());
        montoR = MontoR;
        nC = nombreCliente; // Asignar el nombre del cliente
        txtNombreDeudor.setText(nombreCliente); // Opcionalmente, asignar el nombre al campo de texto en el diálogo
        LblIDVenta.setText(idVenta); // Asignar el ID de venta al label correspondiente
    }
    
    private void guardar() {
         
        float deuda = Float.parseFloat(LblMontoAdeudado.getText());
        float deudapositivo = Math.abs(deuda);

        System.out.print("deuda positivo: " + deudapositivo );
        float totalFaltante = Float.parseFloat(LblMontoAdeudado.getText());
        String descripcion = txtDescripcion.getText();

        String consulta = "SELECT idVenta FROM cuentaporcobrar WHERE idVenta = ?";
        String query = "INSERT INTO cuentaporcobrar (idVenta, deuda, totalFaltante, fecha, descripcion) VALUES (?,?,?,?,?)";
        String ventaQuery = "INSERT INTO venta (id, nombreCliente, total, fecha) VALUES (?,?,?,?)";
        String detalleVentaQuery = "INSERT INTO detalleventa (idVenta, idProducto, cantidadP) VALUES (?,?,?)";
        String ingresoQuery = "INSERT INTO ingresos (concepto, monto, fecha) VALUES (?,?,?)";
        String concepto = "Venta de producto.";

        try (Connection conectar = Conexion.conectar()) {
            PreparedStatement pst = conectar.prepareStatement(consulta);
            pst.setString(1, LblIDVenta.getText());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Este ID de venta ya existe, por favor inténtelo de nuevo.");
                return;
            }

            conectar.setAutoCommit(false);

        try {
            System.out.print("Entrando venta");
            PreparedStatement pst2 = conectar.prepareStatement(ventaQuery);
            pst2.setString(1, LblIDVenta.getText());
            pst2.setString(2, nC);
            pst2.setFloat(3, montoR);
            pst2.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pst2.executeUpdate();
            System.out.print("antes de entrar a ingresos");

            PreparedStatement pst5 = conectar.prepareStatement(ingresoQuery);
            System.out.print("ingresos" + concepto + montoR);
            pst5.setString(1, concepto);
            pst5.setFloat(2, montoR);
            pst5.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pst5.executeUpdate();
            System.out.print("ingresos2" + concepto + montoR);

            DefaultTableModel model = (DefaultTableModel) tablaProductosVentas.getModel();
            
            for (int row = 0; row < model.getRowCount(); row++) {
                System.out.print("entro al for");
                String productoID = (String) model.getValueAt(row, 0);
                String cantidadStr = (String) model.getValueAt(row, 4);
                int cantidad = Integer.parseInt(cantidadStr);
                
                System.out.print("entro esto es el result; " + row + productoID + cantidad );

                PreparedStatement pst3 = conectar.prepareStatement(detalleVentaQuery);
                pst3.setString(1, LblIDVenta.getText());
                pst3.setString(2, productoID);
                pst3.setInt(3, cantidad);
                pst3.executeUpdate();
                System.out.print("se ejecuto");
            }

            PreparedStatement pst4 = conectar.prepareStatement(query);
            pst4.setString(1, LblIDVenta.getText());
            pst4.setFloat(2, deudapositivo);
            pst4.setFloat(3, totalFaltante);
            pst4.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pst4.setString(5, descripcion);
            pst4.executeUpdate();

                conectar.commit();
                JOptionPane.showMessageDialog(this, "Deuda guardada exitosamente.");
               // this.dispose();
//                boolean activarMetodoB = true;
//                if (activarMetodoB) {
//                    fv.metodoB();
//                }
        this.dispose();
            } catch (SQLException ex) {
                conectar.rollback();
                throw ex;
            } finally {
                conectar.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeudaClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DialogDeudor = new javax.swing.JPanel();
        PanelDatos = new javax.swing.JPanel();
        txtDescripcion = new javax.swing.JTextArea();
        txtNombreDeudor = new javax.swing.JTextField();
        BtnGuardarDeuda = new javax.swing.JButton();
        LblDeudor = new javax.swing.JLabel();
        LblMontoAdeudado = new javax.swing.JLabel();
        LblMonto = new javax.swing.JLabel();
        LblFecha = new javax.swing.JLabel();
        LblFechaDeuda = new javax.swing.JLabel();
        LblDescripcion = new javax.swing.JLabel();
        LblIdProducto = new javax.swing.JLabel();
        LblIDVenta = new javax.swing.JLabel();
        LblTitulo = new javax.swing.JLabel();
        btnSalir = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(24, 39, 72));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(459, 456));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DialogDeudor.setBackground(new java.awt.Color(24, 39, 72));
        DialogDeudor.setMaximumSize(new java.awt.Dimension(600, 400));
        DialogDeudor.setPreferredSize(new java.awt.Dimension(459, 456));
        DialogDeudor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelDatos.setBackground(new java.awt.Color(24, 39, 72));
        PanelDatos.setPreferredSize(new java.awt.Dimension(459, 456));
        PanelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setColumns(20);
        txtDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        PanelDatos.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 170, 60));

        txtNombreDeudor.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreDeudor.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombreDeudor.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreDeudor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreDeudor.setEnabled(false);
        PanelDatos.add(txtNombreDeudor, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 150, -1));

        BtnGuardarDeuda.setBackground(new java.awt.Color(132, 178, 80));
        BtnGuardarDeuda.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        BtnGuardarDeuda.setForeground(new java.awt.Color(255, 255, 255));
        BtnGuardarDeuda.setText("GUARDAR");
        BtnGuardarDeuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarDeudaActionPerformed(evt);
            }
        });
        PanelDatos.add(BtnGuardarDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, -1, 30));

        LblDeudor.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblDeudor.setForeground(new java.awt.Color(255, 255, 255));
        LblDeudor.setText("Cliente:");
        PanelDatos.add(LblDeudor, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, 20));

        LblMontoAdeudado.setBackground(new java.awt.Color(255, 255, 255));
        LblMontoAdeudado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        LblMontoAdeudado.setForeground(new java.awt.Color(0, 0, 0));
        LblMontoAdeudado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblMontoAdeudado.setEnabled(false);
        LblMontoAdeudado.setOpaque(true);
        PanelDatos.add(LblMontoAdeudado, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 290, 150, 20));

        LblMonto.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblMonto.setForeground(new java.awt.Color(255, 255, 255));
        LblMonto.setText("Monto deuda:");
        PanelDatos.add(LblMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 20));

        LblFecha.setBackground(new java.awt.Color(255, 255, 255));
        LblFecha.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        LblFecha.setForeground(new java.awt.Color(0, 0, 0));
        LblFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblFecha.setEnabled(false);
        LblFecha.setOpaque(true);
        PanelDatos.add(LblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 150, 20));

        LblFechaDeuda.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblFechaDeuda.setForeground(new java.awt.Color(255, 255, 255));
        LblFechaDeuda.setText("Fecha:");
        PanelDatos.add(LblFechaDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, 20));

        LblDescripcion.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        LblDescripcion.setText("Descripción:");
        PanelDatos.add(LblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, 20));

        LblIdProducto.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblIdProducto.setForeground(new java.awt.Color(255, 255, 255));
        LblIdProducto.setText("ID Venta: ");
        PanelDatos.add(LblIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, 20));

        LblIDVenta.setBackground(new java.awt.Color(255, 255, 255));
        LblIDVenta.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        LblIDVenta.setForeground(new java.awt.Color(51, 255, 51));
        LblIDVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblIDVenta.setOpaque(true);
        PanelDatos.add(LblIDVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 120, 20));

        LblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        LblTitulo.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        LblTitulo.setForeground(new java.awt.Color(204, 0, 0));
        LblTitulo.setText("DIGITE LOS DATOS DEL DEUDOR");
        PanelDatos.add(LblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, 40));

        DialogDeudor.add(PanelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 440, 410));

        btnSalir.setBackground(new java.awt.Color(24, 39, 72));
        btnSalir.setText("X");
        btnSalir.setToolTipText("Cancelar facturación.");
        btnSalir.setFont(new java.awt.Font("Roboto Black", 1, 10)); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        DialogDeudor.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 30));

        getContentPane().add(DialogDeudor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void BtnGuardarDeudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarDeudaActionPerformed
       guardar();
        boolean activarMetodoB = true;
        if (activarMetodoB) {
            fv.metodoB();
        }
        this.dispose();
    }//GEN-LAST:event_BtnGuardarDeudaActionPerformed

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        char t = evt.getKeyChar();
        if (t == KeyEvent.VK_ENTER) {
            BtnGuardarDeuda.doClick();
        } 
    }//GEN-LAST:event_txtDescripcionKeyTyped
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardarDeuda;
    private javax.swing.JPanel DialogDeudor;
    private javax.swing.JLabel LblDescripcion;
    private javax.swing.JLabel LblDeudor;
    private javax.swing.JLabel LblFecha;
    private javax.swing.JLabel LblFechaDeuda;
    private javax.swing.JLabel LblIDVenta;
    private javax.swing.JLabel LblIdProducto;
    private javax.swing.JLabel LblMonto;
    private javax.swing.JLabel LblMontoAdeudado;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JPanel PanelDatos;
    private rojerusan.RSMaterialButtonRectangle btnSalir;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombreDeudor;
    // End of variables declaration//GEN-END:variables
}
