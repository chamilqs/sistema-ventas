
import Utils.Utils;
import java.awt.Desktop;
import java.net.URI;


/**
 *
 * @author Angelo
 */
public class Informacion extends javax.swing.JDialog {


    int xMouse, yMouse;
    
    public Informacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       // System.out.println(getClass().getResource("instagram.png"));
        
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
        
        // Samil
        Utils.setScaledImage(iconGmail, "email.png", 30, 30);
        Utils.setScaledImage(iconLinkedin, "linkedin.png", 60, 30);
        Utils.setScaledImage(iconGithub, "Git.png", 30, 30);
        Utils.setScaledImage(iconTelegram, "Tlg.png", 30, 30);
        Utils.setScaledImage(iconInstagram, "Ig.png", 30, 30);

    }
    
    

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelInfo = new javax.swing.JPanel();
        nameLinkedin = new javax.swing.JLabel();
        iconLinkedin = new javax.swing.JLabel();
        LblInformacion = new javax.swing.JLabel();
        nameGithub = new javax.swing.JLabel();
        iconGithub = new javax.swing.JLabel();
        nameGmail = new javax.swing.JLabel();
        iconGmail = new javax.swing.JLabel();
        nameTelegram = new javax.swing.JLabel();
        iconTelegram = new javax.swing.JLabel();
        nameInstagram = new javax.swing.JLabel();
        iconInstagram = new javax.swing.JLabel();
        LblVersion = new javax.swing.JLabel();
        PanelMoveLogin = new javax.swing.JPanel();
        btnSalir = new rojerusan.RSMaterialButtonRectangle();
        nombreCreador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        PanelInfo.setBackground(new java.awt.Color(24, 39, 72));
        PanelInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nameLinkedin.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        nameLinkedin.setForeground(new java.awt.Color(204, 204, 204));
        nameLinkedin.setText("Linkedin");
        nameLinkedin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nameLinkedin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nameLinkedinMousePressed(evt);
            }
        });
        PanelInfo.add(nameLinkedin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        iconLinkedin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        iconLinkedin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iconLinkedinMousePressed(evt);
            }
        });
        PanelInfo.add(iconLinkedin, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 130, 50, 30));

        LblInformacion.setBackground(new java.awt.Color(255, 255, 255));
        LblInformacion.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        LblInformacion.setForeground(new java.awt.Color(255, 255, 255));
        LblInformacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblInformacion.setText("Informacion");
        PanelInfo.add(LblInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        nameGithub.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        nameGithub.setForeground(new java.awt.Color(204, 204, 204));
        nameGithub.setText("Github");
        nameGithub.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nameGithub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nameGithubMousePressed(evt);
            }
        });
        PanelInfo.add(nameGithub, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        iconGithub.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        iconGithub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iconGithubMousePressed(evt);
            }
        });
        PanelInfo.add(iconGithub, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 30, 30));

        nameGmail.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        nameGmail.setForeground(new java.awt.Color(204, 204, 204));
        nameGmail.setText("Gmail");
        nameGmail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nameGmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nameGmailMousePressed(evt);
            }
        });
        PanelInfo.add(nameGmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        iconGmail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        iconGmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iconGmailMousePressed(evt);
            }
        });
        PanelInfo.add(iconGmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 30, 30));

        nameTelegram.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        nameTelegram.setForeground(new java.awt.Color(204, 204, 204));
        nameTelegram.setText("Telegram");
        nameTelegram.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nameTelegram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nameTelegramMousePressed(evt);
            }
        });
        PanelInfo.add(nameTelegram, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        iconTelegram.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        iconTelegram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iconTelegramMousePressed(evt);
            }
        });
        PanelInfo.add(iconTelegram, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 30, 30));

        nameInstagram.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        nameInstagram.setForeground(new java.awt.Color(204, 204, 204));
        nameInstagram.setText("Instagram");
        nameInstagram.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nameInstagram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nameInstagramMousePressed(evt);
            }
        });
        PanelInfo.add(nameInstagram, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        iconInstagram.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        iconInstagram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iconInstagramMousePressed(evt);
            }
        });
        PanelInfo.add(iconInstagram, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 30, 30));

        LblVersion.setBackground(new java.awt.Color(255, 255, 255));
        LblVersion.setFont(new java.awt.Font("Montserrat", 0, 10)); // NOI18N
        LblVersion.setForeground(new java.awt.Color(255, 255, 255));
        LblVersion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblVersion.setText("Version 0.1 BETA");
        PanelInfo.add(LblVersion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, -1));

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
        PanelMoveLogin.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 26, 25));

        PanelInfo.add(PanelMoveLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, -1));

        nombreCreador.setBackground(new java.awt.Color(255, 255, 255));
        nombreCreador.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        nombreCreador.setForeground(new java.awt.Color(255, 255, 255));
        nombreCreador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreCreador.setText("Samil Quezada");
        PanelInfo.add(nombreCreador, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_btnSalirMousePressed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        //System.exit(0);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void PanelMoveLoginMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMoveLoginMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_PanelMoveLoginMouseDragged

    private void iconLinkedinMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconLinkedinMousePressed
         String Link = "https://www.linkedin.com/in/samilquezada-ss20190808301/";
        abrirRedSocial(Link);
    }//GEN-LAST:event_iconLinkedinMousePressed

    private void iconGithubMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconGithubMousePressed
        String Link = "https://github.com/chamilqs";
        abrirRedSocial(Link);
    }//GEN-LAST:event_iconGithubMousePressed

    private void iconGmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconGmailMousePressed
        String Link = "https://mail.google.com/mail/?view=cm&fs=1&to=samilq26@gmail.com";
        abrirRedSocial(Link);
    }//GEN-LAST:event_iconGmailMousePressed

    private void iconTelegramMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconTelegramMousePressed
        String Link = "https://t.me/notsamil";
        abrirRedSocial(Link);
    }//GEN-LAST:event_iconTelegramMousePressed

    private void iconInstagramMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconInstagramMousePressed
        String Link = "https://www.instagram.com/notsamil";
        abrirRedSocial(Link);
    }//GEN-LAST:event_iconInstagramMousePressed

    private void nameInstagramMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameInstagramMousePressed
        String Link = "https://www.instagram.com/notsamil";
        abrirRedSocial(Link);
    }//GEN-LAST:event_nameInstagramMousePressed

    private void nameTelegramMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameTelegramMousePressed
        String Link = "https://t.me/notsamil";
        abrirRedSocial(Link);
    }//GEN-LAST:event_nameTelegramMousePressed

    private void nameGmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameGmailMousePressed
        String Link = "https://mail.google.com/mail/?view=cm&fs=1&to=samilq26@gmail.com";
        abrirRedSocial(Link);
    }//GEN-LAST:event_nameGmailMousePressed

    private void nameGithubMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameGithubMousePressed
        String Link = "https://github.com/chamilqs";
        abrirRedSocial(Link);
    }//GEN-LAST:event_nameGithubMousePressed

    private void nameLinkedinMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameLinkedinMousePressed
        String Link = "https://www.linkedin.com/in/samilquezada-ss20190808301/";
        abrirRedSocial(Link);
    }//GEN-LAST:event_nameLinkedinMousePressed

    
     private void abrirRedSocial(String enlace) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(enlace));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Informacion dialog = new Informacion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblInformacion;
    private javax.swing.JLabel LblVersion;
    private javax.swing.JPanel PanelInfo;
    private javax.swing.JPanel PanelMoveLogin;
    private rojerusan.RSMaterialButtonRectangle btnSalir;
    private javax.swing.JLabel iconGithub;
    private javax.swing.JLabel iconGmail;
    private javax.swing.JLabel iconInstagram;
    private javax.swing.JLabel iconLinkedin;
    private javax.swing.JLabel iconTelegram;
    private javax.swing.JLabel nameGithub;
    private javax.swing.JLabel nameGmail;
    private javax.swing.JLabel nameInstagram;
    private javax.swing.JLabel nameLinkedin;
    private javax.swing.JLabel nameTelegram;
    private javax.swing.JLabel nombreCreador;
    // End of variables declaration//GEN-END:variables
}
