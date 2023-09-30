package Utils;

import Database.Connect.ConexionSingleton;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 *
 * @author Samil
 */

public class Tools {
    
    static ConexionSingleton  c = ConexionSingleton.obtenerInstancia(); 
    
    public static void showPane(JPanel container, JPanel panel) {
        container.removeAll();
        container.add(panel, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }
    public static void setScaledImage(JLabel lblImage, String imageName, int width, int height) {
        ImageIcon img = new ImageIcon(Tools.class.getResource("/imgnew/" + imageName));
        Image scaledImage = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(scaledImage);
        lblImage.setIcon(icon);
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
            
       public static class UniqueIDGenerator2 {
    private static final String CHAIN = "1234567890ABCDEFGHIJKMLNOPQRSTUVWXYZ";
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
    public static boolean dbCheck(String id) {
        String consultaSQL = "SELECT COUNT(*) FROM detalleventa WHERE idVenta = ?";

        try (Connection conectar = c.getConexion();
             PreparedStatement pst = conectar.prepareStatement(consultaSQL)) {

            pst.setString(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al verificar la existencia en la base de datos", ex);
        }

        return false;
    }

       
public class UniqueIDGenerator {
    private static char currentChar = 'A';
    private static int currentNumber = 1;
    private static Set<String> usedIDs = new HashSet<>();
    
    private static final char FIRST_CHAR = 'A';
    private static final char LAST_CHAR = 'Z';
    private static final int MAX_NUMBER = 9999999;

    public static String generateUniqueID() {
        while (true) {
            String id = String.format("%c%06d", currentChar, currentNumber);

            if (!isIDUsed(id) && !dbCheck(id)) {
                usedIDs.add(id);
                incrementCurrentNumber();

                if (currentNumber > MAX_NUMBER) {
                    currentNumber = 1;
                    incrementCurrentChar();
                }

                return id;
            }

            incrementCurrentNumber();

            if (currentNumber > MAX_NUMBER) {
                currentNumber = 1;
                incrementCurrentChar();
            }
        }
    }

    private static boolean isIDUsed(String id) {
        return usedIDs.contains(id);
    }

    private static void incrementCurrentChar() {
        if (currentChar == LAST_CHAR) {
            currentChar = FIRST_CHAR;
        } else {
            currentChar++;
        }
    }

    private static void incrementCurrentNumber() {
        currentNumber++;
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
    
    

