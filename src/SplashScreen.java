import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SplashScreen extends JWindow {

    public SplashScreen() {
        JLabel label = new JLabel("Water Billing System", SwingConstants.CENTER);
        JLabel madeByLabel = new JLabel("Made by Risandu Disanayake", SwingConstants.CENTER);

        label.setFont(new Font("Arial", Font.BOLD, 24)); // Font size for the main label
        madeByLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Font size for the secondary label

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(madeByLabel, BorderLayout.SOUTH);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setBackground(Color.WHITE);

        getContentPane().add(panel);

        // Set a larger fixed size for the splash screen
        setSize(400, 300);

        setLocationRelativeTo(null); // Center the splash screen
        setVisible(true);

        // Close the splash screen after 5 seconds
        Timer timer = new Timer(5000, e -> {
            dispose(); // Close the splash screen
            SwingUtilities.invokeLater(() -> new Login().setVisible(true)); // Open MainDashboard
        });
        timer.setRepeats(false); // Only fire once
        timer.start();
    }

    public static void main(String[] args) {
        new SplashScreen();
    }
}
