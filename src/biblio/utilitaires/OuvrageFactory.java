package biblio.utilitaires;

import biblio.metier.Ouvrage;

import java.time.LocalDate;
import java.util.Scanner;

public abstract class OuvrageFactory {
    protected Scanner sc = new Scanner(System.in);

    protected String titre;
    protected int ageMin;
    protected LocalDate dateParution;
    protected double prixLocation;
    protected String langue;
    protected String genre;

    public void base() {
        System.out.println("Titre : ");
        titre = sc.nextLine();
        System.out.println("Âge minimum : ");
        ageMin = sc.nextInt();
        sc.skip("\n");
        System.out.println("Date de parution : ");
        dateParution = Utilitaire.lecDate();
        System.out.println("Prix de location : ");
        prixLocation = sc.nextDouble();
        sc.skip("\n");
        System.out.println("Langue : ");
        langue = sc.nextLine();
        System.out.println("Genre : ");
        genre = sc.nextLine();
    }

    public abstract Ouvrage create();
}
