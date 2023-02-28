import java.util.Scanner;

public class ustawienia {
    static Scanner scanner = new Scanner(System.in);

    public static void zmienHaslo(String login, String haslo) {
        System.out.println("-----------------------");
        System.out.println("- Zmień hasło -");
        System.out.println("-----------------------");

        System.out.print("Podaj obecne hasło: ");
        String stareHaslo = scanner.nextLine();

        if (stareHaslo.equals(haslo)) {
            System.out.println("(Hasła powinny być różne)");
            System.out.print("Podaj nowe hasło: ");
            String noweHaslo = scanner.nextLine();

            if (!stareHaslo.equals(noweHaslo)) {
                String zapytanie = "UPDATE `konta` SET `haslo`='"+noweHaslo+"' WHERE login ='"+login+"'";
                ObslugaZapytan.executeQuery(zapytanie);

                System.out.println(" ");
                System.out.println("Zmieniono hasło");
                System.out.println("Zostaniesz wylogowany...");
                Logowanie.zaloguj();
                System.out.println(" ");
            } else {
                System.out.println("Hasła nie mogą być takie same");
                zmienHaslo(login, haslo);
            }

        } else {
            System.out.println("Błędne hasło, podaj nowe");
            zmienHaslo(login, haslo);
        }

    }
}
