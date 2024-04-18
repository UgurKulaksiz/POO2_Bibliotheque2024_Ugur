package biblio.mvcold.controller;

import biblio.metier.Auteur;
import biblio.metier.Livre;
import biblio.metier.Ouvrage;
import biblio.metier.TypeLivre;

import java.util.List;
import java.util.Set;

public interface ControllerSpecialAuteur {
    public Set<Ouvrage> listerOuvrages(Auteur a) ;

    public List<Livre> listerLivre(Auteur a, TypeLivre tl) ;
    public List<Ouvrage> listerOuvrages(Auteur a, String genre) ;
}
