package Contabilidad;

import Connect.Conexion;
import Utils.MaterialTabbed;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JFrame;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angelo
 */
public class TabContabilidad extends javax.swing.JPanel {

    private JFrame fr;    
    
    public TabContabilidad() {
        initComponents();
         initialize();
          
    }
    
    public TabContabilidad(JFrame fr) {
      this.fr = fr; 
      initComponents();
      initialize();    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materialTabbed1 = new Utils.MaterialTabbed();
        PanelCobrar = new javax.swing.JPanel();
        ScrollCobrar = new javax.swing.JScrollPane();
        TableCobrar = new javax.swing.JTable();
        BtnRegistrarPagoCCobrar = new rojerusan.RSMaterialButtonRectangle();
        BtnReload = new rojerusan.RSMaterialButtonRectangle();
        ComboFechaCobrar = new javax.swing.JComboBox<>();
        TxtFecha = new javax.swing.JTextField();
        LblNotFecha = new javax.swing.JLabel();
        BtnBuscarCCobrar1 = new rojerusan.RSMaterialButtonRectangle();
        LblUtilidad4 = new javax.swing.JLabel();
        PanelPagar = new javax.swing.JPanel();
        ScrollPagar = new javax.swing.JScrollPane();
        TablePagar = new javax.swing.JTable();
        BtnRegistrarPagoCPagar = new rojerusan.RSMaterialButtonRectangle();
        BtnReloadPagar = new rojerusan.RSMaterialButtonRectangle();
        BtnBuscarPagar = new rojerusan.RSMaterialButtonRectangle();
        ComboFechaPagar = new javax.swing.JComboBox<>();
        TxtFechaPagar = new javax.swing.JTextField();
        LblNotFecha3 = new javax.swing.JLabel();
        LblUtilidad3 = new javax.swing.JLabel();
        PanelGastos = new javax.swing.JPanel();
        PaneldentroGastos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaGastos = new javax.swing.JTable();
        BtnRegistrarGasto = new rojerusan.RSMaterialButtonRectangle();
        BtnReloadGastos = new rojerusan.RSMaterialButtonRectangle();
        BtnBuscarGastos = new rojerusan.RSMaterialButtonRectangle();
        ComboFechaGastos = new javax.swing.JComboBox<>();
        TxtFechaGastos = new javax.swing.JTextField();
        LblNotFecha1 = new javax.swing.JLabel();
        LblUtilidad2 = new javax.swing.JLabel();
        PanelIngresos = new javax.swing.JPanel();
        PanelDentrongresos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaIngresos = new javax.swing.JTable();
        BtnUtilidad = new rojerusan.RSMaterialButtonRectangle();
        BtnReloadIngresos = new rojerusan.RSMaterialButtonRectangle();
        BtnBuscarIngresos = new rojerusan.RSMaterialButtonRectangle();
        ComboFechaIngresos = new javax.swing.JComboBox<>();
        TxtFechaIngresos = new javax.swing.JTextField();
        LblNotFecha2 = new javax.swing.JLabel();
        LblUtilidad1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaUtilidadGastos = new javax.swing.JTable();
        LblUtilidad = new javax.swing.JLabel();
        LblTotalIngresos = new javax.swing.JLabel();
        LblTotalGastos = new javax.swing.JLabel();
        LblTotalUtilidad = new javax.swing.JLabel();
        BtnReloadUtlidad = new rojerusan.RSMaterialButtonRectangle();
        TotalUtilidad = new javax.swing.JLabel();
        TotalIngresos = new javax.swing.JLabel();
        TotalGastos = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaUtilidadIngresos = new javax.swing.JTable();
        TotalUtilidadset = new javax.swing.JLabel();
        TotalIngresos1 = new javax.swing.JLabel();
        TotalGastos1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        LblUtilidad5 = new javax.swing.JLabel();
        BtnReloadCC = new rojerusan.RSMaterialButtonRectangle();
        BtnBuscarCC = new rojerusan.RSMaterialButtonRectangle();
        ComboFechaPago = new javax.swing.JComboBox<>();
        TxtFechaCC = new javax.swing.JTextField();
        LblNotFecha4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaPago = new javax.swing.JTable();
        IconReload = new javax.swing.JLabel();
        PanelBarBlue = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        materialTabbed1.setForeground(new java.awt.Color(255, 255, 255));
        materialTabbed1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N

        PanelCobrar.setBackground(new java.awt.Color(255, 255, 255));
        PanelCobrar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        PanelCobrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableCobrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdVenta", "Cliente Deudor", "Fecha", "Deuda", "TotalFaltante", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableCobrar.getTableHeader().setReorderingAllowed(false);
        ScrollCobrar.setViewportView(TableCobrar);
        if (TableCobrar.getColumnModel().getColumnCount() > 0) {
            TableCobrar.getColumnModel().getColumn(0).setResizable(false);
            TableCobrar.getColumnModel().getColumn(0).setPreferredWidth(20);
            TableCobrar.getColumnModel().getColumn(1).setResizable(false);
            TableCobrar.getColumnModel().getColumn(1).setPreferredWidth(150);
            TableCobrar.getColumnModel().getColumn(2).setResizable(false);
            TableCobrar.getColumnModel().getColumn(2).setPreferredWidth(30);
            TableCobrar.getColumnModel().getColumn(3).setResizable(false);
            TableCobrar.getColumnModel().getColumn(3).setPreferredWidth(20);
            TableCobrar.getColumnModel().getColumn(4).setResizable(false);
            TableCobrar.getColumnModel().getColumn(4).setPreferredWidth(20);
            TableCobrar.getColumnModel().getColumn(5).setResizable(false);
            TableCobrar.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        PanelCobrar.add(ScrollCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 960, 440));

        BtnRegistrarPagoCCobrar.setText("Registrar Pago");
        BtnRegistrarPagoCCobrar.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnRegistrarPagoCCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrarPagoCCobrarActionPerformed(evt);
            }
        });
        PanelCobrar.add(BtnRegistrarPagoCCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 180, 40));

        BtnReload.setText("Reload");
        BtnReload.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReloadActionPerformed(evt);
            }
        });
        PanelCobrar.add(BtnReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, 90, 40));

        ComboFechaCobrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Mes", "Año", "Full Date" }));
        PanelCobrar.add(ComboFechaCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, -1, -1));

        TxtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtFechaKeyTyped(evt);
            }
        });
        PanelCobrar.add(TxtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 130, 130, -1));

        LblNotFecha.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        LblNotFecha.setForeground(new java.awt.Color(51, 51, 51));
        LblNotFecha.setText("YYYY - MM - DD");
        PanelCobrar.add(LblNotFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 120, 10));

        BtnBuscarCCobrar1.setText("Buscar");
        BtnBuscarCCobrar1.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnBuscarCCobrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarCCobrar1ActionPerformed(evt);
            }
        });
        PanelCobrar.add(BtnBuscarCCobrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 80, 40));

        LblUtilidad4.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad4.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblUtilidad4.setForeground(new java.awt.Color(0, 112, 192));
        LblUtilidad4.setText("Cuentas Por Cobrar");
        PanelCobrar.add(LblUtilidad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        materialTabbed1.addTab("Cuentas por Cobrar", PanelCobrar);

        PanelPagar.setBackground(new java.awt.Color(255, 255, 255));
        PanelPagar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        PanelPagar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablePagar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdPagar", "IdAcreedor", "DescripcionAcreedor", "Fecha", "Monto", "TotalFaltante"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablePagar.getTableHeader().setReorderingAllowed(false);
        ScrollPagar.setViewportView(TablePagar);
        if (TablePagar.getColumnModel().getColumnCount() > 0) {
            TablePagar.getColumnModel().getColumn(0).setResizable(false);
            TablePagar.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablePagar.getColumnModel().getColumn(1).setResizable(false);
            TablePagar.getColumnModel().getColumn(1).setPreferredWidth(20);
            TablePagar.getColumnModel().getColumn(2).setResizable(false);
            TablePagar.getColumnModel().getColumn(2).setPreferredWidth(150);
            TablePagar.getColumnModel().getColumn(3).setResizable(false);
            TablePagar.getColumnModel().getColumn(4).setResizable(false);
            TablePagar.getColumnModel().getColumn(4).setPreferredWidth(30);
            TablePagar.getColumnModel().getColumn(5).setResizable(false);
        }

        PanelPagar.add(ScrollPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 940, 420));

        BtnRegistrarPagoCPagar.setText("Registrar Pago");
        BtnRegistrarPagoCPagar.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnRegistrarPagoCPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrarPagoCPagarActionPerformed(evt);
            }
        });
        PanelPagar.add(BtnRegistrarPagoCPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 180, 40));

        BtnReloadPagar.setText("Reload");
        BtnReloadPagar.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnReloadPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReloadPagarActionPerformed(evt);
            }
        });
        PanelPagar.add(BtnReloadPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 90, 40));

        BtnBuscarPagar.setText("Buscar");
        BtnBuscarPagar.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnBuscarPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarPagarActionPerformed(evt);
            }
        });
        PanelPagar.add(BtnBuscarPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 80, 40));

        ComboFechaPagar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Mes", "Año", "Full Date" }));
        PanelPagar.add(ComboFechaPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, -1, -1));

        TxtFechaPagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtFechaPagarKeyTyped(evt);
            }
        });
        PanelPagar.add(TxtFechaPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 140, 130, -1));

        LblNotFecha3.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        LblNotFecha3.setForeground(new java.awt.Color(51, 51, 51));
        LblNotFecha3.setText("YYYY - MM - DD");
        PanelPagar.add(LblNotFecha3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 130, 120, 10));

        LblUtilidad3.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad3.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblUtilidad3.setForeground(new java.awt.Color(0, 112, 192));
        LblUtilidad3.setText("Cuentas Por Pagar");
        PanelPagar.add(LblUtilidad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, -1, -1));

        materialTabbed1.addTab("Cuentas por Pagar", PanelPagar);

        PanelGastos.setPreferredSize(new java.awt.Dimension(1005, 620));

        PaneldentroGastos.setBackground(new java.awt.Color(255, 255, 255));
        PaneldentroGastos.setPreferredSize(new java.awt.Dimension(1005, 660));
        PaneldentroGastos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaGastos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaGastos);

        PaneldentroGastos.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 182, 961, 410));

        BtnRegistrarGasto.setText("Registrar gasto");
        BtnRegistrarGasto.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnRegistrarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrarGastoActionPerformed(evt);
            }
        });
        PaneldentroGastos.add(BtnRegistrarGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 180, 40));

        BtnReloadGastos.setText("Reload");
        BtnReloadGastos.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnReloadGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReloadGastosActionPerformed(evt);
            }
        });
        PaneldentroGastos.add(BtnReloadGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 90, 40));

        BtnBuscarGastos.setText("Buscar");
        BtnBuscarGastos.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnBuscarGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarGastosActionPerformed(evt);
            }
        });
        PaneldentroGastos.add(BtnBuscarGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, 80, 40));

        ComboFechaGastos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Mes", "Año", "Full Date" }));
        PaneldentroGastos.add(ComboFechaGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 150, -1, -1));

        TxtFechaGastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtFechaGastosKeyTyped(evt);
            }
        });
        PaneldentroGastos.add(TxtFechaGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 150, 130, -1));

        LblNotFecha1.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        LblNotFecha1.setForeground(new java.awt.Color(51, 51, 51));
        LblNotFecha1.setText("YYYY - MM - DD");
        PaneldentroGastos.add(LblNotFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 140, 120, 10));

        LblUtilidad2.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad2.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblUtilidad2.setForeground(new java.awt.Color(0, 112, 192));
        LblUtilidad2.setText("Gastos");
        PaneldentroGastos.add(LblUtilidad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, -1));

        javax.swing.GroupLayout PanelGastosLayout = new javax.swing.GroupLayout(PanelGastos);
        PanelGastos.setLayout(PanelGastosLayout);
        PanelGastosLayout.setHorizontalGroup(
            PanelGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaneldentroGastos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelGastosLayout.setVerticalGroup(
            PanelGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaneldentroGastos, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Gastos", PanelGastos);

        PanelDentrongresos.setBackground(new java.awt.Color(255, 255, 255));
        PanelDentrongresos.setPreferredSize(new java.awt.Dimension(1005, 620));
        PanelDentrongresos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaIngresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Monto", "Descripcion", "Fecha"
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
        TablaIngresos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(TablaIngresos);
        if (TablaIngresos.getColumnModel().getColumnCount() > 0) {
            TablaIngresos.getColumnModel().getColumn(0).setResizable(false);
            TablaIngresos.getColumnModel().getColumn(0).setPreferredWidth(10);
            TablaIngresos.getColumnModel().getColumn(1).setResizable(false);
            TablaIngresos.getColumnModel().getColumn(2).setResizable(false);
            TablaIngresos.getColumnModel().getColumn(3).setResizable(false);
        }

        PanelDentrongresos.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 182, 961, 410));

        BtnUtilidad.setText("Registrar Ingreso");
        BtnUtilidad.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnUtilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUtilidadActionPerformed(evt);
            }
        });
        PanelDentrongresos.add(BtnUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 180, 40));

        BtnReloadIngresos.setText("Reload");
        BtnReloadIngresos.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnReloadIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReloadIngresosActionPerformed(evt);
            }
        });
        PanelDentrongresos.add(BtnReloadIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 90, 40));

        BtnBuscarIngresos.setText("Buscar");
        BtnBuscarIngresos.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnBuscarIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarIngresosActionPerformed(evt);
            }
        });
        PanelDentrongresos.add(BtnBuscarIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, 80, 40));

        ComboFechaIngresos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Mes", "Año", "Full Date" }));
        PanelDentrongresos.add(ComboFechaIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 150, -1, -1));

        TxtFechaIngresos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtFechaIngresosKeyTyped(evt);
            }
        });
        PanelDentrongresos.add(TxtFechaIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 150, 130, -1));

        LblNotFecha2.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        LblNotFecha2.setForeground(new java.awt.Color(51, 51, 51));
        LblNotFecha2.setText("YYYY - MM - DD");
        PanelDentrongresos.add(LblNotFecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 140, 120, 10));

        LblUtilidad1.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad1.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblUtilidad1.setForeground(new java.awt.Color(0, 112, 192));
        LblUtilidad1.setText("Ingresos");
        PanelDentrongresos.add(LblUtilidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        javax.swing.GroupLayout PanelIngresosLayout = new javax.swing.GroupLayout(PanelIngresos);
        PanelIngresos.setLayout(PanelIngresosLayout);
        PanelIngresosLayout.setHorizontalGroup(
            PanelIngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelDentrongresos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelIngresosLayout.setVerticalGroup(
            PanelIngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelIngresosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(PanelDentrongresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        materialTabbed1.addTab("Ingresos", PanelIngresos);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane3.setViewportView(TablaUtilidadGastos);
        if (TablaUtilidadGastos.getColumnModel().getColumnCount() > 0) {
            TablaUtilidadGastos.getColumnModel().getColumn(0).setResizable(false);
            TablaUtilidadGastos.getColumnModel().getColumn(0).setPreferredWidth(150);
            TablaUtilidadGastos.getColumnModel().getColumn(1).setResizable(false);
            TablaUtilidadGastos.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 400, 416));

        LblUtilidad.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblUtilidad.setForeground(new java.awt.Color(0, 112, 192));
        LblUtilidad.setText("Utilidad");
        jPanel1.add(LblUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 192, 75));

        LblTotalIngresos.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        LblTotalIngresos.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(LblTotalIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 550, 100, 40));

        LblTotalGastos.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblTotalGastos.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(LblTotalGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 540, 114, 28));

        LblTotalUtilidad.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblTotalUtilidad.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(LblTotalUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 540, 110, 28));

        BtnReloadUtlidad.setText("Reload");
        BtnReloadUtlidad.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnReloadUtlidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReloadUtlidadActionPerformed(evt);
            }
        });
        jPanel1.add(BtnReloadUtlidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 90, 40));

        TotalUtilidad.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        TotalUtilidad.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(TotalUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 560, 130, 40));

        TotalIngresos.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        TotalIngresos.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(TotalIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 100, 25));

        TotalGastos.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        TotalGastos.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(TotalGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 500, 100, 24));

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
        jScrollPane5.setViewportView(TablaUtilidadIngresos);
        if (TablaUtilidadIngresos.getColumnModel().getColumnCount() > 0) {
            TablaUtilidadIngresos.getColumnModel().getColumn(0).setResizable(false);
            TablaUtilidadIngresos.getColumnModel().getColumn(0).setPreferredWidth(150);
            TablaUtilidadIngresos.getColumnModel().getColumn(1).setResizable(false);
            TablaUtilidadIngresos.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 400, 416));

        TotalUtilidadset.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        TotalUtilidadset.setForeground(new java.awt.Color(51, 51, 51));
        TotalUtilidadset.setText("Total Utilidad:");
        jPanel1.add(TotalUtilidadset, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, -1, 30));

        TotalIngresos1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        TotalIngresos1.setForeground(new java.awt.Color(51, 51, 51));
        TotalIngresos1.setText("Total ingresos:");
        jPanel1.add(TotalIngresos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 500, -1, 25));

        TotalGastos1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        TotalGastos1.setForeground(new java.awt.Color(51, 51, 51));
        TotalGastos1.setText("Total Gastos:");
        jPanel1.add(TotalGastos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 500, -1, 24));

        materialTabbed1.addTab("Utilidad", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblUtilidad5.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad5.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        LblUtilidad5.setForeground(new java.awt.Color(0, 112, 192));
        LblUtilidad5.setText("Pagos");
        jPanel2.add(LblUtilidad5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, -1, -1));

        BtnReloadCC.setText("Reload");
        BtnReloadCC.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnReloadCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReloadCCActionPerformed(evt);
            }
        });
        jPanel2.add(BtnReloadCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 90, 40));

        BtnBuscarCC.setText("Buscar");
        BtnBuscarCC.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnBuscarCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarCCActionPerformed(evt);
            }
        });
        jPanel2.add(BtnBuscarCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 80, 40));

        ComboFechaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Mes", "Año", "Full Date" }));
        jPanel2.add(ComboFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, -1, -1));

        TxtFechaCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtFechaCCKeyTyped(evt);
            }
        });
        jPanel2.add(TxtFechaCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, 130, -1));

        LblNotFecha4.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        LblNotFecha4.setForeground(new java.awt.Color(51, 51, 51));
        LblNotFecha4.setText("YYYY - MM - DD");
        jPanel2.add(LblNotFecha4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 120, 10));

        TablaPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "IdVenta", "Cliente", "Monto Pago", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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
        TablaPago.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(TablaPago);
        if (TablaPago.getColumnModel().getColumnCount() > 0) {
            TablaPago.getColumnModel().getColumn(0).setResizable(false);
            TablaPago.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaPago.getColumnModel().getColumn(1).setResizable(false);
            TablaPago.getColumnModel().getColumn(1).setPreferredWidth(150);
            TablaPago.getColumnModel().getColumn(2).setResizable(false);
            TablaPago.getColumnModel().getColumn(2).setPreferredWidth(25);
            TablaPago.getColumnModel().getColumn(3).setResizable(false);
            TablaPago.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 934, 411));
        jPanel2.add(IconReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 50, 30));

        materialTabbed1.addTab("Pagos", jPanel2);

        add(materialTabbed1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 660));

        PanelBarBlue.setBackground(new java.awt.Color(24, 39, 72));

        javax.swing.GroupLayout PanelBarBlueLayout = new javax.swing.GroupLayout(PanelBarBlue);
        PanelBarBlue.setLayout(PanelBarBlueLayout);
        PanelBarBlueLayout.setHorizontalGroup(
            PanelBarBlueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1010, Short.MAX_VALUE)
        );
        PanelBarBlueLayout.setVerticalGroup(
            PanelBarBlueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(PanelBarBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, -1));
    }// </editor-fold>//GEN-END:initComponents

   
    private void BtnRegistrarGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarGastoActionPerformed
        RegistrarGastos Rg = new RegistrarGastos(fr, true);
        Rg.setLocationRelativeTo(this);
        Rg.setVisible(true);
        initialize();
    }//GEN-LAST:event_BtnRegistrarGastoActionPerformed

    private void BtnRegistrarPagoCPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarPagoCPagarActionPerformed
        registrarPagoCuentaPagar();
        initialize();
        revisarCuentasSaldadasCuentaPagar();

    }//GEN-LAST:event_BtnRegistrarPagoCPagarActionPerformed

    private void BtnRegistrarPagoCCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarPagoCCobrarActionPerformed
        registrarPagoCuentaCobrar();
        initialize();
        revisarCuentasSaldadas();
    }//GEN-LAST:event_BtnRegistrarPagoCCobrarActionPerformed

    private void BtnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReloadActionPerformed

        initialize();
        TxtFecha.setText("");
    }//GEN-LAST:event_BtnReloadActionPerformed

    private void TxtFechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFechaKeyTyped
        char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
           buscarPorFechaCuentaCobrar();
            
        }
    }//GEN-LAST:event_TxtFechaKeyTyped

    private void BtnBuscarCCobrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarCCobrar1ActionPerformed
         buscarPorFechaCuentaCobrar();
    }//GEN-LAST:event_BtnBuscarCCobrar1ActionPerformed

    private void BtnUtilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUtilidadActionPerformed
        RegistrarIngreso RI = new RegistrarIngreso();
        RI.setLocationRelativeTo(this);
        RI.setVisible(true);
        initialize();
    }//GEN-LAST:event_BtnUtilidadActionPerformed

    private void BtnReloadGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReloadGastosActionPerformed
       initialize();
        //obtenerGastos();
        TxtFechaGastos.setText("");
    }//GEN-LAST:event_BtnReloadGastosActionPerformed

    private void BtnBuscarGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarGastosActionPerformed
        buscarGastosPorFecha();
    }//GEN-LAST:event_BtnBuscarGastosActionPerformed

    private void TxtFechaGastosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFechaGastosKeyTyped
        char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
           buscarGastosPorFecha();  
        }
    }//GEN-LAST:event_TxtFechaGastosKeyTyped

    private void BtnReloadIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReloadIngresosActionPerformed
       initialize();
        //obtenerIngresos();
        TxtFechaIngresos.setText("");
    }//GEN-LAST:event_BtnReloadIngresosActionPerformed

    private void BtnBuscarIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarIngresosActionPerformed
        buscarIngresosPorFecha();
    }//GEN-LAST:event_BtnBuscarIngresosActionPerformed

    private void TxtFechaIngresosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFechaIngresosKeyTyped
         char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
          buscarIngresosPorFecha();
            
        }
    }//GEN-LAST:event_TxtFechaIngresosKeyTyped

    private void BtnReloadPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReloadPagarActionPerformed
        DatosCuentaPagar();
        revisarCuentasSaldadas();
        TxtFechaPagar.setText("");
    }//GEN-LAST:event_BtnReloadPagarActionPerformed

    private void BtnBuscarPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarPagarActionPerformed
        buscarPorFechaCuentaPagar();
    }//GEN-LAST:event_BtnBuscarPagarActionPerformed

    private void TxtFechaPagarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFechaPagarKeyTyped
         char teclapress =evt.getKeyChar();
        
        if(teclapress == KeyEvent.VK_ENTER){
             buscarPorFechaCuentaPagar();
            
        }
    }//GEN-LAST:event_TxtFechaPagarKeyTyped

    private void BtnReloadUtlidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReloadUtlidadActionPerformed
         initialize();
    }//GEN-LAST:event_BtnReloadUtlidadActionPerformed

    private void BtnReloadCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReloadCCActionPerformed
        initialize();
        TxtFechaCC.setText("");
    }//GEN-LAST:event_BtnReloadCCActionPerformed

    private void BtnBuscarCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarCCActionPerformed
         buscarPorFechaPago();
    }//GEN-LAST:event_BtnBuscarCCActionPerformed

    private void TxtFechaCCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFechaCCKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtFechaCCKeyTyped

    
    
    
    //Metodos Iniciadores
    
    private void initialize() {
        DatosCuentaPagar();
        DatosCuentaCobrar();
        DatosPagos();
        llenarTablaIngresos();
        llenarTablaGastos();
        calcularUtilidad();
        obtenerGastos();
        obtenerIngresos();
    }
    
    
    //Cuentas por Cobrar
    
    public void DatosCuentaCobrar() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        String query = "SELECT c.idVenta AS IdVenta, v.nombreCliente AS ClienteDeudor, c.fecha as Fecha, c.deuda as Deuda, c.totalFaltante as 'Total Faltante', c.descripcion as Descripcion "
             + "FROM cuentaporcobrar AS c "
             + "INNER JOIN venta AS v ON v.id = c.idVenta";


        
        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TableCobrar.setModel(modelo);

        TableCobrar.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < TableCobrar.getColumnCount(); i++) {
            TableCobrar.getColumnModel().getColumn(i).setResizable(false);
        }
            } catch (SQLException ex) {
                Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void buscarPorFechaCuentaCobrar() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        String seleccion = (String) ComboFechaCobrar.getSelectedItem(); 
        String query = "";

        switch (seleccion) {
            case "Dia" -> {
                String dia = TxtFecha.getText();
                query = "SELECT v.id AS ventaId, cl.nombre AS Cliente, c.fecha, c.deuda, c.totalFaltante, c.descripcion"
                        + " FROM cuentaporcobrar AS c"
                        + " INNER JOIN Cliente AS cl ON cl.id = c.idCliente"
                        + " INNER JOIN Venta AS v ON v.id = c.idVenta"
                        + " WHERE DAY(c.fecha) = '" + dia + "'";
            }
            case "Mes" -> {
                String Mes = TxtFecha.getText();
                query = "SELECT v.id AS ventaId, cl.nombre AS Cliente, c.fecha, c.deuda, c.totalFaltante, c.descripcion"
                        + " FROM cuentaporcobrar AS c"
                        + " INNER JOIN Cliente AS cl ON cl.id = c.idCliente"
                        + " INNER JOIN Venta AS v ON v.id = c.idVenta"
                        + " WHERE MONTH(c.fecha) = '" + Mes + "'";
            }
            case "Año" -> {
                String Año = TxtFecha.getText();
                query = "SELECT v.id AS ventaId, cl.nombre AS Cliente, c.fecha, c.deuda, c.totalFaltante, c.descripcion"
                        + " FROM cuentaporcobrar AS c"
                        + " INNER JOIN Cliente AS cl ON cl.id = c.idCliente"
                        + " INNER JOIN Venta AS v ON v.id = c.idVenta"
                        + " WHERE YEAR(c.fecha) = '" + Año + "'";
            }
            case "Full Date" -> {
                String fechaCompleta = TxtFecha.getText();
                query = "SELECT v.id AS ventaId, cl.nombre AS Cliente, c.fecha, c.deuda, c.totalFaltante, c.descripcion "
                        + "FROM cuentaporcobrar AS c "
                        + "INNER JOIN Cliente AS cl ON cl.id = c.idCliente "
                        + "INNER JOIN Venta AS v ON v.id = c.idVenta "
                        + "WHERE c.fecha = '" + fechaCompleta + "'";
            }
            default -> {System.out.println("No valido");
            }
        }

        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TableCobrar.setModel(modelo);
    } catch (SQLException ex) {
        Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void registrarPagoCuentaCobrar() {
    int selectedRow = TableCobrar.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para registrar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    String IdVenta = TableCobrar.getValueAt(selectedRow, 0).toString();
    String clienteDeudor = TableCobrar.getValueAt(selectedRow, 1).toString();
    String deuda = TableCobrar.getValueAt(selectedRow, 3).toString();
    String totalFaltante = TableCobrar.getValueAt(selectedRow, 4).toString();
    
    // Crear un nuevo diálogo de RegistroPagoCobrar
    RegistrarPagoCCobrar dialog = new RegistrarPagoCCobrar(fr, true);
    
    //  los campos en el diálogo con los datos recopilados
    dialog.setDatosRegistro(IdVenta, clienteDeudor, deuda, totalFaltante);

    dialog.setVisible(true);
}

    private void eliminarCuentaPorCobrar(int idVenta) {
    try {
        String query = "DELETE FROM cuentaporcobrar WHERE idVenta = ?";
        try (Connection connection = Conexion.conectar();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idVenta);
            statement.executeUpdate();
        }
    } catch (SQLException ex) {
        Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
    }
 }

    public void revisarCuentasSaldadas() {
        
       try {
           DefaultTableModel modelo = (DefaultTableModel) TableCobrar.getModel();

           for (int row = modelo.getRowCount() - 1; row >= 0; row--) {
               double totalFaltante = Double.parseDouble(modelo.getValueAt(row, 4).toString());
               if (totalFaltante == 0.00) {
                   int idCuenta = Integer.parseInt(modelo.getValueAt(row, 0).toString());
                   String nombreCliente = modelo.getValueAt(row, 1).toString();

                   // Mostrar mensaje de cuenta saldada
                   JOptionPane.showMessageDialog(this, "Cuenta del cliente " + nombreCliente + " fue saldada.", "Cuenta Saldada", JOptionPane.INFORMATION_MESSAGE);

                   // Eliminar la fila de la tabla
                   modelo.removeRow(row);

                   // Eliminar la cuenta de la base de datos
                   eliminarCuentaPorCobrar(idCuenta);
               }
           }
       } catch (Exception ex) {
           Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
       }
   }

    
    //Cuentas por Pagar
    
    public void DatosCuentaPagar() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        String query = "SELECT cp.idCompra AS IdCompra, cp.idProveedor AS IdAcreedor, pr.nombre AS DescripcionAcreedor,\n" +
                        "cp.deuda AS Deuda, cp.totalFaltante AS TotalFaltante, cp.fecha AS Fecha\n" +
                        "FROM cuentaporpagar cp\n" +
                        "JOIN proveedor pr ON cp.idProveedor = pr.id;";

        
        // Ejecutar la consulta y llenar el modelo
        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TablePagar.setModel(modelo);
    } catch (SQLException ex) {
        Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    private void buscarPorFechaCuentaPagar() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        String seleccion = (String) ComboFechaPagar.getSelectedItem(); // Obtener la opción seleccionada del combo box

        String query = ""; // Inicializar la consulta

        if (seleccion.equals("Dia")) {
            String dia = TxtFechaPagar.getText();
            query = "SELECT cp.id, cp.idProveedor AS idAcreedor, pr.nombre AS DescripcionAcreedor,"
                    + " cp.fecha, cp.deuda AS 'Deuda', pc.monto AS Pago,"
                    + " pc.fecha AS 'Fecha Pago', cp.totalFaltante"
                    + " FROM cuentaporpagar cp"
                    + " JOIN proveedor pr ON cp.idProveedor = pr.id"
                    + " LEFT JOIN pagocp pc ON cp.id = pc.idCompra AND pc.monto BETWEEN cp.deuda AND cp.totalFaltante"
                    + " WHERE DAY(cp.fecha) = '" + dia + "'";
        } else if (seleccion.equals("Mes")) {
            String mes = TxtFechaPagar.getText();
            query = "SELECT cp.id, cp.idProveedor AS idAcreedor, pr.nombre AS DescripcionAcreedor,"
                    + " cp.fecha, cp.deuda AS 'Deuda', pc.monto AS Pago,"
                    + " pc.fecha AS 'Fecha Pago', cp.totalFaltante"
                    + " FROM cuentaporpagar cp"
                    + " JOIN proveedor pr ON cp.idProveedor = pr.id"
                    + " LEFT JOIN pagocp pc ON cp.id = pc.idCompra AND pc.monto BETWEEN cp.deuda AND cp.totalFaltante"
                    + " WHERE MONTH(cp.fecha) = '" + mes + "'";
        } else if (seleccion.equals("Año")) {
            String ano = TxtFechaPagar.getText();
            query = "SELECT cp.id, cp.idProveedor AS idAcreedor, pr.nombre AS DescripcionAcreedor,"
                    + " cp.fecha, cp.deuda AS 'Deuda', pc.monto AS Pago,"
                    + " pc.fecha AS 'Fecha Pago', cp.totalFaltante"
                    + " FROM cuentaporpagar cp"
                    + " JOIN proveedor pr ON cp.idProveedor = pr.id"
                    + " LEFT JOIN pagocp pc ON cp.id = pc.idCompra AND pc.monto BETWEEN cp.deuda AND cp.totalFaltante"
                    + " WHERE YEAR(cp.fecha) = '" + ano + "'";
        } else if (seleccion.equals("Full Date")) {
            String fechaCompleta = TxtFechaPagar.getText();
            query = "SELECT cp.id, cp.idProveedor AS idAcreedor, pr.nombre AS DescripcionAcreedor,"
                    + " cp.fecha, cp.deuda AS 'Deuda', pc.monto AS Pago,"
                    + " pc.fecha AS 'Fecha Pago', cp.totalFaltante"
                    + " FROM cuentaporpagar cp"
                    + " JOIN proveedor pr ON cp.idProveedor = pr.id"
                    + " LEFT JOIN pagocp pc ON cp.id = pc.idCompra AND pc.monto BETWEEN cp.deuda AND cp.totalFaltante"
                    + " WHERE cp.fecha = '" + fechaCompleta + "'";
        }

        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TablePagar.setModel(modelo);
    } catch (SQLException ex) {
        Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public void revisarCuentasSaldadasCuentaPagar() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) TablePagar.getModel();

            for (int row = modelo.getRowCount() - 1; row >= 0; row--) {
                double totalFaltante = Double.parseDouble(modelo.getValueAt(row, 4).toString());
                if (totalFaltante == 0.00) {
                   // int idCompra = Integer.parseInt(modelo.getValueAt(row, 0).toString());
                     String idCompra = (modelo.getValueAt(row, 0).toString());
                    String nombreProveedor = modelo.getValueAt(row, 2).toString();

                    // Mostrar mensaje de cuenta saldada
                    JOptionPane.showMessageDialog(this, "Cuenta del proveedor " + nombreProveedor + " fue saldada.", "Cuenta Saldada", JOptionPane.INFORMATION_MESSAGE);

                    // Eliminar la fila de la tabla
                    modelo.removeRow(row);

                    // Eliminar la cuenta de la base de datos
                    eliminarCuentaPorPagar(idCompra);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarCuentaPorPagar(String idCompra) {
        try {
            String query = "DELETE FROM cuentaporpagar WHERE idCompra = ?";
            try (Connection connection = Conexion.conectar();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, idCompra);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registrarPagoCuentaPagar() {
        int selectedRow = TablePagar.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para registrar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idCompra = TablePagar.getValueAt(selectedRow, 0).toString();
        String idAcreedor = TablePagar.getValueAt(selectedRow, 1).toString();
        String descripcionAcreedor = TablePagar.getValueAt(selectedRow, 2).toString();
        String deuda = TablePagar.getValueAt(selectedRow, 3).toString();
        String totalFaltante = TablePagar.getValueAt(selectedRow, 4).toString();

        // Crear un nuevo diálogo de RegistroPagoCuentaPagar
        RegistrarPagoCPagar dialog = new RegistrarPagoCPagar(fr, true);

        // Establecer los campos en el diálogo con los datos recopilados
        dialog.setDatosRegistroPago(idCompra, idAcreedor, descripcionAcreedor, deuda, totalFaltante);

        dialog.setVisible(true);
    }



    //Pagos
    
    private void DatosPagos() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

         String query = "SELECT pc.idVenta as IDVenta, v.nombreCliente AS Cliente, pc.monto AS 'Monto Pago', pc.fecha AS 'Fecha Pago' "
                     + "FROM pagocc AS pc "
                     + "INNER JOIN cuentaporcobrar AS c ON pc.idVenta = c.idVenta "
                     + "INNER JOIN venta AS v ON c.idVenta = v.id";


        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TablaPago.setModel(modelo);

        // Hacer que la tabla no sea redimensionable
        TablaPago.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < TablaPago.getColumnCount(); i++) {
            TablaPago.getColumnModel().getColumn(i).setResizable(false);
        }
    } catch (SQLException ex) {
        Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    private void buscarPorFechaPago() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        String seleccion = (String) ComboFechaPago.getSelectedItem();
        String query = "";

        if (seleccion.equals("Dia")) {
            String dia = TxtFechaCC.getText();
            query = "SELECT pc.idVenta, c.idCliente, cl.nombre AS Cliente, pc.monto AS MontoPago, pc.fecha AS FechaPago"
                    + " FROM pagocc AS pc"
                    + " INNER JOIN cuentaporcobrar AS c ON pc.idVenta = c.idVenta"
                    + " INNER JOIN Cliente AS cl ON c.idCliente = cl.id"
                    + " WHERE DAY(pc.fecha) = '" + dia + "'";
        } else if (seleccion.equals("Mes")) {
            String Mes = TxtFechaCC.getText();
            query = "SELECT pc.idVenta, c.idCliente, cl.nombre AS Cliente, pc.monto AS MontoPago, pc.fecha AS FechaPago"
                    + " FROM pagocc AS pc"
                    + " INNER JOIN cuentaporcobrar AS c ON pc.idVenta = c.idVenta"
                    + " INNER JOIN Cliente AS cl ON c.idCliente = cl.id"
                    + " WHERE MONTH(pc.fecha) = '" + Mes + "'";
        } else if (seleccion.equals("Año")) {
            String Año = TxtFechaCC.getText();
            query = "SELECT pc.idVenta, c.idCliente, cl.nombre AS Cliente, pc.monto AS MontoPago, pc.fecha AS FechaPago"
                    + " FROM pagocc AS pc"
                    + " INNER JOIN cuentaporcobrar AS c ON pc.idVenta = c.idVenta"
                    + " INNER JOIN Cliente AS cl ON c.idCliente = cl.id"
                    + " WHERE YEAR(pc.fecha) = '" + Año + "'";
        } else if (seleccion.equals("Full Date")) {
            String fechaCompleta = TxtFechaCC.getText();
            query = "SELECT pc.idVenta, c.idCliente, cl.nombre AS Cliente, pc.monto AS MontoPago, pc.fecha AS FechaPago"
                    + " FROM pagocc AS pc"
                    + " INNER JOIN cuentaporcobrar AS c ON pc.idVenta = c.idVenta"
                    + " INNER JOIN Cliente AS cl ON c.idCliente = cl.id"
                    + " WHERE pc.fecha = '" + fechaCompleta + "'";
        }

        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TablaPago.setModel(modelo); // Cambiar el nombre de la tabla al que corresponda
    } catch (SQLException ex) {
        Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
    }
}

   
  
    //Gastos
    
    private void buscarGastosPorFecha() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        String seleccion = (String) ComboFechaCobrar.getSelectedItem(); 

        String query = ""; 

        if (seleccion.equals("Dia")) {
            String dia = TxtFechaGastos.getText();
            query = "SELECT id, concepto, monto, fecha FROM Gastos WHERE DAY(fecha) = '" + dia + "'";

        } else if (seleccion.equals("Mes")) {
            String mes = TxtFechaGastos.getText();
            query = "SELECT id, concepto, monto, fecha FROM Gastos WHERE MONTH(fecha) = '" + mes + "'";

        } else if (seleccion.equals("Año")) {
            String ano = TxtFechaGastos.getText();
            query = "SELECT id, concepto, monto, fecha FROM Gastos WHERE YEAR(fecha) = '" + ano + "'";

        } else if (seleccion.equals("Full Date")) {
            String fechaCompleta = TxtFechaGastos.getText();
            query = "SELECT id, concepto, monto, fecha FROM Gastos WHERE fecha = '" + fechaCompleta + "'";
        }

        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TablaGastos.setModel(modelo);

    } catch (SQLException ex) {
        Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
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

        try (Connection connection = Conexion.conectar();
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

            TablaUtilidadGastos.setModel(modeloGastos);

            double totalGastos = 0;

            for (int row = 0; row < modeloGastos.getRowCount(); row++) {
                totalGastos += Double.parseDouble(modeloGastos.getValueAt(row, 1).toString());
            }

            TotalGastos.setForeground(Color.red);
            TotalGastos.setText("$" + totalGastos);

        } catch (SQLException ex) {
            Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerGastos() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        // La consulta SQL para obtener los gastos
        String query = "SELECT id, concepto, monto, fecha FROM Gastos";

        // Ejecutar la consulta y llenar el modelo
        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TablaGastos.setModel(modelo);

    } catch (SQLException ex) {
        Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    //Utlidad
    
    private void calcularUtilidad() {
        String totalIngresosText = TotalIngresos.getText();
        double totalIngresos = Double.parseDouble(totalIngresosText.substring(1));

        String totalGastosText = TotalGastos.getText();
        double totalGastos = Double.parseDouble(totalGastosText.substring(1));

        double totalUtilidad = totalIngresos - totalGastos;

        TotalUtilidad.setForeground(Color.BLUE);
        TotalUtilidad.setText("$" + totalUtilidad);
    }

    
    //Ingresos
    
    private void buscarIngresosPorFecha() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        String seleccion = (String) ComboFechaCobrar.getSelectedItem();

        String query = "";

        if (seleccion.equals("Dia")) {
            String dia = TxtFechaIngresos.getText();
            query = "SELECT id, concepto, monto, fecha FROM Ingresos WHERE DAY(fecha) = '" + dia + "'";

        } else if (seleccion.equals("Mes")) {
            String mes = TxtFechaIngresos.getText();
            query = "SELECT id, concepto, monto, fecha FROM Ingresos WHERE MONTH(fecha) = '" + mes + "'";

        } else if (seleccion.equals("Año")) {
            String ano = TxtFechaIngresos.getText();
            query = "SELECT id, concepto, monto, fecha FROM Ingresos WHERE YEAR(fecha) = '" + ano + "'";

        } else if (seleccion.equals("Full Date")) {
            String fechaCompleta = TxtFechaIngresos.getText();
            query = "SELECT id, concepto, monto, fecha FROM Ingresos WHERE fecha = '" + fechaCompleta + "'";
        }

        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TablaIngresos.setModel(modelo);

    } catch (SQLException ex) {
        Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void obtenerIngresos() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar que las celdas sean editables
            }
        };

        // La consulta SQL para obtener los ingresos
        String query = "SELECT id, concepto as Categoria, monto, fecha FROM Ingresos";

        // Ejecutar la consulta y llenar el modelo
        try (Connection connection = Conexion.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo
            for (int column = 1; column <= columnCount; column++) {
                modelo.addColumn(metaData.getColumnLabel(column));
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                modelo.addRow(rowData);
            }
        }

        TablaIngresos.setModel(modelo);

    } catch (SQLException ex) {
        Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void llenarTablaIngresos() {
    DefaultTableModel modeloIngresos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Evitar que las celdas sean editables
        }
    };

    String queryIngresos = "SELECT concepto, SUM(monto) AS 'Total Ingresos' FROM Ingresos GROUP BY concepto";

    try (Connection connection = Conexion.conectar();
         Statement statement = connection.createStatement()) {

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

    } catch (SQLException ex) {
        Logger.getLogger(TabContabilidad.class.getName()).log(Level.SEVERE, null, ex);
    }
}

  
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle BtnBuscarCC;
    private rojerusan.RSMaterialButtonRectangle BtnBuscarCCobrar1;
    private rojerusan.RSMaterialButtonRectangle BtnBuscarGastos;
    private rojerusan.RSMaterialButtonRectangle BtnBuscarIngresos;
    private rojerusan.RSMaterialButtonRectangle BtnBuscarPagar;
    private rojerusan.RSMaterialButtonRectangle BtnRegistrarGasto;
    private rojerusan.RSMaterialButtonRectangle BtnRegistrarPagoCCobrar;
    private rojerusan.RSMaterialButtonRectangle BtnRegistrarPagoCPagar;
    private rojerusan.RSMaterialButtonRectangle BtnReload;
    private rojerusan.RSMaterialButtonRectangle BtnReloadCC;
    private rojerusan.RSMaterialButtonRectangle BtnReloadGastos;
    private rojerusan.RSMaterialButtonRectangle BtnReloadIngresos;
    private rojerusan.RSMaterialButtonRectangle BtnReloadPagar;
    private rojerusan.RSMaterialButtonRectangle BtnReloadUtlidad;
    private rojerusan.RSMaterialButtonRectangle BtnUtilidad;
    private javax.swing.JComboBox<String> ComboFechaCobrar;
    private javax.swing.JComboBox<String> ComboFechaGastos;
    private javax.swing.JComboBox<String> ComboFechaIngresos;
    private javax.swing.JComboBox<String> ComboFechaPagar;
    private javax.swing.JComboBox<String> ComboFechaPago;
    private javax.swing.JLabel IconReload;
    private javax.swing.JLabel LblNotFecha;
    private javax.swing.JLabel LblNotFecha1;
    private javax.swing.JLabel LblNotFecha2;
    private javax.swing.JLabel LblNotFecha3;
    private javax.swing.JLabel LblNotFecha4;
    private javax.swing.JLabel LblTotalGastos;
    private javax.swing.JLabel LblTotalIngresos;
    private javax.swing.JLabel LblTotalUtilidad;
    private javax.swing.JLabel LblUtilidad;
    private javax.swing.JLabel LblUtilidad1;
    private javax.swing.JLabel LblUtilidad2;
    private javax.swing.JLabel LblUtilidad3;
    private javax.swing.JLabel LblUtilidad4;
    private javax.swing.JLabel LblUtilidad5;
    private javax.swing.JPanel PanelBarBlue;
    private javax.swing.JPanel PanelCobrar;
    private javax.swing.JPanel PanelDentrongresos;
    private javax.swing.JPanel PanelGastos;
    private javax.swing.JPanel PanelIngresos;
    private javax.swing.JPanel PanelPagar;
    private javax.swing.JPanel PaneldentroGastos;
    private javax.swing.JScrollPane ScrollCobrar;
    private javax.swing.JScrollPane ScrollPagar;
    private javax.swing.JTable TablaGastos;
    private javax.swing.JTable TablaIngresos;
    private javax.swing.JTable TablaPago;
    private javax.swing.JTable TablaUtilidadGastos;
    private javax.swing.JTable TablaUtilidadIngresos;
    private javax.swing.JTable TableCobrar;
    private javax.swing.JTable TablePagar;
    private javax.swing.JLabel TotalGastos;
    private javax.swing.JLabel TotalGastos1;
    private javax.swing.JLabel TotalIngresos;
    private javax.swing.JLabel TotalIngresos1;
    private javax.swing.JLabel TotalUtilidad;
    private javax.swing.JLabel TotalUtilidadset;
    private javax.swing.JTextField TxtFecha;
    private javax.swing.JTextField TxtFechaCC;
    private javax.swing.JTextField TxtFechaGastos;
    private javax.swing.JTextField TxtFechaIngresos;
    private javax.swing.JTextField TxtFechaPagar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private Utils.MaterialTabbed materialTabbed1;
    // End of variables declaration//GEN-END:variables
}
