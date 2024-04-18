package biblio.mvcold.controller;

import biblio.metier.Exemplaire;
import biblio.metier.Rayon;

import java.util.List;

public interface ControllerSpecialRayon {
    public List<Exemplaire> listerExemplaires(Rayon r);
}
