import Caja.PanelCaja;
import Connect.Conexion;
import Utils.Utils;
import java.awt.Color;
import javax.swing.Timer;
import DTO.Usuario;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Angelo
 */


public class Principal extends javax.swing.JPanel {

    Usuario u = null;
    private int letraActual = 0;
    private int repeticiones = 0;
    private String Mensaje;
    private Timer timer;

    public Principal() {
        initComponents();
        inicializar();
       
        Mensaje = "Bienvenido/a ";

        
    }

    public Principal(Usuario u) {
        this.u = u;
        String nombre = u.getNombre();
        Mensaje = "Bienvenido/a " + nombre;

        initComponents();
        inicializar();
    }
      
    
    private void inicializar(){
         agregarRelojPanel();
        agregarGraficosPastel();
        FlatLightLaf.install(); // Instalar el tema FlatLaf
        
        try {
                Connection conectar = Conexion.conectar();
                String checkNullDate = "SELECT fecha_Cierre FROM historialcaja WHERE fecha_Cierre IS NULL";
                PreparedStatement pstCheck = conectar.prepareStatement(checkNullDate);
                ResultSet rsCheck = pstCheck.executeQuery();

                if (rsCheck.next()) {
                    
                LblEstadoCaja.setForeground(new Color(0,204,0));    
                LblEstadoCaja.setText("Abierta");


                } else if (!rsCheck.next()){

                LblEstadoCaja.setForeground(Color.red);
                LblEstadoCaja.setText("Cerrada");

                }
                Conexion.desconectar(conectar);
        } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
            }
        
