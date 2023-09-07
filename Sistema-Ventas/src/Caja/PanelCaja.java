
package Caja;

//import Utils.Utils;

import Connect.Conexion;
import DTO.Usuario;
import Utils.MaterialTabbed;
import Utils.Utils.UniqueIDGenerator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Samil
 */
public class PanelCaja extends javax.swing.JPanel {

    private static final String rutaBase = "src\\imgnew\\";
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime fechaHoraActual = LocalDateTime.now();
    String date = fechaHoraActual.format(formato);
    Usuario u = null;  
    
    public PanelCaja() {
        initComponents();
        inicializar();
        LblUserLogged.setText("");
                
        if(!"admin".equals(u.getNivelAcceso())){
            btnSetMontoInicial.setVisible(false);
        }
    }
    
    public PanelCaja(Usuario u) {
        initComponents();
        inicializar();
        this.u = u;
        LblUserLogged.setText(u.getNombre());
        
        if(!"admin".equals(u.getNivelAcceso())){
            btnSetMontoInicial.setVisible(false);
        }
    }    
    
    public static void setScaledImage(JLabel lblImage, String imageName, int width, int height) {
        ImageIcon img = new ImageIcon(PanelCaja.class.getResource("/imgnew/" + imageName));
        Image scaledImage = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(scaledImage);
        lblImage.setIcon(icon);
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void inicializar(){
        setScaledImage(CajaR, "CajaAzul.png", 100, 100);
        String fecha = formato.format(fechaHoraActual);
               
        try {
                Connection conectar = Conexion.conectar();

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

                Conexion.desconectar(conectar);
            } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
            }
        
        recargarTabla();
        recargarTablaSalida();    
    
    }
    private void addToTable() {
            String closed = LblEstadoCaja.getText();

            float montoInicial = Float.parseFloat(DineroMontoInicial.getText());
            float montoActual = Float.parseFloat(DineroMontoActual.getText());
            float limiteCaja = montoActual - montoInicial;

            String idSalida = UniqueIDGenerator.generateUniqueID();
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

                    try {
                        Connection conectar = Conexion.conectar();

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

                        Conexion.desconectar(conectar);
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }   
    private void abrirCaja() {
    
    // Verificar si hay un fecha_Cierre nulo en la base de datos
    String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre IS NULL";
    String montoInicial = "SELECT monto_Actual FROM caja";
    
    try {
        Connection conectar = Conexion.conectar();
        PreparedStatement pstCheck = conectar.prepareStatement(checkNullDate);
        ResultSet rsCheck = pstCheck.executeQuery();

            if (rsCheck.next()) {
                // Hay un fecha_Cierre nulo, no se puede abrir la caja nuevamente
                JOptionPane.showMessageDialog(this, "La caja sigue abierta. No se puede abrir nuevamente.", "Error en apertura.", JOptionPane.ERROR_MESSAGE);
                Conexion.desconectar(conectar);

            } else if(Float.parseFloat(DineroMontoInicial.getText()) == 0){
            
                    setMontoInicial();            
            
            }else {
                
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
                    
                // Enviar datos a la tabla Caja e HistorialCaja
                float montoApertura = Float.parseFloat(DineroMontoInicial.getText());
                String userId = u.getId();

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
                            } 

                        }

                        Conexion.desconectar(conectar);
                    } catch (SQLException ex) {
                    System.out.print(ex);
                     Logger.getLogger(PanelCaja.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }   
                }

                Conexion.desconectar(conectar);
            } catch (SQLException ex) {
                System.out.print(ex);
                Logger.getLogger(PanelCaja.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            Connection conectar = Conexion.conectar();
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
                MontoGastoCajaChica.setText("0");
                LblMontoCobrar.setText("0");
                
                btnCerrarCaja.setVisible(false);
                LblEstadoCaja.setForeground(Color.red);
                LblEstadoCaja.setText("Cerrada");
                btnAbrirCaja.setVisible(true);
                btnSetMontoInicial.setVisible(true);
                
                JOptionPane.showMessageDialog(this, "Se cerró la sesión de caja correctamente.", "Cierre de Caja",JOptionPane.INFORMATION_MESSAGE);
                
            } else if(!rs.next()){
            
                JOptionPane.showMessageDialog(this, "No existe ninguna sesión de caja abierta.","Error en cierre.",JOptionPane.ERROR_MESSAGE);
            
            }
            
            Conexion.desconectar(conectar);
        } catch (SQLException ex) {
            System.out.print(ex);
             Logger.getLogger(PanelCaja.class.getName()).log(Level.SEVERE, null, ex);
        }   

        }
    
    }
    private void recargarTabla() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String todayDate = now.format(formatter); 
    
    String selectQuery = "SELECT v.id, v.total, c.deuda FROM venta v LEFT JOIN cuentaporcobrar c ON v.id = c.idVenta WHERE DATE(v.fecha) = ? ORDER BY v.fecha ASC";
    String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre is NULL";
    String sumSalidaQuery = "SELECT SUM(monto) AS suma_montos FROM salidacaja WHERE DATE(fecha) = ?";
    
    try {
        Connection conectar = Conexion.conectar();            
        PreparedStatement pstCheck = conectar.prepareStatement(checkNullDate);
        ResultSet rsCheck = pstCheck.executeQuery();
        
        if (!rsCheck.next()) {
            // No hay sesión de caja abierta, no hacer nada.
        } else {
            // Hay una sesión de caja abierta
            PreparedStatement pstVentas = conectar.prepareStatement(selectQuery);
            pstVentas.setString(1, todayDate);
            ResultSet rsVentas = pstVentas.executeQuery();

            DefaultTableModel model = (DefaultTableModel) TablaCaja.getModel();
            model.setRowCount(0); // Limpiar los datos actuales en la tabla
            float montoInicial = Float.parseFloat(DineroMontoInicial.getText());

                while (rsVentas.next()) {
                    String id = rsVentas.getString("id");
                    float total = rsVentas.getFloat("total");
                    float adeudado = rsVentas.getFloat("deuda");

                    // Agregar la fila a la tabla
                    model.addRow(new Object[]{id, total, adeudado});
                }

            // Obtener la suma de montos de la tabla "salidacaja"
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

            DineroMontoActual.setText(String.format("%.2f", montoActual).replace(",", "."));
            ResumenTotalVendido.setText(String.format("%.2f", montoTotal).replace(",", "."));

            // Sumar columna adeudado
            float montoAdeudado = sumarAdeudadoCaja(model);
            LblMontoCobrar.setText(String.format("%.2f", montoAdeudado).replace(",", "."));
            LblMontoCierreCaja.setText(String.format("%.2f", montoActual).replace(",", "."));

            Conexion.desconectar(conectar);
        }
    } catch (SQLException ex) {
        Logger.getLogger(PanelCaja.class.getName()).log(Level.SEVERE, null, ex);
    }
}  
    private void recargarTablaSalida() {   
        
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String todayDate = now.format(formatter);

            String selectQuery = "SELECT id, justificacion, monto, fecha FROM salidacaja WHERE DATE(fecha) = ?";
            String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre is NULL";

            try {        
                Connection conectar = Conexion.conectar();
                PreparedStatement pst = conectar.prepareStatement(selectQuery);
                pst.setString(1, todayDate);
                ResultSet rs = pst.executeQuery();

                DefaultTableModel model = (DefaultTableModel) TablaSalida.getModel();
                model.setRowCount(0); // Limpiar los datos actuales en la tabla
                
                PreparedStatement pst2 = conectar.prepareStatement(checkNullDate);
                ResultSet rsCheck = pst2.executeQuery();
            
            if(!rsCheck.next()){
                
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
                Conexion.desconectar(conectar);
            }
            } catch (SQLException ex) {
                Logger.getLogger(PanelCaja.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }       
    private void setMontoInicial() {

        JButton btnSalida = new rojerusan.RSMaterialButtonRectangle();
        btnSalida.setBounds(1, 1, 30, 33);
        btnSalida.setText("x");

        // Configuración de la fuente y estilo para la "X"
        Font font = new Font("Arial", Font.BOLD, 14);
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

        JTextField textField = new JTextField();
        textField.setBounds(50, 50, 200, 30);
        montoInicial.add(textField);

        JButton confirmar = new JButton("Confirmar");
        confirmar.setBounds(100, 95, 100, 30);
        confirmar.addActionListener(e -> {
            String input = textField.getText();

            try {
                double monto = Double.parseDouble(input);
                if (monto < 0) {
                    JOptionPane.showMessageDialog(montoInicial, "Ingresa un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    
                    DineroMontoActual.setText(String.format("%.2f", monto).replace(",", "."));
                    DineroMontoInicial.setText(String.format("%.2f", monto).replace(",", "."));
                    LblMontoCierreCaja.setText(String.format("%.2f", monto).replace(",", "."));

                    try {
                        Connection conectar = Conexion.conectar();            
                        String updateCaja = "UPDATE caja\n SET monto_Actual =" + monto + "\nWHERE id = 1";
                        PreparedStatement pst = conectar.prepareStatement(updateCaja);
                        pst.executeUpdate();

                        Conexion.desconectar(conectar);
                    } catch (SQLException ex) {
                        System.out.print(ex);
                        Logger.getLogger(PanelCaja.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    montoInicial.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(montoInicial, "Ingresa un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        confirmar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        montoInicial.add(confirmar);
        montoInicial.add(btnSalida);
        montoInicial.setVisible(true);
    }

    public void cerrarCajaAutomatically() {
        String newMontoInicial = DineroMontoInicial.getText();

        double montoActual = Double.parseDouble(DineroMontoActual.getText());
        String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre is NULL";
        String updateNullDate = "UPDATE historialCaja SET monto_Cierre = ?, fecha_Cierre = ? WHERE idCaja = 1 AND fecha_Cierre IS NULL";
        String updateCaja = "UPDATE caja SET monto_Actual =" + newMontoInicial;
        LocalDateTime fechaHora = LocalDateTime.now();
        try {
            Connection conectar = Conexion.conectar();
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
                MontoGastoCajaChica.setText("0");
                LblMontoCobrar.setText("0");
                
                btnCerrarCaja.setVisible(false);
                LblEstadoCaja.setForeground(Color.red);
                LblEstadoCaja.setText("Cerrada");
                btnAbrirCaja.setVisible(true);
                btnSetMontoInicial.setVisible(true);
  
            }             
            Conexion.desconectar(conectar);
        } catch (SQLException ex) {
            System.out.print(ex);
             Logger.getLogger(PanelCaja.class.getName()).log(Level.SEVERE, null, ex);
        }   

        
    
    }     
    
    private float sumarMontosTablaCaja(DefaultTableModel model) {
    float total = 0.0f;
    for (int row = 0; row < model.getRowCount(); row++) {
        total += (float) model.getValueAt(row, 1);
    }
    return total;
    }
    private float sumarMontosTablaSalida(DefaultTableModel model) {
    float total = 0.0f;
    for (int row = 0; row < model.getRowCount(); row++) {
        total += (float) model.getValueAt(row, 1);
    }
    return total;
    }
    private float sumarAdeudadoCaja(DefaultTableModel model) {
    float total = 0.0f;
    for (int row = 0; row < model.getRowCount(); row++) {
        total += (float) model.getValueAt(row, 2);
    }
    return total;
    }    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContentAll = new javax.swing.JPanel();
        scrollTablaCaja = new javax.swing.JScrollPane();
        TablaCaja = new javax.swing.JTable();
        TabResumenSalida = new Utils.MaterialTabbed();
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
        ContentSalidaDinero = new javax.swing.JPanel();
        PanelDatosSalida = new javax.swing.JPanel();
        LblConcepto = new javax.swing.JLabel();
        LblMontoSalida = new javax.swing.JLabel();
        txtMontoSalida = new javax.swing.JTextField();
        scrollConceptoSalida = new javax.swing.JScrollPane();
        txtConceptoSalida = new javax.swing.JTextArea();
        BtnAgregar = new javax.swing.JButton();
        LblSalidaCaja = new javax.swing.JLabel();
        ContentSalidas = new javax.swing.JPanel();
        ScrollSalida = new javax.swing.JScrollPane();
        TablaSalida = new javax.swing.JTable();
        LblEstadoCaj4 = new javax.swing.JLabel();
        LblEstadoCaja = new javax.swing.JLabel();
        LblMontoInici4l = new javax.swing.JLabel();
        DineroMontoInicial = new javax.swing.JLabel();
        LblUser = new javax.swing.JLabel();
        LblUserLogged = new javax.swing.JLabel();
        LblMontoActual = new javax.swing.JLabel();
        DineroMontoActual = new javax.swing.JLabel();
        PanelDivisor2 = new javax.swing.JPanel();
        btnAbrirCaja = new rojerusan.RSMaterialButtonRectangle();
        btnCerrarCaja = new rojerusan.RSMaterialButtonRectangle();
        LblCajaCobro = new javax.swing.JLabel();
        CajaR = new javax.swing.JLabel();
        PanelDivisor1 = new javax.swing.JPanel();
        PanelDivisor4 = new javax.swing.JPanel();
        PanelDivisor3 = new javax.swing.JPanel();
        btnSetMontoInicial = new rojerusan.RSMaterialButtonRectangle();
        PanelDivisor5 = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(1005, 660));
        setPreferredSize(new java.awt.Dimension(1005, 660));

        ContentAll.setBackground(new java.awt.Color(24, 39, 72));
        ContentAll.setMinimumSize(new java.awt.Dimension(1005, 660));
        ContentAll.setPreferredSize(new java.awt.Dimension(1005, 660));
        ContentAll.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        scrollTablaCaja.setViewportView(TablaCaja);
        if (TablaCaja.getColumnModel().getColumnCount() > 0) {
            TablaCaja.getColumnModel().getColumn(0).setResizable(false);
            TablaCaja.getColumnModel().getColumn(1).setResizable(false);
            TablaCaja.getColumnModel().getColumn(2).setResizable(false);
        }

        ContentAll.add(scrollTablaCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 580, 480));

        TabResumenSalida.setBackground(new java.awt.Color(69, 98, 126));
        TabResumenSalida.setForeground(new java.awt.Color(255, 255, 255));
        TabResumenSalida.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        TabResumenSalida.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N

        ContentResumenDia.setBackground(new java.awt.Color(24, 39, 72));
        ContentResumenDia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelDatosResumenDia.setBackground(new java.awt.Color(69, 98, 126));
        PanelDatosResumenDia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblTotalVendido.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        LblTotalVendido.setForeground(new java.awt.Color(255, 255, 255));
        LblTotalVendido.setText("Total vendido");
        PanelDatosResumenDia.add(LblTotalVendido, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        LblGastoCajaChica.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        LblGastoCajaChica.setForeground(new java.awt.Color(255, 255, 255));
        LblGastoCajaChica.setText("Salidas de Caja");
        PanelDatosResumenDia.add(LblGastoCajaChica, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 90, -1, -1));

        LblCierreCaja.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        LblCierreCaja.setForeground(new java.awt.Color(255, 255, 255));
        LblCierreCaja.setText("Cierre de caja");
        PanelDatosResumenDia.add(LblCierreCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 210, -1, -1));

        LblPendienteCobrar.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        LblPendienteCobrar.setForeground(new java.awt.Color(255, 255, 255));
        LblPendienteCobrar.setText("Pendiente a cobrar");
        PanelDatosResumenDia.add(LblPendienteCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        ResumenTotalVendido.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        ResumenTotalVendido.setForeground(new java.awt.Color(0, 0, 0));
        ResumenTotalVendido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResumenTotalVendido.setText("0.0");
        PanelDatosResumenDia.add(ResumenTotalVendido, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 60, 88, 22));

        LblMontoCobrar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoCobrar.setForeground(new java.awt.Color(0, 0, 0));
        LblMontoCobrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblMontoCobrar.setText("0.0");
        PanelDatosResumenDia.add(LblMontoCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 170, 124, 22));

        LblMontoCierreCaja.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoCierreCaja.setForeground(new java.awt.Color(0, 0, 0));
        LblMontoCierreCaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblMontoCierreCaja.setText("0.0");
        PanelDatosResumenDia.add(LblMontoCierreCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 92, 22));
        PanelDatosResumenDia.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 178, 88, 22));

        MontoGastoCajaChica.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        MontoGastoCajaChica.setForeground(new java.awt.Color(0, 0, 0));
        MontoGastoCajaChica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MontoGastoCajaChica.setText("0.0");
        PanelDatosResumenDia.add(MontoGastoCajaChica, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 110, 105, 22));

        ContentResumenDia.add(PanelDatosResumenDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 60, 330, 290));

        LblResumen.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        LblResumen.setForeground(new java.awt.Color(255, 255, 255));
        LblResumen.setText("RESUMEN DEL DÍA");
        ContentResumenDia.add(LblResumen, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 170, 20));

        TabResumenSalida.addTab("                               Resumen Diario                             ", ContentResumenDia);

        ContentSalidaDinero.setBackground(new java.awt.Color(24, 39, 72));
        ContentSalidaDinero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelDatosSalida.setBackground(new java.awt.Color(69, 98, 126));
        PanelDatosSalida.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblConcepto.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        LblConcepto.setText("CONCEPTO DEL PAGO");
        PanelDatosSalida.add(LblConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        LblMontoSalida.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        LblMontoSalida.setText("MONTO DE PAGO");
        PanelDatosSalida.add(LblMontoSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, 20));

        txtMontoSalida.setBackground(new java.awt.Color(255, 255, 255));
        txtMontoSalida.setToolTipText("Monto de la salida de dinero de Caja.");
        PanelDatosSalida.add(txtMontoSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 220, -1));

        txtConceptoSalida.setBackground(new java.awt.Color(255, 255, 255));
        txtConceptoSalida.setColumns(18);
        txtConceptoSalida.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtConceptoSalida.setLineWrap(true);
        txtConceptoSalida.setRows(2);
        txtConceptoSalida.setTabSize(4);
        txtConceptoSalida.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtConceptoSalida.setMaximumSize(new java.awt.Dimension(20, 20));
        scrollConceptoSalida.setViewportView(txtConceptoSalida);

        PanelDatosSalida.add(scrollConceptoSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 270, 120));

        BtnAgregar.setBackground(new java.awt.Color(132, 178, 80));
        BtnAgregar.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        BtnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAgregar.setText("AGREGAR");
        BtnAgregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });
        PanelDatosSalida.add(BtnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, -1, 30));

        ContentSalidaDinero.add(PanelDatosSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 320, 330));

        LblSalidaCaja.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        LblSalidaCaja.setForeground(new java.awt.Color(255, 255, 255));
        LblSalidaCaja.setText("SALIDA DE CAJA");
        ContentSalidaDinero.add(LblSalidaCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 30, 155, 20));

        TabResumenSalida.addTab("                            Registrar Salida                         ", ContentSalidaDinero);

        ContentSalidas.setBackground(new java.awt.Color(24, 39, 72));

        TablaSalida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idSalida", "Monto", "Concepto", "Fecha"
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
        TablaSalida.getTableHeader().setReorderingAllowed(false);
        ScrollSalida.setViewportView(TablaSalida);

        javax.swing.GroupLayout ContentSalidasLayout = new javax.swing.GroupLayout(ContentSalidas);
        ContentSalidas.setLayout(ContentSalidasLayout);
        ContentSalidasLayout.setHorizontalGroup(
            ContentSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentSalidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addContainerGap())
        );
        ContentSalidasLayout.setVerticalGroup(
            ContentSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentSalidasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ScrollSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        TabResumenSalida.addTab("                                  Salidas Registradas                                                            ", ContentSalidas);

        ContentAll.add(TabResumenSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 370, 650));

        LblEstadoCaj4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        LblEstadoCaj4.setForeground(new java.awt.Color(255, 255, 255));
        LblEstadoCaj4.setText("Estado Caja:");
        ContentAll.add(LblEstadoCaj4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, -1));

        LblEstadoCaja.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        LblEstadoCaja.setForeground(new java.awt.Color(255, 51, 51));
        LblEstadoCaja.setText("Cerrada");
        ContentAll.add(LblEstadoCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(936, 20, -1, -1));

        LblMontoInici4l.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        LblMontoInici4l.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoInici4l.setText("Monto inicial:");
        ContentAll.add(LblMontoInici4l, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, -1, -1));

        DineroMontoInicial.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        DineroMontoInicial.setForeground(new java.awt.Color(51, 255, 51));
        DineroMontoInicial.setText("0.0");
        ContentAll.add(DineroMontoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 40, 50, -1));

        LblUser.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        LblUser.setForeground(new java.awt.Color(255, 255, 255));
        LblUser.setText("Usuario:");
        ContentAll.add(LblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, -1, -1));

        LblUserLogged.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        LblUserLogged.setForeground(new java.awt.Color(51, 255, 51));
        LblUserLogged.setText("Samil");
        ContentAll.add(LblUserLogged, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 60, -1, -1));

        LblMontoActual.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoActual.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoActual.setText("Monto Actual: RD$");
        ContentAll.add(LblMontoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, -1, 40));

        DineroMontoActual.setBackground(new java.awt.Color(51, 51, 51));
        DineroMontoActual.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        DineroMontoActual.setForeground(new java.awt.Color(255, 255, 255));
        DineroMontoActual.setText("0.0");
        DineroMontoActual.setToolTipText("");
        ContentAll.add(DineroMontoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 90, 40));

        PanelDivisor2.setBackground(new java.awt.Color(132, 178, 80));

        javax.swing.GroupLayout PanelDivisor2Layout = new javax.swing.GroupLayout(PanelDivisor2);
        PanelDivisor2.setLayout(PanelDivisor2Layout);
        PanelDivisor2Layout.setHorizontalGroup(
            PanelDivisor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelDivisor2Layout.setVerticalGroup(
            PanelDivisor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        ContentAll.add(PanelDivisor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(995, 0, 10, 660));

        btnAbrirCaja.setBackground(new java.awt.Color(69, 98, 126));
        btnAbrirCaja.setText("Abrir");
        btnAbrirCaja.setFont(new java.awt.Font("Roboto Black", 0, 10)); // NOI18N
        btnAbrirCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAbrirCajaMouseClicked(evt);
            }
        });
        btnAbrirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCajaActionPerformed(evt);
            }
        });
        ContentAll.add(btnAbrirCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, 120, 40));

        btnCerrarCaja.setBackground(new java.awt.Color(69, 98, 126));
        btnCerrarCaja.setText("Cerrar");
        btnCerrarCaja.setFont(new java.awt.Font("Roboto Black", 0, 10)); // NOI18N
        btnCerrarCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarCajaMouseClicked(evt);
            }
        });
        btnCerrarCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarCajaActionPerformed(evt);
            }
        });
        ContentAll.add(btnCerrarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, 120, 40));

        LblCajaCobro.setBackground(new java.awt.Color(255, 255, 255));
        LblCajaCobro.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblCajaCobro.setForeground(new java.awt.Color(0, 112, 192));
        LblCajaCobro.setText("Caja de cobro");
        ContentAll.add(LblCajaCobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));
        ContentAll.add(CajaR, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 140, 130));

        PanelDivisor1.setBackground(new java.awt.Color(132, 178, 80));
        PanelDivisor1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        ContentAll.add(PanelDivisor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 660));

        PanelDivisor4.setBackground(new java.awt.Color(132, 178, 80));
        PanelDivisor4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        ContentAll.add(PanelDivisor4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 1030, 10));

        PanelDivisor3.setBackground(new java.awt.Color(132, 178, 80));

        javax.swing.GroupLayout PanelDivisor3Layout = new javax.swing.GroupLayout(PanelDivisor3);
        PanelDivisor3.setLayout(PanelDivisor3Layout);
        PanelDivisor3Layout.setHorizontalGroup(
            PanelDivisor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelDivisor3Layout.setVerticalGroup(
            PanelDivisor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        ContentAll.add(PanelDivisor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(996, 0, 10, -1));

        btnSetMontoInicial.setBackground(new java.awt.Color(69, 98, 126));
        btnSetMontoInicial.setText("SET");
        btnSetMontoInicial.setFont(new java.awt.Font("Roboto Black", 0, 10)); // NOI18N
        btnSetMontoInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSetMontoInicialMouseClicked(evt);
            }
        });
        btnSetMontoInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetMontoInicialActionPerformed(evt);
            }
        });
        ContentAll.add(btnSetMontoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 60, 40));

        PanelDivisor5.setBackground(new java.awt.Color(132, 178, 80));

        javax.swing.GroupLayout PanelDivisor5Layout = new javax.swing.GroupLayout(PanelDivisor5);
        PanelDivisor5.setLayout(PanelDivisor5Layout);
        PanelDivisor5Layout.setHorizontalGroup(
            PanelDivisor5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelDivisor5Layout.setVerticalGroup(
            PanelDivisor5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        ContentAll.add(PanelDivisor5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 10, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContentAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ContentAll, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        recargarTablaSalida();
        addToTable();
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void btnCerrarCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarCajaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarCajaMouseClicked

    private void btnCerrarCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCajaActionPerformed
        cerrarCaja();
    }//GEN-LAST:event_btnCerrarCajaActionPerformed

    private void btnAbrirCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirCajaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAbrirCajaMouseClicked

    private void btnAbrirCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCajaActionPerformed
        abrirCaja();
        recargarTabla();
        recargarTablaSalida();
        
    }//GEN-LAST:event_btnAbrirCajaActionPerformed

    private void btnSetMontoInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSetMontoInicialMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSetMontoInicialMouseClicked

    private void btnSetMontoInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetMontoInicialActionPerformed
        setMontoInicial();
    }//GEN-LAST:event_btnSetMontoInicialActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JLabel CajaR;
    private javax.swing.JPanel ContentAll;
    private javax.swing.JPanel ContentResumenDia;
    private javax.swing.JPanel ContentSalidaDinero;
    private javax.swing.JPanel ContentSalidas;
    private javax.swing.JLabel DineroMontoActual;
    private javax.swing.JLabel DineroMontoInicial;
    private javax.swing.JLabel LblCajaCobro;
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
    private javax.swing.JLabel LblSalidaCaja;
    private javax.swing.JLabel LblTotalVendido;
    private javax.swing.JLabel LblUser;
    private javax.swing.JLabel LblUserLogged;
    private javax.swing.JLabel MontoGastoCajaChica;
    private javax.swing.JPanel PanelDatosResumenDia;
    private javax.swing.JPanel PanelDatosSalida;
    private javax.swing.JPanel PanelDivisor1;
    private javax.swing.JPanel PanelDivisor2;
    private javax.swing.JPanel PanelDivisor3;
    private javax.swing.JPanel PanelDivisor4;
    private javax.swing.JPanel PanelDivisor5;
    private javax.swing.JLabel ResumenTotalVendido;
    private javax.swing.JScrollPane ScrollSalida;
    private Utils.MaterialTabbed TabResumenSalida;
    private javax.swing.JTable TablaCaja;
    private javax.swing.JTable TablaSalida;
    private rojerusan.RSMaterialButtonRectangle btnAbrirCaja;
    private rojerusan.RSMaterialButtonRectangle btnCerrarCaja;
    private rojerusan.RSMaterialButtonRectangle btnSetMontoInicial;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane scrollConceptoSalida;
    private javax.swing.JScrollPane scrollTablaCaja;
    private javax.swing.JTextArea txtConceptoSalida;
    private javax.swing.JTextField txtMontoSalida;
    // End of variables declaration//GEN-END:variables
}
