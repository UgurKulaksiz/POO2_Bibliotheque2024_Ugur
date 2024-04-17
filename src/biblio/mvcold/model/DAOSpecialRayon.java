package biblio.mvcold.model;

import biblio.metier.Exemplaire;
import biblio.metier.Rayon;

import java.util.List;

public interface DAOSpecialRayon {
    public List<Exemplaire> listerExemplaires(Rayon r) ;
}
