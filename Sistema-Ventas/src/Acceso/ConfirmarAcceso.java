
package Acceso;

import DAOImpl.UsuarioDAOImpl;
import Utils.Utils;
import java.time.LocalDateTime;
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
import java.sql.PreparedStatement;


import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.time.LocalDateTime;

/**
 *
 * @author Angelo
 */

 
public class ConfirmarAcceso extends javax.swing.JDialog {

    /**
     * Creates new form ConfirmarAcceso
     */

   
    private VerificacionListener verificacionListener;
     int xMouse, yMouse;
     Usuario u = null;  
     private boolean isPasswordClicked = false;
     
    public ConfirmarAcceso(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //this.u = u;
        initComponents();
       // this.framePrincipal = framePrincipal;
        
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
        
            //
       txtPass.setText("********");
       txtPass.setForeground(Color.GRAY);
       txtPass.addFocusListener(new FocusAdapter() {

           public void focusGained(FocusEvent e) {
               if (!isPasswordClicked) {
                   txtPass.setText("");
                   txtPass.setForeground(Color.BLACK);
                   isPasswordClicked = true;
               }
           }


           public void focusLost(FocusEvent e) {
               if (txtPass.getPassword().length == 0) {
                   txtPass.setText("*******");
                   txtPass.setForeground(Color.GRAY);
                   isPasswordClicked = false;
               }
           }
       });

        
        
         Utils.setScaledImage(IconLogo1, "MiLogo.png", 200, 200);
    } 
    
        public ConfirmarAcceso(java.awt.Frame parent, boolean modal, Usuario u) {
        super(parent, modal);
        this.u = u;
        initComponents();
       // this.framePrincipal = framePrincipal;
        
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
        
            //
       txtPass.setText("********");
       txtPass.setForeground(Color.GRAY);
       txtPass.addFocusListener(new FocusAdapter() {

           public void focusGained(FocusEvent e) {
               if (!isPasswordClicked) {
                   txtPass.setText("");
                   txtPass.setForeground(Color.BLACK);
                   isPasswordClicked = true;
               }
           }


           public void focusLost(FocusEvent e) {
               if (txtPass.getPassword().length == 0) {
                   txtPass.setText("*******");
                   txtPass.setForeground(Color.GRAY);
                   isPasswordClicked = false;
               }
           }
       });

        
        
         Utils.setScaledImage(IconLogo1, "MiLogo.png", 200, 200);
    } 
    
        public static interface VerificacionListener {
        void onVerificacionExitosa();
        void onVerificacionFallida();
    }
        public void setVerificacionListener(VerificacionListener listener) {
        this.verificacionListener = listener;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        IconLogo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtPass = new javax.swing.JPasswordField();
        IconLogo1 = new javax.swing.JLabel();
        LblUtilidad4 = new javax.swing.JLabel();
        LblNotificacion = new javax.swing.JLabel();
        Contrasenha = new javax.swing.JLabel();
        PanelMoveLogin = new javax.swing.JPanel();
        btnSalir1 = new rojerusan.RSMaterialButtonRectangle();

        IconLogo.setForeground(new java.awt.Color(255, 255, 255));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(24, 39, 72));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPass.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtPass.setText("jPasswordField1");
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPassMousePressed(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassKeyTyped(evt);
            }
        });
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 289, 140, -1));

        IconLogo1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(IconLogo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 187, 151));

        LblUtilidad4.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad4.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        LblUtilidad4.setForeground(new java.awt.Color(255, 255, 255));
        LblUtilidad4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblUtilidad4.setText("Validar acceso");
        jPanel1.add(LblUtilidad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        LblNotificacion.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        LblNotificacion.setForeground(new java.awt.Color(255, 255, 255));
        LblNotificacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(LblNotificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 180, 20));

        Contrasenha.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        Contrasenha.setForeground(new java.awt.Color(255, 255, 255));
        Contrasenha.setText("Contraseña:");
        jPanel1.add(Contrasenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 290, -1, -1));

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
                .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 364, Short.MAX_VALUE))
        );
        PanelMoveLoginLayout.setVerticalGroup(
            PanelMoveLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMoveLoginLayout.createSequentialGroup()
                .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel1.add(PanelMoveLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void txtPassMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassMousePressed

    private void txtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyTyped
       char teclapress = evt.getKeyChar();
       
       if(teclapress == KeyEvent.VK_ENTER){
           verificarCredenciales();
           //this.dispose();
       }
    }//GEN-LAST:event_txtPassKeyTyped


  private void verificarCredenciales() {
    String contrasenaIngresada = new String(txtPass.getPassword());
    String nombreUsuario = u.getNombre(); // Asegúrate de obtener el nombre de usuario ingresado

    // Realizar la verificación en la base de datos
    try (Connection connection = Conexion.conectar()) {
        String query = "SELECT id FROM Usuario WHERE nombre = ? AND Password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombreUsuario);
            statement.setString(2, contrasenaIngresada);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String usuarioID = resultSet.getString("id");
                    UsuarioDAOImpl ud = new UsuarioDAOImpl();

                    // Obtener el objeto Usuario usando el ID
                    u = ud.get(usuarioID);

                    // Contraseña correcta
                    LblNotificacion.setForeground(Color.green);
                    LblNotificacion.setText("Contraseña correcta");
                    isPasswordClicked = true; // Establecer el valor booleano a true

                    if (verificacionListener != null) {
                        verificacionListener.onVerificacionExitosa();
                        this.dispose();
                    }

                } else {
                    // Contraseña incorrecta
                    LblNotificacion.setForeground(Color.red);
                    LblNotificacion.setText("Contraseña incorrecta");
                    isPasswordClicked = false; // Establecer el valor booleano a false
                    if (verificacionListener != null) {
                        verificacionListener.onVerificacionFallida();
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}





    
    
    
    
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ConfirmarAcceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ConfirmarAcceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ConfirmarAcceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ConfirmarAcceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ConfirmarAcceso dialog = new ConfirmarAcceso(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Contrasenha;
    private javax.swing.JLabel IconLogo;
    private javax.swing.JLabel IconLogo1;
    private javax.swing.JLabel LblNotificacion;
    private javax.swing.JLabel LblUtilidad4;
    private javax.swing.JPanel PanelMoveLogin;
    private rojerusan.RSMaterialButtonRectangle btnSalir1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}
