import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Logowanie.zaloguj();

//        obslugaZapytan.executeQuery("INSERT INTO `test`(`login`) VALUES ('xD')");
//
//        try {
//            ResultSet resultTest = obslugaZapytan.executeSelect("SELECT * FROM test");
//            while (resultTest.next()) {
//                String table1 = resultTest.getString("id");
//                String table2 = resultTest.getString("login");
//                System.out.println(table1 + " " + table2);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}