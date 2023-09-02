package Venta;

import Connect.Conexion;
import Utils.Utils;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cotizacion extends javax.swing.JDialog {
   
    public Cotizacion() {
              
        initComponents();
        setModal(true);
        Utils.setScaledImage(IconLogo, "ParadaLogo.png", 100, 100);
        
        // Setear la hora actual
        Date fechaHoraActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

        String fecha = formatoFecha.format(fechaHoraActual);
        String hora = formatoHora.format(fechaHoraActual);

        LblFechaFactura.setText(fecha);
        LblHoraFactura.setText(hora);
        
    }
    
    /**
     *
     * @param id
     */
 
    private float total = 0;
    public void addProducto(String ID, String nombre, int cantidad, float precio, float totalProducto) {
        
        DefaultTableModel model = (DefaultTableModel) TablaFacturacion.getModel();
        model.addRow(new Object[]{ID, nombre, cantidad, precio});
        
        total += totalProducto; 
        LblTotalFactura.setText(String.format("%.2f", total));
        
    }
    private void checkCliente(){
        String idCliente = txtIDCliente.getText();
        String consulta = "SELECT id, nombre FROM cliente WHERE id = ?";
        
    try {
        Connection conectar = Conexion.conectar();
        PreparedStatement pst = conectar.prepareStatement(consulta);
        pst.setString(1, idCliente);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            txtNombreCliente.setText(rs.getString("nombre"));            
        } 
        
    } catch (SQLException ex) {
        Logger.getLogger(PanelVenta.class.getName()).log(Level.SEVERE, null, ex);
        }   
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContentDialog = new javax.swing.JPanel();
        ScrollFactura = new javax.swing.JScrollPane();
        TablaFacturacion = new javax.swing.JTable();
        LblTtituloFactura = new javax.swing.JLabel();
        LblFecha = new javax.swing.JLabel();
        IconLogo = new javax.swing.JLabel();
        LblIDCliente = new javax.swing.JLabel();
        LblTotal = new javax.swing.JLabel();
        LblTotalFactura = new javax.swing.JLabel();
        LblNombre = new javax.swing.JLabel();
        LblFechaFactura = new javax.swing.JLabel();
        LblHoraFactura = new javax.swing.JLabel();
        LblHora = new javax.swing.JLabel();
        txtIDCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        BtnPrint = new javax.swing.JButton();
        SeparadorNombreCliente = new javax.swing.JSeparator();
        SeparadorFecha = new javax.swing.JSeparator();
        SeparadorTotal = new javax.swing.JSeparator();
        SeparadorIDCliente = new javax.swing.JSeparator();
        SeparadorHora = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(24, 39, 72));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(415, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ContentDialog.setBackground(new java.awt.Color(24, 39, 72));
        ContentDialog.setMaximumSize(new java.awt.Dimension(415, 600));
        ContentDialog.setMinimumSize(new java.awt.Dimension(415, 600));
        ContentDialog.setPreferredSize(new java.awt.Dimension(415, 600));
        ContentDialog.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaFacturacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Productos", "Cantidad", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
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
        TablaFacturacion.getTableHeader().setReorderingAllowed(false);
        ScrollFactura.setViewportView(TablaFacturacion);
        if (TablaFacturacion.getColumnModel().getColumnCount() > 0) {
            TablaFacturacion.getColumnModel().getColumn(0).setResizable(false);
            TablaFacturacion.getColumnModel().getColumn(0).setPreferredWidth(25);
            TablaFacturacion.getColumnModel().getColumn(1).setResizable(false);
            TablaFacturacion.getColumnModel().getColumn(2).setResizable(false);
            TablaFacturacion.getColumnModel().getColumn(2).setPreferredWidth(15);
            TablaFacturacion.getColumnModel().getColumn(3).setResizable(false);
            TablaFacturacion.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        ContentDialog.add(ScrollFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 360, 334));

        LblTtituloFactura.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblTtituloFactura.setForeground(new java.awt.Color(255, 255, 255));
        LblTtituloFactura.setText("PARADA FRÍA");
        ContentDialog.add(LblTtituloFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        LblFecha.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblFecha.setForeground(new java.awt.Color(255, 255, 255));
        LblFecha.setText("Fecha: ");
        ContentDialog.add(LblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, -1, 20));

        IconLogo.setForeground(new java.awt.Color(255, 255, 255));
        ContentDialog.add(IconLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, 100));

        LblIDCliente.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblIDCliente.setForeground(new java.awt.Color(255, 255, 255));
        LblIDCliente.setText("ID Client:");
        ContentDialog.add(LblIDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 20));

        LblTotal.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblTotal.setForeground(new java.awt.Color(255, 255, 255));
        LblTotal.setText("TOTAL:");
        ContentDialog.add(LblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 520, -1, 20));

        LblTotalFactura.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblTotalFactura.setForeground(new java.awt.Color(255, 255, 255));
        LblTotalFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ContentDialog.add(LblTotalFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, 120, 20));

        LblNombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblNombre.setForeground(new java.awt.Color(255, 255, 255));
        LblNombre.setText(" Nombre:");
        ContentDialog.add(LblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 20));

        LblFechaFactura.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblFechaFactura.setForeground(new java.awt.Color(255, 255, 255));
        LblFechaFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ContentDialog.add(LblFechaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 110, 110, 20));

        LblHoraFactura.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        LblHoraFactura.setForeground(new java.awt.Color(255, 255, 255));
        LblHoraFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ContentDialog.add(LblHoraFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 140, 110, 20));

        LblHora.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblHora.setForeground(new java.awt.Color(255, 255, 255));
        LblHora.setText("Hora:");
        ContentDialog.add(LblHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 140, -1, 20));

        txtIDCliente.setBackground(new java.awt.Color(24, 39, 72));
        txtIDCliente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtIDCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtIDCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDCliente.setBorder(null);
        ContentDialog.add(txtIDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 120, 20));

        txtNombreCliente.setBackground(new java.awt.Color(24, 39, 72));
        txtNombreCliente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreCliente.setBorder(null);
        txtNombreCliente.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });
        ContentDialog.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 120, 20));

        BtnPrint.setText("PRINT");
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        ContentDialog.add(BtnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, -1, -1));

        SeparadorNombreCliente.setBackground(new java.awt.Color(255, 255, 255));
        SeparadorNombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorNombreCliente.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        SeparadorNombreCliente.setMinimumSize(new java.awt.Dimension(0, 2));
        SeparadorNombreCliente.setOpaque(true);
        SeparadorNombreCliente.setPreferredSize(new java.awt.Dimension(0, 2));
        ContentDialog.add(SeparadorNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 120, -1));

        SeparadorFecha.setBackground(new java.awt.Color(255, 255, 255));
        SeparadorFecha.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorFecha.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        SeparadorFecha.setMinimumSize(new java.awt.Dimension(0, 2));
        SeparadorFecha.setOpaque(true);
        SeparadorFecha.setPreferredSize(new java.awt.Dimension(0, 2));
        ContentDialog.add(SeparadorFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 130, 110, -1));

        SeparadorTotal.setBackground(new java.awt.Color(255, 255, 255));
        SeparadorTotal.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorTotal.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        SeparadorTotal.setMinimumSize(new java.awt.Dimension(0, 2));
        SeparadorTotal.setOpaque(true);
        SeparadorTotal.setPreferredSize(new java.awt.Dimension(0, 2));
        ContentDialog.add(SeparadorTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 540, 120, -1));

        SeparadorIDCliente.setBackground(new java.awt.Color(255, 255, 255));
        SeparadorIDCliente.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorIDCliente.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        SeparadorIDCliente.setMinimumSize(new java.awt.Dimension(0, 2));
        SeparadorIDCliente.setOpaque(true);
        SeparadorIDCliente.setPreferredSize(new java.awt.Dimension(0, 2));
        ContentDialog.add(SeparadorIDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 120, -1));

        SeparadorHora.setBackground(new java.awt.Color(255, 255, 255));
        SeparadorHora.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorHora.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        SeparadorHora.setMinimumSize(new java.awt.Dimension(0, 2));
        SeparadorHora.setOpaque(true);
        SeparadorHora.setPreferredSize(new java.awt.Dimension(0, 2));
        ContentDialog.add(SeparadorHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 160, 110, -1));

        getContentPane().add(ContentDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 415, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed

        int option = JOptionPane.showConfirmDialog(this, "¿Estás seguro de imprimir?", "Imprimir factura.", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // Agregar la opcion de que el JDialog "se imprima" como PDF. 
            try {

        BufferedImage bf = new BufferedImage(
            ContentDialog.getWidth(), ContentDialog.getHeight(), BufferedImage.TYPE_INT_RGB
        );
        Graphics2D graphics = bf.createGraphics();
        ContentDialog.print(graphics);
        graphics.dispose();        

        PrinterJob printJob = PrinterJob.getPrinterJob();

            if (printJob.printDialog()) {

                printJob.setPrintable(new Printable() {
                    public int print(Graphics g, PageFormat pf, int pageIndex) {
                        if (pageIndex != 0) {
                            return NO_SUCH_PAGE;
                        }
                        Graphics2D g2 = (Graphics2D) g;
                        g2.translate(pf.getImageableX(), pf.getImageableY());
                        g2.drawImage(bf, 0, 0, ContentDialog.getWidth(), ContentDialog.getHeight(), null);
                        return PAGE_EXISTS;
                    }
                });

                printJob.print(); 

            }
            
        } catch (PrinterException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this, "Error al imprimir la factura.", "Error", JOptionPane.ERROR_MESSAGE
            );
        }           
            
            // Cerrar la ventana actual.
            this.dispose();

        }
    }//GEN-LAST:event_BtnPrintActionPerformed
    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        char t = evt.getKeyChar();
        if(t == KeyEvent.VK_ENTER){ 
        checkCliente();
        }    
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cotizacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnPrint;
    private javax.swing.JPanel ContentDialog;
    private javax.swing.JLabel IconLogo;
    private javax.swing.JLabel LblFecha;
    private javax.swing.JLabel LblFechaFactura;
    private javax.swing.JLabel LblHora;
    private javax.swing.JLabel LblHoraFactura;
    private javax.swing.JLabel LblIDCliente;
    private javax.swing.JLabel LblNombre;
    private javax.swing.JLabel LblTotal;
    private javax.swing.JLabel LblTotalFactura;
    private javax.swing.JLabel LblTtituloFactura;
    private javax.swing.JScrollPane ScrollFactura;
    private javax.swing.JSeparator SeparadorFecha;
    private javax.swing.JSeparator SeparadorHora;
    private javax.swing.JSeparator SeparadorIDCliente;
    private javax.swing.JSeparator SeparadorNombreCliente;
    private javax.swing.JSeparator SeparadorTotal;
    private javax.swing.JTable TablaFacturacion;
    private javax.swing.JTextField txtIDCliente;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
