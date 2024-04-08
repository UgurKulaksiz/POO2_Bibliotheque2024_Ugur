package biblio.utilitaires.comparators;

import biblio.metier.Rayon;

import java.util.Comparator;

public class RayonComparator implements Comparator<Rayon> {
    @Override
    public int compare(Rayon r1, Rayon r2){
        return r1.getCodeRayon().compareTo(r2.getCodeRayon());
    }
}
