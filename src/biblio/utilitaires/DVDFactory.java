package biblio.utilitaires;

import biblio.metier.DVD;
import biblio.metier.Ouvrage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DVDFactory extends OuvrageFactory {
    @Override
    public Ouvrage addDetailOuvrage(String titre, int ageMin, LocalDate dateParution, double prixLocation, String langue, String genre) {
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
            dvd.getAutresLangues().add(langues.get(choix - 1));//TODO vérifier unicité ou utiliser set et pas de doublon avec langue d'origine

        } while (true);


        System.out.println("Sous-titres");
        do {
            choix = Utilitaire.choixListe(langues);

            if (choix == langues.size()) break;
            dvd.getSousTitres().add(langues.get(choix - 1));//TODO vérifier unicité ou utiliser set

        } while (true);

        return dvd;
    }
}
