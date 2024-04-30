import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerDetails extends JFrame implements ActionListener {
    JTextField textField;
    JButton submitButton, cancelButton;

    CustomerDetails() {
        super("Meter Number");
        setLocationRelativeTo(null);
        setSize(300, 150);

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // Create form panel with GridLayout
        JPanel formPanel = new JPanel(new GridLayout(2, 1));
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create JLabel for prompt
        JLabel promptLabel = new JLabel("Enter Customer Meter Number:");
        formPanel.add(promptLabel);

        // Create JTextField for user input
        textField = new JTextField();
        formPanel.add(textField);

        // Create button panel with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));

        // Create buttons
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        // Add buttons to button panel
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        // Add form panel and button panel to main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            int customerMeterNo = Integer.parseInt(textField.getText());
            JOptionPane.showMessageDialog(this, "Customer ID entered: " + customerMeterNo);
            displayCustomerDetails(String.valueOf(customerMeterNo));
        } else if (ae.getSource() == cancelButton) {
            dispose();
        }
    }

    public void displayCustomerDetails(String customerMeterNo){
        String query = "SELECT * FROM customer c JOIN payment p ON c.accNo = p.accNo WHERE c.meterNo = " + customerMeterNo;
        try {
            connection connection = new connection();
            ResultSet results = connection.s.executeQuery(query);
            if (results.next()) {
                String accNo = results.getString("accNo");
                String customerName = results.getString("customerName");
                String meterNo = results.getString("meterNo");
                String address = results.getString("address");
                String city = results.getString("city");
                String mobileNo = results.getString("mobileNo");
                double outstandingAmount = results.getDouble("outstandingAmount");
                int isConnectionDisconnected = results.getInt("isConnectionDisconnected");

                //System.out.println(accNo + customerName + meterNo + address + city + mobileNo);
                // Create a new instance of CustomerDetailsWindow
                CustomerDetailsWindow detailsWindow = new CustomerDetailsWindow(accNo, customerName, meterNo, address, city, mobileNo, outstandingAmount, isConnectionDisconnected);
                setVisible(false);
                detailsWindow.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found!");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new CustomerDetails().setVisible(true));
    }
}
