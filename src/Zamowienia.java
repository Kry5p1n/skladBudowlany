import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Zamowienia {
    static Scanner scanner = new Scanner(System.in);

    public static void dodajZamowienie(int nrPracownika) {
        Asortyment.wyswietlProdukty();
        System.out.println(" ");

        int value2;
        while (true) {
            System.out.print("Id towaru: ");
            try {
                value2 = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }

        int value3;
        while (true) {
            System.out.print("Ilość sztuk: ");
            try {
                value3 = scanner.nextInt();

                try {
                    ResultSet resultTest = ObslugaZapytan.executeSelect("SELECT * FROM produkty WHERE `id_produktu` = '" + value2 + "'");
                    while (resultTest.next()) {
                        int table1 = resultTest.getInt("ilosc");

                        if (value3 > table1) {
                            System.out.println("Tego produktu jest: " + table1 + " sztuk.");
                            System.out.print("Podaj inną ilość: ");
                            value3 = scanner.nextInt();
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }

        int value4;
        while (true) {
            System.out.print("Do zapłaty (liczba): ");
            try {
                value4 = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadź liczbę.");
                scanner.next();
            }
        }
        scanner.nextLine();
        System.out.print("Rodzaj płatności: ");
        String value5 = scanner.nextLine();
        System.out.print("Rodzaj odbioru: ");
        String value6 = scanner.nextLine();
        System.out.print("Imię klienta: ");
        String value7 = scanner.nextLine();
        System.out.print("Nazwisko klienta: ");
        String value8 = scanner.nextLine();
        int value9;
        while (true) {
            System.out.print("Numer telefonu: ");
            try {
                value9 = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadź liczbę.");
                scanner.next();
            }
        }
        scanner.nextLine();
        System.out.print("Adres doręczenia: ");
        String value10 = scanner.nextLine();
        System.out.print("Status zamówienia: ");
        String value11 = scanner.nextLine();

        ObslugaZapytan.executeQuery("INSERT INTO `zamowienia`(`id_pracownika`, `id_towaru`, `ilosc`, `cena`, `rodzaj_platnosci`, `rodzaj_odbioru`, `imie_klienta`, `nazwisko_klienta`, `numer_telefonu`, `adres_doreczenia`, `status_zamowienia`) VALUES ('" + nrPracownika + "', '" + value2 + "', '" + value3 + "', '" + value4 + "', '" + value5 + "', '" + value6 + "', '" + value7 + "', '" + value8 + "', '" + value9 + "', '" + value10 + "', '" + value11 + "')");
        ObslugaZapytan.executeQuery("UPDATE `produkty` SET `ilosc`= `ilosc` - '" + value3 + "' WHERE id_produktu = '" + value2 + "'");

        System.out.println(" ");
        System.out.println("Dodano zamówienie do bazy danych");
        System.out.println(" ");
    }

    public static void edytujZamowienie(String login, String haslo, String imie, String stanowisko, int nrPracownika) {
        wyswietlHistorieZamowien2();

        int idZamowienia;
        while (true) {
            System.out.print("Podaj id zamówienia: ");
            try {
                idZamowienia = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
        scanner.nextLine();

        while (true) {
            System.out.println("------------------");
            System.out.println("Co chcesz zmienić?");
            System.out.println("------------------");
            System.out.println("1. Dobierz towaru");
            System.out.println("2. Cenę ogólną");
            System.out.println("3. Adres doręczenia");
            System.out.println("4. Wróć");

            int zmiana;
            while (true) {
                System.out.print("Wybierz opcję: ");
                try {
                    zmiana = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                    scanner.next();
                }
            }

            switch (zmiana) {
                case 1 -> {
                    int nowaIlosc;
                    while (true) {
                        System.out.print("Podaj ile chcesz dobrać: ");
                        try {
                            nowaIlosc = scanner.nextInt();

                            try {
                                ResultSet resultTest = ObslugaZapytan.executeSelect("SELECT * FROM zamowienia WHERE `id_zamowienia` = '" + idZamowienia + "' ");

                                while (resultTest.next()) {
                                    int table1 = resultTest.getInt("id_towaru");

                                    ObslugaZapytan.executeQuery("UPDATE `produkty` SET `ilosc`= `ilosc` - '" + nowaIlosc + "' WHERE `id_produktu` =  '" + table1 + "'");
                                }

                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }

                            ObslugaZapytan.executeQuery("UPDATE `zamowienia` SET `ilosc`=`ilosc` + '" + nowaIlosc + "' WHERE `id_zamowienia` = " + idZamowienia + "");
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                            scanner.next();
                        }
                    }

                }
                case 2 -> {
                    int nowaCena;
                    while (true) {
                        System.out.print("Podaj nową cenę towaru: ");
                        try {
                            nowaCena = scanner.nextInt();

                            ObslugaZapytan.executeQuery("UPDATE `zamowienia` SET `cena`='" + nowaCena + "' WHERE `id_zamowienia` = " + idZamowienia + "");
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                            scanner.next();
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Podaj nowy adres doręczeń: ");
                    scanner.nextLine();
                    String nowyAdres = scanner.nextLine();

                    ObslugaZapytan.executeQuery("UPDATE `zamowienia` SET `adres_doreczenia`='" + nowyAdres + "' WHERE `id_zamowienia` = " + idZamowienia + "");
                }
                case 4 -> PanelSkladu.zamowienia(login, haslo, imie, stanowisko, nrPracownika);
                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    public static void zmienStatusZamowienia() {
        wyswietlHistorieZamowien2();

        int idZamowienia;
        while (true) {
            System.out.print("Podaj id zamówienia: ");
            try {
                idZamowienia = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
        scanner.nextLine();
        System.out.print("Podaj nowy status zamówienia: ");
        String statusZamowienia = scanner.nextLine();

        String zapytanie = "UPDATE `zamowienia` SET `status_zamowienia`='" + statusZamowienia + "' WHERE `id_zamowienia` = '" + idZamowienia + "'";
        ObslugaZapytan.executeQuery(zapytanie);

        System.out.println(" ");
        System.out.println("Zmieniono status zamowienia");
        System.out.println(" ");
    }

    public static void wyswietlHistorieZamowien(String login, String haslo, String imie, String stanowisko, int nrPracownika) {
        StringBuilder toFile = new StringBuilder();
        try {
            ResultSet resultSelect = ObslugaZapytan.executeSelect("SELECT z.id_zamowienia, k.login, z.data_zamowienia, z.godzina_zamowienia, p.nazwa, z.ilosc, z.cena, z.rodzaj_platnosci, z.rodzaj_platnosci, z.rodzaj_odbioru, z.imie_klienta, z.nazwisko_klienta, z.numer_telefonu, z.adres_doreczenia, z.status_zamowienia FROM Zamowienia AS Z JOIN Produkty AS P ON Z.id_towaru = P.id_produktu JOIN Konta AS K ON Z.id_pracownika = K.id_konta ORDER BY `Z`.`id_zamowienia` ASC;");

            while (resultSelect.next()) {

                String table1 = resultSelect.getString("id_zamowienia");
                String table2 = resultSelect.getString("login");
                String table3 = resultSelect.getString("data_zamowienia");
                String table4 = resultSelect.getString("godzina_zamowienia");
                String table5 = resultSelect.getString("nazwa");
                String table6 = resultSelect.getString("ilosc");
                String table7 = resultSelect.getString("cena");
                String table8 = resultSelect.getString("rodzaj_platnosci");
                String table9 = resultSelect.getString("rodzaj_odbioru");
                String table10 = resultSelect.getString("imie_klienta");
                String table11 = resultSelect.getString("nazwisko_klienta");
                String table12 = resultSelect.getString("numer_telefonu");
                String table13 = resultSelect.getString("adres_doreczenia");
                String table14 = resultSelect.getString("status_zamowienia");

                System.out.println("Nr. Zamówienia: " + table1);
                System.out.println("Pracownik obsługujący: " + table2);
                System.out.println("Data: " + table3);
                System.out.println("Godzina: " + table4);
                System.out.println("Id_towaru: " + table5);
                System.out.println("Ilość sztuk: " + table6);
                System.out.println("Do zapłaty: " + table7);
                System.out.println("Rodzaj płatności: " + table8);
                System.out.println("Rodzaj odbioru: " + table9);
                System.out.println(" --------------------- ");
                System.out.println("Imię klienta: " + table10);
                System.out.println("Nazwisko klienta: " + table11);
                System.out.println("Numer telefonu: " + table12);
                System.out.println("Adres doręczenia: " + table13);
                System.out.println(" --------------------- ");
                System.out.println("Status zamówienia: " + table14);
                System.out.println("--");

                toFile.append("Nr.: ").append(table1).append("\n");
                toFile.append("Pracownik obsługujący: ").append(table2).append("\n");
                toFile.append("Data: ").append(table3).append("\n");
                toFile.append("Godzina: ").append(table4).append("\n");
                toFile.append("Id_towaru: ").append(table5).append("\n");
                toFile.append("Ilość sztuk: ").append(table6).append("\n");
                toFile.append("Do zapłaty: ").append(table7).append("zł\n");
                toFile.append("Rodzaj płatności: ").append(table8).append("\n");
                toFile.append("Rodzaj odbioru: ").append(table9).append("\n");
                toFile.append("Imię klienta: ").append(table10).append("\n");
                toFile.append("Nazwisko klienta: ").append(table11).append("\n");
                toFile.append("Numer telefonu: ").append(table12).append("\n");
                toFile.append("Adres doręczenia: ").append(table13).append("\n");
                toFile.append("Status zamówienia: ").append(table14).append("\n");
                toFile.append("--\n");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Czy chcesz zapisać zamówienia w pliku txt?");
        System.out.println("1. Tak");
        System.out.println("2. Nie, wróć do menu ");
        int wybor;

        while (true) {
            System.out.print("Wybierz opcję: ");
            try {
                wybor = scanner.nextInt();

                switch (wybor) {
                    case 1 -> {
                        String fileName = "zamowienia.txt";
                        try {
                            FileWriter fileWriter = new FileWriter(fileName);
                            fileWriter.write(toFile.toString());
                            fileWriter.close();
                            System.out.println(" ");
                            System.out.println("Dane zapisano do pliku " + fileName);
                            System.out.println(" ");
                            PanelSkladu.zamowienia(login, haslo, imie, stanowisko, nrPracownika);
                        } catch (IOException e) {
                            System.out.println("Wystąpił błąd podczas zapisywania danych do pliku " + fileName);
                            e.printStackTrace();
                        }
                    }
                    case 2 -> PanelSkladu.zamowienia(login, haslo, imie, stanowisko, nrPracownika);
                    default -> {
                        System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                        PanelSkladu.zamowienia(login, haslo, imie, stanowisko, nrPracownika);
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
    }

    public static void wyswietlHistorieZamowien2() {
        try {
            ResultSet resultSelect = ObslugaZapytan.executeSelect("SELECT z.id_zamowienia, k.login, z.data_zamowienia, z.godzina_zamowienia, p.nazwa, z.ilosc, z.cena, z.rodzaj_platnosci, z.rodzaj_platnosci, z.rodzaj_odbioru, z.imie_klienta, z.nazwisko_klienta, z.numer_telefonu, z.adres_doreczenia, z.status_zamowienia FROM Zamowienia AS Z JOIN Produkty AS P ON Z.id_towaru = P.id_produktu JOIN Konta AS K ON Z.id_pracownika = K.id_konta ORDER BY `Z`.`id_zamowienia` ASC;");

            while (resultSelect.next()) {

                String table1 = resultSelect.getString("id_zamowienia");
                String table2 = resultSelect.getString("login");
                String table3 = resultSelect.getString("data_zamowienia");
                String table4 = resultSelect.getString("godzina_zamowienia");
                String table5 = resultSelect.getString("nazwa");
                String table6 = resultSelect.getString("ilosc");
                String table7 = resultSelect.getString("cena");
                String table8 = resultSelect.getString("rodzaj_platnosci");
                String table9 = resultSelect.getString("rodzaj_odbioru");
                String table10 = resultSelect.getString("imie_klienta");
                String table11 = resultSelect.getString("nazwisko_klienta");
                String table12 = resultSelect.getString("numer_telefonu");
                String table13 = resultSelect.getString("adres_doreczenia");
                String table14 = resultSelect.getString("status_zamowienia");

                System.out.println("Nr. Zamówienia: " + table1);
                System.out.println("Pracownik obsługujący: " + table2);
                System.out.println("Data: " + table3);
                System.out.println("Godzina: " + table4);
                System.out.println("Id_towaru: " + table5);
                System.out.println("Ilość sztuk: " + table6);
                System.out.println("Do zapłaty: " + table7);
                System.out.println("Rodzaj płatności: " + table8);
                System.out.println("Rodzaj odbioru: " + table9);
                System.out.println(" --------------------- ");
                System.out.println("Imię klienta: " + table10);
                System.out.println("Nazwisko klienta: " + table11);
                System.out.println("Numer telefonu: " + table12);
                System.out.println("Adres doręczenia: " + table13);
                System.out.println(" --------------------- ");
                System.out.println("Status zamówienia: " + table14);
                System.out.println("--");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
