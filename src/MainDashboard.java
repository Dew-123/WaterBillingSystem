import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainDashboard extends JFrame implements ActionListener {
    private JButton allCustomersButton;
    private JButton addCustomerButton;
    private JButton generateBillButton;

    public MainDashboard() {
        super("Main Dashboard");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        allCustomersButton = new JButton("All Customers Details");
        allCustomersButton.addActionListener(this);
        mainPanel.add(allCustomersButton);

        addCustomerButton = new JButton("Add New Customer");
        addCustomerButton.addActionListener(this);
        mainPanel.add(addCustomerButton);

        generateBillButton = new JButton("Generate a Bill");
        generateBillButton.addActionListener(this);
        mainPanel.add(generateBillButton);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == allCustomersButton) {
            // Open All Customers Details window
            AllCustomerDetails allCustomersDetailsWindow = new AllCustomerDetails();
            allCustomersDetailsWindow.setVisible(true);
        } else if (e.getSource() == addCustomerButton) {
            // Open Add New Customer window
            NewCustomer addNewCustomerWindow = new NewCustomer();
            addNewCustomerWindow.setVisible(true);
        } else if (e.getSource() == generateBillButton) {
            // Open Generate a Bill window
            CustomerDetails generateBillWindow = new CustomerDetails();
            generateBillWindow.setVisible(true);
        }
    }
}
