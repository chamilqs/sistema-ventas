
package Historial;

import Connect.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author baez_
 */
public class PanelHistorial extends javax.swing.JPanel {

    public PanelHistorial() {
        initComponents();
        initialize();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabHistorial = new Utils.MaterialTabbed();
        Usuarios = new javax.swing.JPanel();
        BtnReload = new rojerusan.RSMaterialButtonRectangle();
        BtnBuscarCCobrar1 = new rojerusan.RSMaterialButtonRectangle();
        cmbBuscador = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        LblUtilidad5 = new javax.swing.JLabel();
        Compras = new javax.swing.JPanel();
        ScrollCompra = new javax.swing.JScrollPane();
        TablaCompraHistorial = new javax.swing.JTable();
        BtnReloadCompra = new rojerusan.RSMaterialButtonRectangle();
        BtnBuscarCompra = new rojerusan.RSMaterialButtonRectangle();
        cmbBuscadorCompra = new javax.swing.JComboBox<>();
        txtBuscadorCompra = new javax.swing.JTextField();
        LblCompra = new javax.swing.JLabel();
        Caja = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnReloadCaja = new rojerusan.RSMaterialButtonRectangle();
        btnBuscarCaja = new rojerusan.RSMaterialButtonRectangle();
        cmbCaja = new javax.swing.JComboBox<>();
        txtBuscadorCaja = new javax.swing.JTextField();
        lblCaja = new javax.swing.JLabel();
        Utilidad = new javax.swing.JPanel();
        lblUtilidad = new javax.swing.JLabel();
        scrUtilidad = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnReloadUtilidad = new rojerusan.RSMaterialButtonRectangle();
        btnBuscarUtilidad = new rojerusan.RSMaterialButtonRectangle();
        cmbBuscadorUtilidad = new javax.swing.JComboBox<>();
        txtBuscadorUtilidad = new javax.swing.JTextField();
        Ventas = new javax.swing.JPanel();
        lblVentas = new javax.swing.JLabel();
        srcVentas = new javax.swing.JScrollPane();
        TablaVentaHistorial = new javax.swing.JTable();
        btnReloadVentas = new rojerusan.RSMaterialButtonRectangle();
        btnBuscadorVentas = new rojerusan.RSMaterialButtonRectangle();
        cmbVentas = new javax.swing.JComboBox<>();
        txtBuscadorVentas = new javax.swing.JTextField();
        Entradas = new javax.swing.JPanel();
        lblEntradas = new javax.swing.JLabel();
        scrEntradas = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        btnReloadEntradas = new rojerusan.RSMaterialButtonRectangle();
        btnBuscadorEntradas = new rojerusan.RSMaterialButtonRectangle();
        cmbEntradas = new javax.swing.JComboBox<>();
        txtBuscadorEntradas = new javax.swing.JTextField();
        Salidas = new javax.swing.JPanel();
        scrSalidas = new javax.swing.JScrollPane();
        TablaPagadosHistorial = new javax.swing.JTable();
        txtBuscadorSalidas = new javax.swing.JTextField();
        cmbSalidas = new javax.swing.JComboBox<>();
        btnBuscarSalidas = new rojerusan.RSMaterialButtonRectangle();
        btnReloadSalidas = new rojerusan.RSMaterialButtonRectangle();
        lblSalidas = new javax.swing.JLabel();
        PanelBarBlue1 = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(1005, 660));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabHistorial.setForeground(java.awt.Color.white);
        tabHistorial.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N

