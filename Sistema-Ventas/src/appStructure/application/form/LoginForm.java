package appStructure.application.form;

import Database.Connect.ConexionSingleton;
import Database.DAOImpl.UsuarioDAOImpl;
import Database.DTO.Usuario;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import appStructure.application.Application;

/**
 *
 * @author Samil
 */
public class LoginForm extends javax.swing.JPanel {

    Usuario u = null;
    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
            
    public LoginForm() {
        initComponents();
        init();
    }
    
    public LoginForm(Usuario u) {
        this.u = u;
        initComponents();
        init();
    }

    private void init() {
        setLayout(new LoginFormLayout());
        login.setLayout(new LoginLayout());
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        login.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;"
                + "border:30,40,50,30");

        txtPass.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");
        txtUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nombre de Usuario");
        txtPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Contraseña");
        iconR.setSvgImage("appStructure/icon/svg/red.svg", 25, 25);
        iconY.setSvgImage("appStructure/icon/svg/yellow.svg", 25, 25);
    }

    private void verificarCredenciales() {
        String correoUsuario = txtUser.getText();
        String contrasenaUsuario = new String(txtPass.getPassword()); 

        // Realizar la verificación en la base de datos
        try (Connection connection = c.getConexion()) 
        {
            Statement statement = connection.createStatement();

            String query = "SELECT id FROM Usuario WHERE (Correo = '" + correoUsuario + "' OR nombre = '" +  correoUsuario + "') AND Password = '" + contrasenaUsuario + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                
                String id = resultSet.getString("id"); 
                UsuarioDAOImpl ud = new UsuarioDAOImpl();

                u = ud.get(id);

                LocalDateTime fechaHoraActual = LocalDateTime.now();
                u.setLogin(fechaHoraActual);
                ud.actualizarLogin(u);               

                txtUser.setText("");
                txtPass.setText("");
                LblWrongPass.setText("");
                Application.login();

                c.desconectar();
            } else {
                // Credenciales incorrectas
                LblWrongPass.setForeground(Color.red);
                LblWrongPass.setText("Credenciales incorrectas");
                c.desconectar();

            }
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException ex) {
            
                 Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        cmdLogin = new javax.swing.JButton();
        lbTitle = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        LblWrongPass = new javax.swing.JLabel();
        iconR = new Utils.SVGImage2();
        iconY = new Utils.SVGImage2();

        cmdLogin.setText("Login");
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed(evt);
            }
        });

        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Login");

        lbUser.setText("Nombre de Usuario");

        lbPass.setText("Contraseña");

        LblWrongPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(loginLayout.createSequentialGroup()
                                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPass)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(loginLayout.createSequentialGroup()
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loginLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lbUser))
                            .addGroup(loginLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(loginLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmdLogin))
                            .addGroup(loginLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(LblWrongPass, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addGap(10, 10, 10)
                .addComponent(lbUser)
                .addGap(5, 5, 5)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbPass)
                .addGap(5, 5, 5)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(cmdLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblWrongPass, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        iconR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconRMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iconRMousePressed(evt);
            }
        });

        iconY.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconYMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iconYMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(iconR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iconY, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(319, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(iconY, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoginActionPerformed
        verificarCredenciales();
    }//GEN-LAST:event_cmdLoginActionPerformed

    private void iconRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconRMouseClicked
        System.exit(0);
    }//GEN-LAST:event_iconRMouseClicked

    private void iconRMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconRMousePressed
        System.exit(0);
    }//GEN-LAST:event_iconRMousePressed

    private void iconYMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconYMouseClicked
        Application.minimizeWindow();
    }//GEN-LAST:event_iconYMouseClicked

    private void iconYMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconYMousePressed
        Application.minimizeWindow();
    }//GEN-LAST:event_iconYMousePressed

    private class LoginFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                int width = parent.getWidth();
                int height = parent.getHeight();
                int loginWidth = UIScale.scale(320);
                int loginHeight = login.getPreferredSize().height;
                int x = (width - loginWidth) / 2;
                int y = (height - loginHeight) / 2;
                login.setBounds(x, y, loginWidth, loginHeight);
            }
        }
    }

    private class LoginLayout implements LayoutManager {

        private final int titleGap = 10;
        private final int textGap = 10;
        private final int labelGap = 5;
        private final int buttonGap = 50;

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int height = insets.top + insets.bottom;

                height += lbTitle.getPreferredSize().height;
                height += UIScale.scale(titleGap);
                height += lbUser.getPreferredSize().height;
                height += UIScale.scale(labelGap);
                height += txtUser.getPreferredSize().height;
                height += UIScale.scale(textGap);

                height += lbPass.getPreferredSize().height;
                height += UIScale.scale(labelGap);
                height += txtPass.getPreferredSize().height;
                height += UIScale.scale(buttonGap);
                
                height += LblWrongPass.getPreferredSize().height;
                height += UIScale.scale(titleGap);
                
                height += iconR.getPreferredSize().height;
                height += UIScale.scale(titleGap);
                
                height += iconY.getPreferredSize().height;
                height += UIScale.scale(titleGap);
                
                height += cmdLogin.getPreferredSize().height;
                return new Dimension(0, height);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);

                lbTitle.setBounds(x, y, width, lbTitle.getPreferredSize().height);
                y += lbTitle.getPreferredSize().height + UIScale.scale(titleGap);

                lbUser.setBounds(x, y, width, lbUser.getPreferredSize().height);
                y += lbUser.getPreferredSize().height + UIScale.scale(labelGap);
                txtUser.setBounds(x, y, width, txtUser.getPreferredSize().height);
                y += txtUser.getPreferredSize().height + UIScale.scale(textGap);

                lbPass.setBounds(x, y, width, lbPass.getPreferredSize().height);
                y += lbPass.getPreferredSize().height + UIScale.scale(labelGap);
                txtPass.setBounds(x, y, width, txtPass.getPreferredSize().height);
                y += txtPass.getPreferredSize().height + UIScale.scale(buttonGap);

                LblWrongPass.setBounds(x, y, width, LblWrongPass.getPreferredSize().height);
                y += LblWrongPass.getPreferredSize().height + UIScale.scale(labelGap);
                
                iconR.setBounds(6, 6, iconR.getPreferredSize().width, iconR.getPreferredSize().height);
                iconY.setBounds(30, 6, iconY.getPreferredSize().width, iconY.getPreferredSize().height);
                
                cmdLogin.setBounds(x, y, width, cmdLogin.getPreferredSize().height);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblWrongPass;
    private javax.swing.JButton cmdLogin;
    private Utils.SVGImage2 iconR;
    private Utils.SVGImage2 iconY;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPanel login;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
