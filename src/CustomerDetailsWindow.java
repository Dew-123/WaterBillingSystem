import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerDetailsWindow extends JFrame implements ActionListener {
    private JButton calculateBillButton;
    private JButton cancelButton;
    private String name;
    private String address;
    private String city;
    private double outstandingAmount;

    private int accNo;

    public CustomerDetailsWindow(String accNo, String customerName, String meterNo, String address, String city, String mobileNo, Double outstandingAmount, int isConnectionDisconnected) {
        super("Customer Details");
        setSize(300, 400); // Increased height to accommodate the buttons and additional label

        this.name = customerName;
        this.address = address;
        this.city = city;
        this.outstandingAmount = outstandingAmount;
        this.accNo= Integer.parseInt(accNo);

        JPanel detailsPanel = new JPanel(new GridLayout(9, 1)); // Increased rows to accommodate the additional label
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel accNoLabel = new JLabel("Account No: " + accNo);
        JLabel meterNoLabel = new JLabel("Meter No: " + meterNo);
        JLabel nameLabel = new JLabel("Name: " + customerName);
        JLabel addressLabel = new JLabel("Address: " + address);
        JLabel cityLabel = new JLabel("City: " + city);
        JLabel mobileNoLabel = new JLabel("Mobile No: " + mobileNo);
        JLabel outstandingAmountLabel = new JLabel("Outstanding Amount: " + outstandingAmount);
        JLabel connectionStatusLabel = new JLabel("Connection Status: " + (isConnectionDisconnected == 1 ? "Disconnected" : "Connected"));
        JLabel billStatusLabel = new JLabel();
        // Check if outstandingAmount is not null
        if (outstandingAmount != 0.0) {
            billStatusLabel = new JLabel("Bill Overdue");
            billStatusLabel.setForeground(Color.RED); // Set the text color to red
        }

        // Add labels to detailsPanel
        detailsPanel.add(accNoLabel);
        detailsPanel.add(meterNoLabel);
        detailsPanel.add(nameLabel);
        detailsPanel.add(addressLabel);
        detailsPanel.add(cityLabel);
        detailsPanel.add(mobileNoLabel);
        detailsPanel.add(outstandingAmountLabel);
        detailsPanel.add(connectionStatusLabel);
        detailsPanel.add(billStatusLabel);

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
            SwingUtilities.invokeLater(() -> new GenerateBill(name, address, city, outstandingAmount, accNo).setVisible(true));
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }
}