        Usuarios.setBackground(new java.awt.Color(255, 255, 255));
        Usuarios.setForeground(java.awt.Color.white);
        Usuarios.setPreferredSize(new java.awt.Dimension(1005, 660));
        Usuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnReload.setText("Reload");
        BtnReload.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReloadActionPerformed(evt);
            }
        });
        Usuarios.add(BtnReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 90, 40));

        BtnBuscarCCobrar1.setText("Buscar");
        BtnBuscarCCobrar1.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnBuscarCCobrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarCCobrar1ActionPerformed(evt);
            }
        });
        Usuarios.add(BtnBuscarCCobrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 80, 40));

        cmbBuscador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Dia", "Mes", "Año" }));
        Usuarios.add(cmbBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, -1, -1));
        Usuarios.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, 130, -1));

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Login", "Logout"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tablaUsuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaUsuarios);
        if (tablaUsuarios.getColumnModel().getColumnCount() > 0) {
            tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(1).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(2).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(3).setResizable(false);
        }

        Usuarios.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 900, 430));

        LblUtilidad5.setBackground(new java.awt.Color(255, 255, 255));
        LblUtilidad5.setFont(new java.awt.Font("Montserrat", 1, 48)); // NOI18N
        LblUtilidad5.setForeground(new java.awt.Color(0, 112, 192));
        LblUtilidad5.setText("Usuarios");
        Usuarios.add(LblUtilidad5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        tabHistorial.addTab("Usuarios", Usuarios);

        Compras.setBackground(new java.awt.Color(255, 255, 255));
        Compras.setPreferredSize(new java.awt.Dimension(1005, 660));
        Compras.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaCompraHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "IdCompra", "IdProveedor", "Nombre Proveedor", "Descripcion articulo", "Precio Compra", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
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
        TablaCompraHistorial.getTableHeader().setReorderingAllowed(false);
        ScrollCompra.setViewportView(TablaCompraHistorial);
        if (TablaCompraHistorial.getColumnModel().getColumnCount() > 0) {
            TablaCompraHistorial.getColumnModel().getColumn(0).setResizable(false);
            TablaCompraHistorial.getColumnModel().getColumn(1).setResizable(false);
            TablaCompraHistorial.getColumnModel().getColumn(2).setResizable(false);
            TablaCompraHistorial.getColumnModel().getColumn(3).setResizable(false);
            TablaCompraHistorial.getColumnModel().getColumn(4).setResizable(false);
            TablaCompraHistorial.getColumnModel().getColumn(5).setResizable(false);
        }

        Compras.add(ScrollCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 158, 949, -1));

        BtnReloadCompra.setText("Reload");
        BtnReloadCompra.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnReloadCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReloadCompraActionPerformed(evt);
            }
        });
        Compras.add(BtnReloadCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 90, 40));

        BtnBuscarCompra.setText("Buscar");
        BtnBuscarCompra.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        BtnBuscarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarCompraActionPerformed(evt);
            }
        });
        Compras.add(BtnBuscarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 80, 40));

        cmbBuscadorCompra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Dia", "Mes", "Año" }));
        Compras.add(cmbBuscadorCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, -1, -1));
        Compras.add(txtBuscadorCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, 130, -1));

        LblCompra.setBackground(new java.awt.Color(255, 255, 255));
        LblCompra.setFont(new java.awt.Font("Montserrat", 1, 48)); // NOI18N
        LblCompra.setForeground(new java.awt.Color(0, 112, 192));
        LblCompra.setText("Compras");
        Compras.add(LblCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        tabHistorial.addTab("Compra", Compras);

        Caja.setBackground(new java.awt.Color(255, 255, 255));
        Caja.setPreferredSize(new java.awt.Dimension(1005, 660));
        Caja.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "IdCaja", "F Apertura", "Monto Apertura", "F Cierre", "Monto Cierre", "IdUsuario", "Nombre Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(6).setResizable(false);
        }

        Caja.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 158, 949, -1));

        btnReloadCaja.setText("Reload");
        btnReloadCaja.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        Caja.add(btnReloadCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 90, 40));

        btnBuscarCaja.setText("Buscar");
        btnBuscarCaja.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        Caja.add(btnBuscarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 80, 40));

        cmbCaja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Dia", "Mes", "Año" }));
        Caja.add(cmbCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, -1, -1));
        Caja.add(txtBuscadorCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, 130, -1));

        lblCaja.setBackground(new java.awt.Color(255, 255, 255));
        lblCaja.setFont(new java.awt.Font("Montserrat", 1, 48)); // NOI18N
        lblCaja.setForeground(new java.awt.Color(0, 112, 192));
        lblCaja.setText("Caja");
        Caja.add(lblCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        tabHistorial.addTab("Caja", Caja);

        Utilidad.setBackground(new java.awt.Color(255, 255, 255));
        Utilidad.setPreferredSize(new java.awt.Dimension(1005, 660));
        Utilidad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUtilidad.setBackground(new java.awt.Color(255, 255, 255));
        lblUtilidad.setFont(new java.awt.Font("Montserrat", 1, 48)); // NOI18N
        lblUtilidad.setForeground(new java.awt.Color(0, 112, 192));
        lblUtilidad.setText("Utilidad");
        Utilidad.add(lblUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "IdUtilidad", "Ingresos", "Fecha Ingresos", "Gastos", "Fecha Gastos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
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
        jTable3.getTableHeader().setReorderingAllowed(false);
        scrUtilidad.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setHeaderValue("Total Venta");
            jTable3.getColumnModel().getColumn(4).setResizable(false);
            jTable3.getColumnModel().getColumn(4).setHeaderValue("Fecha Venta");
        }

        Utilidad.add(scrUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 158, 949, -1));

        btnReloadUtilidad.setText("Reload");
        btnReloadUtilidad.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        Utilidad.add(btnReloadUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 90, 40));

        btnBuscarUtilidad.setText("Buscar");
        btnBuscarUtilidad.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        Utilidad.add(btnBuscarUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 80, 40));

        cmbBuscadorUtilidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Dia", "Mes", "Año" }));
        Utilidad.add(cmbBuscadorUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, -1, -1));
        Utilidad.add(txtBuscadorUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, 130, -1));

        tabHistorial.addTab("Utilidad", Utilidad);

        Ventas.setBackground(new java.awt.Color(255, 255, 255));
        Ventas.setPreferredSize(new java.awt.Dimension(1005, 660));
        Ventas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblVentas.setBackground(new java.awt.Color(255, 255, 255));
        lblVentas.setFont(new java.awt.Font("Montserrat", 1, 48)); // NOI18N
        lblVentas.setForeground(new java.awt.Color(0, 112, 192));
        lblVentas.setText("Ventas");
        Ventas.add(lblVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        TablaVentaHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "IdVenta", "Nombre Clientes", "Total Venta", "Fecha Venta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
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
        TablaVentaHistorial.getTableHeader().setReorderingAllowed(false);
        srcVentas.setViewportView(TablaVentaHistorial);
        if (TablaVentaHistorial.getColumnModel().getColumnCount() > 0) {
            TablaVentaHistorial.getColumnModel().getColumn(0).setResizable(false);
            TablaVentaHistorial.getColumnModel().getColumn(1).setResizable(false);
            TablaVentaHistorial.getColumnModel().getColumn(2).setResizable(false);
            TablaVentaHistorial.getColumnModel().getColumn(3).setResizable(false);
        }

        Ventas.add(srcVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 158, 949, -1));

        btnReloadVentas.setText("Reload");
        btnReloadVentas.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        Ventas.add(btnReloadVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 90, 40));

        btnBuscadorVentas.setText("Buscar");
        btnBuscadorVentas.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        btnBuscadorVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscadorVentasActionPerformed(evt);
            }
        });
        Ventas.add(btnBuscadorVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 80, 40));

        cmbVentas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Dia", "Mes", "Año" }));
        Ventas.add(cmbVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, -1, -1));
        Ventas.add(txtBuscadorVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, 130, -1));

        tabHistorial.addTab("Ventas", Ventas);

        Entradas.setBackground(new java.awt.Color(255, 255, 255));
        Entradas.setPreferredSize(new java.awt.Dimension(1005, 660));

        lblEntradas.setBackground(new java.awt.Color(255, 255, 255));
        lblEntradas.setFont(new java.awt.Font("Montserrat", 1, 48)); // NOI18N
        lblEntradas.setForeground(new java.awt.Color(0, 112, 192));
        lblEntradas.setText("Cobrados");

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "IdVenta", "Nombre Clientes", "Monto Deuda", "Monto Pagado", "Fecha Pago"
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
        jTable6.getTableHeader().setReorderingAllowed(false);
        scrEntradas.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(0).setResizable(false);
            jTable6.getColumnModel().getColumn(1).setResizable(false);
            jTable6.getColumnModel().getColumn(2).setResizable(false);
            jTable6.getColumnModel().getColumn(3).setResizable(false);
            jTable6.getColumnModel().getColumn(4).setResizable(false);
        }

        btnReloadEntradas.setText("Reload");
        btnReloadEntradas.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N

        btnBuscadorEntradas.setText("Buscar");
        btnBuscadorEntradas.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N

        cmbEntradas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Dia", "Mes", "Año" }));

        javax.swing.GroupLayout EntradasLayout = new javax.swing.GroupLayout(Entradas);
        Entradas.setLayout(EntradasLayout);
        EntradasLayout.setHorizontalGroup(
            EntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(EntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(EntradasLayout.createSequentialGroup()
                    .addGap(0, 26, Short.MAX_VALUE)
                    .addGroup(EntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(EntradasLayout.createSequentialGroup()
                            .addGap(546, 546, 546)
                            .addComponent(btnReloadEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(90, 90, 90)
                            .addComponent(cmbEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(txtBuscadorEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(EntradasLayout.createSequentialGroup()
                            .addGap(636, 636, 636)
                            .addComponent(btnBuscadorEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(EntradasLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(lblEntradas))
                        .addComponent(scrEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 949, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 25, Short.MAX_VALUE)))
        );
        EntradasLayout.setVerticalGroup(
            EntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
            .addGroup(EntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(EntradasLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(EntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(EntradasLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(btnReloadEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(EntradasLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(cmbEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(EntradasLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(txtBuscadorEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(EntradasLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(btnBuscadorEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblEntradas)
                        .addGroup(EntradasLayout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addComponent(scrEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tabHistorial.addTab("Pagos Entradas", Entradas);

        Salidas.setBackground(new java.awt.Color(255, 255, 255));

        TablaPagadosHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "IdCompra", "Proveedor", "Monto Deuda", "Monto Pagado", "Fecha Pago"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
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
        TablaPagadosHistorial.getTableHeader().setReorderingAllowed(false);
        scrSalidas.setViewportView(TablaPagadosHistorial);
        if (TablaPagadosHistorial.getColumnModel().getColumnCount() > 0) {
            TablaPagadosHistorial.getColumnModel().getColumn(0).setResizable(false);
            TablaPagadosHistorial.getColumnModel().getColumn(1).setResizable(false);
            TablaPagadosHistorial.getColumnModel().getColumn(2).setResizable(false);
            TablaPagadosHistorial.getColumnModel().getColumn(3).setResizable(false);
            TablaPagadosHistorial.getColumnModel().getColumn(4).setResizable(false);
        }

        txtBuscadorSalidas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscadorSalidasKeyTyped(evt);
            }
        });

        cmbSalidas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Dia", "Mes", "Año" }));

        btnBuscarSalidas.setText("Buscar");
        btnBuscarSalidas.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N

        btnReloadSalidas.setText("Reload");
        btnReloadSalidas.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N

        lblSalidas.setBackground(new java.awt.Color(255, 255, 255));
        lblSalidas.setFont(new java.awt.Font("Montserrat", 1, 48)); // NOI18N
        lblSalidas.setForeground(new java.awt.Color(0, 112, 192));
        lblSalidas.setText("Pagados");

        javax.swing.GroupLayout SalidasLayout = new javax.swing.GroupLayout(Salidas);
        Salidas.setLayout(SalidasLayout);
        SalidasLayout.setHorizontalGroup(
            SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SalidasLayout.createSequentialGroup()
                    .addGap(0, 26, Short.MAX_VALUE)
                    .addGroup(SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SalidasLayout.createSequentialGroup()
                            .addGap(546, 546, 546)
                            .addComponent(btnReloadSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(90, 90, 90)
                            .addComponent(cmbSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(txtBuscadorSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SalidasLayout.createSequentialGroup()
                            .addGap(636, 636, 636)
                            .addComponent(btnBuscarSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SalidasLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(lblSalidas))
                        .addComponent(scrSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 949, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 25, Short.MAX_VALUE)))
        );
        SalidasLayout.setVerticalGroup(
            SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
            .addGroup(SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SalidasLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SalidasLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(btnReloadSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SalidasLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(cmbSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SalidasLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(txtBuscadorSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SalidasLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(btnBuscarSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblSalidas)
                        .addGroup(SalidasLayout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addComponent(scrSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tabHistorial.addTab("Pagos Salidas", Salidas);

        add(tabHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1005, 660));

        PanelBarBlue1.setBackground(new java.awt.Color(24, 39, 72));
        PanelBarBlue1.setPreferredSize(new java.awt.Dimension(1005, 40));

        javax.swing.GroupLayout PanelBarBlue1Layout = new javax.swing.GroupLayout(PanelBarBlue1);
        PanelBarBlue1.setLayout(PanelBarBlue1Layout);
        PanelBarBlue1Layout.setHorizontalGroup(
            PanelBarBlue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1005, Short.MAX_VALUE)
        );
        PanelBarBlue1Layout.setVerticalGroup(
            PanelBarBlue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(PanelBarBlue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void BtnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReloadActionPerformed
        actualizarTablaUsuario();
        txtBuscador.setText("");
    }//GEN-LAST:event_BtnReloadActionPerformed

    private void BtnBuscarCCobrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarCCobrar1ActionPerformed
        buscarPorFecha();
    }//GEN-LAST:event_BtnBuscarCCobrar1ActionPerformed

    private void txtBuscadorSalidasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorSalidasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscadorSalidasKeyTyped

    private void BtnBuscarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarCompraActionPerformed
    buscadorCompra();    
    }//GEN-LAST:event_BtnBuscarCompraActionPerformed

    private void btnBuscadorVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscadorVentasActionPerformed
        buscarPorFecha();
    }//GEN-LAST:event_btnBuscadorVentasActionPerformed

    private void BtnReloadCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReloadCompraActionPerformed
        actualizarTablaCompra();
    }//GEN-LAST:event_BtnReloadCompraActionPerformed

    
    private void actualizarTablaUsuario()
    {
        String query = "select h.idUsuario as ID, u.nombre as Nombre, h.login as Login, h.logout as Logout from Historial as h"
                                               + " inner join Usuario as u on u.id = h.idUsuario;";
        
        actualizarTabla(tablaUsuarios, query);
    }
    
    
       private void actualizarTabla(JTable tabla, String query)
    {
         DefaultTableModel model = new DefaultTableModel();
 
        try {
            Connection cn = Conexion.conectar();
            Statement stm = cn.createStatement();
            
            ResultSet rs = stm.executeQuery(query);
            
            //RELLENADO DE LAS COLUMNAS
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            for (int column = 1; column <= columnCount; column++) {
                 model.addColumn(metaData.getColumnLabel(column));
             }

            //RELLENADO DE LAS FILAS
            while (rs.next()) {
                 Object[] rowData = new Object[columnCount];
                 for (int column = 1; column <= columnCount; column++) {
                     rowData[column - 1] = rs.getObject(column);
                 }
                 model.addRow(rowData);
             }
            
            tabla.setModel(model);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
       
       private void actualizarTablaCompra(){
       
           String query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id INNER JOIN producto AS a ON a.id = dc.idProducto";
           
           actualizarTabla(TablaCompraHistorial,query);
       }
       
     
    //Metodos Iniciadores
    
    private void initialize() {
        DatosCompraHistorial();
        DatosVentaHistorial();
        DatosPagadosHistorial();
        actualizarTablaUsuario();
        actualizarTablaCompra();
    }   
       
    // Historial Compras
       
      public void DatosCompraHistorial() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        String query = "SELECT c.id AS idCompra, c.idProveedor, pr.nombre AS nombreProveedor, p.descripcion AS descripcionArticulo, c.total AS precioCompra, c.fecha "
             + "FROM compra AS c "
             + "INNER JOIN proveedor AS pr ON c.idProveedor = pr.id "
             + "INNER JOIN producto AS p ON p.id = c.idProveedor";

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

        TablaCompraHistorial.setModel(modelo);

        TablaCompraHistorial.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < TablaCompraHistorial.getColumnCount(); i++) {
            TablaCompraHistorial.getColumnModel().getColumn(i).setResizable(false);
        }
            } catch (SQLException ex) {
                Logger.getLogger(PanelHistorial.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    // Historial ventas
      
      public void DatosVentaHistorial() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        String query = "SELECT v.id AS IDVenta, v.nombreCliente AS 'Nombres Clientes', v.total AS 'Total Venta', v.fecha as Fecha "
             + "FROM venta AS v";


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

        TablaVentaHistorial.setModel(modelo);

        TablaVentaHistorial.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < TablaVentaHistorial.getColumnCount(); i++) {
            TablaVentaHistorial.getColumnModel().getColumn(i).setResizable(false);
        }
    } catch (SQLException ex) {
        Logger.getLogger(PanelHistorial.class.getName()).log(Level.SEVERE, null, ex);
    }
}

      
    // Historial Psgados
         
      public void DatosPagadosHistorial() {
    try {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        String query = "SELECT p.idCompra, c.idProveedor, pr.nombre AS nombreProveedor, c.deuda, p.monto AS montoPagado, p.fecha AS fechaPago "
             + "FROM pagocp AS p "
             + "INNER JOIN cuentaporpagar AS c ON p.idCompra = c.idCompra "
             + "INNER JOIN proveedor AS pr ON c.idProveedor = pr.id";

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

        TablaPagadosHistorial.setModel(modelo);

        TablaPagadosHistorial.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < TablaPagadosHistorial.getColumnCount(); i++) {
            TablaPagadosHistorial.getColumnModel().getColumn(i).setResizable(false);
        }
            } catch (SQLException ex) {
                Logger.getLogger(PanelHistorial.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    // Historial Compras             
       
    private void buscadorCompra() {
     try {
         DefaultTableModel modeloCompra = new DefaultTableModel() {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false; // Evitar que las celdas sean editables, lo hice por code no deja el properties
             }
         };

         String seleccion = (String) cmbBuscadorCompra.getSelectedItem(); // Obtener la opción seleccionada del combo box

         String query = ""; // Inicializar la consulta
         switch (seleccion) {
              case "ID" -> {                   
                String id = txtBuscadorCompra.getText();                                  
                    query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id INNER JOIN producto AS a ON a.id = dc.idProducto WHERE c.idProveedor = '" + id +"'";                    
             }
               case "Nombre" -> {
                 
                 String nombre = txtBuscadorCompra.getText();
                 query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id INNER JOIN producto AS a ON a.id = dc.idProducto WHERE a.descripcion = '" + nombre +"'";                    
             } 
             case "Dia" -> {
                 String dia = txtBuscadorCompra.getText();
                query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id INNER JOIN producto AS a ON a.id = dc.idProducto WHERE DAY(c.fecha) = '" + dia +"'";                    
             }
             case "Mes" -> {
                 String mes = txtBuscadorCompra.getText();
                 query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id INNER JOIN producto AS a ON a.id = dc.idProducto WHERE MONTH(c.fecha) = '" + mes +"'";                    
             }
             case "Año" -> {
                 String year = txtBuscadorCompra.getText();
                 query = "SELECT c.id AS IdCompra, c.idProveedor, p.nombre AS NombreProveedor, a.descripcion AS DescripcionArticulo, dc.cantidadP as Cantidad, dc.precioCompra, c.fecha FROM compra AS c INNER JOIN proveedor AS p ON p.id = c.idProveedor INNER JOIN detallecompra AS dc ON dc.idCompra = c.id INNER JOIN producto AS a ON a.id = dc.idProducto WHERE YEAR(c.fecha) = '" + year +"'";                    
             }
             default -> {
             }
         }

         try (Connection connection = Conexion.conectar();
              Statement statement = connection.createStatement();
              ResultSet resultSet = statement.executeQuery(query)) {

             ResultSetMetaData metaData = resultSet.getMetaData();
             int columnCount = metaData.getColumnCount();

             // Agregar nombres de columnas al modelo
             for (int column = 1; column <= columnCount; column++) {
                 modeloCompra.addColumn(metaData.getColumnLabel(column));
                 
             }

             // Agregar filas al modelo
             while (resultSet.next()) {
                 Object[] rowData = new Object[columnCount];
                 for (int column = 1; column <= columnCount; column++) {
                     rowData[column - 1] = resultSet.getObject(column);
                 }
                 modeloCompra.addRow(rowData);
             }
         }

         TablaCompraHistorial.setModel(modeloCompra);
     } catch (SQLException ex) {
         System.out.println(ex);
     }
 }  
    private void buscarPorFecha() {
     try {
         DefaultTableModel modelo = new DefaultTableModel() {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false; // Evitar que las celdas sean editables, lo hice por code no deja el properties
             }
         };

         String seleccion = (String) cmbBuscador.getSelectedItem(); // Obtener la opción seleccionada del combo box

         String query = ""; // Inicializar la consulta
         switch (seleccion) {
              case "ID" -> {
                 //            LblNotFecha.setForeground(Color.red);
                 //            LblNotFecha.setText("Formato Fecha");
                 
                 String id = txtBuscador.getText();
                 
                 query = "select h.idUsuario as ID, u.nombre as Nombre, h.login as Login, h.logout as Logout from Historial as h"
                         + " inner join Usuario as u on u.id = h.idUsuario"
                         + " WHERE h.idUsuario = '" + id + "'";
             }
               case "Nombre" -> {
                 //            LblNotFecha.setForeground(Color.red);
                 //            LblNotFecha.setText("Formato Fecha");
                 
                 String nombre = txtBuscador.getText();
                 query = "select h.idUsuario as ID, u.nombre as Nombre, h.login as Login, h.logout as Logout from Historial as h"
                         + " inner join Usuario as u on u.id = h.idUsuario"
                         + " WHERE u.nombre = '" + nombre + "'";
             } 
             case "Dia" -> {
                 String dia = txtBuscador.getText();
                 query = "select h.idUsuario as ID, u.nombre as Nombre, h.login as Login, h.logout as Logout from Historial as h"
                         + " inner join Usuario as u on u.id = h.idUsuario"
                         + " WHERE DAY(h.login) = '" + dia + "'";
             }
             case "Mes" -> {
                 String mes = txtBuscador.getText();
                 query = "select h.idUsuario as ID, u.nombre as Nombre, h.login as Login, h.logout as Logout from Historial as h"
                         + " inner join Usuario as u on u.id = h.idUsuario"
                         + " WHERE MONTH(h.login) = '" + mes + "'";
             }
             case "Año" -> {
                 String ano = txtBuscador.getText();
                 query = "select h.idUsuario as ID, u.nombre as Nombre, h.login as Login, h.logout as Logout from Historial as h"
                         + " inner join Usuario as u on u.id = h.idUsuario"
                         + " WHERE YEAR(h.login) = '" + ano + "'";
             }
             default -> {
             }
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

         tablaUsuarios.setModel(modelo);
     } catch (SQLException ex) {
         System.out.println(ex);
     }
 }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle BtnBuscarCCobrar1;
    private rojerusan.RSMaterialButtonRectangle BtnBuscarCompra;
    private rojerusan.RSMaterialButtonRectangle BtnReload;
    private rojerusan.RSMaterialButtonRectangle BtnReloadCompra;
    private javax.swing.JPanel Caja;
    private javax.swing.JPanel Compras;
    private javax.swing.JPanel Entradas;
    private javax.swing.JLabel LblCompra;
    private javax.swing.JLabel LblUtilidad5;
    private javax.swing.JPanel PanelBarBlue1;
    private javax.swing.JPanel Salidas;
    private javax.swing.JScrollPane ScrollCompra;
    private javax.swing.JTable TablaCompraHistorial;
    private javax.swing.JTable TablaPagadosHistorial;
    private javax.swing.JTable TablaVentaHistorial;
    private javax.swing.JPanel Usuarios;
    private javax.swing.JPanel Utilidad;
    private javax.swing.JPanel Ventas;
    private rojerusan.RSMaterialButtonRectangle btnBuscadorEntradas;
    private rojerusan.RSMaterialButtonRectangle btnBuscadorVentas;
    private rojerusan.RSMaterialButtonRectangle btnBuscarCaja;
    private rojerusan.RSMaterialButtonRectangle btnBuscarSalidas;
    private rojerusan.RSMaterialButtonRectangle btnBuscarUtilidad;
    private rojerusan.RSMaterialButtonRectangle btnReloadCaja;
    private rojerusan.RSMaterialButtonRectangle btnReloadEntradas;
    private rojerusan.RSMaterialButtonRectangle btnReloadSalidas;
    private rojerusan.RSMaterialButtonRectangle btnReloadUtilidad;
    private rojerusan.RSMaterialButtonRectangle btnReloadVentas;
    private javax.swing.JComboBox<String> cmbBuscador;
    private javax.swing.JComboBox<String> cmbBuscadorCompra;
    private javax.swing.JComboBox<String> cmbBuscadorUtilidad;
    private javax.swing.JComboBox<String> cmbCaja;
    private javax.swing.JComboBox<String> cmbEntradas;
    private javax.swing.JComboBox<String> cmbSalidas;
    private javax.swing.JComboBox<String> cmbVentas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable6;
    private javax.swing.JLabel lblCaja;
    private javax.swing.JLabel lblEntradas;
    private javax.swing.JLabel lblSalidas;
    private javax.swing.JLabel lblUtilidad;
    private javax.swing.JLabel lblVentas;
    private javax.swing.JScrollPane scrEntradas;
    private javax.swing.JScrollPane scrSalidas;
    private javax.swing.JScrollPane scrUtilidad;
    private javax.swing.JScrollPane srcVentas;
    private Utils.MaterialTabbed tabHistorial;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtBuscadorCaja;
    private javax.swing.JTextField txtBuscadorCompra;
    private javax.swing.JTextField txtBuscadorEntradas;
    private javax.swing.JTextField txtBuscadorSalidas;
    private javax.swing.JTextField txtBuscadorUtilidad;
    private javax.swing.JTextField txtBuscadorVentas;
    // End of variables declaration//GEN-END:variables
}
