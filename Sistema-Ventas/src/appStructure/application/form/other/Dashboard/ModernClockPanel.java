package appStructure.application.form.other.Dashboard;

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
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setForeground(Color.WHITE);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(Color.WHITE);
        fechaPanel.setBackground(new Color(49, 62, 74));

        fechaPanel.add(dateLabel, BorderLayout.NORTH);
        fechaPanel.add(timeLabel, BorderLayout.CENTER);

        add(fechaPanel, BorderLayout.CENTER);

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        updateDateTime();
    }

    private void updateDateTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd 'de' MMMM, yyyy");

        String currentTime = timeFormat.format(new Date());
        String currentDate = dateFormat.format(new Date());

        timeLabel.setText(currentTime);
        dateLabel.setText(currentDate);
    }
}
