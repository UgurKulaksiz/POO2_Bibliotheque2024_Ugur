package biblio.utilitaires.comparators;

import biblio.metier.Auteur;

import java.util.Comparator;

public class AuteurComparator implements Comparator<Auteur> {
    @Override
    public int compare(Auteur a1, Auteur a2){
        if (a1.getNom().compareTo(a2.getNom()) != 0) return a1.getNom().compareTo(a2.getNom());
        return a1.getPrenom().compareTo(a2.getPrenom());
    }
}
