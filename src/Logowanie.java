import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Logowanie {
    static Scanner scanner = new Scanner(System.in);
    public static void zaloguj() {

        try {
            System.out.println("|***********************|");
            System.out.println("|Firma Budowlana KrysBud|");
            System.out.println("|***********************|");
            System.out.print("Login: ");
            String loginUSER = scanner.nextLine();
            System.out.print("Hasło: ");
            String hasloUSER = scanner.nextLine();

            ResultSet result = ObslugaZapytan.executeSelect("SELECT * FROM `konta` WHERE login='" + loginUSER + "'");

            if (result.next()) {
                int nrPracownika = result.getInt("id_konta");
                String login = result.getString("login");
                String haslo = result.getString("haslo");
                String imie = result.getString("imie");
                String stanowisko = result.getString("stanowisko");
                String statusKonta = result.getString("status_konta");

                if (loginUSER.equals(login) && hasloUSER.equals(haslo)) {
                    if (statusKonta.equals("aktywne")) {
                        switch (stanowisko) {

                            case "wlasciciel", "pracownik" -> PanelSkladu.ekranPowitalny(login, haslo, imie, stanowisko, nrPracownika);

                        }
                    } else {
                        System.out.println("Twoje konto jest nieaktywne");
                        zaloguj();
                    }
                } else {
                    System.out.println("Hasło nieprawidłowe");
                    zaloguj();
                }
            } else {
                System.out.println("Niepoprawny login lub hasło");
                zaloguj();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
