package biblio.utilitaires;

import biblio.metier.DVD;
import biblio.metier.Ouvrage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DVDFactoryBeta {
    protected Scanner sc = new Scanner(System.in);

    public Ouvrage create() {
        System.out.println("Titre : ");
        String titre = sc.nextLine();
        System.out.println("Âge minimum : ");
        int ageMin = sc.nextInt();
        sc.skip("\n");
        System.out.println("Date de parution : ");
        LocalDate dateParution = Utilitaire.lecDate();
        System.out.println("Prix de location : ");
        double prixLocation = sc.nextDouble();
        sc.skip("\n");
        System.out.println("Langue : ");
        String langue = sc.nextLine();
        System.out.println("Genre : ");
        String genre = sc.nextLine();

        System.out.println("DETAILS du DVD");
        //détails propres à la classe DVD
        System.out.println("Code : ");
        long code = sc.nextLong();
        System.out.println("Durée totale : ");
        LocalTime dureeTotale = Utilitaire.lecTime();
        byte nbreBonus = sc.nextByte();

        DVD dvd = new DVD(titre, ageMin, dateParution, prixLocation, langue, genre, code, dureeTotale, nbreBonus);

        System.out.println("Autres langues");
        List<String> langues = new ArrayList<>(Arrays.asList("anglais", "français", "italien", "allemand", "fin"));
        int choix;
        do {
            choix = Utilitaire.choixListe(langues);
            if (choix == langues.size()) break;

            String originlangue = langues.get(choix - 1);
            if (originlangue.equals(langue)) {
                System.out.println("C'est la langue originale");
                continue;
            }
            boolean ok = dvd.getAutresLangues().add(langues.get(choix - 1));

            if (ok) System.out.println("Langue ajoutée");
            else
                System.out.println("Langue déjà encodée");

        } while (true);

        System.out.println("Sous-titres");
        do {
            choix = Utilitaire.choixListe(langues);
            if (choix == langues.size()) break;

            String originlangue = langues.get(choix - 1);
            boolean ok = dvd.getSousTitres().add(langues.get(choix - 1));

            if (ok) System.out.println("Langue ajoutée");
            else
                System.out.println("Langue déjà encodée");

        } while (true);

        return dvd;
    }
}
