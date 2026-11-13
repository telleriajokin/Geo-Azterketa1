
package paagbi;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import paagbi.model.klaseak.Hiria;
import paagbi.model.klaseak.Hiriak;
import paagbi.model.klaseak.Koordenatuak;

import paagbi.model.marshal.marshal;
import paagbi.model.unmarshal.unmarshal;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int aukera;

        do {

            System.out.println("\n===== KOORDENATUEN JOKOA =====");
            System.out.println("1. Hiri berria gehitu");
            System.out.println("2. Hiri guztiak erakutsi");
            System.out.println("3. Jolastu");
            System.out.println("4. Hiri hurbilena bilatu");
            System.out.println("5. Irten");
            System.out.print("Aukeratu aukera bat: ");

            try {
                aukera = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Zenbaki bat sartu behar duzu!");
                aukera = 0;
                continue;
            }

            switch (aukera) {

                case 1:
                    gehituHiria(sc);
                    break;

                case 2:
                    erakutsiHiriak();
                    break;

                case 3:
                    jolastu(sc);
                    break;

                case 4:
                    hurbilenekoHiriaBilatu(sc);
                    break;

                case 5:
                    System.out.println("Agur!");
                    break;

                default:
                    System.out.println("Aukera ez da zuzena.");
            }

        } while (aukera != 5);

        sc.close();
    }

    private static void gehituHiria(Scanner sc) {

        try {

            Hiriak hiriak = unmarshal.irakurri();

            System.out.print("Hiriaren izena: ");
            String izena = sc.nextLine();

            System.out.print("Latitudea: ");
            String latitudea = sc.nextLine();

            System.out.print("Longitudea: ");
            String longitudea = sc.nextLine();

            Koordenatuak koordenatuak =
                    new Koordenatuak(latitudea, longitudea);

            Hiria hiria = new Hiria(izena, koordenatuak);

            hiriak.gehituHiria(hiria);

            marshal.gorde(hiriak);

            System.out.println("Hiria ondo gorde da.");

        } catch (Exception e) {

            System.out.println("Errorea: " + e.getMessage());
        }
    }

    private static void erakutsiHiriak() {

        try {

            Hiriak hiriak = unmarshal.irakurri();

            if (hiriak.getHiriak().isEmpty()) {
                System.out.println("Ez dago daturik.");
                return;
            }

            for (Hiria h : hiriak.getHiriak()) {

                System.out.println(
                        h.getIzena()
                                + " ("
                                + h.getKoordenatuak().getLatitudea()
                                + ", "
                                + h.getKoordenatuak().getLongitudea()
                                + ")"
                );
            }

        } catch (Exception e) {

            System.out.println("Errorea: " + e.getMessage());
        }
    }

    private static void jolastu(Scanner sc) {

        try {

            Hiriak hiriakXML = unmarshal.irakurri();

            List<Hiria> hiriak = hiriakXML.getHiriak();

            if (hiriak.isEmpty()) {

                System.out.println("Ez dago hiriarik gordeta.");
                return;
            }

            Random random = new Random();

            Hiria hiria = hiriak.get(
                    random.nextInt(hiriak.size())
            );

            System.out.println("\n===== JOKOA =====");

            System.out.println(
                    "Latitudea: "
                            + hiria.getKoordenatuak().getLatitudea()
            );

            System.out.println(
                    "Longitudea: "
                            + hiria.getKoordenatuak().getLongitudea()
            );

            System.out.print("Zein hiria da? ");

            String erantzuna = sc.nextLine();

            if (erantzuna.equalsIgnoreCase(hiria.getIzena())) {

                System.out.println("Zuzena!");

            } else {

                System.out.println("Okerra!");
                System.out.println(
                        "Erantzun zuzena: " + hiria.getIzena()
                );
            }

        } catch (Exception e) {

            System.out.println("Errorea jokoan: " + e.getMessage());
        }
    }

    /**
     * XML fitxategitik hiriak irakurtzen ditu eta erabiltzaileak
     * emandako koordenatuetatik hurbilen dagoen hiria erakusten du.
     *
     * @param sc erabiltzailearen datuak irakurtzeko Scanner objektua
     */
    private static void hurbilenekoHiriaBilatu(Scanner sc) {

        try {

            Hiriak hiriak = unmarshal.irakurri();

            if (hiriak.getHiriak().isEmpty()) {
                System.out.println("Ez dago hiriarik gordeta.");
                return;
            }

            System.out.print("Latitudea: ");
            String latitudea = sc.nextLine();

            System.out.print("Longitudea: ");
            String longitudea = sc.nextLine();

            Koordenatuak puntua =
                    new Koordenatuak(latitudea, longitudea);

            Hiria hurbilena = hurbilenekoHiria(
                    puntua,
                    hiriak.getHiriak()
            );

            if (hurbilena != null) {
                System.out.println(
                        "Hiri hurbilena: " + hurbilena.getIzena()
                );
            }

        } catch (Exception e) {

            System.out.println("Errorea: " + e.getMessage());
        }
    }

    /**
     * Emandako koordenatuetatik hurbilen dagoen hiria bilatzen du.
     *
     * @param puntua bilaketa egiteko koordenatuak
     * @param hiriak bilatu beharreko hirien zerrenda
     * @return hurbilen dagoen Hiria objektua
     */
    private static Hiria hurbilenekoHiria(
            Koordenatuak puntua,
            List<Hiria> hiriak) {

        Hiria hurbilena = null;
        double distantziaMin = Double.MAX_VALUE;

        for (Hiria h : hiriak) {

            double distantzia =
                    puntua.distantzia(h.getKoordenatuak());

            if (distantzia < distantziaMin) {

                distantziaMin = distantzia;
                hurbilena = h;
            }
        }

        return hurbilena;
    }
}
