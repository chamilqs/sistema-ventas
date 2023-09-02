
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
              btnSalir1MousePressed(evt);
            }    
        });
        PanelMoveLogin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PanelMoveLoginMouseDragged(evt);
            }
        });
        
        
        Utils.setScaledImage(lblGmail, "email.png", 30, 30);
        Utils.setScaledImage(lblFace, "facebook2.png", 30, 30);
        Utils.setScaledImage(lblGit, "Git.png", 30, 30);
        Utils.setScaledImage(lblTelegram, "Tlg.png", 30, 30);
        Utils.setScaledImage(lblIG, "Ig.png", 30, 30);
        
        //Samil
        Utils.setScaledImage(lblGmail2, "email.png", 30, 30);
        Utils.setScaledImage(lblFace2, "facebook2.png", 30, 30);
        Utils.setScaledImage(lblGit2, "github.png", 30, 30);
        Utils.setScaledImage(lblTelegram2, "Tlg.png", 30, 30);
        Utils.setScaledImage(lblIG2, "Ig.png", 30, 30);
        
        //Chris
        Utils.setScaledImage(lblGmail1, "email.png", 30, 30);
        Utils.setScaledImage(lblFace1, "facebook2.png", 30, 30);
        Utils.setScaledImage(lblGit1, "github.png", 30, 30);
        Utils.setScaledImage(lblTelegram1, "Tlg.png", 30, 30);
        Utils.setScaledImage(lblIG1, "Ig.png", 30, 30);

    }
    
    

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelInfo = new javax.swing.JPanel();
        LblUtilidad4 = new javax.swing.JLabel();
        LblUtilidad5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        LblUtilidad6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        LblUtilidad7 = new javax.swing.JLabel();
        lblTelegram = new javax.swing.JLabel();
        lblFace = new javax.swing.JLabel();
        lblGit = new javax.swing.JLabel();
        PanelMoveLogin = new javax.swing.JPanel();
        btnSalir1 = new rojerusan.RSMaterialButtonRectangle();
        lblGmail1 = new javax.swing.JLabel();
        lblGit1 = new javax.swing.JLabel();
        lblFace1 = new javax.swing.JLabel();
        lblGit2 = new javax.swing.JLabel();
        lblFace2 = new javax.swing.JLabel();
        lblGmail2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblIG = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblGmail = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblTelegram2 = new javax.swing.JLabel();
        lblIG2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblTelegram1 = new javax.swing.JLabel();
        lblIG1 = new javax.swing.JLabel();
        LblUtilidad8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        PanelInfo.setBackground(new java.awt.Color(24, 39, 72));
        PanelInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblUtilidad4.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad4.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        LblUtilidad4.setForeground(new java.awt.Color(255, 255, 255));
        LblUtilidad4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblUtilidad4.setText("Angelo Francisco");
        PanelInfo.add(LblUtilidad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        LblUtilidad5.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad5.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        LblUtilidad5.setForeground(new java.awt.Color(255, 255, 255));
        LblUtilidad5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblUtilidad5.setText("Informacion");
        PanelInfo.add(LblUtilidad5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        PanelInfo.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 370, 30));

        LblUtilidad6.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad6.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        LblUtilidad6.setForeground(new java.awt.Color(255, 255, 255));
        LblUtilidad6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblUtilidad6.setText("Christian Baez");
        PanelInfo.add(LblUtilidad6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        PanelInfo.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, 370, 30));

        LblUtilidad7.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad7.setFont(new java.awt.Font("Montserrat", 0, 10)); // NOI18N
        LblUtilidad7.setForeground(new java.awt.Color(255, 255, 255));
        LblUtilidad7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblUtilidad7.setText("Version 0.1 BETA");
        PanelInfo.add(LblUtilidad7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 910, -1, -1));

        lblTelegram.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTelegram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTelegramMousePressed(evt);
            }
        });
        PanelInfo.add(lblTelegram, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 30, 30));

        lblFace.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblFace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblFaceMousePressed(evt);
            }
        });
        PanelInfo.add(lblFace, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 30, 30));

        lblGit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblGit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblGitMousePressed(evt);
            }
        });
        PanelInfo.add(lblGit, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 30, 30));

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

        PanelInfo.add(PanelMoveLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, -1));

        lblGmail1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(lblGmail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 770, 30, 30));

        lblGit1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(lblGit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 730, 30, 30));

        lblFace1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(lblFace1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 690, 30, 30));

        lblGit2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(lblGit2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 30, 30));

        lblFace2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(lblFace2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 30, 30));

        lblGmail2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(lblGmail2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 30, 30));

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Gmail");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 780, -1, -1));

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Telegram");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        PanelInfo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Facebook");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, -1, -1));

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Github");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, -1, -1));

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Gmail");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, -1, -1));

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Facebook");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 700, -1, -1));

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Github");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 740, -1, -1));

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Facebook");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });
        PanelInfo.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel9.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("Github");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });
        PanelInfo.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        lblIG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblIG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblIGMousePressed(evt);
            }
        });
        PanelInfo.add(lblIG, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 30, 30));

        jLabel10.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Instagram");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel10MousePressed(evt);
            }
        });
        PanelInfo.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        lblGmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblGmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblGmailMousePressed(evt);
            }
        });
        PanelInfo.add(lblGmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 30, 30));

        jLabel11.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("Gmail");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });
        PanelInfo.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        jLabel12.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("Instagram");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, -1, -1));

        jLabel13.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("Telegram");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, -1, -1));

        lblTelegram2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(lblTelegram2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 30, 30));

        lblIG2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(lblIG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 30, 30));

        jLabel14.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Instagram");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 860, -1, -1));

        jLabel15.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Telegram");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 820, -1, -1));

        lblTelegram1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(lblTelegram1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 810, 30, 30));

        lblIG1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelInfo.add(lblIG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 850, 30, 30));

        LblUtilidad8.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad8.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        LblUtilidad8.setForeground(new java.awt.Color(255, 255, 255));
        LblUtilidad8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblUtilidad8.setText("Samil Quezada");
        PanelInfo.add(LblUtilidad8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void lblFaceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFaceMousePressed
         String Link = "https://www.facebook.com/";
        abrirRedSocial(Link);
    }//GEN-LAST:event_lblFaceMousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
         String Link = "https://www.facebook.com/";
        abrirRedSocial(Link);
    }//GEN-LAST:event_jLabel8MousePressed

    private void lblGitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGitMousePressed
        String Link = "https://github.com/Angelo2315/Angelo2315";
        abrirRedSocial(Link);
    }//GEN-LAST:event_lblGitMousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        String Link = "https://github.com/Angelo2315/Angelo2315";
        abrirRedSocial(Link);
    }//GEN-LAST:event_jLabel9MousePressed

    private void lblGmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGmailMousePressed
        String Link = "https://mail.google.com/mail/u/0/#inbox";
        abrirRedSocial(Link);
    }//GEN-LAST:event_lblGmailMousePressed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
       String Link = "https://mail.google.com/mail/u/0/#inbox";
        abrirRedSocial(Link);
    }//GEN-LAST:event_jLabel11MousePressed

    private void lblTelegramMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTelegramMousePressed
        String Link = "https://t.me/TellMe_Aboutt";
        abrirRedSocial(Link);
    }//GEN-LAST:event_lblTelegramMousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        String Link = "https://t.me/TellMe_Aboutt";
        abrirRedSocial(Link);
    }//GEN-LAST:event_jLabel2MousePressed

    private void lblIGMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIGMousePressed
        String Link = "https://www.instagram.com/";
        abrirRedSocial(Link);
    }//GEN-LAST:event_lblIGMousePressed

    private void jLabel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MousePressed
        String Link = "https://www.instagram.com/";
        abrirRedSocial(Link);
    }//GEN-LAST:event_jLabel10MousePressed

    
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
    private javax.swing.JLabel LblUtilidad4;
    private javax.swing.JLabel LblUtilidad5;
    private javax.swing.JLabel LblUtilidad6;
    private javax.swing.JLabel LblUtilidad7;
    private javax.swing.JLabel LblUtilidad8;
    private javax.swing.JPanel PanelInfo;
    private javax.swing.JPanel PanelMoveLogin;
    private rojerusan.RSMaterialButtonRectangle btnSalir1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblFace;
    private javax.swing.JLabel lblFace1;
    private javax.swing.JLabel lblFace2;
    private javax.swing.JLabel lblGit;
    private javax.swing.JLabel lblGit1;
    private javax.swing.JLabel lblGit2;
    private javax.swing.JLabel lblGmail;
    private javax.swing.JLabel lblGmail1;
    private javax.swing.JLabel lblGmail2;
    private javax.swing.JLabel lblIG;
    private javax.swing.JLabel lblIG1;
    private javax.swing.JLabel lblIG2;
    private javax.swing.JLabel lblTelegram;
    private javax.swing.JLabel lblTelegram1;
    private javax.swing.JLabel lblTelegram2;
    // End of variables declaration//GEN-END:variables
}
