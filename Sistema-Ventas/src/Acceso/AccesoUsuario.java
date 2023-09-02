package Acceso;

import Acceso.*;
import Connect.Conexion;
import DAOImpl.UsuarioDAOImpl;
import DTO.Usuario;
import Updater.Updater;
import Utils.MaterialTabbed;
import Utils.Utils;

import java.awt.Color;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angelo
 */
public class AccesoUsuario extends javax.swing.JPanel {


    private Timer notificationTimer;

    public AccesoUsuario() {
    initComponents();



    try {
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };
        Updater.actualizarTabla("Usuario",modelo);
        TablaAccess.setModel(modelo);
        //TablaAccess.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        

    } catch (SQLException ex) {
        Logger.getLogger(AccesoUsuario.class.getName()).log(Level.SEVERE, null, ex);
    }


    Utils.setScaledImage(IconAccesoAzul1, "AccesoUser.png", 150, 150);
    Utils.setScaledImage(IconAccesoAzul2, "AccesoUser.png", 150, 150);
    //utils.setScaledImage("Principal.png", 20, 20);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        TabRegistroUser = new MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtIdUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CheckAdmin = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtconfirmarPassword = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        BtnRegistrarUsuario = new rojerusan.RSMaterialButtonRectangle();
        jLabel12 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        LblNotificacion = new javax.swing.JLabel();
        aterisco = new javax.swing.JLabel();
        aterisco2 = new javax.swing.JLabel();
        IconAccesoAzul1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnModificador = new rojerusan.RSMaterialButtonRectangle();
        btnEliminar = new rojerusan.RSMaterialButtonRectangle();
        jLabel13 = new javax.swing.JLabel();
        txtIdUsuario2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNombre2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtCorreo2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtPassword2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtconfirmarPassword2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        CheckAdmin2 = new javax.swing.JCheckBox();
        LblNotificacion2 = new javax.swing.JLabel();
        aterisco3 = new javax.swing.JLabel();
        aterisco4 = new javax.swing.JLabel();
        IconAccesoAzul2 = new javax.swing.JLabel();
        PanelBarBlue = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaAccess = new javax.swing.JTable();

        jLabel14.setText("jLabel14");

        jLabel15.setText("jLabel15");

        jLabel16.setText("jLabel16");

