import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PolaczenieMysql {
    private static String URL = "jdbc:mysql://localhost:3306/skladbudowlany";
    private static String user = "root";
    private static String pass = "";

    public static Connection connect(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}