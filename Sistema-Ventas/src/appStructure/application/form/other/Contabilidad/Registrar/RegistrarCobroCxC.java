package appStructure.application.form.other.Contabilidad.Registrar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Database.Connect.ConexionSingleton;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import appStructure.application.form.other.Contabilidad.FormCxC;

/**
 *
 * @author Samil
 */
public class RegistrarCobroCxC extends javax.swing.JDialog {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    LocalDateTime fechaActual = LocalDateTime.now();
    int xMouse, yMouse;
    
    public RegistrarCobroCxC(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setLocationRelativeTo(parent);
        
         PanelMoveLogin.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mousePressed(java.awt.event.MouseEvent evt) {
            btnSalirMousePressed(evt);
         }    
        });
        PanelMoveLogin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PanelMoveLoginMouseDragged(evt);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelDatos = new javax.swing.JPanel();
        LblIdProducto = new javax.swing.JLabel();
        LblDeudor = new javax.swing.JLabel();
        LblTitulo = new javax.swing.JLabel();
        LblMontoPagar = new javax.swing.JLabel();
        LblDeudaResultado = new javax.swing.JLabel();
        LblMontoDeuda = new javax.swing.JLabel();
        txtNombreDeudor = new javax.swing.JTextField();
        BtnGuardar = new javax.swing.JButton();
        TotalFaltante = new javax.swing.JLabel();
        LblTotalFaltanteResultado = new javax.swing.JLabel();
        PanelMoveLogin = new javax.swing.JPanel();
        btnSalir = new Utils.ButtonRounded();
        txtMontoPagar = new javax.swing.JTextField();
        txtIdVenta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        PanelDatos.setBackground(new java.awt.Color(24, 39, 72));

        LblIdProducto.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblIdProducto.setForeground(new java.awt.Color(255, 255, 255));
        LblIdProducto.setText("ID Venta: ");

        LblDeudor.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblDeudor.setForeground(new java.awt.Color(255, 255, 255));
        LblDeudor.setText("Cliente Deudor:");

        LblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        LblTitulo.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        LblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LblTitulo.setText("Pago a Cuenta por Cobrar");

        LblMontoPagar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoPagar.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoPagar.setText("Monto a Pagar:");

        LblDeudaResultado.setBackground(new java.awt.Color(255, 255, 255));
        LblDeudaResultado.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        LblDeudaResultado.setForeground(new java.awt.Color(0, 0, 0));
        LblDeudaResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDeudaResultado.setEnabled(false);
        LblDeudaResultado.setOpaque(true);

        LblMontoDeuda.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoDeuda.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoDeuda.setText("Monto Deuda:");

        txtNombreDeudor.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreDeudor.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombreDeudor.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreDeudor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreDeudor.setCaretColor(new java.awt.Color(0, 0, 0));
        txtNombreDeudor.setEnabled(false);

        BtnGuardar.setBackground(new java.awt.Color(132, 178, 80));
        BtnGuardar.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        BtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        BtnGuardar.setText("GUARDAR");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        TotalFaltante.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        TotalFaltante.setForeground(new java.awt.Color(255, 255, 255));
        TotalFaltante.setText("Total Faltante:");

        LblTotalFaltanteResultado.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblTotalFaltanteResultado.setForeground(new java.awt.Color(255, 255, 255));
        LblTotalFaltanteResultado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LblTotalFaltanteResultado.setText("0");

        PanelMoveLogin.setBackground(new java.awt.Color(24, 39, 72));
        PanelMoveLogin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PanelMoveLoginMouseDragged(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(255, 51, 51));
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText(" X ");
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalirMousePressed(evt);
            }
        });
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMoveLoginLayout = new javax.swing.GroupLayout(PanelMoveLogin);
        PanelMoveLogin.setLayout(PanelMoveLoginLayout);
        PanelMoveLoginLayout.setHorizontalGroup(
            PanelMoveLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMoveLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMoveLoginLayout.setVerticalGroup(
            PanelMoveLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMoveLoginLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        txtMontoPagar.setBackground(new java.awt.Color(255, 255, 255));
        txtMontoPagar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtMontoPagar.setForeground(new java.awt.Color(51, 51, 51));
        txtMontoPagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoPagarKeyTyped(evt);
            }
        });

        txtIdVenta.setBackground(new java.awt.Color(255, 255, 255));
        txtIdVenta.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtIdVenta.setForeground(new java.awt.Color(51, 51, 51));
        txtIdVenta.setCaretColor(new java.awt.Color(0, 0, 0));
        txtIdVenta.setEnabled(false);

        javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMoveLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LblTitulo)
                    .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDatosLayout.createSequentialGroup()
                                .addComponent(LblMontoPagar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMontoPagar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDatosLayout.createSequentialGroup()
                                .addComponent(LblMontoDeuda)
                                .addGap(7, 7, 7)
                                .addComponent(LblDeudaResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PanelDatosLayout.createSequentialGroup()
                            .addComponent(LblIdProducto)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelDatosLayout.createSequentialGroup()
                            .addComponent(LblDeudor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombreDeudor, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelDatosLayout.createSequentialGroup()
                            .addComponent(TotalFaltante)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(LblTotalFaltanteResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 44, Short.MAX_VALUE))
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelDatosLayout.setVerticalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addComponent(PanelMoveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblDeudor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreDeudor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblMontoDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblDeudaResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblMontoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMontoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblTotalFaltanteResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(BtnGuardar)
                .addGap(87, 87, 87))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PanelMoveLoginMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMoveLoginMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_PanelMoveLoginMouseDragged

     
    public boolean revisar = false;

    public boolean isRevisar() {
        return revisar;
    }
    
    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        
        try {
        double montoPagar = Double.parseDouble(txtMontoPagar.getText());
        double totalFaltante = Double.parseDouble(LblTotalFaltanteResultado.getText());

        if (montoPagar > totalFaltante) {
            JOptionPane.showMessageDialog(this, "El monto ingresado es mayor que el faltante.\nDebe ingresar la misma cantidad o menos.", "Error de Monto", JOptionPane.ERROR_MESSAGE);
            return; // Sale del método sin continuar
        }

        guardarCambiosSeleccionados();
        FormCxC tc = new FormCxC();
        tc.DatosCuentaCobrar();
        tc.revisarCuentasSaldadas();
        revisar = true;
        this.dispose();
        
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Ingrese un monto válido.", "Error de Monto", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void txtMontoPagarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoPagarKeyTyped
        char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
                BtnGuardar.doClick();
        }
    }//GEN-LAST:event_txtMontoPagarKeyTyped

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_btnSalirMousePressed
    
    // Método para establecer los datos en el diálogo
    public void setDatosRegistro(String IdVenta, String nombreCliente, String deuda, String totalFaltante) {
        txtIdVenta.setText(IdVenta);
        txtNombreDeudor.setText(nombreCliente);
        LblDeudaResultado.setText(deuda);
        LblTotalFaltanteResultado.setText(totalFaltante);
    }

    private void registrarPagoCuentaPorCobrar(double montoPagado) {
        String idVenta = txtIdVenta.getText();
        
        try (Connection connection = c.getConexion()) {
            connection.setAutoCommit(false);
            
            String insertPagoQuery = "INSERT INTO pagocc (idVenta, monto, fecha) VALUES (?, ?, ?)";
            try (PreparedStatement insertPagoStatement = connection.prepareStatement(insertPagoQuery)) {
                insertPagoStatement.setString(1, idVenta);
                insertPagoStatement.setDouble(2, montoPagado);
                insertPagoStatement.setTimestamp(3, Timestamp.valueOf(fechaActual));
                insertPagoStatement.executeUpdate();
            }

            connection.commit();
          c.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "No se pudo registrar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarCobroCxC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void guardarCambiosSeleccionados() {
    
    double montoPagado = Double.parseDouble(txtMontoPagar.getText()); // Obtener el monto pagado
    registrarPagoCuentaPorCobrar(montoPagado); // Registrar el pago y los ingresos
    txtMontoPagar.setText("");

}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JLabel LblDeudaResultado;
    private javax.swing.JLabel LblDeudor;
    private javax.swing.JLabel LblIdProducto;
    private javax.swing.JLabel LblMontoDeuda;
    private javax.swing.JLabel LblMontoPagar;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JLabel LblTotalFaltanteResultado;
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JPanel PanelMoveLogin;
    private javax.swing.JLabel TotalFaltante;
    private Utils.ButtonRounded btnSalir;
    private Utils.ButtonRounded btnSalir1;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtMontoPagar;
    private javax.swing.JTextField txtNombreDeudor;
    // End of variables declaration//GEN-END:variables
}
