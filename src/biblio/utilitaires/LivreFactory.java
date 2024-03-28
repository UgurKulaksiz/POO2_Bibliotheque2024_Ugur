package biblio.utilitaires;

import biblio.metier.Livre;
import biblio.metier.Ouvrage;
import biblio.metier.TypeLivre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LivreFactory extends OuvrageFactory {
    @Override
    public Ouvrage addDetailOuvrage(String titre, int ageMin, LocalDate dateParution, double prixLocation, String langue, String genre) {
        System.out.println("ISBN : ");
        String isbn = sc.next();
        System.out.println("Pages : ");
        int nbrePages = sc.nextInt();
        sc.skip("\n");

        TypeLivre[] typeLivre = TypeLivre.values();
        List<TypeLivre> listTypeLivre = new ArrayList<>(Arrays.asList(typeLivre));

        int choix = Utilitaire.choixListe(listTypeLivre);
        TypeLivre tl = typeLivre[choix - 1];

        System.out.println("Résumé du livre :");
        String resume = sc.nextLine();

        Livre l = new Livre(titre, ageMin, dateParution, prixLocation, langue, genre, isbn, nbrePages, tl, resume);
        return l;
    }
}
