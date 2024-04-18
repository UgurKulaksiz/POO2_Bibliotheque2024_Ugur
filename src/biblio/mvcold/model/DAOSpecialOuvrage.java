package biblio.mvcold.model;

import biblio.metier.Exemplaire;
import biblio.metier.Ouvrage;

import java.util.List;

public interface DAOSpecialOuvrage {
    public List<Exemplaire> listerExemplaire(Ouvrage o);

    public List<Exemplaire> listerExemplaire(Ouvrage o, boolean enLocation);

    public double amendeRetard(Ouvrage o, int nj);
}
