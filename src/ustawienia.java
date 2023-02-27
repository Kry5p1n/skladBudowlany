import java.util.Scanner;

public class ustawienia {
    static Scanner scanner = new Scanner(System.in);

    public static void zmienHaslo(String loginDB, String hasloDB) {
        System.out.println("-----------------------");
        System.out.println("- Zmień hasło -");
        System.out.println("-----------------------");

        System.out.print("Podaj obecne hasło: ");
        String stareHaslo = scanner.nextLine();

        if (stareHaslo.equals(hasloDB)) {
            System.out.println("(Hasła powinny być różne)");
            System.out.print("Podaj nowe hasło: ");
            String noweHaslo = scanner.nextLine();

            if (!stareHaslo.equals(noweHaslo)) {
                String zapytanie = "UPDATE `konta` SET `haslo`='"+noweHaslo+"' WHERE login ='"+loginDB+"'";
                ObslugaZapytan.executeQuery(zapytanie);

                System.out.println(" ");
                System.out.println("Zmieniono hasło");
                System.out.println("Zostaniesz wylogowany...");
                Logowanie.zaloguj();
                System.out.println(" ");
            } else {
                System.out.println("Hasła nie mogą być takie same");
                zmienHaslo(loginDB, hasloDB);
            }

        } else {
            System.out.println("Błędne hasło, podaj nowe");
            zmienHaslo(loginDB, hasloDB);
        }

    }
}
