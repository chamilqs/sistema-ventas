
package Utils;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Angelo
 */

public class Utils {
    // Método para cambiar el contenido del contenedor por un nuevo JPanel
    
    private static final String rutaBase = "src\\imgnew\\";
     
    public static void showPane(JPanel container, JPanel panel) {
        container.removeAll();
        container.add(panel, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }
    public static void setScaledImage(JLabel lblImage, String imageName, int width, int height) {
        ImageIcon img = new ImageIcon(Utils.class.getResource("/imgnew/" + imageName));
        Image scaledImage = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(scaledImage);
        lblImage.setIcon(icon);
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
   public static class UniqueIDGenerator {
    private static final String CHAIN = "123456789";
    private static final int LENGTH = 12;
    private static final int MAX_ATTEMPTS = 1000; // Maximum attempts to generate a unique ID

    private static Set<String> usedIDs = new HashSet<>();

    public static String generateUniqueID() {
        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < LENGTH; i++) {
                double random = Math.random() * CHAIN.length();
                int posicion = (int) random;
                char letter = CHAIN.charAt(posicion);
                sb.append(letter);
            }
            String newID = sb.toString();

            if (!usedIDs.contains(newID)) {
                usedIDs.add(newID);
                return newID;
            }
        }
        throw new RuntimeException("Unable to generate a unique ID after " + MAX_ATTEMPTS + " attempts.");
    }
}

    public static class HistorialDeNavegacion {
        private Stack<String> historial;

        public HistorialDeNavegacion() {
            historial = new Stack<>();
        }

        public void guardarAccion(String accion) {
            historial.push(accion);
        }

        public String obtenerAccionAnterior() {
            if (!historial.isEmpty()) {
                return historial.pop();
            }
            return null;
        }
    }
           
}
    
    

