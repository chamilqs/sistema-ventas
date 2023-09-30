package appStructure.application.form;

import Database.Connect.Conexion;
import Database.Connect.ConexionSingleton;
import Database.DAOImpl.UsuarioDAOImpl;
import Database.DTO.Usuario;
import appStructure.application.form.other.FormProfile;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import appStructure.application.Application;
import appStructure.application.form.other.Contabilidad.FormCxC;
import appStructure.application.form.other.Contabilidad.FormCxP;
import appStructure.application.form.other.Contabilidad.FormGastos;
import appStructure.application.form.other.Contabilidad.FormIngresos;
import appStructure.application.form.other.Contabilidad.FormCobrosClientes;
import appStructure.application.form.other.Contabilidad.FormUtilidadOp;
import appStructure.application.form.other.FormAcceso;
import appStructure.application.form.other.Dashboard.FormDashboard;
import appStructure.application.form.other.Ventas.FormFacturacion;
import appStructure.application.form.other.Ventas.FormCaja;
import appStructure.application.form.other.FormConfirmarAcceso;
import appStructure.application.form.other.Historial.FormHCaja;
import appStructure.application.form.other.Historial.FormHCompra;
import appStructure.application.form.other.Historial.FormHPagos;
import appStructure.application.form.other.Historial.FormHUsuarios;
import appStructure.application.form.other.Historial.FormHVentas;
import appStructure.application.form.other.Producto.FormCatalogo;
import appStructure.application.form.other.Producto.FormInventario;
import appStructure.application.form.other.Producto.FormProveedores;
import appStructure.application.form.other.Producto.FormRInventario;
import appStructure.application.form.other.Reportes.FormReporteProductos;
import appStructure.application.form.other.Reportes.FormReporteVentas;
import appStructure.menu.Menu;
import appStructure.menu.MenuAction;

/**
 *
 * @author Samil
 */
public class MainForm extends JLayeredPane {

    Usuario u = null;
    
    public MainForm() {
        init();
    }

    public MainForm(Usuario u) {
        this.u = u;
        init();
    }
    
    private void init() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
        menu = new Menu(u);
        panelBody = new JPanel(new BorderLayout());
        initMenuArrowIcon();
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");
        menuButton.addActionListener((ActionEvent e) -> {
            setMenuFull(!menu.isMenuFull());
        });
        initMenuEvent();
        setLayer(menuButton, JLayeredPane.POPUP_LAYER);
        add(menuButton);
        add(menu);
        add(panelBody);
    }

    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        super.applyComponentOrientation(o);
        initMenuArrowIcon();
    }

    private void initMenuArrowIcon() {
        if (menuButton == null) {
            menuButton = new JButton();
        }
        String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
        menuButton.setIcon(new FlatSVGIcon("appStructure/icon/svg/" + icon, 0.8f));
    }

    private void logoutManager() {
     
    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
        
    try (Connection connection = c.getConexion()){
                
            Statement statement = connection.createStatement();

            String query = "SELECT logout FROM historial WHERE logout IS NULL";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                // Credenciales correctas
                String queryU = "UPDATE historial SET logout = ? WHERE logout IS NULL";
                PreparedStatement pst = connection.prepareStatement(queryU);
                LocalDateTime fechaHoraActual = LocalDateTime.now();
                pst.setTimestamp(1, Timestamp.valueOf(fechaHoraActual));
                pst.executeUpdate();
                FormCaja fc = new FormCaja();
                fc.forLogout();
                
                c.desconectar();
            } else {
                
                System.out.println("Error");
                c.desconectar();

            }  

    } catch (SQLException ex) {
        System.out.println(ex);
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
    }catch (ClassNotFoundException ex) {
                 Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
             }
}

    FormFacturacion formFacturacion = FormFacturacion.getInstance();
    FormCaja formCaja = FormCaja.getInstance();

    private void initMenuEvent() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            // Application.mainForm.showForm(new DefaultForm("Form : " + index + " " + subIndex));
            if (index == 0) {
                Application.showForm(new FormDashboard(u));
            } else if (index == 1) {
                if (subIndex == 1) {                   
                    Application.showForm(formCaja);
                } else if (subIndex == 2) {
                    Application.showForm(formFacturacion);
                } else {
                    action.cancel();
                }  
            } else if (index == 2) {                            
                    Application.showForm(new FormReporteVentas());                          
            } else if (index == 3) {                            
                    Application.showForm(new FormReporteProductos());                            
            } else if (index == 4) {
                if (subIndex == 1) {                   
                    Application.showForm(new FormCxC());
                } else if (subIndex == 2) {
                    Application.showForm(new FormCxP());
                } else if (subIndex == 3) {
                    Application.showForm(new FormGastos());
                } else if (subIndex == 4) {
                    Application.showForm(new FormIngresos());
                } else {
                    action.cancel();
                }                                
            } else if (index == 5) {                            
                    Application.showForm(new FormCobrosClientes());                          
            } else if (index == 6) {                            
                    Application.showForm(new FormUtilidadOp());                          
            } else if (index == 7) {                            
                if (subIndex == 1) {                   
                    Application.showForm(new FormCatalogo());
                } else if (subIndex == 2) {
                    Application.showForm(new FormProveedores());
                } else if (subIndex == 3) {
                    Application.showForm(new FormInventario());
                } else if (subIndex == 4) {
                    Application.showForm(new FormRInventario());
                } else {
                    action.cancel();
                }                                                
            } else if (index == 8) {            
                    Application.showForm(new FormConfirmarAcceso());                        
            } else if (index == 9) {                            
                if (subIndex == 1) {                   
                    Application.showForm(new FormHVentas());
                } else if (subIndex == 2) {
                    Application.showForm(new FormHCompra());
                } else if (subIndex == 3) {
                    Application.showForm(new FormHCaja());
                } else if (subIndex == 4) {
                    Application.showForm(new FormHUsuarios());
                } else {
                    action.cancel();
                }                         
            }else if (index == 10) {                            
                    Application.showForm(new FormProfile());                          
            }else if (index == 11) {
                
                Application.logout();
                logoutManager();                
                
            } else {
                action.cancel();
            }
        });
    }

    private void setMenuFull(boolean full) {
        String icon;
        if (getComponentOrientation().isLeftToRight()) {
            icon = (full) ? "menu_left.svg" : "menu_right.svg";
        } else {
            icon = (full) ? "menu_right.svg" : "menu_left.svg";
        }
        menuButton.setIcon(new FlatSVGIcon("appStructure/icon/svg/" + icon, 0.8f));
        menu.setMenuFull(full);
        revalidate();
    }

    public void hideMenu() {
        menu.hideMenuItem();
    }

    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void setSelectedMenu(int index, int subIndex, Usuario u) {
        menu.setSelectedMenu(index, subIndex, u);
    }

    private Menu menu;
    private JPanel panelBody;
    private JButton menuButton;

    private class MainFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int menuWidth = UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
                int menuX = ltr ? x : x + width - menuWidth;
                menu.setBounds(menuX, y, menuWidth, height);
                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                int menubX;
                if (ltr) {
                    menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
                } else {
                    menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
                }
                menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
                int gap = UIScale.scale(5);
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = ltr ? (x + menuWidth + gap) : x;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
            }
        }
    }
}
