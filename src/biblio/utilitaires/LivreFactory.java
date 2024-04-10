package biblio.utilitaires;

import biblio.metier.Livre;
import biblio.metier.Ouvrage;
import biblio.metier.TypeLivre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LivreFactory extends OuvrageFactory {
    protected String isbn;
    protected int nbrePages;
    protected TypeLivre tl;
    protected String resume;

    public Ouvrage create(){
        super.base();
        System.out.println("ISBN : ");
        isbn = sc.next();
        System.out.println("Pages : ");
        nbrePages = sc.nextInt();
        sc.skip("\n");

        TypeLivre[] ttl = TypeLivre.values();
        List<TypeLivre> ltl = new ArrayList<>(Arrays.asList(ttl));

        int choix = Utilitaire.choixListe(ltl);
        tl = ttl[choix-1];

        System.out.println("Résumé du livre :");
        resume = sc.nextLine();

        Livre l=new Livre(titre,ageMin,dateParution,prixLocation,langue,genre,isbn,nbrePages,tl,resume);

        return l;
    }
}
