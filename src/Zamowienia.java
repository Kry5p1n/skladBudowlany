import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Zamowienia {
    static Scanner scanner = new Scanner(System.in);

    public static void dodajZamowienie(int nrPracownika) {
        Asortyment.wyswietlProdukty();
        System.out.println(" ");

        System.out.print("Id towaru: ");
        String value2 = scanner.nextLine();
        System.out.print("Ilość sztuk: ");
        int value3 = scanner.nextInt();
        try {
            ResultSet resultTest = ObslugaZapytan.executeSelect("SELECT * FROM produkty");
            while (resultTest.next()) {
                int table1 = resultTest.getInt("ilosc");

                if (value3 > table1) {
                    System.out.println("Brak wystarczającej ilości produktu.");
                    scanner.nextLine();
                    dodajZamowienie(nrPracownika);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Do zapłaty: ");
        int value4 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Rodzaj płatności: ");
        String value5 = scanner.nextLine();
        System.out.print("Rodzaj odbioru: ");
        String value6 = scanner.nextLine();
        System.out.print("Imię klienta: ");
        String value7 = scanner.nextLine();
        System.out.print("Nazwisko klienta: ");
        String value8 = scanner.nextLine();
        System.out.print("Numer telefonu: ");
        int value9 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Adres doręczenia: ");
        String value10 = scanner.nextLine();
        System.out.print("Status zamówienia: ");
        String value11 = scanner.nextLine();

        ObslugaZapytan.executeQuery("INSERT INTO `zamowienia`(`id_pracownika`, `id_towaru`, `ilosc`, `cena`, `rodzaj_platnosci`, `rodzaj_odbioru`, `imie_klienta`, `nazwisko_klienta`, `numer_telefonu`, `adres_doreczenia`, `status_zamowienia`) VALUES ('"+nrPracownika+"', '"+value2+"', '"+value3+"', '"+value4+"', '"+value5+"', '"+value6+"', '"+value7+"', '"+value8+"', '"+value9+"', '"+value10+"', '"+value11+"')");
        ObslugaZapytan.executeQuery("UPDATE `produkty` SET `ilosc`= `ilosc` - '"+value3+"'");

        System.out.println(" ");
        System.out.println("Dodano zamówienie do bazy danych");
        System.out.println(" ");
    }
    public static void edytujZamowienie(String login, String haslo, String imie, String stanowisko, int nrPracownika) {
        wyswietlHistorieZamowien();

        System.out.print("Podaj id zamówienia: ");
        int idZamowienia = scanner.nextInt();
        scanner.nextLine();

        while (true) {
            System.out.println("------------------");
            System.out.println("Co chcesz zmienić?");
            System.out.println("------------------");
            System.out.println("1. Id towaru");
            System.out.println("2. Ilość");
            System.out.println("3. Cenę ogólną");
            System.out.println("4. Adres doręczenia");
            System.out.println("5. Wróć");

            int zmiana = scanner.nextInt();
            switch (zmiana) {
                case 1 -> {
                    Asortyment.wyswietlProdukty();
                    System.out.println("Podaj nowe id towaru: ");
                    int noweId = scanner.nextInt();

                    ObslugaZapytan.executeQuery("UPDATE `zamowienia` SET `id_zamowienia`='"+noweId+"' WHERE `id_zamowienia` = "+idZamowienia+"");
                }
                case 2 -> {
                    System.out.println("Podaj nową ilość towaru: ");
                    int nowaIlosc = scanner.nextInt();

                    ObslugaZapytan.executeQuery("UPDATE `zamowienia` SET `ilosc`='"+nowaIlosc+"' WHERE `id_zamowienia` = "+idZamowienia+"");
                }
                case 3 -> {
                    System.out.println("Podaj nową cenę towaru");
                    int nowaCena = scanner.nextInt();

                    ObslugaZapytan.executeQuery("UPDATE `zamowienia` SET `cena`='"+nowaCena+"' WHERE `id_zamowienia` = "+idZamowienia+"");
                }
                case 4 -> {
                    System.out.println("Podaj nowy adres doręczeń");
                    String nowyAdres = scanner.nextLine();

                    ObslugaZapytan.executeQuery("UPDATE `zamowienia` SET `adres_doreczenia`='"+nowyAdres+"' WHERE `id_zamowienia` = "+idZamowienia+"");
                }
                case 5 -> PanelSkladu.zamowienia(login, haslo, imie, stanowisko, nrPracownika);
                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
    public static void zmienStatusZamowienia() {
        wyswietlHistorieZamowien();

        System.out.print("Podaj id zamówienia: ");
        int idZamowienia = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Podaj nowy status zamówienia: ");
        String statusZamowienia = scanner.nextLine();

        String zapytanie = "UPDATE `zamowienia` SET `status_zamowienia`='"+statusZamowienia+"' WHERE `id_zamowienia` = '"+idZamowienia+"'";
        ObslugaZapytan.executeQuery(zapytanie);

        System.out.println(" ");
        System.out.println("Zmieniono status zamowienia");
        System.out.println(" ");
    }
    public static void wyswietlHistorieZamowien() {
        try {
            ResultSet resultPracownik = ObslugaZapytan.executeSelect("SELECT * FROM `zamowienia`");

            while (resultPracownik.next()) {

                String table1 = resultPracownik.getString("id_zamowienia");
                String table2 = resultPracownik.getString("id_pracownika");
                String table3 = resultPracownik.getString("data_zamowienia");
                String table4 = resultPracownik.getString("godzina_zamowienia");
                String table5 = resultPracownik.getString("id_towaru");
                String table6 = resultPracownik.getString("ilosc");
                String table7 = resultPracownik.getString("cena");
                String table8 = resultPracownik.getString("rodzaj_platnosci");
                String table9 = resultPracownik.getString("rodzaj_odbioru");
                String table10 = resultPracownik.getString("imie_klienta");
                String table11 = resultPracownik.getString("nazwisko_klienta");
                String table12 = resultPracownik.getString("numer_telefonu");
                String table13 = resultPracownik.getString("adres_doreczenia");
                String table14 = resultPracownik.getString("status_zamowienia");

                System.out.println("Nr. Zamówienia: " + table1);
                System.out.println("Pracownik obsługujący: " + table2);
                System.out.println("Data: " + table3);
                System.out.println("Godzina: " + table4);
                System.out.println("Id_towaru: " + table5);
                System.out.println("Ilość sztuk: " + table6);
                System.out.println("Do zapłaty: " + table7);
                System.out.println("Rodzaj płatności: " + table8);
                System.out.println("Rodzaj odbioru: " + table9);
                System.out.println("Imię klienta: " + table10);
                System.out.println("Nazwisko klienta: " + table11);
                System.out.println("Numer telefonu: " + table12);
                System.out.println("Adres doręczenia: " + table13);
                System.out.println("Status zamówienia: " + table14);
                System.out.println("--");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
