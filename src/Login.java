import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel labelUsername, labelPassword, labelImage;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, cancelButton;
    JPanel inputPanel, buttonPanel;

    public Login() {
        super("Login Page");

        // Labels
        labelUsername = new JLabel("Username");
        labelPassword = new JLabel("Password");

        // Text Fields
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        // Buttons
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");

        // Add ActionListener to buttons
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // Image Label
        labelImage = new JLabel();

        // Create input panel
        inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(labelUsername);
        inputPanel.add(usernameField);
        inputPanel.add(labelPassword);
        inputPanel.add(passwordField);
        inputPanel.setBackground(Color.WHITE);

        // Create button panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setBackground(Color.WHITE);

        // Set layout and add components
        setLayout(new BorderLayout());
        add(labelImage, BorderLayout.WEST);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set frame properties
        setSize(640, 450);
        setLocation(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
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
                        new NewCustomer().setVisible(true);
                        this.setVisible(false);

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
