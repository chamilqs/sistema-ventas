package appStructure.application;

import Database.DTO.Usuario;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import appStructure.application.form.LoginForm;
import appStructure.application.form.MainForm;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import raven.toast.Notifications;

/**
 *
 * @author Samil
 */
public class Application extends javax.swing.JFrame {

    private static Application app;
    private final MainForm mainForm;
    private final LoginForm loginForm;
    static Usuario u = null;
        
    private static final String LOCK_FILE_PATH = "app.lock";
    private static File lockFile;
     private static final int PORT = 12345;
    private static ServerSocket serverSocket;

    public Application() {
        initComponents();
        setSize(new Dimension(1200, 768));
        setLocationRelativeTo(null);
        mainForm = new MainForm();
        loginForm = new LoginForm();
        setContentPane(loginForm);
        Notifications.getInstance().setJFrame(this);
        
        // Intenta crear el archivo de bloqueo
        lockFile = new File(LOCK_FILE_PATH);
        try {
            if (lockFile.createNewFile()) {
                // El archivo de bloqueo se creó correctamente, lo que significa que esta instancia es la única en ejecución
            } else {
                // El archivo de bloqueo ya existe, lo que significa que otra instancia está en ejecución
                System.err.println("La aplicación ya está en ejecución.");
                System.exit(1); // Salir de la aplicación
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public Application(Usuario u) {
        initComponents();
        this.u = u;
        setSize(new Dimension(1200, 768));
        setLocationRelativeTo(null);
        mainForm = new MainForm(u);
        loginForm = new LoginForm(u);
        setContentPane(loginForm);
        Notifications.getInstance().setJFrame(this);
    }

    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
    }

    public static void login() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.mainForm);
        app.mainForm.applyComponentOrientation(app.getComponentOrientation());
        setSelectedMenu(0, 0);
        app.mainForm.hideMenu();
        SwingUtilities.updateComponentTreeUI(app.mainForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void logout() {
        
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.loginForm);
        app.loginForm.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.loginForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
        
    }
       
    public static void setSelectedMenu(int index, int subIndex) {
        app.mainForm.setSelectedMenu(index, subIndex, u);
    }
    
    public static void minimizeWindow() {
        app.setState(JFrame.ICONIFIED);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.exit(1); 
        }
        
        FlatLaf.registerCustomDefaultsSource("appStructure.theme");
        FlatDarculaLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            app = new Application();
            //  app.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            app.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
