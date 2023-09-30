
package appStructure.application.form.other.Contabilidad.Registrar;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Database.Connect.ConexionSingleton;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import appStructure.application.form.other.Contabilidad.FormGastos;

/**
 *
 * @author Samil
 */
public class RegistrarGasto extends javax.swing.JDialog {

    int xMouse, yMouse;
    FormGastos tb = new FormGastos();
    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    LocalDateTime fechaActual = LocalDateTime.now();     
    
    public RegistrarGasto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //Para los CaretUpdate
        txtConcepto.addCaretListener((javax.swing.event.CaretEvent evt) -> {
            txtConceptoCaretUpdate(evt);
        });

        txtMontoGasto.addCaretListener((javax.swing.event.CaretEvent evt) -> {
            txtMontoGastoCaretUpdate(evt);
        });
        
        // Para Arrastrar la Ventana
        PanelMoveLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalirMousePressed(evt);
            }    
        });
        PanelMoveLogin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PanelMoveLoginMouseDragged(evt);
            }
        });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        PanelDatos = new javax.swing.JPanel();
        LblDeudor = new javax.swing.JLabel();
        LblTitulo = new javax.swing.JLabel();
        LblFechaGastos = new javax.swing.JLabel();
        LblMontoPagar = new javax.swing.JLabel();
        LblMontoDeuda = new javax.swing.JLabel();
        txtConcepto = new javax.swing.JTextField();
        BtnGuardar = new javax.swing.JButton();
        PanelMoveLogin = new javax.swing.JPanel();
        btnSalir = new Utils.ButtonRounded();
        txtMontoGasto = new javax.swing.JTextField();
        LblHoraGastos = new javax.swing.JLabel();
        LblMontoPagar1 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        PanelDatos.setBackground(new java.awt.Color(24, 39, 72));

        LblDeudor.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblDeudor.setForeground(new java.awt.Color(255, 255, 255));
        LblDeudor.setText("Concepto:");

        LblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        LblTitulo.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        LblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LblTitulo.setText("Gasto a Realizar");

        LblFechaGastos.setBackground(new java.awt.Color(255, 255, 255));
        LblFechaGastos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        LblFechaGastos.setForeground(new java.awt.Color(0, 0, 0));
        LblFechaGastos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblFechaGastos.setEnabled(false);
        LblFechaGastos.setOpaque(true);

        LblMontoPagar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoPagar.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoPagar.setText("Fecha:");

        LblMontoDeuda.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoDeuda.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoDeuda.setText("Monto Gasto:");

        txtConcepto.setBackground(new java.awt.Color(255, 255, 255));
        txtConcepto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtConcepto.setForeground(new java.awt.Color(0, 0, 0));
        txtConcepto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtConcepto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConceptoCaretUpdate(evt);
            }
        });
        txtConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConceptoKeyTyped(evt);
            }
        });

        BtnGuardar.setBackground(new java.awt.Color(132, 178, 80));
        BtnGuardar.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        BtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        BtnGuardar.setText("GUARDAR");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        PanelMoveLogin.setBackground(new java.awt.Color(24, 39, 72));
        PanelMoveLogin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PanelMoveLoginMouseDragged(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(255, 51, 51));
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText(" X ");
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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

        javax.swing.GroupLayout PanelMoveLoginLayout = new javax.swing.GroupLayout(PanelMoveLogin);
        PanelMoveLogin.setLayout(PanelMoveLoginLayout);
        PanelMoveLoginLayout.setHorizontalGroup(
            PanelMoveLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMoveLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMoveLoginLayout.setVerticalGroup(
            PanelMoveLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMoveLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        txtMontoGasto.setBackground(new java.awt.Color(255, 255, 255));
        txtMontoGasto.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtMontoGasto.setForeground(new java.awt.Color(51, 51, 51));
        txtMontoGasto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMontoGastoCaretUpdate(evt);
            }
        });
        txtMontoGasto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoGastoKeyTyped(evt);
            }
        });

        LblHoraGastos.setBackground(new java.awt.Color(255, 255, 255));
        LblHoraGastos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        LblHoraGastos.setForeground(new java.awt.Color(0, 0, 0));
        LblHoraGastos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblHoraGastos.setEnabled(false);
        LblHoraGastos.setOpaque(true);

        LblMontoPagar1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblMontoPagar1.setForeground(new java.awt.Color(255, 255, 255));
        LblMontoPagar1.setText("Hora:");

        javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMoveLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(LblTitulo))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                .addComponent(LblMontoDeuda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMontoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                .addComponent(LblDeudor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                .addComponent(LblMontoPagar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LblFechaGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                .addComponent(LblMontoPagar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LblHoraGastos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        PanelDatosLayout.setVerticalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addComponent(PanelMoveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LblTitulo)
                .addGap(34, 34, 34)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblDeudor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LblMontoDeuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMontoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblMontoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblFechaGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblHoraGastos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblMontoPagar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(BtnGuardar)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PanelMoveLoginMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMoveLoginMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_PanelMoveLoginMouseDragged

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        try {   
            registrarGasto();
            tb.obtenerGastos();
        } catch (SQLException ex) {
            System.out.println(ex);
        }            
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void txtConceptoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConceptoCaretUpdate
        actualizarFechaYHora();
    }//GEN-LAST:event_txtConceptoCaretUpdate

    private void txtMontoGastoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMontoGastoCaretUpdate
        actualizarFechaYHora();
    }//GEN-LAST:event_txtMontoGastoCaretUpdate

    private void txtConceptoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConceptoKeyTyped
        char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
            BtnGuardar.doClick();
            //registrarGasto();  
        }
        //this.dispose();
    }//GEN-LAST:event_txtConceptoKeyTyped

    private void txtMontoGastoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoGastoKeyTyped
       char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
                BtnGuardar.doClick();
//             registrarGasto();  
//             this.dispose(); 
        }
        
    }//GEN-LAST:event_txtMontoGastoKeyTyped

    private void btnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_btnSalirMousePressed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void actualizarFechaYHora() {
    LocalDateTime now = LocalDateTime.now();
    
    // Formatea la fecha y la hora en el formato deseado
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    String fechaActual = now.format(dateFormatter);
    String horaActual = now.format(timeFormatter);
    
    LblFechaGastos.setText(fechaActual);
    LblHoraGastos.setText(horaActual);
    }
    
    private void registrarGasto() throws SQLException {
        String concepto = txtConcepto.getText();
        String montoStr = txtMontoGasto.getText();

        if (concepto.isEmpty() || montoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double monto;
        try {
            monto = Double.parseDouble(montoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El monto ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
            
        String sql = "INSERT INTO gastos (concepto, monto, fecha) VALUES (?, ?, ?)";

        try (Connection connection = c.getConexion()) {
            
            c.desactivarAutoCommit();
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, concepto);
            preparedStatement.setDouble(2, monto);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(fechaActual));
           // preparedStatement.setTimestamp(3, now);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                this.dispose();
              //  JOptionPane.showMessageDialog(this, "Gasto registrado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar el gasto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            c.commit();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this, "Error al acceder a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            c.rollback();
            
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JLabel LblDeudor;
    private javax.swing.JLabel LblFechaGastos;
    private javax.swing.JLabel LblHoraGastos;
    private javax.swing.JLabel LblMontoDeuda;
    private javax.swing.JLabel LblMontoPagar;
    private javax.swing.JLabel LblMontoPagar1;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JPanel PanelMoveLogin;
    private Utils.ButtonRounded btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtConcepto;
    private javax.swing.JTextField txtMontoGasto;
    // End of variables declaration//GEN-END:variables
}
