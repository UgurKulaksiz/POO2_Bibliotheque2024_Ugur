package biblio.utilitaires;

import biblio.metier.CD;
import biblio.metier.Ouvrage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class CDFactoryBeta {
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

        System.out.println("DETAILS du CD");
        //détails propres à la classe CD
        System.out.println("Code : ");
        long code = sc.nextLong();
        System.out.println("Nombre de plages :");
        byte nbrePlages = sc.nextByte();
        System.out.println("Durée totale : ");
        LocalTime dureeTotale = Utilitaire.lecTime();

        CD cd = new CD(titre, ageMin, dateParution, prixLocation, langue, genre, code, nbrePlages, dureeTotale);

        return cd;
    }

}
