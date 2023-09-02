
package Contabilidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import Connect.Conexion;
import java.awt.event.KeyEvent;


/**
 *
 * @author Angelo
 */

public class RegistrarPagoCPagar extends javax.swing.JDialog {


    TabContabilidad tc = new TabContabilidad();
    int xMouse, yMouse;
       
    public RegistrarPagoCPagar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setLocationRelativeTo(parent);
        
         PanelMoveLogin.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mousePressed(java.awt.event.MouseEvent evt) {
            btnSalir1MousePressed(evt);
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
        LblDeuda = new javax.swing.JLabel();
        LblMontoDeuda = new javax.swing.JLabel();
        txtNombreAcreedor = new javax.swing.JTextField();
        BtnGuardarCp = new javax.swing.JButton();
        TotalFaltante = new javax.swing.JLabel();
        LblTotalFaltante = new javax.swing.JLabel();
        PanelMoveLogin = new javax.swing.JPanel();
        btnSalir1 = new rojerusan.RSMaterialButtonRectangle();
        txtMontoPago = new javax.swing.JTextField();
        txtIdAcreedor = new javax.swing.JTextField();
        LblIdProducto1 = new javax.swing.JLabel();
        txtCompra = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        PanelDatos.setBackground(new java.awt.Color(24, 39, 72));

        LblIdProducto.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblIdProducto.setForeground(new java.awt.Color(255, 255, 255));
        LblIdProducto.setText("ID Acreedor: ");

        LblDeudor.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblDeudor.setForeground(new java.awt.Color(255, 255, 255));
        LblDeudor.setText("Descrip. Acreedor:");

        LblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        LblTitulo.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        LblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LblTitulo.setText("Pago a Cuenta por Pagar");

        LblMontoPagar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoPagar.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoPagar.setText("Monto a Pagar:");

        LblDeuda.setBackground(new java.awt.Color(255, 255, 255));
        LblDeuda.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        LblDeuda.setForeground(new java.awt.Color(0, 0, 0));
        LblDeuda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDeuda.setEnabled(false);
        LblDeuda.setOpaque(true);

        LblMontoDeuda.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoDeuda.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoDeuda.setText("Monto Deuda:");

        txtNombreAcreedor.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreAcreedor.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombreAcreedor.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreAcreedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreAcreedor.setEnabled(false);

        BtnGuardarCp.setBackground(new java.awt.Color(132, 178, 80));
        BtnGuardarCp.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        BtnGuardarCp.setForeground(new java.awt.Color(255, 255, 255));
        BtnGuardarCp.setText("GUARDAR");
        BtnGuardarCp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarCpActionPerformed(evt);
            }
        });

        TotalFaltante.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        TotalFaltante.setForeground(new java.awt.Color(255, 255, 255));
        TotalFaltante.setText("Total Faltante:");

        LblTotalFaltante.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblTotalFaltante.setForeground(new java.awt.Color(255, 255, 255));
        LblTotalFaltante.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LblTotalFaltante.setText("0");

        PanelMoveLogin.setBackground(new java.awt.Color(24, 39, 72));
        PanelMoveLogin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PanelMoveLoginMouseDragged(evt);
            }
        });

        btnSalir1.setBackground(new java.awt.Color(24, 39, 72));
        btnSalir1.setText("X");
        btnSalir1.setFont(new java.awt.Font("Roboto Light", 1, 10)); // NOI18N
        btnSalir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalir1MousePressed(evt);
            }
        });
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMoveLoginLayout = new javax.swing.GroupLayout(PanelMoveLogin);
        PanelMoveLogin.setLayout(PanelMoveLoginLayout);
        PanelMoveLoginLayout.setHorizontalGroup(
            PanelMoveLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMoveLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMoveLoginLayout.setVerticalGroup(
            PanelMoveLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMoveLoginLayout.createSequentialGroup()
                .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        txtMontoPago.setBackground(new java.awt.Color(255, 255, 255));
        txtMontoPago.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtMontoPago.setForeground(new java.awt.Color(51, 51, 51));
        txtMontoPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoPagoKeyTyped(evt);
            }
        });

        txtIdAcreedor.setBackground(new java.awt.Color(255, 255, 255));
        txtIdAcreedor.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtIdAcreedor.setForeground(new java.awt.Color(51, 51, 51));
        txtIdAcreedor.setEnabled(false);

        LblIdProducto1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblIdProducto1.setForeground(new java.awt.Color(255, 255, 255));
        LblIdProducto1.setText("ID Compra: ");

        txtCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtCompra.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCompra.setForeground(new java.awt.Color(51, 51, 51));
        txtCompra.setEnabled(false);

        javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMoveLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDatosLayout.createSequentialGroup()
                                .addComponent(LblMontoPagar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMontoPago))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDatosLayout.createSequentialGroup()
                                .addComponent(LblMontoDeuda)
                                .addGap(7, 7, 7)
                                .addComponent(LblDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PanelDatosLayout.createSequentialGroup()
                            .addComponent(LblIdProducto)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtIdAcreedor, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelDatosLayout.createSequentialGroup()
                            .addComponent(LblDeudor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombreAcreedor, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelDatosLayout.createSequentialGroup()
                            .addComponent(TotalFaltante)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(LblTotalFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelDatosLayout.createSequentialGroup()
                            .addGap(87, 87, 87)
                            .addComponent(BtnGuardarCp, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelDatosLayout.createSequentialGroup()
                            .addComponent(LblIdProducto1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(LblTitulo))
                .addGap(0, 44, Short.MAX_VALUE))
        );
        PanelDatosLayout.setVerticalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addComponent(PanelMoveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblIdProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdAcreedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblDeudor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreAcreedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblMontoDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblMontoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMontoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblTotalFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(BtnGuardarCp)
                .addGap(55, 55, 55))
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

    private void BtnGuardarCpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarCpActionPerformed

        try {
            double montoPagar = Double.parseDouble(txtMontoPago.getText());
            double totalFaltante = Double.parseDouble(LblTotalFaltante.getText());

            if (montoPagar > totalFaltante) {
                JOptionPane.showMessageDialog(this, "El monto ingresado es mayor que el faltante.\nDebe ingresar la misma cantidad o menos.", "Error de Monto", JOptionPane.ERROR_MESSAGE);
                return; // Sale del método sin continuar
            }

            RegistrarPagoCuentaPagar();
            tc.DatosCuentaPagar();
            tc.revisarCuentasSaldadasCuentaPagar();
          //  revisar = true;
            this.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto válido.", "Error de Monto", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_BtnGuardarCpActionPerformed

    public void setDatosRegistroPago(String idCompra, String idAcreedor, String descripcionAcreedor, String deuda, String totalFaltante) {
        txtCompra.setText(idCompra);
        txtIdAcreedor.setText(idAcreedor);
        txtNombreAcreedor.setText(descripcionAcreedor);
        LblDeuda.setText(deuda);
        LblTotalFaltante.setText(totalFaltante);
    }

    private void registrarPagoCuentaPagar(double montoPagado) {
        String idCompra = txtCompra.getText();
        LocalDate fechaActual = LocalDate.now();

        try (Connection connection = Conexion.conectar()) {
            connection.setAutoCommit(false);

            String insertPagoQuery = "INSERT INTO pagocp (idCompra, monto, fecha) VALUES (?, ?, ?)";
            try (PreparedStatement insertPagoStatement = connection.prepareStatement(insertPagoQuery)) {
                insertPagoStatement.setString(1, idCompra);
                insertPagoStatement.setDouble(2, montoPagado);
                insertPagoStatement.setDate(3, Date.valueOf(fechaActual));
                insertPagoStatement.executeUpdate();
            }

            connection.commit();
            // JOptionPane.showMessageDialog(this, "Pago registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "No se pudo registrar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void RegistrarPagoCuentaPagar() {
    double montoPagado = Double.parseDouble(txtMontoPago.getText());

    registrarPagoCuentaPagar(montoPagado);
    // Limpiar los campos después de guardar
    txtMontoPago.setText("");
}
    
    private void btnSalir1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir1MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_btnSalir1MousePressed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
       // System.exit(0);
        this.dispose();
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void PanelMoveLoginMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMoveLoginMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_PanelMoveLoginMouseDragged

    private void txtMontoPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoPagoKeyTyped
         char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
                BtnGuardarCp.doClick();
        }
    }//GEN-LAST:event_txtMontoPagoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardarCp;
    private javax.swing.JLabel LblDeuda;
    private javax.swing.JLabel LblDeudor;
    private javax.swing.JLabel LblIdProducto;
    private javax.swing.JLabel LblIdProducto1;
    private javax.swing.JLabel LblMontoDeuda;
    private javax.swing.JLabel LblMontoPagar;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JLabel LblTotalFaltante;
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JPanel PanelMoveLogin;
    private javax.swing.JLabel TotalFaltante;
    private rojerusan.RSMaterialButtonRectangle btnSalir1;
    private javax.swing.JTextField txtCompra;
    private javax.swing.JTextField txtIdAcreedor;
    private javax.swing.JTextField txtMontoPago;
    private javax.swing.JTextField txtNombreAcreedor;
    // End of variables declaration//GEN-END:variables
}
