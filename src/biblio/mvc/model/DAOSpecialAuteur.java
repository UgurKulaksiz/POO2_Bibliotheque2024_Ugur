package biblio.mvc.model;

import biblio.metier.Auteur;
import biblio.metier.Livre;
import biblio.metier.Ouvrage;
import biblio.metier.TypeLivre;

import java.util.List;
import java.util.Set;

public interface DAOSpecialAuteur {
    public Set<Ouvrage> listerOuvrages(Auteur a);

    public List<Livre> listerLivre(Auteur a, TypeLivre tl);


    public List<Ouvrage> listerOuvrages(Auteur a, String genre) ;
}
