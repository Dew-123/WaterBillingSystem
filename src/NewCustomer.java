import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class NewCustomer extends JFrame implements ActionListener {
    JTextField t1, t2, t3, t4, t5, t6;
    JButton b1, b2;

    NewCustomer() {
        super("New Customer Form");
        setLocationRelativeTo(null); // Center the frame on the screen
        setSize(400, 500);

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // Create form panel with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add form components
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name"), gbc);

        gbc.gridx = 1;
        t1 = new JTextField(20);
        formPanel.add(t1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Meter No"), gbc);

        gbc.gridx = 1;
        t2 = new JTextField(20);
        formPanel.add(t2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Address"), gbc);

        gbc.gridx = 1;
        t3 = new JTextField(20);
        formPanel.add(t3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("City"), gbc);

        gbc.gridx = 1;
        t4 = new JTextField(20);
        formPanel.add(t4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Email"), gbc);

        gbc.gridx = 1;
        t5 = new JTextField(20);
        formPanel.add(t5, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Mobile Number"), gbc);

        gbc.gridx = 1;
        t6 = new JTextField(20);
        formPanel.add(t6, gbc);

        // Create button panel with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));

        // Create buttons
        b1 = new JButton("Submit");
        b1.setBackground(new Color(59, 89, 182));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);

        b2 = new JButton("Cancel");
        b2.setBackground(new Color(59, 89, 182));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);

        // Add buttons to button panel
        buttonPanel.add(b1);
        buttonPanel.add(b2);

        // Add form panel and button panel to main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to the frame
        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        String name = t1.getText();
        String meterNo = t2.getText();
        String address = t3.getText();
        String city = t4.getText();
        String email = t5.getText();
        String phoneNo = t6.getText();

        String query = "INSERT INTO customer(customerName, meterNo, address, city, email, mobileNo) " +
                "VALUES ('" + name + "', '" + meterNo + "', '" + address + "', '" + city + "', '" + email + "', '" + phoneNo + "')";

        try {
            connection connection = new connection();
            connection.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"New Customer Added !");
            this.setVisible(false);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
