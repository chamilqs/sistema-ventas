package appStructure.application.form.other.Ventas;

import Database.Connect.Conexion;
import Database.Connect.ConexionSingleton;
import Database.DTO.Usuario;
import Utils.Tools;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import appStructure.application.Application;

/**
 *
 * @author Samil
 */
public class FormCaja extends javax.swing.JPanel {

    private static FormCaja instance = null;
    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime fechaHoraActual = LocalDateTime.now();
    String date = fechaHoraActual.format(formato);
    Usuario u = null;
    
    public FormCaja() {
        initComponents();
        inicializar();

    }

    public FormCaja(Usuario u) {
        initComponents();
        inicializar();
        this.u = u;
        
    }
    
    // Instancia de la clase 
     public static FormCaja getInstance() {
        if (instance == null) {
            instance = new FormCaja();
        }
        return instance;
    }
     
    private void inicializar(){
        
        iconCaja.setSvgImage("appStructure/icon/svg/cashRegister.svg", 260, 260);
                               
        try(Connection conectar = c.getConexion()) {
                            
                String montoInicial = "SELECT monto_Actual FROM caja";

                PreparedStatement pstMontoInicial = conectar.prepareStatement(montoInicial);
                ResultSet rsMontoInicial = pstMontoInicial.executeQuery();

                if (rsMontoInicial.next()) {
                double monto = rsMontoInicial.getDouble("monto_Actual");

                DineroMontoInicial.setText(String.format("%.2f", monto));
                DineroMontoActual.setText(String.format("%.2f", monto));
                }

                String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre IS NULL";
                PreparedStatement pstCheck = conectar.prepareStatement(checkNullDate);
                ResultSet rsCheck = pstCheck.executeQuery();

                if (rsCheck.next()) {
                    
                btnAbrirCaja.setVisible(false);
                LblEstadoCaja.setForeground(new Color(51,255,51));
                LblEstadoCaja.setText("Abierta");
                btnCerrarCaja.setVisible(true);
                btnSetMontoInicial.setVisible(false);
                
                } else if (!rsCheck.next()){

                    btnCerrarCaja.setVisible(false);
                    LblEstadoCaja.setForeground(Color.red);
                    LblEstadoCaja.setText("Cerrada");
                    btnAbrirCaja.setVisible(true);
                    btnSetMontoInicial.setVisible(true);                    
                    
                }   

            } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
            }
        
