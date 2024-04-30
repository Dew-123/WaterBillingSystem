import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerDetailsWindow extends JFrame implements ActionListener {
    private JButton calculateBillButton;
    private JButton cancelButton;

    public CustomerDetailsWindow(String accNo, String customerName, String meterNo, String address, String city, String mobileNo, double outstandingAmount, int isConnectionDisconnected) {
        super("Customer Details");
        setSize(300, 350); // Increased height to accommodate the buttons

        JPanel detailsPanel = new JPanel(new GridLayout(8, 1));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel accNoLabel = new JLabel("Account No: " + accNo);
        JLabel meterNoLabel = new JLabel("Meter No: " + meterNo);
        JLabel nameLabel = new JLabel("Name: " + customerName);
        JLabel addressLabel = new JLabel("Address: " + address);
        JLabel cityLabel = new JLabel("City: " + city);
        JLabel mobileNoLabel = new JLabel("Mobile No: " + mobileNo);
        JLabel outstandingAmountLabel = new JLabel("Outstanding Amount: " + outstandingAmount);
        JLabel connectionStatusLabel = new JLabel("Connection Status: " + (isConnectionDisconnected == 1 ? "Disconnected" : "Connected"));

        detailsPanel.add(accNoLabel);
        detailsPanel.add(meterNoLabel);
        detailsPanel.add(nameLabel);
        detailsPanel.add(addressLabel);
        detailsPanel.add(cityLabel);
        detailsPanel.add(mobileNoLabel);
        detailsPanel.add(outstandingAmountLabel);
        detailsPanel.add(connectionStatusLabel);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Create buttons
        calculateBillButton = new JButton("Calculate Bill");
        cancelButton = new JButton("Cancel");

        // Add action listeners to buttons
        calculateBillButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // Add buttons to button panel
        buttonPanel.add(calculateBillButton);
        buttonPanel.add(cancelButton);

        // Add detailsPanel and buttonPanel to the frame
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateBillButton) {

            // Implement the logic to calculate the bill

        } else if (e.getSource() == cancelButton) {

            dispose();
        }
    }
}
