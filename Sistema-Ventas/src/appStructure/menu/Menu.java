package appStructure.menu;

import Database.DTO.Usuario;
import Utils.SVGImage;
import appStructure.menu.mode.LightDarkMode;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import appStructure.application.Application;
import appStructure.application.form.other.Ventas.FormCaja;
import appStructure.menu.mode.ToolBarAccentColor;

/**
 *
 * @author Samil
 */
public class Menu extends JPanel {

    Usuario u = null;
    
    private final String menuItems[][] = {
        {"~PRINCIPAL~"},
         {"Dashboard"}, //0
     {"~ÁREA DE VENTAS~"},
         {"Ventas", "Caja", "Facturación"}, //1
         {"Reporte de ventas"}, //2
         {"Reporte de productos"}, //4
     {"~ÁREA CONTABLE~"},
         {"Contabilidad", "Cuentas por Cobrar", "Cuentas por Pagar", "Gastos", "Ingresos"}, //5
         {"Cobros a clientes"}, //6
         {"Utilidad"}, //7
     {"~MANEJO DE PRODUCTOS~"},
         {"Productos","Catálogo","Proveedores", "Inventario","Rellenar Inventario"}, //8
     {"~SEGURIDAD~"},
         {"Accesos"}, //9
     {"~CENTRO DE INFORMACIÓN~"},
         {"Historial", "Ventas", "Compras", "Caja", "Usuarios"}, //10
     {"~OTROS~"},
         {"Perfil"}, //11
         {"Logout"} //12
    };

    public boolean isMenuFull() {
        return menuFull;
    }

