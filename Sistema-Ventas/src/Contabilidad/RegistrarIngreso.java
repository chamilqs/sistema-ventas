
package Contabilidad;

import java.awt.event.KeyEvent;

/**
 *
 * @author Angelo
 */
public class RegistrarIngreso extends javax.swing.JDialog {


    int xMouse, yMouse;
    public RegistrarIngreso() {
        initComponents();
        setModal(true);
        
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
        lblIDVenta = new javax.swing.JLabel();
        LblDeudor = new javax.swing.JLabel();
        LblTitulo = new javax.swing.JLabel();
        LblMontoPagarResultado = new javax.swing.JLabel();
        LblMontoPagar = new javax.swing.JLabel();
        LblMontoDeuda = new javax.swing.JLabel();
        txtNombreDeudor = new javax.swing.JTextField();
        BtnGuardar = new javax.swing.JButton();
        TotalFaltante = new javax.swing.JLabel();
        PanelMoveLogin = new javax.swing.JPanel();
        btnSalir1 = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txtMontoGasto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setUndecorated(true);

        PanelDatos.setBackground(new java.awt.Color(24, 39, 72));

        LblIdProducto.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblIdProducto.setForeground(new java.awt.Color(255, 255, 255));
        LblIdProducto.setText("ID auto increment: ");

        lblIDVenta.setBackground(new java.awt.Color(255, 255, 255));
        lblIDVenta.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        lblIDVenta.setForeground(new java.awt.Color(0, 0, 0));
        lblIDVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIDVenta.setEnabled(false);
        lblIDVenta.setOpaque(true);

        LblDeudor.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblDeudor.setForeground(new java.awt.Color(255, 255, 255));
        LblDeudor.setText("Descripcion:");

        LblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        LblTitulo.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        LblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LblTitulo.setText(" Realizar Ingreso");

        LblMontoPagarResultado.setBackground(new java.awt.Color(255, 255, 255));
        LblMontoPagarResultado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        LblMontoPagarResultado.setForeground(new java.awt.Color(0, 0, 0));
        LblMontoPagarResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblMontoPagarResultado.setEnabled(false);
        LblMontoPagarResultado.setOpaque(true);

        LblMontoPagar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoPagar.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoPagar.setText("Fecha:");

        LblMontoDeuda.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoDeuda.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoDeuda.setText("Monto Ingreso:");

        txtNombreDeudor.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreDeudor.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombreDeudor.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreDeudor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        BtnGuardar.setBackground(new java.awt.Color(132, 178, 80));
        BtnGuardar.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        BtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        BtnGuardar.setText("GUARDAR");

        TotalFaltante.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        TotalFaltante.setForeground(new java.awt.Color(255, 255, 255));
        TotalFaltante.setText("Nota:");

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

        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        txtMontoGasto.setBackground(new java.awt.Color(255, 255, 255));
        txtMontoGasto.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtMontoGasto.setForeground(new java.awt.Color(51, 51, 51));
        txtMontoGasto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoGastoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(LblIdProducto)
                        .addGap(9, 9, 9)
                        .addComponent(lblIDVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(LblDeudor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreDeudor, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(LblMontoDeuda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMontoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(LblMontoPagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblMontoPagarResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(TotalFaltante)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 44, Short.MAX_VALUE))
            .addComponent(PanelMoveLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(LblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelDatosLayout.setVerticalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addComponent(PanelMoveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblTitulo)
                .addGap(35, 35, 35)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIDVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblDeudor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreDeudor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblMontoDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMontoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblMontoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblMontoPagarResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotalFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(BtnGuardar)
                .addGap(47, 47, 47))
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

    private void txtMontoGastoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoGastoKeyTyped
        char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
                BtnGuardar.doClick();
        }
        
    }//GEN-LAST:event_txtMontoGastoKeyTyped

        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JLabel LblDeudor;
    private javax.swing.JLabel LblIdProducto;
    private javax.swing.JLabel LblMontoDeuda;
    private javax.swing.JLabel LblMontoPagar;
    private javax.swing.JLabel LblMontoPagarResultado;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JPanel PanelMoveLogin;
    private javax.swing.JLabel TotalFaltante;
    private rojerusan.RSMaterialButtonRectangle btnSalir1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblIDVenta;
    private javax.swing.JTextField txtMontoGasto;
    private javax.swing.JTextField txtNombreDeudor;
    // End of variables declaration//GEN-END:variables
}
