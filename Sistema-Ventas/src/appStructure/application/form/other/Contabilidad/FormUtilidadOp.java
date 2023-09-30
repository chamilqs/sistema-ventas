/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appStructure.application.form.other.Contabilidad;

import Database.Connect.Conexion;
import Database.Connect.ConexionSingleton;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samil Quezada Suriel
 */
public class FormUtilidadOp extends javax.swing.JPanel {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public FormUtilidadOp() {
        initComponents();
        init();
    }

    private void init() {
        llenarTablaIngresos();
        llenarTablaGastos();
        calcularUtilidad();
    }
    
    private void calcularUtilidad() {
        String totalIngresosText = TotalIngresos.getText();
        double totalIngresos = Double.parseDouble(totalIngresosText.substring(1));

        String totalGastosText = TotalGastos.getText();
        double totalGastos = Double.parseDouble(totalGastosText.substring(1));

        double totalUtilidad = totalIngresos - totalGastos;

        TotalUtilidad.setForeground(Color.WHITE);
        TotalUtilidad.setText("$" + totalUtilidad);
    }
    private void llenarTablaIngresos() {
    DefaultTableModel modeloIngresos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Evitar que las celdas sean editables
        }
    };

    String queryIngresos = "SELECT concepto, SUM(monto) AS 'Total Ingresos' FROM Ingresos GROUP BY concepto";

    try (Connection connection = c.getConexion()) 
    {
        
        Statement statement = connection.createStatement();
        ResultSet resultSetIngresos = statement.executeQuery(queryIngresos);

        modeloIngresos.addColumn("Concepto Ingresos");
        modeloIngresos.addColumn("Total Ingresos");

        while (resultSetIngresos.next()) {
            Object[] rowData = new Object[2];
            rowData[0] = resultSetIngresos.getString("concepto");
            rowData[1] = resultSetIngresos.getDouble("Total Ingresos");
            modeloIngresos.addRow(rowData);
        }

        TablaUtilidadIngresos.setModel(modeloIngresos);
        
        double totalIngresos = 0;

        for (int row = 0; row < modeloIngresos.getRowCount(); row++) {
            totalIngresos += Double.parseDouble(modeloIngresos.getValueAt(row, 1).toString());
        }

        TotalIngresos.setForeground(new Color(34, 177, 76));
        TotalIngresos.setText("$" + totalIngresos);
        c.desconectar();

    } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(FormUtilidadOp.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    private void llenarTablaGastos() {
        DefaultTableModel modeloGastos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        String queryGastos = "SELECT concepto, SUM(monto) AS 'Total Gastos' FROM Gastos GROUP BY concepto";

        try (Connection connection = c.getConexion();
             Statement statement = connection.createStatement()) {

            ResultSet resultSetGastos = statement.executeQuery(queryGastos);

            modeloGastos.addColumn("Concepto Gastos");
            modeloGastos.addColumn("Total Gastos");

            while (resultSetGastos.next()) {
                Object[] rowData = new Object[2];
                rowData[0] = resultSetGastos.getString("concepto");
                rowData[1] = resultSetGastos.getDouble("Total Gastos");
                modeloGastos.addRow(rowData);
            }
            // Esto es de UTILIDAD
            TablaUtilidadGastos.setModel(modeloGastos);

            double totalGastos = 0;

            for (int row = 0; row < modeloGastos.getRowCount(); row++) {
                totalGastos += Double.parseDouble(modeloGastos.getValueAt(row, 1).toString());
            }

            TotalGastos.setForeground(Color.red);
            TotalGastos.setText("$" + totalGastos);
            
            c.desconectar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormUtilidadOp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelUtilidadOp = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        TablaUtilidadGastos = new javax.swing.JTable();
        LblTitle = new javax.swing.JLabel();
        TotalGastos = new javax.swing.JLabel();
        TotalUtilidad = new javax.swing.JLabel();
        TotalIngresos = new javax.swing.JLabel();
        scroll1 = new javax.swing.JScrollPane();
        TablaUtilidadIngresos = new javax.swing.JTable();
        LblUtilidad = new javax.swing.JLabel();
        LblTotalIngresos = new javax.swing.JLabel();
        LblTotalGasto = new javax.swing.JLabel();
        btnRecargarTabla = new Utils.ButtonRounded();

        PanelUtilidadOp.setBackground(new java.awt.Color(24, 39, 72));
        PanelUtilidadOp.setMinimumSize(new java.awt.Dimension(1115, 760));
        PanelUtilidadOp.setPreferredSize(new java.awt.Dimension(1115, 760));
        PanelUtilidadOp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaUtilidadGastos.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        TablaUtilidadGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Concepto Gastos", "Monto Gastos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaUtilidadGastos.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(TablaUtilidadGastos);
        if (TablaUtilidadGastos.getColumnModel().getColumnCount() > 0) {
            TablaUtilidadGastos.getColumnModel().getColumn(0).setResizable(false);
            TablaUtilidadGastos.getColumnModel().getColumn(1).setResizable(false);
        }

        PanelUtilidadOp.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 460, 540));

        LblTitle.setBackground(new java.awt.Color(255, 255, 255));
        LblTitle.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("Utilidad Operativa");
        PanelUtilidadOp.add(LblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 350, 60));

        TotalGastos.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        TotalGastos.setForeground(new java.awt.Color(255, 255, 255));
        PanelUtilidadOp.add(TotalGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 653, 114, 20));

        TotalUtilidad.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        TotalUtilidad.setForeground(new java.awt.Color(255, 255, 255));
        PanelUtilidadOp.add(TotalUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 706, 130, 40));

        TotalIngresos.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        TotalIngresos.setForeground(new java.awt.Color(255, 255, 255));
        PanelUtilidadOp.add(TotalIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(725, 650, 100, 25));

        TablaUtilidadIngresos.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        TablaUtilidadIngresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Concepto Ingresos", "Total Ingresos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaUtilidadIngresos.getTableHeader().setReorderingAllowed(false);
        scroll1.setViewportView(TablaUtilidadIngresos);
        if (TablaUtilidadIngresos.getColumnModel().getColumnCount() > 0) {
            TablaUtilidadIngresos.getColumnModel().getColumn(0).setResizable(false);
            TablaUtilidadIngresos.getColumnModel().getColumn(1).setResizable(false);
        }

        PanelUtilidadOp.add(scroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 460, 540));

        LblUtilidad.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        LblUtilidad.setForeground(new java.awt.Color(255, 255, 255));
        LblUtilidad.setText("Utilidad Operativa: RD");
        PanelUtilidadOp.add(LblUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 710, -1, 30));

        LblTotalIngresos.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        LblTotalIngresos.setForeground(new java.awt.Color(255, 255, 255));
        LblTotalIngresos.setText("Total Ingresos: RD");
        PanelUtilidadOp.add(LblTotalIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 650, -1, 25));

        LblTotalGasto.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        LblTotalGasto.setForeground(new java.awt.Color(255, 255, 255));
        LblTotalGasto.setText("Total Gastos: RD");
        PanelUtilidadOp.add(LblTotalGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, -1, 24));

        btnRecargarTabla.setBackground(new java.awt.Color(0, 112, 192));
        btnRecargarTabla.setForeground(new java.awt.Color(255, 255, 255));
        btnRecargarTabla.setText("RECARGAR TABLA");
        btnRecargarTabla.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRecargarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarTablaActionPerformed(evt);
            }
        });
        PanelUtilidadOp.add(btnRecargarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 70, 140, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1115, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelUtilidadOp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelUtilidadOp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarTablaActionPerformed
        init();
    }//GEN-LAST:event_btnRecargarTablaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblTitle;
    private javax.swing.JLabel LblTotalGasto;
    private javax.swing.JLabel LblTotalIngresos;
    private javax.swing.JLabel LblUtilidad;
    private javax.swing.JPanel PanelUtilidadOp;
    private javax.swing.JTable TablaUtilidadGastos;
    private javax.swing.JTable TablaUtilidadIngresos;
    private javax.swing.JLabel TotalGastos;
    private javax.swing.JLabel TotalIngresos;
    private javax.swing.JLabel TotalUtilidad;
    private Utils.ButtonRounded btnRecargarTabla;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scroll1;
    // End of variables declaration//GEN-END:variables

}
