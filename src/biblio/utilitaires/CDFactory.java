package biblio.utilitaires;

import biblio.metier.CD;
import biblio.metier.Ouvrage;

import java.time.LocalDate;
import java.time.LocalTime;

public class CDFactory extends OuvrageFactory {
    protected  long code;
    protected  byte nbrePlages;
    protected LocalTime dureeTotale;


    public Ouvrage create() {
        System.out.println("Code : ");
        code = sc.nextLong();
        System.out.println("Nombre de plages : ");
        nbrePlages = sc.nextByte();
        System.out.println("Durée totale : ");
        dureeTotale = Utilitaire.lecTime();

        CD cd = new CD(titre, ageMin, dateParution, prixLocation, langue, genre, code, nbrePlages, dureeTotale);
        return cd;
    }
}
