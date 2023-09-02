
import Acceso.AccesoUsuario;
import Acceso.ConfirmarAcceso;
import Caja.PanelCaja;

import Contabilidad.TabContabilidad;
import Inventario.PanelInventario;
import Utils.Utils;
import Venta.PanelVenta;
import java.awt.Color;
import java.awt.Frame;
import DTO.Usuario;
import Historial.PanelHistorial;
import Productos.CatalogoProducto;
import Reporte.ReportesGeneral;
import Venta.FacturacionVenta;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Samil Quezada Suriel
 */
public class FramePrincipal extends javax.swing.JFrame implements ConfirmarAcceso.VerificacionListener {

    private static final int ICON_WIDTH = 20;
    private static final int ICON_HEIGHT = 20;
    private String usuarioRol;
    Usuario u = null;
    int xMouse, yMouse;
    boolean isPasswordClicked;
    private FacturacionVenta facturacionVenta;
    
    
    public FramePrincipal() {
        initComponents();        
        inicializar();
}
    
    public FramePrincipal(Usuario u) {
        this.u = u;    
        initComponents();
        inicializar(); 
       
}

    public void setUsuarioRol(String usuarioRol) {
        this.usuarioRol = usuarioRol;
    }
    
    public void inicializar() {
     
        handleUserRole(usuarioRol);

     setupMouseListeners(
        BtnCasa, BtnAcceso, BtnProducto, BtnContabilidad, BtnReportes,
        BtnCaja, BtnVentas, BtnInventario, BtnHistorial,
        Lblcasa, LblAccesos, LblProductos, LblContabilidad, LblReportes,
        LblCaja, LblVentas, LblInventario, LblHistorial,
        IconCasa, IconCaja, IconVentas, IconContabilidad, IconInventario,
        IconProducto, IconAcceso, IconReportes, LblVentas,
        IconHistorial
    );

        setupDragListeners(PanelMoveLogin);
        
        LblEncabezado.setText("Principal");
        Principal p = new Principal(u);
        p.setSize(1005, 660);
        p.setLocation(0, 0);

        Utils.showPane(ContentAll, p);

        setupIcons();

     }
 