        setBackground(new java.awt.Color(24, 39, 72));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabRegistroUser.setBackground(new java.awt.Color(132, 178, 80));
        TabRegistroUser.setForeground(new java.awt.Color(255, 255, 255));
        TabRegistroUser.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(24, 39, 72));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtIdUsuario.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtIdUsuario.setForeground(new java.awt.Color(0, 0, 0));
        txtIdUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel2.add(txtIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 115, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Nivel de acceso:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        CheckAdmin.setText("Admin");
        jPanel2.add(CheckAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Nombre:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Correo:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Password:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        txtconfirmarPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtconfirmarPassword.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtconfirmarPassword.setForeground(new java.awt.Color(0, 0, 0));
        txtconfirmarPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel2.add(txtconfirmarPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 115, -1));

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));
        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 115, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Confi. Pass:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        BtnRegistrarUsuario.setText("Registrar");
        BtnRegistrarUsuario.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrarUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(BtnRegistrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 130, 40));

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("ID usuario:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtCorreo.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel2.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 115, -1));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 115, -1));

        LblNotificacion.setForeground(new java.awt.Color(255, 0, 51));
        LblNotificacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(LblNotificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 240, 20));

        aterisco.setForeground(new java.awt.Color(255, 0, 51));
        jPanel2.add(aterisco, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 20, 20));

        aterisco2.setForeground(new java.awt.Color(255, 0, 51));
        jPanel2.add(aterisco2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 20, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 250, 300));

        IconAccesoAzul1.setBackground(new java.awt.Color(24, 39, 72));
        IconAccesoAzul1.setOpaque(true);
        jPanel1.add(IconAccesoAzul1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 220, 190));

        TabRegistroUser.addTab("                                      REGISTRAR                                          ", jPanel1);

        jPanel3.setBackground(new java.awt.Color(24, 39, 72));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnModificador.setText("Modificar");
        btnModificador.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        btnModificador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificadorActionPerformed(evt);
            }
        });
        jPanel4.add(btnModificador, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 110, 40));

        btnEliminar.setText("Eliminar");
        btnEliminar.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 110, 40));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("ID usuario:");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtIdUsuario2.setBackground(new java.awt.Color(255, 255, 255));
        txtIdUsuario2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtIdUsuario2.setForeground(new java.awt.Color(0, 0, 0));
        txtIdUsuario2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel4.add(txtIdUsuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 115, -1));

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Nombre:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        txtNombre2.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtNombre2.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel4.add(txtNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 115, -1));

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Correo:");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        txtCorreo2.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreo2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCorreo2.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel4.add(txtCorreo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 115, -1));

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Password:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        txtPassword2.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtPassword2.setForeground(new java.awt.Color(0, 0, 0));
        txtPassword2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel4.add(txtPassword2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 115, -1));

        jLabel20.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Confi. Pass:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        txtconfirmarPassword2.setBackground(new java.awt.Color(255, 255, 255));
        txtconfirmarPassword2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtconfirmarPassword2.setForeground(new java.awt.Color(0, 0, 0));
        txtconfirmarPassword2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel4.add(txtconfirmarPassword2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 115, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Nivel de acceso:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        CheckAdmin2.setText("Admin");
        jPanel4.add(CheckAdmin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, -1));

        LblNotificacion2.setForeground(new java.awt.Color(255, 0, 51));
        LblNotificacion2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(LblNotificacion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 250, 20));

        aterisco3.setForeground(new java.awt.Color(255, 0, 51));
        aterisco3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(aterisco3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 20, 20));

        aterisco4.setForeground(new java.awt.Color(255, 0, 51));
        aterisco4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(aterisco4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 20, 20));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 250, 300));

        IconAccesoAzul2.setBackground(new java.awt.Color(24, 39, 72));
        IconAccesoAzul2.setOpaque(true);
        jPanel3.add(IconAccesoAzul2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 220, 190));

        TabRegistroUser.addTab("                                      EDITAR                                              ", jPanel3);

        add(TabRegistroUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 310, 660));

        PanelBarBlue.setBackground(new java.awt.Color(24, 39, 72));
        PanelBarBlue.setMinimumSize(new java.awt.Dimension(1005, 40));
        PanelBarBlue.setPreferredSize(new java.awt.Dimension(1005, 40));
        PanelBarBlue.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(PanelBarBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 41));

        TablaAccess.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Nivel de acceso", "Correo", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaAccess.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaAccess);
        if (TablaAccess.getColumnModel().getColumnCount() > 0) {
            TablaAccess.getColumnModel().getColumn(0).setResizable(false);
            TablaAccess.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaAccess.getColumnModel().getColumn(1).setResizable(false);
            TablaAccess.getColumnModel().getColumn(2).setResizable(false);
            TablaAccess.getColumnModel().getColumn(2).setPreferredWidth(20);
            TablaAccess.getColumnModel().getColumn(3).setResizable(false);
            TablaAccess.getColumnModel().getColumn(3).setPreferredWidth(100);
            TablaAccess.getColumnModel().getColumn(4).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 640, 560));
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificadorActionPerformed
       ModificarUsuario();
    }//GEN-LAST:event_btnModificadorActionPerformed

    private void BtnRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarUsuarioActionPerformed
        RegistrarUsuario();
    }//GEN-LAST:event_BtnRegistrarUsuarioActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarUsuario();  
    }//GEN-LAST:event_btnEliminarActionPerformed

