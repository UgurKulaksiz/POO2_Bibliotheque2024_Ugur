package biblio.mvc.model;

import biblio.metier.Auteur;
import biblio.metier.Livre;
import biblio.metier.Ouvrage;
import biblio.metier.TypeLivre;
import biblio.mvc.observer.Subject;

import java.util.List;
import java.util.Set;

public abstract class DAOAuteur extends Subject {
    public abstract Auteur add(Auteur a);

    public abstract boolean remove(Auteur a);

    public abstract Auteur update(Auteur a);

    public abstract Auteur read(Auteur rechAuteur);

    public abstract List<Auteur> getAll();


    public abstract Set<Ouvrage> listerOuvrages(Auteur a);

    public abstract List<Livre> listerLivre(Auteur a, TypeLivre typeLive);

    public abstract List<Ouvrage> listerOuvrages(Auteur a, String genre);

    public List<Auteur> getNotification() {
        return getAll();
    }
}