    public void setMenuFull(boolean menuFull) {
        this.menuFull = menuFull;
        if (menuFull) {
            //header.setText(headerName);
            //header.setHorizontalAlignment(getComponentOrientation().isLeftToRight() ? JLabel.LEFT : JLabel.RIGHT);
        } else {
            //header.setText("");
            //header.setHorizontalAlignment(JLabel.CENTER);
        }
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).setFull(menuFull);
            }
        }
        lightDarkMode.setMenuFull(menuFull);
        toolBarAccentColor.setMenuFull(menuFull);
    }

    private final List<MenuEvent> events = new ArrayList<>();
    private boolean menuFull = true;

    protected final boolean hideMenuTitleOnMinimum = true;
    protected final int menuTitleLeftInset = 5;
    protected final int menuTitleVgap = 5;
    protected final int menuMaxWidth = 250;
    protected final int menuMinWidth = 60;
    protected final int headerFullHgap = 5;

    public Menu() {
        init();
    }
        public Menu(Usuario u) {
        init();
    }
    

    private void init() {
        setLayout(new MenuLayout());
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:20,2,2,2;"
                + "background:$Menu.background;"
                + "arc:10");
        
        header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header,BoxLayout.X_AXIS));
        
        SVGImage exitSVG = new SVGImage();
        SVGImage minimizeSVG = new SVGImage();

        minimizeSVG.setSvgImage("appStructure/icon/svg/yellow.svg", 21, 21, 1, 1);
        
        exitSVG.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FormCaja formCaja = FormCaja.getInstance();
                formCaja.forLogout();
                System.exit(0);
            }
        });

        minimizeSVG.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Application.minimizeWindow();
            }
        });


        // header.add(exitSVG);
        header.add(minimizeSVG);

        //  Menu
        scroll = new JScrollPane();
        panelMenu = new JPanel(new MenuItemLayout(this));
        panelMenu.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5;"
                + "background:$Menu.background");

        scroll.setViewportView(panelMenu);
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:null");
        JScrollBar vscroll = scroll.getVerticalScrollBar();
        vscroll.setUnitIncrement(10);
        vscroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "width:$Menu.scroll.width;"
                + "trackInsets:$Menu.scroll.trackInsets;"
                + "thumbInsets:$Menu.scroll.thumbInsets;"
                + "background:$Menu.ScrollBar.background;"
                + "thumb:$Menu.ScrollBar.thumb");
        createMenu();
        
        lightDarkMode = new LightDarkMode();
        toolBarAccentColor = new ToolBarAccentColor(this);
        
        add(header);
        add(scroll);
        // add(lightDarkMode);
        // add(toolBarAccentColor);
    }

    private void createMenu() {
        int index = 0;
        for (int i = 0; i < menuItems.length; i++) {
            String menuName = menuItems[i][0];
            if (menuName.startsWith("~") && menuName.endsWith("~")) {
                panelMenu.add(createTitle(menuName));
            } else {
                MenuItem menuItem = new MenuItem(this, menuItems[i], index++, events);
                panelMenu.add(menuItem);
            }
        }
    }

    private JLabel createTitle(String title) {
        String menuName = title.substring(1, title.length() - 1);
        JLabel lbTitle = new JLabel(menuName);
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$Menu.label.font;"
                + "foreground:$Menu.title.foreground");
        return lbTitle;
    }

    public void setSelectedMenu(int index, int subIndex, Usuario u) {
        runEvent(index, subIndex);
    }

    protected void setSelected(int index, int subIndex) {
        int size = panelMenu.getComponentCount();
        for (int i = 0; i < size; i++) {
            Component com = panelMenu.getComponent(i);
            if (com instanceof MenuItem) {
                MenuItem item = (MenuItem) com;
                if (item.getMenuIndex() == index) {
                    item.setSelectedIndex(subIndex);
                } else {
                    item.setSelectedIndex(-1);
                }
            }
        }
    }

    protected void runEvent(int index, int subIndex) {
        MenuAction menuAction = new MenuAction();
        for (MenuEvent event : events) {
            event.menuSelected(index, subIndex, menuAction);
        }
        if (!menuAction.isCancel()) {
            setSelected(index, subIndex);
        }
    }

    public void addMenuEvent(MenuEvent event) {
        events.add(event);
    }

    public void hideMenuItem() {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).hideMenuItem();
            }
        }
        revalidate();
    }

    public boolean isHideMenuTitleOnMinimum() {
        return hideMenuTitleOnMinimum;
    }

    public int getMenuTitleLeftInset() {
        return menuTitleLeftInset;
    }

    public int getMenuTitleVgap() {
        return menuTitleVgap;
    }

    public int getMenuMaxWidth() {
        return menuMaxWidth;
    }

    public int getMenuMinWidth() {
        return menuMinWidth;
    }

    private JPanel header;
    private JScrollPane scroll;
    private JPanel panelMenu;
    private LightDarkMode lightDarkMode;
    private ToolBarAccentColor toolBarAccentColor;

    private class MenuLayout implements LayoutManager {

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
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int gap = UIScale.scale(0);
                int sheaderFullHgap = UIScale.scale(headerFullHgap);
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int iconWidth = width;
                int iconHeight = header.getPreferredSize().height;
                int hgap = menuFull ? sheaderFullHgap : 0;
                header.setBounds(20,12,40,20); // ADD

                int ldgap = UIScale.scale(0);
                int ldWidth = width - ldgap * 2;
                int ldHeight = lightDarkMode.getPreferredSize().height;
                int ldx = x + ldgap;
                int ldy = y + height - ldHeight - ldgap - gap - toolBarAccentColor.getPreferredSize().height;

                int menux = x;
                int menuy = y + iconHeight + gap;
                int menuWidth = width;
                int menuHeight = height - (iconHeight + gap) - (ldHeight + ldgap * 2) - (toolBarAccentColor.getPreferredSize().height + gap);
                scroll.setBounds(menux, menuy, menuWidth, menuHeight);

                // lightDarkMode.setBounds(ldx, ldy, ldWidth, ldHeight);

                int tbheight = toolBarAccentColor.getPreferredSize().height;
                int tbwidth = Math.min(toolBarAccentColor.getPreferredSize().width, ldWidth);
                int tby = y + height - tbheight - ldgap;
                int tbx = ldx + ((ldWidth - tbwidth) / 2);
                // toolBarAccentColor.setBounds(tbx, tby, tbwidth, tbheight);
            }
        }
    }
}
