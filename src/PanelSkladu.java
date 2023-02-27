import java.util.Scanner;

public class PanelSkladu {
    static Scanner scanner = new Scanner(System.in);

    public static void ekranPowitalny(String login,String haslo, String imie, String stanowisko) {
        while (true) {
            System.out.println("-----------------------");
            System.out.println("Dzień dobry "+imie+"");
            System.out.println("Miłego dnia");
            System.out.println("-----------------------");
            System.out.println("-- Wybierz dział --");
            System.out.println("-----------------------");
            System.out.println("1. Zarządzanie asortymentem");
            System.out.println("2. Zarządzanie zamówieniami");
            if (stanowisko.equals("wlasciciel")) {
                System.out.println("3. Zarządzaj pracownikami");
                System.out.println("4. Sprawdź finanse");
            }
            System.out.println("5. Ustawienia");
            System.out.println(" ");
            System.out.println("6. Wyloguj");
            System.out.println(" ");
            System.out.print("Wybierz opcje: ");

            int choice = scanner.nextInt();

//            switch (choice) {
//                case 1 ->
//                case 2 ->
//                case 3 ->
//                case 4 ->
//                case 5 -> {
//                    System.out.println("----------------");
//                    System.out.println("- Ustawienia -");
//                    System.out.println("---------------");
//                    ustawienia.zmienHaslo(login, haslo);
//                    System.out.println("1. Wróć");
//                    System.out.print("Wybierz opcje: ");
//                    scanner.nextInt();
//                }
//                case 6 -> {
//                    System.out.println("Wylogowywanie");
//                    Logowanie.zaloguj();
//                }
//                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
//            }
        }
    }
}
