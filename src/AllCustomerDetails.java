import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AllCustomerDetails extends JFrame implements ActionListener{

    JTable t1;
    String x[] = {"Account No","Customer Name","Meter No","Address","City","Email","Mobile Number"};
    String y[][] = new String[50][7];
    int i=0, j=0;
    AllCustomerDetails(){
        super("Customer Details");
        setSize(1200,650);
        setLocation(200,200);

        try{
            connection c1  = new connection();
            String s1 = "select * from customer";
            ResultSet rs  = c1.s.executeQuery(s1);
            while(rs.next()){
                y[i][j++]=rs.getString("accNo");
                y[i][j++]=rs.getString("customerName");
                y[i][j++]=rs.getString("meterNo");
                y[i][j++]=rs.getString("address");
                y[i][j++]=rs.getString("city");
                y[i][j++]=rs.getString("email");
                y[i][j++]=rs.getString("mobileNo");
                i++;
                j=0;
            }
            t1 = new JTable(y,x);

        }catch(Exception e){
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(t1);
        add(sp);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            t1.print();
        }catch(Exception e){}
    }

    public static void main(String[] args){
        new AllCustomerDetails().setVisible(true);
    }

}