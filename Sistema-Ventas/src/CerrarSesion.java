
import Caja.PanelCaja;
import DAOImpl.UsuarioDAOImpl;
import Utils.Utils;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import DTO.Usuario;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Angelo duro
 */
public class CerrarSesion extends javax.swing.JDialog {

    int xMouse, yMouse;
    Usuario u = null;
    
    public CerrarSesion() {
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
        
        LblNombreUser.setText("");
        Utils.setScaledImage(IconCerrarSesion, "AccesoUser.png", 100, 100);

    }
    
    public CerrarSesion(Usuario u) {
        initComponents();
        this.u = u;
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
        
        LblNombreUser.setText(u.getNombre());
        Utils.setScaledImage(IconCerrarSesion, "AccesoUser.png", 100, 100);

    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCerrarSesion = new rojerusan.RSMaterialButtonRectangle();
        LblNombreUser = new javax.swing.JLabel();
        IconCerrarSesion = new javax.swing.JLabel();
        PanelMoveLogin = new javax.swing.JPanel();
        btnSalir1 = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(24, 39, 72));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(24, 39, 72));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnCerrarSesion.setBackground(new java.awt.Color(24, 39, 72));
        btnCerrarSesion.setText("Cerrar sesion");
        btnCerrarSesion.setFont(new java.awt.Font("Roboto Light", 1, 10)); // NOI18N
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        LblNombreUser.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        LblNombreUser.setForeground(new java.awt.Color(51, 51, 51));
        LblNombreUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblNombreUser.setText("Angelo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LblNombreUser, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(IconCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IconCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblNombreUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
            .addComponent(PanelMoveLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PanelMoveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
    int option = JOptionPane.showConfirmDialog(this, "¿Estás seguro de cerrar la sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            u.setLogout(fechaHoraActual);
            
            System.out.println(fechaHoraActual);
            
            UsuarioDAOImpl ud = new UsuarioDAOImpl();
            
            PanelCaja pc = new PanelCaja(u);
            pc.cerrarCajaAutomatically(); 
            
        try {
            ud.actualizarLogout(u);
        } catch (SQLException ex) {
            Logger.getLogger(CerrarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //u.setLogout(fechaHoraActual);
            // Cerrar todas las ventanas abiertas
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JFrame && window != this) {
                    window.dispose();
                }
            }
            
            LoginParada loginFrame = new LoginParada();
            loginFrame.setVisible(true);

            this.dispose();
        }
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnSalir1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir1MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_btnSalir1MousePressed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        //System.exit(0);
        this.dispose();
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void PanelMoveLoginMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMoveLoginMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_PanelMoveLoginMouseDragged

    private void btnCerrarSesionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IconCerrarSesion;
    private javax.swing.JLabel LblNombreUser;
    private javax.swing.JPanel PanelMoveLogin;
    private rojerusan.RSMaterialButtonRectangle btnCerrarSesion;
    private rojerusan.RSMaterialButtonRectangle btnSalir1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
