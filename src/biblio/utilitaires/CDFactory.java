package biblio.utilitaires;

import biblio.metier.CD;
import biblio.metier.Ouvrage;

import java.time.LocalDate;
import java.time.LocalTime;

public class CDFactory extends OuvrageFactory {
    @Override
    public Ouvrage addDetailOuvrage(String titre, int ageMin, LocalDate dateParution, double prixLocation, String langue, String genre) {
        System.out.println("Code : ");
        long code = sc.nextLong();
        System.out.println("Nombre de plages :");
        byte nbrePlages = sc.nextByte();
        LocalTime dureeTotale = Utilitaire.lecTime();

        CD cd = new CD(titre, ageMin, dateParution, prixLocation, langue, genre, code, nbrePlages, dureeTotale);
        return cd;
    }
}
