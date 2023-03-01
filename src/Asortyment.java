import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Asortyment {
    static Scanner scanner = new Scanner(System.in);

    public static void wyswietlProdukty() {
        try {
            ResultSet resultSelect = ObslugaZapytan.executeSelect("SELECT * FROM `produkty`");

            while (resultSelect.next()) {

                String table1 = resultSelect.getString("id_produktu");
                String table2 = resultSelect.getString("nazwa");
                String table3 = resultSelect.getString("ilosc");
                String table4 = resultSelect.getString("cena_za_sztuke");

                System.out.println("Nr. " + table1 + " " + table2 + " ilość: " + table3 + " - " + table4 + "zł/szt");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dodajProdukt() {
        System.out.println(" ");

        System.out.print("Podaj nazwę produktu: ");
        String productName = scanner.nextLine();

        double productPrice;
        while (true) {
            System.out.print("Podaj cenę produktu za sztukę(zl,gr): ");
            try {
                productPrice = scanner.nextDouble();

                int quantity;
                while (true) {
                    System.out.print("Podaj ilość dostępnych sztuk: ");
                    try {
                        quantity = scanner.nextInt();

                        if (quantity > 0 && productPrice > 0) {
                            ObslugaZapytan.executeQuery("INSERT INTO `produkty`(`nazwa`, `ilosc`, `cena_za_sztuke`) VALUES ('" + productName + "','" + quantity + "','" + productPrice + "')");

                            System.out.println(" ");
                            System.out.println("Dodano nowy produkt.");
                        } else {
                            System.out.println("Nieprawidłowe wartości. Spróbuj ponownie");
                            dodajProdukt();
                        }
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                        scanner.next();
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }

        System.out.println(" ");
    }

    public static void usunProdukt() {
        System.out.println(" ");
        wyswietlProdukty();
        System.out.println(" ");

        int produktId;
        while (true) {
            System.out.print("Podaj Numer produktu: ");
            try {
                produktId = scanner.nextInt();

                ObslugaZapytan.executeQuery("UPDATE `produkty` SET `ilosc`='0' WHERE `id_produktu` = '"+produktId+"' ");

                System.out.println(" ");
                System.out.println("Usunięto produkt ze sprzedaży");
                System.out.println(" ");
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
    }

    public static void uzupelnijProdukt() {
        System.out.println(" ");
        wyswietlProdukty();
        System.out.println(" ");

        int produktId;
        while (true) {
            System.out.print("Podaj Numer produktu: ");
            try {
                produktId = scanner.nextInt();

                int nowaIlosc;
                while (true) {
                    System.out.print("Podaj nową ilość: ");
                    try {
                        nowaIlosc = scanner.nextInt();

                        ObslugaZapytan.executeQuery("UPDATE `produkty` SET `ilosc`=`ilosc` + '"+nowaIlosc+"' WHERE `id_produktu` = '"+produktId+"' ");

                        System.out.println(" ");
                        System.out.println("Zaktualizowano ilość produktu.");
                        System.out.println(" ");

                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                        scanner.next();
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }

    }

    public static void zmienCeneProduktu() {
        System.out.println(" ");
        wyswietlProdukty();
        System.out.println(" ");

        int produktId;
        while (true) {
            System.out.print("Podaj Numer Produktu: ");
            try {
                produktId = scanner.nextInt();

                double nowaCena;
                while (true) {
                    System.out.print("Podaj nową cenę produktu: ");
                    try {
                        nowaCena = scanner.nextDouble();

                        if (nowaCena > 0) {
                            ObslugaZapytan.executeQuery("UPDATE `produkty` SET `cena_za_sztuke`='" + nowaCena + "' WHERE `id_produktu` = '" + produktId + "'");

                            System.out.println(" ");
                            System.out.println("Zmieniono cenę.");
                            System.out.println(" ");
                        }
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                        scanner.next();
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
    }

}
