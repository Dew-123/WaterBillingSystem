import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    private JLabel labelUsername, labelPassword, labelImage;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton;

    public Login() {
        super("Login");

        // Main Panel with rounded corners
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(230, 230, 230)); // Light gray background
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true)); // Black border

        // Username & Password Fields with labels
        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Padding around text
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        fieldPanel.add(new JLabel("Username:"));
        fieldPanel.add(usernameField);
        fieldPanel.add(new JLabel("Password:"));
        fieldPanel.add(passwordField);
        fieldPanel.setBackground(new Color(230, 230, 230)); // Light gray background for fields

        // Login & Cancel Buttons with styling
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(Color.BLUE); // Blue button
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setBorder(BorderFactory.createRaisedBevelBorder()); // Raised border
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBorder(BorderFactory.createRaisedBevelBorder());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setBackground(new Color(230, 230, 230)); // Light gray background for buttons

        // Add components to main panel & set layout
        mainPanel.add(Box.createVerticalStrut(20)); // Spacing above fields
        mainPanel.add(fieldPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Spacing between fields and buttons
        mainPanel.add(buttonPanel);
        add(mainPanel, BorderLayout.CENTER);

        // Action Listeners & other settings
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) { // Login button clicked
            try {
                connection connection = new connection();

                // Retrieve username and password from text fields
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    String query = "SELECT * FROM employee WHERE username = '" + username + "' AND password = '" + password + "'";
                    ResultSet resultSet = connection.s.executeQuery(query);
                    if (resultSet.next()) {
                        // Login successful
                        JOptionPane.showMessageDialog(this, "Login Successful!");
                        new MainDashboard().setVisible(true);
                        dispose(); // Close the login window

                    } else {
                        // Login failed
                        JOptionPane.showMessageDialog(this, "Invalid username or password!");
                    }
                    resultSet.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage());
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == cancelButton) { // Cancel button clicked
            // Clear text fields
            usernameField.setText("");
            passwordField.setText("");
        }
    }
}
