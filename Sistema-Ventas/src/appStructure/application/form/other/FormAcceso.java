package appStructure.application.form.other;

import Database.Connect.ConexionSingleton;
import Database.DAOImpl.UsuarioDAOImpl;
import Database.DTO.Usuario;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Samil
 */
public class FormAcceso extends javax.swing.JPanel {

    private Timer notificationTimer;
    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormAcceso() {       
        initComponents(); 
        init();
        
    }
    
    private void init() {
        
        DefaultTableModel modelo = (DefaultTableModel) TablaAccess.getModel();
        actualizarTabla(modelo);
        TablaAccess.setModel(modelo);
        iconUser.setSvgImage("appStructure/icon/svg/newUser.svg", 170, 170);
        iconEdit.setSvgImage("appStructure/icon/svg/edit.svg", 170, 170);
        
        // Agregar un ListSelectionListener a la tabla
        TablaAccess.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            // Obtener la fila seleccionada
            int selectedRow = TablaAccess.getSelectedRow();
            
            // Verificar si se ha seleccionado una fila
            if (selectedRow != -1) {
                // Obtener los valores de las celdas de esa fila
                String id = TablaAccess.getValueAt(selectedRow, 0).toString();
                String nombre = TablaAccess.getValueAt(selectedRow, 1).toString();
                String nivelAcceso = TablaAccess.getValueAt(selectedRow, 2).toString();
                String correo = TablaAccess.getValueAt(selectedRow, 3).toString();
                String contrasena = TablaAccess.getValueAt(selectedRow, 4).toString();
                                
                // Panel Editar
                
                txtIDUsuarioE.setText(id);
                txtNombreE.setText(nombre);
                txtCorreoE.setText(correo);
                txtPasswordE.setText(contrasena);
                txtConfirmarPassE.setText(contrasena);
                if(nivelAcceso.equals("admin")){
                
                    CheckAdminE.setSelected(true);
                } else {
                
                    CheckAdminE.setSelected(false);
                }
                
                
                
            }
        }
    }
});

        
    }
    
    @SuppressWarnings("UnusedAssignment")
    private void RegistrarUsuario() {
        String IdUser = txtIdUsuarioR.getText();
        String nombre = txtNombreR.getText();
        String Correo = txtCorreoR.getText();
        String Password = txtPasswordR.getText();
        String confirmarpassword = txtConfirmarPassR.getText();
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
            if (CheckAdminR.isSelected()) {
                nivelAcceso = "admin";
            } else {
                nivelAcceso = "user";
            }

            Usuario u = new Usuario(IdUser, nombre, Correo, Password, nivelAcceso);
            UsuarioDAOImpl d = new UsuarioDAOImpl();
            int result;
            try {
                result = d.insert(u); // Guardar
                System.out.print(result);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(FormAcceso.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            DefaultTableModel modelo = (DefaultTableModel) TablaAccess.getModel();
            actualizarTabla(modelo);
            TablaAccess.setModel(modelo);
            
        } else {
            aterisco.setText("*");
            aterisco2.setText("*");
            LblNotificacion.setBackground(Color.red);
            LblNotificacion.setText("La contraseña no coincide");
            limpiarPass();
        }
    }
    private boolean nombreUsuarioExiste(String nombreUsuario) {
        try (Connection connection = c.getConexion()) {
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
            c.desconectar();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormAcceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    private boolean validarDuplicado(String id, String correo) {
        try(Connection connection = c.getConexion()) {
            
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
            
            c.desconectar();
            return true; // Ambos ID y correo son válidos y no existen en la base de datos
        } catch (SQLException e) {
            System.out.println(e);
            return false; // Hubo un error en la consulta, asumimos que no es válido
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormAcceso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
    }
    
    private void actualizarTabla(DefaultTableModel modelo) {
        modelo.setRowCount(0); 
        //Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try (Connection conectar = c.getConexion()){
            statement = conectar.createStatement();

            // Consulta para obtener todos los usuarios
            String query = "SELECT * FROM Usuario";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // Obtener datos de cada usuario y agregarlos al modelo
                String id = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");
                String nivelAcceso = resultSet.getString("nivelAcceso");                
                String correo = resultSet.getString("Correo");
                String password = resultSet.getString("Password");


                modelo.addRow(new Object[]{id, nombre, nivelAcceso, correo, password});
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                c.desconectar();
            } catch (SQLException e) {
                System.out.println(e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FormAcceso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void limpiarText() {
        txtIdUsuarioR.setText("");
        txtNombreR.setText("");
        txtCorreoR.setText("");
        txtPasswordR.setText("");
       // fechaChooser.setDate(null);
        txtConfirmarPassR.setText("");
    }
    private void limpiarPass() {
        txtPasswordR.setText("");
       // fechaChooser.setDate(null);
        txtConfirmarPassR.setText("");
    }  
    
    // Modificar
    @SuppressWarnings("UnusedAssignment")
    private void ModificarUsuario() {
        
    String IdUser = txtIDUsuarioE.getText();
    String nombre = txtNombreE.getText();
    String Correo = txtCorreoE.getText();
    String Password = txtPasswordE.getText();
    String confirmarpassword = txtConfirmarPassE.getText();
    String nivelAcceso = null;

    // Validar campos Empty
    if (IdUser.isEmpty() || nombre.isEmpty() || Correo.isEmpty() || Password.isEmpty() || confirmarpassword.isEmpty()) {
        LblNotificacionE.setForeground(Color.red);
        LblNotificacionE.setText("Debe completar todos los campos");
        return;
    }

    // Verificar Pass
    if (confirmarpassword.equals(Password)) {
        // Verificar si el nuevo correo ya existe
        if (!validarNuevoCorreo(IdUser, Correo)) {
            txtCorreoE.setText(""); // Limpiar el campo de correo
            LblNotificacionE.setForeground(Color.red);
            LblNotificacionE.setText("El correo ya está en uso. Pruebe otro");
            return;
        }

        LblNotificacionE.setForeground(Color.green);
        LblNotificacionE.setText("Cuenta Modificada Exitosamente");
        limpiarText2();
        
        /* Iniciar un temporizador para borrar el mensaje después de 2 segundos
        debe ponerse en milisegundo*/
        
        if (notificationTimer != null) {
            notificationTimer.stop();
        }
        notificationTimer = new Timer(2000, e -> {
            LblNotificacionE.setText(""); 
        });
        notificationTimer.setRepeats(false); 
        notificationTimer.start();

        // Verificar si es admin
        if (CheckAdminE.isSelected()) {
            nivelAcceso = "admin";
        } else {
            nivelAcceso = "user";
        }

        Usuario u = new Usuario(IdUser, nombre, Correo, Password, nivelAcceso);
        UsuarioDAOImpl d = new UsuarioDAOImpl();
        int result;
        try {
            result = d.update(u); // Actualizar
            System.out.print(result);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormAcceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            DefaultTableModel modelo = (DefaultTableModel) TablaAccess.getModel();
            actualizarTabla(modelo);
            TablaAccess.setModel(modelo);
        
    } else {
        ateriscoE1.setText("*");
        ateriscoE2.setText("*");
       // LblNotificacion2.setBackground(Color.red);
        LblNotificacionE.setForeground(Color.red); // Cambio de color
        LblNotificacionE.setText("Password No Coincide");
        limpiarPass2();
    }
}
        private boolean validarNuevoCorreo(String id, String nuevoCorreo) {
    try(Connection connection = c.getConexion()) {
        
        Statement statement = connection.createStatement();

        // Verificar si el correo ya existe para otros usuarios
        String query = "SELECT * FROM Usuario WHERE Correo = '" + nuevoCorreo + "' AND id <> '" + id + "'";
        ResultSet resultCorreo = statement.executeQuery(query);

        if (resultCorreo.next()) {
            LblNotificacionE.setForeground(Color.red);
            LblNotificacionE.setText("El correo electrónico ya está en uso. Pruebe otro.");
            return false;
        }
        c.desconectar();
        return true; // El nuevo correo es válido y no está en uso por otros usuarios
    } catch (SQLException e) {
        System.out.println(e);
        return false; // Hubo un error en la consulta, asumimos que no es válido
    }   catch (ClassNotFoundException ex) {
        Logger.getLogger(FormAcceso.class.getName()).log(Level.SEVERE, null, ex);
        return false;   
        }
        
}
    private void limpiarText2() {
        txtIDUsuarioE.setText("");
        txtNombreE.setText("");
        txtCorreoE.setText("");
        txtPasswordE.setText("");
       // fechaChooser.setDate(null);
        txtConfirmarPassE.setText("");
    }

    private void limpiarPass2() {
        txtPasswordE.setText("");
       // fechaChooser.setDate(null);
        txtConfirmarPassE.setText("");
    }
    
    
    // Eliminar usuario
    
    private void EliminarUsuario() {
        String IdUser = txtIDUsuarioE.getText();

        if (IdUser.isEmpty()) {
            LblNotificacionE.setForeground(Color.red);
            LblNotificacionE.setText("Debe ingresar un ID para eliminar.");
            return;
        }

        // Bloquear los campos durante 3 segundos
        txtNombreE.setEnabled(false);
        txtCorreoE.setEnabled(false);
        txtPasswordE.setEnabled(false);
        txtConfirmarPassE.setEnabled(false);
        CheckAdminE.setEnabled(false);

        LblNotificacionE.setForeground(Color.yellow);
        LblNotificacionE.setText("Usuario eliminado.");

        // Iniciar un temporizador para habilitar los campos después de 3 segundos
        if (notificationTimer != null) {
            notificationTimer.stop();
        }
        notificationTimer = new Timer(3000, e -> {
            txtNombreE.setEnabled(true);
            txtCorreoE.setEnabled(true);
            txtPasswordE.setEnabled(true);
            txtConfirmarPassE.setEnabled(true);
            CheckAdminE.setEnabled(true);
            limpiarText2();
            LblNotificacionE.setText(""); // Borrar el mensaje de confirmación
        });
        notificationTimer.setRepeats(false); // Ejecutar solo una vez
        notificationTimer.start();

        try {
            UsuarioDAOImpl d = new UsuarioDAOImpl();
            Usuario u = d.get(IdUser);

            if (u != null) {
                int result;
                try {
                    result = d.delete(u); // Eliminar
                    if (result > 0) {
                        LblNotificacionE.setForeground(Color.green);
                        LblNotificacionE.setText("Usuario eliminado.");

                        DefaultTableModel modelo = (DefaultTableModel) TablaAccess.getModel();
                        actualizarTabla(modelo);
                        TablaAccess.setModel(modelo);
                    } else {
                        LblNotificacionE.setForeground(Color.red);
                        LblNotificacionE.setText("Usuario no encontrado.");
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FormAcceso.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                LblNotificacionE.setForeground(Color.red);
                LblNotificacionE.setText("Usuario no encontrado.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Content = new javax.swing.JPanel();
        scrollAccess = new javax.swing.JScrollPane();
        TablaAccess = new javax.swing.JTable();
        TabAccess = new Utils.MaterialTabbed();
        PanelRegistrar = new javax.swing.JPanel();
        PanelDatosR = new javax.swing.JPanel();
        txtIdUsuarioR = new javax.swing.JTextField();
        LblNivelR = new javax.swing.JLabel();
        CheckAdminR = new javax.swing.JCheckBox();
        LblNombreR = new javax.swing.JLabel();
        LblCorreoR = new javax.swing.JLabel();
        txtConfirmarPassR = new javax.swing.JTextField();
        txtPasswordR = new javax.swing.JTextField();
        LblidR = new javax.swing.JLabel();
        txtCorreoR = new javax.swing.JTextField();
        txtNombreR = new javax.swing.JTextField();
        LblNotificacion = new javax.swing.JLabel();
        aterisco = new javax.swing.JLabel();
        aterisco2 = new javax.swing.JLabel();
        LblPasswordR = new javax.swing.JLabel();
        LblCPasswordR = new javax.swing.JLabel();
        btnRegistrar = new Utils.ButtonRounded();
        iconUser = new Utils.SVGImage2();
        PanelEditar = new javax.swing.JPanel();
        PanelDatosE = new javax.swing.JPanel();
        txtIDUsuarioE = new javax.swing.JTextField();
        LblNivelE = new javax.swing.JLabel();
        CheckAdminE = new javax.swing.JCheckBox();
        LblNombreE = new javax.swing.JLabel();
        LblCorreoE = new javax.swing.JLabel();
        txtConfirmarPassE = new javax.swing.JTextField();
        txtPasswordE = new javax.swing.JTextField();
        LblIDE = new javax.swing.JLabel();
        txtCorreoE = new javax.swing.JTextField();
        txtNombreE = new javax.swing.JTextField();
        LblNotificacionE = new javax.swing.JLabel();
        ateriscoE1 = new javax.swing.JLabel();
        ateriscoE2 = new javax.swing.JLabel();
        LblPasswordE = new javax.swing.JLabel();
        LblCPasswordE = new javax.swing.JLabel();
        btnModificar = new Utils.ButtonRounded();
        btnEliminar = new Utils.ButtonRounded();
        iconEdit = new Utils.SVGImage2();
        LblTitle = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1115, 760));

        Content.setBackground(new java.awt.Color(24, 39, 72));
        Content.setPreferredSize(new java.awt.Dimension(1103, 748));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaAccess.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre de Usuario", "Nivel de acceso", "Correo", "Contraseña"
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
        scrollAccess.setViewportView(TablaAccess);
        if (TablaAccess.getColumnModel().getColumnCount() > 0) {
            TablaAccess.getColumnModel().getColumn(0).setResizable(false);
            TablaAccess.getColumnModel().getColumn(0).setPreferredWidth(10);
            TablaAccess.getColumnModel().getColumn(1).setResizable(false);
            TablaAccess.getColumnModel().getColumn(2).setResizable(false);
            TablaAccess.getColumnModel().getColumn(2).setPreferredWidth(15);
            TablaAccess.getColumnModel().getColumn(3).setResizable(false);
            TablaAccess.getColumnModel().getColumn(4).setResizable(false);
        }

        Content.add(scrollAccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 720, 610));

        TabAccess.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N

        PanelRegistrar.setBackground(new java.awt.Color(24, 39, 72));
        PanelRegistrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelDatosR.setBackground(new java.awt.Color(255, 255, 255));

        txtIdUsuarioR.setBackground(new java.awt.Color(255, 255, 255));
        txtIdUsuarioR.setFont(new java.awt.Font("Montserrat Medium", 0, 15)); // NOI18N
        txtIdUsuarioR.setForeground(new java.awt.Color(0, 0, 0));
        txtIdUsuarioR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdUsuarioR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtIdUsuarioR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdUsuarioRActionPerformed(evt);
            }
        });

        LblNivelR.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblNivelR.setForeground(new java.awt.Color(0, 0, 0));
        LblNivelR.setText("Nivel de acceso:");

        CheckAdminR.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        CheckAdminR.setForeground(new java.awt.Color(0, 0, 0));
        CheckAdminR.setText("Admin");
        CheckAdminR.setToolTipText("Privilegios de administrador significa que podrá modificar y ver información sensible.");

        LblNombreR.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblNombreR.setForeground(new java.awt.Color(51, 51, 51));
        LblNombreR.setText("Nombre:");

        LblCorreoR.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblCorreoR.setForeground(new java.awt.Color(51, 51, 51));
        LblCorreoR.setText("Correo:");

        txtConfirmarPassR.setBackground(new java.awt.Color(255, 255, 255));
        txtConfirmarPassR.setFont(new java.awt.Font("Montserrat Medium", 0, 15)); // NOI18N
        txtConfirmarPassR.setForeground(new java.awt.Color(0, 0, 0));
        txtConfirmarPassR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtPasswordR.setBackground(new java.awt.Color(255, 255, 255));
        txtPasswordR.setFont(new java.awt.Font("Montserrat Medium", 0, 15)); // NOI18N
        txtPasswordR.setForeground(new java.awt.Color(0, 0, 0));
        txtPasswordR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        LblidR.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblidR.setForeground(new java.awt.Color(51, 51, 51));
        LblidR.setText("ID usuario:");

        txtCorreoR.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreoR.setFont(new java.awt.Font("Montserrat Medium", 0, 15)); // NOI18N
        txtCorreoR.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreoR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtNombreR.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreR.setFont(new java.awt.Font("Montserrat Medium", 0, 15)); // NOI18N
        txtNombreR.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        LblNotificacion.setForeground(new java.awt.Color(255, 0, 51));
        LblNotificacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        aterisco.setForeground(new java.awt.Color(255, 0, 51));

        aterisco2.setForeground(new java.awt.Color(255, 0, 51));

        LblPasswordR.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblPasswordR.setForeground(new java.awt.Color(51, 51, 51));
        LblPasswordR.setText("Contraseña:");

        LblCPasswordR.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblCPasswordR.setForeground(new java.awt.Color(51, 51, 51));
        LblCPasswordR.setText("Confirmar:");

        btnRegistrar.setBackground(new java.awt.Color(51, 204, 0));
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDatosRLayout = new javax.swing.GroupLayout(PanelDatosR);
        PanelDatosR.setLayout(PanelDatosRLayout);
        PanelDatosRLayout.setHorizontalGroup(
            PanelDatosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosRLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(LblidR, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtIdUsuarioR, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelDatosRLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(PanelDatosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblNombreR, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelDatosRLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(LblCorreoR)))
                .addGroup(PanelDatosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCorreoR, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreR, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelDatosRLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(PanelDatosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aterisco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aterisco2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(PanelDatosRLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(LblPasswordR, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(txtPasswordR, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelDatosRLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(LblCPasswordR, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtConfirmarPassR, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelDatosRLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(LblNotificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelDatosRLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(LblNivelR))
            .addGroup(PanelDatosRLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(CheckAdminR))
            .addGroup(PanelDatosRLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelDatosRLayout.setVerticalGroup(
            PanelDatosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosRLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(PanelDatosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblidR)
                    .addGroup(PanelDatosRLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtIdUsuarioR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(PanelDatosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosRLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(LblNombreR)
                        .addGap(1, 1, 1)
                        .addComponent(LblCorreoR))
                    .addGroup(PanelDatosRLayout.createSequentialGroup()
                        .addComponent(aterisco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelDatosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aterisco2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelDatosRLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtCorreoR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PanelDatosRLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtNombreR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(PanelDatosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblPasswordR)
                    .addGroup(PanelDatosRLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtPasswordR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(PanelDatosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblCPasswordR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelDatosRLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtConfirmarPassR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(LblNotificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(LblNivelR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(CheckAdminR)
                .addGap(28, 28, 28)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PanelRegistrar.add(PanelDatosR, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 206, 335, 360));

        iconUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanelRegistrar.add(iconUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 220, 190));

        TabAccess.addTab("             REGISTRAR          ", PanelRegistrar);

        PanelEditar.setBackground(new java.awt.Color(24, 39, 72));
        PanelEditar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelDatosE.setBackground(new java.awt.Color(255, 255, 255));
        PanelDatosE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIDUsuarioE.setBackground(new java.awt.Color(255, 255, 255));
        txtIDUsuarioE.setFont(new java.awt.Font("Montserrat Medium", 0, 15)); // NOI18N
        txtIDUsuarioE.setForeground(new java.awt.Color(0, 0, 0));
        txtIDUsuarioE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDUsuarioE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtIDUsuarioE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDUsuarioEActionPerformed(evt);
            }
        });
        PanelDatosE.add(txtIDUsuarioE, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 38, 115, 20));

        LblNivelE.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblNivelE.setForeground(new java.awt.Color(0, 0, 0));
        LblNivelE.setText("Nivel de acceso:");
        PanelDatosE.add(LblNivelE, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 218, -1, 20));

        CheckAdminE.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        CheckAdminE.setForeground(new java.awt.Color(0, 0, 0));
        CheckAdminE.setText("Admin");
        CheckAdminE.setToolTipText("Privilegios de administrador significa que podrá modificar y ver información sensible.");
        PanelDatosE.add(CheckAdminE, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 238, -1, -1));

        LblNombreE.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblNombreE.setForeground(new java.awt.Color(51, 51, 51));
        LblNombreE.setText("Nombre:");
        PanelDatosE.add(LblNombreE, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 61, 90, -1));

        LblCorreoE.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblCorreoE.setForeground(new java.awt.Color(51, 51, 51));
        LblCorreoE.setText("Correo:");
        PanelDatosE.add(LblCorreoE, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 85, -1, -1));

        txtConfirmarPassE.setBackground(new java.awt.Color(255, 255, 255));
        txtConfirmarPassE.setFont(new java.awt.Font("Montserrat Medium", 0, 15)); // NOI18N
        txtConfirmarPassE.setForeground(new java.awt.Color(0, 0, 0));
        txtConfirmarPassE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        PanelDatosE.add(txtConfirmarPassE, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 115, 20));

        txtPasswordE.setBackground(new java.awt.Color(255, 255, 255));
        txtPasswordE.setFont(new java.awt.Font("Montserrat Medium", 0, 15)); // NOI18N
        txtPasswordE.setForeground(new java.awt.Color(0, 0, 0));
        txtPasswordE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        PanelDatosE.add(txtPasswordE, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 131, 115, 20));

        LblIDE.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblIDE.setForeground(new java.awt.Color(51, 51, 51));
        LblIDE.setText("ID usuario:");
        PanelDatosE.add(LblIDE, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 35, 110, -1));

        txtCorreoE.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreoE.setFont(new java.awt.Font("Montserrat Medium", 0, 15)); // NOI18N
        txtCorreoE.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreoE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        PanelDatosE.add(txtCorreoE, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 88, 115, 20));

        txtNombreE.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreE.setFont(new java.awt.Font("Montserrat Medium", 0, 15)); // NOI18N
        txtNombreE.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        PanelDatosE.add(txtNombreE, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 63, 115, 20));

        LblNotificacionE.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        LblNotificacionE.setForeground(new java.awt.Color(255, 0, 51));
        LblNotificacionE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanelDatosE.add(LblNotificacionE, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 195, 240, 20));

        ateriscoE1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ateriscoE1.setForeground(new java.awt.Color(255, 0, 51));
        ateriscoE1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanelDatosE.add(ateriscoE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 39, 20, 20));

        ateriscoE2.setForeground(new java.awt.Color(255, 0, 51));
        PanelDatosE.add(ateriscoE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 78, 20, 20));

        LblPasswordE.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblPasswordE.setForeground(new java.awt.Color(51, 51, 51));
        LblPasswordE.setText("Contraseña:");
        PanelDatosE.add(LblPasswordE, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 128, 120, -1));

        LblCPasswordE.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblCPasswordE.setForeground(new java.awt.Color(51, 51, 51));
        LblCPasswordE.setText("Confirmar:");
        PanelDatosE.add(LblCPasswordE, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 158, 110, 20));

        btnModificar.setBackground(new java.awt.Color(51, 204, 0));
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("MODIFICAR");
        btnModificar.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        PanelDatosE.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 288, 130, 30));

        btnEliminar.setBackground(new java.awt.Color(255, 0, 0));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        PanelDatosE.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 288, 130, 30));

        PanelEditar.add(PanelDatosE, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 206, 335, 360));

        iconEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanelEditar.add(iconEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 220, 190));

        TabAccess.addTab("             EDITAR            ", PanelEditar);

        Content.add(TabAccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 340, 620));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Acceso");
        Content.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1120, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Content, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Content, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdUsuarioRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdUsuarioRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdUsuarioRActionPerformed

    private void txtIDUsuarioEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDUsuarioEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDUsuarioEActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        RegistrarUsuario();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ModificarUsuario();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarUsuario();
    }//GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckAdminE;
    private javax.swing.JCheckBox CheckAdminR;
    private javax.swing.JPanel Content;
    private javax.swing.JLabel LblCPasswordE;
    private javax.swing.JLabel LblCPasswordR;
    private javax.swing.JLabel LblCorreoE;
    private javax.swing.JLabel LblCorreoR;
    private javax.swing.JLabel LblIDE;
    private javax.swing.JLabel LblNivelE;
    private javax.swing.JLabel LblNivelR;
    private javax.swing.JLabel LblNombreE;
    private javax.swing.JLabel LblNombreR;
    private javax.swing.JLabel LblNotificacion;
    private javax.swing.JLabel LblNotificacionE;
    private javax.swing.JLabel LblPasswordE;
    private javax.swing.JLabel LblPasswordR;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JLabel LblidR;
    private javax.swing.JPanel PanelDatosE;
    private javax.swing.JPanel PanelDatosR;
    private javax.swing.JPanel PanelEditar;
    private javax.swing.JPanel PanelRegistrar;
    private Utils.MaterialTabbed TabAccess;
    private javax.swing.JTable TablaAccess;
    private javax.swing.JLabel aterisco;
    private javax.swing.JLabel aterisco2;
    private javax.swing.JLabel ateriscoE1;
    private javax.swing.JLabel ateriscoE2;
    private Utils.ButtonRounded btnEliminar;
    private Utils.ButtonRounded btnModificar;
    private Utils.ButtonRounded btnRegistrar;
    private Utils.SVGImage2 iconEdit;
    private Utils.SVGImage2 iconUser;
    private javax.swing.JScrollPane scrollAccess;
    private javax.swing.JTextField txtConfirmarPassE;
    private javax.swing.JTextField txtConfirmarPassR;
    private javax.swing.JTextField txtCorreoE;
    private javax.swing.JTextField txtCorreoR;
    private javax.swing.JTextField txtIDUsuarioE;
    private javax.swing.JTextField txtIdUsuarioR;
    private javax.swing.JTextField txtNombreE;
    private javax.swing.JTextField txtNombreR;
    private javax.swing.JTextField txtPasswordE;
    private javax.swing.JTextField txtPasswordR;
    // End of variables declaration//GEN-END:variables
}
