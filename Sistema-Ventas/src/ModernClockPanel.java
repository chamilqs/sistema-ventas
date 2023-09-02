import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModernClockPanel extends JPanel {
    private JLabel timeLabel;
    private JLabel dateLabel;

    public ModernClockPanel() {
        setLayout(new BorderLayout());

        JPanel fechaPanel = new JPanel(new BorderLayout());

        dateLabel = new JLabel();
         //System.out.println("llego");
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setForeground(new Color(24, 39, 72));

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(new Color(24, 39, 72));
        fechaPanel.setBackground(Color.white);

        fechaPanel.add(dateLabel, BorderLayout.NORTH);
        fechaPanel.add(timeLabel, BorderLayout.CENTER);

        add(fechaPanel, BorderLayout.CENTER);

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        updateDateTime();
    }

    private void updateDateTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");

        String currentTime = timeFormat.format(new Date());
        String currentDate = dateFormat.format(new Date());

        timeLabel.setText(currentTime);
        dateLabel.setText(currentDate);
    }

//    public static void main(String[] args) {
//        FlatLightLaf.install(); // Instalar el tema FlatLaf
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Modern Clock Panel");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(310, 420);
//            
//            ModernClockPanel clockPanel = new ModernClockPanel();
//            frame.add(clockPanel);
//            
//            frame.setVisible(true);
//        });
//    }
}
