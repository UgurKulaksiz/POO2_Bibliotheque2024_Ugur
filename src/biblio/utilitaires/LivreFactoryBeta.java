package biblio.utilitaires;

import biblio.metier.DVD;
import biblio.metier.Livre;
import biblio.metier.Ouvrage;
import biblio.metier.TypeLivre;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LivreFactoryBeta {
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
        //détails propres à la classe Livre
        System.out.println("ISBN : ");
        String isbn = sc.next();
        System.out.println("Pages : ");
        int nbrePages = sc.nextInt();
        sc.skip("\n");

        TypeLivre[] typeLivre = TypeLivre.values();
        List<TypeLivre> listTypeLivre = new ArrayList<>(Arrays.asList(typeLivre));

        int choix = Utilitaire.choixListe(listTypeLivre);
        TypeLivre tl = typeLivre[choix - 1];

        System.out.println("Résumé du livre : ");
        String resume = sc.nextLine();

        Livre l = new Livre(titre, ageMin, dateParution, prixLocation, langue, genre, isbn, nbrePages, tl, resume);

        return l;
    }
}
