import java.util.Scanner;

public class PanelSkladu {
    static Scanner scanner = new Scanner(System.in);

    public static void ekranPowitalny(String login,String haslo, String imie, String stanowisko) {
        while (true) {
            System.out.println("-----------------------");
            System.out.println("Dzień dobry "+imie+"");
            System.out.println("|*****************|");
            System.out.println("|- KrysBud Panel -|");
            System.out.println("|*****************|");
            System.out.println("1. Zarządzanie asortymentem");
            System.out.println("2. Zarządzanie zamówieniami");
            if (stanowisko.equals("wlasciciel")) {
                System.out.println("3. Zarządzaj pracownikami");
            }
            System.out.println("4. Ustawienia");
            System.out.println(" ");
            System.out.println("5. Wyloguj");
            System.out.println(" ");
            System.out.print("Wybierz opcje: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> magazyn(login, haslo, imie, stanowisko);
                case 2 -> zamowienia(login, haslo, imie, stanowisko);
                case 3 -> {
                    if (stanowisko.equals("wlasciciel")) {
                        pracownicy(login, haslo, imie, stanowisko);
                    } else {
                        System.out.println("Nie masz uprawnień");
                    }
                }
                case 4 -> {
                    System.out.println("***************");
                    System.out.println("- Ustawienia -");
                    System.out.println("***************");
                    ustawienia.zmienHaslo(login, haslo);
                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 5 -> {
                    System.out.println("Wylogowywanie");
                    Logowanie.zaloguj();
                }
                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    public static void magazyn(String login, String haslo ,String imie,String stanowisko) {

        while (true) {
            System.out.println("***********************");
            System.out.println("-- Co chcesz zrobić --");
            System.out.println("***********************");
            System.out.println("1. Wyświetl produkty");
            System.out.println("2. Dodaj nowy produkt");
            System.out.println("3. Usuń produkt");
            System.out.println("4. Zamień cenę produktu");
            System.out.println(" ");
            System.out.println("5. Wróć");

            System.out.print("Wybierz opcje: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("-----------------------");
                    System.out.println("- Wyświetl produkty -");
                    System.out.println("-----------------------");

                    Asortyment.wyswietlProdukty();

                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 2 -> {
                    System.out.println("-----------------------");
                    System.out.println("- Dodaj nowy produkt -");
                    System.out.println("-----------------------");

                    Asortyment.dodajProdukt();

                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 3 -> {
                    System.out.println("-----------------------");
                    System.out.println("- Usuń produkt -");
                    System.out.println("-----------------------");

                    Asortyment.usunProdukt();

                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 4 -> {
                    System.out.println("-----------------------");
                    System.out.println("- Zamień cenę produktu -");
                    System.out.println("-----------------------");

                    Asortyment.zmienCeneProduktu();

                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 5 -> ekranPowitalny(login,haslo,imie,stanowisko);
                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
    public static void zamowienia(String login, String haslo ,String imie,String stanowisko) {

        while (true) {
            System.out.println("***********************");
            System.out.println("-- Co chcesz zrobić --");
            System.out.println("***********************");
            System.out.println("1. Dodaj zamówienie");
            System.out.println("2. Edytuj zamówienie");
            System.out.println("3. Zmień status zamówienia");
            System.out.println("4. Wyświetl historię zamówień");
            System.out.println(" ");
            System.out.println("5. Wróć");

            System.out.print("Wybierz opcje: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("-----------------------");
                    System.out.println("- Dodaj zamówienie -");
                    System.out.println("-----------------------");

                    Zamowienia.dodajZamowienie();

                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 2 -> {
                    System.out.println("-----------------------");
                    System.out.println("- Edytuj zamówienie -");
                    System.out.println("-----------------------");

                    Zamowienia.edytujZamowienie();

                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 3 -> {
                    System.out.println("---------------------------");
                    System.out.println("- Zmień status zamówienia -");
                    System.out.println("---------------------------");

                    Zamowienia.zmienStatusZamowienia();

                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 4 -> {
                    System.out.println("------------------------------");
                    System.out.println("- Wyświetl historię zamówień -");
                    System.out.println("------------------------------");

                    Zamowienia.wyswietlHistorieZamowien();

                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 5 -> ekranPowitalny(login,haslo,imie,stanowisko);
                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
    public static void pracownicy(String login, String haslo ,String imie,String stanowisko) {

        while (true) {
            System.out.println("***********************");
            System.out.println("-- Co chcesz zrobić --");
            System.out.println("***********************");
            System.out.println("1. Wyświetl pracowników");
            System.out.println("2. Dodaj nowego pracownika");
            System.out.println("3. Zmień status konta pracownika");
            System.out.println(" ");
            System.out.println("4. Wróć");

            System.out.print("Wybierz opcje: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("-----------------------");
                    System.out.println("- Wyświetl pracowników -");
                    System.out.println("-----------------------");

                    ZarzadzanieKontami.wyswietlPracownikow();

                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 2 -> {
                    System.out.println("-----------------------");
                    System.out.println("- Dodaj nowego pracownika -");
                    System.out.println("-----------------------");

                    ZarzadzanieKontami.dodajPracownika();

                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 3 -> {
                    System.out.println("---------------------------------");
                    System.out.println("- Zmień status konta pracownika -");
                    System.out.println("---------------------------------");

                    ZarzadzanieKontami.zmianaStatusu();

                    System.out.println("1. Wróć");
                    System.out.print("Wybierz opcje: ");
                    scanner.nextInt();
                }
                case 4 -> ekranPowitalny(login,haslo,imie,stanowisko);
                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
}
