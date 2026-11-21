import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HA1_Radwan {

    public static void main(String[] args) {
        String neuStart;
        Scanner sc = new Scanner(System.in);

        do {
            char[][] karten = new char[4][5];
            boolean[][] aufgedeckt = new boolean[4][5];
            int paare = 10;
            int gefundenePaare = 0;
            int zugzahl = 30;
            int benoetigteZuege = 0;

            Kartenliste(karten); // Methode hier, um Karten zu füllen.

            while (zugzahl > 0 && gefundenePaare < 10) {
                printSpielfeld(karten, aufgedeckt); // printSpielfeld Methode anzeigen

                boolean richtigEingegebenSpalte = true;
                int spaltenNummer1 = 0;

                do {
                    richtigEingegebenSpalte = true;
                    System.out.print("Bitte Spalte von A bis E eingeben für erste Karte:  ");
                    String spalte1 = sc.next().toUpperCase(); // Erlaubt auch Kleinbuchstaben

                    if (spalte1.equals("A")) {
                        spaltenNummer1 = 0;
                    } else if (spalte1.equals("B")) {
                        spaltenNummer1 = 1;
                    } else if (spalte1.equals("C")) {
                        spaltenNummer1 = 2;
                    } else if (spalte1.equals("D")) {
                        spaltenNummer1 = 3;
                    } else if (spalte1.equals("E")) {
                        spaltenNummer1 = 4;
                    } else {
                        richtigEingegebenSpalte = false;
                        System.out.println("Bitte ein Buchstabe zwischen A und E eingeben!");
                        System.out.println();
                    }
                } while (richtigEingegebenSpalte == false);

                int zeile1 = 0;
                boolean richtigEingegebenZeile = false;

                do {
                    try {
                        System.out.print("Bitte Zeile für erste Karte eingeben (1-4): ");

                        zeile1 = sc.nextInt();

                        // 1. Prüfen: Ist Zahl gültig
                        if (zeile1 < 1 || zeile1 > 4) {
                            System.out.println("Die Zeile kann nur 1, 2, 3 oder 4 sein.");
                            System.out.println();
                            richtigEingegebenZeile = false;

                            // 2. Prüfen: Ist das Feld schon offen
                        } else if (aufgedeckt[zeile1 - 1][spaltenNummer1] == true) {
                            System.out.println("Dieses Feld ist schon aufgedeckt! Wähle ein anderes.");
                            System.out.println();
                            richtigEingegebenZeile = false;

                            // 3. richtig eingegeben
                        } else {
                            richtigEingegebenZeile = true;
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Das war leider keine Zahl! Bitte eine Zahl eingeben.");
                        System.out.println();
                        richtigEingegebenZeile = false;
                        sc.next(); // Scanner leeren
                    }
                } while (richtigEingegebenZeile == false);

                aufgedeckt[zeile1 - 1][spaltenNummer1] = true;
                printSpielfeld(karten, aufgedeckt);

                // Ab hier 2te Karte ziehen

                boolean richtigEingegebenSpalte2 = true;
                int spaltenNummer2 = 0;

                do {
                    richtigEingegebenSpalte2 = true;
                    System.out.print("Bitte Spalte von A bis E eingeben für zweite Karte:  ");
                    String spalte2 = sc.next().toUpperCase();

                    if (spalte2.equals("A")) {
                        spaltenNummer2 = 0;
                    } else if (spalte2.equals("B")) {
                        spaltenNummer2 = 1;
                    } else if (spalte2.equals("C")) {
                        spaltenNummer2 = 2;
                    } else if (spalte2.equals("D")) {
                        spaltenNummer2 = 3;
                    } else if (spalte2.equals("E")) {
                        spaltenNummer2 = 4;
                    } else {
                        richtigEingegebenSpalte2 = false;
                        System.out.println(
                                "Das war leider kein Buchstabe! Bitte ein Buchstabe zwischen A und E eingeben.");
                        System.out.println();
                    }
                } while (richtigEingegebenSpalte2 == false);

                int zeile2 = 0;
                boolean richtigEingegebenZeile2 = true;
                do {
                    try {
                        System.out.print("Bitte Zeile für zweite Karte eingeben (1-4): ");
                        System.out.println();
                        zeile2 = sc.nextInt();

                        // 1. Prüfen: Ist Zahl gültig?
                        if (zeile2 < 1 || zeile2 > 4) {
                            System.out.println("Die Zeile kann nur 1, 2, 3 oder 4 sein.");
                            richtigEingegebenZeile2 = false;

                            // 2. Prüfen: Ist das Feld schon offen?
                        } else if (aufgedeckt[zeile2 - 1][spaltenNummer2] == true) {
                            System.out.println("Dieses Feld ist schon aufgedeckt!");
                            System.out.println();
                            richtigEingegebenZeile2 = false;

                            // 3. Prüfen: Ist Koordinate genau wie Karte 1?
                        } else if (istGleicheKoordinate(zeile1, spaltenNummer1, zeile2, spaltenNummer2) == true) {
                            System.out.println("Fehler: Du hast exakt dieselbe Karte wie beim ersten Mal gewählt!");
                            System.out.println();
                            richtigEingegebenZeile2 = false;

                            // 4. alles richtig
                        } else {
                            richtigEingegebenZeile2 = true;
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Keine Zahl! Bitte neu eingeben.");
                        System.out.println();
                        richtigEingegebenZeile2 = false;
                        sc.next();
                    }
                } while (richtigEingegebenZeile2 == false);

                aufgedeckt[zeile2 - 1][spaltenNummer2] = true;
                printSpielfeld(karten, aufgedeckt);

                if (checkGleich(karten, zeile1, spaltenNummer1, zeile2, spaltenNummer2) == true) {
                    System.out.println("Das war ein Treffer!");
                    zugzahl = zugzahl - 2;
                    gefundenePaare = gefundenePaare + 1;
                    paare = paare - 1;
                    benoetigteZuege = benoetigteZuege + 1;
                    System.out.println("Übrige Paare: " + paare);
                    System.out.print("Übrige Züge: " + zugzahl);

                } else {
                    System.out.println("Das war leider kein Treffer!");

                    try {
                        Thread.sleep(3000); // Wartet 3000 Millisekunden
                    } catch (InterruptedException e) {
                    }

                    zugzahl = zugzahl - 2;
                    benoetigteZuege = benoetigteZuege + 1;
                    System.out.print("Übrige Züge: " + zugzahl);
                    System.out.println();

                    aufgedeckt[zeile1 - 1][spaltenNummer1] = false; // ungleiche Karten wieder verdecken
                    aufgedeckt[zeile2 - 1][spaltenNummer2] = false;
                }
            }

            if (gefundenePaare != 10 || zugzahl <= 0) {
                System.out.println();
                System.out.println("Du hast leider verloren!");
            } else {
                System.out.println("Herzlichen Glückwunsch! Du hast alle Paare gefunden!");
            }

            // Statistik am Ende des Spiels
            System.out.println("Du hast " + gefundenePaare + " Paare gefunden und hast dafür " + benoetigteZuege
                    + " Züge benötigt.");

            // Neustart abfragen
            System.out.println("Möchten Sie nochmal spielen? (J/N)?");
            neuStart = sc.next();

        } while (neuStart.equalsIgnoreCase("J"));

        sc.close();

    }

    private static void Kartenliste(char[][] karten) { // Kartenliste erstellen (A-J also bis 10, je zweimal)
        char[] symbole = {
                'A', 'B', 'C', 'D', 'E',
                'F', 'G', 'H', 'I', 'J'
        };

        ArrayList<Character> kartenStapel = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            kartenStapel.add(symbole[i]);
            kartenStapel.add(symbole[i]);
        }
        Collections.shuffle(kartenStapel);

        for (int zeile = 0; zeile < 4; zeile++) {
            for (int spalte = 0; spalte < 5; spalte++) {
                karten[zeile][spalte] = kartenStapel.remove(0);
            }
        }
    }

    private static void printSpielfeld(char[][] karten, boolean[][] aufgedeckt) {

        for (int i = 0; i < 42; i++) {
            System.out.println(); // Leere Zeilen damit der Spieler nicht das vorherige Spielfeld sehen kann
                                  // Hinweis: Konsolenfenster vollständig öffnen
        }

        System.out.println("    A   B   C   D   E");
        System.out.println("  ---------------------");

        for (int zeile = 0; zeile < 4; zeile++) {
            System.out.print((zeile + 1) + " |");

            for (int spalte = 0; spalte < 5; spalte++) {

                if (aufgedeckt[zeile][spalte] == true) {
                    System.out.print(" " + karten[zeile][spalte] + " |");
                } else {
                    System.out.print(" ~ |");
                }
            }
            System.out.println();
            System.out.println("  ---------------------");
        }
    }

    private static boolean istGleicheKoordinate(int zeile1, int spaltenNummer1, int zeile2, int spaltenNummer2) {

        if (zeile1 == zeile2 && spaltenNummer1 == spaltenNummer2) {
            return true; // Gleiche Koordinate gewählt. Fehler
        }
        return false; // nicht die gleiche Koordinate gewählt
    }

    private static boolean checkGleich(char karten[][], int zeile1, int spaltenNummer1, int zeile2,
            int spaltenNummer2) {

        char karte1 = karten[zeile1 - 1][spaltenNummer1];
        char karte2 = karten[zeile2 - 1][spaltenNummer2];

        if (karte1 == karte2) {
            return true; // Beide Karten sind gleich

        } else {
            return false; // Karten sind nicht gleich
        }

    }

}
