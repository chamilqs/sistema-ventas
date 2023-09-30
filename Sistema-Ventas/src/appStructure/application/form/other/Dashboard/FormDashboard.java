package appStructure.application.form.other.Dashboard;

import Database.Connect.ConexionSingleton;
import Database.DTO.Usuario;
import Utils.Tools;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Timer;
import appStructure.application.Application;
import appStructure.application.form.other.Ventas.FormCaja;
import raven.toast.Notifications;

/**
 *
 * @author Samil
 */
public class FormDashboard extends javax.swing.JPanel {

    Usuario u = null;
    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormDashboard() {
        initComponents();
        inicializar();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
    }

    public FormDashboard(Usuario u) {
        initComponents();
        this.u = u;
        inicializar();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
    }
    private void inicializar(){

        iconCaja.setSvgImage("appStructure/icon/svg/cashRegister.svg", 300, 300);
        PanelReloj();
        PanelGrafico();
        
        try(Connection conectar = c.getConexion()) {
                            
                String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre IS NULL";
                PreparedStatement pstCheck = conectar.prepareStatement(checkNullDate);
                ResultSet rsCheck = pstCheck.executeQuery();

                if (rsCheck.next()) {
                    
                LblEstadoCaja.setForeground(new Color(51,255,51));
                LblEstadoCaja.setText("Abierta");
                btnCaja.setBackground(Color.red);
                
                } else if (!rsCheck.next()){

                    LblEstadoCaja.setForeground(Color.red);
                    LblEstadoCaja.setText("Cerrada");   
                    btnCaja.setBackground(new Color(0,204,0));
                    
                }   
                
        Timer alertTimer = new Timer(2000, (ActionEvent e) -> {
            AlertaStock();
                });

        alertTimer.setRepeats(false); 
        alertTimer.start();

            } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
            }
        
        
    }
    private void PanelReloj() {
        ModernClockPanel W = new ModernClockPanel();
        W.setSize(370, 370);
        W.setLocation(0, 0);

        Tools.showPane(DateTime, W);
       
    }
    private void PanelGrafico() {
        
        GraficosPrincipal Gp = new GraficosPrincipal();
        Gp.setSize(650, 450);
        Gp.setBackground(new Color(49, 62, 74));
        Gp.setLocation(0, 0); 
        
        Tools.showPane(PanelGraficoPrincipal, Gp);
       
    }
    
    public void AlertaStock() {
        
    String query = "SELECT p.id, p.descripcion, p.prioridad, i.stock " +
                   "FROM producto p " +
                   "INNER JOIN inventario i ON p.id = i.idProducto " +
                   "WHERE (p.prioridad = 'alta' AND i.stock < 15) OR " +
                   "(p.prioridad = 'baja' AND i.stock <= 5)";

        try (Connection cn = c.getConexion()) {
        PreparedStatement preparedStatement = cn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        StringBuilder alertMessage = new StringBuilder();

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String descripcion = resultSet.getString("descripcion");
            String prioridad = resultSet.getString("prioridad");
            int stock = resultSet.getInt("stock"); 

            alertMessage.append("ID: ").append(id)
                        .append(", Descripción: ").append(descripcion)
                        .append(", Prioridad: ").append(prioridad)
                        .append(", Stock: ").append(stock)
                        .append("\n");
        }

        if (alertMessage.length() > 0) {
            // JOptionPane.showMessageDialog(null,"Productos con alerta:\n" + alertMessage , "Alerta de Stock", JOptionPane.WARNING_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Productos con alerta:\n" + alertMessage);
        } 

    } catch (SQLException ex) {
        System.out.println(ex);
    }
}
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Content = new javax.swing.JPanel();
        LblBienvenidos = new javax.swing.JLabel();
        Separador4 = new javax.swing.JPanel();
        DateTime = new javax.swing.JPanel();
        PanelGraficoPrincipal = new javax.swing.JPanel();
        Separador = new javax.swing.JPanel();
        LblTitulo = new javax.swing.JLabel();
        Separador2 = new javax.swing.JPanel();
        Separador3 = new javax.swing.JPanel();
        LblEstadoCaja = new javax.swing.JLabel();
        LblEstadoC = new javax.swing.JLabel();
        Separador5 = new javax.swing.JPanel();
        iconCaja = new Utils.SVGImage2();
        btnCaja = new Utils.ButtonRounded();
        Separador6 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();

        setBackground(new java.awt.Color(24, 39, 72));
        setMinimumSize(new java.awt.Dimension(1115, 760));
        setPreferredSize(new java.awt.Dimension(1115, 760));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Content.setBackground(new java.awt.Color(49, 62, 74));
        Content.setPreferredSize(new java.awt.Dimension(1005, 568));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblBienvenidos.setFont(new java.awt.Font("Montserrat", 3, 14)); // NOI18N
        LblBienvenidos.setForeground(new java.awt.Color(0, 204, 51));
        LblBienvenidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Content.add(LblBienvenidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 280, 30));

        Separador4.setBackground(new java.awt.Color(24, 39, 72));

        javax.swing.GroupLayout Separador4Layout = new javax.swing.GroupLayout(Separador4);
        Separador4.setLayout(Separador4Layout);
        Separador4Layout.setHorizontalGroup(
            Separador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1115, Short.MAX_VALUE)
        );
        Separador4Layout.setVerticalGroup(
            Separador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        Content.add(Separador4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 695, 1115, 16));

        DateTime.setBackground(new java.awt.Color(49, 62, 74));
        DateTime.setLayout(new java.awt.BorderLayout());
        Content.add(DateTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 70, 290, 120));

        PanelGraficoPrincipal.setBackground(new java.awt.Color(49, 62, 74));
        PanelGraficoPrincipal.setFont(new java.awt.Font("Montserrat", 0, 10)); // NOI18N

        javax.swing.GroupLayout PanelGraficoPrincipalLayout = new javax.swing.GroupLayout(PanelGraficoPrincipal);
        PanelGraficoPrincipal.setLayout(PanelGraficoPrincipalLayout);
        PanelGraficoPrincipalLayout.setHorizontalGroup(
            PanelGraficoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        PanelGraficoPrincipalLayout.setVerticalGroup(
            PanelGraficoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        Content.add(PanelGraficoPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 155, 635, 460));

        Separador.setBackground(new java.awt.Color(24, 39, 72));

        javax.swing.GroupLayout SeparadorLayout = new javax.swing.GroupLayout(Separador);
        Separador.setLayout(SeparadorLayout);
        SeparadorLayout.setHorizontalGroup(
            SeparadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        SeparadorLayout.setVerticalGroup(
            SeparadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 711, Short.MAX_VALUE)
        );

        Content.add(Separador, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 10, 711));

        LblTitulo.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        LblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LblTitulo.setText("Productos más vendidos:");
        Content.add(LblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        Separador2.setBackground(new java.awt.Color(24, 39, 72));

        javax.swing.GroupLayout Separador2Layout = new javax.swing.GroupLayout(Separador2);
        Separador2.setLayout(Separador2Layout);
        Separador2Layout.setHorizontalGroup(
            Separador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Separador2Layout.setVerticalGroup(
            Separador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 711, Short.MAX_VALUE)
        );

        Content.add(Separador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 0, 10, 711));

        Separador3.setBackground(new java.awt.Color(24, 39, 72));

        javax.swing.GroupLayout Separador3Layout = new javax.swing.GroupLayout(Separador3);
        Separador3.setLayout(Separador3Layout);
        Separador3Layout.setHorizontalGroup(
            Separador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        Separador3Layout.setVerticalGroup(
            Separador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 711, Short.MAX_VALUE)
        );

        Content.add(Separador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 15, 711));

        LblEstadoCaja.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblEstadoCaja.setForeground(new java.awt.Color(0, 204, 0));
        LblEstadoCaja.setText("Abierta");
        Content.add(LblEstadoCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 530, -1, -1));

        LblEstadoC.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblEstadoC.setForeground(new java.awt.Color(255, 255, 255));
        LblEstadoC.setText("La caja está:");
        Content.add(LblEstadoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 530, -1, -1));

        Separador5.setBackground(new java.awt.Color(24, 39, 72));

        javax.swing.GroupLayout Separador5Layout = new javax.swing.GroupLayout(Separador5);
        Separador5.setLayout(Separador5Layout);
        Separador5Layout.setHorizontalGroup(
            Separador5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1110, Short.MAX_VALUE)
        );
        Separador5Layout.setVerticalGroup(
            Separador5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        Content.add(Separador5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        iconCaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Content.add(iconCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 240, 330, 290));

        btnCaja.setBackground(new java.awt.Color(0, 204, 0));
        btnCaja.setForeground(new java.awt.Color(255, 255, 255));
        btnCaja.setText("IR A CAJA");
        btnCaja.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCajaActionPerformed(evt);
            }
        });
        Content.add(btnCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 580, 150, -1));

        add(Content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1120, 711));

        Separador6.setBackground(new java.awt.Color(24, 39, 72));
        Separador6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Dashboard");
        Separador6.add(lb, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 130, 50));

        add(Separador6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCajaActionPerformed
        Application.showForm(new FormCaja());
    }//GEN-LAST:event_btnCajaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JPanel DateTime;
    private javax.swing.JLabel LblBienvenidos;
    private javax.swing.JLabel LblEstadoC;
    private javax.swing.JLabel LblEstadoCaja;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JPanel PanelGraficoPrincipal;
    private javax.swing.JPanel Separador;
    private javax.swing.JPanel Separador2;
    private javax.swing.JPanel Separador3;
    private javax.swing.JPanel Separador4;
    private javax.swing.JPanel Separador5;
    private javax.swing.JPanel Separador6;
    private Utils.ButtonRounded btnCaja;
    private Utils.SVGImage2 iconCaja;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