        timer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPalabraInteractiva();
            }
        });

        timer.start();

        Utils.setScaledImage(CajaVerde, "CajaVerde.png", 200, 200);
        
 
         // Configurar y ejecutar el Timer aquí
        Timer alertTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = Conexion.conectar();
                    mostrarAlertasProductosBajos(connection);
                    Conexion.desconectar(connection);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        alertTimer.setRepeats(false); // Ejecutar solo una vez
        alertTimer.start();
    }

    private void mostrarPalabraInteractiva() {
        if (letraActual < Mensaje.length()) {
            LblBienvenidos.setText(Mensaje.substring(0, letraActual + 1));
            letraActual++;
        } else {
            letraActual = 0;
            repeticiones++;
            LblBienvenidos.setText("");
            if (repeticiones >= 10000) {
                timer.stop();
            } else {
                // Agregar una pausa de 1.5 segundos
                Timer pausaTimer = new Timer(1500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        timer.start(); // Reanudar el timer después de la pausa
                    }
                });
                pausaTimer.setRepeats(false); // Solo se ejecutará una vez
                pausaTimer.start();
            }
        }
    }

    private void agregarRelojPanel() {
        ModernClockPanel W = new ModernClockPanel();
        W.setSize(370, 370);
        W.setLocation(0, 0);

        Utils.showPane(DateTime, W);
       
    }
    
    private void agregarGraficosPastel() {
        GraficosPrincipal Gp = new GraficosPrincipal();
        Gp.setSize(650, 450);
        Gp.setBackground(Color.white);
        Gp.setLocation(0, 0); 
        
//        JLabel titleLabel = new JLabel("Productos más vendidos");
//        Font font = new Font("Montserrat", Font.PLAIN, 18); // Cambia el tamaño y estilo de la fuente si es necesario
//        titleLabel.setFont(font);
//        Gp.add(titleLabel, BorderLayout.NORTH); // Agregar en la parte superior

        Utils.showPane(PanelGraficoPrincipal, Gp);
       
    }

    public static void mostrarAlertasProductosBajos(Connection connection) {
    String query = "SELECT p.id, p.descripcion, p.prioridad, i.stock " +
                   "FROM producto p " +
                   "INNER JOIN inventario i ON p.id = i.idProducto " +
                   "WHERE (p.prioridad = 'alta' AND i.stock < 15) OR " +
                   "(p.prioridad = 'baja' AND i.stock <= 5)";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        StringBuilder alertMessage = new StringBuilder();

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String descripcion = resultSet.getString("descripcion");
            String prioridad = resultSet.getString("prioridad");
            int stock = resultSet.getInt("stock"); // Use "stock" instead of "cantidad"

            alertMessage.append("ID: ").append(id)
                        .append(", Descripción: ").append(descripcion)
                        .append(", Prioridad: ").append(prioridad)
                        .append(", Stock: ").append(stock)
                        .append("\n");
        }

        if (alertMessage.length() > 0) {
            JOptionPane.showMessageDialog(null, "Productos con alerta:\n" + alertMessage, "Alerta de Stock", JOptionPane.WARNING_MESSAGE);
        } else {
       //     JOptionPane.showMessageDialog(null, "No hay productos con alerta de stock.", "Alerta de Stock", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ContentAll = new javax.swing.JPanel();
        Content = new javax.swing.JPanel();
        LblBienvenidos = new javax.swing.JLabel();
        BtnNuevaVenta = new javax.swing.JPanel();
        DateTime = new javax.swing.JPanel();
        imgContent = new javax.swing.JPanel();
        CajaVerde = new javax.swing.JLabel();
        PanelGraficoPrincipal = new javax.swing.JPanel();
        Separador = new javax.swing.JPanel();
        LblTitulo = new javax.swing.JLabel();
        Separador2 = new javax.swing.JPanel();
        Separador3 = new javax.swing.JPanel();
        LblEstadoCaja = new javax.swing.JLabel();
        LblEstadoC = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setMaximumSize(new java.awt.Dimension(1005, 660));
        setMinimumSize(new java.awt.Dimension(1005, 660));

        ContentAll.setBackground(new java.awt.Color(24, 39, 72));
        ContentAll.setPreferredSize(new java.awt.Dimension(1005, 660));

        Content.setBackground(new java.awt.Color(255, 255, 255));
        Content.setPreferredSize(new java.awt.Dimension(1005, 568));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblBienvenidos.setFont(new java.awt.Font("Montserrat", 3, 14)); // NOI18N
        LblBienvenidos.setForeground(new java.awt.Color(0, 204, 51));
        LblBienvenidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Content.add(LblBienvenidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 280, 30));

        BtnNuevaVenta.setBackground(new java.awt.Color(132, 178, 80));
        BtnNuevaVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnNuevaVentaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnNuevaVentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnNuevaVentaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout BtnNuevaVentaLayout = new javax.swing.GroupLayout(BtnNuevaVenta);
        BtnNuevaVenta.setLayout(BtnNuevaVentaLayout);
        BtnNuevaVentaLayout.setHorizontalGroup(
            BtnNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1008, Short.MAX_VALUE)
        );
        BtnNuevaVentaLayout.setVerticalGroup(
            BtnNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        Content.add(BtnNuevaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 645, 1008, 15));

        DateTime.setBackground(new java.awt.Color(255, 255, 255));
        DateTime.setLayout(new java.awt.BorderLayout());
        Content.add(DateTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 290, 120));

        imgContent.setBackground(new java.awt.Color(255, 255, 255));
        imgContent.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        imgContent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgContentMouseClicked(evt);
            }
        });

        CajaVerde.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CajaVerde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CajaVerdeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout imgContentLayout = new javax.swing.GroupLayout(imgContent);
        imgContent.setLayout(imgContentLayout);
        imgContentLayout.setHorizontalGroup(
            imgContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imgContentLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(CajaVerde, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        imgContentLayout.setVerticalGroup(
            imgContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imgContentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CajaVerde, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Content.add(imgContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 250, 310, 240));

        PanelGraficoPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        PanelGraficoPrincipal.setFont(new java.awt.Font("Montserrat", 0, 10)); // NOI18N

        javax.swing.GroupLayout PanelGraficoPrincipalLayout = new javax.swing.GroupLayout(PanelGraficoPrincipal);
        PanelGraficoPrincipal.setLayout(PanelGraficoPrincipalLayout);
        PanelGraficoPrincipalLayout.setHorizontalGroup(
            PanelGraficoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        PanelGraficoPrincipalLayout.setVerticalGroup(
            PanelGraficoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        Content.add(PanelGraficoPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 140, 635, 450));

        Separador.setBackground(new java.awt.Color(132, 178, 80));

        javax.swing.GroupLayout SeparadorLayout = new javax.swing.GroupLayout(Separador);
        Separador.setLayout(SeparadorLayout);
        SeparadorLayout.setHorizontalGroup(
            SeparadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        SeparadorLayout.setVerticalGroup(
            SeparadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );

        Content.add(Separador, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 10, 645));

        LblTitulo.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        LblTitulo.setForeground(new java.awt.Color(51, 51, 51));
        LblTitulo.setText("Productos más vendidos:");
        Content.add(LblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        Separador2.setBackground(new java.awt.Color(132, 178, 80));

        javax.swing.GroupLayout Separador2Layout = new javax.swing.GroupLayout(Separador2);
        Separador2.setLayout(Separador2Layout);
        Separador2Layout.setHorizontalGroup(
            Separador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        Separador2Layout.setVerticalGroup(
            Separador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );

        Content.add(Separador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 15, 645));

        Separador3.setBackground(new java.awt.Color(132, 178, 80));

        javax.swing.GroupLayout Separador3Layout = new javax.swing.GroupLayout(Separador3);
        Separador3.setLayout(Separador3Layout);
        Separador3Layout.setHorizontalGroup(
            Separador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        Separador3Layout.setVerticalGroup(
            Separador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );

        Content.add(Separador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 15, 645));

        LblEstadoCaja.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        LblEstadoCaja.setForeground(new java.awt.Color(0, 204, 0));
        LblEstadoCaja.setText("Abierta");
        Content.add(LblEstadoCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 490, -1, -1));

        LblEstadoC.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        LblEstadoC.setForeground(new java.awt.Color(51, 51, 51));
        LblEstadoC.setText("Estado Caja:");
        Content.add(LblEstadoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 490, -1, -1));

        javax.swing.GroupLayout ContentAllLayout = new javax.swing.GroupLayout(ContentAll);
        ContentAll.setLayout(ContentAllLayout);
        ContentAllLayout.setHorizontalGroup(
            ContentAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ContentAllLayout.setVerticalGroup(
            ContentAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContentAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContentAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnNuevaVentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnNuevaVentaMouseEntered
        // BtnNuevaVenta.setBackground(new Color(85, 115, 52));
    }//GEN-LAST:event_BtnNuevaVentaMouseEntered

    private void BtnNuevaVentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnNuevaVentaMouseExited
        // BtnNuevaVenta.setBackground(new Color(132,178,80));
    }//GEN-LAST:event_BtnNuevaVentaMouseExited

    private void BtnNuevaVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnNuevaVentaMouseClicked
//        PanelVenta pv = new PanelVenta();
//        pv.setSize(1005, 660);
//        pv.setLocation(0, 0);
//      
//        Utils.showPane(ContentAll, pv);
    }//GEN-LAST:event_BtnNuevaVentaMouseClicked

    private void CajaVerdeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CajaVerdeMouseClicked
        PanelCaja pc = new PanelCaja(u);
        pc.setSize(1005, 660);
        pc.setLocation(0, 0);

        Utils.showPane(ContentAll, pc);
    }//GEN-LAST:event_CajaVerdeMouseClicked

    private void imgContentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgContentMouseClicked
       PanelCaja pc = new PanelCaja(u);
        pc.setSize(1005, 660);
        pc.setLocation(0, 0);

        Utils.showPane(ContentAll, pc);
    }//GEN-LAST:event_imgContentMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BtnNuevaVenta;
    private javax.swing.JLabel CajaVerde;
    private javax.swing.JPanel Content;
    private javax.swing.JPanel ContentAll;
    private javax.swing.JPanel DateTime;
    private javax.swing.JLabel LblBienvenidos;
    private javax.swing.JLabel LblEstadoC;
    private javax.swing.JLabel LblEstadoCaja;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JPanel PanelGraficoPrincipal;
    private javax.swing.JPanel Separador;
    private javax.swing.JPanel Separador2;
    private javax.swing.JPanel Separador3;
    private javax.swing.JPanel imgContent;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
