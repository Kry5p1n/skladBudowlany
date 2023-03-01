import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ZarzadzanieKontami {
    static Scanner scanner = new Scanner(System.in);

    public static void wyswietlPracownikow() {
        try {
            ResultSet resultPracownik = ObslugaZapytan.executeSelect("SELECT * FROM `konta`");

            while (resultPracownik.next()) {

                String table1 = resultPracownik.getString("id_konta");
                String table2 = resultPracownik.getString("login");
                String table3 = resultPracownik.getString("imie");
                String table4 = resultPracownik.getString("nazwisko");
                String table5 = resultPracownik.getString("stanowisko");
                String table6 = resultPracownik.getString("status_konta");

                System.out.println("Id. Pracownika: " + table1 + " - Login: " + table2 + " - Imię: " + table3 + " - Nazwisko: " + table4 + " - Pełnione stanowisko: " + table5 + " - Status konta: " + table6);

            }

            System.out.println(" ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dodajPracownika() {
        System.out.print("Podaj login: ");
        String login = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String haslo = scanner.nextLine();
        System.out.print("Podaj imię: ");
        String imie = scanner.nextLine();
        System.out.print("Podaj nazwisko: ");
        String nazwisko = scanner.nextLine();

        ObslugaZapytan.executeQuery("INSERT INTO `konta`(`login`, `haslo`, `imie`, `nazwisko`, `stanowisko`, `status_konta`) VALUES ('" + login + "', '" + haslo + "', '" + imie + "','" + nazwisko + "', 'pracownik', 'aktywne')");

        System.out.println(" ");
        System.out.println("Dodano pracownika");
        System.out.println(" ");
    }

    public static void zmianaStatusu() {
        wyswietlPracownikow();

        System.out.println(" ");
        System.out.print("Podaj login konta, którego rangę chcesz zmienić: ");
        String login = scanner.nextLine();

        try {
            ResultSet resultSelect = ObslugaZapytan.executeSelect("SELECT `id_konta`, `login`, `imie`, `stanowisko`, status_konta FROM `konta` WHERE login = '" + login + "'");

            while (resultSelect.next()) {

                String table1 = resultSelect.getString("id_konta");
                String table2 = resultSelect.getString("login");
                String table3 = resultSelect.getString("imie");
                String table4 = resultSelect.getString("stanowisko");
                String table5 = resultSelect.getString("status_konta");

                System.out.println("--");
                System.out.println("Id. Pracownika: " + table1 + " - Login: " + table2 + " - Imię: " + table3 + " - Pełnione stanowisko: " + table4 + " - Status konta: " + table5);
                System.out.println("--");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.print("Podaj nowy status (aktywne/zamkniete): ");
        String statusKonta = scanner.nextLine();

        String zapytanie = "UPDATE `konta` SET `status_konta`='" + statusKonta + "' WHERE `login` = '" + login + "'";
        ObslugaZapytan.executeQuery(zapytanie);
    }

}
