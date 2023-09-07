
import Connect.Conexion;
import DAOImpl.UsuarioDAOImpl;
import Utils.Utils;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DTO.Usuario;
import java.time.LocalDateTime;

/**
 *
 * @author Angelo
 */

// Hola
public class LoginParada extends javax.swing.JFrame {

     int xMouse, yMouse;
     Usuario u = null;
     
    public LoginParada() {
        initComponents();
        
          
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
        
        Utils.setScaledImage(IconLogo, "MiLogo.png", 300, 300);

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelMoveLogin = new javax.swing.JPanel();
        btnSalir = new rojerusan.RSMaterialButtonRectangle();
        jPanel1 = new javax.swing.JPanel();
        IconLogo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        TxtCorreo_Nombre_User = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        TxtPassword = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        BtnIniciarSesion = new rojerusan.RSMaterialButtonRectangle();
        LblNotificacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(24, 39, 72));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelMoveLogin.setBackground(new java.awt.Color(24, 39, 72));
        PanelMoveLogin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PanelMoveLoginMouseDragged(evt);
            }
        });
        PanelMoveLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir.setBackground(new java.awt.Color(24, 39, 72));
        btnSalir.setText("X");
        btnSalir.setFont(new java.awt.Font("Roboto Light", 1, 10)); // NOI18N
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
        PanelMoveLogin.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 26, 25));

        getContentPane().add(PanelMoveLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 40));

        jPanel1.setBackground(new java.awt.Color(24, 39, 72));
        jPanel1.setPreferredSize(new java.awt.Dimension(640, 437));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TxtCorreo_Nombre_User.setForeground(new java.awt.Color(153, 153, 153));
        TxtCorreo_Nombre_User.setText("Correo o Usuario");
        TxtCorreo_Nombre_User.setBorder(null);
        TxtCorreo_Nombre_User.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TxtCorreo_Nombre_UserMousePressed(evt);
            }
        });
        TxtCorreo_Nombre_User.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtCorreo_Nombre_UserKeyTyped(evt);
            }
        });
        jPanel3.add(TxtCorreo_Nombre_User, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 190, 30));

        jLabel1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 48)); // NOI18N
        jLabel1.setText("Bienvenido");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 50, -1, 60));

        TxtPassword.setForeground(new java.awt.Color(153, 153, 153));
        TxtPassword.setText("**********");
        TxtPassword.setBorder(null);
        TxtPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TxtPasswordMousePressed(evt);
            }
        });
        TxtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtPasswordKeyTyped(evt);
            }
        });
        jPanel3.add(TxtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 190, 30));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 230, 10));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 230, 10));

        BtnIniciarSesion.setBackground(new java.awt.Color(24, 39, 72));
        BtnIniciarSesion.setText("INICIAR SESION");
        BtnIniciarSesion.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        BtnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIniciarSesionActionPerformed(evt);
            }
        });
        jPanel3.add(BtnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 120, 40));

        LblNotificacion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jPanel3.add(LblNotificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 260, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IconLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(IconLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 31, 650, 370));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TxtCorreo_Nombre_UserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtCorreo_Nombre_UserMousePressed
          if (TxtCorreo_Nombre_User.getText().equals("Correo o Usuario")) {
                TxtCorreo_Nombre_User.setText("");
                TxtCorreo_Nombre_User.setForeground(Color.black);
        }

        if (String.valueOf(TxtPassword.getPassword()).isEmpty()) {
            TxtPassword.setText("**********");
            TxtPassword.setForeground(Color.gray);
        }
    }//GEN-LAST:event_TxtCorreo_Nombre_UserMousePressed

    private void TxtPasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtPasswordMousePressed
        if (String.valueOf(TxtPassword.getPassword()).equals("**********")) {
        TxtPassword.setText("");
        TxtPassword.setForeground(Color.black);
    }

    if (TxtCorreo_Nombre_User.getText().isEmpty()) {
        TxtCorreo_Nombre_User.setText("Correo o Usuario");
        TxtCorreo_Nombre_User.setForeground(Color.gray); 
    }
    }//GEN-LAST:event_TxtPasswordMousePressed

    private void TxtCorreo_Nombre_UserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCorreo_Nombre_UserKeyTyped

        char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
            verificarCredenciales();  
        }
    }//GEN-LAST:event_TxtCorreo_Nombre_UserKeyTyped

    private void TxtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPasswordKeyTyped

        char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
            verificarCredenciales();
            
        }
    }//GEN-LAST:event_TxtPasswordKeyTyped

    private void BtnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIniciarSesionActionPerformed

        verificarCredenciales();
        
    }//GEN-LAST:event_BtnIniciarSesionActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMousePressed
       xMouse = evt.getX();
       yMouse = evt.getY();
    }//GEN-LAST:event_btnSalirMousePressed

    private void PanelMoveLoginMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMoveLoginMouseDragged
       int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_PanelMoveLoginMouseDragged

    private void verificarCredenciales() {
    String correoUsuario = TxtCorreo_Nombre_User.getText();
    String contrasenaUsuario = new String(TxtPassword.getPassword()); 

    // Realizar la verificación en la base de datos
    try (Connection connection = Conexion.conectar()) {
        Statement statement = connection.createStatement();

        String query = "SELECT id FROM Usuario WHERE (Correo = '" + correoUsuario + "' OR nombre = '" +  correoUsuario + "') AND Password = '" + contrasenaUsuario + "'";
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            // Credenciales correctas
            String id = resultSet.getString("id"); 
            UsuarioDAOImpl ud = new UsuarioDAOImpl();
            
            u = ud.get(id);

            LblNotificacion.setForeground(Color.green);
            LblNotificacion.setText("Credenciales correctas");
            
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            u.setLogin(fechaHoraActual);
            ud.actualizarLogin(u);               
                    
            FramePrincipal form = new FramePrincipal(u);
            form.setUsuarioRol(u.getNivelAcceso()); // Pasa el rol de usuario
            form.inicializar(); 
            form.setVisible(true);
            this.dispose();
            
        } else {
            // Credenciales incorrectas
            LblNotificacion.setForeground(Color.red);
            LblNotificacion.setText("Credenciales incorrectas");
        }
    } catch (SQLException e) {
        e.printStackTrace();

    }
}
 
    
public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(LoginParada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(LoginParada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(LoginParada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(LoginParada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            LoginParada loginParada = new LoginParada();
            loginParada.setLocationRelativeTo(null); 
            loginParada.setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle BtnIniciarSesion;
    private javax.swing.JLabel IconLogo;
    private javax.swing.JLabel LblNotificacion;
    private javax.swing.JPanel PanelMoveLogin;
    private javax.swing.JTextField TxtCorreo_Nombre_User;
    private javax.swing.JPasswordField TxtPassword;
    private rojerusan.RSMaterialButtonRectangle btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
