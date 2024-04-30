import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GenerateBill extends JFrame implements ActionListener {
    private JTextField monthField, unitsField;
    private JLabel nameLabel, addressLabel, cityLabel, outstandingAmountLabel;
    private JLabel monthLabel, unitsLabel, unitCostLabel, totalAmountLabel;
    private JButton generateButton;

    private Double outstandingAmount;

    private int accNo;

    public GenerateBill(String name, String address, String city, double outstandingAmount, int accNo) {
        super("Bill Generator");

        this.outstandingAmount=outstandingAmount;
        this.accNo=accNo;

        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(11, 1, 10, 10)); // Changed grid rows to 11
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        monthLabel = new JLabel("Enter Current Month:");
        panel.add(monthLabel);
        monthField = new JTextField();
        panel.add(monthField);

        unitsLabel = new JLabel("Enter Units Consumed:");
        panel.add(unitsLabel);
        unitsField = new JTextField();
        panel.add(unitsField);

        nameLabel = new JLabel("Name: " + name);
        panel.add(nameLabel);

        addressLabel = new JLabel("Address: " + address);
        panel.add(addressLabel);

        cityLabel = new JLabel("City: " + city);
        panel.add(cityLabel);

        outstandingAmountLabel = new JLabel("Outstanding Amount: $" + outstandingAmount);
        panel.add(outstandingAmountLabel);

        unitCostLabel = new JLabel("Unit Cost: $10.00");
        panel.add(unitCostLabel);

        totalAmountLabel = new JLabel("Total Amount: ");
        panel.add(totalAmountLabel);

        generateButton = new JButton("Generate Bill");
        generateButton.addActionListener(this);
        panel.add(generateButton);

        add(panel, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == generateButton) {
            // Retrieve inputs
            String month = monthField.getText();
            String units = unitsField.getText();

            // Update total amount label
            double unitCost = 10.0; // Cost per unit
            double unitsConsumed = Double.parseDouble(units);
            double totalAmount = unitCost * unitsConsumed;

            double newOutstandingAmount = totalAmount+outstandingAmount;
            totalAmountLabel.setText("Total Amount: $" + totalAmount);

            try {
                connection c = new connection();
                String query = "UPDATE payment SET outstandingAmount ="+newOutstandingAmount+"WHERE accNo="+accNo;
                c.s.executeUpdate(query);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