//    private void mostrarContrasena() {
//        if (txtPassword.getEchoChar() == '*') {
//            txtPassword.setEchoChar((char) 0); // Mostrar la contraseña
//        } else {
//            txtPassword.setEchoChar('*'); // Ocultar la contraseña
//        }
//    }
    
    private void RegistrarUsuario() {
    String IdUser = txtIdUsuario.getText();
    String nombre = txtNombre.getText();
    String Correo = txtCorreo.getText();
    String Password = txtPassword.getText();
    String confirmarpassword = txtconfirmarPassword.getText();
    String nivelAcceso = null;
    
    //validar existencia de usernombre
    if (nombreUsuarioExiste(nombre)) {
        LblNotificacion.setForeground(Color.red);
        LblNotificacion.setText("El nombre de usuario ya existe");
        return;
    }

    // Validar campos Empty
    if (IdUser.isEmpty() || nombre.isEmpty() || Correo.isEmpty() || Password.isEmpty() || confirmarpassword.isEmpty()) {
        LblNotificacion.setForeground(Color.red);
        LblNotificacion.setText("Debe completar todos los campos");
        return;
    }

    // Validar longitud mínima de la clave
    if (Password.length() < 8) {
        LblNotificacion.setForeground(Color.red);
        LblNotificacion.setText("La clave debe tener al menos 8 caracteres");
        return;
    }

    // Validar ID y correo
    if (!validarDuplicado(IdUser, Correo)) {
        return; 
    }
    
    // Verificar Pass
    if (confirmarpassword.equals(Password)) {
        LblNotificacion.setForeground(Color.green);
        LblNotificacion.setText("Cuenta Registrada Exitosamente");
        limpiarText();

        // Verificar si es admin
        if (CheckAdmin.isSelected()) {
            nivelAcceso = "admin";
        } else {
            nivelAcceso = "user";
        }

        try (Connection connection = Conexion.conectar()) {
            Usuario u = new Usuario(IdUser, nombre, Correo, Password, nivelAcceso);
            UsuarioDAOImpl d = new UsuarioDAOImpl();
            int result;
            try {
                result = d.insert(u); // Guardar
                System.out.print(result);
            } catch (SQLException ex) {
                Logger.getLogger(AccesoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            DefaultTableModel modelo = new DefaultTableModel();
            Updater.actualizarTabla("Usuario",modelo);
            TablaAccess.setModel(modelo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        aterisco.setText("*");
        aterisco2.setText("*");
        LblNotificacion.setBackground(Color.red);
        LblNotificacion.setText("La contraseña no coincide");
        limpiarPass();
    }
}

    
    private boolean nombreUsuarioExiste(String nombreUsuario) {
        try (Connection connection = Conexion.conectar()) {
            String query = "SELECT COUNT(*) AS count FROM Usuario WHERE nombre = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nombreUsuario);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt("count");
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void ModificarUsuario() {
    String IdUser = txtIdUsuario2.getText();
    String nombre = txtNombre2.getText();
    String Correo = txtCorreo2.getText();
    String Password = txtPassword2.getText();
    String confirmarpassword = txtconfirmarPassword2.getText();
    String nivelAcceso = null;

    // Validar campos Empty
    if (IdUser.isEmpty() || nombre.isEmpty() || Correo.isEmpty() || Password.isEmpty() || confirmarpassword.isEmpty()) {
        LblNotificacion2.setForeground(Color.red);
        LblNotificacion2.setText("Debe completar todos los campos");
        return;
    }

//    // Validar ID y correo
//    if (!validarDuplicado(IdUser, Correo)) {
//        return; // No continuar si hay problemas con el ID o el correo
//    }

    // Verificar Pass
    if (confirmarpassword.equals(Password)) {
        // Verificar si el nuevo correo ya existe
        if (!validarNuevoCorreo(IdUser, Correo)) {
            txtCorreo2.setText(""); // Limpiar el campo de correo
            LblNotificacion2.setForeground(Color.red);
            LblNotificacion2.setText("El correo ya está en uso. Pruebe otro");
            return;
        }

        LblNotificacion2.setForeground(Color.green);
        LblNotificacion2.setText("Cuenta Modificada Exitosamente");
        limpiarText2();
        
        /* Iniciar un temporizador para borrar el mensaje después de 2 segundos
        debe ponerse en milisegundo*/
        
        if (notificationTimer != null) {
            notificationTimer.stop();
        }
        notificationTimer = new Timer(2000, e -> {
            LblNotificacion2.setText(""); // Esto es borrar el mensaje obvi
        });
        notificationTimer.setRepeats(false); // Esto es para ejecutarlo solo una vez
        notificationTimer.start();

        // Verificar si es admin
        if (CheckAdmin2.isSelected()) {
            nivelAcceso = "admin";
        } else {
            nivelAcceso = "user";
        }

        try (Connection connection = Conexion.conectar()) {
            Usuario u = new Usuario(IdUser, nombre, Correo, Password, nivelAcceso);
            UsuarioDAOImpl d = new UsuarioDAOImpl();
            int result;
            try {
                result = d.update(u); // Actualizar
                System.out.print(result);
            } catch (SQLException ex) {
                Logger.getLogger(AccesoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            DefaultTableModel modelo = new DefaultTableModel();
            Updater actualizador = new Updater(); // Cree una instancia con el Updater
            actualizador.actualizarTabla("Usuario", modelo);
            TablaAccess.setModel(modelo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        aterisco3.setText("*");
        aterisco4.setText("*");
       // LblNotificacion2.setBackground(Color.red);
        LblNotificacion2.setForeground(Color.red); // Cambio de color
        LblNotificacion2.setText("Password No Coincide");
        limpiarPass2();
    }
}
   
    private void EliminarUsuario() {
        String IdUser = txtIdUsuario2.getText();

        if (IdUser.isEmpty()) {
            LblNotificacion2.setForeground(Color.red);
            LblNotificacion2.setText("Debe ingresar un ID para eliminar");
            return;
        }

        // Bloquear los campos durante 3 segundos
        txtNombre2.setEnabled(false);
        txtCorreo2.setEnabled(false);
        txtPassword2.setEnabled(false);
        txtconfirmarPassword2.setEnabled(false);
        CheckAdmin2.setEnabled(false);

        LblNotificacion2.setForeground(Color.yellow);
        LblNotificacion2.setText("Usuario eliminado. Presione Guardar para confirmar");

        // Iniciar un temporizador para habilitar los campos después de 3 segundos
        if (notificationTimer != null) {
            notificationTimer.stop();
        }
        notificationTimer = new Timer(3000, e -> {
            txtNombre2.setEnabled(true);
            txtCorreo2.setEnabled(true);
            txtPassword2.setEnabled(true);
            txtconfirmarPassword2.setEnabled(true);
            CheckAdmin2.setEnabled(true);
            limpiarText2();
            LblNotificacion2.setText(""); // Borrar el mensaje de confirmación
        });
        notificationTimer.setRepeats(false); // Ejecutar solo una vez
        notificationTimer.start();

        try (Connection connection = Conexion.conectar()) {
            UsuarioDAOImpl d = new UsuarioDAOImpl();
            Usuario u = d.get(IdUser);

            if (u != null) {
                int result;
                try {
                    result = d.delete(u); // Eliminar
                    if (result > 0) {
                        LblNotificacion2.setForeground(Color.green);
                        LblNotificacion2.setText("Usuario eliminado exitosamente");

                        DefaultTableModel modelo = new DefaultTableModel();
                        Updater actualizador = new Updater();
                        actualizador.actualizarTabla("Usuario", modelo);
                        TablaAccess.setModel(modelo);
                    } else {
                        LblNotificacion2.setForeground(Color.red);
                        LblNotificacion2.setText("El usuario no fue encontrado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AccesoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                LblNotificacion2.setForeground(Color.red);
                LblNotificacion2.setText("El usuario no fue encontrado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean validarDuplicado(String id, String correo) {
        try {
            Connection connection = Conexion.conectar();
            Statement statement = connection.createStatement();

            // Verificar si el ID ya existe
            ResultSet resultId = statement.executeQuery("SELECT * FROM Usuario WHERE id = '" + id + "'");
            if (resultId.next()) {
                LblNotificacion.setForeground(Color.red);
                LblNotificacion.setText("El ID ya está en uso.");
                return false;
            }

            // Verificar si el correo ya existe
            ResultSet resultCorreo = statement.executeQuery("SELECT * FROM Usuario WHERE Correo = '" + correo + "'");
            if (resultCorreo.next()) {
                LblNotificacion.setForeground(Color.red);
                LblNotificacion.setText("El correo electrónico ya está en uso.");
                return false;
            }

            return true; // Ambos ID y correo son válidos y no existen en la base de datos
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hubo un error en la consulta, asumimos que no es válido
        }
    }

    private boolean validarNuevoCorreo(String id, String nuevoCorreo) {
    try {
        Connection connection = Conexion.conectar();
        Statement statement = connection.createStatement();

        // Verificar si el correo ya existe para otros usuarios
        String query = "SELECT * FROM Usuario WHERE Correo = '" + nuevoCorreo + "' AND id <> '" + id + "'";
        ResultSet resultCorreo = statement.executeQuery(query);

        if (resultCorreo.next()) {
            LblNotificacion2.setForeground(Color.red);
            LblNotificacion2.setText("El correo electrónico ya está en uso. Pruebe otro.");
            return false;
        }

        return true; // El nuevo correo es válido y no está en uso por otros usuarios
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Hubo un error en la consulta, asumimos que no es válido
    }
}

    private void limpiarPass() {
        txtPassword.setText("");
       // fechaChooser.setDate(null);
        txtconfirmarPassword.setText("");
    }
    
    private void limpiarPass2() {
        txtPassword2.setText("");
       // fechaChooser.setDate(null);
        txtconfirmarPassword2.setText("");
    }
    
    private void limpiarText() {
        txtIdUsuario.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtPassword.setText("");
       // fechaChooser.setDate(null);
        txtconfirmarPassword.setText("");
    }
    
    private void limpiarText2() {
        txtIdUsuario2.setText("");
        txtNombre2.setText("");
        txtCorreo2.setText("");
        txtPassword2.setText("");
       // fechaChooser.setDate(null);
        txtconfirmarPassword2.setText("");
    }

   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle BtnRegistrarUsuario;
    private javax.swing.JCheckBox CheckAdmin;
    private javax.swing.JCheckBox CheckAdmin2;
    private javax.swing.JLabel IconAccesoAzul1;
    private javax.swing.JLabel IconAccesoAzul2;
    private javax.swing.JLabel LblNotificacion;
    private javax.swing.JLabel LblNotificacion2;
    private javax.swing.JPanel PanelBarBlue;
    private MaterialTabbed TabRegistroUser;
    private javax.swing.JTable TablaAccess;
    private javax.swing.JLabel aterisco;
    private javax.swing.JLabel aterisco2;
    private javax.swing.JLabel aterisco3;
    private javax.swing.JLabel aterisco4;
    private rojerusan.RSMaterialButtonRectangle btnEliminar;
    private rojerusan.RSMaterialButtonRectangle btnModificador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCorreo2;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtIdUsuario2;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPassword2;
    private javax.swing.JTextField txtconfirmarPassword;
    private javax.swing.JTextField txtconfirmarPassword2;
    // End of variables declaration//GEN-END:variables
}