    private void setupDragListeners(Component component) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                xMouse = evt.getX();
                yMouse = evt.getY();
            }
        });
        component.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });
    }

    private void setupMouseListeners(Component... components) {
        for (Component component : components) {
            component.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    component.setBackground(new Color(44, 58, 88));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    component.setBackground(new Color(24, 39, 72));
                }

                // Add more events as needed
            });
        }
    }
    
    private void setupIcons() {
       
    Utils.setScaledImage(IconCasa, "Principal.png", ICON_WIDTH, ICON_HEIGHT);
    Utils.setScaledImage(IconCaja, "caja-registradora.png", ICON_WIDTH, ICON_HEIGHT);
    Utils.setScaledImage(IconVentas, "ventas.png", ICON_WIDTH, ICON_HEIGHT);
    Utils.setScaledImage(IconContabilidad, "Contabilidad.png", ICON_WIDTH, ICON_HEIGHT);
    Utils.setScaledImage(IconInventario, "inventario.png", ICON_WIDTH, ICON_HEIGHT);
    Utils.setScaledImage(IconProducto, "Producto.png", ICON_WIDTH, ICON_HEIGHT);
    Utils.setScaledImage(IconLogo, "ParadaLogo.png", 200, 200);
    Utils.setScaledImage(IconAcceso, "acceso.png", ICON_WIDTH, ICON_HEIGHT);
    Utils.setScaledImage(IconReportes, "Reporte.png", ICON_WIDTH, ICON_HEIGHT);
    Utils.setScaledImage(IconHistorial, "historial.png", ICON_WIDTH, ICON_HEIGHT);
    Utils.setScaledImage(IconUser, "usuario.png", ICON_WIDTH, ICON_HEIGHT);
  
    }

    public void handleUserRole(String role) {
        if ("admin".equals(role)) {
            TipoUser.setText(role);
            
        } else if ("user".equals(role)) {
            TipoUser.setText(role);
            
            //Bloqueo de Icon, Label, Botones 
            
            //Contabilidad
            IconContabilidad.setEnabled(false);
            LblContabilidad.setEnabled(false);
            BtnContabilidad.setEnabled(false);
            
            //Acceso
            IconAcceso.setEnabled(false);
            LblAccesos.setEnabled(false);
            BtnAcceso.setEnabled(false);
            
            //Reportes
            IconReportes.setEnabled(false);
            LblReportes.setEnabled(false);
            BtnReportes.setEnabled(false);
            
            //Inventario
            IconInventario.setEnabled(false);
            LblInventario.setEnabled(false);
            BtnInventario.setEnabled(false);
            
            //Historial 
            IconHistorial.setEnabled(false);
            LblHistorial.setEnabled(false);
            BtnHistorial.setEnabled(false);
                       
        }
    }
    
    public void onVerificacionExitosa() {
        // Realizar acciones necesarias cuando la verificación es exitosa
        AccesoUsuario AU = new AccesoUsuario();
        LblEncabezado.setText("Acceso");
        AU.setSize(1005, 660);
        AU.setLocation(0, 0);
        Utils.showPane(ContentAll, AU);
        System.out.println("Contraseña ingresada correctamente.");
    }

    public void onVerificacionFallida() {
        // Realizar acciones necesarias cuando la verificación falla
        System.out.println("Contraseña incorrecta o no ingresada.");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BGPanel = new javax.swing.JPanel();
        PanelLateral = new javax.swing.JPanel();
        IconLogo = new javax.swing.JLabel();
        BtnCasa = new javax.swing.JPanel();
        IconCasa = new javax.swing.JLabel();
        Lblcasa = new javax.swing.JLabel();
        BtnContabilidad = new javax.swing.JPanel();
        IconContabilidad = new javax.swing.JLabel();
        LblContabilidad = new javax.swing.JLabel();
        BtnProducto = new javax.swing.JPanel();
        IconProducto = new javax.swing.JLabel();
        LblProductos = new javax.swing.JLabel();
        BtnAcceso = new javax.swing.JPanel();
        IconAcceso = new javax.swing.JLabel();
        LblAccesos = new javax.swing.JLabel();
        BtnCaja = new javax.swing.JPanel();
        IconCaja = new javax.swing.JLabel();
        LblCaja = new javax.swing.JLabel();
        BtnReportes = new javax.swing.JPanel();
        IconReportes = new javax.swing.JLabel();
        LblReportes = new javax.swing.JLabel();
        BtnVentas = new javax.swing.JPanel();
        IconVentas = new javax.swing.JLabel();
        LblVentas = new javax.swing.JLabel();
        BtnInventario = new javax.swing.JPanel();
        IconInventario = new javax.swing.JLabel();
        LblInventario = new javax.swing.JLabel();
        BtnHistorial = new javax.swing.JPanel();
        IconHistorial = new javax.swing.JLabel();
        LblHistorial = new javax.swing.JLabel();
        btninfo = new rojerusan.RSMaterialButtonRectangle();
        PanelBarGreen = new javax.swing.JPanel();
        BtnUser = new javax.swing.JPanel();
        IconUser = new javax.swing.JLabel();
        TipoUser = new javax.swing.JLabel();
        PanelMoveLogin = new javax.swing.JPanel();
        LblEncabezado = new javax.swing.JLabel();
        btnSalida = new rojerusan.RSMaterialButtonRectangle();
        btnMinimizar2 = new rojerusan.RSMaterialButtonRectangle();
        ContentAll = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1215, 700));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BGPanel.setForeground(new java.awt.Color(255, 255, 255));
        BGPanel.setMinimumSize(new java.awt.Dimension(1215, 700));
        BGPanel.setOpaque(false);
        BGPanel.setPreferredSize(new java.awt.Dimension(1215, 700));
        BGPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelLateral.setBackground(new java.awt.Color(24, 39, 72));
        PanelLateral.setMinimumSize(new java.awt.Dimension(210, 700));
        PanelLateral.setPreferredSize(new java.awt.Dimension(210, 700));

        IconLogo.setForeground(new java.awt.Color(255, 255, 255));

        BtnCasa.setBackground(new java.awt.Color(24, 39, 72));
        BtnCasa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCasa.setRequestFocusEnabled(false);
        BtnCasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCasaMouseClicked(evt);
            }
        });

        IconCasa.setForeground(new java.awt.Color(255, 255, 255));
        IconCasa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        IconCasa.setPreferredSize(new java.awt.Dimension(25, 25));

        Lblcasa.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        Lblcasa.setForeground(new java.awt.Color(255, 255, 255));
        Lblcasa.setText("Principal");
        Lblcasa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Lblcasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblcasaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LblcasaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LblcasaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout BtnCasaLayout = new javax.swing.GroupLayout(BtnCasa);
        BtnCasa.setLayout(BtnCasaLayout);
        BtnCasaLayout.setHorizontalGroup(
            BtnCasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnCasaLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(IconCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Lblcasa, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BtnCasaLayout.setVerticalGroup(
            BtnCasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IconCasa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtnCasaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Lblcasa)
                .addContainerGap())
        );

        BtnContabilidad.setBackground(new java.awt.Color(24, 39, 72));
        BtnContabilidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnContabilidad.setRequestFocusEnabled(false);
        BtnContabilidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnContabilidadMouseClicked(evt);
            }
        });

        IconContabilidad.setForeground(new java.awt.Color(255, 255, 255));
        IconContabilidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        LblContabilidad.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        LblContabilidad.setForeground(new java.awt.Color(255, 255, 255));
        LblContabilidad.setText("Contabilidad");
        LblContabilidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblContabilidadMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnContabilidadLayout = new javax.swing.GroupLayout(BtnContabilidad);
        BtnContabilidad.setLayout(BtnContabilidadLayout);
        BtnContabilidadLayout.setHorizontalGroup(
            BtnContabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnContabilidadLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(IconContabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblContabilidad)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BtnContabilidadLayout.setVerticalGroup(
            BtnContabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtnContabilidadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblContabilidad)
                .addContainerGap())
            .addComponent(IconContabilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        BtnProducto.setBackground(new java.awt.Color(24, 39, 72));
        BtnProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnProducto.setRequestFocusEnabled(false);
        BtnProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnProductoMouseClicked(evt);
            }
        });

        IconProducto.setForeground(new java.awt.Color(255, 255, 255));
        IconProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        IconProducto.setMaximumSize(new java.awt.Dimension(67, 16));
        IconProducto.setMinimumSize(new java.awt.Dimension(67, 16));
        IconProducto.setPreferredSize(new java.awt.Dimension(20, 20));

        LblProductos.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        LblProductos.setForeground(new java.awt.Color(255, 255, 255));
        LblProductos.setText("Productos");
        LblProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblProductosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnProductoLayout = new javax.swing.GroupLayout(BtnProducto);
        BtnProducto.setLayout(BtnProductoLayout);
        BtnProductoLayout.setHorizontalGroup(
            BtnProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnProductoLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(IconProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblProductos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BtnProductoLayout.setVerticalGroup(
            BtnProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IconProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtnProductoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblProductos)
                .addContainerGap())
        );

        BtnAcceso.setBackground(new java.awt.Color(24, 39, 72));
        BtnAcceso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAcceso.setRequestFocusEnabled(false);
        BtnAcceso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAccesoMouseClicked(evt);
            }
        });

        IconAcceso.setForeground(new java.awt.Color(255, 255, 255));
        IconAcceso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        IconAcceso.setPreferredSize(new java.awt.Dimension(20, 20));

        LblAccesos.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        LblAccesos.setForeground(new java.awt.Color(255, 255, 255));
        LblAccesos.setText("Accesos");
        LblAccesos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LblAccesos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblAccesosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnAccesoLayout = new javax.swing.GroupLayout(BtnAcceso);
        BtnAcceso.setLayout(BtnAccesoLayout);
        BtnAccesoLayout.setHorizontalGroup(
            BtnAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnAccesoLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(IconAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblAccesos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BtnAccesoLayout.setVerticalGroup(
            BtnAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IconAcceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtnAccesoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblAccesos)
                .addContainerGap())
        );

        BtnCaja.setBackground(new java.awt.Color(24, 39, 72));
        BtnCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCaja.setRequestFocusEnabled(false);
        BtnCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCajaMouseClicked(evt);
            }
        });

        IconCaja.setForeground(new java.awt.Color(255, 255, 255));
        IconCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        LblCaja.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        LblCaja.setForeground(new java.awt.Color(255, 255, 255));
        LblCaja.setText("Caja");
        LblCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LblCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblCajaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnCajaLayout = new javax.swing.GroupLayout(BtnCaja);
        BtnCaja.setLayout(BtnCajaLayout);
        BtnCajaLayout.setHorizontalGroup(
            BtnCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnCajaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(IconCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblCaja)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BtnCajaLayout.setVerticalGroup(
            BtnCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IconCaja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtnCajaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblCaja)
                .addContainerGap())
        );

        BtnReportes.setBackground(new java.awt.Color(24, 39, 72));
        BtnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnReportes.setRequestFocusEnabled(false);
        BtnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnReportesMouseClicked(evt);
            }
        });

        IconReportes.setForeground(new java.awt.Color(255, 255, 255));
        IconReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        LblReportes.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        LblReportes.setForeground(new java.awt.Color(255, 255, 255));
        LblReportes.setText("Reportes");
        LblReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LblReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblReportesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnReportesLayout = new javax.swing.GroupLayout(BtnReportes);
        BtnReportes.setLayout(BtnReportesLayout);
        BtnReportesLayout.setHorizontalGroup(
            BtnReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnReportesLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(IconReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblReportes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BtnReportesLayout.setVerticalGroup(
            BtnReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IconReportes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtnReportesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblReportes)
                .addContainerGap())
        );

        BtnVentas.setBackground(new java.awt.Color(24, 39, 72));
        BtnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnVentas.setRequestFocusEnabled(false);
        BtnVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnVentasMouseClicked(evt);
            }
        });

        IconVentas.setForeground(new java.awt.Color(255, 255, 255));
        IconVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        LblVentas.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        LblVentas.setForeground(new java.awt.Color(255, 255, 255));
        LblVentas.setText("Ventas");
        LblVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblVentasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnVentasLayout = new javax.swing.GroupLayout(BtnVentas);
        BtnVentas.setLayout(BtnVentasLayout);
        BtnVentasLayout.setHorizontalGroup(
            BtnVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnVentasLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(IconVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblVentas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BtnVentasLayout.setVerticalGroup(
            BtnVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IconVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtnVentasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblVentas)
                .addContainerGap())
        );

        BtnInventario.setBackground(new java.awt.Color(24, 39, 72));
        BtnInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnInventario.setRequestFocusEnabled(false);
        BtnInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnInventarioMouseClicked(evt);
            }
        });

        IconInventario.setForeground(new java.awt.Color(255, 255, 255));
        IconInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        LblInventario.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        LblInventario.setForeground(new java.awt.Color(255, 255, 255));
        LblInventario.setText("Inventario");
        LblInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LblInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblInventarioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnInventarioLayout = new javax.swing.GroupLayout(BtnInventario);
        BtnInventario.setLayout(BtnInventarioLayout);
        BtnInventarioLayout.setHorizontalGroup(
            BtnInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnInventarioLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(IconInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblInventario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BtnInventarioLayout.setVerticalGroup(
            BtnInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IconInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BtnInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblInventario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BtnHistorial.setBackground(new java.awt.Color(24, 39, 72));
        BtnHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnHistorial.setRequestFocusEnabled(false);
        BtnHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnHistorialMouseClicked(evt);
            }
        });

        IconHistorial.setForeground(new java.awt.Color(255, 255, 255));
        IconHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        LblHistorial.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        LblHistorial.setForeground(new java.awt.Color(255, 255, 255));
        LblHistorial.setText("Historial");
        LblHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LblHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblHistorialMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnHistorialLayout = new javax.swing.GroupLayout(BtnHistorial);
        BtnHistorial.setLayout(BtnHistorialLayout);
        BtnHistorialLayout.setHorizontalGroup(
            BtnHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnHistorialLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(IconHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblHistorial)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BtnHistorialLayout.setVerticalGroup(
            BtnHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IconHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BtnHistorialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblHistorial)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btninfo.setBackground(new java.awt.Color(255, 255, 255));
        btninfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btninfo.setForeground(new java.awt.Color(24, 39, 72));
        btninfo.setText("I");
        btninfo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btninfo.setFont(new java.awt.Font("Roboto Light", 1, 10)); // NOI18N
        btninfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelLateralLayout = new javax.swing.GroupLayout(PanelLateral);
        PanelLateral.setLayout(PanelLateralLayout);
        PanelLateralLayout.setHorizontalGroup(
            PanelLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BtnCasa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnAcceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnContabilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnHistorial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelLateralLayout.createSequentialGroup()
                .addGroup(PanelLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLateralLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(IconLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btninfo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        PanelLateralLayout.setVerticalGroup(
            PanelLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLateralLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(IconLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(BtnAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(BtnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(BtnContabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(BtnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(BtnCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(BtnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(BtnInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addComponent(btninfo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        BGPanel.add(PanelLateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        PanelBarGreen.setBackground(new java.awt.Color(132, 178, 80));
        PanelBarGreen.setPreferredSize(new java.awt.Dimension(1005, 43));
        PanelBarGreen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnUser.setBackground(new java.awt.Color(132, 178, 80));
        BtnUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnUserMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BtnUserMousePressed(evt);
            }
        });

        IconUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        IconUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IconUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IconUserMouseExited(evt);
            }
        });

        TipoUser.setBackground(new java.awt.Color(255, 255, 255));
        TipoUser.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        TipoUser.setForeground(new java.awt.Color(255, 255, 255));
        TipoUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TipoUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout BtnUserLayout = new javax.swing.GroupLayout(BtnUser);
        BtnUser.setLayout(BtnUserLayout);
        BtnUserLayout.setHorizontalGroup(
            BtnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TipoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IconUser, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BtnUserLayout.setVerticalGroup(
            BtnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnUserLayout.createSequentialGroup()
                .addComponent(IconUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(BtnUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TipoUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelBarGreen.add(BtnUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1091, 0, -1, 40));

        PanelMoveLogin.setBackground(new java.awt.Color(132, 178, 80));
        PanelMoveLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        PanelMoveLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblEncabezado.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        LblEncabezado.setForeground(new java.awt.Color(255, 255, 255));
        LblEncabezado.setText("Panel Principal");
        PanelMoveLogin.add(LblEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 0, -1, 40));

        btnSalida.setBackground(new java.awt.Color(132, 178, 80));
        btnSalida.setText("X");
        btnSalida.setFont(new java.awt.Font("Roboto Light", 1, 10)); // NOI18N
        btnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidaActionPerformed(evt);
            }
        });
        PanelMoveLogin.add(btnSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 30, 40));

        btnMinimizar2.setBackground(new java.awt.Color(132, 178, 80));
        btnMinimizar2.setText("--");
        btnMinimizar2.setFont(new java.awt.Font("Roboto Light", 1, 10)); // NOI18N
        btnMinimizar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizar2ActionPerformed(evt);
            }
        });
        PanelMoveLogin.add(btnMinimizar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 30, 40));

        PanelBarGreen.add(PanelMoveLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1090, 40));

        BGPanel.add(PanelBarGreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 1220, 40));

        ContentAll.setMinimumSize(new java.awt.Dimension(1005, 660));
        ContentAll.setPreferredSize(new java.awt.Dimension(1005, 660));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setText("ContentAll");

        javax.swing.GroupLayout ContentAllLayout = new javax.swing.GroupLayout(ContentAll);
        ContentAll.setLayout(ContentAllLayout);
        ContentAllLayout.setHorizontalGroup(
            ContentAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentAllLayout.createSequentialGroup()
                .addContainerGap(460, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(459, 459, 459))
        );
        ContentAllLayout.setVerticalGroup(
            ContentAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentAllLayout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(jLabel1)
                .addContainerGap(321, Short.MAX_VALUE))
        );

        BGPanel.add(ContentAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

        getContentPane().add(BGPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnUserMouseEntered
        BtnUser.setBackground(new Color(152,191,109));
    }//GEN-LAST:event_BtnUserMouseEntered

    private void BtnUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnUserMouseExited
        BtnUser.setBackground(new Color(132,178,80));
    }//GEN-LAST:event_BtnUserMouseExited

    private void BtnContabilidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnContabilidadMouseClicked

    if (BtnContabilidad.isEnabled()) {

            LblEncabezado.setText("Contabilidad");
            TabContabilidad pv = new TabContabilidad(this);
            pv.setSize(1005, 660);
            pv.setLocation(0, 0);

            Utils.showPane(ContentAll, pv);

        }

    }//GEN-LAST:event_BtnContabilidadMouseClicked

    private void LblContabilidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblContabilidadMouseClicked
 
     if (BtnContabilidad.isEnabled()) {

            LblEncabezado.setText("Contabilidad");
            TabContabilidad pv = new TabContabilidad(this);
            pv.setSize(1005, 660);
            pv.setLocation(0, 0);

            Utils.showPane(ContentAll, pv);

        }
    }//GEN-LAST:event_LblContabilidadMouseClicked
 
    private void LblcasaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblcasaMouseEntered
        BtnCasa.setBackground(new Color(44, 58, 88));
    }//GEN-LAST:event_LblcasaMouseEntered

    private void LblcasaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblcasaMouseExited
        BtnCasa.setBackground(new Color(24,39,72));
    }//GEN-LAST:event_LblcasaMouseExited

    private void LblCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblCajaMouseClicked
        
        LblEncabezado.setText("Caja");
        
        PanelCaja pc = new PanelCaja(u);
        pc.setSize(1005, 660);
        pc.setLocation(0, 0);
      
        Utils.showPane(ContentAll, pc);
      
    }//GEN-LAST:event_LblCajaMouseClicked

    private void BtnCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCajaMouseClicked
      
        LblEncabezado.setText("Caja");
        
        PanelCaja pc = new PanelCaja(u);
        pc.setSize(1005, 660);
        pc.setLocation(0, 0);
      
        Utils.showPane(ContentAll, pc);
      
    }//GEN-LAST:event_BtnCajaMouseClicked

    private void BtnReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnReportesMouseClicked
      
        if (BtnReportes.isEnabled()) {
            ReportesGeneral rg = new ReportesGeneral();
            rg.setSize(1005, 660);
            rg.setLocation(0, 0);

            Utils.showPane(ContentAll, rg);
      }  
    }//GEN-LAST:event_BtnReportesMouseClicked

    private void LblReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblReportesMouseClicked
     
        if (LblReportes.isEnabled()) {
        LblEncabezado.setText("Reporte");
        ReportesGeneral rg = new ReportesGeneral();
        rg.setSize(1005, 660);
        rg.setLocation(0, 0);
      
        Utils.showPane(ContentAll, rg);
      }  
    }//GEN-LAST:event_LblReportesMouseClicked

    private void BtnCasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCasaMouseClicked
   
        LblEncabezado.setText("Principal");
        Principal p = new Principal();
        p.setSize(1005, 660);
        p.setLocation(0, 0);
      
        Utils.showPane(ContentAll, p);
       
    }//GEN-LAST:event_BtnCasaMouseClicked

    private void BtnAccesoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAccesoMouseClicked
       
     if (LblAccesos.isEnabled()) {        
        
        ConfirmarAcceso CA = new ConfirmarAcceso(null, true, u);
        CA.setVerificacionListener(FramePrincipal.this);
        CA.setLocationRelativeTo(this);
        CA.setVisible(true);//rootPaneCheckingEnabled
       
       }
    }//GEN-LAST:event_BtnAccesoMouseClicked

    private void LblcasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblcasaMouseClicked

       LblEncabezado.setText("Principal");
        Principal p = new Principal();
        p.setSize(1005, 660);
        p.setLocation(0, 0);
      
        Utils.showPane(ContentAll, p);        
    }//GEN-LAST:event_LblcasaMouseClicked

    private void IconUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IconUserMouseEntered
        BtnUser.setBackground(new Color(152,191,109));
    }//GEN-LAST:event_IconUserMouseEntered

    private void LblInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblInventarioMouseClicked
      if (LblInventario.isEnabled()) {
        LblEncabezado.setText("Inventario");
        PanelInventario pi = new PanelInventario();
        pi.setSize(1005,660);
        pi.setLocation(0, 0);
        
        Utils.showPane(ContentAll, pi);
       }
    }//GEN-LAST:event_LblInventarioMouseClicked

    private void LblHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblHistorialMouseClicked
      if (LblHistorial.isEnabled()) {
        LblEncabezado.setText("Historial");
        PanelHistorial ph = new PanelHistorial();
        ph.setSize(1005,660);
        ph.setLocation(0, 0);
        
        Utils.showPane(ContentAll, ph);
       }
    }//GEN-LAST:event_LblHistorialMouseClicked

    private void IconUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IconUserMouseExited
        BtnUser.setBackground(new Color(132,178,80));
    }//GEN-LAST:event_IconUserMouseExited

    private void BtnInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnInventarioMouseClicked
       if (LblInventario.isEnabled()) {
        LblEncabezado.setText("Inventario");
        PanelInventario pi = new PanelInventario();
        pi.setSize(1005,660);
        pi.setLocation(0, 0);
        
        Utils.showPane(ContentAll, pi);
       }
    
    }//GEN-LAST:event_BtnInventarioMouseClicked

    private void LblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblProductosMouseClicked
        
        LblEncabezado.setText("Productos");
        //PanelProductos pp = new PanelProductos(this);
        CatalogoProducto pp = new CatalogoProducto(u);
        pp.setSize(1005,660);
        pp.setLocation(0, 0);
        
        Utils.showPane(ContentAll, pp);
    }//GEN-LAST:event_LblProductosMouseClicked

    private void BtnProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnProductoMouseClicked
        
        LblEncabezado.setText("Productos");
        //PanelProductos pp = new PanelProductos(this);
        CatalogoProducto pp = new CatalogoProducto(u);
        pp.setSize(1005,660);
        pp.setLocation(0, 0);
        
        Utils.showPane(ContentAll, pp);
    }//GEN-LAST:event_BtnProductoMouseClicked

    private void LblAccesosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblAccesosMouseClicked
       if (LblAccesos.isEnabled()) {        
        
        ConfirmarAcceso CA = new ConfirmarAcceso(null, true, u);
        CA.setVerificacionListener(FramePrincipal.this);
        CA.setLocationRelativeTo(this);
        CA.setVisible(true);//rootPaneCheckingEnabled
        
       }
    }//GEN-LAST:event_LblAccesosMouseClicked

    private void BtnVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnVentasMouseClicked
        LblEncabezado.setText("Ventas");
        
        if (facturacionVenta == null) {
            facturacionVenta = new FacturacionVenta(u);
             facturacionVenta.setSize(1005, 660);
             facturacionVenta.setLocation(0, 0);
            Utils.showPane(ContentAll, facturacionVenta);
        } else {
            Utils.showPane(ContentAll, facturacionVenta);
            facturacionVenta.restaurarDatos(); // Restaurar los datos al cambiar al panel
        }
          
    }//GEN-LAST:event_BtnVentasMouseClicked

    private void LblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblVentasMouseClicked
        LblEncabezado.setText("Ventas");
        
        if (facturacionVenta == null) {
            facturacionVenta = new FacturacionVenta(u);
             facturacionVenta.setSize(1005, 660);
             facturacionVenta.setLocation(0, 0);
            Utils.showPane(ContentAll, facturacionVenta);
        } else {
            Utils.showPane(ContentAll, facturacionVenta);
            facturacionVenta.restaurarDatos(); // Restaurar los datos al cambiar al panel
        }
          
    }//GEN-LAST:event_LblVentasMouseClicked

    private void BtnUserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnUserMousePressed
            CerrarSesion CS = new CerrarSesion(u);
            CS.setLocationRelativeTo(this); 
            CS.setVisible(true);       
    }//GEN-LAST:event_BtnUserMousePressed

    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidaActionPerformed

    PanelCaja pc = new PanelCaja(u);
    pc.cerrarCajaAutomatically(); 

    System.exit(0);

    }//GEN-LAST:event_btnSalidaActionPerformed

    private void btninfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninfoActionPerformed
       Informacion i = new Informacion(null, true);
       i.setLocationRelativeTo(this);
       i.setVisible(true);
    }//GEN-LAST:event_btninfoActionPerformed

    private void BtnHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnHistorialMouseClicked
        // TODO add your handling code here:
        if (LblHistorial.isEnabled()) {
        LblEncabezado.setText("Historial");
        PanelHistorial ph = new PanelHistorial();
        ph.setSize(1005,660);
        ph.setLocation(0, 0);
        
        Utils.showPane(ContentAll, ph);
       }
    }//GEN-LAST:event_BtnHistorialMouseClicked

    private void btnMinimizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizar2ActionPerformed
          this.setState(Frame.ICONIFIED); 
    }//GEN-LAST:event_btnMinimizar2ActionPerformed

    public void realizarAccionesSegunPassword() {
        if (isPasswordClicked) {
            // La contraseña ha sido ingresada correctamente, realiza las acciones aquí
            // Por ejemplo:
            
        AccesoUsuario AU = new AccesoUsuario();
        AU.setSize(1005, 660);
        AU.setLocation(0, 0);
      
        Utils.showPane(ContentAll, AU);
       
            System.out.println("Contraseña ingresada correctamente. Realizar acciones necesarias.");
        } else {
            // La contraseña no ha sido ingresada correctamente
            System.out.println("Contraseña incorrecta o no ingresada. No se pueden realizar acciones.");
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BGPanel;
    private javax.swing.JPanel BtnAcceso;
    private javax.swing.JPanel BtnCaja;
    private javax.swing.JPanel BtnCasa;
    private javax.swing.JPanel BtnContabilidad;
    private javax.swing.JPanel BtnHistorial;
    private javax.swing.JPanel BtnInventario;
    private javax.swing.JPanel BtnProducto;
    private javax.swing.JPanel BtnReportes;
    public static javax.swing.JPanel BtnUser;
    private javax.swing.JPanel BtnVentas;
    private javax.swing.JPanel ContentAll;
    private javax.swing.JLabel IconAcceso;
    private javax.swing.JLabel IconCaja;
    private javax.swing.JLabel IconCasa;
    private javax.swing.JLabel IconContabilidad;
    private javax.swing.JLabel IconHistorial;
    private javax.swing.JLabel IconInventario;
    private javax.swing.JLabel IconLogo;
    private javax.swing.JLabel IconProducto;
    private javax.swing.JLabel IconReportes;
    public static javax.swing.JLabel IconUser;
    private javax.swing.JLabel IconVentas;
    private javax.swing.JLabel LblAccesos;
    private javax.swing.JLabel LblCaja;
    private javax.swing.JLabel LblContabilidad;
    public static javax.swing.JLabel LblEncabezado;
    private javax.swing.JLabel LblHistorial;
    private javax.swing.JLabel LblInventario;
    private javax.swing.JLabel LblProductos;
    private javax.swing.JLabel LblReportes;
    private javax.swing.JLabel LblVentas;
    private javax.swing.JLabel Lblcasa;
    public static javax.swing.JPanel PanelBarGreen;
    private javax.swing.JPanel PanelLateral;
    private javax.swing.JPanel PanelMoveLogin;
    private javax.swing.JLabel TipoUser;
    private rojerusan.RSMaterialButtonRectangle btnMinimizar2;
    private rojerusan.RSMaterialButtonRectangle btnSalida;
    private rojerusan.RSMaterialButtonRectangle btninfo;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
