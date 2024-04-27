import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class connection {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/waterbillingsystem";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    Connection connection;
    Statement s;

    public connection() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            s = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
