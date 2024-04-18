package biblio.mvcold.controller;

import biblio.metier.Exemplaire;
import biblio.metier.Ouvrage;

import java.util.List;

public interface ControllerSpecialOuvrage {
    List<Exemplaire> listerExemplaireOuvrage(Ouvrage o);

    List<Exemplaire> listerExemplaireOuvrageLocation(Ouvrage o, boolean enLocation);

    double amendeRetard(Ouvrage o,int nj);
}