        recargarTabla();
        recargarTablaSalida();    
    
    }  
    public void forLogout() {
        String newMontoInicial = DineroMontoInicial.getText();

        double montoActual = Double.parseDouble(DineroMontoActual.getText());
        String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre is NULL";
        String updateNullDate = "UPDATE historialCaja SET monto_Cierre = ?, fecha_Cierre = ? WHERE idCaja = 1 AND fecha_Cierre IS NULL";
        String updateCaja = "UPDATE caja SET monto_Actual =" + newMontoInicial;
        LocalDateTime fechaHora = LocalDateTime.now();
        try(Connection conectar = c.getConexion()) {
            
            PreparedStatement pst = conectar.prepareStatement(checkNullDate);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {               
                
                PreparedStatement pst2 = conectar.prepareStatement(updateNullDate);  
                pst2.setDouble(1, montoActual);
                pst2.setTimestamp(2, Timestamp.valueOf(fechaHora));
                System.out.print(fechaHora);
                pst2.executeUpdate();
                
                // Volver a setear la caja al monto inicial pesos
                PreparedStatement pst3 = conectar.prepareStatement(updateCaja); 
                pst3.executeUpdate();
                
                DefaultTableModel model = (DefaultTableModel) TablaCaja.getModel();
                model.setRowCount(0); 
                
                DefaultTableModel modelSalida = (DefaultTableModel) TablaSalida.getModel();
                modelSalida.setRowCount(0); 
                               
                DineroMontoActual.setText(newMontoInicial);
                DineroMontoInicial.setText(newMontoInicial);
                ResumenTotalVendido.setText("0.0");
                LblMontoCierreCaja.setText(newMontoInicial);
                MontoGastoCajaChica.setText("0.0");
                LblMontoCobrar.setText("0.0");
                
                btnCerrarCaja.setVisible(false);
                LblEstadoCaja.setForeground(Color.red);
                LblEstadoCaja.setText("Cerrada");
                btnAbrirCaja.setVisible(true);
                btnSetMontoInicial.setVisible(true);
                
                c.desconectar();
  
            } else{
                System.out.println("No hay sesion de caja abierta");
                c.desconectar();
            }            
            
        } catch (SQLException ex) {
            System.out.print(ex);
             Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }     
    private void abrirCaja() {
    
    String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre IS NULL";
    String montoInicial = "SELECT monto_Actual FROM caja";
    
    try(Connection conectar = c.getConexion()) {
        
        PreparedStatement pstCheck = conectar.prepareStatement(checkNullDate);
        ResultSet rsCheck = pstCheck.executeQuery();

            if (rsCheck.next()) {
                // Hay un fecha_Cierre nulo, no se puede abrir la caja nuevamente
                JOptionPane.showMessageDialog(this, "La caja sigue abierta. No se puede abrir nuevamente.", "Error en apertura.", JOptionPane.ERROR_MESSAGE);
                c.desconectar();

            } else if( Float.parseFloat(DineroMontoInicial.getText()) == 0){
            
                setMontoInicial();            
            
            } else {
                
                // No hay un fecha_Cierre nulo, se puede abrir la caja
                int confirmarApertura = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres abrir la caja?", "Abrir la caja.", JOptionPane.YES_NO_OPTION);
                if (confirmarApertura == JOptionPane.YES_OPTION) {                    
                                
                PreparedStatement pstMontoInicial = conectar.prepareStatement(montoInicial);
                ResultSet rsMontoInicial = pstMontoInicial.executeQuery();
                    
                if (rsMontoInicial.next()) {
                    double monto = rsMontoInicial.getDouble("monto_Actual");

                    DineroMontoInicial.setText(String.format("%.2f", monto));
                    DineroMontoActual.setText(String.format("%.2f", monto));
                }
                    
                btnAbrirCaja.setVisible(false);
                LblEstadoCaja.setForeground(new Color(51,255,51));
                LblEstadoCaja.setText("Abierta");
                btnCerrarCaja.setVisible(true);
                btnSetMontoInicial.setVisible(false);
                    
                // Enviar datos a la BD Caja e HistorialCaja
                float montoApertura = Float.parseFloat(DineroMontoInicial.getText());
                            
                String queryUser = "SELECT idUsuario FROM historial WHERE logout IS NULL";
                PreparedStatement pstU = conectar.prepareStatement(queryUser);
                ResultSet rsU = pstU.executeQuery();

                // Mover el cursor al primer resultado (si existe)
                if (rsU.next()) {
                    // Obtener el valor de la columna 'idUsuario' en el primer resultado
                    String userId = rsU.getString("idUsuario");                      

                    // Obtener el nombre de ese usuario
                    String queryUserName = "SELECT nombre FROM usuario WHERE id = ?";            
                    PreparedStatement pstUN = conectar.prepareStatement(queryUserName);
                    pstUN.setString(1, userId);
                    ResultSet rsUN = pstUN.executeQuery();

                    if (rsUN.next()) {

                       String nombreUsuario = rsUN.getString("nombre");
                       LblUserLogged.setText(nombreUsuario);

                    } else{

                        JOptionPane.showMessageDialog(this, "No pudimos cargar el usuario actual, lo sentimos.", 					"Error de carga.", JOptionPane.ERROR_MESSAGE);
                        LblUserLogged.setText("undefined");

                    }   

                } else {
                    System.out.println("Error obteniendo al usuario activo.");
                }

                //String userId = u.getId();
                String userId = rsU.getString("idUsuario");
                                        
                double valorInicial = Double.parseDouble(DineroMontoInicial.getText());
                String consulta = "SELECT id FROM caja WHERE id = 1";

                try {
                        PreparedStatement pst = conectar.prepareStatement(consulta);
                        ResultSet rs = pst.executeQuery();

                        if (rs.next()) {

                            String updateHistorial = "INSERT INTO historialcaja(idCaja, usuarioId, fecha_Apertura, monto_Apertura) VALUES(1,?,?,?)";
                            PreparedStatement pst4 = conectar.prepareStatement(updateHistorial);
                            pst4.setString(1, userId);
                            pst4.setTimestamp(2, Timestamp.valueOf(fechaHoraActual));
                            pst4.setDouble(3, montoApertura);
                            pst4.executeUpdate();
                            
                            recargarTabla();
                            recargarTablaSalida();

                        } else {

                            int confirm = JOptionPane.showConfirmDialog(this, "La caja no existe, ¿desea crearla?", "Confirmación.", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {                    

                                // añadir el usuario a la base de datos si ese id no existe
                                String crearCaja = "INSERT INTO caja(monto_Actual) VALUES(?)";
                                PreparedStatement pst3 = conectar.prepareStatement(crearCaja);
                                pst3.setDouble(1, valorInicial);
                                pst3.executeUpdate();

                                String updateHistorial = "INSERT INTO historialcaja(idCaja, usuarioId, fecha_Apertura, monto_Apertura) VALUES(1,?,?,?)";
                                PreparedStatement pst4 = conectar.prepareStatement(updateHistorial);
                                pst4.setString(1, userId);
                                pst4.setTimestamp(2, Timestamp.valueOf(fechaHoraActual));
                                pst4.setDouble(3, montoApertura);
                                pst4.executeUpdate();
                                
                                recargarTabla();
                                recargarTablaSalida();
                                
                            } 

                        }

                        c.desconectar();
                    } catch (SQLException ex) {
                    System.out.print(ex);
                     Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }   
                }

                c.desconectar();
            } catch (SQLException ex) {
                System.out.print(ex);
                Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    private void cerrarCaja(){
        
        String newMontoInicial = DineroMontoInicial.getText();
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres cerrar la caja?", "Cerrar la caja.", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
        double montoActual = Double.parseDouble(DineroMontoActual.getText());
        String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre is NULL";
        String updateNullDate = "UPDATE historialCaja SET monto_Cierre = ?, fecha_Cierre = ? WHERE idCaja = 1 AND fecha_Cierre IS NULL";
        String updateCaja = "UPDATE caja SET monto_Actual =" + newMontoInicial;
        LocalDateTime fechaHora = LocalDateTime.now();
        try(Connection conectar = c.getConexion()) {
            
            PreparedStatement pst = conectar.prepareStatement(checkNullDate);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {               
                
                PreparedStatement pst2 = conectar.prepareStatement(updateNullDate);  
                pst2.setDouble(1, montoActual);
                pst2.setTimestamp(2, Timestamp.valueOf(fechaHora));                
                pst2.executeUpdate();
                
                // Volver a setear la caja al monto inicial pesos
                PreparedStatement pst3 = conectar.prepareStatement(updateCaja); 
                pst3.executeUpdate();
                
                DefaultTableModel model = (DefaultTableModel) TablaCaja.getModel();
                model.setRowCount(0); 
                
                DefaultTableModel modelSalida = (DefaultTableModel) TablaSalida.getModel();
                modelSalida.setRowCount(0); 
                               
                DineroMontoActual.setText(newMontoInicial);
                DineroMontoInicial.setText(newMontoInicial);
                ResumenTotalVendido.setText("0.0");
                LblMontoCierreCaja.setText(newMontoInicial);
                MontoGastoCajaChica.setText("0");
                LblMontoCobrar.setText("0");
                
                btnCerrarCaja.setVisible(false);
                LblEstadoCaja.setForeground(Color.red);
                LblEstadoCaja.setText("Cerrada");
                btnAbrirCaja.setVisible(true);
                btnSetMontoInicial.setVisible(true);
                
                c.desconectar();
                JOptionPane.showMessageDialog(this, "Se cerró la sesión de caja correctamente.", "Cierre de Caja",JOptionPane.INFORMATION_MESSAGE);
                
            } else if(!rs.next()){
            
                JOptionPane.showMessageDialog(this, "No existe ninguna sesión de caja abierta.","Error en cierre.",JOptionPane.ERROR_MESSAGE);
                c.desconectar();
            }            
            
        } catch (SQLException ex) {
            System.out.print(ex);
             Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (ClassNotFoundException ex) {   
                Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
            }   

        }
    }  
    private void setMontoInicial() {

        JButton btnSalida = new Utils.ButtonRounded();
        btnSalida.setBounds(1, 1, 25, 25);
        btnSalida.setText("x");

        // Configuración de la fuente y estilo para la "X"
        Font font = new Font("Monserrat", Font.BOLD, 12);
        btnSalida.setFont(font);
        btnSalida.setForeground(Color.white);
        btnSalida.setBackground(Color.red);

        JDialog montoInicial = new JDialog();
        montoInicial.setTitle("Monto Inicial");
        montoInicial.setBackground(new Color(24, 39, 72));
        montoInicial.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        montoInicial.setModal(true);
        montoInicial.setUndecorated(true);

        btnSalida.addActionListener(e -> {
            montoInicial.dispose();
        });

        montoInicial.setSize(300, 150);
        montoInicial.setLayout(null);
        montoInicial.setLocationRelativeTo(null);

        // Agregar el label "MONTO INICIAL"
        JLabel labelMontoInicial = new JLabel("MONTO INICIAL");
        labelMontoInicial.setBounds(0, 20, montoInicial.getWidth(), 20);
        labelMontoInicial.setHorizontalAlignment(JLabel.CENTER);
        labelMontoInicial.setFont(new Font("Arial", Font.BOLD, 16));
        labelMontoInicial.setForeground(Color.BLACK);
        montoInicial.add(labelMontoInicial);

        JTextField txtMontoInicial = new JTextField();
        txtMontoInicial.setBounds(50, 50, 200, 30);
        montoInicial.add(txtMontoInicial);

        JButton btnConfirmar = new JButton("CONFIRMAR");
        btnConfirmar.setBounds(100, 95, 100, 30);
        btnConfirmar.addActionListener(e -> {
        String input = txtMontoInicial.getText();

            try {
                double monto = Double.parseDouble(input);
                if (monto < 0) {
                    JOptionPane.showMessageDialog(montoInicial, "Ingresa un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    
                    DineroMontoActual.setText(String.format("%.2f", monto).replace(",", "."));
                    DineroMontoInicial.setText(String.format("%.2f", monto).replace(",", "."));
                    LblMontoCierreCaja.setText(String.format("%.2f", monto).replace(",", "."));

                    try(Connection conectar = c.getConexion()) {
                                
                        String updateCaja = "UPDATE caja\n SET monto_Actual =" + monto + "\nWHERE id = 1";
                        PreparedStatement pst = conectar.prepareStatement(updateCaja);
                        pst.executeUpdate();

                        c.desconectar();
                    } catch (SQLException ex) {
                        System.out.print(ex);
                        Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    montoInicial.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(montoInicial, "Ingresa un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnConfirmar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        montoInicial.add(btnConfirmar);
        montoInicial.add(btnSalida);
        montoInicial.setVisible(true);
    }
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
      
    // Salidas
    private void addSalida() {
            String closed = LblEstadoCaja.getText();

            float montoInicial = Float.parseFloat(DineroMontoInicial.getText());
            float montoActual = Float.parseFloat(DineroMontoActual.getText());
            float limiteCaja = montoActual - montoInicial;

            String idSalida = Tools.UniqueIDGenerator.generateUniqueID();
            String monto = txtMontoSalida.getText();
            String concepto = txtConceptoSalida.getText();
            float montoSalida;

            try {
                montoSalida = Float.parseFloat(monto);
            } catch (NumberFormatException ex) {
                showMessage("Ingrese un monto válido.");
                return; 
            }

            float montoActualizado = montoActual - montoSalida;
            String justificacion = txtConceptoSalida.getText();

            if (closed.equals("Cerrada")) {
                showMessage("La caja está cerrada. No puedes registrar una salida.");
            } else if (montoSalida > limiteCaja) {
                showMessage("No puedes sacar más dinero de la caja.\nLa caja no puede quedar con menos de RD$" + montoInicial + " pesos.");
            } else if (monto.isEmpty() || concepto.isEmpty()) {
                showMessage("Rellene todos los campos.");
            } else if (montoSalida <= 0 || montoActual < montoSalida) {
                showMessage("Ingrese un monto válido.");
            } else {
                int option = JOptionPane.showConfirmDialog(this, "¿Estás seguro de registrar una salida de Caja?", "Registrar salida.", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) TablaSalida.getModel();
                    Object[] fila = { idSalida, montoSalida, concepto, date };
                    model.addRow(fila);

                    txtMontoSalida.setText("");
                    txtConceptoSalida.setText("");

                    float montoTotal = sumarMontosTablaSalida(model);
                    MontoGastoCajaChica.setText(String.format("%.2f", montoTotal).replace(",", "."));

                    try(Connection conectar = c.getConexion()) {
                        
                        String query = "INSERT INTO salidacaja (idCaja, justificacion, monto, fecha) VALUES (1, ?, ?, ?)";
                        PreparedStatement pst = conectar.prepareStatement(query);
                        pst.setString(1, justificacion);
                        pst.setFloat(2, montoSalida);
                        pst.setTimestamp(3, Timestamp.valueOf(fechaHoraActual));
                        pst.executeUpdate();

                        DineroMontoActual.setText(String.valueOf(montoActualizado));

                        // Actualizar el monto actual que es el monto actual (en el programa) - monto de salida
                        String query2 = "UPDATE caja SET monto_Actual =" + montoActualizado + "WHERE id = 1";
                        PreparedStatement pst2 = conectar.prepareStatement(query2);
                        pst2.executeUpdate();
                                                
                        recargarTablaSalida();

                        c.desconectar();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    private float sumarMontosTablaSalida(DefaultTableModel model) {
    float total = 0.0f;
    for (int row = 0; row < model.getRowCount(); row++) {
        total += (float) model.getValueAt(row, 1);
    }
    return total;
    }
    private void recargarTablaSalida() {   
        
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String todayDate = fechaHoraActual.format(formatter);

            String selectQuery = "SELECT id, justificacion, monto, fecha FROM salidacaja WHERE DATE(fecha) = ?";
            String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre is NULL";

            try(Connection conectar = Conexion.conectar()) {        
                
                PreparedStatement pst = conectar.prepareStatement(selectQuery);
                pst.setString(1, todayDate);
                ResultSet rs = pst.executeQuery();

                DefaultTableModel model = (DefaultTableModel) TablaSalida.getModel();
                model.setRowCount(0); // Limpiar los datos actuales en la tabla
                
                PreparedStatement pst2 = conectar.prepareStatement(checkNullDate);
                ResultSet rsCheck = pst2.executeQuery();                
                
            if(!rsCheck.next()){
                // c.desconectar();                
            } else{

                while (rs.next()) {
                    String id = rs.getString("id");
                    float monto = rs.getFloat("monto");
                    String justificacion = rs.getString("justificacion");
                    String fecha = rs.getString("fecha");

                    // Agregar la fila a la tabla
                    model.addRow(new Object[]{id, monto, justificacion, fecha});
                }
                    
                float montoTotal = sumarMontosTablaSalida(model);
                MontoGastoCajaChica.setText(String.format("%.2f", montoTotal).replace(",", "."));
                
                // c.desconectar();
            }
             // c.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
                
            } 
        }         
    private void recargarTabla() {
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = now.format(formatter); 

        // Consulta para verificar si hay una sesión de caja abierta
        String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre is NULL";

        // Consulta para obtener ventas y deudas para el día actual
        String selectQuery = "SELECT v.id, v.total, c.deuda FROM venta v LEFT JOIN cuentaporcobrar c ON v.id = c.idVenta WHERE DATE(v.fecha) = ? ORDER BY v.fecha ASC";

        // Consulta para obtener la suma de montos de la tabla "salidacaja" para el día actual
        String sumSalidaQuery = "SELECT SUM(monto) AS suma_montos FROM salidacaja WHERE DATE(fecha) = ?";

        try (Connection conectar = Conexion.conectar()) {
            
            PreparedStatement pstCheck = conectar.prepareStatement(checkNullDate);
            ResultSet rsCheck = pstCheck.executeQuery();

            if (!rsCheck.next()) {
                // No hay sesión de caja abierta, no hacer nada.
                // c.desconectar();
            } else {
                // Hay una sesión de caja abierta

                PreparedStatement pstVentas = conectar.prepareStatement(selectQuery);
                pstVentas.setString(1, todayDate);
                ResultSet rsVentas = pstVentas.executeQuery();

                DefaultTableModel model = (DefaultTableModel) TablaCaja.getModel();
                model.setRowCount(0);

                // Obtener el monto inicial de la caja
                float montoInicial = Float.parseFloat(DineroMontoInicial.getText());

                while (rsVentas.next()) {
                    String id = rsVentas.getString("id");
                    float total = rsVentas.getFloat("total");
                    float adeudado = rsVentas.getFloat("deuda");
                    model.addRow(new Object[]{id, total, adeudado});
                }

                try {

                    PreparedStatement pstSumSalida = conectar.prepareStatement(sumSalidaQuery);
                    pstSumSalida.setString(1, todayDate);
                    ResultSet rsSumSalida = pstSumSalida.executeQuery();
                    float sumaMontosSalida = 0.0f;

                    if (rsSumSalida.next()) {
                        sumaMontosSalida = rsSumSalida.getFloat("suma_montos");
                    }

                    // Calcular el monto Actual
                    float montoTotal = sumarMontosTablaCaja(model);
                    float montoActual = (montoTotal + montoInicial) - sumaMontosSalida;

                    // Actualizar las etiquetas en la interfaz de usuario
                    DineroMontoActual.setText(String.format("%.2f", montoActual).replace(",", "."));
                    ResumenTotalVendido.setText(String.format("%.2f", montoTotal).replace(",", "."));

                    // Sumar columna adeudado
                    float montoAdeudado = sumarAdeudadoCaja(model);
                    LblMontoCobrar.setText(String.format("%.2f", montoAdeudado).replace(",", "."));
                    LblMontoCierreCaja.setText(String.format("%.2f", montoActual).replace(",", "."));

                } catch (SQLException e) {
                    System.out.println(e);
                    // c.desconectar();
                }
                // c.desconectar();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private float sumarAdeudadoCaja(DefaultTableModel model) {
        float total = 0.0f;
        for (int row = 0; row < model.getRowCount(); row++) {
            total += (float) model.getValueAt(row, 2);
        }
        return total;
    }    
    private float sumarMontosTablaCaja(DefaultTableModel model) {
        float total = 0.0f;
        for (int row = 0; row < model.getRowCount(); row++) {
            total += (float) model.getValueAt(row, 1);
        }
        return total;
    }   
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContentAll = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        TablaCaja = new javax.swing.JTable();
        TabResumenSalida = new Utils.MaterialTabbed();
        ContentSalidaDinero = new javax.swing.JPanel();
        btnSetMontoInicial = new Utils.ButtonRounded();
        btnAbrirCaja = new Utils.ButtonRounded();
        btnCerrarCaja = new Utils.ButtonRounded();
        btnGoFacturacion = new Utils.ButtonRounded();
        panelRedondeado1 = new Utils.PanelRedondeado();
        LblEstadoCaj4 = new javax.swing.JLabel();
        LblMontoInici4l = new javax.swing.JLabel();
        LblMontoActual = new javax.swing.JLabel();
        DineroMontoActual = new javax.swing.JLabel();
        DineroMontoInicial = new javax.swing.JLabel();
        LblEstadoCaja = new javax.swing.JLabel();
        LblUserLogged = new javax.swing.JLabel();
        panelRedondeado2 = new Utils.PanelRedondeado();
        LblUser = new javax.swing.JLabel();
        LblUser1 = new javax.swing.JLabel();
        iconCaja = new Utils.SVGImage2();
        ContentSalidas = new javax.swing.JPanel();
        ScrollSalida = new javax.swing.JScrollPane();
        TablaSalida = new javax.swing.JTable();
        PanelDatosSalida1 = new javax.swing.JPanel();
        LblConcepto = new javax.swing.JLabel();
        LblMontoSalida = new javax.swing.JLabel();
        txtMontoSalida = new javax.swing.JTextField();
        scrollConceptoSalida1 = new javax.swing.JScrollPane();
        txtConceptoSalida = new javax.swing.JTextArea();
        btnAgregarSalida = new Utils.ButtonRounded();
        ContentResumenDia = new javax.swing.JPanel();
        PanelDatosResumenDia = new javax.swing.JPanel();
        LblTotalVendido = new javax.swing.JLabel();
        LblGastoCajaChica = new javax.swing.JLabel();
        LblCierreCaja = new javax.swing.JLabel();
        LblPendienteCobrar = new javax.swing.JLabel();
        ResumenTotalVendido = new javax.swing.JLabel();
        LblMontoCobrar = new javax.swing.JLabel();
        LblMontoCierreCaja = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        MontoGastoCajaChica = new javax.swing.JLabel();
        LblResumen = new javax.swing.JLabel();
        LblTitle = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1115, 760));
        setPreferredSize(new java.awt.Dimension(1115, 760));

        ContentAll.setBackground(new java.awt.Color(24, 39, 72));
        ContentAll.setMinimumSize(new java.awt.Dimension(1103, 748));
        ContentAll.setPreferredSize(new java.awt.Dimension(1103, 748));
        ContentAll.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaCaja.setBackground(new java.awt.Color(51, 51, 51));
        TablaCaja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Venta", "Monto", "Adeudado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaCaja.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(TablaCaja);

        ContentAll.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 620, 620));

        TabResumenSalida.setBackground(new java.awt.Color(69, 98, 126));
        TabResumenSalida.setForeground(new java.awt.Color(255, 255, 255));
        TabResumenSalida.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        TabResumenSalida.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N

        ContentSalidaDinero.setBackground(new java.awt.Color(24, 39, 72));
        ContentSalidaDinero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSetMontoInicial.setBackground(new java.awt.Color(153, 153, 153));
        btnSetMontoInicial.setForeground(new java.awt.Color(255, 255, 255));
        btnSetMontoInicial.setText("SETEAR CAJA");
        btnSetMontoInicial.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnSetMontoInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetMontoInicialActionPerformed(evt);
            }
        });
        ContentSalidaDinero.add(btnSetMontoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 300, 130, 40));

        btnAbrirCaja.setBackground(new java.awt.Color(51, 204, 0));
        btnAbrirCaja.setForeground(new java.awt.Color(255, 255, 255));
        btnAbrirCaja.setText("ABRIR CAJA");
        btnAbrirCaja.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnAbrirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCajaActionPerformed(evt);
            }
        });
        ContentSalidaDinero.add(btnAbrirCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 300, 130, 40));

        btnCerrarCaja.setBackground(new java.awt.Color(255, 51, 51));
        btnCerrarCaja.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarCaja.setText("CERRAR CAJA");
        btnCerrarCaja.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnCerrarCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarCajaActionPerformed(evt);
            }
        });
        ContentSalidaDinero.add(btnCerrarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 300, 130, 40));

        btnGoFacturacion.setBackground(new java.awt.Color(153, 153, 153));
        btnGoFacturacion.setForeground(new java.awt.Color(255, 255, 255));
        btnGoFacturacion.setText("IR A FACTURAR");
        btnGoFacturacion.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnGoFacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoFacturacionActionPerformed(evt);
            }
        });
        ContentSalidaDinero.add(btnGoFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 130, 40));

        panelRedondeado1.setBackground(new java.awt.Color(22, 56, 158));
        panelRedondeado1.setRoundBottomLeft(50);
        panelRedondeado1.setRoundBottomRight(50);
        panelRedondeado1.setRoundTopLeft(50);
        panelRedondeado1.setRoundTopRight(50);
        panelRedondeado1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblEstadoCaj4.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblEstadoCaj4.setForeground(new java.awt.Color(255, 255, 255));
        LblEstadoCaj4.setText("Estado Caja:");
        panelRedondeado1.add(LblEstadoCaj4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        LblMontoInici4l.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblMontoInici4l.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoInici4l.setText("Monto Inicial: RD$");
        panelRedondeado1.add(LblMontoInici4l, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        LblMontoActual.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblMontoActual.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoActual.setText("Monto Actual: RD$");
        panelRedondeado1.add(LblMontoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 20));

        DineroMontoActual.setBackground(new java.awt.Color(51, 51, 51));
        DineroMontoActual.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        DineroMontoActual.setForeground(new java.awt.Color(255, 255, 255));
        DineroMontoActual.setText("0.0");
        DineroMontoActual.setToolTipText("");
        panelRedondeado1.add(DineroMontoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 140, 20));

        DineroMontoInicial.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        DineroMontoInicial.setForeground(new java.awt.Color(255, 255, 255));
        DineroMontoInicial.setText("0.0");
        panelRedondeado1.add(DineroMontoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 140, 150, 20));

        LblEstadoCaja.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblEstadoCaja.setForeground(new java.awt.Color(255, 51, 51));
        LblEstadoCaja.setText("Cerrada");
        panelRedondeado1.add(LblEstadoCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        LblUserLogged.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblUserLogged.setForeground(new java.awt.Color(255, 255, 255));
        panelRedondeado1.add(LblUserLogged, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 80, 140, 20));

        panelRedondeado2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelRedondeado2.setRoundTopLeft(50);
        panelRedondeado2.setRoundTopRight(50);
        panelRedondeado2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblUser.setFont(new java.awt.Font("Montserrat Black", 1, 18)); // NOI18N
        LblUser.setForeground(new java.awt.Color(255, 255, 255));
        LblUser.setText("DATOS IMPORTANTES");
        panelRedondeado2.add(LblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 13, -1, -1));

        panelRedondeado1.add(panelRedondeado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 50));

        LblUser1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblUser1.setForeground(new java.awt.Color(255, 255, 255));
        LblUser1.setText("Usuario:");
        panelRedondeado1.add(LblUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        ContentSalidaDinero.add(panelRedondeado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 400, 220));

        iconCaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ContentSalidaDinero.add(iconCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 260, 250));

        TabResumenSalida.addTab("CONTROL DE CAJA", ContentSalidaDinero);

        ContentSalidas.setBackground(new java.awt.Color(24, 39, 72));
        ContentSalidas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaSalida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Salida", "Monto", "Concepto", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaSalida.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TablaSalida.getTableHeader().setReorderingAllowed(false);
        ScrollSalida.setViewportView(TablaSalida);
        if (TablaSalida.getColumnModel().getColumnCount() > 0) {
            TablaSalida.getColumnModel().getColumn(0).setResizable(false);
            TablaSalida.getColumnModel().getColumn(1).setResizable(false);
            TablaSalida.getColumnModel().getColumn(2).setResizable(false);
            TablaSalida.getColumnModel().getColumn(3).setResizable(false);
        }

        ContentSalidas.add(ScrollSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 308, 440, 380));

        PanelDatosSalida1.setBackground(new java.awt.Color(24, 39, 72));
        PanelDatosSalida1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblConcepto.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblConcepto.setForeground(java.awt.Color.white);
        LblConcepto.setText("CONCEPTO DEL PAGO");
        PanelDatosSalida1.add(LblConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        LblMontoSalida.setBackground(new java.awt.Color(255, 255, 255));
        LblMontoSalida.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblMontoSalida.setForeground(java.awt.Color.white);
        LblMontoSalida.setText("MONTO DE PAGO");
        PanelDatosSalida1.add(LblMontoSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, 20));

        txtMontoSalida.setBackground(new java.awt.Color(255, 255, 255));
        txtMontoSalida.setForeground(new java.awt.Color(0, 0, 0));
        txtMontoSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMontoSalida.setToolTipText("Monto de la salida de dinero de Caja.");
        PanelDatosSalida1.add(txtMontoSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 220, -1));

        txtConceptoSalida.setBackground(new java.awt.Color(255, 255, 255));
        txtConceptoSalida.setColumns(18);
        txtConceptoSalida.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtConceptoSalida.setForeground(new java.awt.Color(0, 0, 0));
        txtConceptoSalida.setLineWrap(true);
        txtConceptoSalida.setRows(2);
        txtConceptoSalida.setTabSize(4);
        txtConceptoSalida.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtConceptoSalida.setMaximumSize(new java.awt.Dimension(20, 20));
        scrollConceptoSalida1.setViewportView(txtConceptoSalida);

        PanelDatosSalida1.add(scrollConceptoSalida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 280, 120));

        btnAgregarSalida.setBackground(new java.awt.Color(51, 204, 0));
        btnAgregarSalida.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarSalida.setText("AGREGAR SALIDA");
        btnAgregarSalida.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnAgregarSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSalidaActionPerformed(evt);
            }
        });
        PanelDatosSalida1.add(btnAgregarSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 230, 150, 30));

        ContentSalidas.add(PanelDatosSalida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 320, 270));

        TabResumenSalida.addTab("REGISTRAR SALIDAS", ContentSalidas);

        ContentResumenDia.setBackground(new java.awt.Color(24, 39, 72));
        ContentResumenDia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelDatosResumenDia.setBackground(new java.awt.Color(69, 98, 126));
        PanelDatosResumenDia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblTotalVendido.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblTotalVendido.setForeground(new java.awt.Color(255, 255, 255));
        LblTotalVendido.setText("Total vendido");
        PanelDatosResumenDia.add(LblTotalVendido, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        LblGastoCajaChica.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblGastoCajaChica.setForeground(new java.awt.Color(255, 255, 255));
        LblGastoCajaChica.setText("Salidas de Caja");
        PanelDatosResumenDia.add(LblGastoCajaChica, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        LblCierreCaja.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblCierreCaja.setForeground(new java.awt.Color(255, 255, 255));
        LblCierreCaja.setText("Cierre de caja");
        PanelDatosResumenDia.add(LblCierreCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, -1));

        LblPendienteCobrar.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblPendienteCobrar.setForeground(new java.awt.Color(255, 255, 255));
        LblPendienteCobrar.setText("Pendiente a cobrar");
        PanelDatosResumenDia.add(LblPendienteCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        ResumenTotalVendido.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        ResumenTotalVendido.setForeground(new java.awt.Color(0, 0, 0));
        ResumenTotalVendido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResumenTotalVendido.setText("0.0");
        PanelDatosResumenDia.add(ResumenTotalVendido, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 210, 22));

        LblMontoCobrar.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblMontoCobrar.setForeground(new java.awt.Color(0, 0, 0));
        LblMontoCobrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblMontoCobrar.setText("0.0");
        PanelDatosResumenDia.add(LblMontoCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 170, 22));

        LblMontoCierreCaja.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblMontoCierreCaja.setForeground(new java.awt.Color(0, 0, 0));
        LblMontoCierreCaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblMontoCierreCaja.setText("0.0");
        PanelDatosResumenDia.add(LblMontoCierreCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 170, 22));
        PanelDatosResumenDia.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 178, 88, 22));

        MontoGastoCajaChica.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        MontoGastoCajaChica.setForeground(new java.awt.Color(0, 0, 0));
        MontoGastoCajaChica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MontoGastoCajaChica.setText("0.0");
        PanelDatosResumenDia.add(MontoGastoCajaChica, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 170, 22));

        ContentResumenDia.add(PanelDatosResumenDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 340, 340));

        LblResumen.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        LblResumen.setForeground(new java.awt.Color(255, 255, 255));
        LblResumen.setText("RESUMEN DEL DÍA");
        ContentResumenDia.add(LblResumen, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 250, 30));

        TabResumenSalida.addTab("RESUMEN DIARIO", ContentResumenDia);

        ContentAll.add(TabResumenSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 0, 447, 730));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 45)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Caja chica");
        ContentAll.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContentAll, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContentAll, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCajaActionPerformed
        abrirCaja();        
    }//GEN-LAST:event_btnAbrirCajaActionPerformed

    private void btnCerrarCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCajaActionPerformed
        cerrarCaja();
    }//GEN-LAST:event_btnCerrarCajaActionPerformed

    private void btnSetMontoInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetMontoInicialActionPerformed
        setMontoInicial();
    }//GEN-LAST:event_btnSetMontoInicialActionPerformed

    private void btnGoFacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoFacturacionActionPerformed
        FormFacturacion formFacturacion = FormFacturacion.getInstance();
        Application.showForm(formFacturacion);
    }//GEN-LAST:event_btnGoFacturacionActionPerformed

    private void btnAgregarSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSalidaActionPerformed
        addSalida();
    }//GEN-LAST:event_btnAgregarSalidaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContentAll;
    private javax.swing.JPanel ContentResumenDia;
    private javax.swing.JPanel ContentSalidaDinero;
    private javax.swing.JPanel ContentSalidas;
    private javax.swing.JLabel DineroMontoActual;
    private javax.swing.JLabel DineroMontoInicial;
    private javax.swing.JLabel LblCierreCaja;
    private javax.swing.JLabel LblConcepto;
    private javax.swing.JLabel LblEstadoCaj4;
    private javax.swing.JLabel LblEstadoCaja;
    private javax.swing.JLabel LblGastoCajaChica;
    private javax.swing.JLabel LblMontoActual;
    private javax.swing.JLabel LblMontoCierreCaja;
    private javax.swing.JLabel LblMontoCobrar;
    private javax.swing.JLabel LblMontoInici4l;
    private javax.swing.JLabel LblMontoSalida;
    private javax.swing.JLabel LblPendienteCobrar;
    private javax.swing.JLabel LblResumen;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JLabel LblTotalVendido;
    private javax.swing.JLabel LblUser;
    private javax.swing.JLabel LblUser1;
    private javax.swing.JLabel LblUserLogged;
    private javax.swing.JLabel MontoGastoCajaChica;
    private javax.swing.JPanel PanelDatosResumenDia;
    private javax.swing.JPanel PanelDatosSalida1;
    private javax.swing.JLabel ResumenTotalVendido;
    private javax.swing.JScrollPane ScrollSalida;
    private Utils.MaterialTabbed TabResumenSalida;
    private javax.swing.JTable TablaCaja;
    private javax.swing.JTable TablaSalida;
    private Utils.ButtonRounded btnAbrirCaja;
    private Utils.ButtonRounded btnAgregarSalida;
    private Utils.ButtonRounded btnCerrarCaja;
    private Utils.ButtonRounded btnGoFacturacion;
    private Utils.ButtonRounded btnSetMontoInicial;
    private Utils.SVGImage2 iconCaja;
    private javax.swing.JLabel jLabel8;
    private Utils.PanelRedondeado panelRedondeado1;
    private Utils.PanelRedondeado panelRedondeado2;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scrollConceptoSalida1;
    private javax.swing.JTextArea txtConceptoSalida;
    private javax.swing.JTextField txtMontoSalida;
    // End of variables declaration//GEN-END:variables
}
